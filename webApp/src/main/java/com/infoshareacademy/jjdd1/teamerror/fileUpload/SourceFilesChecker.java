package com.infoshareacademy.jjdd1.teamerror.fileUpload;

import com.infoshareacademy.jjdd1.teamerror.file_loader.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by sebastian_los on 09.05.17.
 */

public class SourceFilesChecker extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(SourceFilesChecker.class);

    public static boolean checkForSourceFiles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        String petrolFilePath = FileReader.PATH_TO_FILES + FileReader.PETROL_FILE_NAME;
        LOGGER.debug("Checking existence of resource files path: {}", petrolFilePath);
        File petrolFile = new File(petrolFilePath);

        String currencyInfoFilePath = FileReader.PATH_TO_FILES + FileReader.CURRENCY_INFO_FILE;
        LOGGER.debug("Checking existence of resource files path: {}", currencyInfoFilePath);
        File currencyInfoFile = new File(currencyInfoFilePath);

        String currencyZipFilePath = FileReader.PATH_TO_FILES + FileReader.CURRENCY_ZIP_FILE;
        LOGGER.debug("Checking existence of resource files path: {}", currencyZipFilePath);
        File currencyZipFile = new File(currencyZipFilePath);

        if(!petrolFile.exists() || !currencyInfoFile.exists() || !currencyZipFile.exists()) {
            req.setAttribute("petrolFileStatus",  "ok");
            req.setAttribute("currencyInfoFileStatus",  "ok");
            req.setAttribute("currencyZipFileStatus",  "ok");

            if (!petrolFile.exists()) {
                req.setAttribute("petrolFileStatus",  "missing");
                LOGGER.warn("Petrol file missing");
            }
            if (!currencyInfoFile.exists()) {
                req.setAttribute("currencyInfoFileStatus",  "missing");
                LOGGER.warn("Currency info file missing");
            }
            if (!currencyZipFile.exists()) {
                req.setAttribute("currencyZipFileStatus",  "missing");
                LOGGER.warn("Currency zim file missing");
            }
            return true;
        }
        LOGGER.info("Source files found.");
        return false;
    }

}

