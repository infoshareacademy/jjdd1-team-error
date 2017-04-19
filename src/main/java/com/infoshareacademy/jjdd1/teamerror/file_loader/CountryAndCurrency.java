package com.infoshareacademy.jjdd1.teamerror.file_loader;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by samulilaine on 19/04/2017.
 */
public class CountryAndCurrency {

    private final FilesContent filesContent;
    private Map<String, String> countriesAndCurrency = new LinkedHashMap<>();

    public CountryAndCurrency(FilesContent filesContent) {
        this.filesContent = filesContent;
    }

    public Map<String, String> getCountriesAndCurrency() {
        if (countriesAndCurrency.isEmpty()) {
            loadCountriesAndCurrency();
        }
        return countriesAndCurrency;
    }
    // single elements of given line as object

    public void loadCountriesAndCurrency() {
        List<String> lines = filesContent.getPetrolDataFile();
        String[] parts;
        // iterate over all lines
        for (int i = 1; i < lines.size(); i++) {
            parts = lines.get(i).split(";");
            countriesAndCurrency.put(parts[0], parts[3]);
        }
    }
}
