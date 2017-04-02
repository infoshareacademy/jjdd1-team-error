package com.infoshareacademy.jjdd1.teamerror;

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
}
