package com.infoshareacademy.jjdd1.teamerror;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sebas on 13.04.2017.
 */
public class AverageMonthValuesInGivenYear {
    Map<Integer, List<Double>> monthsAndDailyValues = new LinkedHashMap<>();

    public void setMonthsAndDaysValues(LocalDate date, Double dailyValue) {
        int month = date.getMonthValue();
        if (monthsAndDailyValues.containsKey(month)) {
            List<Double> dailyValues = monthsAndDailyValues.get(month);
            dailyValues.add(dailyValue);
        }
        else if (!monthsAndDailyValues.containsKey(month)) {
            monthsAndDailyValues.put(month, new ArrayList<>());
        }
    }

    public Map<Integer, Double> getMonthAverageValues() {
        Map<Integer, Double> monthsAndItsAverageValues = new LinkedHashMap<>();
        for (Map.Entry value : monthsAndDailyValues.entrySet()) {
            LinkedList<Double> monthsValues = new LinkedList();
            Collections.copy(monthsValues, (List)value.getValue());
            monthsAndItsAverageValues.put((Integer)value.getKey(), Collections.min(monthsValues));
        }
        return monthsAndItsAverageValues;
    }
}
