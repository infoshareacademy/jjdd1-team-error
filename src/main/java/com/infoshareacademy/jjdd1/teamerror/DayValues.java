package com.infoshareacademy.jjdd1.teamerror;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by sebas on 13.04.2017.
 */
public class DayValues {


    private List<Double> dayValues = new ArrayList<>();
    private LocalDate date;

    public DayValues(LocalDate date) {
        this.date = date;
    }

    public void setDayValue(Double dayValues) {
        this.dayValues.add(dayValues);
    }

    public Double getAverageMonthValue() {
        DoubleSummaryStatistics stats = dayValues.stream()
                .mapToDouble(s -> s)
                .summaryStatistics();
        return stats.getAverage();
    }

    public LocalDate getDate() {
        return date;
    }
}
