package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by sebastianlos on 13.04.2017.
 */
public class MonthDayValues {


    private List<Double> dayValues = new ArrayList<>();
    private LocalDate date;

    MonthDayValues(LocalDate date) {
        this.date = date;
    }

    void setDayValue(Double dayValues) {
        this.dayValues.add(dayValues);
    }

    Double getAverageMonthValue() {
        DoubleSummaryStatistics stats = dayValues.stream()
                .mapToDouble(s -> s)
                .summaryStatistics();
        return stats.getAverage();
    }

    public LocalDate getDate() {
        return date;
    }
}
