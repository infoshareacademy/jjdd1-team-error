package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.file_loader.*;
import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by Krystian on 2017-04-29.
 */
public class InitialData {

    private final Logger LOGGER = LoggerFactory.getLogger(InitialData.class);

    CurrencyFileFilter currencyFileFilter;
    PetrolFileFilter petrolFileFilter;
    Trendy trendy;
    FilesContent filesContent;
    CountryAndCurrency countryAndCurrency;
    Map<String, String> countryAndCurrencyList;
    PromotedCountries promotedCountries;
    String country;
    String fuelType;
    String date1;
    String date2;
    String fuelUsage;
    String fullDistance;
    String fullCostString;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public String getFuelUsage() {
        return fuelUsage;
    }

    public void setFuelUsage(String fuelUsage) {
        this.fuelUsage = fuelUsage;
    }

    public String getFullDistance() {
        return fullDistance;
    }

    public void setFullDistance(String fullDistance) {
        this.fullDistance = fullDistance;
    }

    public String getFullCostString() {
        return fullCostString;
    }

    public void setFullCostString(String fullCostString) {
        this.fullCostString = fullCostString;
    }

    public InitialData() {
        super();
        LOGGER.info("InitialServlet initialisation");

        trendy = new Trendy();

        filesContent = new CachedFilesContent();
        currencyFileFilter = new CurrencyFileFilter();
        petrolFileFilter = new PetrolFileFilter();
        currencyFileFilter.setFilesContent(filesContent);
        petrolFileFilter.setFilesContent(filesContent);

        trendy.setCurrencyFileFilter(currencyFileFilter);
        trendy.setPetrolFileFilter(petrolFileFilter);
        countryAndCurrency = new CountryAndCurrency();
        LOGGER.info("InitialServlet initialised");
        promotedCountries = new PromotedCountries();
        promotedCountries.setFilesContent(filesContent);


    }
}

