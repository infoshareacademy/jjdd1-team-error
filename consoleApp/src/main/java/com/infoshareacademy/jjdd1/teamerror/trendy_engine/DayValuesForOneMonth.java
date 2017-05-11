package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sebastianlos on 13.04.2017.
 */
public class DayValuesForOneMonth {


    private List<Double> dayValues = new ArrayList<>();
    private LocalDate date;

    DayValuesForOneMonth(LocalDate date) {
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
