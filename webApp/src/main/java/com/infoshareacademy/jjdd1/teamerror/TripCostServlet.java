package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.dataBase.PromotedCountriesSaver;
import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingCountryStatistics;
import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingCurrencyStatistics;
import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingFuelTypeStatistics;
import com.infoshareacademy.jjdd1.teamerror.fileUpload.SourceFilesChecker;
import com.infoshareacademy.jjdd1.teamerror.file_loader.FilesContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by krystianskrzyszewski on 09.05.17.
 */
@WebServlet(urlPatterns = "/tripCost")
public class TripCostServlet extends HttpServlet {


    private final Logger LOGGER = LoggerFactory.getLogger(com.infoshareacademy.jjdd1.teamerror.TripCostServlet.class);
    private HttpSession session;


    @Inject
    PromotedCountriesSaver promotedCountriesSaver;

    @Inject
    TripFullCost cost;

    @Inject
    SavingFuelTypeStatistics savingFuelTypeStatistics;

    @Inject
    SavingCountryStatistics savingCountryStatistics;

    @Inject
    SavingCurrencyStatistics savingCurrencyStatistics;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.debug("Servlet start");

        AfterInitialDataServlet.setReqParametersToSession(req, resp);

        if (SourceFilesChecker.checkForSourceFiles(req, resp)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/missingFiles.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        session = req.getSession();
        FilesContent filesContent = (FilesContent)session.getAttribute("filesContent");
        cost.setTripFullCost(filesContent);


        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        String country = (String) session.getAttribute("country");
        String currency;
        if (country != null) {
            cost.setCountry(country);
            currency = cost.getCurrency();
            session.setAttribute("currency", currency);
        }

        String fuelType = (String) session.getAttribute("fuelType");
        if (fuelType != null) {
            cost.setFuelType(fuelType);
        }
        fuelType = cost.getFuelType();
        session.setAttribute("fuelTypeString", fuelType);

        String fuelUsage = (String) session.getAttribute("fuelUsage");
        if (fuelUsage != null) {
            cost.setFuelUsage(fuelUsage);
        }

        String fullDistance = (String) session.getAttribute("fullDistance");
        if (fullDistance != null) {
            cost.setDistance(fullDistance);
        }

        String date1 = (String) session.getAttribute("date1");
        if (date1 != null) {
            cost.setDate1(date1.replaceAll("/", ""));
        }

        String date2 = (String) session.getAttribute("date2");
        if (date2 != null) {
            cost.setDate2(date2.replaceAll("/", ""));
        }

        LOGGER.info("Session params: country-{} fuelType-{}, fuelUsage-{}, fullDistance-{}, date1-{}, date2-{}",
                country, fuelType, fuelUsage, fullDistance, date1, date2);
        LOGGER.debug("Cost class: {}", cost.toString());

        session.setAttribute("fullCost", cost.costCount());

        LOGGER.info("Popularity of diesel is {}", savingFuelTypeStatistics.getPopularity("diesel"));
        LOGGER.info("Popularity of gasoline is {}", savingFuelTypeStatistics.getPopularity("gasoline"));
        savingFuelTypeStatistics.updatePopularity(cost.getFuelType());
        LOGGER.info("Popularity of {} is updated to {}", cost.getFuelType(), savingFuelTypeStatistics.getPopularity(cost.getFuelType()));

        savingCountryStatistics.updateCountryStatistics(cost.getCountry());
        LOGGER.info("Popularity of {} is updated to {}", cost.getCountry(), savingCountryStatistics.getPopularity(cost.getCountry()));
        LOGGER.info("Top 10 countries from database: {}", savingCountryStatistics.getListOfCountries());
        LOGGER.info("Top 10 popularity of countries from database: {}", savingCountryStatistics.getListOfPopularity());

        savingCurrencyStatistics.updateCurrencyStatistics(cost.getCurrency());
        LOGGER.info("Popularity of {} is updated to {}", cost.getCurrency(), savingCurrencyStatistics.getPopularity(cost.getCurrency()));
        LOGGER.info("Top 10 currencies from database: {}", savingCurrencyStatistics.getListOfCurrencies());
        LOGGER.info("Top 10 popularity of currencies from database: {}", savingCurrencyStatistics.getListOfPopularity());

        session.setAttribute("filesContent", filesContent);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/tripCost.jsp");
        dispatcher.forward(req, resp);
    }
}

