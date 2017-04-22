package com.infoshareacademy.jjdd1.teamerror.file_loader;

import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.PetrolPrices;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastianlos on 06.04.17.
 */
public class PetrolFileFilter {

    private static final int NUMBER_OF_ELEMENTS_IN_LINE = 6;
    private FilesContent filesContent;

    private List<PetrolPrices> listOfPetrolDataObjects = new ArrayList<>();

    public PetrolFileFilter() {
    }

    public void setFilesContent(FilesContent filesContent) {
        this.filesContent = filesContent;
    }

    public List<PetrolPrices> getListOfPetrolDataObjects(String country) {
        if (listOfPetrolDataObjects.isEmpty() || !listOfPetrolDataObjects.get(0).getCountryName().equalsIgnoreCase(country)) {
            putPetrolFileContentToClass(country);
        }
        return listOfPetrolDataObjects;
    }

    // divide content of Currency File and put this information as objects
    public void putPetrolFileContentToClass(String country) {

        // single elements of given line as object
        List<String> lines = filesContent.getPetrolDataFile();
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
                    value.setCurrencyCode(parts[3].toUpperCase());
                    value.setGasolinePrice(Double.parseDouble(changeComaToPoint(parts[4])));
                    value.setDieselPrice(Double.parseDouble(changeComaToPoint(parts[5])));

                    listOfPetrolDataObjects.add(value);
                }
            }
        }
    }

    private static String changeComaToPoint(String price) {
        return price.replace(',','.');
    }

    public static void getListOfPetrolDataObjects() {
    }

}
