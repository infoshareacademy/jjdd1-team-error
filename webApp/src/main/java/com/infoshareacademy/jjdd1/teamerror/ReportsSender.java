package com.infoshareacademy.jjdd1.teamerror;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by sebastianlos on 12.05.17.
 */
public class ReportsSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportsSender.class);

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    public static void sendAnEmail(String subject, String message) {

        LOGGER.info("Getting in sendAnEmail class and setting up Mail Server properties");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        LOGGER.info("Mail server properties setup successfully");

        LOGGER.debug("Getting mail session");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        try {
            generateMailMessage.addRecipient(Message.RecipientType.TO,
                    new InternetAddress("teamerror.tripcalculator@gmail.com"));
            generateMailMessage.addRecipient(Message.RecipientType.CC,
                    new InternetAddress("teamerror.tripcalculator@gmail.com"));
            generateMailMessage.setSubject(subject);
            generateMailMessage.setContent(message, "text/html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        LOGGER.debug("Mail session has been created successfully");

        LOGGER.debug("Getting session and sending email");
        Transport transport = null;
        try {
            transport = getMailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", "teamerror.tripcalculator", "errorerror");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
            try {
                transport.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }


    }
}
