package com.infoshareacademy.jjdd1.teamerror;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.infoshareacademy.jjdd1.teamerror.CurrencyNames.currencies;

/**
 * Created by sebastianlos on 06.04.17.
 */
public class PetrolFileFilter {

    // divide content of Currency File and put this information as objects
    static List<PetrolPrices> putPetrolFileContentToClass(List<String> lines, String country) {

        // single elements of given line as object
        List<PetrolPrices> petrolPrices = new ArrayList<>();
        String[] parts;

        // iterate over all lines
        for (int i = 1; i < lines.size(); i++) {
            parts = lines.get(i).split(";");
            // read only data of given country
            if (parts[0].equalsIgnoreCase(country)) {
                PetrolPrices value = new PetrolPrices();
                value.setCountryName(parts[0]);
                value.setDate(DateParser.DateFromString(parts[1], parts[2]));
                value.setCurrencyCode(parts[3]);
                value.setGasolinePrice(Double.parseDouble(changeComaToPoint(parts[4])));
                value.setDieselPrice(Double.parseDouble(changeComaToPoint(parts[5])));

                petrolPrices.add(value);
            }
        }
        return petrolPrices;
    }

    static Set<String> loadAvailableCountries() {

        List<String> lines = FileReader.loadContent(FileReader.PATH_TO_FILES + FileReader.PETROL_FILE_NAME);
        // single elements of given line as object
        Set<String> countries = new LinkedHashSet<>();
        String[] parts;

        // iterate over all lines
        for (int i = 1; i < lines.size(); i++) {
            parts = lines.get(i).split(";");
            // read only countries
            countries.add(parts[0]);
        }
        return countries;
    }

    static Set<String> loadAvailableCurrency() {

        List<String> lines = FileReader.loadContent(FileReader.PATH_TO_FILES + FileReader.PATH_TO_FILES);
        // single elements of ginven line as object
        Set<String> countries = new LinkedHashSet<>();

        // iterate over all lines
        for (int i = 1; i < lines.size(); i++) {
            parts = lines.get(i).split(";");
            // read only currencies
            currencies.add(parts[3]);)
        }
    }


    private static String changeComaToPoint(String price) {
        return price.replace(',','.');
    }

}
