package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * Created by sebastianlos on 04.05.17.
 */
public class DayValuesForAllYearsGroupedByDay {
    private LocalDate dayOfYear;
    private List<Double> dayPercentageDeviations;

    DayValuesForAllYearsGroupedByDay(LocalDate dayOfYear) {
        this.dayOfYear = dayOfYear;
        dayPercentageDeviations = new ArrayList<>();
    }

    void setDayPercentageDeviations(Double deviation) {
        this.dayPercentageDeviations.add(deviation);
    }

    Double getAverageDeviation() {
        return dayPercentageDeviations.stream()
                .mapToDouble(s -> s)
                .summaryStatistics().getAverage();
    }

    public LocalDate getDayOfYear() {
        return dayOfYear;
    }
}
