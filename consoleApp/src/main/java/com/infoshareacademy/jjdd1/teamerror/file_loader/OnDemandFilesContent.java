package com.infoshareacademy.jjdd1.teamerror.file_loader;

import java.util.List;

import static com.infoshareacademy.jjdd1.teamerror.file_loader.FileReader.*;

/**
 * Created by Krystian on 2017-04-18.
 */

public class OnDemandFilesContent implements FilesContent {
    @Override
    public List<String> getPetrolDataFile() {
        return FileReader.loadFile(PATH_TO_FILES + PETROL_FILE_NAME);
    }

    @Override
    public List<String> getCurrencyInfoFile() {
        return FileReader.loadFile(PATH_TO_FILES + CURRENCY_FILE_WITH_GENERAL_DATA);
    }

    @Override
    public List<String> getCurrencyDataFile(String currencySymbol) {
        return FileReader.loadFileForDefaultZip(FileReader.createPath(currencySymbol));
    }
}
