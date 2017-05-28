package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import com.infoshareacademy.jjdd1.teamerror.HelpfulMethods;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.RatesInfo;
import com.infoshareacademy.jjdd1.teamerror.file_loader.CountryAndCurrency;
import com.infoshareacademy.jjdd1.teamerror.file_loader.CurrencyFileFilter;
import com.infoshareacademy.jjdd1.teamerror.file_loader.FilesContent;
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
    private static final int MONTHS_NUMBER = 12;
    private static final int YEAR_WITH_29_DAY_FEBRUARY = 1904;
    private static final int DEFAULT_DAYS_NUMBER = 60;
    private static final int DEFAULT_TRIP_LENGTH = 7;
    private static final int DEFAULT_STARTING_DAY = 6;
    private static final int DATE_FORMAT_LENGTH = 8;
    private static final int MAXIMUM_ANALYSIS_PERIOD = 5;

    private String conclusion = "";
    private Map<LocalDate, Double> petrolTrendy = new LinkedHashMap<>();
    private Map<LocalDate, Double> currencyDayTrendy = new LinkedHashMap<>();

    private PetrolFileFilter petrolFileFilter;
    private CurrencyFileFilter currencyFileFilter;
    private CountryAndCurrency countryAndCurrency;
    private String currencySymbol;
    private String country;
    private String fuelType;
    private LocalDate trendyPeriodFrom = LocalDate.now();
    private LocalDate trendyPeriodTill = LocalDate.now().plusDays(DEFAULT_DAYS_NUMBER);

    private Integer tripLength = DEFAULT_TRIP_LENGTH;
    private Set<Integer> startingDays = new TreeSet<>(Collections.singleton(DEFAULT_STARTING_DAY));

    public Trendy() {
    }

    public void setupClass(FilesContent filesContent) {
        petrolFileFilter = new PetrolFileFilter(filesContent);
        currencyFileFilter = new CurrencyFileFilter(filesContent);
        countryAndCurrency = new CountryAndCurrency(filesContent);
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public String getCountry() {
        return country;
    }

    //data check added to standard SET method
    public void setCountry(String country){
        try{
            country = country.toUpperCase();
            if (countryAndCurrency.getCountryAndCurrency().containsKey(country)) {
                this.country = country;
                this.currencySymbol = countryAndCurrency.getCountryAndCurrency().get(country);
                LOGGER.info("{} and {} chosen", country, currencySymbol);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            LOGGER.warn("Country [{}] is not accepted --> {}", country, e);
        }
    }

    public String getFuelType() {
        return fuelType;
    }

    //data check added to standard SET method
    public void setFuelType(String fuelNumber) {
        try{
            int number = Integer.parseInt(fuelNumber);
            switch(number){
                case 1:
                    this.fuelType = "diesel";
                    LOGGER.info("[{}] chosen", fuelType);
                    break;
                case 2:
                    this.fuelType = "gasoline";
                    LOGGER.info("[{}] chosen", fuelType);
                    break;
                default:
                    LOGGER.warn("[{}] fuel type is incorrect", fuelNumber);
                    break;
            }
        }catch (Exception e) {
            LOGGER.warn("[{}] is not a number", fuelNumber);
        }
    }

    public LocalDate getTrendyPeriodFrom() {
        return trendyPeriodFrom;
    }

    public void setTrendyPeriodFrom(String trendyPeriodFrom) {
        try {
            if(trendyPeriodFrom.length()== DATE_FORMAT_LENGTH){
                LocalDate date = LocalDate.parse(trendyPeriodFrom, DateTimeFormatter.ofPattern("yyyyMMdd"));
                if(date.toString().substring(8).equals(trendyPeriodFrom.substring(6))){
                    if(ChronoUnit.YEARS.between(LocalDate.now(), date) < MAXIMUM_ANALYSIS_PERIOD) {
                        this.trendyPeriodFrom = date;
                        LOGGER.info("Period date from chosen: {}", date);
                    }
                    else {
                        LOGGER.warn("Too long period chosen");
                    }
                }
                else{
                    LOGGER.warn("No such day exists");
                }
            }
            else{
                LOGGER.warn("Wrong date format");
            }
        }
        catch (NumberFormatException e){
            LOGGER.warn("Input contains letters");
        }
        catch( Exception e ) {
            LOGGER.warn("No such year/month/day exists");
        }
    }

    public LocalDate getTrendyPeriodTill() {
        return trendyPeriodTill;
    }

    public void setTrendyPeriodTill(String trendyPeriodTill) {
        try {
            if(trendyPeriodTill.length()==8){
                LocalDate date = LocalDate.parse(trendyPeriodTill, DateTimeFormatter.ofPattern("yyyyMMdd"));
                if(date.toString().substring(8).equals(trendyPeriodTill.substring(6))){
                    if(ChronoUnit.YEARS.between(LocalDate.now(), date) < MAXIMUM_ANALYSIS_PERIOD) {
                        this.trendyPeriodTill = date;
                        LOGGER.info("Period date till chosen: {}", date);
                    }
                    else {
                        LOGGER.warn("Too long period chosen");
                    }
                }
                else{
                    LOGGER.warn("No such day exists");
                }
            }
            else{
                LOGGER.warn("Wrong date format");
            }
        }
        catch (NumberFormatException e){
            LOGGER.warn("Input contains letters");
        }
        catch( Exception e ) {
            LOGGER.warn("No such year/month/day exists");
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

    public Set<String> getStartingDaysString() {
        String[] weekDays = DateFormatSymbols.getInstance().getWeekdays();
        if (startingDays.contains(7)) {
            startingDays.remove(7);
            startingDays.add(0);
        }
        return startingDays.stream()
                .map(day -> weekDays[++day])
                .collect(Collectors.toSet());
    }

    public void setStartingDays(Set<String> startingDays) {
        this.startingDays = startingDays.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toSet());
    }

    public Map<LocalDate, List<Double>> getPeriodTrendy() {
        LOGGER.debug("Getting in getPeriodTrendy method, parameters - DateFrom: {} DateTill: {} TripLength: {} " +
                        "startingDays: {}", trendyPeriodFrom, trendyPeriodTill, tripLength, getStartingDaysString());
        setTrendy();
        Map<LocalDate, Double> currencyValuesAvgList = new TreeMap<>();
        Map<LocalDate, Double> petrolValuesAvgList = new TreeMap<>();
        Map<LocalDate, List<Double>> results = new TreeMap<>();
        Map<LocalDate, Double> cheapestAveragesSums = new TreeMap<>();
        determineAvgValues(currencyValuesAvgList, petrolValuesAvgList);

        Map<LocalDate, Double> currencyValuesAvgForStartingDays = filtrateByStartingDays(currencyValuesAvgList);
        Map<LocalDate, Double> petrolValuesAvgForStartingDays = filtrateByStartingDays(petrolValuesAvgList);

        Map<LocalDate, Double> currencyValuesAvgListFinal = HelpfulMethods.minimizeDeviations(currencyValuesAvgForStartingDays);
        Map<LocalDate, Double> petrolValuesAvgListFinal = HelpfulMethods.minimizeDeviations(petrolValuesAvgForStartingDays);

        determineCurrencyAndPetrolValuesSumsAndGenerateResults(results, cheapestAveragesSums, currencyValuesAvgListFinal, petrolValuesAvgListFinal);

        setConclusion(cheapestAveragesSums);
        LOGGER.debug("Getting out of get PeriodTrendy method");
        return results;
    }

    private void setConclusion(Map<LocalDate, Double> cheapestAveragesSums) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM YYYY");
        if (!cheapestAveragesSums.isEmpty()) {
            conclusion = "The cheapest dates for trip are between: \n<br>";
            cheapestAveragesSums.forEach((key, value) -> conclusion += key.format(formatter) +
                    " and " + key.plusDays(tripLength).format(formatter) + "\n<br>");
        }
        else {
            conclusion = "No data for given parameters";
        }
        LOGGER.debug("Trendy conclusion set");
    }

    private void determineCurrencyAndPetrolValuesSumsAndGenerateResults(
            Map<LocalDate, List<Double>> results,
            Map<LocalDate, Double> cheapestAveragesSums,
            Map<LocalDate, Double> currencyValuesAvgListFinal,
            Map<LocalDate, Double> petrolValuesAvgListFinal) {

        for (Map.Entry tripValue : currencyValuesAvgListFinal.entrySet()) {
            List<Double> values = new ArrayList<>();
            LocalDate currentDate = (LocalDate)tripValue.getKey();
            Double currencyValue = HelpfulMethods.round((Double)tripValue.getValue(), 2);
            Double petrolValue = HelpfulMethods.round(
                    petrolValuesAvgListFinal.getOrDefault(currentDate, 0.0), 2);
            Double currentSumOfAverages = HelpfulMethods.round(currencyValue + petrolValue, 2);
            values.add(currencyValue);
            values.add(petrolValue);
            values.add(currentSumOfAverages);
            results.put(currentDate, values);
            updateCheapestAverageSums(cheapestAveragesSums, currentDate, currentSumOfAverages);
        }
        LOGGER.debug("Petrol and currency results generated");
    }

    private void updateCheapestAverageSums(Map<LocalDate, Double> cheapestAveragesSums, LocalDate currentDate, Double currentSumOfAverages) {
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

    private void determineAvgValues(Map<LocalDate, Double> currencyValuesAvgList,
                                    Map<LocalDate, Double> petrolValuesAvgList) {
        LOGGER.debug("Getting in determineAvgValues method");
        Long daysNumber = trendyPeriodFrom.until(trendyPeriodTill, ChronoUnit.DAYS);
        for (int i = 0; i < daysNumber - tripLength; i++) {
            LocalDate currentDate = trendyPeriodFrom.plusDays(i);
            List<Double> currencyValues = new ArrayList<>();
            List<Double> petrolValues = new ArrayList<>();

            for (int j = 0; j < tripLength; j++) {
                currentDate.plusDays(j);
                if (currencyDayTrendy.containsKey(currentDate.withYear(YEAR_WITH_29_DAY_FEBRUARY))) {
                    currencyValues.add(currencyDayTrendy.get(currentDate.withYear(YEAR_WITH_29_DAY_FEBRUARY)));
                }
                if (petrolTrendy.containsKey(currentDate.withYear(YEAR_WITH_29_DAY_FEBRUARY))) {
                    petrolValues.add(petrolTrendy.get(currentDate.withYear(YEAR_WITH_29_DAY_FEBRUARY)));
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
        LOGGER.debug("Getting out of determineAvgValues method");
    }

    private Map<LocalDate, Double> filtrateByStartingDays(Map<LocalDate, Double> valuesAvgList) {
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
        LOGGER.debug("Setting trendy");
        currencyDayTrendy.clear();
        petrolTrendy.clear();
        Map<LocalDate, Double> currencyDayTrendyList = new TreeMap<>();
        Map<LocalDate, Double> petrolTrendyList = new TreeMap<>();
        List<RatesInfo> currencyDataList = currencyFileFilter.getListOfCurrencyDataObjects(currencySymbol);
        List <RatesInfo> petrolDataList = petrolFileFilter.getListOfPetrolDataObjects(country, fuelType);
        if (!currencyDataList.isEmpty()) {
            PercentageDeviations currencyDeviations = new PercentageDeviations(currencyDataList);
            currencyDayTrendyList = currencyDeviations.getDayPercentageDeviations();
        }
        if (!petrolDataList.isEmpty()) {
            PercentageDeviations petrolDeviations = new PercentageDeviations(petrolDataList);
            petrolTrendyList = petrolDeviations.getMonthPercentageDeviations();
        }

        for (Map.Entry currencyValue : currencyDayTrendyList.entrySet()) {
            LocalDate date = (LocalDate)currencyValue.getKey();
            LocalDate petrolDate = ((LocalDate) currencyValue.getKey()).withDayOfMonth(FIRST_DAY_OF_MONTH);
            currencyDayTrendy.put(date, (Double)currencyValue.getValue());
            petrolTrendy.put(date, petrolTrendyList.get(petrolDate));
        }
        LOGGER.debug("Trendy set");
    }


    // print differences in currencies and fuel rates in each month and the best month for cheap travel
    public String getMonthTrendyAsString() {
        LOGGER.debug("Preparing trendy results as string");
        Map<LocalDate, Double> currencyTrendyList = new HashMap<>();
        Map<LocalDate, Double> petrolTrendyList = new HashMap<>();
        List<RatesInfo> currencyDataList = currencyFileFilter.getListOfCurrencyDataObjects(currencySymbol);
        List <RatesInfo> petrolDataList = petrolFileFilter.getListOfPetrolDataObjects(country, fuelType);
        if (!currencyDataList.isEmpty()) {
            PercentageDeviations currencyDeviations = new PercentageDeviations(currencyDataList);
            currencyTrendyList = currencyDeviations.getMonthPercentageDeviations();

        }
        if (!petrolDataList.isEmpty()) {
            PercentageDeviations petrolDeviations = new PercentageDeviations(petrolDataList);
            petrolTrendyList = petrolDeviations.getMonthPercentageDeviations();
        }
        Double sum = null;
        Integer numberOfMonthWithOptimalRates = null;
        StringBuilder returnStatement = new StringBuilder();
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.US);
        returnStatement.append("Optimal time for trip analysis: \n\n");
        for (int i = 1; i <= MONTHS_NUMBER; i++) {
            LocalDate currentDate = LocalDate.of(YEAR_WITH_29_DAY_FEBRUARY, i, FIRST_DAY_OF_MONTH);
            returnStatement.append(symbols.getMonths()[i-1].toUpperCase());
            returnStatement.append("\n");
            if (!currencyTrendyList.containsKey(currentDate)) {
                returnStatement.append( "Currency --> NO DATA \t\t");
            }
            if (currencyTrendyList.containsKey(currentDate) && currencyTrendyList.get(currentDate).equals(0.0)) {
                returnStatement.append("Currency --> THE LOWEST \t\t");
            }
            else if (currencyTrendyList.containsKey(currentDate)) {
                returnStatement.append("Currency --> ");
                returnStatement.append(currencyTrendyList.get(currentDate));
                returnStatement.append("% higher \t\t");
            }
            if (!petrolTrendyList.containsKey(currentDate)) {
                returnStatement.append("Petrol --> NO DATA \n");
            }
            if (petrolTrendyList.containsKey(currentDate) && petrolTrendyList.get(currentDate).equals(0.0)) {
                returnStatement.append("Petrol --> THE LOWEST \n");
            }
            else if (petrolTrendyList.containsKey(currentDate)) {
                returnStatement.append("Petrol --> ");
                returnStatement.append(petrolTrendyList.get(currentDate));
                returnStatement.append("% higher. \n");
            }
            // determine the lowest sum of currency and petrol percentage rates
            if ((currencyTrendyList.containsKey(currentDate) || petrolTrendyList.containsKey(currentDate)) &&
                    (sum == null || (currencyTrendyList.getOrDefault(currentDate, 0.0) +
                            petrolTrendyList.getOrDefault(currentDate, 0.0)) < sum)) {
                sum = (currencyTrendyList.getOrDefault(currentDate, 0.0) +
                        petrolTrendyList.getOrDefault(currentDate, 0.0));
                numberOfMonthWithOptimalRates = i - 1;
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

}
