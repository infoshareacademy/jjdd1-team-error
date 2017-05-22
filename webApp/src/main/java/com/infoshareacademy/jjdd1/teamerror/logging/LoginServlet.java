package com.infoshareacademy.jjdd1.teamerror.logging;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.infoshareacademy.jjdd1.teamerror.AfterInitialDataServlet;
import com.infoshareacademy.jjdd1.teamerror.ReportsSender;
import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingAdminBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by igafalkowska on 28.04.17.
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Inject
    SavingAdminBase savingAdminBase;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        try {
            String idToken = req.getParameter("id_token");
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
            String name = (String) payLoad.get("name");
            String email = payLoad.getEmail();
            logger.debug("User name: " + name);
            logger.debug("User email: " + email);

            HttpSession session = req.getSession(true);
            session.setAttribute("userName", name);
            session.setAttribute("adminList", savingAdminBase.getListOfAdmins());

            for (String s: savingAdminBase.getListOfAdmins()) {
                if(email.equals(s)){
                    session.setAttribute("userMail", email);
                }
            }

            logger.info("User data : {} and {}", name, session.getAttribute("adminMail"));

            req.getServletContext()
                    .getRequestDispatcher("/index.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
