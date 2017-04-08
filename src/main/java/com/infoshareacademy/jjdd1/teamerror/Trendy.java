package com.infoshareacademy.jjdd1.teamerror;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by sebastianlos on 07.04.17.
 */
public class Trendy {
    public static void checkTrendy(List<CurrencyHistoryDayValue> currencyList, List<PetrolPrices> petrolList) {

        Set<Integer> years = new LinkedHashSet<>();
        List<Double> monthAveragePrices = new ArrayList<>();
        Double[] results = new Double[12];

        Map<Integer,List<Double>> allMonthsAverageValues = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            List<Double> monthValues = new ArrayList<>();
            allMonthsAverageValues.put(i, monthValues);
        }

        double value = 0.0;
        int dayCount = 0;

        for (CurrencyHistoryDayValue obj : currencyList) {
            years.add(obj.getDate().getYear());
        }

        for (int year : years) {
            for (Integer x = 1; x <= 12; x++) {
                double averageMonthPrice = 0;
                for (CurrencyHistoryDayValue obj : currencyList) {
                    if (obj.getDate().getYear() == year && obj.getDate().getMonthValue() == x) {
                        value += obj.getClose();
                        dayCount++;
                    }
                }
                if (dayCount != 0)
                    averageMonthPrice = value / dayCount;
                value = 0.0;
                if (averageMonthPrice != 0)
                    monthAveragePrices.add(averageMonthPrice);
                dayCount = 0;
            }
            double minValue = Collections.min(monthAveragePrices);
            for (int i = 0; i < monthAveragePrices.size(); i++) {
                List<Double> list = new ArrayList<>(allMonthsAverageValues.get(i));
                list.add(monthAveragePrices.get(i) / minValue);
                allMonthsAverageValues.put(i,list);
            }
            monthAveragePrices.clear();
        }
        for (int i = 0; i < 12; i++) {
            List<Double> list = allMonthsAverageValues.get(i);
            Double sum = 0.0;
            for (Double d : list) {
                sum += d;
            }
            Double result = 0.0;
            if (list.size() != 0)
                result = sum / list.size();
            results[i] = result;
            System.out.println(result);
        }
    }
}
