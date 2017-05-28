package com.infoshareacademy.jjdd1.teamerror.logging;

/**
 * Created by Iga on 21.05.2017.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/start", "/tripCost", "/report", "/trendy", "/", "/admin"})
public class LoginFilter implements  Filter{

        private static Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);

        @Inject
        SessionData sessionData;

        @Override
        public void doFilter(ServletRequest request,
                             ServletResponse response,
                             FilterChain chain) throws IOException, ServletException {

            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            if (sessionData.isLogged() == false) {
                LOGGER.debug("User not logged, redirecting to login page");
                httpServletResponse.sendRedirect("/login");
                return;
            }

            LOGGER.debug("User {} {} already logged  in", sessionData.getUserFirstName(), sessionData.getUserSecondName());
            chain.doFilter(request, response);
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void destroy() {

        }
}
