package org.infoshare;

import org.infoshare.dataBase.SavingCountryStatistics;
import org.infoshare.dataBase.SavingCurrencyStatistics;
import org.infoshare.dataBase.SavingFuelTypeStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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

    @Schedule(dayOfWeek = "*")
    public void sendReportEmail() {
        String subject = "New report - " + LocalDate.now();
        Map<String, Integer> countryStatistics = savingCountryStatistics.getCountryStatistics();

        Map<String, Integer> currencyStatistics = savingCurrencyStatistics.getCurrenciesStatistics();

        Map<String, Integer> petrolStatistics = savingFuelTypeStatistics.getPetrolStatistics();


        StringBuilder report = new StringBuilder();
        report.append("New report - ");
        report.append( LocalDate.now());
        report.append("<br>--------------------------------------<br>");
        report.append("COUNTRY STATISTICS<br>");
        createMultipleValues(countryStatistics, report);
        report.append("<br>--------------------------------------<br>");
        report.append("CURRENCY STATISTICS<br>");
        createMultipleValues(currencyStatistics, report);
        report.append("<br>--------------------------------------<br>");
        report.append("FUEL TYPE STATISTICS<br>");
        createMultipleValues(petrolStatistics, report);
        sendAnEmail(subject, report.toString());
    }

    private void createMultipleValues(Map<String, Integer> statistics,
                                      StringBuilder report) {
        statistics.forEach((k,v) -> {
            report.append(k);
            report.append(" ");
            report.append(v);
            report.append("<br>");
        });
    }
}
