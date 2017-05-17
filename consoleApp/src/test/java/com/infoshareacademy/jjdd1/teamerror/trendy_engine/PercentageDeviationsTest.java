package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.CurrencyRates;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.PetrolRates;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.RatesInfo;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.Matchers.equalTo;


/**
 * Created by sebastian_los on 14.05.17.
 */
public class PercentageDeviationsTest {

    private static final int RANDOM_YEAR = 2016;
    private static final int RANDOM_MONTH = 10;
    private static final int RANDOM_DAY_OF_MONTH = 11;
    private static final double RANDOM_RATE = 4.39;
    private static final int DEFAULT_YEAR = 1904;
    private static final int FIRST_DAY_OF_MONTH = 1;
    private static final double ZERO_RATE = 0.0;
    private static final int DAYS_IN_YEAR = 366;
    private static final double RATE_INCREMENT = 0.02;
    private static final double EXP_RESULT_INCREMENT = 0.5;
    private static final double RATE_START_VALUE = 4.0;
    private static final double EXP_RATE_START_VALUE = 0.0;
    private static final int FIRST_DAY_OF_YEAR = 1;
    private static final int FIRST_MONTH_OF_YEAR = 1;
    private static final int MONTHS_IN_YEAR = 12;
    private static PercentageDeviations percentageDeviations;



    @Test
    public void dayDeviations_should_return_empty_map_for_empty_ratesList() {

        // given
        percentageDeviations = new PercentageDeviations(Collections.emptyList());

        // when
        Map<LocalDate, Double> result =  percentageDeviations.getDayPercentageDeviations();

        // then
        Assert.assertThat(Collections.emptyMap(), equalTo(result));
    }

    @Test
    public void monthDeviations_should_return_empty_map_for_empty_ratesList() {

        // given
        percentageDeviations = new PercentageDeviations(Collections.emptyList());

        // when
        Map<LocalDate, Double> result =  percentageDeviations.getMonthPercentageDeviations();

        // then
        Assert.assertThat(Collections.emptyMap(), equalTo(result));
    }

    @Test
    public void dayDeviations_should_return_zero_for_single_currency_element_with_rate_zero() {

        // given
        LocalDate randomDate = LocalDate.of(RANDOM_YEAR, RANDOM_MONTH, RANDOM_DAY_OF_MONTH);
        setUpPercentageDeviationsForSingleDay(ZERO_RATE, randomDate, new CurrencyRates());

        // when
        Map<LocalDate, Double> result = percentageDeviations.getDayPercentageDeviations();

        // then
        Assert.assertThat(result.get(randomDate.withYear(DEFAULT_YEAR)), equalTo(0.0));
    }

    @Test
    public void dayDeviations_should_return_zero_for_single_petrol_element_with_rate_zero() {

        // given

        LocalDate randomDate = LocalDate.of(RANDOM_YEAR, RANDOM_MONTH, RANDOM_DAY_OF_MONTH);
        setUpPercentageDeviationsForSingleDay(ZERO_RATE, randomDate, new PetrolRates());

        // when
        Map<LocalDate, Double> result = percentageDeviations.getDayPercentageDeviations();

        // then
        Assert.assertThat(result.get(randomDate.withYear(DEFAULT_YEAR)), equalTo(0.0));
    }

    @Test
    public void monthDeviations_should_return_zero_for_single_currency_element_with_rate_zero() {

        // given
        LocalDate randomDate = LocalDate.of(RANDOM_YEAR, RANDOM_MONTH, RANDOM_DAY_OF_MONTH);
        setUpPercentageDeviationsForSingleDay(ZERO_RATE, randomDate, new CurrencyRates());

        // when
        Double result = percentageDeviations.getMonthPercentageDeviations().get(
                randomDate.withYear(DEFAULT_YEAR).withDayOfMonth(FIRST_DAY_OF_MONTH));

        // then
        Assert.assertThat(result, equalTo(0.0));
    }

