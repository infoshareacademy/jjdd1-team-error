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

    TripFullCost cost;
    CurrencyFileFilter currencyFileFilter;
    PetrolFileFilter petrolFileFilter;
    Trendy trendy;
    FilesContent filesContent;
    CountryAndCurrency countryAndCurrency;
    Map<String, String> countryAndCurrencyList;

    public InitialData() {
        super();
        LOGGER.info("Initial data start");

        trendy = new Trendy();

        filesContent = new OnDemandFilesContent();
        currencyFileFilter = new CurrencyFileFilter();
        petrolFileFilter = new PetrolFileFilter();
        currencyFileFilter.setFilesContent(filesContent);
        petrolFileFilter.setFilesContent(filesContent);

        trendy.setCurrencyFileFilter(currencyFileFilter);
        trendy.setPetrolFileFilter(petrolFileFilter);
        countryAndCurrency = new CountryAndCurrency();
        LOGGER.info("InitialServlet initialised");

        cost = new TripFullCost();
        cost.setTripFullCost(filesContent, petrolFileFilter, currencyFileFilter);
//        cost.setCountryAndCurrency(countryAndCurrency);
    }
}

