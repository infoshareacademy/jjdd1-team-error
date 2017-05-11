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

@WebServlet(urlPatterns = "/data")
public class DataGatheringServlet extends HttpServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(InitialServlet.class);

    @Inject
    SavingClass savingClass;

    @Inject
    InitialData initialData;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        initialData.cost.setCountry(req.getParameter("country").toUpperCase());
        initialData.cost.setFuelType(req.getParameter("fuelType"));
        initialData.cost.setDate1(req.getParameter("date1").replaceAll("/",""));
        initialData.cost.setDate2(req.getParameter("date2").replaceAll("/",""));
        initialData.cost.setFuelUsage(req.getParameter("fuelUsage"));
        initialData.cost.setDistance(req.getParameter("fullDistance"));

        try{
            initialData.cost.costCount();
        }catch(Exception e){
            LOGGER.error("Something went wrong. Please check your input (above)", e);
        }

        LOGGER.info("servlet req params: date1-{} date2-{} fuel usage-{} full distance-{}",
                initialData.cost.getDate1(), initialData.cost.getDate2(),
                initialData.cost.getFuelUsage(), initialData.cost.getDistance());
        LOGGER.info(initialData.cost.toString());

        session.setAttribute("country", initialData.cost.getCountry());
        session.setAttribute("currency", initialData.cost.getCurrency());
        session.setAttribute("fuelType", initialData.cost.getFuelType());
        session.setAttribute("date1", initialData.cost.getDate1());
        session.setAttribute("date2", initialData.cost.getDate2());
        session.setAttribute("fuelUsage", initialData.cost.getFuelUsage());
        session.setAttribute("fullDistance", initialData.cost.getDistance());
        session.setAttribute("fullCost", initialData.cost.costCount());

        LOGGER.info("initialData trip atributes set:{} {} {} {} {} {}",
                initialData.cost.getCountry(), initialData.cost.getFuelType(), initialData.cost.getDate1(),
                initialData.cost.getDate2(), initialData.cost.getFuelUsage(), initialData.cost.getDistance());


        if(req.getParameter("trendy") != null){
            req.setAttribute("title", "Optimal time for trip");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/trendy.jsp");
            dispatcher.forward(req, resp);
        }
        else if (req.getParameter("tripCost") != null){
            req.setAttribute("title", "Estimated cost of the trip");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/tripCost.jsp");
            dispatcher.forward(req, resp);
        }
    }



}
