package com.infoshareacademy.jjdd1.teamerror;

import com.google.gson.Gson;
import com.infoshareacademy.jjdd1.teamerror.dataBase.PromotedCountriesSaver;
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
@WebServlet(urlPatterns = "/trendy")
public class TrendsServlet extends HttpServlet{

    private final Logger LOGGER = LoggerFactory.getLogger(com.infoshareacademy.jjdd1.teamerror.TrendsServlet.class);
    HttpSession session;


    @Inject
    PromotedCountriesSaver promotedCountriesSaver;

    @Inject
    Trendy trendy;

    @Inject
    AfterInitialData afterInitialData;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.debug("Servlet start");

        if (SourceFilesChecker.checkForSourceFiles(req, resp)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/missingFiles.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        session = req.getSession();
        FilesContent filesContent = (FilesContent)session.getAttribute("filesContent");

        afterInitialData.setReqParametersToSession(req, resp, filesContent);

        trendy.setupClass(filesContent);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        String country = (String) session.getAttribute("country");
        String currency;
        if (country != null) {
            trendy.setCountry(country);
            currency = trendy.getCurrencySymbol();
            session.setAttribute("currency", currency);
        }

        String fuelType = (String) session.getAttribute("fuelType");
        if (fuelType != null) {
            trendy.setFuelType(fuelType);
        }
        fuelType = trendy.getFuelType();
        session.setAttribute("fuelTypeString", fuelType);

        String tripLength = (String) session.getAttribute("tripLength");
        if (tripLength != null) {
            trendy.setTripLength(tripLength);
        }
        tripLength = trendy.getTripLength().toString();
        session.setAttribute("tripLength", tripLength);

        String trendPeriodFrom = (String) session.getAttribute("trendPeriodFrom");
        if (trendPeriodFrom != null) {
            trendy.setTrendyPeriodFrom(trendPeriodFrom.replaceAll("/", ""));
        }
        trendPeriodFrom = trendy.getTrendyPeriodFrom().toString().replaceAll("-", "/");
        session.setAttribute("trendPeriodFrom", trendPeriodFrom);

        String trendPeriodTill = (String) session.getAttribute("trendPeriodTill");
        if (trendPeriodTill != null) {
            trendy.setTrendyPeriodTill(trendPeriodTill.replaceAll("/", ""));
        }
        trendPeriodTill = trendy.getTrendyPeriodTill().toString().replaceAll("-", "/");
        session.setAttribute("trendPeriodTill", trendPeriodTill);

        String[] startingDays = (String[]) session.getAttribute("startingDays");
        if (startingDays != null) {
            trendy.setStartingDays(new HashSet<>(Arrays.asList(startingDays)));
        }

        Set<String> startingDaysString = trendy.getStartingDaysString();
        session.setAttribute("startingDaysString", startingDaysString);

        LOGGER.info("Session params: country-{} fuelType-{}, trendPeriodFrom-{}, trendPeriodTill-{}, startingDays-{}",
                country, fuelType, trendPeriodFrom, trendPeriodTill, startingDays);

        Gson gson = new Gson();

        Map<LocalDate, List<Double>> periodTrendy = trendy.getPeriodTrendy();
        String json1 = gson.toJson(periodTrendy.keySet());
        session.setAttribute("json1", json1);
        String json2 = gson.toJson(periodTrendy.values());
        session.setAttribute("json2", json2);
        session.setAttribute("periodTrendy", periodTrendy);
        String conclusion = trendy.getConclusion();
        session.setAttribute("conclusion", conclusion);

        session.setAttribute("filesContent", filesContent);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/trendy.jsp");
        dispatcher.forward(req, resp);
    }

}


