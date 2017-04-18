package com.infoshareacademy.jjdd1.teamerror;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by samulilaine on 18/04/2017.
 */
public class CountryAndCurrency {  static Map<String, String> loadAvailableCurrencyAndCountries() {

    List<String> lines = FileReader.loadContent(FileReader.PATH_TO_FILES + FileReader.PETROL_FILE_NAME);

    // single elements of given line as object

    HashMap<String, String> countriesAndCurrency = new LinkedHashMap<>();
    String[] parts;

    // iterate over all lines

    for (int i = 1; i < lines.size(); i++) {
        parts = lines.get(i).split(";");

        countriesAndCurrency.put(parts[0], parts[3]);
    }
    return countriesAndCurrency;
}
}
