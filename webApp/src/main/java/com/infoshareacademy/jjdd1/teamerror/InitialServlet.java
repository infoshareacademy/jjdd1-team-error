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
import java.util.*;

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

                initialData.trendy.setTrendy(cost.getCurrency(), cost.getFuelType(), cost.getCountry());

                LOGGER.info("calculated trend for trip: country-{} currency-{} fuel type-{}",
                        cost.getCountry(), cost.getCurrency(), cost.getFuelType());
            }

            Collection<String> monthsTable = initialData.trendy.getCurrencyTrendy().keySet();
            Collection<String> currencyTable = initialData.trendy.getCurrencyTrendy().values();
            Collection<String> fuelTable = initialData.trendy.getPetrolTrendy().values();

            LOGGER.info("MonthsTable: {} + CurrencyTable: {} + FuelTable: {}", monthsTable, currencyTable, fuelTable);

            req.setAttribute("petrolValues", fuelTable);
            req.setAttribute("currencyValues", currencyTable);
            req.setAttribute("months", monthsTable);

            req.setAttribute("petrolTrendy", initialData.trendy.getPetrolTrendy());
            req.setAttribute("currencyTrendy", initialData.trendy.getCurrencyTrendy());
            req.setAttribute("conclusion", initialData.trendy.getConclusion());
            req.setAttribute("country", cost.getCountry());
            req.setAttribute("currency", cost.getCurrency());
            req.setAttribute("fuelType", cost.getFuelType());
            req.setAttribute("date1", cost.getDate1());
            req.setAttribute("date2", cost.getDate2());
            req.setAttribute("fuelUsage", cost.getFuelUsage());
            req.setAttribute("fullDistance", cost.getDistance());
            req.setAttribute("fullCost", cost.costCount() + " PLN");

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
}
