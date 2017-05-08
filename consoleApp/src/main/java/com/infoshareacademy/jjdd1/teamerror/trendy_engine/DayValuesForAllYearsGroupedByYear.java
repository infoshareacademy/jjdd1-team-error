package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sebastian_los on 03.05.17.
 */
public class DayValuesForAllYearsGroupedByYear {
    
    private Map<LocalDate, Double> dayDeviations;
    private Integer year;

    DayValuesForAllYearsGroupedByYear(Integer year) {
        this.dayDeviations = new LinkedHashMap<>();
        this.year = year;
    }

    public void setDayDeviations(LocalDate date, Double rate) {
        this.dayDeviations.put(date, rate);
    }

    public Integer getYear() {
        return year;
    }

    private Double getMinValue() {
        return Collections.min(dayDeviations.values());
    }

    public Map<LocalDate, Double> getDayPercentageDeviations() {
        Double minValue = getMinValue();
        return dayDeviations.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        dayObject -> (((dayObject.getValue() / minValue) -1) * 100)));
    }
}
