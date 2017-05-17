package com.infoshareacademy.jjdd1.teamerror.logging;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.awt.SystemColor.window;
import static java.lang.System.out;

/**
 * Created by Iga on 17.05.2017.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       if (request.getSession() != null){
           response.sendRedirect("https://accounts.google.com/Logout");
//           response.sendRedirect("/login.jsp");
//           PrintWriter printWriter = response.getWriter();
//           out.println("<html><body>");
//           out.println ("<script>window.open('https://accounts.google.com/Logout', 'mywin',\n" +
//                   "'left=20,top=20,width=500,height=500,toolbar=1,resizable=0')</script>");
//           out.println("<body><html>");

       }
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

}
