package com.infoshareacademy.jjdd1.teamerror;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sebastianlos on 14.04.17.
 */
public class MonthValuesForOneYear {
    List<Double> monthValues = new ArrayList<>();

    public void setMonthValues(Double monthValues) {
        this.monthValues.add(monthValues);
    }

    public List<Double> getMonthDeviations() {

        return monthValues.stream()
                .map(s -> s / getMinValue())
                .collect(Collectors.toList());
    }

    public Double getMinValue() {
        return Collections.min(monthValues);
    }

}
