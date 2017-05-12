package com.infoshareacademy.jjdd1.teamerror;

import com.google.gson.Gson;
import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingClass;
import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingFuelTypeStatistics;
import com.infoshareacademy.jjdd1.teamerror.fileUpload.SourceFilesChecker;
import com.infoshareacademy.jjdd1.teamerror.file_loader.FilesContent;
import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;
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
import java.time.LocalDate;
import java.util.*;

/**
 * Created by krystianskrzyszewski on 09.05.17.
 */
@WebServlet(urlPatterns = "/tripCost")
public class TripCostServlet extends HttpServlet {


    private final Logger LOGGER = LoggerFactory.getLogger(com.infoshareacademy.jjdd1.teamerror.TrendsServlet.class);
    private HttpSession session;


    @Inject
    SavingClass savingClass;

    @Inject
    TripFullCost cost;

    @Inject
    SavingFuelTypeStatistics savingFuelTypeStatistics;

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


        session.setAttribute("filesContent", filesContent);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/tripCost.jsp");
        dispatcher.forward(req, resp);
    }
}

