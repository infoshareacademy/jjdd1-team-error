package com.infoshareacademy.jjdd1.teamerror.logging;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.Gson;
import com.infoshareacademy.jjdd1.teamerror.Statistics;
import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingAdminBase;
import com.infoshareacademy.jjdd1.teamerror.fileUpload.SourceFilesChecker;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final String MAIN_DOMAIN = "https://team-error.jjdd1.is-academy.pl";
    private static Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);

    @Inject
    SessionData sessionData;

    @Inject
    Statistics statistics;

    private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v2/userinfo";

    private OAuth20Service service = null;

    private OAuth20Service getOAuthService(HttpServletRequest req) {
        if (service == null) {
            String path = getProperPath(req);
            String CLIENT_ID = "447589672882-lon09s9eq542cpusfm4njbkjcuhpgif7.apps.googleusercontent.com";
            String CLIENT_SECRET = "kypWEr8p2gMxtv1DZZG6g2mt";
            service = new ServiceBuilder()
                    .apiKey(CLIENT_ID)
                    .apiSecret(CLIENT_SECRET)
                    .scope("profile")
                    .scope("email")
                    .callback(path)
                    .build(GoogleApi20.instance());
        }

        return service;
    }

    private String getProperPath(HttpServletRequest req) {
        String hostAddress = req.getServerName();
        Integer portName = req.getServerPort();
        if (hostAddress.equals("localhost")) {
            return "http://" + hostAddress + ":" + portName + "/login";
        }
        else {
            return MAIN_DOMAIN + "/login";
        }
    }

    @Inject
    SavingAdminBase savingAdminBase;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        if (null != req.getParameter("error")) {
            req.setAttribute("error", req.getParameter("error"));
            return;
        }

        //setting the current list of admins
        HttpSession session = req.getSession(true);
        session.setAttribute("adminList", savingAdminBase.getListOfAdmins());
        LOGGER.info("Admin List data : {} ", session.getAttribute("adminList"));

        //creating a JSON admin list, to later use it in JavaScript in footer.jsp
        List<String> adminList = savingAdminBase.getListOfAdmins();
        String adminListString = new Gson().toJson(adminList);
        session.setAttribute("jsonAdminList", adminListString);
        LOGGER.info("JSON string from the Admin List: {} ", adminListString);

        final String code = req.getParameter("code");
        if (null != code) {
            OAuth2AccessToken accessToken = null;

            try {
                accessToken = getOAuthService(req).getAccessToken(code);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
            getOAuthService(req).signRequest(accessToken, request);

            Response response = null;
            try {
                response = getOAuthService(req).execute(request);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            if (response != null && response.getCode() != 200) {
                req.setAttribute("error", "Brak połączenia z api google");
            } else {
                String googleJson = null;
                if (response != null) {
                    googleJson = response.getBody();
                }
                Gson gson = new Gson();
                GoogleUser googleUser = gson.fromJson(googleJson, GoogleUser.class);

                sessionData.logUser(googleUser.getGiven_name(), googleUser.getFamily_name(), googleUser.getEmail());
                String path = getProperPath(req);
                resp.sendRedirect(path);

            }
        }
            Map<String, String> sessionUser = new HashMap<>();
            sessionUser.put("given_name", sessionData.getUserFirstName());
            sessionUser.put("family_name", sessionData.getUserSecondName());
            sessionUser.put("email", sessionData.getEmail());
            req.setAttribute("oauth", sessionUser);
            LOGGER.debug(sessionUser.get("given_name"));
            LOGGER.debug(sessionUser.get("family_name"));
            LOGGER.debug(sessionUser.get("email"));

            //if there is an email (a user is logged in) use the userEmail to string
            if (sessionData.getEmail() != null) {
                session.setAttribute("userEmail", (sessionData.getEmail()));
                LOGGER.debug("UserEmail data: {} ", (sessionData.getEmail()));
            }

            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();

            statistics.updateStatisticsOfUserData(sessionUser.get("given_name"), sessionUser.get("family_name"),
                    sessionUser.get("email"), date.toString(), time.toString());
            req.setAttribute("isLogged", sessionData.isLogged());

            if (SourceFilesChecker.checkForSourceFiles(req, resp)) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/missingFiles.jsp");
                dispatcher.forward(req, resp);
                return;
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
            dispatcher.forward(req, resp);
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("login").equals("1")) {
            final Map<String, String> additionalParams = new HashMap<>();
            additionalParams.put("access_type", "offline");
            additionalParams.put("prompt", "consent");
            resp.sendRedirect(getOAuthService(req).getAuthorizationUrl(additionalParams));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
