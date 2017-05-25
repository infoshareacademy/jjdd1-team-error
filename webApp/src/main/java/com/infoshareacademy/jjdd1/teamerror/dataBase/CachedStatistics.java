package com.infoshareacademy.jjdd1.teamerror.dataBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sebastianlos on 24.05.17.
 */
@Singleton
public class CachedStatistics {
    private final Logger LOGGER = LoggerFactory.getLogger(CachedStatistics.class);

    private List<List<String>> cashedStatistics = new ArrayList<>();

    // don't remove public
    public List<List<String>> getCashedStatistics() {
        return cashedStatistics;
    }

    void setCashedStatistics(String country, String currency, String fuelType) {
        LOGGER.debug("Caching statistics: {} {} {}", country, currency, fuelType);
        this.cashedStatistics.add(Arrays.asList(country, currency, fuelType));
    }

    void clearCashedStatistics() {
        cashedStatistics.clear();
    }
}
