package com.infoshareacademy.jjdd1.teamerror.fileUpload;

import com.infoshareacademy.jjdd1.teamerror.file_loader.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by sebastian_los on 16.05.17.
 */


@Singleton
public class FileDownloader {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileDownloader.class);
    private static final String CURRENCY_ZIP_FILE_URL = "http://bossa.pl/pub/waluty/omega/omeganbp.zip";
    private static final String CURRENCY_INFO_FILE_URL = "http://bossa.pl/pub/waluty/omega/omeganbp.lst";

    private void downloadFile(String sourceUrl, String fileName) {
        try {
            LOGGER.debug("Creating URL from string: {}", sourceUrl);
            URL url = new URL(sourceUrl);
            URLConnection urlConnection = url.openConnection();
            LOGGER.debug("Getting InputStream from source: ()", sourceUrl);
            InputStream inputStream = urlConnection.getInputStream();
            FileSaver.saveFileOnServer(fileName, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Schedule(hour = "*")
    public void downloadSourceFiles() {
        downloadFile(CURRENCY_INFO_FILE_URL, FileReader.CURRENCY_INFO_FILE);
        downloadFile(CURRENCY_ZIP_FILE_URL, FileReader.CURRENCY_ZIP_FILE);
    }
}
