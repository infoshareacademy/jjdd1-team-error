package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.fileUpload.SourceFilesChecker;
import com.infoshareacademy.jjdd1.teamerror.file_loader.CountryAndCurrency;
import com.infoshareacademy.jjdd1.teamerror.file_loader.FilesContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by sebastian_los on 11.05.17.
 */

public class AfterInitialData extends HttpServlet {


    @Inject
    Statistics statistics;


    private final Logger LOGGER = LoggerFactory.getLogger(AfterInitialData.class);


    void setReqParametersToSession(HttpServletRequest req, HttpServletResponse resp,
                                          FilesContent filesContent) throws ServletException, IOException {

        LOGGER.debug("Setting req paremeters to session");

        if (SourceFilesChecker.checkForSourceFiles(req, resp)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/missingFiles.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        HttpSession session = req.getSession();

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        String country = req.getParameter("country");
        if (country != null) {
            session.setAttribute("country", country);
        }

        String fuelType = req.getParameter("fuelType");
        if (fuelType != null) {
            session.setAttribute("fuelType", fuelType);
        }

        String fuelUsage = req.getParameter("fuelUsage");
        if (fuelUsage != null) {
            session.setAttribute("fuelUsage", fuelUsage);
        }

        String fullDistance = req.getParameter("fullDistance");
        if (fullDistance != null) {
            session.setAttribute("fullDistance", fullDistance);
        }

        String date1 = req.getParameter("date1");
        if (date1 != null) {
            session.setAttribute("date1", date1);
        }

        String date2 = req.getParameter("date2");
        if (date2 != null) {
            session.setAttribute("date2", date2);
        }

        String tripLength = req.getParameter("tripLength");
        if (tripLength != null) {
            session.setAttribute("tripLength", tripLength);
        }

        String trendPeriodFrom = req.getParameter("trendPeriodFrom");
        if (trendPeriodFrom != null) {
            session.setAttribute("trendPeriodFrom", trendPeriodFrom);
        }

        String trendPeriodTill = req.getParameter("trendPeriodTill");
        if (trendPeriodTill != null) {
            session.setAttribute("trendPeriodTill", trendPeriodTill);
        }

        String[] startingDays = req.getParameterValues("startingDays");
        if (startingDays != null) {
            session.setAttribute("startingDays", startingDays);
        }

        LOGGER.info("Inisial data req params: country-{} fuelType-{} fuelUsage-{} full distance-{} " +
                        "date1-{} date2-{}, tripLength-{} trendPeriodFrom-{}, trendPeriodTill-{}, startingDays-{}",
                country, fuelType, fuelUsage, fullDistance, date1, date2, tripLength,
                trendPeriodFrom, trendPeriodTill, startingDays);

        if (country != null && fuelType != null) {
            CountryAndCurrency countryAndCurrency = new CountryAndCurrency(filesContent);
            countryAndCurrency.setCurrency(country);
            String currency = countryAndCurrency.getCurrency();
            statistics.updateStatisticsOfCountryAndCurrencyAndFuelType(country, currency, fuelType);
            LOGGER.debug("Statistics updated, parameters: {} {} {}", country, currency, fuelType);
        }
    }
}