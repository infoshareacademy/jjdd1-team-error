package com.infoshareacademy.jjdd1.teamerror.file_loader;

import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.CurrencyRates;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.RatesInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SebastianLos on 06.04.17.
 */
public class CurrencyFileFilter {

    public static final int NUMBER_OF_ELEMENTS_IN_LINE = 7;

    private List<RatesInfo> listOfCurrencyDataObjects = new ArrayList<>();
    private FilesContent filesContent;

    public CurrencyFileFilter() {
    }

    public void setFilesContent(FilesContent filesContent) {
        this.filesContent = filesContent;
    }

    public List<RatesInfo> getListOfCurrencyDataObjects(String currencySymbol) {
        if (listOfCurrencyDataObjects.isEmpty() || !listOfCurrencyDataObjects.get(0).getCurrencyCode().equalsIgnoreCase(currencySymbol)) {
            putCurrencyFileContentToClass(currencySymbol);
        }
        return listOfCurrencyDataObjects;
    }

    // divide content of Currency File and put this information as objects
    public void putCurrencyFileContentToClass(String currencySymbol) {

        listOfCurrencyDataObjects.clear();
        List<String> lines = filesContent.getCurrencyDataFile(currencySymbol);
        String[] parts;

        // iterate over all lines excepts the first one
        for (int i = 1; i < lines.size(); i++) {
            parts = lines.get(i).split(",");
            if (!lines.get(i).isEmpty() && parts.length == NUMBER_OF_ELEMENTS_IN_LINE) {
                CurrencyRates value = new CurrencyRates();
                value.setCurrencyCode(parts[0].toUpperCase());
                value.setDate(DateParser.DateFromString(parts[1]));
                value.setRate(Double.parseDouble(parts[5]));
                listOfCurrencyDataObjects.add(value);
            }
        }
    }
}
