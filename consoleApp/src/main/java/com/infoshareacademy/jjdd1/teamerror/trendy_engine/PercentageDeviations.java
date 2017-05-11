package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import com.infoshareacademy.jjdd1.teamerror.HelpfulMethods;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.RatesInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sebastianlos on 11.05.17.
 */
public class PercentageDeviations {

    private static final int YEAR_WITH_29_DAY_FEBRUARY = 1904;
    private static final int DECIMAL_PLACES = 2;
    private static final int FIRST_ELEMENT = 0;
    private static final int FIRST_DAY_OF_MONTH = 1;
    private static final Logger LOGGER = LoggerFactory.getLogger(PercentageDeviations.class);

    private List<RatesInfo> ratesList;


    PercentageDeviations(List<RatesInfo> ratesList) {
        this.ratesList = ratesList;
    }

    Map<LocalDate, Double> getDayPercentageDeviations() {
        if (ratesList.isEmpty()) {
            return new HashMap<>();
        }
        Map<Integer, DayValuesGroupedByYear> dayValuesByYear = new LinkedHashMap<>();
        Integer currentYear = ratesList.get(0).getDate().getYear();
        dayValuesByYear.put(currentYear, new DayValuesGroupedByYear());
        for (RatesInfo dayData : ratesList) {
            currentYear = dayData.getDate().getYear();
            dayValuesByYear.putIfAbsent(currentYear, new DayValuesGroupedByYear());
            dayValuesByYear.get(currentYear).setDayDeviations(dayData.getDate(), dayData.getRate());
        }

        Map<LocalDate, DayValuesGroupedByDay> dayValuesByDay = new LinkedHashMap<>();
        dayValuesByYear.forEach((key, value) -> value.getDayPercentageDeviations().forEach((key1, value1) -> {
            LocalDate dayOfYear = key1.withYear(YEAR_WITH_29_DAY_FEBRUARY);
            dayValuesByDay.putIfAbsent(dayOfYear, new DayValuesGroupedByDay());
            dayValuesByDay.get(dayOfYear).setDayPercentageDeviations(value1);
        }));


        Map<LocalDate, Double> averageDeviations = dayValuesByDay.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, s -> s.getValue().getAverageDeviation()));

        Double minVal = Collections.min(averageDeviations.values());

        Map<LocalDate, Double> result = new TreeMap<>();
        result.putAll(averageDeviations.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        s -> HelpfulMethods.round(s.getValue() - minVal, DECIMAL_PLACES))));

        return result;
    }

    Map<LocalDate, Double> getMonthPercentageDeviations() {
        if (ratesList.isEmpty()) {
            return new HashMap<>();
        }
        List<MonthDayValues> monthDayValuesList = new ArrayList<>();
        List<YearMonthValues> yearMonthValuesList = new ArrayList<>();
        LocalDate currentDate =  ratesList.get(FIRST_ELEMENT).getDate().withDayOfMonth(FIRST_DAY_OF_MONTH);
        monthDayValuesList.add(new MonthDayValues(currentDate));
        for (RatesInfo dailyRate : ratesList) {
            if (!dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH).equals(currentDate) &&
                    dailyRate.getDate().getYear() == currentDate.getYear()) {
                currentDate = dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH);
                monthDayValuesList.add(new MonthDayValues(currentDate));
            }
            else if (!dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH).equals(currentDate) &&
                    dailyRate.getDate().getYear() != currentDate.getYear()) {
                YearMonthValues monthValues = new YearMonthValues();
                monthDayValuesList.forEach(monthValues::setMonthValue);
                yearMonthValuesList.add(monthValues);
                LOGGER.debug("Month average currency rates for year {} calculated.", currentDate.getYear());
                monthDayValuesList.clear();
                currentDate = dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH);
                monthDayValuesList.add(new MonthDayValues(currentDate));

            }
            if (dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH).equals(currentDate)) {
                monthDayValuesList.get(monthDayValuesList.size()-1).setDayValue(dailyRate.getRate());
            }
        }
        YearMonthValues monthValues = new YearMonthValues();
        monthDayValuesList.forEach(monthValues::setMonthValue);
        yearMonthValuesList.add(monthValues);


        Map<LocalDate, YearsMonthValues> monthValuesForAllYearsList = new HashMap<>();
        yearMonthValuesList.forEach(
                monthRates -> monthRates.getMonthDeviations().forEach((date, value) -> {
                    monthValuesForAllYearsList.putIfAbsent(date.withDayOfMonth(FIRST_DAY_OF_MONTH)
                            .withYear(YEAR_WITH_29_DAY_FEBRUARY), new YearsMonthValues());
                    monthValuesForAllYearsList.get(date.withDayOfMonth(FIRST_DAY_OF_MONTH)
                            .withYear(YEAR_WITH_29_DAY_FEBRUARY)).setMonthDeviation(value);
                }));
        LOGGER.debug("Currency month ratio values for all years calculated");
        Map<LocalDate, Double> averageValuesForSingleMonths = monthValuesForAllYearsList.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        s -> s.getValue().getAverageMonthValue()
                ));
        LOGGER.debug("Currency month average ratio values for all years calculated");

        Map<LocalDate, Double> results = new TreeMap<>();
        results.putAll(averageValuesForSingleMonths.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        s -> HelpfulMethods.round(((s.getValue() - 1) * 100), DECIMAL_PLACES)
                )));
        LOGGER.info("Currency month average deviations for all years calculated");
        return HelpfulMethods.minimizeDeviations(results);
    }

}
