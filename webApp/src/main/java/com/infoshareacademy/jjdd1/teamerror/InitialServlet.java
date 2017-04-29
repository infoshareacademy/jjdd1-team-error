package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.file_loader.*;
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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Created by krystianskrzyszewski on 19.04.17.
 */

@WebServlet(urlPatterns = "/calc")
public class InitialServlet extends HttpServlet {

    private final String TRIP_FULL_COST_SESSION_ATTR = "fripFullCost";
    private final Logger LOGGER = LoggerFactory.getLogger(InitialServlet.class);
//
//    CurrencyFileFilter currencyFileFilter;
//    PetrolFileFilter petrolFileFilter;
//    Trendy trendy;
//    FilesContent filesContent;
//    CountryAndCurrency countryAndCurrency;
//    Map<String, String> countryAndCurrencyList;
//    PromotedCountries promotedCountries;
//
    @Inject
    SavingClass savingClass;
//
//    public InitialServlet() {
//        super();
//        LOGGER.info("InitialServlet initialisation");
//
//        trendy = new Trendy();
//
//        filesContent = new CachedFilesContent();
//        currencyFileFilter = new CurrencyFileFilter();
//        petrolFileFilter = new PetrolFileFilter();
//        currencyFileFilter.setFilesContent(filesContent);
//        petrolFileFilter.setFilesContent(filesContent);
//
//        trendy.setCurrencyFileFilter(currencyFileFilter);
//        trendy.setPetrolFileFilter(petrolFileFilter);
//        countryAndCurrency = new CountryAndCurrency();
//        LOGGER.info("InitialServlet initialised");
//        promotedCountries = new PromotedCountries();
//        promotedCountries.setFilesContent(filesContent);
//
//
//    }

    InitialData initialData = new InitialData();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.debug("Reading data from database");
        List<String> ret = savingClass.getPromotedCountries();
        LOGGER.debug("List of promoted countries from database: {}", ret);
        initialData.promotedCountries.setPromotedCountries(ret);
        LOGGER.info("Data from database successfully loaded");

        HttpSession session = req.getSession(true);
        TripFullCost cost = (TripFullCost) session.getAttribute(TRIP_FULL_COST_SESSION_ATTR);
        if (cost == null) {
            cost = new TripFullCost();
            cost.setTripFullCost(initialData.filesContent, initialData.petrolFileFilter, initialData.currencyFileFilter);
            cost.setCountryAndCurrency(new CountryAndCurrency());

            session.setAttribute(TRIP_FULL_COST_SESSION_ATTR, cost);
        }

        LOGGER.debug("servlet request");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");
        initialData.filesContent.getPetrolDataFile();
        initialData.filesContent.getCurrencyInfoFile();

        initialData.countryAndCurrency.setFilesContent(initialData.filesContent);
        initialData.countryAndCurrencyList = initialData.countryAndCurrency.getCountriesAndCurrency();

        LOGGER.debug("Checking existence of resource files");
        File petrolFile = new File(System.getProperty("java.io.tmpdir")+"/files/" + "iSA-PetrolPrices.csv");
        File currencyInfoFile = new File(System.getProperty("java.io.tmpdir")+"/files/" + "omeganbp.lst.txt");
        File currencyZipFile = new File(System.getProperty("java.io.tmpdir")+"/files/" + "omeganbp.zip");
        if(!petrolFile.exists() || !currencyInfoFile.exists() || !currencyZipFile.exists()) {
            req.setAttribute("missingFile",  "yes");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/missingFiles.jsp");
            dispatcher.forward(req, resp);
            LOGGER.error("At least one source file is missing");
        }

//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        LOGGER.debug("Reading data from database");
//        List<String> ret = savingClass.getPromotedCountries();
//        LOGGER.debug("List of promoted countries from database: {}", ret);
//        promotedCountries.setPromotedCountries(ret);
//        LOGGER.info("Data from database successfully loaded");
//
//        HttpSession session = req.getSession(true);
//        TripFullCost cost = (TripFullCost) session.getAttribute(TRIP_FULL_COST_SESSION_ATTR);
//        if (cost == null) {
//            cost = new TripFullCost();
//            cost.setTripFullCost(filesContent, petrolFileFilter, currencyFileFilter);
//            cost.setCountryAndCurrency(new CountryAndCurrency());
//
//            session.setAttribute(TRIP_FULL_COST_SESSION_ATTR, cost);
//        }
//
//        LOGGER.debug("servlet request");
//        resp.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/plain;charset=UTF-8");
//        filesContent.getPetrolDataFile();
//        filesContent.getCurrencyInfoFile();
//
//        countryAndCurrency.setFilesContent(filesContent);
//        countryAndCurrencyList = countryAndCurrency.getCountriesAndCurrency();
//
//        LOGGER.debug("Checking existence of resource files");
//        File petrolFile = new File(System.getProperty("java.io.tmpdir")+"/files/" + "iSA-PetrolPrices.csv");
//        File currencyInfoFile = new File(System.getProperty("java.io.tmpdir")+"/files/" + "omeganbp.lst.txt");
//        File currencyZipFile = new File(System.getProperty("java.io.tmpdir")+"/files/" + "omeganbp.zip");
//        if(!petrolFile.exists() || !currencyInfoFile.exists() || !currencyZipFile.exists()) {
//            req.setAttribute("missingFile",  "yes");
//            RequestDispatcher dispatcher = req.getRequestDispatcher("/missingFiles.jsp");
//            dispatcher.forward(req, resp);
//            LOGGER.error("At least one source file is missing");
//        }

