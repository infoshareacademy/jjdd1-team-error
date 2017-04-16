package com.infoshareacademy.jjdd1.teamerror;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Los on 02.04.2017.
 */
public class FileReader {

    public static final String PATH_TO_FILES = "src/main/resources/files/";
    public static final String PETROL_FILE_NAME = "iSA-PetrolPrices.csv";
    public static final String ZIP_CURRENCY_FILE = "omeganbp.zip";

    // load file's content
    public static List<String> loadContent(String path) {
        // file's content
        List<String> lines = new ArrayList<>();
        // iterate over all currency names
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    // unzip the file from and to given location
    public static void unzipFile (String source, String destination) {
        try {
            ZipFile file = new ZipFile(source);
            // extract file
            file.extractAll(destination);
        } catch (ZipException e) {
            System.out.println(e);
        }
    }

    // delete extracted files
    public static void removeExtractedFiles () {
        for (Map.Entry currency : CurrencyNames.currencies.entrySet()) {
            try {
                Files.delete(Paths.get(PATH_TO_FILES + currency.getKey() + ".txt"));
            } catch (IOException e) {}
        }
    }


    // create path
    public static String createPath(String fileName) {
        return PATH_TO_FILES + fileName + ".txt";
    }

    // do all
    public static List<CurrencyHistoryDayValue> loadCurrencyFile (String symbol) {
        CurrencyNames.loadCurrencies();
        unzipFile(PATH_TO_FILES + ZIP_CURRENCY_FILE, PATH_TO_FILES);
        String path = createPath(symbol);
        List<CurrencyHistoryDayValue> result = CurrencyFileFilter.putCurrencyFileContentToClass(loadContent(path));
        removeExtractedFiles();
        return result;
    }

    // do all
    public static List<PetrolPrices> loadPetrolFiles (String country) {
        return PetrolFileFilter.putPetrolFileContentToClass(loadContent(PATH_TO_FILES + PETROL_FILE_NAME), country);
    }
}
