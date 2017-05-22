package com.infoshareacademy.jjdd1.teamerror.logging;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Iga on 17.05.2017.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

        @Inject
        SessionData sessionData;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            sessionData.logoutUser();
            resp.sendRedirect("/login");

        }

    }

