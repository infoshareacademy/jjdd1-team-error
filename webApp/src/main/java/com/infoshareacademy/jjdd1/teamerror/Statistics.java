package com.infoshareacademy.jjdd1.teamerror;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.infoshareacademy.jjdd1.teamerror.dataBase.CachedStatistics;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sebastianlos on 22.05.17.
 */

@Stateless
public class Statistics {

    @Inject
    CachedStatistics cachedStatistics;

    private final Logger LOGGER = LoggerFactory.getLogger(Statistics.class);
    private final String REPORTS_MODULE_PATH =
            "http://reportsmodule:8080/reportsModule-1.0-SNAPSHOT/";

    // doesn't work without public!!!
    public void updateStatisticsOfCountryAndCurrencyAndFuelType(String country, String currency, String fuelType) {
        LOGGER.debug("Starting statistics update");
        checkForCachedStatisticsOfCountryAndCurrencyAndFuelTypeAndSendToApi();
        Integer status = sendStatisticsToApi(country, currency, fuelType);
        if (status == null || status != 200) {
            cachedStatistics.setCashedStatisticsOfCountryAndCurrencyAndFuelType(country, currency, fuelType);
        }
    }

    // doesn't work without public!!!
    public Map<String, Integer> getStatisticsOfCountryOrCurrencyOrFuelType(String kind){
        try {
            checkForCachedStatisticsOfCountryAndCurrencyAndFuelTypeAndSendToApi();
            WebTarget target = getWebTarget(kind);
            Map<String, Integer> unsortedStatistics = getData(target);
            return mapSorterByValue(unsortedStatistics);
        }
        catch (Exception e) {
            LOGGER.warn("Getting statistics for {} failed", kind);
        }
        return null;
    }

    // don't remove public
    public void checkForCachedStatisticsOfCountryAndCurrencyAndFuelTypeAndSendToApi() {
        LOGGER.debug("Coming into checkForCachedStatisticsAndSendApi class");
        List<List<String>> cachedStatisticsList = this.cachedStatistics.getCashedStatisticsOfCountryAndCurrencyAndFuelType();
        if (!cachedStatisticsList.isEmpty()) {
            LOGGER.debug("Sending cached statistic data to Reports Module");
            for (List<String> values : cachedStatisticsList) {
                Integer status = sendStatisticsToApi(values.get(0), values.get(1), values.get(2));
                if (status == null || status != 200) {
                    LOGGER.debug("Sending cached statistics failed");
                    return;
                }
            }
            LOGGER.debug("Clearing cached statistics");
            cachedStatistics.clearCashedStatisticsOfCountryAndCurrencyAndFuelType();
        }
    }

    private Integer sendStatisticsToApi(String country, String currency, String fuelType) {

        Client client = new ResteasyClientBuilder().build();

        Form paramsForm = new Form();
        paramsForm.param("country", country);
        paramsForm.param("currency", currency);

        String fuelTypeName = fuelType.equals("1") ? "diesel" : "gasoline";
        paramsForm.param("fuelType", fuelTypeName);

        String REPORTS_MODULE_UPDATE_PATH = REPORTS_MODULE_PATH + "statisticsUpdate";
        LOGGER.debug("Getting WebTarget of {}", REPORTS_MODULE_UPDATE_PATH);
        WebTarget target = client.target(REPORTS_MODULE_UPDATE_PATH);

        Integer status = null;
        try {
            Response response = target.request().post((Entity.form(paramsForm)));
            status = response.getStatus();
        }
        catch (Exception e) {
            LOGGER.debug("Sending statistics to Reports Module failed");
        }
        LOGGER.debug("Statistics sending finished. Response status: ", status);
        return status;
    }

    private WebTarget getWebTarget(String kind) {
        Client client = new ResteasyClientBuilder().build();
        String path = REPORTS_MODULE_PATH + kind + "Statistics";
        LOGGER.debug("Getting WebTarget of {}", path);
        return client.target(path);
    }