    @Test
    public void monthDeviations_should_return_zero_for_single_petrol_element_with_rate_zero() {

        // given
        LocalDate randomDate = LocalDate.of(RANDOM_YEAR, RANDOM_MONTH, RANDOM_DAY_OF_MONTH);
        setUpPercentageDeviationsForSingleDay(ZERO_RATE, randomDate, new PetrolRates());

        // when
        Double result = percentageDeviations.getMonthPercentageDeviations().get(
                randomDate.withYear(DEFAULT_YEAR).withDayOfMonth(FIRST_DAY_OF_MONTH));

        // then
        Assert.assertThat(result, equalTo(0.0));
    }

    @Test
    public void dayDeviations_should_return_zero_for_single_currency_element() {

        // given
        LocalDate randomDate = LocalDate.of(RANDOM_YEAR, RANDOM_MONTH, RANDOM_DAY_OF_MONTH);
        setUpPercentageDeviationsForSingleDay(RANDOM_RATE, randomDate, new CurrencyRates());

        // when
        Map<LocalDate, Double> result = percentageDeviations.getDayPercentageDeviations();

        // then
        Assert.assertThat(result.get(randomDate.withYear(DEFAULT_YEAR)), equalTo(0.0));
    }

    @Test
    public void dayDeviations_should_return_zero_for_single_petrol_element() {

        // given
        LocalDate randomDate = LocalDate.of(RANDOM_YEAR, RANDOM_MONTH, RANDOM_DAY_OF_MONTH);
        setUpPercentageDeviationsForSingleDay(RANDOM_RATE, randomDate, new PetrolRates());

        // when
        Map<LocalDate, Double> result = percentageDeviations.getDayPercentageDeviations();

        // then
        Assert.assertThat(result.get(randomDate.withYear(DEFAULT_YEAR)), equalTo(0.0));
    }

    @Test
    public void monthDeviations_should_return_zero_for_single_currency_element() {

        // given
        LocalDate randomDate = LocalDate.of(RANDOM_YEAR, RANDOM_MONTH, RANDOM_DAY_OF_MONTH);
        setUpPercentageDeviationsForSingleDay(RANDOM_RATE, randomDate, new CurrencyRates());

        // when
        Double result = percentageDeviations.getMonthPercentageDeviations().get(
                randomDate.withYear(DEFAULT_YEAR).withDayOfMonth(FIRST_DAY_OF_MONTH));

        // then
        Assert.assertThat(result, equalTo(0.0));
    }

    @Test
    public void monthDeviations_should_return_zero_for_single_petrol_element() {

        // given
        LocalDate randomDate = LocalDate.of(RANDOM_YEAR, RANDOM_MONTH, RANDOM_DAY_OF_MONTH);
        setUpPercentageDeviationsForSingleDay(RANDOM_RATE, randomDate, new PetrolRates());

        // when
        Double result = percentageDeviations.getMonthPercentageDeviations().get(
                randomDate.withYear(DEFAULT_YEAR).withDayOfMonth(FIRST_DAY_OF_MONTH));

        // then
        Assert.assertThat(result, equalTo(0.0));
    }

    private void setUpPercentageDeviationsForSingleDay(Double rate, LocalDate date, RatesInfo ratesInfo) {
        List<RatesInfo> ratesList = new ArrayList<>();
        ratesInfo.setDate(date);
        ratesInfo.setRate(rate);
        ratesList.add(ratesInfo);
        percentageDeviations = new PercentageDeviations(ratesList);
    }

    @Test
    public void dayDeviations_should_return_proper_values_for_some_days_in_year() {

        // given
        Map<LocalDate, Double> expectedResult = new LinkedHashMap<>();
        Integer dayIncrement = 15;
        setUpDayPercentageDeviationsForYears(expectedResult, dayIncrement);

        // when
        Map<LocalDate, Double> result = percentageDeviations.getDayPercentageDeviations();

        // then
        Assert.assertThat(result, equalTo(expectedResult));
    }

    @Test
    public void dayDeviations_should_return_proper_values_for_all_days_in_year() {

        // given
        Map<LocalDate, Double> expectedResult = new LinkedHashMap<>();
        Integer dayIncrement = 1;
        setUpDayPercentageDeviationsForYears(expectedResult, dayIncrement);

        // when
        Map<LocalDate, Double> result = percentageDeviations.getDayPercentageDeviations();

        // then
        Assert.assertThat(result, equalTo(expectedResult));
    }

