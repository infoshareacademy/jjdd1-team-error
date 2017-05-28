package com.infoshareacademy.jjdd1.teamerror.dataBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sebastianlos on 24.05.17.
 */
@Singleton
public class CachedStatistics {
    private final Logger LOGGER = LoggerFactory.getLogger(CachedStatistics.class);

    private List<List<String>> cashedStatisticsOfCountryAndCurrencyAndFuelType = new ArrayList<>();
    private List<List<String>> cashedStatisticsOfUserData = new ArrayList<>();

    // don't remove public
    public List<List<String>> getCashedStatisticsOfCountryAndCurrencyAndFuelType() {
        return cashedStatisticsOfCountryAndCurrencyAndFuelType;
    }

    void setCashedStatisticsOfCountryAndCurrencyAndFuelType(String country, String currency, String fuelType) {
        LOGGER.debug("Caching statistics: {} {} {}", country, currency, fuelType);
        this.cashedStatisticsOfCountryAndCurrencyAndFuelType.add(Arrays.asList(country, currency, fuelType));
    }

    void clearCashedStatisticsOfCountryAndCurrencyAndFuelType() {
        cashedStatisticsOfCountryAndCurrencyAndFuelType.clear();
    }

    public List<List<String>> getCashedStatisticsOfUserData() {
        return cashedStatisticsOfUserData;
    }

    void setCashedStatisticsOfUserData(String firstName, String secondName, String email, String localDateString, String localTimeString) {
        LOGGER.debug("Caching statistics: {}, {}, {}, {}, {}", firstName, secondName, email, localDateString, localTimeString);
        this.cashedStatisticsOfUserData.add(Arrays.asList(firstName, secondName, email, localDateString, localTimeString));
    }

    void clearCashedStatisticsOfUserData() {
        cashedStatisticsOfUserData.clear();
    }
}
