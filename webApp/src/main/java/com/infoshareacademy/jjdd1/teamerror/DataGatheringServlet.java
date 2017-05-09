package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingClass;
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
 * Created by krystianskrzyszewski on 09.05.17.
 */

@WebServlet(urlPatterns = "/data")
public class DataGatheringServlet extends HttpServlet {
    private final Logger LOGGER = LoggerFactory.getLogger(InitialServlet.class);
    @Inject
    SavingClass savingClass;

    @Inject
    InitialData initialData;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

    }



}