        String action = req.getParameter("action");
        req.setAttribute("countryList", initialData.promotedCountries.getOrderedPromotedCountries());

        if ("Start".equals(action)) {
            req.setAttribute("countryList", initialData.promotedCountries.getOrderedPromotedCountries());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/initialData.jsp");
            dispatcher.forward(req, resp);
        }
        else if ("Initialization".equals(action)) {
            String country = req.getParameter("country").toUpperCase();
            String fuelType = req.getParameter("fuelType");
            String date1 = req.getParameter("date1");
            String date2 = req.getParameter("date2");
            String fuelUsage = req.getParameter("fuelUsage");
            String fullDistance = req.getParameter("fullDistance");

            cost.setCountry(country);
            cost.setFuelType(fuelType);
            cost.setDate1(date1);
            cost.setDate2(date2);
            cost.setFuelUsage(fuelUsage);
            cost.setDistance(fullDistance);

            LOGGER.info("servlet req params: country-{} fuel type-{}", country, fuelType);
            req.setAttribute("title", "Menu");
            req.setAttribute("country", cost.getCountry());
            req.setAttribute("currency", cost.getCurrency());
            req.setAttribute("fuelType", cost.getFuelType());
            req.setAttribute("date1", date1);
            req.setAttribute("date2", date2);
            req.setAttribute("fuelUsage", fuelUsage);
            req.setAttribute("fullDistance", fullDistance);
            req.setAttribute("fullCost", cost.costCount());

            RequestDispatcher dispatcher = req.getRequestDispatcher("/trendy.jsp");
            dispatcher.forward(req, resp);
        }

        // proceed trendy.jsp
        else if (req.getParameter("trendy") != null) {

            req.setAttribute("title", "Optimal time for trip");
            req.setAttribute("country", cost.getCountry());
            req.setAttribute("currency", cost.getCurrency());
            req.setAttribute("fuelType", cost.getFuelType());


            initialData.trendy.setTrendy(cost.getCurrency(), cost.getFuelType(), cost.getCountry());
            LOGGER.info("calculated trend for trip: country-{} currency-{} fuel type-{}",
                    cost.getCountry(), cost.getCurrency(), cost.getFuelType());
            req.setAttribute("petrolTrendy", initialData.trendy.getPetrolTrendy());
            req.setAttribute("currencyTrendy", initialData.trendy.getCurrencyTrendy());
            req.setAttribute("conclusion", initialData.trendy.getConclusion());

            RequestDispatcher dispatcher = req.getRequestDispatcher("/trendy.jsp");
            dispatcher.forward(req, resp);
        }

        // proceed tripOtherData.jsp
        else if (req.getParameter("additionalData") != null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/tripOtherData.jsp");
            dispatcher.forward(req, resp);
        }

