package com.infoshareacademy.jjdd1.teamerror.file_loader;

import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Los on 02.04.2017.
 */
public class FileReader {

    public static final String CURRENCY_FILE_WITH_GENERAL_DATA = "omeganbp.lst.txt";
    public static final String PATH_TO_FILES = "src/main/resources/files/";
    public static final String PETROL_FILE_NAME = "iSA-PetrolPrices.csv";
    public static final String ZIP_CURRENCY_FILE = "omeganbp.zip";
    public static final String UNZIP_FOLDER = PATH_TO_FILES + "unzip";

    private static final Logger LOGGER = LoggerFactory.getLogger(Trendy.class);

    // load file's content
    public static List<String> loadContent(String path) {
        // file's content
        List<String> lines = new ArrayList<>();
        // iterate over all currency names
        try {
            LOGGER.debug("Loading file content, path: {}", path);
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            LOGGER.error("Loading file failed, path: {}", path);
        }
        LOGGER.info("File successfully loaded, path: {}", path);
        return lines;
    }

    // unzip the file from and to given location
    public static void unzipFile() {
        String source = PATH_TO_FILES + ZIP_CURRENCY_FILE;
        try {
            LOGGER.debug("Extracting files, source: {}, destination: {}", source, UNZIP_FOLDER);
            ZipFile file = new ZipFile(source);
            // extract file
            file.extractAll(UNZIP_FOLDER);
        } catch (ZipException e) {
            LOGGER.error("Extracting files failed, source: {}, destination: {}", source, UNZIP_FOLDER);
        }
        LOGGER.info("Files successfully extracted, source: {}, destination: {}", source, UNZIP_FOLDER);
    }

    // delete extracted files
    public static void removeExtractedFiles() {
        try {
            Files.delete(Paths.get(UNZIP_FOLDER));
        } catch (IOException e) {
            LOGGER.warn("Removing extracted files failed, path: {}", UNZIP_FOLDER);
        }
    }

    // create path
    public static String createPath(String fileName) {
        return PATH_TO_FILES + fileName + ".txt";
    }

    // do all
//    public static List<CurrencyHistoryDayValue> loadCurrencyFile (String symbol) {
//        if (CurrencyNames.getCurrencies().isEmpty()) {
//            CurrencyNames.loadCurrencies();
//        }
//        unzipFile(PATH_TO_FILES + ZIP_CURRENCY_FILE, PATH_TO_FILES);
//        List<CurrencyHistoryDayValue> result = CurrencyFileFilter.getListOfCurrencyDataObjects(symbol);
//        removeExtractedFiles();
//        return result;
//    }

//    // do all
//    public static List<PetrolPrices> loadPetrolFiles (String country) {
//        return PetrolFileFilter.getListOfPetrolDataObjects(country);
//    }
}
