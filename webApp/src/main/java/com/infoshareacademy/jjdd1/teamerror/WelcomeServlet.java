package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.dataBase.PromotedCountriesSaver;
import com.infoshareacademy.jjdd1.teamerror.dataBase.AdminBase;
import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingAdminBase;
import com.infoshareacademy.jjdd1.teamerror.fileUpload.FileDownloader;
import com.infoshareacademy.jjdd1.teamerror.fileUpload.SourceFilesChecker;
import com.infoshareacademy.jjdd1.teamerror.file_loader.CachedFilesContent;
import com.infoshareacademy.jjdd1.teamerror.file_loader.FilesContent;
import com.infoshareacademy.jjdd1.teamerror.file_loader.PromotedCountries;
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
import java.util.List;

/**
 * Created by sebastian_los on 09.05.17.
 */
@WebServlet(urlPatterns = "/start")
public class WelcomeServlet extends HttpServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(WelcomeServlet.class);
    private FilesContent filesContent;

    @Inject
    PromotedCountries promotedCountries;

    @Inject
    PromotedCountriesSaver promotedCountriesSaver;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (SourceFilesChecker.checkForSourceFiles(req, resp)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/missingFiles.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        if (session.getAttribute("filesContent") == null) {
            filesContent = new CachedFilesContent();
        }
        promotedCountries.setFilesContent(filesContent);


        LOGGER.debug("Reading data from database");
        List<String> ret = promotedCountriesSaver.getPromotedCountries();
        LOGGER.debug("List of promoted countries from database: {}", ret);
        promotedCountries.setPromotedCountries(ret);
        LOGGER.info("Data from database successfully loaded");



        req.setAttribute("countryList", promotedCountries.getOrderedPromotedCountries());
        session.setAttribute("filesContent", filesContent);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/initialData.jsp");
        dispatcher.forward(req, resp);
    }

}