        // proceed tripCost.jsp
        else if (req.getParameter("tripCost") != null) {
            cost = TripFullCost.reset(cost);

            try {
                String date1 = req.getParameter("date1");
                String date2 = req.getParameter("date2");
                String fuelUsage = req.getParameter("fuelUsage");
                String fullDistance = req.getParameter("fullDistance");
                LOGGER.info("servlet req params: date1-{} date2-{} fuel usage-{} " +
                        "full distance-{}", date1, date2, fuelUsage, fullDistance);

                String fuelUsageString = "";
                try{
                    if(fuelUsage.toString().equals("")){
                        LOGGER.debug("FIRST:   fuelUsage = [{}], fuellUsageString = [{}]", fuelUsage, fuelUsageString);
                        fuelUsageString = "No input recorded";
                    }
                    else if(Double.parseDouble(fuelUsage)<=0.0){
                        LOGGER.debug("SECOND:   fuelUsage = [{}], fuellUsageString = [{}]", fuelUsage, fuelUsageString);
                        fuelUsageString = "Value can't be equall or less than 0";
                    }
                    else {
                        LOGGER.debug("THIRD:   fuelUsage = [{}], fuellUsageString = [{}]", fuelUsage, fuelUsageString);
                        Double.parseDouble(fuelUsage.toString());
                        cost.setFuelUsage(fuelUsage);
                        fuelUsageString = cost.getFuelUsage().toString();
                    }
                }catch(Exception e){
                    LOGGER.debug("FOURTH:  FuelUsage = [{}], fuellUsageString = [{}]", fuelUsage, fuelUsageString);
                    fuelUsageString = fuelUsage + " is a wrong fuel usage input. ";
                }


                String fullDistanceString="";
                try{
                    if(fullDistance.toString().equals("")){
                        LOGGER.debug("FIRST:   fullDistance = [{}], fullDistanceString = [{}]", fullDistance, fullDistanceString);
                        fullDistanceString = "No input recorded";
                    }
                    else if(Double.parseDouble(fullDistance)<=0.0) {
                        LOGGER.debug("SECOND:   fullDistance = [{}], fullDistanceString = [{}]", fullDistance, fullDistanceString);
                        fullDistanceString = "Value can't be equall or less than 0";
                    }
                    else{
                        LOGGER.debug("THIRD:   fullDistance = [{}], fullDistanceString = [{}]", fullDistance, fullDistanceString);
                        cost.setDistance(fullDistance);
                        fullDistanceString = cost.getDistance().toString();
                    }
                }catch(Exception e){
                    LOGGER.debug("FOURTH:   fullDistance = [{}], fullDistanceString = [{}]", fullDistance, fullDistanceString);
                    fullDistanceString = fullDistance + " is a wrong distance input. ";
                }

                String date1String="";
                try{
                    if(date1.toString().equals("")){
                        LOGGER.info("FIRST:   date1 = [{}], date1String = [{}]", date1, date1String);
                        date1String = "No input recorded";
                    }
                    else if(cost.getDate1()!=null && DateParser.DateFromString(date1).toString()!=date1){
                        LOGGER.info("SECOND:   date1 = [{}], date1String = [{}]", date1, date1String);
                        cost.setDate1(date1);
                        date1String = cost.getDate1().toString();
                    }
                    else{
                        LOGGER.info("THIRD:   date1 = [{}], date1String = [{}]", date1, date1String);
                        cost.setDate1(date1);
                        date1String = cost.getDate1().toString();
                    }
                }catch(Exception e){
                    LOGGER.info("FOURTH:   date1 = [{}], date1String = [{}]", date1, date1String);
                    date1String = date1 + " is a wrong date.";
                }

                String date2String="";
                try {
                    if(date2.length()==8){
                        Integer.parseInt(date2);
                        LocalDate date = LocalDate.parse(date2, DateTimeFormatter.ofPattern("yyyyMMdd"));
                        LOGGER.info("date = {}, date2 = ", date, date2);
                        if(date.toString().substring(8).equals(date2.substring(6))){
                            if (cost.getDate1()!=null){
                                if (date.isAfter(cost.getDate1())) {
                                    cost.setDate2(date2);
                                    date2String = cost.getDate2().toString();
                                }
                                else{
                                    LOGGER.error("Return date [{}] is before start date [{}]", date2, date1);
                                    date2String = "Return date is before start date";
                                }
                            }
                            else {
                                LOGGER.error("No start date specified");
                                date2String = "No start date specified";
                            }
                        }
                        else{
                            LOGGER.error("No such day exists");
                            date2String = "No such day exists";
                        }
                    }
                    else if(date2.equals("")){
                        LOGGER.error("No input recorded");
                        date2String = "No input recorded";
                    }
                    else{
                        LOGGER.error("Wrong date format");
                        date2String = "Wrong date format";
                    }
                }
                catch (NumberFormatException e){
                    LOGGER.error("Input contains letters");
                    date2String = "Input contains letters";
                }
                catch( Exception e ) {
                    LOGGER.error("No such year/month/day exists");
                    date2String = "No such year/month/day exists";
                }


                LOGGER.info(cost.toString());

                String fullCostString;
                try{
                    cost.costCount();
                    fullCostString = String.valueOf(cost.costCount()) + " PLN";
                }catch(Exception e){
                    LOGGER.error("Something went wrong. Please check your input (above)", e);
                    fullCostString = "Something went wrong. Please check your input (above)";
                }

                LOGGER.info(cost.toString());

                req.setAttribute("title", "Menu");
                req.setAttribute("currency", cost.getCurrency());
                req.setAttribute("country", cost.getCountry());
                req.setAttribute("fuelType", cost.getFuelType());
                req.setAttribute("date1", date1String);
                req.setAttribute("date2", date2String);
                req.setAttribute("fuelUsage", fuelUsageString);
                req.setAttribute("fullDistance", fullDistanceString);
                req.setAttribute("fullCost", fullCostString);

                RequestDispatcher dispatcher = req.getRequestDispatcher("/tripCost.jsp");
                dispatcher.forward(req, resp);

            }catch(Exception e){
                e.printStackTrace();
                RequestDispatcher dispatcher = req.getRequestDispatcher("/exceptionHandling.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}
