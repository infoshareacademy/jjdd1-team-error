package com.infoshareacademy.jjdd1.teamerror;



import com.google.gson.reflect.TypeToken;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;

/**
 * Created by sebastian_los on 19.05.17.
 */
public class test {

    public static void main(String[] args) {

        updateStatisticsTest();
        getCurrencyStatisticsTest();
        getCountryStatisticsTest();
        getPetrolStatisticsTest();
        updateUserStatistics();
        getUserStatisticsTest();
    }

    private static void updateStatisticsTest() {
        Client client = new ResteasyClientBuilder().build();

        Form paramsForm = new Form();
        paramsForm.param("country", "Luxemburg");
        paramsForm.param("currency", "Eur");
        paramsForm.param("fuelType", "Diesel");

        WebTarget target = client.target("http://localhost:8742/reportsModule-1.0-SNAPSHOT/statisticsUpdate");

        Response response = target.request().post((Entity.form(paramsForm)));
        response.close();
    }

    private static void getCurrencyStatisticsTest() {
        Client client = new ResteasyClientBuilder().build();
        WebTarget target = client.target("http://localhost:8742/reportsModule-1.0-SNAPSHOT/currencyStatistics");
        getData(target);
    }

    private static void getCountryStatisticsTest() {
        Client client = new ResteasyClientBuilder().build();
        WebTarget target = client.target("http://localhost:8742/reportsModule-1.0-SNAPSHOT/countryStatistics");
        getData(target);
    }

    private static void getPetrolStatisticsTest() {
        Client client = new ResteasyClientBuilder().build();
        WebTarget target = client.target("http://localhost:8742/reportsModule-1.0-SNAPSHOT/petrolStatistics");
        getData(target);
    }

    private static void getData(WebTarget target) {
        Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
        String result = response.readEntity(String.class);
        response.close();
        Gson gson = new Gson();

        Map<String, Integer> resultMap = gson.fromJson(result, new TypeToken<Map<String, Integer>>(){}.getType());
        resultMap.forEach((k,v) -> System.out.println(k + " " + v));
    }

    private static void updateUserStatistics() {

        Client client = new ResteasyClientBuilder().build();

        Form paramsForm = new Form();
        paramsForm.param("userFirstName", "Jan");
        paramsForm.param("userSecondName", "Kowalski");
        paramsForm.param("email", "jan.kowalski@gmail.com");
        paramsForm.param("recentLoginDate", "2016-05-05");
        paramsForm.param("recentLoginTime", "12:30:08");

        WebTarget target = client.target("http://localhost:8742/reportsModule-1.0-SNAPSHOT/statisticsUserUpdate");

        Response response = target.request().post((Entity.form(paramsForm)));
        response.close();
    }

    private static void getUserData(WebTarget target) {
        Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
        String result = response.readEntity(String.class);
        response.close();

        Gson gson = new Gson();
        List<List<String>> resultUserList = gson.fromJson(result, new TypeToken<List<List<String>>>(){}.getType());

        resultUserList.forEach((k) -> System.out.println(k));
        System.out.println(resultUserList);
    }

    private static void getUserStatisticsTest() {
        Client client = new ResteasyClientBuilder().build();
        WebTarget target = client.target("http://localhost:8742/reportsModule-1.0-SNAPSHOT/userStatistics");
        getUserData(target);
    }
}
