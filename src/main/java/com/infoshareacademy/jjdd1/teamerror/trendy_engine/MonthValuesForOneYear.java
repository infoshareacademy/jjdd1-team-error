package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sebastianlos on 14.04.17.
 */
public class MonthValuesForOneYear {
    Map<LocalDate, Double> monthValues = new HashMap<>();

    public void setMonthValue(DayValues dayValues) {
        this.monthValues.put(dayValues.getDate(), dayValues.getAverageMonthValue());
    }

    public Double getMinValue() {
        return Collections.min(monthValues.values());
    }

    public Map<LocalDate, Double> getMonthDeviations() {

        Map <LocalDate, Double> result = monthValues.entrySet().stream()
                .collect(Collectors.toMap(
                        s -> s.getKey(),
                        s -> s.getValue() / getMinValue()));
        return result;
    }


}
