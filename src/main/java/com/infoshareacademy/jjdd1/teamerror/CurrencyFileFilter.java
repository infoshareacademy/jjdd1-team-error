package com.infoshareacademy.jjdd1.teamerror;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastianlos on 06.04.17.
 */
public class CurrencyFileFilter {

    // divide content of Currency File and put this information as objects
    public static List<Object> putCurrencyFileContentToClass(List<String> lines) {

        // single elements of given line
        List<Object> currencyHistoryDayValues = new ArrayList<>();
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
        return currencyHistoryDayValues;
    }
}
