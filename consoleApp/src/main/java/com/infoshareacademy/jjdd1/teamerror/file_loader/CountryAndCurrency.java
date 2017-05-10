package com.infoshareacademy.jjdd1.teamerror.file_loader;

import com.infoshareacademy.jjdd1.teamerror.TripFullCost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by samulilaine on 19/04/2017.
 */
public class CountryAndCurrency {

    private FilesContent filesContent;
    private Map<String, String> countryAndCurrency = new LinkedHashMap<>();
    private String currency;
    private  CurrencyNames currencyNames;

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryAndCurrency.class);


    public CountryAndCurrency() {

    }

    public void setFilesContent(FilesContent filesContent) {
        this.filesContent = filesContent;
        currencyNames = new CurrencyNames(filesContent);
    }

    public Map<String, String> getCountryAndCurrency() {
        if (countryAndCurrency.isEmpty()) {
            LOGGER.debug("Loading country and currency");
            loadCountryAndCurrency();
            LOGGER.debug("Filtering countries to show only available ones");
            selectCountriesAndCurrency();
        }
        return countryAndCurrency;
    }

    private void loadCountryAndCurrency() {
        countryAndCurrency = new LinkedHashMap<>();
        List<String> lines = filesContent.getPetrolDataFile();
        String[] parts;
        CurrencyNames currencyNames = new CurrencyNames(filesContent);
        currencyNames.loadCurrencies();
        // iterate over all lines
        for (int i = 1; i < lines.size(); i++) {
            parts = lines.get(i).split(";");
            if (currencyNames.getCurrencies().containsKey(parts[3])) {
                countryAndCurrency.put(parts[0].toUpperCase(), parts[3]);
            }
        }
    }

    // select countries which are available for petrol and currencies
    public void selectCountriesAndCurrency(){
        List<String> countries= new ArrayList<>(countryAndCurrency.keySet());
        List<String> currencies = new ArrayList<>(countryAndCurrency.values());

        for(int i = 0; i < countries.size(); i++)
            if((currencyNames.getCurrencies().get(currencies.get(i)) == null))
                countryAndCurrency.remove(countries.get(i), currencies.get(i));
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String country) {
        this.currency = getCountryAndCurrency().get(country);
    }
}
