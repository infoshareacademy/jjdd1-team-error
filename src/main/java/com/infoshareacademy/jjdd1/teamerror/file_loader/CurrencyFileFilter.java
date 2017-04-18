package com.infoshareacademy.jjdd1.teamerror.file_loader;

import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.CurrencyHistoryDayValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastianlos on 06.04.17.
 */
public class CurrencyFileFilter {

    public static final int NUMBER_OF_ELEMENTS_IN_LINE = 7;

    private static List<CurrencyHistoryDayValue> listOfCurrencyDataObjects = new ArrayList<>();

    public static List<CurrencyHistoryDayValue> getListOfCurrencyDataObjects(String currencySymbol) {
        if (listOfCurrencyDataObjects.isEmpty() || !listOfCurrencyDataObjects.get(0).getName().equalsIgnoreCase(currencySymbol)) {
            putCurrencyFileContentToClass(currencySymbol);
        }
        return listOfCurrencyDataObjects;
    }

    // divide content of Currency File and put this information as objects
    public static void putCurrencyFileContentToClass(String currencySymbol) {

        // single elements of given line
        List<String> lines = FilesContent.getCurrencyDataFile(currencySymbol);
        String[] parts;

        for (int i = 1; i < lines.size(); i++) {
            parts = lines.get(i).split(",");
            if (!lines.get(i).isEmpty() && parts.length == NUMBER_OF_ELEMENTS_IN_LINE) {
                CurrencyHistoryDayValue value = new CurrencyHistoryDayValue();
                value.setName(parts[0]);
                value.setDate(DateParser.DateFromString(parts[1]));
                value.setOpen(Double.parseDouble(parts[2]));
                value.setHigh(Double.parseDouble(parts[3]));
                value.setLow(Double.parseDouble(parts[4]));
                value.setClose(Double.parseDouble(parts[5]));
                value.setVolume(Double.parseDouble(parts[6]));
                listOfCurrencyDataObjects.add(value);
            }
        }
    }
}
