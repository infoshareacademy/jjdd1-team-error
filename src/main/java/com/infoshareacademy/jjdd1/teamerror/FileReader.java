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

    // load file's content
    public static List<String> loadContent(String path) {
        // file's content
        List<String> lines = null;
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
        for (Map.Entry currency : CurrencyNames.Currencies.entrySet()) {
            try {
                Files.delete(Paths.get(PATH_TO_FILES + currency.getKey() + ".txt"));
            } catch (IOException e) {}
        }
    }


    // create path
    public static String createPath(String fileName, String extension) {
        return PATH_TO_FILES + fileName + extension;
    }

    // do all
    public static List<Object> loadCurrencyFile (String symbol) {
        CurrencyNames.loadCurrencies();
        unzipFile(PATH_TO_FILES + "omeganbp.zip", PATH_TO_FILES);
        String path = createPath(symbol, ".txt");
        List<Object> result = CurrencyFileFilter.putCurrencyFileContentToClass(loadContent(path));
        removeExtractedFiles();
        return result;
    }

    // do all
    public static List<Object> loadPetrolFiles (String fileName) {
        String path = createPath(fileName, ".csv");
        return PetrolFileFilter.putPetrolFileContentToClass(loadContent(path));
    }


}