    @Test
    public void monthDeviations_should_return_proper_values_for_some_months_in_year() {

        // given
        Map<LocalDate, Double> expectedResult = new LinkedHashMap<>();
        Integer monthIncrement = 3;
        setUpMonthPercentageDeviationsForMultipleYears(expectedResult, monthIncrement);

        // when
        Map<LocalDate, Double> result = percentageDeviations.getMonthPercentageDeviations();

        // then
        Assert.assertThat(result, equalTo(expectedResult));
    }

    @Test
    public void monthDeviations_should_return_proper_values_for_all_months_in_year() {

        // given
        Map<LocalDate, Double> expectedResult = new LinkedHashMap<>();
        Integer monthIncrement = 1;
        setUpMonthPercentageDeviationsForMultipleYears(expectedResult, monthIncrement);

        // when
        Map<LocalDate, Double> result = percentageDeviations.getMonthPercentageDeviations();

        // then
        Assert.assertThat(result, equalTo(expectedResult));
    }

    @Test
    public void dayDeviations_should_return_proper_values_for_all_days_for_multiple_years() {

        // given
        Map<LocalDate, Double> expectedResult = new LinkedHashMap<>();
        Integer dayIncrement = 1;
        setUpDayPercentageDeviationsForYears(expectedResult, dayIncrement, 2013, 2014);

        // when
        Map<LocalDate, Double> result = percentageDeviations.getDayPercentageDeviations();

        // then
        Assert.assertThat(result, equalTo(expectedResult));
    }

    @Test
    public void monthDeviations_should_return_proper_values_for_all_months_for_multiple_years() {

        // given
        Map<LocalDate, Double> expectedResult = new LinkedHashMap<>();
        Integer monthIncrement = 1;
        setUpMonthPercentageDeviationsForMultipleYears(expectedResult, monthIncrement, 2013, 2014);

        // when
        Map<LocalDate, Double> result = percentageDeviations.getMonthPercentageDeviations();

        // then
        Assert.assertThat(result, equalTo(expectedResult));
    }

    private void setUpDayPercentageDeviationsForYears(Map<LocalDate, Double> expectedResult, Integer dayIncrement, Integer...years) {
        List<RatesInfo> ratesList = new ArrayList<>();
        for(Integer year : years) {
            LocalDate date = LocalDate.of(year, RANDOM_MONTH, RANDOM_DAY_OF_MONTH);
            Double rate = RATE_START_VALUE;
            Double expectedRate = EXP_RATE_START_VALUE;
            for (Integer day = FIRST_DAY_OF_YEAR; day < DAYS_IN_YEAR; day += dayIncrement, rate += RATE_INCREMENT,
                    expectedRate += EXP_RESULT_INCREMENT) {
                ratesList.add(new CurrencyRates(date.withDayOfYear(day), rate));
                expectedResult.putIfAbsent(date.withDayOfYear(day).withYear(DEFAULT_YEAR), expectedRate);
            }
        }
        percentageDeviations = new PercentageDeviations(ratesList);
    }

    private void setUpMonthPercentageDeviationsForMultipleYears(Map<LocalDate, Double> expectedResult,
                                                                Integer monthIncrement, Integer...years) {
        List<RatesInfo> ratesList = new ArrayList<>();
        for (Integer year : years) {
            LocalDate date = LocalDate.of(year, RANDOM_MONTH, RANDOM_DAY_OF_MONTH);
            Double rate = RATE_START_VALUE;
            Double expectedRate = EXP_RATE_START_VALUE;
            for (Integer month = FIRST_MONTH_OF_YEAR; month < MONTHS_IN_YEAR; month += monthIncrement, rate += RATE_INCREMENT,
                    expectedRate += EXP_RESULT_INCREMENT) {
                ratesList.add(new CurrencyRates(date.withMonth(month), rate));
                expectedResult.putIfAbsent(LocalDate.of(DEFAULT_YEAR, month, FIRST_DAY_OF_MONTH), expectedRate);
            }
        }
        percentageDeviations = new PercentageDeviations(ratesList);
    }

}