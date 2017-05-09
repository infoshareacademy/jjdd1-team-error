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
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

//        String fileName = Paths.get(petrolFile.getSubmittedFileName()).getFileName().toString();
//        LOGGER.debug("File name: {}", fileName);

        Path pathToDirectory = Paths.get(FileReader.PATH_TO_FILES);
        if (!Files.exists(pathToDirectory)) {
            Files.createDirectory(pathToDirectory);
        }

        if(petrolFile.getSize() != 0) {
            LOGGER.debug("Converting petrol file from Part format to InputStream");
            Path pathToPetrolFile = Paths.get(FileReader.PATH_TO_FILES +
                    FileReader.PETROL_FILE_NAME);
            LOGGER.debug("Saving petrol file on server");

            Files.copy(petrolFile.getInputStream(), pathToPetrolFile, StandardCopyOption.REPLACE_EXISTING);
            LOGGER.info("Petrol file saved on server");
        }
        if(currencyInfoFile.getSize() != 0) {
            LOGGER.debug("Converting currency info file from Part format to InputStream");
            Path pathToCurrencyInfoFile = Paths.get(FileReader.PATH_TO_FILES +
                    FileReader.CURRENCY_INFO_FILE);
            LOGGER.debug("Saving currency info file on server");
            Files.copy(currencyInfoFile.getInputStream(), pathToCurrencyInfoFile, StandardCopyOption.REPLACE_EXISTING);
            LOGGER.info("Currency info file saved on server");
        }
        if(currencyZipFile.getSize() != 0) {
            LOGGER.debug("Converting currency zip file from Part format to InputStream");
            Path pathToCurrencyZipFile = Paths.get(FileReader.PATH_TO_FILES +
                    FileReader.CURRENCY_ZIP_FILE);
            LOGGER.debug("Saving currency zip file on server");
            Files.copy(currencyZipFile.getInputStream(), pathToCurrencyZipFile, StandardCopyOption.REPLACE_EXISTING);
            LOGGER.info("Currency zip file saved on server");
        }

        if (SourceFilesChecker.checkForSourceFiles(req, resp)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/missingFiles.jsp");
            dispatcher.forward(req, resp);
            LOGGER.debug("Redirection to missingFiles.jsp");
            return;
        }

//        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
//        dispatcher.forward(req, resp);

        req.setAttribute("start", "");
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/calc");
//        dispatcher.forward(req, resp);


        resp.sendRedirect("/calc");
    }

}
