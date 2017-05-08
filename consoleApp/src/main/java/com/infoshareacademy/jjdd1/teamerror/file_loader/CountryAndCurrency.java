package com.infoshareacademy.jjdd1.teamerror.file_loader;

import java.util.*;

/**
 * Created by samulilaine on 19/04/2017.
 */
public class CountryAndCurrency {

    private FilesContent filesContent;
    private Map<String, String> countryAndCurrency;
    private String currency;
    private  CurrencyNames currencyNames;

    public CountryAndCurrency() {

    }

    public void setFilesContent(FilesContent filesContent) {
        this.filesContent = filesContent;
        currencyNames = new CurrencyNames(filesContent);
    }

    public Map<String, String> getCountryAndCurrency() {
        loadCountryAndCurrency();
        selectCountriesAndCurrency();
        return countryAndCurrency;
    }

    public void loadCountryAndCurrency() {
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
        List<String> countries= new ArrayList<String>(countryAndCurrency.keySet());
        List<String> currencies = new ArrayList<String>(countryAndCurrency.values());

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
