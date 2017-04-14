package com.infoshareacademy.jjdd1.teamerror;

import java.util.*;

/**
 * Created by sebas on 13.04.2017.
 */
public class DayValues {


    List<Double> dayValues = new ArrayList<>();

    public void setDayValue(Double dayValues) {
        this.dayValues.add(dayValues);
    }

    public Double getAverageMonthValue() {
        DoubleSummaryStatistics stats = dayValues.stream()
                .mapToDouble(s -> s)
                .summaryStatistics();
        return stats.getAverage();
    }
}
