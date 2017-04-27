package com.infoshareacademy.jjdd1.teamerror.file_loader;

//import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Created by Sebastian Los on 02.04.2017.
 */
public class FileReader {

    public static final String PROMOTED_COUNTRIES = "promotedCountries.txt";
    public static final String CURRENCY_FILE_WITH_GENERAL_DATA = "omeganbp.lst.txt";
    public static final String PATH_TO_FILES = "/files/"; 
    public static final String PETROL_FILE_NAME = "iSA-PetrolPrices.csv";
    public static final String ZIP_CURRENCY_FILE = "omeganbp.zip";
    public static final String UNZIP_FOLDER = PATH_TO_FILES + "unzip/";

    private static final Logger LOGGER = LoggerFactory.getLogger(FileReader.class);

    // load file's content
    public static List<String> loadFile(String path) {
        // file's content
        InputStream inputStream = FileReader.class.getResourceAsStream(path);

        return new BufferedReader(new InputStreamReader(inputStream)).lines()
                .collect(Collectors.toList());
    }

    public static List<String> loadFileForZip(String filename) {

        InputStream inputStream = FileReader.class.getResourceAsStream(PATH_TO_FILES + ZIP_CURRENCY_FILE);
        ZipInputStream zip = new ZipInputStream(inputStream);

        try {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (filename.equals(entry.getName())) {
                    return new BufferedReader(new InputStreamReader(zip)).lines()
                            .collect(Collectors.toList());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        throw new RuntimeException(new FileNotFoundException("No file in zip"));
    }

    // create String path
    public static String createPath(String fileName) {
        return fileName + ".txt";
    }

}