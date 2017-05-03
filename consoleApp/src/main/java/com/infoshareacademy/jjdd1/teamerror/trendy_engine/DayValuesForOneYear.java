package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sebastian_los on 03.05.17.
 */
public class DayValuesForOneYear {


    private Map<LocalDate, Double> dayValues = new LinkedHashMap<>();

    public void setDayValue(LocalDate date, Double value) {
        this.dayValues.put(date, value);
    }

    private Double getMinValue() {
        return Collections.min(dayValues.values());
    }

    public Map<LocalDate, Double> getDayPercentageDeviations() {
        Double minValue = getMinValue();
        return dayValues.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        dayObject -> (((dayObject.getValue() / minValue) -1) * 100)));
    }
}
