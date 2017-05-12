package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastianlos on 04.05.17.
 */
class DayValuesGroupedByDay {
    private List<Double> dayPercentageDeviations;

    DayValuesGroupedByDay() {
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
}
