package com.infoshareacademy.jjdd1.teamerror.file_loader;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sebastianlos on 04.04.17.
 */
public class CurrencyNames {
    private final FilesContent filesContent;
    // symbols and full names of currencies
    private Map<String, String> currencies = new LinkedHashMap<>();

    public CurrencyNames(FilesContent filesContent) {
        this.filesContent = filesContent;
    }

    public Map<String, String> getCurrencies() {
        if (currencies.isEmpty()) {
            loadCurrencies();
        }
        return currencies;
    }

    // load all available currencies from given file
    public void loadCurrencies() {

        // read content of the file line by line
        List<String> lines = filesContent.getCurrencyInfoFile();
        String[] parts, file, currencyName;

        currencies.clear();
        for (int i = 3; i < lines.size() - 2; i++) {
            // split line by at least 2 spaces
            parts = lines.get(i).split("\\s{2,}");
            file = parts[parts.length - 2].split("\\.");
            currencyName = parts[parts.length - 1].split("\\(");
            currencies.put(file[0].toUpperCase(), currencyName[0]);
        };
    }
}