package com.infoshareacademy.jjdd1.teamerror.file_loader;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sebastianlos on 04.04.17.
 */
public class CurrencyNames {
    public static final String CURRENCY_FILE_WITH_GENERAL_DATA = "omeganbp.lst.txt";
    // symbols and full names of currencies
    private static Map<String, String> currencies = new LinkedHashMap<>();

    public static Map<String, String> getCurrencies() {
        if (currencies.isEmpty()) {
            loadCurrencies();
        }
        return currencies;
    }

    // load all available currencies from given file
    public static void loadCurrencies() {

        // read content of the file line by line
        List<String> lines = FilesContent.getCurrencyInfoFile();
        String[] parts, file;

        currencies.clear();
        for (int i = 3; i < lines.size() - 2; i++) {
            // split line by at least 2 spaces
            parts = lines.get(i).split("\\s{2,}");
            file = parts[parts.length - 2].split("\\.");
            currencies.put(file[0], parts[parts.length - 1]);
        };
    }
}