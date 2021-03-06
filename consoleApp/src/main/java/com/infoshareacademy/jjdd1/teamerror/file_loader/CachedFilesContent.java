package com.infoshareacademy.jjdd1.teamerror.file_loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.infoshareacademy.jjdd1.teamerror.file_loader.FileReader.*;


public class CachedFilesContent implements FilesContent {

    private static final Logger LOGGER = LoggerFactory.getLogger(CachedFilesContent.class);

    private List<String> currencyInfoFile = new ArrayList<>();
    private List<String> currencyDataFile = new ArrayList<>();
    private List<String> petrolDataFile = new ArrayList<>();

    public CachedFilesContent() {

    }

    @Override
    public List<String> getCurrencyInfoFile() {
        if (currencyInfoFile.isEmpty()) {
            setCurrencyInfoFile();
        }
        return currencyInfoFile;
    }

    private void setCurrencyInfoFile() {
        LOGGER.debug("Creating path and loading file: {}", CURRENCY_INFO_FILE);
        currencyInfoFile = loadFile(PATH_TO_FILES + CURRENCY_INFO_FILE);
    }

    @Override
    public List<String> getCurrencyDataFile(String currencySymbol) {
        if (currencyDataFile.isEmpty() || !currencyDataFile.get(0).equalsIgnoreCase(currencySymbol)) {
            setCurrencyDataFile(currencySymbol);
        }
        return currencyDataFile;
    }

    private void setCurrencyDataFile(String currencySymbol) {

        currencyDataFile = loadFileForDefaultZip(createPath(currencySymbol));
    }

    @Override
    public List<String> getPetrolDataFile() {
        if (petrolDataFile.isEmpty()) {
            setPetrolDataFile();
        }
        return petrolDataFile;
    }

    private void setPetrolDataFile() {
        LOGGER.debug("Creating path and loading file: {}", PETROL_FILE_NAME);
        this.petrolDataFile = loadFile(PATH_TO_FILES + PETROL_FILE_NAME);
    }
}