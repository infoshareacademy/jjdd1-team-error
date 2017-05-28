package com.infoshareacademy.jjdd1.teamerror.fileUpload;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.infoshareacademy.jjdd1.teamerror.file_loader.FileReader;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by sebastian_los on 16.05.17.
 */

@Startup
@Singleton
public class FileDownloader {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileDownloader.class);
    private static final String CURRENCY_ZIP_FILE_URL = "http://bossa.pl/pub/waluty/omega/omeganbp.zip";
    private static final String CURRENCY_INFO_FILE_URL = "http://bossa.pl/pub/waluty/omega/omeganbp.lst";
    private static final String PETROL_INFO_FILE_URL = "https://isacademy-jjdd1.herokuapp.com/petrol/countries.json";
    private static final String PETROL_URL = "https://isacademy-jjdd1.herokuapp.com/petrol/prices.csv";

    private void downloadFile(String sourceUrl, String fileName) {
        try {
            LOGGER.debug("Creating URL from string: {}", sourceUrl);
            URL url = new URL(sourceUrl);
            URLConnection urlConnection = url.openConnection();
            LOGGER.debug("Getting InputStream");
            InputStream inputStream = urlConnection.getInputStream();
            FileSaver.saveFileOnServer(fileName, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    @Schedule(hour = "*")
    public void downloadSourceFiles() {
        downloadFile(CURRENCY_INFO_FILE_URL, FileReader.CURRENCY_INFO_FILE);
        downloadFile(CURRENCY_ZIP_FILE_URL, FileReader.CURRENCY_ZIP_FILE);
        downloadFile(PETROL_INFO_FILE_URL, FileReader.PETROL_INFO_FILE_NAME);
        try {
            downloadFile(createPetrolURL(), FileReader.PETROL_FILE_NAME);
        } catch (URISyntaxException e) {
            LOGGER.error("Creating URI failed");
        }
    }

    private List<String> readAndParseJsonPetrolFileToList() {
        LOGGER.debug("");
        InputStream inputStream = FileReader.loadFileToInputStream(FileReader.PATH_TO_FILES +
                FileReader.PETROL_INFO_FILE_NAME);
        StringBuilder sb = new StringBuilder();
        new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(sb::append);
        Gson gson = new Gson();

        LOGGER.debug("Parsing json file to string List");
        return gson.fromJson(sb.toString(), new TypeToken<List<String>>(){}.getType());
    }

    private String createPetrolURL() throws URISyntaxException {
        List<String> countries = readAndParseJsonPetrolFileToList();
        StringBuilder sb = new StringBuilder();
        countries.forEach(country -> {
            sb.append(country);
            sb.append(",");
        });
        URIBuilder uriBuilder;
        uriBuilder = new URIBuilder(PETROL_URL);
        uriBuilder.addParameter("countries", sb.toString());
        uriBuilder.addParameter("from", "1990");
        uriBuilder.addParameter("to", "2017");

        return uriBuilder.toString();
    }
}
