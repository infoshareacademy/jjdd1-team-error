package com.infoshareacademy.jjdd1.teamerror.fileUpload;

import com.infoshareacademy.jjdd1.teamerror.file_loader.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by sebastianlos on 08.05.17.
 */
@WebServlet(urlPatterns = "/upload")
@MultipartConfig
public class FileUploader extends HttpServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(FileUploader.class);

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        Part petrolFile = req.getPart("petrolFile");
        Part currencyInfoFile = req.getPart("currencyInfoFile");
        Part currencyZipFile = req.getPart("currencyZipFile");
        LOGGER.debug("Getting files as request parameters {}", petrolFile, currencyInfoFile, currencyZipFile);

        saveSourceFiles(petrolFile, currencyInfoFile, currencyZipFile);

        if (SourceFilesChecker.checkForSourceFiles(req, resp)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/missingFiles.jsp");
            dispatcher.forward(req, resp);
            LOGGER.debug("Redirection to missingFiles.jsp");
            return;
        }

        req.setAttribute("start", "");
        resp.sendRedirect("/start");
    }

    private void saveSourceFiles(Part petrolFile, Part currencyInfoFile, Part currencyZipFile) throws IOException {
        Path pathToDirectory = Paths.get(FileReader.PATH_TO_FILES);
        if (!Files.exists(pathToDirectory)) {
            Files.createDirectory(pathToDirectory);
        }
        if(petrolFile.getSize() != 0) {
            FileSaver.saveFileOnServer(FileReader.PETROL_FILE_NAME, petrolFile.getInputStream());
        }
        if(currencyInfoFile.getSize() != 0) {
            FileSaver.saveFileOnServer(FileReader.CURRENCY_INFO_FILE, currencyInfoFile.getInputStream());
        }
        if(currencyZipFile.getSize() != 0) {
            FileSaver.saveFileOnServer(FileReader.CURRENCY_ZIP_FILE, currencyZipFile.getInputStream());
        }
    }


}
