package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by sebastianlos on 04.05.17.
 */
public class DayValuesForAllYearsGroupedByDay {
    private Integer dayOfYear;
    private List<Double> dayPercentageDeviations;

    public DayValuesForAllYearsGroupedByDay(Integer dayOfYear) {
        this.dayOfYear = dayOfYear;
    }

    public void setDayPercentageDeviations(Double deviation) {
        this.dayPercentageDeviations.add(deviation);
    }
}
