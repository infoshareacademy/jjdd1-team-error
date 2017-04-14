package com.infoshareacademy.jjdd1.teamerror;

import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sebastianlos on 14.04.17.
 */
public class MonthValuesForAllYears {

    private List<Double> monthDeviations = new ArrayList<>();

    public void setMonthDeviations(Double monthValue) {
        this.monthDeviations.add(monthValue);
    }

    public Double getMinValue() {
        return Collections.min(monthDeviations);
    }

    public Double getAverageMonthValue() {
        DoubleSummaryStatistics stats = monthDeviations.stream()
                .mapToDouble(s -> s)
                .summaryStatistics();
        return stats.getAverage();
    }

    public Double getPercentageValue() {

        return getAverageMonthValue() - getMinValue();
    }



}
