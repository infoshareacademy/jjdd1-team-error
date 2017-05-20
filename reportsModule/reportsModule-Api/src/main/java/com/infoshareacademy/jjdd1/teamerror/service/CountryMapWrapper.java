package com.infoshareacademy.jjdd1.teamerror.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sebastianlos on 18.05.17.
 */
public class CountryMapWrapper {
    private Map<String, Integer> countryStatistics = new HashMap<>();

    public CountryMapWrapper() {
    }

    public Map<String, Integer> getCountryStatistics() {
        return countryStatistics;
    }

    public void setCountryStatistics(Map<String, Integer> countryStatistics) {
        this.countryStatistics = countryStatistics;
    }
}
