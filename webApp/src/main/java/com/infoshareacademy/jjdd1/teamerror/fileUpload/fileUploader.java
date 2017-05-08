package com.infoshareacademy.jjdd1.teamerror.fileUpload;

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
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by sebastianlos on 08.05.17.
 */
@WebServlet(urlPatterns = "/upload")
@MultipartConfig
public class fileUploader extends HttpServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(fileUploader.class);

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        Part petrolFile = req.getPart("petrolFile");
        LOGGER.debug("Getting file as request parameter {}", petrolFile);

//        String fileName = Paths.get(petrolFile.getSubmittedFileName()).getFileName().toString();
//        LOGGER.debug("File name: {}", fileName);

        LOGGER.debug("Converting file part into stream");
        InputStream contentOfFile = petrolFile.getInputStream();

//        Files.copy(contentOfFile, Files.createTempFile(Paths.get("vfs:/content/ROOT.war/WEB-INF/lib/consoleApp-1.0-SNAPSHOT.jar/com/infoshareacademy/jjdd1/teamerror/file_loader/files/"), "iSA-PetrolPrices", ".csv"));
        LOGGER.debug("Petrol file saved on server");


        RequestDispatcher dispatcher = req.getRequestDispatcher("/initialData.jsp");
        dispatcher.forward(req, resp);
    }

}
