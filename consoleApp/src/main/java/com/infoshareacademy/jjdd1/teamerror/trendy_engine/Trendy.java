package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import com.infoshareacademy.jjdd1.teamerror.HelpfulMethods;
import com.infoshareacademy.jjdd1.teamerror.TripFullCost;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.RatesInfo;
import com.infoshareacademy.jjdd1.teamerror.file_loader.CurrencyFileFilter;
import com.infoshareacademy.jjdd1.teamerror.file_loader.PetrolFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
    private static final int ANY_YEAR_WHERE_FEBRUARY_HAS_29_DAYS = 1904;

    private String conclusion = "";
    private Map<LocalDate, Double> petrolTrendy = new LinkedHashMap<>();
    private Map<LocalDate, Double> currencyDayTrendy = new LinkedHashMap<>();

    private PetrolFileFilter petrolFileFilter;
    private CurrencyFileFilter currencyFileFilter;
    private TripFullCost tripFullCost = new TripFullCost();
    private LocalDate trendyPeriodFrom = LocalDate.now();
    private LocalDate trendyPeriodTill = LocalDate.now().plusDays(60);
    private Integer tripLength = 7;
    private Set<Integer> startingDays = new TreeSet<>(Collections.singleton(6));

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

    public LocalDate getTrendyPeriodFrom() {
        return trendyPeriodFrom;
    }

    public void setTrendyPeriodFrom(String trendyPeriodFrom) {
        try {
            if(trendyPeriodFrom.length()==8){
                int integerCheck = Integer.parseInt(trendyPeriodFrom);
                LocalDate date = LocalDate.parse(trendyPeriodFrom, DateTimeFormatter.ofPattern("yyyyMMdd"));
                if(date.toString().substring(8).equals(trendyPeriodFrom.substring(6))){
                    this.trendyPeriodFrom = date;
                }
                else{
                    LOGGER.error("No such day exists");
                }
            }
            else{
                LOGGER.error("Wrong date format");
            }
        }
        catch (NumberFormatException e){
            LOGGER.error("Input contains letters");
        }
        catch( Exception e ) {
            LOGGER.error("No such year/month/day exists");
        }
    }

    public LocalDate getTrendyPeriodTill() {
        return trendyPeriodTill;
    }

    public void setTrendyPeriodTill(String trendyPeriodTill) {
        try {
            if(trendyPeriodTill.length()==8){
                int integerCheck = Integer.parseInt(trendyPeriodTill);
                LocalDate date = LocalDate.parse(trendyPeriodTill, DateTimeFormatter.ofPattern("yyyyMMdd"));
                if(date.toString().substring(8).equals(trendyPeriodTill.substring(6))){
                    this.trendyPeriodTill = date;
                }
                else{
                    LOGGER.error("No such day exists");
                }
            }
            else{
                LOGGER.error("Wrong date format");
            }
        }
        catch (NumberFormatException e){
            LOGGER.error("Input contains letters");
        }
        catch( Exception e ) {
            LOGGER.error("No such year/month/day exists");
        }

        LocalDate date = LocalDate.parse(trendyPeriodTill, DateTimeFormatter.ofPattern("yyyyMMdd"));
        if(date.toString().substring(8).equals(trendyPeriodTill.substring(6))){
            this.trendyPeriodTill = date;
        }
    }

    public Integer getTripLength() {
        return tripLength;
    }

    public void setTripLength(String tripLength) {
        this.tripLength = Integer.valueOf(tripLength);
    }

    public String getConclusion() {
        return conclusion;
    }

    public Map<String, String> getPetrolTrendy() {
        return transformToStringValues(petrolTrendy);
    }

    public Map<String, String> getCurrencyDayTrendy() {
        return transformToStringValues(currencyDayTrendy);
    }

    private Map<String, String> transformToStringValues(Map<LocalDate, Double> input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM YYYY");
        return input.entrySet().stream()
                .collect(Collectors.toMap(key -> key.getKey().format(formatter), value -> value.getValue().toString()));
    }

    public Set<String> getStartingDaysString() {
        String[] weekDays = DateFormatSymbols.getInstance().getWeekdays();
        System.out.println(weekDays);
        return startingDays.stream()
                .map(day -> weekDays[day])
                .collect(Collectors.toSet());
    }

    public void setStartingDays(Set<String> startingDays) {
        this.startingDays = startingDays.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toSet());
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
                            .withYear(ANY_YEAR_WHERE_FEBRUARY_HAS_29_DAYS), new MonthValuesForAllYears());
                    monthValuesForAllYearsList.get(date.withDayOfMonth(FIRST_DAY_OF_MONTH)
                            .withYear(ANY_YEAR_WHERE_FEBRUARY_HAS_29_DAYS)).setMonthDeviation(value);
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
            LocalDate dayOfYear = key1.withYear(ANY_YEAR_WHERE_FEBRUARY_HAS_29_DAYS);
            dayValuesByDay.putIfAbsent(dayOfYear, new DayValuesForAllYearsGroupedByDay(dayOfYear));
            dayValuesByDay.get(dayOfYear).setDayPercentageDeviations(value1);
        }));


        Map<LocalDate, Double> averageDeviations = dayValuesByDay.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, s -> s.getValue().getAverageDeviation()));

        Double minVal = Collections.min(averageDeviations.values());

        Map<LocalDate, Double> result = new TreeMap<>();
        result.putAll(averageDeviations.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, s -> HelpfulMethods.round(s.getValue() - minVal, DECIMAL_PLACES))));

        return result;
    }

    public Map<LocalDate, List<Double>> getPeriodTrendy() {

        LOGGER.debug("Calculating trendy for parameters - DateFrom: {} DateTill: {} TripLength: {}", trendyPeriodFrom, trendyPeriodTill, tripLength);
        Long daysNumber = trendyPeriodFrom.until(trendyPeriodTill, ChronoUnit.DAYS);
        Map<LocalDate, Double> currencyValuesAvgList = new LinkedHashMap<>();
        Map<LocalDate, Double> petrolValuesAvgList = new LinkedHashMap<>();
        Map<LocalDate, List<Double>> results = new TreeMap<>();
        Map<LocalDate, Double> cheapestAveragesSums = new TreeMap<>();
        for (int i = 0; i < daysNumber - tripLength; i++) {
            LocalDate currentDate = trendyPeriodFrom.plusDays(i);
            List<Double> currencyValues = new ArrayList<>();
            List<Double> petrolValues = new ArrayList<>();

            for (int j = 0; j < tripLength; j++) {
                currentDate.plusDays(j);
                if (currencyDayTrendy.containsKey(currentDate.withYear(ANY_YEAR_WHERE_FEBRUARY_HAS_29_DAYS))) {
                    currencyValues.add(currencyDayTrendy.get(currentDate.withYear(ANY_YEAR_WHERE_FEBRUARY_HAS_29_DAYS)));
                }
                if (petrolTrendy.containsKey(currentDate.withYear(ANY_YEAR_WHERE_FEBRUARY_HAS_29_DAYS))) {
                    petrolValues.add(petrolTrendy.get(currentDate.withYear(ANY_YEAR_WHERE_FEBRUARY_HAS_29_DAYS)));
                }
            }
            if (!currencyValues.isEmpty() && !petrolValues.isEmpty()) {
                Double currencyValuesAvg = HelpfulMethods.average(currencyValues);
                Double petrolValuesAvg = HelpfulMethods.average(petrolValues);

                currentDate.minusDays(tripLength);
                currencyValuesAvgList.put(currentDate, currencyValuesAvg);
                petrolValuesAvgList.put(currentDate, petrolValuesAvg);
            }
        }

        Map<LocalDate, Double> currencyValuesAvgForStartingDays = filtrateByStaringDays(currencyValuesAvgList);
        Map<LocalDate, Double> petrolValuesAvgForStartingDays = filtrateByStaringDays(petrolValuesAvgList);

        Map<LocalDate, Double> currencyValuesAvgListFinal = createDeviations(currencyValuesAvgForStartingDays);
        Map<LocalDate, Double> petrolValuesAvgListFinal = createDeviations(petrolValuesAvgForStartingDays);

        for (Map.Entry tripValue : currencyValuesAvgListFinal.entrySet()) {
            List<Double> values = new ArrayList<>();
            LocalDate currentDate = (LocalDate)tripValue.getKey();
            Double currencyValue = HelpfulMethods.round((Double)tripValue.getValue(), 2);
            Double petrolValue = HelpfulMethods.round(petrolValuesAvgListFinal.getOrDefault(currentDate, 0.0), 2);
            Double currentSumOfAverages = HelpfulMethods.round(currencyValue + petrolValue, 2);
            values.add(currencyValue);
            values.add(petrolValue);
            values.add(currentSumOfAverages);
            results.put(currentDate, values);

            if (cheapestAveragesSums.size() < 3) {
                cheapestAveragesSums.put(currentDate, currentSumOfAverages);
            }
            else if (Collections.max(cheapestAveragesSums.values()) > currentSumOfAverages) {
                LocalDate maxValueDate = null;
                for (Map.Entry sum : cheapestAveragesSums.entrySet()) {
                    if (sum.getValue().equals(Collections.max(cheapestAveragesSums.values()))) {
                        maxValueDate = (LocalDate) sum.getKey();
                    }
                }
                cheapestAveragesSums.remove(maxValueDate);
                cheapestAveragesSums.put(currentDate, currentSumOfAverages);
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM YYYY");
        if (!cheapestAveragesSums.isEmpty()) {
            conclusion = "The cheapest dates for trip are between: \n<br>";
            cheapestAveragesSums.forEach((key, value) -> conclusion += key.format(formatter) +
                    " and " + key.plusDays(tripLength).format(formatter) + "\n<br>");
        }
        else {
            conclusion = "No data for given parameters";
        }
        return results;
    }

    private Map<LocalDate, Double> createDeviations(Map<LocalDate, Double> valuesAvgForStartingDays) {
        Map<LocalDate, Double> valuesAvgListFinal = new TreeMap<>();
        valuesAvgListFinal.putAll(valuesAvgForStartingDays
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, a -> a.getValue() - Collections.min(valuesAvgForStartingDays.values()))));
        return valuesAvgListFinal;
    }

    private Map<LocalDate, Double> filtrateByStaringDays(Map<LocalDate, Double> valuesAvgList) {
        Map<LocalDate, Double> valuesAvgForStartingDays = new TreeMap<>();
        valuesAvgForStartingDays.putAll(valuesAvgList
                .entrySet()
                .stream()
                .filter(dayValue -> startingDays.contains(dayValue.getKey().getDayOfWeek().getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        return valuesAvgForStartingDays;
    }

    // set trendy values for petrol and currency to maps and conclusion
    public void setTrendy() {
        currencyDayTrendy.clear();
        petrolTrendy.clear();
        String currencySymbol = tripFullCost.getCurrency();
        String fuelType = tripFullCost.getFuelType();
        String country = tripFullCost.getCountry();
        Map<LocalDate, Double> currencyDayTrendyList = new TreeMap<>();
        Map<LocalDate, Double> petrolTrendyList = new TreeMap<>();
        List<RatesInfo> currencyDataList = currencyFileFilter.getListOfCurrencyDataObjects(currencySymbol);
        List <RatesInfo> petrolDataList = petrolFileFilter.getListOfPetrolDataObjects(country, fuelType);
        if (!currencyDataList.isEmpty()) {
            currencyDayTrendyList = calculateDayPercentageDeviations(currencyDataList);
        }
        if (!petrolDataList.isEmpty()) {
            petrolTrendyList = calculateMonthPercentageDeviations(petrolDataList);
        }

        for (Map.Entry currencyValue : currencyDayTrendyList.entrySet()) {
            LocalDate date = (LocalDate)currencyValue.getKey();
            LocalDate petrolDate = ((LocalDate) currencyValue.getKey()).withDayOfMonth(FIRST_DAY_OF_MONTH);
            currencyDayTrendy.put(date, (Double)currencyValue.getValue());
            petrolTrendy.put(date, petrolTrendyList.get(petrolDate));
        }
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
            currencyList = calculateMonthPercentageDeviations(currencyDataList);
        }
        if (!petrolDataList.isEmpty()) {
            petrolList = calculateMonthPercentageDeviations(petrolDataList);
        }
        Double sum = null;
        Integer numberOfMonthWithOptimalRates = null;
        StringBuilder returnStatement = new StringBuilder();
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.US);
        returnStatement.append("Optimal time for trip analysis: \n\n");
        for (int i = 1; i <= NUMBER_OF_MONTHS_IN_YEAR; i++) {
            LocalDate currentDate = LocalDate.of(ANY_YEAR_WHERE_FEBRUARY_HAS_29_DAYS, i, FIRST_DAY_OF_MONTH);
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

        setTrendy();

        getPeriodTrendy()
                .forEach((key, value) -> System.out.println(key + " " + value.toString()));
        System.out.println(conclusion);

        return returnStatement.toString();
    }

}
