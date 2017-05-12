package com.infoshareacademy.jjdd1.teamerror.file_loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Sebastian Los on 02.04.2017.
 */
public class FileReader {

    static final String PROMOTED_COUNTRIES = "promotedCountries.txt";
    public static final String CURRENCY_INFO_FILE = "omeganbp.lst.txt";
    public static final String PATH_TO_FILES = System.getProperty("java.io.tmpdir") + "/files/";
    public static final String PETROL_FILE_NAME = "iSA-PetrolPrices.csv";
    public static final String CURRENCY_ZIP_FILE = "omeganbp.zip";

    private static final Logger LOGGER = LoggerFactory.getLogger(FileReader.class);

    // load file's content
    static List<String> loadFile(String path) {

        LOGGER.debug("Loading file, path: {}", path);
        InputStream inputStream = null;
        try {
            inputStream = Files.newInputStream(Paths.get(path));
        } catch (IOException e) {
            LOGGER.error("Loading file failed, path: {} exception: {}", path, e);
        }
        if (inputStream != null) {
            return new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    static List<String> loadFileForDefaultZip(String fileName) {
        InputStream inputStream = null;
        try {
            inputStream = Files.newInputStream(Paths.get(PATH_TO_FILES, CURRENCY_ZIP_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadFileForZip(inputStream, fileName);
    }

    static List<String> loadFileForZip(InputStream stream, String fileName) {
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
        return new ArrayList<>();
    }

    static List<String> loadStream(InputStream inputStream) {
        List<String> result = new ArrayList<>();
        try(BufferedReader br =  new BufferedReader(new InputStreamReader(inputStream))) {
            result = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Stream not converted to List");
        }
        return result;
    }

    // create String path
    static String createPath(String fileName) {
        return fileName + ".txt";
    }
}