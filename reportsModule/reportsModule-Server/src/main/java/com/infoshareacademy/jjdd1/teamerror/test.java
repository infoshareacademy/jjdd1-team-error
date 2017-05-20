package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.service.CountryMapWrapper;
import com.infoshareacademy.jjdd1.teamerror.service.ReportsService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sebastian_los on 19.05.17.
 */
public class test {

    private static final String SERVICE_URL = "http://localhost:9996/statistics";
    private static final String NAMESPACE = "http://server.teamerror.jjdd1.infoshareacademy.com/";

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL(SERVICE_URL);
        QName qName = new QName(NAMESPACE, "ReportsServiceImplService");
        Service service = Service.create(url, qName);
        ReportsService reportsService = service.getPort(ReportsService.class);

        reportsService.updateCountryStatistics("Polska");
        reportsService.updateCountryStatistics("Polska");
        reportsService.updateCountryStatistics("Polska");
        reportsService.updateCountryStatistics("Niemcy");
        reportsService.updateCountryStatistics("Chiny");
        reportsService.updateCountryStatistics("Polska");
        reportsService.updateCountryStatistics("Ameryka");
        reportsService.updateCountryStatistics("Polska");
        reportsService.updateCountryStatistics("Bali");

        CountryMapWrapper countryMapWrapper = reportsService.getCountryStatistics();

        System.out.println(countryMapWrapper.getCountryStatistics().toString());
    }
}
