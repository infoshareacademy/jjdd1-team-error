package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingClass;
import com.google.gson.Gson;
import com.infoshareacademy.jjdd1.teamerror.fileUpload.SourceFilesChecker;
import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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

    @Inject
    Trendy trendy;

    @Inject
    TripFullCost cost;

    @Inject
    InitialData initialData;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.debug("servlet request");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");
        trendy.setupClass(initialData.filesContent);
        cost.setTripFullCost(initialData.filesContent);

        if (SourceFilesChecker.checkForSourceFiles(req, resp)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/missingFiles.jsp");
            dispatcher.forward(req, resp);
            return;
        }

//        // session thingy
//        HttpSession session = req.getSession(true);
//        TripFullCost cost = (TripFullCost) session.getAttribute(TRIP_FULL_COST_SESSION_ATTR);
//        if (cost == null) {
//            cost.setTripFullCost(initialData.filesContent);
//            session.setAttribute(TRIP_FULL_COST_SESSION_ATTR, cost);
//        }


        // proceed trendy.jsp or tripCost.jsp
        if (req.getParameter("trendy") != null || req.getParameter("tripCost") != null) {

            if(switcher == 0){
                switcher++;
                String country = req.getParameter("country").toUpperCase();
                String fuelType = req.getParameter("fuelType");
                cost.setCountry(country);
                cost.setFuelType(fuelType);
                cost.setDate1(req.getParameter("date1").replaceAll("/",""));
                cost.setDate2(req.getParameter("date2").replaceAll("/",""));
                cost.setFuelUsage(req.getParameter("fuelUsage"));
                cost.setDistance(req.getParameter("fullDistance"));
                trendy.setCountry(country);
                trendy.setFuelType(fuelType);
                LOGGER.debug("country-{} fuel type-{}", req.getParameter("country"), req.getParameter("fuelType"));
                               LOGGER.info("servlet req params: date1-{} date2-{} fuel usage-{} " +
                        "full distance-{}", cost.getDate1(), cost.getDate2(), cost.getFuelUsage(), cost.getDistance());
                try{
                    cost.costCount();
                }catch(Exception e){
                    LOGGER.error("Something went wrong. Please check your input (above)", e);
                }

                LOGGER.info("Cost class: {}", cost.toString());

                LOGGER.info("calculated trend for trip: country-{} currency-{} fuel type-{}",
                        cost.getCountry(), cost.getCurrency(), cost.getFuelType());
            }

            Gson gson = new Gson();
            Map<LocalDate, List<Double>> periodTrendy = trendy.getPeriodTrendy();
            String json1 = gson.toJson(periodTrendy.keySet());
            LOGGER.info("Map key set: {} ",json1);
            String json2 = gson.toJson(periodTrendy.values());
            LOGGER.info("Values: {}", json2);


            req.setAttribute("periodTrendy", periodTrendy);
            req.setAttribute("conclusion", periodTrendy);
            req.setAttribute("country", cost.getCountry());
            req.setAttribute("currency", cost.getCurrency());
            req.setAttribute("fuelType", cost.getFuelType());
            req.setAttribute("date1", cost.getDate1());
            req.setAttribute("date2", cost.getDate2());
            req.setAttribute("fuelUsage", cost.getFuelUsage());
            req.setAttribute("fullDistance", cost.getDistance());
            req.setAttribute("fullCost", cost.costCount());
            req.setAttribute("tripLength", trendy.getTripLength());
            req.setAttribute("trendPeriodFrom",
                    trendy.getTrendyPeriodFrom().toString().replaceAll("-", "/"));
            req.setAttribute("trendPeriodTill",
                    trendy.getTrendyPeriodTill().toString().replaceAll("-", "/"));
            req.setAttribute("startingDaysString", trendy.getStartingDaysString());
            req.setAttribute("datesForTrends", json1);
            req.setAttribute("valuesForTrends", json2);

            LOGGER.info("initialData trip atributes set:{} {} {} {} {} {}",
                    cost.getCountry(), cost.getFuelType(), cost.getDate1(),
                    cost.getDate2(), cost.getFuelUsage(), cost.getDistance());

            if(req.getParameter("trendy") != null){
                String periodDateFrom = req.getParameter("periodDateFrom");
                String periodDateTill = req.getParameter("periodDateTill");
                String[] startingDays = req.getParameterValues("startingDays");
                String tripLength = req.getParameter("tripLength");
                LOGGER.debug("Trendy parameters - DateFrom: {} DateTill: {} TripLength: {} WeekDays: {}",
                        periodDateFrom, periodDateTill, tripLength, startingDays);
                if (periodDateFrom != null && periodDateTill != null & tripLength != null && startingDays != null) {
                    periodDateFrom = periodDateFrom.replaceAll("/", "");
                    periodDateTill = periodDateTill.replaceAll("/", "");
                    trendy.setTrendyPeriodFrom(periodDateFrom);
                    trendy.setTrendyPeriodTill(periodDateTill);
                    trendy.setTripLength(tripLength);
                    trendy.setStartingDays(new HashSet<>(Arrays.asList(startingDays)));

                    LOGGER.debug("Trendy parameters changed - DateFrom: {} DateTill: {} TripLength: {}",
                            periodDateFrom, periodDateTill, tripLength);
                }
                periodTrendy = trendy.getPeriodTrendy();
                json1 = gson.toJson(periodTrendy.keySet());
                LOGGER.info("Map key set: {} ",json1);
                json2 = gson.toJson(periodTrendy.values());
                LOGGER.info("Values: {}", json2);

                req.setAttribute("periodTrendy", periodTrendy);
                req.setAttribute("conclusion", trendy.getConclusion());
                req.setAttribute("trendPeriodFrom",
                        trendy.getTrendyPeriodFrom().toString().replaceAll("-", "/"));
                req.setAttribute("trendPeriodTill",
                        trendy.getTrendyPeriodTill().toString().replaceAll("-", "/"));
                req.setAttribute("tripLength", trendy.getTripLength());
                req.setAttribute("startingDaysString", trendy.getStartingDaysString());
                req.setAttribute("datesForTrends", json1);
                req.setAttribute("valuesForTrends", json2);

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
    }
}
