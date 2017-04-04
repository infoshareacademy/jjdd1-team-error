package com.infoshareacademy.jjdd1.teamerror;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Sebastian Los on 02.04.2017.
 */
public class FileReader {
    // load file's content
    public static String loadContent(String currencyName) {
        // file's content
        String content = "";
        String buffer = "";
        // iterate over all currency names
        for(CurrencyNames name : CurrencyNames.values()) {
            // check weather parameter fits to any name
            if (currencyName.equalsIgnoreCase(name.toString())) {
                // read file's content line by line
                try {
                    BufferedReader br = Files.newBufferedReader(Paths.get("files", currencyName + ".txt"));
                    while ((buffer = br.readLine()) != null) {
                        content += buffer + "\n";
                    }
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
        return content;
    }

    // divide string into useful parts (words, prices)
    public static String[][] fileFilter(String content) {
        // split content into single lines
        String[] lines = content.split("\n");
        // single elements of given line
        String[] parts;
        // all single elements
        String[][] result = new String[lines.length][];

        for (int i = 0; i < lines.length; i++) {
            // split single lines into single elements
            parts = lines[i].split(",");
            result[i] = new String[parts.length];
            for (int j = 0; j < parts.length; j++)
                result[i][j] = parts[j];
        }
        return result;
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
}
