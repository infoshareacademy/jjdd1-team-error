package com.infoshareacademy.jjdd1.teamerror.file_loader;

import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.PetrolRates;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.RatesInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastianlos on 06.04.17.
 */
public class PetrolFileFilter {

    private static final int NUMBER_OF_ELEMENTS_IN_LINE = 6;
    private FilesContent filesContent;

    private List<RatesInfo> listOfPetrolDataObjects = new ArrayList<>();

    public PetrolFileFilter(FilesContent filesContent) {
        this.filesContent = filesContent;
    }

    public void setFilesContent(FilesContent filesContent) {
        this.filesContent = filesContent;
    }

    public List<RatesInfo> getListOfPetrolDataObjects(String country, String fuelType) {
        if (listOfPetrolDataObjects.isEmpty() || !((PetrolRates)listOfPetrolDataObjects.get(0)).getCountryName().equalsIgnoreCase(country)) {
            putPetrolFileContentToClass(country, fuelType);
        }
        return listOfPetrolDataObjects;
    }

    // divide content of Currency File and put this information as objects
    private void putPetrolFileContentToClass(String country, String fuelType) {

        listOfPetrolDataObjects.clear();
        List<String> lines = filesContent.getPetrolDataFile();

        // iterate over all lines excepts the first one
        lines.forEach(line -> {
            String[] parts = line.split(";");
            if (!line.isEmpty() && parts.length == NUMBER_OF_ELEMENTS_IN_LINE) {

                if (parts[0].equalsIgnoreCase(country)) {
                    PetrolRates value = new PetrolRates();
                    value.setCountryName(parts[0]);
                    value.setDate(DateParser.DateFromString(parts[1], parts[2]));
                    value.setCurrencyCode(parts[3].toUpperCase());
                    if (fuelType.equalsIgnoreCase("gasoline")) {
                        value.setRate(Double.parseDouble(changeComaToPoint(parts[4])));
                    }
                    else {
                        value.setRate(Double.parseDouble(changeComaToPoint(parts[5])));
                    }

                    listOfPetrolDataObjects.add(value);
                }
            }
        } );
    }

    private static String changeComaToPoint(String price) {
        return price.replace(',','.');
    }
}
