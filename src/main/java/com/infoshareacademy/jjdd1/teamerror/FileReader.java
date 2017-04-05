package com.infoshareacademy.jjdd1.teamerror;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Los on 02.04.2017.
 */
public class FileReader {
    // load file's content
    public static List<String> loadContent(String currencyName) {
        // file's content
        List<String> lines = null;
        // iterate over all currency names
        try {
            lines = Files.readAllLines(Paths.get("src/main/resources/files/", currencyName + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    // divide string into useful parts (words, prices)
    public static List<CurrencyHistoryDayValue> fileFilter(List<String> lines) {

        // single elements of given line
        List<CurrencyHistoryDayValue> currencyHistoryDayValues = new ArrayList<>();
        String[] parts;

        for (int i = 1; i < lines.size(); i++) {
            parts = lines.get(i).split(",");
            CurrencyHistoryDayValue value = new CurrencyHistoryDayValue();
            value.setName(parts[0]);
            value.setDate(DateParser.DateFromString(parts[1]));
            value.setOpen(Double.parseDouble(parts[2]));
            value.setHigh(Double.parseDouble(parts[3]));
            value.setLow(Double.parseDouble(parts[4]));
            value.setClose(Double.parseDouble(parts[5]));
            value.setVolume(Double.parseDouble(parts[6]));
            currencyHistoryDayValues.add(value);
        }
        removeExtractedFiles();
        return currencyHistoryDayValues;
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

    // delete extracted files
    public static void removeExtractedFiles () {
        for (Map.Entry currency : CurrencyNames.Currencies.entrySet()) {
            try {
                Files.delete(Paths.get("src/main/resources/files/" + currency.getKey() + ".txt"));
            } catch (IOException e) {}
        }
    }

    public static List<CurrencyHistoryDayValue> loadCurrencyFile (String symbol) {
        CurrencyNames.loadCurrencies();
        unzipFile("src/main/resources/files/omeganbp.zip", "src/main/resources/files/");
        return fileFilter(loadContent(symbol));
    }
}
