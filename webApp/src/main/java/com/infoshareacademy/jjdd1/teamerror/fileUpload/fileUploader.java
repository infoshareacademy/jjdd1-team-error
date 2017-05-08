package com.infoshareacademy.jjdd1.teamerror.fileUpload;

import com.infoshareacademy.jjdd1.teamerror.InitialServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sebastianlos on 08.05.17.
 */
@WebServlet(urlPatterns = "/fileUploader")
public class fileUploader extends HttpServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(fileUploader.class);

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");
    }

}
