package com.infoshareacademy.jjdd1.teamerror;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sebastianlos on 06.04.17.
 */
public class PetrolFileFilter {

    public static final int NUMBER_OF_ELEMENTS_IN_LINE = 6;

    // divide content of Currency File and put this information as objects
    static List<PetrolPrices> putPetrolFileContentToClass(List<String> lines, String country) {

        // single elements of given line as object
        List<PetrolPrices> petrolPrices = new ArrayList<>();
        String[] parts;

        // iterate over all lines excepts the first one
        for (int i = 1; i < lines.size(); i++) {
            parts = lines.get(i).split(";");
            if (!lines.get(i).isEmpty() && parts.length == NUMBER_OF_ELEMENTS_IN_LINE) {
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

    private static String changeComaToPoint(String price) {
        return price.replace(',','.');
    }

}
