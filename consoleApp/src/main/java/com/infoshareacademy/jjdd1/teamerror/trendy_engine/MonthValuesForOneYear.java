package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sebastianlos on 14.04.17.
 */
public class MonthValuesForOneYear {
    Map<LocalDate, Double> monthValues = new HashMap<>();

    public void setMonthValue(DayValuesForOneMonth dayValuesForOneMonth) {
        this.monthValues.put(dayValuesForOneMonth.getDate(), dayValuesForOneMonth.getAverageMonthValue());
    }

    public Double getMinValue() {
        return Collections.min(monthValues.values());

    }

    public Map<LocalDate, Double> getMonthDeviations() {

        return  monthValues.entrySet().stream()
                .collect(Collectors.toMap(
                        s -> s.getKey(),
                        s -> s.getValue() / getMinValue()));
    }


}
