package com.infoshareacademy.jjdd1.teamerror.file_loader;

import com.infoshareacademy.jjdd1.teamerror.file_loader.CurrencyNames;
import com.infoshareacademy.jjdd1.teamerror.file_loader.FileReader;

import java.util.ArrayList;
import java.util.List;

/**
<<<<<<< HEAD
 * Created by sebas on 17.04.2017.
=======
 * Created by Krystian on 17.04.2017.
>>>>>>> JD1TE-28
 */
public class CachedFilesContent implements FilesContent {

    private List<String> currencyInfoFile = new ArrayList<>();
    private List<String> curreancyDataFile = new ArrayList<>();
    private List<String> petrolDataFile = new ArrayList<>();

    public CachedFilesContent() {
        this.petrolDataFile = FileReader.loadContent(FileReader.PATH_TO_FILES + FileReader.PETROL_FILE_NAME);
    }

    public List<String> getCurrencyInfoFile() {
        if (curreancyDataFile.isEmpty()) {
            setCurrencyInfoFile();
        }
        return currencyInfoFile;
    }

    public void setCurrencyInfoFile() {
        currencyInfoFile = FileReader.loadContent(FileReader.PATH_TO_FILES + FileReader.CURRENCY_FILE_WITH_GENERAL_DATA);
    }

    public List<String> getCurrencyDataFile(String currencySymbol) {
        if (curreancyDataFile.isEmpty() || !curreancyDataFile.get(0).equalsIgnoreCase(currencySymbol)) {
            setCurreancyDataFile(currencySymbol);
        }
        return curreancyDataFile;
    }

    public void setCurreancyDataFile(String currencySymbol) {
        curreancyDataFile = FileReader.loadContent(FileReader.createPath(currencySymbol));
    }

    @Override
    public List<String> getPetrolDataFile() {
        return petrolDataFile;
    }
}