package com.infoshareacademy.jjdd1.teamerror.web;

import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by krystianskrzyszewski on 19.04.17.
 */

@WebServlet(urlPatterns = "/usa")
public class CalculatorServlet extends HttpServlet {

    @Inject
    Trendy trendy;


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(trendy.hashCode());
        String country = req.getParameter("country");
        String currency = req.getParameter("currency");
        String kindOfFuel = req.getParameter("kindOfFuel");

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");


        req.setAttribute("country", country);
        req.setAttribute("currency", currency);
        req.setAttribute("kindOfFuel", kindOfFuel);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);

        System.out.println(country + currency + kindOfFuel);
    }
}
