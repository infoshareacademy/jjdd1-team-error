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

/**
 * Created by igafalkowska on 28.04.17.
 */
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);

    @Inject
    SessionData sessionData;

    final String CLIENT_ID = "447589672882-lon09s9eq542cpusfm4njbkjcuhpgif7.apps.googleusercontent.com";
    final String CLIENT_SECRET = "kypWEr8p2gMxtv1DZZG6g2mt";
    private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v2/userinfo";

    private OAuth20Service service = null;

    private OAuth20Service getOAuthService(HttpServletRequest req) {
        if (service == null) {
            StringBuffer hostAddress = req. getRequestURL();
            service = new ServiceBuilder()
                    .apiKey(CLIENT_ID)
                    .apiSecret(CLIENT_SECRET)
                    .scope("profile")
                    .scope("email")
                    .callback(hostAddress + "/login")
                    .build(GoogleApi20.instance());
        }

        return service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        if (null != req.getParameter("error")) {
            req.setAttribute("error", req.getParameter("error"));
            return;
        }

        final String code = req.getParameter("code");
        if (null != code) {
            OAuth2AccessToken accessToken = null;

            try {
                accessToken = getOAuthService(req).getAccessToken(code);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
            getOAuthService(req).signRequest(accessToken, request);

            Response response = null;
            try {
                response = getOAuthService(req).execute(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if (response.getCode() != 200) {
                req.setAttribute("error", "Brak połączenia z api google");
            } else {
                String googleJson = response.getBody();
                Gson gson = new Gson();
                GoogleUser googleUser = gson.fromJson(googleJson, GoogleUser.class);

                    sessionData.logUser(googleUser.getGiven_name(), googleUser.getFamily_name(), googleUser.getEmail());
                    resp.sendRedirect("http://localhost:8080/login");

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

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("login").equals("1")) {
            final Map<String, String> additionalParams = new HashMap<>();
            additionalParams.put("access_type", "offline");
            additionalParams.put("prompt", "consent");
            resp.sendRedirect(getOAuthService(req).getAuthorizationUrl(additionalParams));
            req.setAttribute("oauth", "wysyłam żądanie do google...");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
