package com.infoshareacademy.jjdd1.teamerror;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by sebastianlos on 07.04.17.
 */
public class Trendy {
    public static List<Double> checkCurrencyTrendy(List<CurrencyHistoryDayValue> currencyList) {

        // set of years for which currency data are available
        Set<Integer> years = new LinkedHashSet<>();
        // list of averaged currency rates for each month at given year
        List<Double> monthAverageCurrencyRate = new ArrayList<>();
        // map of all averaged currency rates for each month at given years
        Map<Integer,List<Double>> allMonthsAverageCurrencyRates = new HashMap<>();
        // create 12 elements for each month
        for (int i = 0; i < 12; i++) {
            List<Double> monthValues = new ArrayList<>();
            allMonthsAverageCurrencyRates.put(i, monthValues);
        }
        // list of averaged difference rates for all months and given years
        List<Double> results = new ArrayList<>();
        // list of final results of Currency differences for all months and given years in percents
        List<Double> resultsInPercents = new ArrayList<>();

        // sum of all currency rates in given month
        double monthSumOfRates = 0.0;
        // number of days in month
        int dayCount = 0;

        // set all available years to list of years
        for (CurrencyHistoryDayValue obj : currencyList) {
            years.add(obj.getDate().getYear());
        }

        // iterate over years
        for (int year : years) {
            // iterate over months
            for (Integer x = 1; x <= 12; x++) {
                // average value of currency rate for given month
                double averagedMonthRate = 0;
                // iterate over all dates(objects)
                for (CurrencyHistoryDayValue obj : currencyList) {
                    // check whether given year and month fit to the object date
                    if (obj.getDate().getYear() == year && obj.getDate().getMonthValue() == x) {
                        // add another rate
                        monthSumOfRates += obj.getClose();
                        dayCount++;
                    }
                }
                if (dayCount != 0)
                    averagedMonthRate = monthSumOfRates / dayCount;
                monthSumOfRates = 0.0;
                if (averagedMonthRate != 0)
                    monthAverageCurrencyRate.add(averagedMonthRate);
                dayCount = 0;
            }
            double minValue = Collections.min(monthAverageCurrencyRate);
            // iterate over all month average currency rates of given year
            for (int i = 0; i < monthAverageCurrencyRate.size(); i++) {
                // copy present list to temporary list
                List<Double> tempList = new ArrayList<>(allMonthsAverageCurrencyRates.get(i));
                // add another values to list
                tempList.add(monthAverageCurrencyRate.get(i) / minValue);
                // update list
                allMonthsAverageCurrencyRates.put(i,tempList);
            }
            monthAverageCurrencyRate.clear();
        }
        // iterate over all months and merge all values of given month to one
        for (int i = 0; i < 12; i++) {
            // copy present list to temporary list
            List<Double> tempList = allMonthsAverageCurrencyRates.get(i);

            Double sum = 0.0;
            // sum all month's values
            for (Double d : tempList) {
                sum += d;
            }
            Double result = 0.0;
            // make average value
            if (tempList.size() != 0)
                result = sum / tempList.size();
            results.add(result);
        }
        Double minVal = Collections.min(results);
        // convert values to percentage form
        for (Double r : results) {
            resultsInPercents.add(round((r - minVal) * 100,2));
        }
        return resultsInPercents;
    }
    // round value to given number of decimal places
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
