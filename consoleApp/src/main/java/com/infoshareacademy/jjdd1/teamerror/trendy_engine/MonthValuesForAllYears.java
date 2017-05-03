package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * Created by sebastianlos on 14.04.17.
 */
public class MonthValuesForAllYears {

    private List<Double> monthDeviations = new ArrayList<>();

    void setMonthDeviation(Double monthValue) {
        this.monthDeviations.add(monthValue);
    }

    public Double getMinValue() {
        return Collections.min(monthDeviations);
    }

    Double getAverageMonthValue() {
        DoubleSummaryStatistics stats = monthDeviations.stream()
                .mapToDouble(s -> s)
                .summaryStatistics();
        return stats.getAverage();
    }
}


