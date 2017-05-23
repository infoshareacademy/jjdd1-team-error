package com.infoshareacademy.jjdd1.teamerror;



import com.google.gson.reflect.TypeToken;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import com.google.gson.Gson;

/**
 * Created by sebastian_los on 19.05.17.
 */
public class test {

    public static void main(String[] args) {

//        updateStatisticsTest();
        getCurrencyStatisticsTest();
        getCountryStatisticsTest();
        getPetrolStatisticsTest();
    }

    private static void updateStatisticsTest() {
        Client client = new ResteasyClientBuilder().build();

        Form paramsForm = new Form();
        paramsForm.param("country", "USA");
        paramsForm.param("currency", "USD");
        paramsForm.param("fuelType", "Gasoline");

        WebTarget target = client.target("http://localhost:8080/reportsModule-1.0-SNAPSHOT/statisticsUpdate");

        Response response = target.request().post((Entity.form(paramsForm)));
        response.close();
    }

    private static void getCurrencyStatisticsTest() {
        Client client = new ResteasyClientBuilder().build();
        WebTarget target = client.target("http://localhost:8080/reportsModule-1.0-SNAPSHOT/currencyStatistics");
        getData(target);
    }



    private static void getCountryStatisticsTest() {
        Client client = new ResteasyClientBuilder().build();
        WebTarget target = client.target("http://localhost:8080/reportsModule-1.0-SNAPSHOT/countryStatistics");
        getData(target);
    }

    private static void getPetrolStatisticsTest() {
        Client client = new ResteasyClientBuilder().build();
        WebTarget target = client.target("http://localhost:8080/reportsModule-1.0-SNAPSHOT/petrolStatistics");
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
}
