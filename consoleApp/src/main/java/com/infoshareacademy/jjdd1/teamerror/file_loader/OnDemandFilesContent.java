package com.infoshareacademy.jjdd1.teamerror.file_loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.infoshareacademy.jjdd1.teamerror.file_loader.FileReader.*;

/**
 * Created by Krystian on 2017-04-18.
 */

public class OnDemandFilesContent implements FilesContent {

    private static final Logger LOGGER = LoggerFactory.getLogger(OnDemandFilesContent.class);

    @Override
    public List<String> getPetrolDataFile() {
        LOGGER.debug("Creating path and loading file: {}", PETROL_FILE_NAME);
        return loadFile(createPathToResourcesFiles(PETROL_FILE_NAME));
    }

    @Override
    public List<String> getCurrencyInfoFile() {
        LOGGER.debug("Creating path and loading file: {}", CURRENCY_FILE_WITH_GENERAL_DATA);
        return loadFile(createPathToResourcesFiles(CURRENCY_FILE_WITH_GENERAL_DATA));
    }

    @Override
    public List<String> getCurrencyDataFile(String currencySymbol) {
        return loadFileForZip(createPath(currencySymbol));
    }
}
