package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingClass;
import com.infoshareacademy.jjdd1.teamerror.fileUpload.SourceFilesChecker;
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

/**
 * Created by sebastian_los on 11.05.17.
 */

public class AfterInitialDataServlet  extends HttpServlet {


    private static final Logger LOGGER = LoggerFactory.getLogger(com.infoshareacademy.jjdd1.teamerror.AfterInitialDataServlet.class);


    public static void setReqParametersToSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

        LOGGER.info("Inisial data req params: country-{} fuelType-{} fuelUsage-{} full distance-{} date1-{} date2-{}",
                country, fuelType, fuelUsage, fullDistance, date1, date2);

    }
}