package com.infoshareacademy.jjdd1.teamerror.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by igafalkowska on 28.04.17.
 */
@WebFilter(urlPatterns = "/calc/initialData")
public class LoggingFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Inject
    SessionData sessionData;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if(!sessionData.isLogged()) {
            logger.debug("User not logged, redirecting...");

            String referrer = httpServletRequest.getRequestURL()
                    .append("?")
                    .append(httpServletRequest.getQueryString())
                    .toString();
            httpServletResponse.sendRedirect("/logging?url=" + referrer);
            return;
        }
        logger.debug("User {} already logged in", sessionData.getUsername());
        chain.doFilter(request,response);
    }


    @Override
    public void destroy() {

    }
}
