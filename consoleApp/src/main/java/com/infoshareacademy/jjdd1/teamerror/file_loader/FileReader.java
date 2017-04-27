package com.infoshareacademy.jjdd1.teamerror.file_loader;

//import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
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

        ZipEntry entry;
        try {
            while ((entry = zip.getNextEntry()) != null) {
                if (filename.equals(entry.getName())) {
                    return new BufferedReader(new InputStreamReader(zip)).lines()
                            .collect(Collectors.toList());
                }
            }
        } catch (IOException e) {
            LOGGER.error("Loading file from zip file failed: {}", filename);
        }
        LOGGER.error("Loading file from zip file failed. File: {} not found", filename);
        return new ArrayList<String>();
    }

    public static Path convertStringToPathClass(String path){
        Path rootPath = Paths.get(path);
        return rootPath;
    }

    public static String createPathToResourcesFiles(String fileName) {
        return FileReader.class.getResource("files/" + fileName).getPath();
    }




    // delete extracted files
    public static void removeExtractedFiles() {
        try {
            String path = convertStringToPathClass(UNZIP_FOLDER).toString();
            LOGGER.debug("Start removing extracted files, path: {}", path);
            Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    LOGGER.debug("Removing file, path: {}", file);
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    LOGGER.debug("Removing directory, path: {}", dir);
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });

        } catch (IOException e) {
            LOGGER.warn("Removing extracted files failed, path: {}", UNZIP_FOLDER);
        }
    }

    // create String path
    public static String createPath(String fileName) {
        return fileName + ".txt";
    }
}