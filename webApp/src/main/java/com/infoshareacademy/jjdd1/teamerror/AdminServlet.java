package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingClass;
import com.infoshareacademy.jjdd1.teamerror.fileUpload.FileDownloader;
import com.infoshareacademy.jjdd1.teamerror.file_loader.FilesContent;
import com.infoshareacademy.jjdd1.teamerror.file_loader.PromotedCountries;
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

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(WelcomeServlet.class);
    private HttpSession session;
    private FilesContent filesContent;

    @Inject
    PromotedCountries promotedCountries;

    @Inject
    SavingClass savingClass;

    @Inject
    FileDownloader fileDownloader;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

}
