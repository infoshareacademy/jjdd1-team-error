package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sebastianlos on 14.04.17.
 */
class YearMonthValues {
    private Map<LocalDate, Double> monthValues = new HashMap<>();

    void setMonthValue(MonthDayValues monthDayValues) {
        this.monthValues.put(monthDayValues.getDate(), monthDayValues.getAverageMonthValue());
    }

    private Double getMinValue() {
        return Collections.min(monthValues.values());

    }

    Map<LocalDate, Double> getMonthDeviations() {

        return  monthValues.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        s -> s.getValue() / getMinValue()));
    }


}
