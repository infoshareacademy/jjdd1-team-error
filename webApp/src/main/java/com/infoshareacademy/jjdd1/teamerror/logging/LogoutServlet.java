package com.infoshareacademy.jjdd1.teamerror.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Iga on 17.05.2017.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

        private final Logger LOGGER = LoggerFactory.getLogger(LogoutServlet.class);

        @Inject
        SessionData sessionData;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            HttpSession session = req.getSession();

            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/plain;charset=UTF-8");

            session.setAttribute("country", null);
            session.setAttribute("fuelTypeString", null);
            session.setAttribute("fuelUsage", null);
            session.setAttribute("fullDistance", null);
            session.setAttribute("date1", null);
            session.setAttribute("date2", null);
            session.setAttribute("tripLength", null);
            session.setAttribute("trendPeriodFrom", null);
            session.setAttribute("trendPeriodTill", null);
            session.setAttribute("startingDays", null);

            sessionData.logoutUser();
            LOGGER.info("User logout. Initial data(country, fuelType, fuelUsage, full distance,date1, date2, tripLength, " +
                    "trendPeriodFrom, trendPeriodTill, startingDays) set to null." );

            resp.sendRedirect("/login");
        }

    }

