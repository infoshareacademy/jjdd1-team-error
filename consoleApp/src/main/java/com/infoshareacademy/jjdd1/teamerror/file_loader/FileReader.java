package com.infoshareacademy.jjdd1.teamerror.file_loader;

//import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;
import  com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Los on 02.04.2017.
 */
public class FileReader {

    public static final String CURRENCY_FILE_WITH_GENERAL_DATA = "omeganbp.lst.txt";
    public static final String PATH_TO_FILES = System.getProperty("java.io.tmpdir")+"files\\";
    public static final String PETROL_FILE_NAME = "iSA-PetrolPrices.csv";
    public static final String ZIP_CURRENCY_FILE = "omeganbp.zip";
    public static final String UNZIP_FOLDER = PATH_TO_FILES + "unzip\\";

    private static final Logger LOGGER = LoggerFactory.getLogger(Trendy.class);

    // load file's content
    public static List<String> loadContent(String path) {
        // file's content
        List<String> lines = new ArrayList<>();

        try {
            LOGGER.debug("Loading file content, path: {}", path);
            lines = Files.readAllLines(createFullPath(path));
            LOGGER.info("File successfully loaded, path: {}", path);
        } catch (IOException e) {
            LOGGER.error("Loading file failed, path: {}", path);
        }
        return lines;
    }

    public static Path createFullPath(String path){
        Path rootPath = Paths.get(path);
        return rootPath;
    }

    // unzip the file from and to given location
    public static void unzipFile() {
        String source = PATH_TO_FILES + ZIP_CURRENCY_FILE;
        try {
            LOGGER.debug("Extracting files, source: {}, destination: {}", source, UNZIP_FOLDER);
            ZipFile file = new ZipFile(createFullPath(source).toString());
            // extract file
            file.extractAll(createFullPath(UNZIP_FOLDER).toString());
            LOGGER.info("Files successfully extracted, source: {}, destination: {}", source, UNZIP_FOLDER);
        } catch (ZipException e) {
            LOGGER.error("Extracting files failed, source: {}, destination: {}", source, UNZIP_FOLDER);
        }
    }

    // delete extracted files
    public static void removeExtractedFiles() {
        try {
            String path = createFullPath(UNZIP_FOLDER).toString();
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            LOGGER.warn("Removing extracted files failed, path: {}", UNZIP_FOLDER);
        }
    }

    // create path
    public static String createPath(String fileName) {
        return UNZIP_FOLDER + fileName + ".txt";
    }
}