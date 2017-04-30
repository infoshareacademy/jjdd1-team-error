package com.infoshareacademy.jjdd1.teamerror.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by igafalkowska on 28.04.17.
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet{
    private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Inject
    SessionData session;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (Objects.nonNull(username) && Objects.nonNull(password) &&
                username.equals("username") && password.equals("password")) {

            logger.debug("{} is logged", username);
            session.logUser(username);
            resp.sendRedirect(req.getParameter("referrer"));

            return;
        }

        resp.sendRedirect("/login?msg=Login error");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
