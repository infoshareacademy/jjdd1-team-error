package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.dataBase.*;
import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingCountryStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/report")
public class ReportsServlet extends HttpServlet{
    private static Logger LOGGER = LoggerFactory.getLogger(ReportsServlet.class);

    @Inject
    SavingFuelTypeStatistics savingFuelTypeStatistics;

    @Inject
    SavingCountryStatistics savingCountryStatistics;

    @Inject
    SavingCurrencyStatistics savingCurrencyStatistics;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("servlet request");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        if (req.getParameter("countryAndCurrencyReport") != null) {
            req.setAttribute("title", "Country and currency report");
            req.setAttribute("countriesList", Statistics.getCountryStatistics().keySet());
            req.setAttribute("countriesPopularityList", Statistics.getCountryStatistics().values());

            req.setAttribute("currenciesList", Statistics.getCurrencyStatistics().keySet());
            req.setAttribute("currenciesPopularityList", Statistics.getCurrencyStatistics().values());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/countryAndCurrencyReport.jsp");
            dispatcher.forward(req, resp);
        }

        else if (req.getParameter("fuelTypeReport") != null) {
            req.setAttribute("title", "Fuel type report");
            req.setAttribute("fuelTypesList", Statistics.getPetrolStatistics());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/fuelTypeReport.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
