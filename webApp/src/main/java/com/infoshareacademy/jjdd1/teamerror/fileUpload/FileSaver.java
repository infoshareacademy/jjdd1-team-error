package com.infoshareacademy.jjdd1.teamerror.fileUpload;

import com.infoshareacademy.jjdd1.teamerror.file_loader.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by sebastian_los on 16.05.17.
 */
public class FileSaver {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSaver.class);

    static void saveFileOnServer(String fileName, InputStream file) throws IOException {
        Path pathToDirectory = Paths.get(FileReader.PATH_TO_FILES);
        if (!Files.exists(pathToDirectory)) {
            LOGGER.debug("Creating directory: {}", pathToDirectory);
            Files.createDirectory(pathToDirectory);
        }
        LOGGER.debug("Converting {} file to InputStream", fileName);
        Path pathToPetrolFile = Paths.get(FileReader.PATH_TO_FILES + fileName);
        LOGGER.debug("Saving {} file on server", fileName);

        Files.copy(file, pathToPetrolFile, StandardCopyOption.REPLACE_EXISTING);
        LOGGER.info("{} file saved in {}", fileName, FileReader.PATH_TO_FILES);
    }
}