    private Map<String, Integer> getData(WebTarget target) {
        Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
        int status = response.getStatus();
        if (status != 200) {
            LOGGER.warn("Response status: {}", response.getStatus());
            return null;
        }
        LOGGER.debug("Getting downloaded data");
        String result = response.readEntity(String.class);
        response.close();

        LOGGER.debug("Parsing data from Json to Map");
        Gson gson = new Gson();
        return gson.fromJson(result, new TypeToken<Map<String, Integer>>(){}.getType());
    }

    private Map<String, Integer> mapSorterByValue(Map<String, Integer> unsortedMap) {
        LOGGER.debug("Sorting map");
        Map<String, Integer> result = new LinkedHashMap<>();
        unsortedMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        return result;
    }

    public void updateStatisticsOfUserData(String firstName, String secondName, String email, String localDateString, String localTimeString) {
        LOGGER.debug("Starting user statistics update");
        checkForCachedStatisticsOfUserDataAndSendToApi();
        Integer status = sendUserStatisticsToApi(firstName, secondName, email, localDateString, localTimeString);
        if (status == null || status != 200) {
            cachedStatistics.setCashedStatisticsOfUserData(firstName, secondName, email, localDateString, localTimeString);
        }
    }

    public List<List<String>> getStatisticsOfUserData(){
        try {
            checkForCachedStatisticsOfUserDataAndSendToApi();
            String user = "user";
            WebTarget target = getWebTarget(user);
            return getUserData(target);
        }
        catch (Exception e) {
            LOGGER.warn("Getting user statistics for failed");
        }
        return null;
    }

    public void checkForCachedStatisticsOfUserDataAndSendToApi() {
        LOGGER.debug("Coming into checkForCachedStatisticsAndSendApi class");
        List<List<String>> cachedStatisticsList = this.cachedStatistics.getCashedStatisticsOfUserData();
        if (!cachedStatisticsList.isEmpty()) {
            LOGGER.debug("Sending cached statistic data to Reports Module");
            for (List<String> values : cachedStatisticsList) {
                Integer status = sendUserStatisticsToApi(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4));
                if (status == null || status != 200) {
                    LOGGER.debug("Sending cached user statistics failed");
                    return;
                }
            }
            LOGGER.debug("Clearing cached user statistics");
            cachedStatistics.clearCashedStatisticsOfUserData();
        }
    }

    private Integer sendUserStatisticsToApi(String firstName, String secondName, String email, String localDateString, String localTimeString) {

        Client client = new ResteasyClientBuilder().build();

        Form paramsForm = new Form();
        paramsForm.param("userFirstName", firstName);
        paramsForm.param("userSecondName", secondName);
        paramsForm.param("email", email);
        paramsForm.param("recentLoginDate", localDateString);
        paramsForm.param("recentLoginTime", localTimeString);

        String REPORTS_MODULE_UPDATE_PATH = REPORTS_MODULE_PATH + "statisticsUserUpdate";
        LOGGER.debug("Getting WebTarget of {}", REPORTS_MODULE_UPDATE_PATH);
        WebTarget target = client.target(REPORTS_MODULE_UPDATE_PATH);

        Integer status = null;
        try {
            Response response = target.request().post((Entity.form(paramsForm)));
            status = response.getStatus();
        }
        catch (Exception e) {
            LOGGER.debug("Sending user statistics to Reports Module failed");
        }
        LOGGER.debug("User statistics sending finished. Response status: ", status);
        return status;
    }

    private List<List<String>> getUserData(WebTarget target) {
        Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
        int status = response.getStatus();
        if (status != 200) {
            LOGGER.warn("Response status: {}", response.getStatus());
            return null;
        }
        LOGGER.debug("Getting downloaded data");
        String result = response.readEntity(String.class);
        response.close();

        LOGGER.debug("Parsing data from Json to Map");
        Gson gson = new Gson();
        List<List<String>> resultUserList = gson.fromJson(result, new TypeToken<ArrayList<List<String>>>(){}.getType());

        resultUserList.forEach((k) -> System.out.println(k.toString()));
        return resultUserList;
    }

}
