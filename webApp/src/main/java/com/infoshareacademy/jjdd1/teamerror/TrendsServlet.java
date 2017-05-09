package com.infoshareacademy.jjdd1.teamerror;

import com.google.gson.Gson;
import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by krystianskrzyszewski on 09.05.17.
 */
@WebServlet(urlPatterns = "/trends")
public class TrendsServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(InitialServlet.class);

    @Inject
    SavingClass savingClass;

    @Inject
    InitialData initialData;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        initialData.trendy.setTripFullCost(initialData.cost);
        initialData.trendy.setTrendy();
        LOGGER.info("calculated trend for trip: country-{} currency-{} fuel type-{}",
                initialData.cost.getCountry(), initialData.cost.getCurrency(), initialData.cost.getFuelType());

        Gson gson = new Gson();
        String json1 = gson.toJson(initialData.trendy.getPeriodTrendy().keySet());
        LOGGER.info("Map key set: {} ",json1);
        String json2 = gson.toJson(initialData.trendy.getPeriodTrendy().values());
        LOGGER.info("Values: {}", json2);

        req.setAttribute("periodTrendy", initialData.trendy.getPeriodTrendy());
        req.setAttribute("conclusion", initialData.trendy.getConclusion());
        req.setAttribute("tripLength", initialData.trendy.getTripLength());
        req.setAttribute("trendPeriodFrom",
                initialData.trendy.getTrendyPeriodFrom().toString().replaceAll("-", "/"));
        req.setAttribute("trendPeriodTill",
                initialData.trendy.getTrendyPeriodTill().toString().replaceAll("-", "/"));
        req.setAttribute("startingDays", initialData.trendy.getStartingDaysString());
        req.setAttribute("datesForTrends", json1);
        req.setAttribute("valuesForTrends", json2);

        String periodDateFrom = req.getParameter("periodDateFrom");
        String periodDateTill = req.getParameter("periodDateTill");
        String[] startingDays = req.getParameterValues("startingDays");
        String tripLength = req.getParameter("tripLength");
        LOGGER.debug("Trendy parameters - DateFrom: {} DateTill: {} TripLength: {} WeekDays: {}",
                periodDateFrom, periodDateTill, tripLength, startingDays);
        if (periodDateFrom != null && periodDateTill != null & tripLength != null && startingDays != null) {
            periodDateFrom = periodDateFrom.replaceAll("/", "");
            periodDateTill = periodDateTill.replaceAll("/", "");
            initialData.trendy.setTrendyPeriodFrom(periodDateFrom);
            initialData.trendy.setTrendyPeriodTill(periodDateTill);
            initialData.trendy.setTripLength(tripLength);
            initialData.trendy.setStartingDays(new HashSet<>(Arrays.asList(startingDays)));

            LOGGER.debug("Trendy parameters changed - DateFrom: {} DateTill: {} TripLength: {}",
                    periodDateFrom, periodDateTill, tripLength);
        }




    }

}
