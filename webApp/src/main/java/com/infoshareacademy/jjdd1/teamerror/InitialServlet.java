package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingClass;
//import com.google.gson.Gson;
import com.infoshareacademy.jjdd1.teamerror.file_loader.*;
import com.infoshareacademy.jjdd1.teamerror.file_loader.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.net.URL;
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
    int switcher = 0;

    @Inject
    SavingClass savingClass;

    InitialData initialData = new InitialData();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.debug("servlet request");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        LOGGER.debug("Reading data from database");
        List<String> ret = savingClass.getPromotedCountries();
        LOGGER.debug("List of promoted countries from database: {}", ret);
        initialData.promotedCountries.setPromotedCountries(ret);
        LOGGER.info("Data from database successfully loaded");

        // session thingy
        HttpSession session = req.getSession(true);
        TripFullCost cost = (TripFullCost) session.getAttribute(TRIP_FULL_COST_SESSION_ATTR);
        if (cost == null) {
            cost = new TripFullCost();
            cost.setTripFullCost(initialData.filesContent, initialData.petrolFileFilter, initialData.currencyFileFilter);
            cost.setCountryAndCurrency(new CountryAndCurrency());

            session.setAttribute(TRIP_FULL_COST_SESSION_ATTR, cost);
        }

        // countries/currencies check
        initialData.filesContent.getPetrolDataFile();
        initialData.filesContent.getCurrencyInfoFile();
        initialData.countryAndCurrency.setFilesContent(initialData.filesContent);
        initialData.countryAndCurrencyList = initialData.countryAndCurrency.getCountryAndCurrency();

        LOGGER.debug("Checking existence of resource files");
        URL petrolFileURL = (FileReader.class.getResource(FileReader.PATH_TO_FILES +
                FileReader.PETROL_FILE_NAME));

        URL currencyInfoFileURL = (FileReader.class.getResource(FileReader.PATH_TO_FILES +
                FileReader.CURRENCY_FILE_WITH_GENERAL_DATA));

        URL currencyZipFileURL = (FileReader.class.getResource(FileReader.PATH_TO_FILES +
                FileReader.ZIP_CURRENCY_FILE));

        if(petrolFileURL == null || currencyInfoFileURL == null || currencyZipFileURL == null) {
            req.setAttribute("missingFile",  "yes");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/missingFiles.jsp");
            dispatcher.forward(req, resp);
            LOGGER.error("At least one source file is missing");
            return;
        }

        // starting servlet work
        if (req.getParameter("start") != null || req.getParameter("initialData") != null) {
            switcher = 0;
            req.setAttribute("countryList", initialData.promotedCountries.getOrderedPromotedCountries());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/initialData.jsp");
            dispatcher.forward(req, resp);
        }

        // proceed trendy.jsp or tripCost.jsp
        else if (req.getParameter("trendy") != null || req.getParameter("tripCost") != null) {

            if(switcher == 0){
                switcher++;

                cost.setCountry(req.getParameter("country").toUpperCase());
                cost.setFuelType(req.getParameter("fuelType"));
                cost.setDate1(req.getParameter("date1").replaceAll("/",""));
                cost.setDate2(req.getParameter("date2").replaceAll("/",""));
                cost.setFuelUsage(req.getParameter("fuelUsage"));
                cost.setDistance(req.getParameter("fullDistance"));

                try{
                    cost.costCount();
                }catch(Exception e){
                    LOGGER.error("Something went wrong. Please check your input (above)", e);
                }

                LOGGER.info("servlet req params: date1-{} date2-{} fuel usage-{} " +
                        "full distance-{}", cost.getDate1(), cost.getDate2(), cost.getFuelUsage(), cost.getDistance());
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
    }

//    @Override
//    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        LOGGER.debug("Getting file as request parameter");
//        Part filePart = req.getPart("file");
////        LOGGER.debug("Getting name of the file");
////        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//        LOGGER.debug("Converting file part into stream");
//        InputStream contentOfFile = filePart.getInputStream();
//        filesContent.setPetrolDataFile(contentOfFile);
////        String wholeFile = new Scanner(contentOfFile, "UTF-8").toString();
//        LOGGER.debug("Creating Bufferedreader from of InputStream");
//        BufferedReader br = new BufferedReader(new InputStreamReader(contentOfFile));
//        LOGGER.debug("Parsing Bufferedreader into lines");
//        List<String> contentInLines = br.lines().collect(Collectors.toList());
//        contentInLines.forEach(System.out::println);
//
//        req.setAttribute("countryList", promotedCountries.getOrderedPromotedCountries());
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/initialData.jsp");
//        dispatcher.forward(req, resp);
//    }
}
