package com.infoshareacademy.jjdd1.teamerror.web;

import com.infoshareacademy.jjdd1.teamerror.TerminalMenu;
import com.infoshareacademy.jjdd1.teamerror.TripFullCost;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by krystianskrzyszewski on 19.04.17.
 */

@WebServlet(urlPatterns = "/")
public class CalculatorServlet extends HttpServlet {
    private String message;

    public void init() throws ServletException{
        message = "Hello World";
    }

    @Inject
    TerminalMenu menu;

    @Inject
    TripFullCost trip;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setIntHeader("Refresh",5);
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.println("<h1>" + message + "</h1>" +"\n");

        String title = "Using GET Method to Read From Data + Auto Refresh Header Setting";

        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        Calendar calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String CT = hour+":"+minute+":"+second;



        out.println(docType + "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>First Name</b>: "
                + req.getParameter("first_name") + "\n" +
                "  <li><b>Last Name</b>: "
                + req.getParameter("last_name") + "\n" +
                " The time is: " + CT +
                "</ul>\n" +
                "</body></html>");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        doGet(req, resp);
    }
}
