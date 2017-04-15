package com.infoshareacademy.jjdd1.teamerror;

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
    public static final int FIRST_DAY_OF_MONTH = 1;
    public static final int DECIMAL_PLACES = 2;

    // return a list of 12 averaged, percentage values of currency rate differences for each month
    public static List<Double> checkCurrencyTrendy(List<CurrencyHistoryDayValue> currencyList) {

        // set of years for which currency data are available
        Set<Integer> years = new LinkedHashSet<>();
        currencyList.forEach(year -> years.add(year.getDate().getYear()));
        // list of averaged currency rates for each month at given year
        List<Double> monthAverageCurrencyRate = new ArrayList<>();
        // map of all averaged currency rates for each month at given years
        Map<Integer,List<Double>> allMonthsAverageCurrencyRates = new HashMap<>();
        // create 12 elements for each month
        for (int i = 0; i < 12; i++) {
            List<Double> monthValues = new ArrayList<>();
            allMonthsAverageCurrencyRates.put(i, monthValues);
        }

        // sum of all currency rates in given month
        double monthSumOfRates = 0.0;
        // number of days in month
        int dayCount = 0;


        // iterate over years
        for (int year : years) {
            // iterate over months
            for (Integer x = 1; x <= 12; x++) {
                // average value of currency rate for given month
                double averagedMonthRate = 0;
                // iterate over all dates(objects)
                for (CurrencyHistoryDayValue obj : currencyList) {
                    // check whether given year and month fit to the object date
                    if (obj.getDate().getYear() == year && obj.getDate().getMonthValue() == x) {
                        // add another rate
                        monthSumOfRates += obj.getClose();
                        dayCount++;
                    }
                }
                if (dayCount != 0)
                    averagedMonthRate = monthSumOfRates / dayCount;
                monthSumOfRates = 0.0;
                if (averagedMonthRate != 0)
                    monthAverageCurrencyRate.add(averagedMonthRate);
                dayCount = 0;
            }
            double minValue = Collections.min(monthAverageCurrencyRate);
            // iterate over all month average currency rates of given year
            for (int i = 0; i < monthAverageCurrencyRate.size(); i++) {
                // copy present list to temporary list
                List<Double> tempList = new ArrayList<>(allMonthsAverageCurrencyRates.get(i));
                // add another values to list
                tempList.add(monthAverageCurrencyRate.get(i) / minValue);
                // update list
                allMonthsAverageCurrencyRates.put(i,tempList);
            }
            monthAverageCurrencyRate.clear();
            LOGGER.debug("List of all average month values of currency in {} generated", year);
        }

        return convertSingleValuesToPercentageValues(allMonthsAverageCurrencyRates);
    }


    public static Map<Integer, Double> calculateMonthPercentageDeviationsForCurrency(List<CurrencyHistoryDayValue> currencyRatesList) {

        List<DayValues> dayValuesList = new ArrayList<>();
        List<MonthValuesForOneYear> monthValuesForOneYearList = new ArrayList<>();
        LocalDate currentDate =  currencyRatesList.get(0).getDate().withDayOfMonth(FIRST_DAY_OF_MONTH);;
        dayValuesList.add(new DayValues(currentDate));
        for (CurrencyHistoryDayValue dailyRate : currencyRatesList) {
            if (!dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH).equals(currentDate) && dailyRate.getDate().getYear() == currentDate.getYear()) {
                currentDate = dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH);
                dayValuesList.add(new DayValues(currentDate));
            }
            else if (!dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH).equals(currentDate) && dailyRate.getDate().getYear() != currentDate.getYear()) {
                MonthValuesForOneYear monthValues = new MonthValuesForOneYear();
                dayValuesList.forEach(s -> monthValues.setMonthValue(s));
                monthValuesForOneYearList.add(monthValues);
                dayValuesList.clear();
                currentDate = dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH);
                dayValuesList.add(new DayValues(currentDate));

            }
            if (dailyRate.getDate().withDayOfMonth(FIRST_DAY_OF_MONTH).equals(currentDate)) {
                dayValuesList.get(dayValuesList.size()-1).setDayValue(dailyRate.getClose());
            }
        }
        MonthValuesForOneYear monthValues = new MonthValuesForOneYear();
        dayValuesList.forEach(s -> monthValues.setMonthValue(s));
        monthValuesForOneYearList.add(monthValues);

        Map<Integer, MonthValuesForAllYears> monthValuesForAllYearsList = new HashMap<>();
        monthValuesForOneYearList.forEach(
                monthRates -> monthRates.getMonthDeviations().entrySet()
                .forEach( monthRate -> {
                    LocalDate date = (LocalDate)monthRate.getKey();
                    monthValuesForAllYearsList.putIfAbsent(date.getMonthValue()-1, new MonthValuesForAllYears());
                    monthValuesForAllYearsList.get(date.getMonthValue()-1).setMonthDeviation((Double)monthRate.getValue());
                }));
        Map<Integer, Double> averageValuesForSingleMonths = monthValuesForAllYearsList.entrySet().stream()
                .collect(Collectors.toMap(
                        s -> s.getKey(),
                        s -> s.getValue().getAverageMonthValue()
                ));

        Double min = Collections.min(averageValuesForSingleMonths.values());

        Map<Integer, Double> averageValuesOfDeviationsForSingleMonths = averageValuesForSingleMonths.entrySet().stream()
                .collect(Collectors.toMap(
                        s -> s.getKey(),
                        s -> round(((s.getValue() - min) * 100), DECIMAL_PLACES)
                ));
        return averageValuesOfDeviationsForSingleMonths;
    }


    // return a list of 12 averaged, percentage values of petrol rate differences for each month
    public static List<Double> checkFuelTrendy(List<PetrolPrices> petrolList, String kindOfFuel) {
        // set of years for which petrol data are available
        Set<Integer> years = new LinkedHashSet<>();
        // list of averaged petrol rates for each month at given year
        List<Double> monthAveragePetrolRate = new ArrayList<>();
        // map of all averaged currency rates for each month at given years
        Map<Integer,List<Double>> allMonthsAveragePetrolRates = new HashMap<>();
        // create 12 elements for each month
        for (int i = 0; i < 12; i++) {
            List<Double> monthValues = new ArrayList<>();
            allMonthsAveragePetrolRates.put(i, monthValues);
        }
        // list of averaged difference rates for all months and given years
        List<Double> results = new ArrayList<>();
        // list of final results of petrol differences for all months and given years in percents
        List<Double> resultsInPercents = new ArrayList<>();

        // set all available years to list of years
        for (PetrolPrices obj : petrolList) {
            years.add(obj.getDate().getYear());
        }

        // iterate over years
        for (int year : years) {
            // iterate over months
            for (Integer x = 1; x <= 12; x++) {
                // iterate over all dates(objects)
                for (PetrolPrices obj : petrolList) {
                    // check whether given year and month fit to the object date
                    if (obj.getDate().getYear() == year && obj.getDate().getMonthValue() == x) {
                        // add another rate
                        if (kindOfFuel.equalsIgnoreCase("diesel"))
                            monthAveragePetrolRate.add(obj.getDieselPrice());
                        else if (kindOfFuel.equalsIgnoreCase("gasoline"))
                            monthAveragePetrolRate.add(obj.getGasolinePrice());
                    }
                }
            }
            double minValue = Collections.min(monthAveragePetrolRate);
            // iterate over all month average petrol rates of given year
            for (int i = 0; i < monthAveragePetrolRate.size(); i++) {
                // copy present list to temporary list
                List<Double> tempList = new ArrayList<>(allMonthsAveragePetrolRates.get(i));
                // add another values to list
                tempList.add(monthAveragePetrolRate.get(i) / minValue);
                // update list
                allMonthsAveragePetrolRates.put(i,tempList);
            }
            monthAveragePetrolRate.clear();
            LOGGER.debug("List of all average month values of currency in {} generated", year);
        }

        return convertSingleValuesToPercentageValues(allMonthsAveragePetrolRates);
    }

    // convert map of all valuse of months for each given years to list of single percentage averaged values for each month
    private static List<Double> convertSingleValuesToPercentageValues(Map<Integer,List<Double>> allMonthsAverageRates) {
        // list of final results of currency/petrol differences for all months and given years in percents
        List<Double> resultsInPercents = new ArrayList<>();
        // list of averaged difference rates for all months and given years
        List<Double> results = new ArrayList<>();

        // iterate over all months and merge all values of given month to one
        for (int i = 0; i < 12; i++) {
            // copy present list to temporary list
            List<Double> tempList = allMonthsAverageRates.get(i);

            Double sum = 0.0;
            // sum all month's values
            for (Double d : tempList) {
                sum += d;
            }
            Double result = 0.0;
            // make average value
            if (tempList.size() != 0)
                result = sum / tempList.size();
            results.add(result);
        }
        Double minVal = Collections.min(results);
        // convert values to percentage form
        for (Double r : results) {
            resultsInPercents.add(round((r - minVal) * 100,2));
        }
        LOGGER.info("Trendy for petrol/currency successfully generated");
        return resultsInPercents;
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
    public static String optimalTimeForTrip(String currencySymbol, String fuelType, String country) {
        // list of percentage values of currency rates difference for each month
        List<Double> currencyList = checkCurrencyTrendy(FileReader.loadCurrencyFile(currencySymbol));
        // list of percentage values of petrol rates difference for each month
        List<Double> petrolList = checkFuelTrendy(FileReader.loadPetrolFiles(country), fuelType);
        Double sum = null;
        int numberOfMonthWithOptimalRates = 0;
        String returnStatement = "";
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.US);
        returnStatement += "Optimal time for trip analysis: \n\n";
        // iterate over all months
        for (int i = 0; i < 12; i++) {
            // determine the lowest sum of currency and petrol percentage rates
            if (sum == null || (currencyList.get(i) + petrolList.get(i)) < sum) {
                sum = (currencyList.get(i) + petrolList.get(i));
                numberOfMonthWithOptimalRates = i;
            }
            returnStatement += symbols.getMonths()[i].toUpperCase() + "\n";
            if (currencyList.get(i).equals(0.0)) {
                returnStatement += "Currency --> THE LOWEST \t\t";
            }
            else {
                returnStatement += "Currency --> " + currencyList.get(i) + "% higher \t\t";
            }
            if (petrolList.get(i).equals(0.0)) {
                returnStatement += "Petrol --> THE LOWEST \n";
            }
            else {
                returnStatement += "Petrol --> " + petrolList.get(i) + "% higher. \n";
            }
            returnStatement += "\n";
        }
        returnStatement += "The best time for cheap travel is in: " + symbols.getMonths()[numberOfMonthWithOptimalRates].toUpperCase();
        return returnStatement;
    }

    // print differences in currencies and fuel rates in each month and the best time for cheap travel
    public static String optimalTimeForTrip2(String currencySymbol, String fuelType, String country) {
        Map<Integer, Double> currencyList = calculateMonthPercentageDeviationsForCurrency(FileReader.loadCurrencyFile(currencySymbol));
        List<Double> petrolList = checkFuelTrendy(FileReader.loadPetrolFiles(country), fuelType);
        Double sum = null;
        int numberOfMonthWithOptimalRates = 0;
        String returnStatement = "";
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.US);
        returnStatement += "Optimal time for trip analysis: \n\n";
        for (int i = 0; i < 12; i++) {
            // determine the lowest sum of currency and petrol percentage rates
            if (sum == null || (currencyList.get(i) + petrolList.get(i)) < sum) {
                sum = (currencyList.get(i) + petrolList.get(i));
                numberOfMonthWithOptimalRates = i;
            }
            returnStatement += symbols.getMonths()[i].toUpperCase() + "\n";
            if (currencyList.get(i).equals(0.0)) {
                returnStatement += "Currency --> THE LOWEST \t\t";
            }
            else {
                returnStatement += "Currency --> " + currencyList.get(i) + "% higher \t\t";
            }
            if (petrolList.get(i).equals(0.0)) {
                returnStatement += "Petrol --> THE LOWEST \n";
            }
            else {
                returnStatement += "Petrol --> " + petrolList.get(i) + "% higher. \n";
            }
            returnStatement += "\n";
        }
        returnStatement += "The best time for cheap travel is in: " + symbols.getMonths()[numberOfMonthWithOptimalRates].toUpperCase();
        return returnStatement;
    }

}
