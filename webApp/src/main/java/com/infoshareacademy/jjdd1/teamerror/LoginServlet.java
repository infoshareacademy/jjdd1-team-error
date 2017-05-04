//package com.infoshareacademy.jjdd1.teamerror;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.inject.Inject;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Objects;
//
///**
// * Created by krystianskrzyszewski on 27.04.17.
// */
//@WebServlet(urlPatterns = "/login")
//public class LoginServlet extends HttpServlet {
//
//    private static Logger log = LoggerFactory
//
//    @Inject
//    SessionData sessionData;
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//
//        if(Objects.nonNull(username) && Objects.nonNull(password) && username.equals("jakub") && password.equals("isa")){
//            log.debug("Zalogował się {}", username);
//            sessionData.logUser(username);
//
//
//
//        }
////        super.doPost(req,resp);
////        System.out.println("Is user logged?" + sessionData.isLogged());
////        sessionData.logUser();
////        System.out.println("Is user logged?" + sessionData.isLogged());
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//
//    }
//}
