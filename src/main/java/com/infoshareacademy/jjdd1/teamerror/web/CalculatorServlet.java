package com.infoshareacademy.jjdd1.teamerror.web;

import com.infoshareacademy.jjdd1.teamerror.TerminalMenu;
import com.infoshareacademy.jjdd1.teamerror.TripFullCost;
import com.infoshareacademy.jjdd1.teamerror.file_loader.CachedFilesContent;
import com.infoshareacademy.jjdd1.teamerror.file_loader.CurrencyFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

/**
 * Created by krystianskrzyszewski on 19.04.17.
 */

@WebServlet(urlPatterns = "/calculate")
public class CalculatorServlet extends HttpServlet {

    private static Logger LOGGER = LoggerFactory.getLogger(CalculatorServlet.class);
    @Inject
    TerminalMenu menu;

    @Inject
    TripFullCost trip;

    @Inject
    CachedFilesContent cachedFilesContent;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String currency = req.getParameter("currency");
        String country = req.getParameter("country");
        String fuelType = req.getParameter("fuelType");

//        if (Objects.isNull(currency)) {
//            resp.sendError(400, "Brak parametru `word`");
//            return;
//        }

//        List<String> wordList = cachedFilesContent.getCurrencyDataFile(currency);
//        for (String w : wordList){
//            System.out.println(w);
//        }

//        System.out.println(menu.hashCode());

        req.setAttribute("currency", currency);
        req.setAttribute("country", country);
        req.setAttribute("fuelType", fuelType);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);

        System.out.println(country+" "+currency+" "+fuelType);
    }

//    public void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
//        doGet(req, resp);
//    }
}
