package com.infoshareacademy.jjdd1.teamerror.dataStatistics;

import com.infoshareacademy.jjdd1.teamerror.dataBase.*;
import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingCountryStatistics;
import com.infoshareacademy.jjdd1.teamerror.file_loader.CachedFilesContent;
import com.infoshareacademy.jjdd1.teamerror.file_loader.FilesContent;
import com.infoshareacademy.jjdd1.teamerror.file_loader.PromotedCountries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/report")
public class ReportsServlet extends HttpServlet{
    private static Logger LOGGER = LoggerFactory.getLogger(ReportsServlet.class);

    @Inject
    SavingFuelTypeStatistics savingFuelTypeStatistics;

    @Inject
    SavingCountryStatistics savingCountryStatistics;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("servlet request");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");


        if (req.getParameter("countryAndCurrencyReport") != null) {
            req.setAttribute("title", "Country and currency report");
            req.setAttribute("countriesList", savingCountryStatistics.getListOfCountries());
            req.setAttribute("popularityList", savingCountryStatistics.getListOfPopularity());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/countryAndCurrencyReport.jsp");
            dispatcher.forward(req, resp);
        }

        if (req.getParameter("fuelTypeReport") != null) {
            req.setAttribute("title", "Fuel type report");

            req.setAttribute("dieselPopularity", savingFuelTypeStatistics.getPopularity("diesel"));
            req.setAttribute("gasolinePopularity", savingFuelTypeStatistics.getPopularity("gasoline"));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/fuelTypeReport.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
