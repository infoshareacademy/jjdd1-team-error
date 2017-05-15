package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingCountryStatistics;
import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingCurrencyStatistics;
import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingFuelTypeStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

/**
 * Created by sebastianlos on 12.05.17.
 */
@Singleton
public class ReportsSender {

    @Inject
    SavingCountryStatistics savingCountryStatistics;

    @Inject
    SavingCurrencyStatistics savingCurrencyStatistics;

    @Inject
    SavingFuelTypeStatistics savingFuelTypeStatistics;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportsSender.class);

    private static void sendAnEmail(String subject, String message) {

        LOGGER.info("Getting in sendAnEmail class and setting up Mail Server properties");
        Properties mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        LOGGER.info("Mail server properties setup successfully");

        LOGGER.debug("Getting mail session");
        Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        MimeMessage generateMailMessage = new MimeMessage(getMailSession);
        try {
            generateMailMessage.addRecipient(Message.RecipientType.TO,
                    new InternetAddress("teamerror.tripcalculator@gmail.com"));
            generateMailMessage.addRecipient(Message.RecipientType.CC,
                    new InternetAddress("teamerror.tripcalculator@gmail.com"));
            generateMailMessage.setSubject(subject);
            generateMailMessage.setContent(message, "text/html");
        } catch (MessagingException e) {
            LOGGER.warn("Problem with mail session, exception: {}", e);
        }
        LOGGER.debug("Mail session has been created successfully");

        LOGGER.debug("Getting session and sending email");
        Transport transport = null;
        try {
            transport = getMailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", "teamerror.tripcalculator", "errorerror");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            LOGGER.info("Email sent successfully");
        } catch (MessagingException e) {
            LOGGER.warn("Sending email failed, exception: {}", e);
        } finally {
            try {
                if (transport != null) {
                    transport.close();
                }

            } catch (MessagingException e) {
                LOGGER.warn("Problem with closing transport, exception: ", e);
            }
        }
    }

    @Schedule(hour = "8")
    public void sendCountryAndCurrencyReport() {
        String subject = "New report - " + LocalDate.now();
        List<String> countries = savingCountryStatistics.getListOfCountries();
        List<Integer> countryPopularity = savingCountryStatistics.getListOfPopularity();
        List<String> currencies = savingCurrencyStatistics.getListOfCurrencies();
        List<Integer> currenciesPopularity = savingCurrencyStatistics.getListOfPopularity();
        Integer dieselPopularity = savingFuelTypeStatistics.getPopularity("diesel");
        Integer gasolinePopularity = savingFuelTypeStatistics.getPopularity("gasoline");

        StringBuilder report = new StringBuilder();
        report.append("New report <br>--------------------------------------<br>");
        createMultipleValues(countries, countryPopularity, report);
        createMultipleValues(currencies, currenciesPopularity, report);
        report.append("diesel ");
        report.append(dieselPopularity);
        report.append("<br>");
        report.append("gasoline ");
        report.append(gasolinePopularity);
        report.append("<br>");
        sendAnEmail(subject, report.toString());
    }

    private void createMultipleValues(List<String> countries, List<Integer> countryPopularity,
                                      StringBuilder report) {
        for (int i = 0; i < countries.size(); i++) {
            report.append(countries.get(i));
            report.append(" ");
            report.append(countryPopularity.get(i));
            report.append("<br>");
            report.append("--------------------------------------<br>");
        }
    }
}
