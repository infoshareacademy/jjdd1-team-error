package com.infoshareacademy.jjdd1.teamerror;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by sebastian_los on 06.05.17.
 */
public class HelpfulMethods {

    // round value to given number of decimal places
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static double average(List<Double> list) {
        return list
                .stream()
                .mapToDouble(a -> a)
                .summaryStatistics().getAverage();
    }

    public static Map<LocalDate, Double> minimizeDeviations(Map<LocalDate, Double> valuesAvgForStartingDays) {
        Map<LocalDate, Double> valuesAvgListFinal = new TreeMap<>();
        valuesAvgListFinal.putAll(valuesAvgForStartingDays
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        a -> a.getValue() - Collections.min(valuesAvgForStartingDays.values()))));
        return valuesAvgListFinal;
    }
}
