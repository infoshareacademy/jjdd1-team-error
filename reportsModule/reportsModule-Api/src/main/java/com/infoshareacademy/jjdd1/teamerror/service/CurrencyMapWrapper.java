package com.infoshareacademy.jjdd1.teamerror.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sebastianlos on 18.05.17.
 */
public class CurrencyMapWrapper {
    Map<String, Integer> currencyStatistics = new HashMap<>();

    public CurrencyMapWrapper() {
    }

    public Map<String, Integer> getCurrencyStatistics() {
        return currencyStatistics;
    }

    public void setCurrencyStatistics(Map<String, Integer> currencyStatistics) {
        this.currencyStatistics = currencyStatistics;
    }
}
