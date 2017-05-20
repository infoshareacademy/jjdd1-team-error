package com.infoshareacademy.jjdd1.teamerror.logging;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = "/check")
public class AdminLoginServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(AdminLoginServlet.class);

    public static void setReqParametersToSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        String loginName = req.getParameter("loginName");
        if (loginName != null) {
            session.setAttribute("loginName", loginName);
        }

        String passwordName = req.getParameter("passwordName");
        if (passwordName != null) {
            session.setAttribute("passwordName", passwordName);
        }

        logger.info("Admin data given: {} and {}", loginName, passwordName);

        try {
            String idToken = req.getParameter("id_token");
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
            String name = (String) payLoad.get("name");
            String email = payLoad.getEmail();
            logger.debug("User name: " + name);
            logger.debug("User email: " + email);

            HttpSession session = req.getSession(true);
            session.setAttribute("userName", name);
            req.getServletContext()
                    .getRequestDispatcher("/index.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
