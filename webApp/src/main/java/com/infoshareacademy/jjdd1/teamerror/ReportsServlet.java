package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.dataBase.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/report")
public class ReportsServlet extends HttpServlet{
    private static Logger LOGGER = LoggerFactory.getLogger(ReportsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("servlet request");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        if (req.getParameter("countryAndCurrencyReport") != null) {
            req.setAttribute("title", "Country and currency report");
            Map<String, Integer> countryStatistics = Statistics.getStatistics("country");
            LOGGER.debug("Country statistics: {}", countryStatistics);
            req.setAttribute("countryStatistics", countryStatistics);
//            req.setAttribute("countriesPopularityList", countriesList.values());

            Map<String, Integer> currencyStatistics = Statistics.getStatistics("currency");
            LOGGER.debug("Currency statistics: {}", currencyStatistics);
            req.setAttribute("currencyStatistics", currencyStatistics);
//            req.setAttribute("currenciesPopularityList", currenciesList.values());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/countryAndCurrencyReport.jsp");
            dispatcher.forward(req, resp);
        }

        else if (req.getParameter("fuelTypeReport") != null) {
            req.setAttribute("title", "Fuel type report");
            Map<String, Integer> fuelTypeStatistics = Statistics.getStatistics("petrol");
            req.setAttribute("fuelTypeStatistics", fuelTypeStatistics);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/fuelTypeReport.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
