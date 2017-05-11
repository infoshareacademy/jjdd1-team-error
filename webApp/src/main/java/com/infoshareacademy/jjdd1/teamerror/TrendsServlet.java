package com.infoshareacademy.jjdd1.teamerror;

import com.google.gson.Gson;
import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingClass;
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
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by krystianskrzyszewski on 09.05.17.
 */
@WebServlet(urlPatterns = "/trends")
public class TrendsServlet extends HttpServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(InitialServlet.class);

    @Inject
    SavingClass savingClass;

    @Inject
    InitialData initialData;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        Gson gson = new Gson();

        initialData.trendy.setTripFullCost(initialData.cost);
        initialData.trendy.setTrendy();
        LOGGER.info("calculated trend for trip: country-{} currency-{} fuel type-{}", session.getAttribute("country")
                , initialData.cost.getCurrency(), initialData.cost.getFuelType());

        String periodDateFrom = req.getParameter("periodDateFrom");
        String periodDateTill = req.getParameter("periodDateTill");
        String[] startingDays = req.getParameterValues("startingDays");
        String tripLength = req.getParameter("tripLength");
        String json1="";
        String json2="";
        LOGGER.debug("Trendy parameters - DateFrom: {} DateTill: {} TripLength: {} WeekDays: {}",
                periodDateFrom, periodDateTill, tripLength, startingDays);
        if (periodDateFrom != null && periodDateTill != null & tripLength != null && startingDays != null) {
            periodDateFrom = periodDateFrom.replaceAll("/", "");
            periodDateTill = periodDateTill.replaceAll("/", "");
            initialData.trendy.setTrendyPeriodFrom(periodDateFrom);
            initialData.trendy.setTrendyPeriodTill(periodDateTill);
            initialData.trendy.setTripLength(tripLength);
            initialData.trendy.setStartingDays(new HashSet<>(Arrays.asList(startingDays)));
            json1 = gson.toJson(initialData.trendy.getPeriodTrendy().keySet());
            json2 = gson.toJson(initialData.trendy.getPeriodTrendy().values());

            LOGGER.debug("Trendy parameters changed - DateFrom: {} DateTill: {} TripLength: {}",
                    periodDateFrom, periodDateTill, tripLength);
        }

        if(json1.equals(null)){
            json1 = gson.toJson(initialData.trendy.getPeriodTrendy().keySet());
            LOGGER.info("Map key set: {} ",json1);
        }
        if(json2.equals(null)){
            json2 = gson.toJson(initialData.trendy.getPeriodTrendy().values());
            LOGGER.info("Values: {}", json2);
        }

        session.setAttribute("periodTrendy", initialData.trendy.getPeriodTrendy());
        session.setAttribute("conclusion", initialData.trendy.getConclusion());
        session.setAttribute("tripLength", initialData.trendy.getTripLength());
        session.setAttribute("trendPeriodFrom",
                initialData.trendy.getTrendyPeriodFrom().toString().replaceAll("-", "/"));
        session.setAttribute("trendPeriodTill",
                initialData.trendy.getTrendyPeriodTill().toString().replaceAll("-", "/"));
        session.setAttribute("startingDays", initialData.trendy.getStartingDaysString());
        session.setAttribute("datesForTrends", json1);
        session.setAttribute("valuesForTrends", json2);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/trendy.jsp");
        dispatcher.forward(req, resp);
    }

}
