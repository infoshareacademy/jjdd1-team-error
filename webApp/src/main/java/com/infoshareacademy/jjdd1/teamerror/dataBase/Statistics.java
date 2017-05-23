package com.infoshareacademy.jjdd1.teamerror.dataBase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by sebastianlos on 22.05.17.
 */

public class Statistics {

    private static final Logger LOGGER = LoggerFactory.getLogger(Statistics.class);


    public static void updateStatistics(String country, String currency, String fuelType) {
        Client client = new ResteasyClientBuilder().build();

        Form paramsForm = new Form();
        paramsForm.param("country", country);
        paramsForm.param("currency", currency);

        String fuelTypeName = fuelType.equals("1") ? "diesel" : "gasoline";
        paramsForm.param("fuelType", fuelTypeName);

        WebTarget target = client.target("http://localhost:8080/reportsModule-1.0-SNAPSHOT/statisticsUpdate");

        Response response = target.request().post((Entity.form(paramsForm)));
        if (response.getStatus() != 200) {
            // cash data
        }
        response.close();
    }

    public static Map<String, Integer> getCountryStatistics(){
        WebTarget target = getWebTarget("country");
        return getData(target);
    }

    public static Map<String, Integer> getCurrencyStatistics(){
        WebTarget target = getWebTarget("currency");
        return getData(target);
    }

    public static Map<String, Integer> getPetrolStatistics(){
        WebTarget target = getWebTarget("petrol");
        return getData(target);
    }

    private static WebTarget getWebTarget(String kind) {
        Client client = new ResteasyClientBuilder().build();
        return client.target("http://localhost:8080/reportsModule-1.0-SNAPSHOT/" + kind + "Statistics");
    }

    private static Map<String, Integer> getData(WebTarget target) {
        Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() != 200) {
            return null;
        }
        String result = response.readEntity(String.class);
        response.close();

        Gson gson = new Gson();
        Map<String, Integer> resultMap = gson.fromJson(result, new TypeToken<Map<String, Integer>>(){}.getType());

        resultMap.forEach((k,v) -> System.out.println(k + " " + v));
        return resultMap;
    }

}
