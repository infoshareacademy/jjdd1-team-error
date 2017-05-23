package com.infoshareacademy.jjdd1.teamerror.dataBase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by sebastianlos on 22.05.17.
 */

public class Statistics {

    private static final Logger LOGGER = LoggerFactory.getLogger(Statistics.class);
    public static final String REPORTS_MODULE_UPDATE_PATH = "http://localhost:8091/reportsModule-1.0-SNAPSHOT/statisticsUpdate";
    public static final String REPORTS_MODULE_PATH = "http://localhost:8091/reportsModule-1.0-SNAPSHOT/";


    public static void updateStatistics(String country, String currency, String fuelType) {
        try {
        Client client = new ResteasyClientBuilder().build();

        Form paramsForm = new Form();
        paramsForm.param("country", country);
        paramsForm.param("currency", currency);

        String fuelTypeName = fuelType.equals("1") ? "diesel" : "gasoline";
        paramsForm.param("fuelType", fuelTypeName);

        LOGGER.debug("Getting WebTarget of {}", REPORTS_MODULE_UPDATE_PATH);
        WebTarget target = client.target(REPORTS_MODULE_UPDATE_PATH);

        target.request().post((Entity.form(paramsForm)));
        }
        catch (ProcessingException e) {
            LOGGER.debug("Statistics update to Reports Module failed");
        }
    }

    public static Map<String, Integer> getStatistics(String kind){
        try {
            WebTarget target = getWebTarget(kind);
            Map<String, Integer> unsortedStatistics = getData(target);
            return mapSorterByValue(unsortedStatistics);
        }
        catch (Exception e) {
            LOGGER.warn("Getting statistics for {} failed", kind);
        }
        return null;
    }

    private static WebTarget getWebTarget(String kind) {
        Client client = new ResteasyClientBuilder().build();
        String path = REPORTS_MODULE_PATH + kind + "Statistics";
        LOGGER.debug("Getting WebTarget of {}", path);
        return client.target(path);
    }

    private static Map<String, Integer> getData(WebTarget target) {
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
        Map<String, Integer> resultMap = gson.fromJson(result, new TypeToken<Map<String, Integer>>(){}.getType());

        resultMap.forEach((k,v) -> System.out.println(k + " " + v));
        return resultMap;
    }

    private static Map<String, Integer> mapSorterByValue(Map<String, Integer> unsortedMap) {
        LOGGER.debug("Sorting map");
        Map<String, Integer> result = new LinkedHashMap<>();
        unsortedMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        return result;
    }

}
