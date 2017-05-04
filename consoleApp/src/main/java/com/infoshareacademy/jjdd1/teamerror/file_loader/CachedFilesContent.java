package com.infoshareacademy.jjdd1.teamerror.file_loader;

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
    private List<String> currencyDataFile = new ArrayList<>();
    private List<String> petrolDataFile = new ArrayList<>();

    public CachedFilesContent() {
        this.petrolDataFile = FileReader.loadFile(FileReader.PATH_TO_FILES + FileReader.PETROL_FILE_NAME);
    }

    public List<String> getCurrencyInfoFile() {
        if (currencyInfoFile.isEmpty()) {
            setCurrencyInfoFile();
        }
        return currencyInfoFile;
    }

    public void setCurrencyInfoFile() {
        currencyInfoFile = FileReader.loadFile(FileReader.PATH_TO_FILES + FileReader.CURRENCY_FILE_WITH_GENERAL_DATA);
    }

    public List<String> getCurrencyDataFile(String currencySymbol) {
        if (currencyDataFile.isEmpty() || !currencyDataFile.get(0).equalsIgnoreCase(currencySymbol)) {
            setCurrencyDataFile(currencySymbol);
        }
        return currencyDataFile;
    }

    public void setCurrencyDataFile(String currencySymbol) {
        currencyDataFile = FileReader.loadFileForDefaultZip(FileReader.createPath(currencySymbol));
    }

    @Override
    public List<String> getPetrolDataFile() {
        return petrolDataFile;
    }
}