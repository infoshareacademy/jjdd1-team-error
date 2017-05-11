package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingClass;
import com.google.gson.Gson;
import com.infoshareacademy.jjdd1.teamerror.file_loader.*;
import com.infoshareacademy.jjdd1.teamerror.file_loader.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by krystianskrzyszewski on 19.04.17.
 */

@WebServlet(urlPatterns = "/calc")
public class InitialServlet extends HttpServlet {

    private final String TRIP_FULL_COST_SESSION_ATTR = "fripFullCost";
    private final Logger LOGGER = LoggerFactory.getLogger(InitialServlet.class);
    int switcher = 0;

    @Inject
    SavingClass savingClass;

    @Inject
    InitialData initialData;

    @Inject
    HttpSession session;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.debug("servlet request");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        LOGGER.debug("Reading data from database");
        List<String> ret = savingClass.getPromotedCountries();
        LOGGER.debug("List of promoted countries from database: {}", ret);
        initialData.promotedCountries.setPromotedCountries(ret);
        LOGGER.info("Data from database successfully loaded");

        // countries/currencies check
        initialData.filesContent.getPetrolDataFile();
        initialData.filesContent.getCurrencyInfoFile();
        initialData.countryAndCurrency.setFilesContent(initialData.filesContent);
        initialData.countryAndCurrencyList = initialData.countryAndCurrency.getCountryAndCurrency();

        LOGGER.debug("Checking existence of resource files");
        URL petrolFileURL = (FileReader.class.getResource(FileReader.PATH_TO_FILES +
                FileReader.PETROL_FILE_NAME));

        URL currencyInfoFileURL = (FileReader.class.getResource(FileReader.PATH_TO_FILES +
                FileReader.CURRENCY_INFO_FILE));

        URL currencyZipFileURL = (FileReader.class.getResource(FileReader.PATH_TO_FILES +
                FileReader.CURRENCY_ZIP_FILE));

        if(petrolFileURL == null || currencyInfoFileURL == null || currencyZipFileURL == null) {
            req.setAttribute("missingFile",  "yes");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/missingFiles.jsp");
            dispatcher.forward(req, resp);
            LOGGER.error("At least one source file is missing");
            return;
        }


        req.setAttribute("countryList", initialData.promotedCountries.getOrderedPromotedCountries());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/initialData.jsp");
        dispatcher.forward(req, resp);
    }
}
