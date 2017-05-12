package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastianlos on 14.04.17.
 */
class YearsMonthValues {

    private List<Double> monthDeviations = new ArrayList<>();

    void setMonthDeviation(Double monthValue) {
        this.monthDeviations.add(monthValue);
    }

    Double getAverageMonthValue() {
        return monthDeviations.stream()
                .mapToDouble(s -> s)
                .summaryStatistics().getAverage();
    }
}


