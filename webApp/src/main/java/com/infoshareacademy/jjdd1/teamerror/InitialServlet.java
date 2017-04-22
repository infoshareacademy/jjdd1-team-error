package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.file_loader.*;
import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by krystianskrzyszewski on 19.04.17.
 */

@WebServlet(urlPatterns = "/calc")
public class InitialServlet extends HttpServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(InitialServlet.class);

    Trendy trendy;
    FilesContent filesContent;
    CurrencyFileFilter currencyFileFilter;
    PetrolFileFilter petrolFileFilter;
    TripFullCost cost;
    CountryAndCurrency countryAndCurrency;

    public InitialServlet() {
        super();
        LOGGER.info("InitialServlet initialisation");

        trendy = new Trendy();

        filesContent = new OnDemandFilesContent();
        currencyFileFilter = new CurrencyFileFilter();
        petrolFileFilter = new PetrolFileFilter();
        currencyFileFilter.setFilesContent(filesContent);
        petrolFileFilter.setFilesContent(filesContent);
        cost = new TripFullCost();
        cost.setTripFullCost(filesContent, petrolFileFilter, currencyFileFilter);
        trendy.setCurrencyFileFilter(currencyFileFilter);
        trendy.setPetrolFileFilter(petrolFileFilter);
        countryAndCurrency = new CountryAndCurrency();
        countryAndCurrency.setFilesContent(filesContent);
        LOGGER.info("InitialServlet initialised");

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("servlet request");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        if (req.getParameter("initialData") != null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        }
        else if (req.getParameter("initialization") != null) {
            String country = req.getParameter("country").toUpperCase();
            String fuelType = req.getParameter("fuelType");

            cost.setCountry(country);

            countryAndCurrency.setCurrency(cost.getCountry());
            cost.setCurrency(countryAndCurrency.getCurrency());
            cost.setFuelType(fuelType);

            LOGGER.info("servlet req params: {} {}", country, cost.getFuelType());

            req.setAttribute("country", cost.getCountry());
            req.setAttribute("currency", cost.getCurrency());
            req.setAttribute("fuelType", cost.getFuelType());

            RequestDispatcher dispatcher = req.getRequestDispatcher("/menu.jsp");
            dispatcher.forward(req, resp);
        }
        else if (req.getParameter("trendy") != null) {

            req.setAttribute("country", cost.getCountry());
            req.setAttribute("currency", cost.getCurrency());
            req.setAttribute("fuelType", cost.getFuelType());

            String trendForTrip = trendy.optimalTimeForTrip(cost.getCurrency(), cost.getFuelType(), cost.getCountry());
            LOGGER.info("calculated trend for trip: {}", trendForTrip);
            req.setAttribute("trendForTrip", trendForTrip);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/trendy.jsp");
            dispatcher.forward(req, resp);
        }
        else if (req.getParameter("additionalData") != null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/tripOtherData.jsp");
            dispatcher.forward(req, resp);
        }
        else if (req.getParameter("tripCost") != null) {

            String date1 = req.getParameter("date1");
            String date2 = req.getParameter("date2");
            String fuelUsage = req.getParameter("fuelUsage");
            String fullDistance = req.getParameter("fullDistance");
            cost.setDate1(date1);
            cost.setDate2(date2);
            cost.setFuelUsage(fuelUsage);
            cost.setDistance(fullDistance);

            String fullCost = String.valueOf(cost.costCount());
            req.setAttribute("country", cost.getCountry());
            req.setAttribute("currency", cost.getCurrency());
            req.setAttribute("fuelType", cost.getFuelType());
            req.setAttribute("date1", cost.getDate1());
            req.setAttribute("date2", cost.getDate2());
            req.setAttribute("fuelUsage", cost.getFuelUsage());
            req.setAttribute("fullDistance", cost.getDistance());
            req.setAttribute("fullCost", fullCost);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/tripCost.jsp");
            dispatcher.forward(req, resp);
        }

    }
}
