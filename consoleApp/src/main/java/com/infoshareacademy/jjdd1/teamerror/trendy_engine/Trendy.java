package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import com.infoshareacademy.jjdd1.teamerror.TripFullCost;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.RatesInfo;
import com.infoshareacademy.jjdd1.teamerror.file_loader.CurrencyFileFilter;
import com.infoshareacademy.jjdd1.teamerror.file_loader.PetrolFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sebastianlos on 07.04.17.
 */
public class Trendy {

    private static final Logger LOGGER = LoggerFactory.getLogger(Trendy.class);
    private static final int FIRST_DAY_OF_MONTH = 1;
    private static final int DECIMAL_PLACES = 2;
    private static final int NUMBER_OF_MONTHS_IN_YEAR = 12;
    private static final int FIRST_ELEMENT = 0;
    private static final int ANY_YEAR_WHERE_FEBRUAR_HAS_29_DAYS = 1904;

    private String conclusion = "";
    private Map<String, String> petrolTrendy = new LinkedHashMap<>();
    private Map<String, String> currencyTrendy = new LinkedHashMap<>();

    private PetrolFileFilter petrolFileFilter;
    private CurrencyFileFilter currencyFileFilter;
    private TripFullCost tripFullCost = new TripFullCost();
    private LocalDate startDate; //= LocalDate.of(2005,1,1);
    private LocalDate endDate; //= LocalDate.of(2017, 12, 30);
    private LocalDate startMonthDay; //= LocalDate.of(ANY_YEAR_WHERE_FEBRUAR_HAS_29_DAYS, 4, 10);
    private LocalDate endMonthDay; //= LocalDate.of(ANY_YEAR_WHERE_FEBRUAR_HAS_29_DAYS, 6, 15);

    public Trendy() {
    }

