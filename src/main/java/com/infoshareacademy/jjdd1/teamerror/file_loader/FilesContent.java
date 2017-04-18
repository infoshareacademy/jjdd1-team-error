package com.infoshareacademy.jjdd1.teamerror.file_loader;

import com.infoshareacademy.jjdd1.teamerror.file_loader.CurrencyNames;
import com.infoshareacademy.jjdd1.teamerror.file_loader.FileReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebas on 17.04.2017.
 */
public class FilesContent {

    private static List<String> currencyInfoFile = new ArrayList<>();
    private static List<String> curreancyDataFile = new ArrayList<>();
    private static List<String> petrolDataFile = new ArrayList<>();

    public static List<String> getCurrencyInfoFile() {
        if (curreancyDataFile.isEmpty()) {
            setCurrencyInfoFile();
        }
        return currencyInfoFile;
    }

    public static void setCurrencyInfoFile() {
        currencyInfoFile = FileReader.loadContent(FileReader.PATH_TO_FILES + CurrencyNames.CURRENCY_FILE_WITH_GENERAL_DATA);
    }

    public static List<String> getCurrencyDataFile(String currencySymbol) {
        if (curreancyDataFile.isEmpty() || !curreancyDataFile.get(0).equalsIgnoreCase(currencySymbol)) {
            setCurreancyDataFile(currencySymbol);
        }
        return curreancyDataFile;
    }

    public static void setCurreancyDataFile(String currencySymbol) {
        curreancyDataFile = FileReader.loadContent(FileReader.createPath(currencySymbol));
    }

    public static List<String> getPetrolDataFile() {
        if (petrolDataFile.isEmpty()) {
            setPetrolDataFile();
        }
        return petrolDataFile;
    }

    public static void setPetrolDataFile() {
        petrolDataFile = FileReader.loadContent(FileReader.PATH_TO_FILES + FileReader.PETROL_FILE_NAME);
    }
}
