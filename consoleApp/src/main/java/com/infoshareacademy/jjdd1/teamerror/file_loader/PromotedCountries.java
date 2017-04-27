package com.infoshareacademy.jjdd1.teamerror.file_loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by igafalkowska on 21.04.17.
 */
public class PromotedCountries {

    private static final Logger LOGGER = LoggerFactory.getLogger(PromotedCountries.class);

    private CountryAndCurrency countryAndCurrency;
    private FilesContent filesContent;
    List<String> orderedPromotedCountries = new ArrayList<>();

    private static List<String> promotedCountries = new ArrayList<>();

    public PromotedCountries() {

    }


    public void setFilesContent(FilesContent filesContent) {
        this.filesContent = filesContent;
        countryAndCurrency = new CountryAndCurrency();
        countryAndCurrency.setFilesContent(filesContent);
    }

    public static void loadPromotedCountries() {
        promotedCountries = FileReader.loadFile(FileReader.PATH_TO_FILES + FileReader.PROMOTED_COUNTRIES);
    }

    public List<String> getPromotedCountries(){

        loadPromotedCountries();

        return promotedCountries;
    }

    public List<String> getOrderedPromotedCountries() {
        setOrderedPromotedCountries();
        return orderedPromotedCountries;
    }

    public void setOrderedPromotedCountries() {
        LOGGER.debug("Preparing list of countries according to promoted countries");
        List<String> availableCountries = new ArrayList<>(countryAndCurrency.getCountriesAndCurrency().keySet());
        orderedPromotedCountries.clear();
        for (int i = 0; i < getPromotedCountries().size(); i++) {
            orderedPromotedCountries.add(getPromotedCountries().get(i).toUpperCase());
        }

        for (String country : getPromotedCountries()) {
            if ((availableCountries.contains(country.toUpperCase()))) {
                availableCountries.remove(country.toUpperCase());
            }
        }

        for (int i = 0; i < availableCountries.size(); i++) {
            orderedPromotedCountries.add(availableCountries.get(i));
        }
        LOGGER.debug("List of countries prepared");
    }

}
