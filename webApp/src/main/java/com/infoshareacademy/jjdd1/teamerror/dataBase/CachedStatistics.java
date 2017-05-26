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

    private List<List<String>> cashedStatisticsOfCountryAndCurrencyAndFuelType = new ArrayList<>();
    private List<UserStatistics> cashedStatisticsOfUserData = new ArrayList<>();

    // don't remove public
    public List<List<String>> getCashedStatisticsOfCountryAndCurrencyAndFuelType() {
        return cashedStatisticsOfCountryAndCurrencyAndFuelType;
    }

    public List<UserStatistics> getCashedStatisticsOfUserData() {
        return cashedStatisticsOfUserData;
    }

    void setCashedStatisticsOfCountryAndCurrencyAndFuelType(String country, String currency, String fuelType) {
        LOGGER.debug("Caching statistics: {} {} {}", country, currency, fuelType);
        this.cashedStatisticsOfCountryAndCurrencyAndFuelType.add(Arrays.asList(country, currency, fuelType));
    }

    void setCashedStatisticsOfUserData(UserStatistics userStatistics) {
        LOGGER.debug("Caching statistics: {}", userStatistics.toString());
        this.cashedStatisticsOfUserData.add(userStatistics);
    }

    void clearCashedStatisticsOfCountryAndCurrencyAndFuelType() {
        cashedStatisticsOfCountryAndCurrencyAndFuelType.clear();
    }

    void clearCashedStatisticsOfUserData() {
        cashedStatisticsOfUserData.clear();
    }
}
