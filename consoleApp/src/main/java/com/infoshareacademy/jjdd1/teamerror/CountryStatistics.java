package com.infoshareacademy.jjdd1.teamerror;


import java.util.Map;
import java.util.HashMap;

/**
 * Created by samulilaine on 27/04/2017.
 */
public class CountryStatistics {

    private Map<String,Long> statistics = new HashMap<>();

    public static void main(String args[]) {}

    public Map<String, Long> getStatistics() {
        return statistics;
    }

    public void selectedCountry(String countryCode) {

        Long countryCounter = statistics.getOrDefault(countryCode, 0l);
        statistics.put(countryCode, ++countryCounter);

    }

}