    public void setPetrolFileFilter(PetrolFileFilter petrolFileFilter) {
        this.petrolFileFilter = petrolFileFilter;
    }
    public void setCurrencyFileFilter(CurrencyFileFilter currencyFileFilter) {
        this.currencyFileFilter = currencyFileFilter;
    }
    public void setTripFullCost(TripFullCost tripFullCost) {
        this.tripFullCost = tripFullCost;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartMonthDay() {
        return startMonthDay;
    }

    public void setStartMonthDay(LocalDate startMonthDay) {
        this.startMonthDay = startMonthDay;
    }

    public LocalDate getEndMonthDay() {
        return endMonthDay;
    }

    public void setEndMonthDay(LocalDate endMonthDay) {
        this.endMonthDay = endMonthDay;
    }

    public String getConclusion() {
        return conclusion;
    }

    public Map<String, String> getPetrolTrendy() {
        return petrolTrendy;
    }

    public Map<String, String> getCurrencyTrendy() {
        return currencyTrendy;
    }

    private Map<LocalDate, Double> calculateMonthPercentageDeviations(List<RatesInfo> ratesList) {
        if (ratesList.isEmpty()) {
            return new HashMap<>();
        }
        List<DayValuesForOneMonth> dayValuesForOneMonthList = new ArrayList<>();
        List<MonthValuesForOneYear> monthValuesForOneYearList = new ArrayList<>();
        LocalDate currentDate =  ratesList.get(FIRST_ELEMENT).getDate().withDayOfMonth(FIRST_DAY_OF_MONTH);
        dayValuesForOneMonthList.add(new DayValuesForOneMonth(currentDate));
        for (RatesInfo dailyRate : ratesList) {
            if (!dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH).equals(currentDate) && dailyRate.getDate().getYear() == currentDate.getYear()) {
                currentDate = dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH);
                dayValuesForOneMonthList.add(new DayValuesForOneMonth(currentDate));
            }
            else if (!dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH).equals(currentDate) &&
                    dailyRate.getDate().getYear() != currentDate.getYear()) {
                MonthValuesForOneYear monthValues = new MonthValuesForOneYear();
                dayValuesForOneMonthList.forEach(monthValues::setMonthValue);
                monthValuesForOneYearList.add(monthValues);
                LOGGER.debug("Month average currency rates for year {} calculated.", currentDate.getYear());
                dayValuesForOneMonthList.clear();
                currentDate = dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH);
                dayValuesForOneMonthList.add(new DayValuesForOneMonth(currentDate));

            }
            if (dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH).equals(currentDate)) {
                dayValuesForOneMonthList.get(dayValuesForOneMonthList.size()-1).setDayValue(dailyRate.getRate());
            }
        }
        MonthValuesForOneYear monthValues = new MonthValuesForOneYear();
        dayValuesForOneMonthList.forEach(monthValues::setMonthValue);
        monthValuesForOneYearList.add(monthValues);


        Map<LocalDate, MonthValuesForAllYears> monthValuesForAllYearsList = new HashMap<>();
        monthValuesForOneYearList.forEach(
                monthRates -> monthRates.getMonthDeviations().forEach((date, value) -> {
                    monthValuesForAllYearsList.putIfAbsent(date.withDayOfMonth(FIRST_DAY_OF_MONTH)
                            .withYear(ANY_YEAR_WHERE_FEBRUAR_HAS_29_DAYS), new MonthValuesForAllYears());
                    monthValuesForAllYearsList.get(date.withDayOfMonth(FIRST_DAY_OF_MONTH)
                            .withYear(ANY_YEAR_WHERE_FEBRUAR_HAS_29_DAYS)).setMonthDeviation(value);
                }));
        LOGGER.debug("Currency month ratio values for all years calculated");
        Map<LocalDate, Double> averageValuesForSingleMonths = monthValuesForAllYearsList.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        s -> s.getValue().getAverageMonthValue()
                ));
        LOGGER.debug("Currency month average ratio values for all years calculated");

        Double min = Collections.min(averageValuesForSingleMonths.values());

        Map<LocalDate, Double> results = new TreeMap<>();
        results.putAll(averageValuesForSingleMonths.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        s -> round(((s.getValue() - min) * 100), DECIMAL_PLACES)
                )));
        LOGGER.info("Currency month average deviations for all years calculated");
        return results;
    }

    private Map<LocalDate, Double> calculateDayPercentageDeviations(List<RatesInfo> ratesList) {
        if (ratesList.isEmpty()) {
            return new HashMap<>();
        }
        Map<Integer, DayValuesForAllYearsGroupedByYear> dayValuesByYear = new LinkedHashMap<>();
        Integer currentYear = ratesList.get(0).getDate().getYear();
        dayValuesByYear.put(currentYear, new DayValuesForAllYearsGroupedByYear(currentYear));
        for (RatesInfo dayData : ratesList) {
            currentYear = dayData.getDate().getYear();
            dayValuesByYear.putIfAbsent(currentYear, new DayValuesForAllYearsGroupedByYear(currentYear));
            dayValuesByYear.get(currentYear).setDayDeviations(dayData.getDate(), dayData.getRate());
        }

        Map<LocalDate, DayValuesForAllYearsGroupedByDay> dayValuesByDay = new LinkedHashMap<>();
        dayValuesByYear.forEach((key, value) -> value.getDayPercentageDeviations().forEach((key1, value1) -> {
            LocalDate dayOfYear = key1.withYear(ANY_YEAR_WHERE_FEBRUAR_HAS_29_DAYS);
            dayValuesByDay.putIfAbsent(dayOfYear, new DayValuesForAllYearsGroupedByDay(dayOfYear));
            dayValuesByDay.get(dayOfYear).setDayPercentageDeviations(value1);
        }));


        Map<LocalDate, Double> averageDeviations = dayValuesByDay.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, s -> s.getValue().getAverageDeviation()));

        Double minVal = Collections.min(averageDeviations.values());

        Map<LocalDate, Double> result = new TreeMap<>();
        result.putAll(averageDeviations.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, s -> round(s.getValue() - minVal, DECIMAL_PLACES))));

        return result;
    }

    // set trendy values for petrol and currency to maps and conclusion
    public void setTrendy(String analysisType) {
        currencyTrendy.clear();
        petrolTrendy.clear();
        String currencySymbol = tripFullCost.getCurrency();
        String fuelType = tripFullCost.getFuelType();
        String country = tripFullCost.getCountry();
        Map<LocalDate, Double> currencyTrendyList = new TreeMap<>();
        Map<LocalDate, Double> petrolTrendyList = new TreeMap<>();
        List<RatesInfo> currencyDataList = currencyFileFilter.getListOfCurrencyDataObjects(currencySymbol);
        List <RatesInfo> petrolDataList = petrolFileFilter.getListOfPetrolDataObjects(country, fuelType);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        if (!currencyDataList.isEmpty()) {
            currencyDataList = filtrateDataByDates(currencyDataList);
            switch (analysisType) {
                case "optimalMonth":
                    currencyTrendyList = calculateMonthPercentageDeviations(currencyDataList);
                    formatter = DateTimeFormatter.ofPattern("MMMM");
                    break;

                case "optimalDay":
                    currencyTrendyList = calculateDayPercentageDeviations(currencyDataList);
                    formatter = DateTimeFormatter.ofPattern("d MMM");
                    break;

            }
        }
        if (!petrolDataList.isEmpty()) {
            petrolDataList = filtrateDataByDates(petrolDataList);
            petrolTrendyList = calculateMonthPercentageDeviations(petrolDataList);
        }
        Double sum = null;
        LocalDate optimalDate = null;

        for (Map.Entry currencyValue : currencyTrendyList.entrySet()) {
            String date = ((LocalDate)currencyValue.getKey()).format(formatter);
            LocalDate petrolDate = ((LocalDate) currencyValue.getKey()).withDayOfMonth(FIRST_DAY_OF_MONTH);
            if (currencyValue.getValue().equals(0.0)) {
                currencyTrendy.put(date, "THE LOWEST");
            }
            else {
                currencyTrendy.put(date, currencyValue.getValue().toString());
            }
            if (!petrolTrendyList.containsKey(petrolDate)) {
                petrolTrendy.put(date, "NO DATA");
            }
            if (petrolTrendyList.containsKey(petrolDate) &&
                    petrolTrendyList.get(petrolDate).equals(0.0)) {
                petrolTrendy.put(date, "THE LOWEST");
            }
            else {
                petrolTrendy.put(date, petrolTrendyList.get(petrolDate).toString());
            }
            // determine the lowest sum of currency and petrol percentage rates
            if (petrolTrendyList.containsKey(petrolDate) &&
                    (sum == null || (Double)currencyValue.getValue() + petrolTrendyList.getOrDefault(petrolDate, 0.0) < sum)) {
                sum = ((Double)currencyValue.getValue() + petrolTrendyList.getOrDefault(petrolDate, 0.0));
                optimalDate = (LocalDate)currencyValue.getKey();
            }
        }
        if (optimalDate == null) {
            conclusion = "The best time for cheap travel is in: NO DATA";
        }
        else {
            conclusion = "The best time for cheap travel is in: " + optimalDate.format(formatter);
        }
        LOGGER.info("Trendy values and conclusion set");
    }

    // print differences in currencies and fuel rates in each month and the best month for cheap travel
    public String getMonthTrendyAsString() {
        String currencySymbol = tripFullCost.getCurrency();
        String fuelType = tripFullCost.getFuelType();
        String country = tripFullCost.getCountry();
        Map<LocalDate, Double> currencyList = new HashMap<>();
        Map<LocalDate, Double> petrolList = new HashMap<>();
        List<RatesInfo> currencyDataList = currencyFileFilter.getListOfCurrencyDataObjects(currencySymbol);
        List <RatesInfo> petrolDataList = petrolFileFilter.getListOfPetrolDataObjects(country, fuelType);
        if (!currencyDataList.isEmpty()) {
            currencyDataList = filtrateDataByDates(currencyDataList);
            currencyList = calculateMonthPercentageDeviations(currencyDataList);
        }
        if (!petrolDataList.isEmpty()) {
            petrolDataList = filtrateDataByDates(petrolDataList);
            petrolList = calculateMonthPercentageDeviations(petrolDataList);
        }
        Double sum = null;
        Integer numberOfMonthWithOptimalRates = null;
        StringBuilder returnStatement = new StringBuilder();
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.US);
        returnStatement.append("Optimal time for trip analysis: \n\n");
        for (int i = 1; i <= NUMBER_OF_MONTHS_IN_YEAR; i++) {
            LocalDate currentDate = LocalDate.of(ANY_YEAR_WHERE_FEBRUAR_HAS_29_DAYS, i, FIRST_DAY_OF_MONTH);
            returnStatement.append(symbols.getMonths()[i-1].toUpperCase());
            returnStatement.append("\n");
            if (!currencyList.containsKey(currentDate)) {
                returnStatement.append( "Currency --> NO DATA \t\t");
            }
            if (currencyList.containsKey(currentDate) && currencyList.get(currentDate).equals(0.0)) {
                returnStatement.append("Currency --> THE LOWEST \t\t");
            }
            else if (currencyList.containsKey(currentDate)) {
                returnStatement.append("Currency --> ");
                returnStatement.append(currencyList.get(currentDate));
                returnStatement.append("% higher \t\t");
            }
            if (!petrolList.containsKey(currentDate)) {
                returnStatement.append("Petrol --> NO DATA \n");
            }
            if (petrolList.containsKey(currentDate) && petrolList.get(currentDate).equals(0.0)) {
                returnStatement.append("Petrol --> THE LOWEST \n");
            }
            else if (petrolList.containsKey(currentDate)) {
                returnStatement.append("Petrol --> ");
                returnStatement.append(petrolList.get(currentDate));
                returnStatement.append("% higher. \n");
            }
            // determine the lowest sum of currency and petrol percentage rates
            if ((currencyList.containsKey(currentDate) || petrolList.containsKey(currentDate)) &&
                    (sum == null || (currencyList.getOrDefault(currentDate, 0.0) + petrolList.getOrDefault(currentDate, 0.0)) < sum)) {
                sum = (currencyList.getOrDefault(currentDate, 0.0) + petrolList.getOrDefault(currentDate, 0.0));
                numberOfMonthWithOptimalRates = i-1;
            }
            returnStatement.append("\n");
        }
        if (numberOfMonthWithOptimalRates == null) {
            returnStatement.append("The best time for cheap travel is in: NO DATA");
        }
        else {
            returnStatement.append("The best time for cheap travel is in: ");
            returnStatement.append(symbols.getMonths()[numberOfMonthWithOptimalRates].toUpperCase());
        }
        LOGGER.info("Trendy analysis in string created");

