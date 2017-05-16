package com.infoshareacademy.jjdd1.teamerror.file_loader;

import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.CurrencyRates;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.RatesInfo;
import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by SebastianLos on 06.04.17.
 */
public class CurrencyFileFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Trendy.class);
    private static final int NUMBER_OF_ELEMENTS_IN_LINE = 7;

    private List<RatesInfo> listOfCurrencyDataObjects = new ArrayList<>();
    private FilesContent filesContent;

    public CurrencyFileFilter(FilesContent filesContent) {
        this.filesContent = filesContent;
    }

    public List<RatesInfo> getListOfCurrencyDataObjects(String currencySymbol) {
        if (listOfCurrencyDataObjects.isEmpty() || !listOfCurrencyDataObjects.get(0).getCurrencyCode().equalsIgnoreCase(currencySymbol)) {
            LOGGER.debug("Loading currency contentent into class");
            putCurrencyFileContentToClass(currencySymbol);
        }
        return listOfCurrencyDataObjects;
    }

    // divide content of Currency File and put this information as objects
    private void putCurrencyFileContentToClass(String currencySymbol) {

        listOfCurrencyDataObjects.clear();
        List<String> lines = filesContent.getCurrencyDataFile(currencySymbol);

        LOGGER.debug("Parsing currency content");
        // iterate over all lines excepts the first one
        lines.forEach(line -> {
            String[] parts = line.split(",");

            if (!line.isEmpty() && parts.length == NUMBER_OF_ELEMENTS_IN_LINE && !line.equals(lines.get(0))) {
                CurrencyRates value = new CurrencyRates();
                value.setCurrencyCode(parts[0].toUpperCase());
                value.setDate(DateParser.DateFromString(parts[1]));
                value.setRate(Double.parseDouble(parts[5]));
                listOfCurrencyDataObjects.add(value);
            }
        } );
        LOGGER.info("Currency content parsed");
    }
}
