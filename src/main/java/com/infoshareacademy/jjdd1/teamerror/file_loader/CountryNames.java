package com.infoshareacademy.jjdd1.teamerror.file_loader;

import com.infoshareacademy.jjdd1.teamerror.FilesContent;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sebas on 17.04.2017.
 */
public class CountryNames {

    private static List<String> countryNames = new ArrayList<>();

    public static List<String> getCountryNames() {
        if (countryNames.isEmpty()) {
            loadAvailableCountries();
        }
        return countryNames;
    }

    public static void loadAvailableCountries() {

        List<String> lines = FilesContent.getPetrolDataFile();

        // single elements of given line as object
        Set<String> countries = new LinkedHashSet<>();
        String[] parts;

        // iterate over all lines
        for (int i = 1; i < lines.size(); i++) {
            parts = lines.get(i).split(";");
            // read only countries
            countryNames.add(parts[0]);
        }
    }
}
