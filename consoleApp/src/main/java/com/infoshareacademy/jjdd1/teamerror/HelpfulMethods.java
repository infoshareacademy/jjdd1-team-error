package com.infoshareacademy.jjdd1.teamerror;

import java.util.List;

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
}