//        calculateDayPercentageDeviations(currencyDataList).forEach((key, value) -> {
//            System.out.println(key + " " + value);
//        });
        return returnStatement.toString();
    }

    // round value to given number of decimal places
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private List<RatesInfo> filtrateDataByDates(List<RatesInfo> ratesData) {

        if (startDate != null) {
            ratesData = ratesData.stream()
                    .filter(dayObject -> dayObject.getDate().isAfter(startDate) || dayObject.getDate().isEqual(startDate))
                    .collect(Collectors.toList());
        }
        if (endDate != null) {
            ratesData = ratesData.stream()
                    .filter(dayObject -> dayObject.getDate().isBefore(endDate) || dayObject.getDate().isEqual(endDate))
                    .collect(Collectors.toList());
        }
        if (startMonthDay != null) {
            ratesData = ratesData.stream()
                    .filter(dayObject -> dayObject.getDate().withYear(ANY_YEAR_WHERE_FEBRUAR_HAS_29_DAYS).isAfter(startMonthDay) ||
                            dayObject.getDate().withYear(ANY_YEAR_WHERE_FEBRUAR_HAS_29_DAYS).isEqual(startMonthDay))
                    .collect(Collectors.toList());
        }
        if (endMonthDay != null) {
            ratesData = ratesData.stream()
                    .filter(dayObject -> dayObject.getDate().withYear(ANY_YEAR_WHERE_FEBRUAR_HAS_29_DAYS).isBefore(endMonthDay) ||
                            dayObject.getDate().withYear(ANY_YEAR_WHERE_FEBRUAR_HAS_29_DAYS).isEqual(endMonthDay))
                    .collect(Collectors.toList());
        }
        return ratesData;
    }

}
