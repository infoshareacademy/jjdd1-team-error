package com.infoshareacademy.jjdd1.teamerror.file_loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Sebastian Los on 02.04.2017.
 */
public class FileReader {

    public static final String PROMOTED_COUNTRIES = "promotedCountries.txt";
    public static final String CURRENCY_FILE_WITH_GENERAL_DATA = "omeganbp.lst.txt";
    public static final String PATH_TO_FILES = "files/";
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

    public static List<String> loadFileForDefaultZip(String fileName) {
        InputStream inputStream = FileReader.class.getResourceAsStream(PATH_TO_FILES + ZIP_CURRENCY_FILE);
        return loadFileForZip(inputStream, fileName);
    }

    public static List<String> loadFileForZip(InputStream stream, String fileName) {
        ZipInputStream zip = new ZipInputStream(stream);

        try {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (fileName.equals(entry.getName())) {
                    return loadStream(zip);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Loading file from zip file failed: {}", fileName);
        }
        LOGGER.error("Loading file from zip file failed. File: {} not found", fileName);
        return new ArrayList<String>();
    }

    public static List<String> loadStream(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream)).lines()
                .collect(Collectors.toList());
    }

    // create String path
    public static String createPath(String fileName) {
        return fileName + ".txt";
    }
}