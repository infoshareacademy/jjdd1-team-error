package com.infoshareacademy.jjdd1.teamerror;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sebas on 13.04.2017.
 */
public class MonthValues {


    List<Double> monthValues = new ArrayList<>();

    public void setDayValue(Double monthValues) {
        this.monthValues.add(monthValues);
    }

    public Double getAverageMonthValue() {
        DoubleSummaryStatistics stats = monthValues.stream()
                .mapToDouble(s -> s)
                .summaryStatistics();
        return stats.getAverage();
    }

//    public Map<Integer, Double> getMonthAverageValues() {
//        Map<Integer, Double> monthsAndItsAverageValues = new LinkedHashMap<>();
//        for (Map.Entry value : monthsAndDailyValues.entrySet()) {
//            LinkedList<Double> monthsValues = new LinkedList();
//            Collections.copy(monthsValues, (List)value.getValue());
//            monthsAndItsAverageValues.put((Integer)value.getKey(), Collections.min(monthsValues));
//        }
//        return monthsAndItsAverageValues;
//    }
//
//    public Double getYearMinValues() {
//        return Collections.min(getMonthAverageValues().values());
//    }
//
//    public Map<Integer, Double> getMonthDeviations() {
//        HashMap<Integer, Double> result = new HashMap<>(getMonthAverageValues());
//        getMonthAverageValues().keySet()
//                .forEach(k -> result.compute(k, (key,v) -> v/getYearMinValues()));
//        return result;
//    }
}
