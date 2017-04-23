package com.infoshareacademy.jjdd1.teamerror.file_loader;

import java.util.*;

/**
 * Created by samulilaine on 19/04/2017.
 */
public class CountryAndCurrency {

    private FilesContent filesContent;
    private Map<String, String> countriesAndCurrency = new LinkedHashMap<>();
    private String currency;
    private  CurrencyNames currencyNames;

    public CountryAndCurrency() {

    }

    public void setFilesContent(FilesContent filesContent) {
        this.filesContent = filesContent;
        currencyNames = new CurrencyNames(filesContent);
    }

    public Map<String, String> getCountriesAndCurrency() {
        if (countriesAndCurrency.isEmpty()) {
            loadCountriesAndCurrency();
            selectCountriesAndCurrency();
        }
        return countriesAndCurrency;
    }

    public void loadCountriesAndCurrency() {
        List<String> lines = filesContent.getPetrolDataFile();
        String[] parts;
        // iterate over all lines
        for (int i = 1; i < lines.size(); i++) {
            parts = lines.get(i).split(";");
            countriesAndCurrency.put(parts[0].toUpperCase(), parts[3]);
        }
    }

    // select countries which are available for petrol and currencies
    public void selectCountriesAndCurrency(){
        List<String> countries= new ArrayList<String>(countriesAndCurrency.keySet());
        List<String> currencies = new ArrayList<String>(countriesAndCurrency.values());

        for(int i = 0; i < countries.size(); i++)
            if((currencyNames.getCurrencies().get(currencies.get(i)) == null))
                countriesAndCurrency.remove(countries.get(i), currencies.get(i));
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String country) {
        this.currency = getCountriesAndCurrency().get(country);
    }
}
