package com.infoshareacademy.jjdd1.teamerror.web;

import com.infoshareacademy.jjdd1.teamerror.TerminalMenu;
import com.infoshareacademy.jjdd1.teamerror.TripFullCost;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by krystianskrzyszewski on 18.04.17.
 */
@WebServlet(urlPatterns = "/")
public class CalculatorServlet extends HttpServlet {

    @Inject
    TerminalMenu terminal;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        terminal.menu();

        //a tutaj ją wykorzystamy

        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
//        np. wyświetlając na ekran
//        usa.forEach(writer::println);
        writer.flush();
    }
}