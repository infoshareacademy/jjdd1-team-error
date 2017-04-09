package com.infoshareacademy.jjdd1.teamerror;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sebastianlos on 04.04.17.
 */
public class CurrencyNames {
    // symbols and full names of currencies
    public static Map<String, String> currencies = new LinkedHashMap<>();

    public static void loadCurrencies() {

        List<String> lines = FileReader.loadContent(FileReader.PATH_TO_FILES + "omeganbp.lst.txt");

        String[] parts, file;

        for (int i = 3; i < lines.size() - 2; i++) {

            parts = lines.get(i).split("\\s{2,}");
            file = parts[parts.length - 2].split("\\.");

            currencies.put(file[0], parts[parts.length - 1]);
        }
    }
}