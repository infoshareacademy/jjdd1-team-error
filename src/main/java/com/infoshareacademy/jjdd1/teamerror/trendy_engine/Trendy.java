package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.PetrolPrices;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.CurrencyHistoryDayValue;
import com.infoshareacademy.jjdd1.teamerror.file_loader.CurrencyFileFilter;
import com.infoshareacademy.jjdd1.teamerror.file_loader.PetrolFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sebastianlos on 07.04.17.
 */
public class Trendy {

    private static final Logger LOGGER = LoggerFactory.getLogger(Trendy.class);
    public static final int FIRST_DAY_OF_MONTH = 1;
    public static final int DECIMAL_PLACES = 2;
    public static final int NUMBER_OF_MONTHS_IN_YEAR = 12;


    private PetrolFileFilter petrolFileFilter;

    private CurrencyFileFilter currencyFileFilter;

    public void setPetrolFileFilter(PetrolFileFilter petrolFileFilter) {
        this.petrolFileFilter = petrolFileFilter;
    }

    public void setCurrencyFileFilter(CurrencyFileFilter currencyFileFilter) {
        this.currencyFileFilter = currencyFileFilter;
    }

    public Trendy() {
    }

    public Map<Integer, Double> calculateMonthPercentageDeviationsForCurrency(List<CurrencyHistoryDayValue> currencyRatesList) {
        if (currencyRatesList.isEmpty()) {
            return new HashMap<>();
        }
        List<DayValues> dayValuesList = new ArrayList<>();
        List<MonthValuesForOneYear> monthValuesForOneYearList = new ArrayList<>();
        LocalDate currentDate =  currencyRatesList.get(0).getDate().withDayOfMonth(FIRST_DAY_OF_MONTH);;
        dayValuesList.add(new DayValues(currentDate));
        for (CurrencyHistoryDayValue dailyRate : currencyRatesList) {
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
                dayValuesList.get(dayValuesList.size()-1).setDayValue(dailyRate.getClose());
            }
        }
        MonthValuesForOneYear monthValues = new MonthValuesForOneYear();
        dayValuesList.forEach(monthValues::setMonthValue);
        monthValuesForOneYearList.add(monthValues);


        Map<Integer, MonthValuesForAllYears> monthValuesForAllYearsList = new HashMap<>();
        monthValuesForOneYearList.forEach(
                monthRates -> monthRates.getMonthDeviations().entrySet()
                .forEach( monthRate -> {
                    LocalDate date = monthRate.getKey();
                    monthValuesForAllYearsList.putIfAbsent(date.getMonthValue()-1, new MonthValuesForAllYears());
                    monthValuesForAllYearsList.get(date.getMonthValue()-1).setMonthDeviation(monthRate.getValue());
                }));
        LOGGER.debug("Currency month ratio values for all years calculated");
        Map<Integer, Double> averageValuesForSingleMonths = monthValuesForAllYearsList.entrySet().stream()
                .collect(Collectors.toMap(
                        s -> s.getKey(),
                        s -> s.getValue().getAverageMonthValue()
                ));
        LOGGER.debug("Currency month average ratio values for all years calculated");

        Double min = Collections.min(averageValuesForSingleMonths.values());

        Map<Integer, Double> results =  averageValuesForSingleMonths.entrySet().stream()
                .collect(Collectors.toMap(
                        s -> s.getKey(),
                        s -> round(((s.getValue() - min) * 100), DECIMAL_PLACES)
                ));
        LOGGER.info("Currency month average deviations for all years calculated");
        return results;
    }

    public Map<Integer, Double> calculateMonthPercentageDeviationsForPetrol(List<PetrolPrices> petrolRatesList, String kindOfFuel) {

        if (petrolRatesList.isEmpty()) {
            return new HashMap<>();
        }
        List<DayValues> dayValuesList = new ArrayList<>();
        List<MonthValuesForOneYear> monthValuesForOneYearList = new ArrayList<>();
        LocalDate currentDate =  petrolRatesList.get(0).getDate().withDayOfMonth(FIRST_DAY_OF_MONTH);;
        dayValuesList.add(new DayValues(currentDate));
        for (PetrolPrices dailyRate : petrolRatesList) {
            if (!dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH).equals(currentDate) && dailyRate.getDate().getYear() == currentDate.getYear()) {
                currentDate = dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH);
                dayValuesList.add(new DayValues(currentDate));
            }
            else if (!dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH).equals(currentDate) &&
                    dailyRate.getDate().getYear() != currentDate.getYear()) {
                MonthValuesForOneYear monthValues = new MonthValuesForOneYear();
                dayValuesList.forEach(monthValues::setMonthValue);
                monthValuesForOneYearList.add(monthValues);
                LOGGER.debug("Month average petrol rates for year {} calculated.", currentDate.getYear());
                dayValuesList.clear();
                currentDate = dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH);
                dayValuesList.add(new DayValues(currentDate));

            }
            if (dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH).equals(currentDate)) {

                if(kindOfFuel.equalsIgnoreCase("diesel")) {
                    dayValuesList.get(dayValuesList.size()-1).setDayValue(dailyRate.getDieselPrice());
                }
                else {
                    dayValuesList.get(dayValuesList.size() - 1).setDayValue(dailyRate.getGasolinePrice());
                }
            }
        }
        MonthValuesForOneYear monthValues = new MonthValuesForOneYear();
        dayValuesList.forEach(monthValues::setMonthValue);
        monthValuesForOneYearList.add(monthValues);

        Map<Integer, MonthValuesForAllYears> monthValuesForAllYearsList = new HashMap<>();
        monthValuesForOneYearList.forEach(
                monthRates -> monthRates.getMonthDeviations().entrySet()
                        .forEach( monthRate -> {
                            LocalDate date = (LocalDate)monthRate.getKey();
                            monthValuesForAllYearsList.putIfAbsent(date.getMonthValue()-1, new MonthValuesForAllYears());
                            monthValuesForAllYearsList.get(date.getMonthValue()-1).setMonthDeviation((Double)monthRate.getValue());
                        }));
        LOGGER.debug("Petrol month ratio values for all years calculated");
        Map<Integer, Double> averageValuesForSingleMonths = monthValuesForAllYearsList.entrySet().stream()
                .collect(Collectors.toMap(
                        s -> s.getKey(),
                        s -> s.getValue().getAverageMonthValue()
                ));
        LOGGER.debug("Petrol month average ratio values for all years calculated");
        Double min = Collections.min(averageValuesForSingleMonths.values());

        Map<Integer, Double> results =  averageValuesForSingleMonths.entrySet().stream()
                .collect(Collectors.toMap(
                        s -> s.getKey(),
                        s -> round(((s.getValue() - min) * 100), DECIMAL_PLACES)
                ));
        LOGGER.info("Petrol month average deviations for all years calculated");
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
    public String optimalTimeForTrip(String currencySymbol, String fuelType, String country) {
        Map<Integer, Double> currencyList = new HashMap<>();
        List<CurrencyHistoryDayValue> currencyDataList = currencyFileFilter.getListOfCurrencyDataObjects(currencySymbol);
        if (!currencyDataList.isEmpty()) {
            currencyList = calculateMonthPercentageDeviationsForCurrency(currencyDataList);
        }
        Map<Integer, Double> petrolList = new HashMap<>();
        List <PetrolPrices> petrolDataList = petrolFileFilter.getListOfPetrolDataObjects(country);
        if (!petrolDataList.isEmpty()) {
            petrolList = calculateMonthPercentageDeviationsForPetrol(petrolDataList, fuelType);
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
        return returnStatement.toString();
    }


}
