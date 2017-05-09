package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingClass;
//import com.google.gson.Gson;
import com.infoshareacademy.jjdd1.teamerror.fileUpload.SourceFilesChecker;
import com.infoshareacademy.jjdd1.teamerror.file_loader.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by krystianskrzyszewski on 19.04.17.
 */

@WebServlet(urlPatterns = "/calc")
public class InitialServlet extends HttpServlet {

    private final String TRIP_FULL_COST_SESSION_ATTR = "fripFullCost";
    private final Logger LOGGER = LoggerFactory.getLogger(InitialServlet.class);
    private int switcher = 0;

    @Inject
    SavingClass savingClass;

    private InitialData initialData = new InitialData();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.debug("servlet request");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        if (SourceFilesChecker.checkForSourceFiles(req, resp)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/missingFiles.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        // session thingy
        HttpSession session = req.getSession(true);
        TripFullCost cost = (TripFullCost) session.getAttribute(TRIP_FULL_COST_SESSION_ATTR);
        if (cost == null) {
            cost = new TripFullCost();
            cost.setTripFullCost(initialData.filesContent, initialData.petrolFileFilter, initialData.currencyFileFilter);
//            cost.setCountryAndCurrency(new CountryAndCurrency());

            session.setAttribute(TRIP_FULL_COST_SESSION_ATTR, cost);
        }

        // countries/currencies check
        initialData.filesContent.getPetrolDataFile();
        initialData.filesContent.getCurrencyInfoFile();
        initialData.countryAndCurrency.setFilesContent(initialData.filesContent);
        initialData.countryAndCurrencyList = initialData.countryAndCurrency.getCountryAndCurrency();


        // proceed trendy.jsp or tripCost.jsp
        if (req.getParameter("trendy") != null || req.getParameter("tripCost") != null) {

            if(switcher == 0){
                switcher++;

                cost.setCountry(req.getParameter("country").toUpperCase());
                cost.setFuelType(req.getParameter("fuelType"));
                cost.setDate1(req.getParameter("date1").replaceAll("/",""));
                cost.setDate2(req.getParameter("date2").replaceAll("/",""));
                cost.setFuelUsage(req.getParameter("fuelUsage"));
                cost.setDistance(req.getParameter("fullDistance"));
                LOGGER.debug("country-{} fuel type-{}", req.getParameter("country"), req.getParameter("fuelType"));
                LOGGER.info("calculated trend for trip: country-{} currency-{} fuel type-{}",
                        cost.getCountry(), cost.getCurrency(), cost.getFuelType());
                LOGGER.info("servlet req params: date1-{} date2-{} fuel usage-{} " +
                        "full distance-{}", cost.getDate1(), cost.getDate2(), cost.getFuelUsage(), cost.getDistance());
                try{
                    cost.costCount();
                }catch(Exception e){
                    LOGGER.error("Something went wrong. Please check your input (above)", e);
                }

                LOGGER.info("servlet req params: date1-{} date2-{} fuel usage-{} " +
                        "full distance-{} country-{} fuelType-{}", cost.getDate1(), cost.getDate2(), cost.getFuelUsage(), cost.getDistance());
                LOGGER.info(cost.toString());

                initialData.trendy.setTripFullCost(cost);
                initialData.trendy.setTrendy();
                LOGGER.info("calculated trend for trip: country-{} currency-{} fuel type-{}",
                        cost.getCountry(), cost.getCurrency(), cost.getFuelType());
            }

            if(req.getParameter("trendy") != null) {
                String periodDateFrom = req.getParameter("periodDateFrom");
                String periodDateTill = req.getParameter("periodDateTill");
                String[] startingDays = req.getParameterValues("startingDays");
                String tripLength = req.getParameter("tripLength");
                LOGGER.debug("Trendy parameters - DateFrom: {} DateTill: {} TripLength: {} WeekDays: {}",
                        periodDateFrom, periodDateTill, tripLength, startingDays);
                if (periodDateFrom != null && periodDateTill != null & tripLength != null && startingDays != null) {
                    periodDateFrom = periodDateFrom.replaceAll("/", "");
                    periodDateTill = periodDateTill.replaceAll("/", "");
                    initialData.trendy.setTrendyPeriodFrom(periodDateFrom);
                    initialData.trendy.setTrendyPeriodTill(periodDateTill);
                    initialData.trendy.setTripLength(tripLength);
                    initialData.trendy.setStartingDays(new HashSet<>(Arrays.asList(startingDays)));

                    LOGGER.debug("Trendy parameters changed - DateFrom: {} DateTill: {} TripLength: {}",
                            periodDateFrom, periodDateTill, tripLength);
                }
            }

//            Gson gson = new Gson();
//            String json1 = gson.toJson(initialData.trendy.getPeriodTrendy().keySet());
//            LOGGER.info("Map key set: {} ",json1);
//            String json2 = gson.toJson(initialData.trendy.getPeriodTrendy().values());
//            LOGGER.info("Values: {}", json2);



            req.setAttribute("periodTrendy", initialData.trendy.getPeriodTrendy());
            req.setAttribute("conclusion", initialData.trendy.getConclusion());
            req.setAttribute("country", cost.getCountry());
            req.setAttribute("currency", cost.getCurrency());
            req.setAttribute("fuelType", cost.getFuelType());
            req.setAttribute("date1", cost.getDate1());
            req.setAttribute("date2", cost.getDate2());
            req.setAttribute("fuelUsage", cost.getFuelUsage());
            req.setAttribute("fullDistance", cost.getDistance());
            req.setAttribute("fullCost", cost.costCount());
            req.setAttribute("tripLength", initialData.trendy.getTripLength());
            req.setAttribute("trendPeriodFrom",
                    initialData.trendy.getTrendyPeriodFrom().toString().replaceAll("-", "/"));
            req.setAttribute("trendPeriodTill",
                    initialData.trendy.getTrendyPeriodTill().toString().replaceAll("-", "/"));
            req.setAttribute("startingDays", initialData.trendy.getStartingDaysString());
//            req.setAttribute("datesForTrends", json1);
//            req.setAttribute("valuesForTrends", json2);

            LOGGER.info("initialData trip atributes set:{} {} {} {} {} {}",
                    cost.getCountry(), cost.getFuelType(), cost.getDate1(),
                    cost.getDate2(), cost.getFuelUsage(), cost.getDistance());

            if(req.getParameter("trendy") != null){
                req.setAttribute("title", "Optimal time for trip");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/trendy.jsp");
                dispatcher.forward(req, resp);
            }
            else if (req.getParameter("tripCost") != null){
                req.setAttribute("title", "Estimated cost of the trip");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/tripCost.jsp");
                dispatcher.forward(req, resp);
            }
        }
//        req.setAttribute("countryList", initialData.promotedCountries.getOrderedPromotedCountries());
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/initialData.jsp");
//        dispatcher.forward(req, resp);
    }








}
