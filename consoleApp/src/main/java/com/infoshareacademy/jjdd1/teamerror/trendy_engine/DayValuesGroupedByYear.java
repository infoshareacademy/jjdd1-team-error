package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sebastian_los on 03.05.17.
 */
class DayValuesGroupedByYear {
    
    private Map<LocalDate, Double> dayDeviations;

    DayValuesGroupedByYear() {
        this.dayDeviations = new LinkedHashMap<>();
    }

    void setDayDeviations(LocalDate date, Double rate) {
        this.dayDeviations.put(date, rate);
    }

    private Double getMinValue() {
        return Collections.min(dayDeviations.values());
    }

    Map<LocalDate, Double> getDayPercentageDeviations() {
        Double minValue = getMinValue();
        return dayDeviations.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        dayObject -> (((dayObject.getValue() / minValue) -1) * 100)));
    }
}
