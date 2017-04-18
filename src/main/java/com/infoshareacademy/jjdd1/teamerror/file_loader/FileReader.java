package com.infoshareacademy.jjdd1.teamerror.file_loader;

import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.CurrencyHistoryDayValue;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.PetrolPrices;
import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Sebastian Los on 02.04.2017.
 */
public class FileReader {

    public static final String PATH_TO_FILES = "src/main/resources/files/";
    public static final String PETROL_FILE_NAME = "iSA-PetrolPrices.csv";
    public static final String ZIP_CURRENCY_FILE = "omeganbp.zip";

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
    public static void unzipFile (String source, String destination) {
        try {
            LOGGER.debug("Extracting files, source: {}, destination: {}", source, destination);
            ZipFile file = new ZipFile(source);
            // extract file
            file.extractAll(destination);
        } catch (ZipException e) {
            LOGGER.error("Extracting files failed, source: {}, destination: {}", source, destination);
        }
        LOGGER.info("Files successfully extracted, source: {}, destination: {}", source, destination);
    }

    // delete extracted files
    public static void removeExtractedFiles () {
        for (Map.Entry currency : CurrencyNames.getCurrencies().entrySet()) {
            String path = createPath((String)currency.getKey());
            try {
                Files.delete(Paths.get(path));
            } catch (IOException e) {
                LOGGER.warn("Removing extracted files failed, path: {}", path);
            }
        }
    }

    // create path
    public static String createPath(String fileName) {
        return PATH_TO_FILES + fileName + ".txt";
    }

    // do all
    public static List<CurrencyHistoryDayValue> loadCurrencyFile (String symbol) {
        if (CurrencyNames.getCurrencies().isEmpty()) {
            CurrencyNames.loadCurrencies();
        }
        unzipFile(PATH_TO_FILES + ZIP_CURRENCY_FILE, PATH_TO_FILES);
        List<CurrencyHistoryDayValue> result = CurrencyFileFilter.getListOfCurrencyDataObjects(symbol);
        removeExtractedFiles();
        return result;
    }

    // do all
    public static List<PetrolPrices> loadPetrolFiles (String country) {
        return PetrolFileFilter.getListOfPetrolDataObjects(country);
    }

    /**
     * Created by samulilaine on 18/04/2017.
     */
    public static class CountryAndCurrency {  public static Map<String, String> loadAvailableCurrencyAndCountries() {

        List<String> lines = loadContent(PATH_TO_FILES + PETROL_FILE_NAME);

        // single elements of given line as object

        HashMap<String, String> countriesAndCurrency = new LinkedHashMap<>();
        String[] parts;

        // iterate over all lines

        for (int i = 1; i < lines.size(); i++) {
            parts = lines.get(i).split(";");

            countriesAndCurrency.put(parts[0], parts[3]);
        }
        return countriesAndCurrency;
    }
    }
}
