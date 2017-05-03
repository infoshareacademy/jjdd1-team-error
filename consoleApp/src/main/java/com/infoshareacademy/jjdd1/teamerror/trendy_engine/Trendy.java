package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import com.infoshareacademy.jjdd1.teamerror.TripFullCost;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.RatesInfo;
import com.infoshareacademy.jjdd1.teamerror.file_loader.CurrencyFileFilter;
import com.infoshareacademy.jjdd1.teamerror.file_loader.PetrolFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
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

    private String conclusion = "";
    private Map<String, String> petrolTrendy = new LinkedHashMap();
    private Map<String, String> currencyTrendy = new LinkedHashMap();

    private PetrolFileFilter petrolFileFilter;
    private CurrencyFileFilter currencyFileFilter;
    private TripFullCost tripFullCost = new TripFullCost();
    private LocalDate startDate;
    private LocalDate endDate;

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

    public String getConclusion() {
        return conclusion;
    }

    public Map<String, String> getPetrolTrendy() {
        return petrolTrendy;
    }

    public Map<String, String> getCurrencyTrendy() {
        return currencyTrendy;
    }

    public Map<Integer, Double> calculateMonthPercentageDeviations(List<RatesInfo> ratesList) {
        if (ratesList.isEmpty()) {
            return new HashMap<>();
        }
        List<DayValues> dayValuesList = new ArrayList<>();
        List<MonthValuesForOneYear> monthValuesForOneYearList = new ArrayList<>();
        LocalDate currentDate =  ratesList.get(FIRST_ELEMENT).getDate().withDayOfMonth(FIRST_DAY_OF_MONTH);
        dayValuesList.add(new DayValues(currentDate));
        for (RatesInfo dailyRate : ratesList) {
            if (!dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH).equals(currentDate) && dailyRate.getDate().getYear() == currentDate.getYear()) {
                currentDate = dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH);
                dayValuesList.add(new DayValues(currentDate));
            }
            else if (!dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH).equals(currentDate) &&
                    dailyRate.getDate().getYear() != currentDate.getYear()) {
                MonthValuesForOneYear monthValues = new MonthValuesForOneYear();
                dayValuesList.forEach(monthValues::setMonthValue);
                monthValuesForOneYearList.add(monthValues);
                LOGGER.debug("Month average currency rates for year {} calculated.", currentDate.getYear());
                dayValuesList.clear();
                currentDate = dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH);
                dayValuesList.add(new DayValues(currentDate));

            }
            if (dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH).equals(currentDate)) {
                dayValuesList.get(dayValuesList.size()-1).setDayValue(dailyRate.getRate());
            }
        }
        MonthValuesForOneYear monthValues = new MonthValuesForOneYear();
        dayValuesList.forEach(monthValues::setMonthValue);
        monthValuesForOneYearList.add(monthValues);


        Map<Integer, MonthValuesForAllYears> monthValuesForAllYearsList = new HashMap<>();
        monthValuesForOneYearList.forEach(
                monthRates -> monthRates.getMonthDeviations().forEach((date, value) -> {
                    monthValuesForAllYearsList.putIfAbsent(date.getMonthValue() - 1, new MonthValuesForAllYears());
                    monthValuesForAllYearsList.get(date.getMonthValue() - 1).setMonthDeviation(value);
                }));
        LOGGER.debug("Currency month ratio values for all years calculated");
        Map<Integer, Double> averageValuesForSingleMonths = monthValuesForAllYearsList.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        s -> s.getValue().getAverageMonthValue()
                ));
        LOGGER.debug("Currency month average ratio values for all years calculated");

        Double min = Collections.min(averageValuesForSingleMonths.values());

        Map<Integer, Double> results =  averageValuesForSingleMonths.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        s -> round(((s.getValue() - min) * 100), DECIMAL_PLACES)
                ));
        LOGGER.info("Currency month average deviations for all years calculated");
        return results;
    }

    // round value to given number of decimal places
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    // print differences in currencies and fuel rates in each month and the best time for cheap travel
    public String getTrendyAsString() {
        String currencySymbol = tripFullCost.getCurrency();
        String fuelType = tripFullCost.getFuelType();
        String country = tripFullCost.getCountry();
        Map<Integer, Double> currencyList = new HashMap<>();
        List<RatesInfo> currencyDataList = currencyFileFilter.getListOfCurrencyDataObjects(currencySymbol);
        if (!currencyDataList.isEmpty()) {
            currencyList = calculateMonthPercentageDeviations(currencyDataList);
        }
        Map<Integer, Double> petrolList = new HashMap<>();
        List <RatesInfo> petrolDataList = petrolFileFilter.getListOfPetrolDataObjects(country, fuelType);
        if (!petrolDataList.isEmpty()) {
            petrolList = calculateMonthPercentageDeviations(petrolDataList);
        }
        Double sum = null;
        Integer numberOfMonthWithOptimalRates = null;
        StringBuilder returnStatement = new StringBuilder();
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.US);
        returnStatement.append("Optimal time for trip analysis: \n\n");
        for (int i = 0; i < NUMBER_OF_MONTHS_IN_YEAR; i++) {
            returnStatement.append(symbols.getMonths()[i].toUpperCase());
            returnStatement.append("\n");
            if (!currencyList.containsKey(i)) {
                returnStatement.append( "Currency --> NO DATA \t\t");
            }
            if (!petrolList.containsKey(i)) {
                returnStatement.append("Petrol --> NO DATA \n");
            }
            if (currencyList.containsKey(i) && currencyList.get(i).equals(0.0)) {
                returnStatement.append("Currency --> THE LOWEST \t\t");
            }
            else if (currencyList.containsKey(i)) {
                returnStatement.append("Currency --> ");
                returnStatement.append(currencyList.get(i));
                returnStatement.append("% higher \t\t");
            }
            if (petrolList.containsKey(i) && petrolList.get(i).equals(0.0)) {
                returnStatement.append("Petrol --> THE LOWEST \n");
            }
            else if (petrolList.containsKey(i)) {
                returnStatement.append("Petrol --> ");
                returnStatement.append(petrolList.get(i));
                returnStatement.append("% higher. \n");
            }
            // determine the lowest sum of currency and petrol percentage rates
            if ((currencyList.containsKey(i) || petrolList.containsKey(i)) &&
                    (sum == null || (currencyList.getOrDefault(i, 0.0) + petrolList.getOrDefault(i, 0.0)) < sum)) {
                sum = (currencyList.getOrDefault(i, 0.0) + petrolList.getOrDefault(i, 0.0));
                numberOfMonthWithOptimalRates = i;
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
        return returnStatement.toString();
    }

    // set trendy values for petrol and currency to maps and conclusion
    public void setTrendy() {
        String currencySymbol = tripFullCost.getCurrency();
        String fuelType = tripFullCost.getFuelType();
        String country = tripFullCost.getCountry();
        Map<Integer, Double> currencyList = new HashMap<>();
        List<RatesInfo> currencyDataList = currencyFileFilter.getListOfCurrencyDataObjects(currencySymbol);
        if (!currencyDataList.isEmpty()) {
            currencyList = calculateMonthPercentageDeviations(currencyDataList);
        }
        Map<Integer, Double> petrolList = new HashMap<>();
        List <RatesInfo> petrolDataList = petrolFileFilter.getListOfPetrolDataObjects(country, fuelType);
        if (!petrolDataList.isEmpty()) {
            petrolList = calculateMonthPercentageDeviations(petrolDataList);
        }
        Double sum = null;
        Integer numberOfMonthWithOptimalRates = null;
        StringBuilder returnStatement = new StringBuilder();
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.US);
        returnStatement.append("Optimal time for trip analysis: \n\n");
        for (int i = 0; i < NUMBER_OF_MONTHS_IN_YEAR; i++) {
            String month = symbols.getMonths()[i].toUpperCase();
            returnStatement.append("\n");
            if (!currencyList.containsKey(i)) {
                currencyTrendy.put(month, "NO DATA");
            }
            if (!petrolList.containsKey(i)) {
                petrolTrendy.put(month, "NO DATA");
            }
            if (currencyList.containsKey(i) && currencyList.get(i).equals(0.0)) {
                currencyTrendy.put(month, "THE LOWEST");
            }
            else if (currencyList.containsKey(i)) {
                currencyTrendy.put(month, currencyList.get(i).toString());
            }
            if (petrolList.containsKey(i) && petrolList.get(i).equals(0.0)) {
                petrolTrendy.put(month, "THE LOWEST");
            }
            else if (petrolList.containsKey(i)) {
                petrolTrendy.put(month, petrolList.get(i).toString());
            }
            // determine the lowest sum of currency and petrol percentage rates
            if ((currencyList.containsKey(i) || petrolList.containsKey(i)) &&
                    (sum == null || (currencyList.getOrDefault(i, 0.0) + petrolList.getOrDefault(i, 0.0)) < sum)) {
                sum = (currencyList.getOrDefault(i, 0.0) + petrolList.getOrDefault(i, 0.0));
                numberOfMonthWithOptimalRates = i;
            }
        }
        if (numberOfMonthWithOptimalRates == null) {
            conclusion = "The best time for cheap travel is in: NO DATA";
        }
        else {
            conclusion = "The best time for cheap travel is in: " + symbols.getMonths()[numberOfMonthWithOptimalRates].toUpperCase();
        }
        LOGGER.info("Trendy values and conclusion set");
    }


}
