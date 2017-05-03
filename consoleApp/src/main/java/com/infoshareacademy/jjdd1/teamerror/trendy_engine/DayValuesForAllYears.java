package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastian_los on 03.05.17.
 */
public class DayValuesForAllYears {
    
    private List<List<Double>> dayDeviationsGroupedByYear = new ArrayList<>();

    public void setDayDeviations(List<Double> dayDeviations) {
        this.dayDeviationsGroupedByYear.add(dayDeviations);
    }
}
