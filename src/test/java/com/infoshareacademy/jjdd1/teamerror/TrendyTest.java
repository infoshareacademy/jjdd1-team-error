package com.infoshareacademy.jjdd1.teamerror;

import com.google.common.collect.ImmutableMap;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.CurrencyHistoryDayValue;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.PetrolPrices;
import com.infoshareacademy.jjdd1.teamerror.file_loader.CurrencyFileFilter;
import com.infoshareacademy.jjdd1.teamerror.file_loader.OnDemandFilesContent;
import com.infoshareacademy.jjdd1.teamerror.file_loader.PetrolFileFilter;
import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by sebas on 17.04.2017.
 */

public class TrendyTest {

    public static final int LIST_OF_MONTHS = 12;
    public static final int FIRST_MONTH = 1;
    public static final int FIRST_DAY_OF_MONTH = 1;
    public static final int EXEMPLARY_YEAR = 2016;

    private Trendy trendy = new Trendy(new PetrolFileFilter(new OnDemandFilesContent()),
            new CurrencyFileFilter(new OnDemandFilesContent()));

    private static List<CurrencyHistoryDayValue> listOfCurrencyObjects = new ArrayList<>();
    private static List<PetrolPrices> listOfPetrolObjects = new ArrayList();
    private static Map<Integer, Double> expectedResultsForCurrencyData = new LinkedHashMap<>();
    private static Map<Integer, Double> expectedResultsForPetrolData = new LinkedHashMap<>();


    @BeforeClass
    public static void setupCurrencyHistoryDayValue() {

    }

    public void createCurrencyDataFor5Years() {

        for (int year = EXEMPLARY_YEAR; year <= EXEMPLARY_YEAR + 5; year++) {
            BigDecimal initialValue = BigDecimal.valueOf(4.0), increment = BigDecimal.valueOf(0.02),
                    initialResultValue = BigDecimal.valueOf(0.0), resultIncrement = BigDecimal.valueOf(0.5);
            for (int month = FIRST_MONTH; month <= LIST_OF_MONTHS; month++) {
                CurrencyHistoryDayValue dayObject = new CurrencyHistoryDayValue();
                dayObject.setDate(LocalDate.of(year, month, FIRST_DAY_OF_MONTH));
                dayObject.setClose(initialValue.doubleValue());
                listOfCurrencyObjects.add(dayObject);
                expectedResultsForCurrencyData.put(month - 1, initialResultValue.doubleValue());
                initialValue = initialValue.add(increment);
                initialResultValue = initialResultValue.add(resultIncrement);
            }
        }
    }

    public void createPetrolDataFor5Years() {
        for (int year = EXEMPLARY_YEAR; year <= EXEMPLARY_YEAR + 5; year++) {
            double initialValue = 4.0, increment = 0.02, initialResultValue = 0.0, resultIncrement = 0.5;
            for (int month = FIRST_MONTH; month <= LIST_OF_MONTHS; month++) {
                PetrolPrices dayObject = new PetrolPrices();
                dayObject.setDate(LocalDate.of(year, month, FIRST_DAY_OF_MONTH));
                dayObject.setDieselPrice(initialValue);
                listOfPetrolObjects.add(dayObject);
                expectedResultsForPetrolData.put(month - 1, initialResultValue);
                initialValue += increment;
                initialResultValue += resultIncrement;
            }
        }
    }

    @Before
    public void setupCurrencyData() throws Exception {
        createCurrencyDataFor5Years();
    }

    @After
    public void setOff() throws Exception{
        listOfCurrencyObjects.clear();
        expectedResultsForCurrencyData.clear();
    }

    @Test
    public void CURRENCY_TEST_for_empty_parameter_should_return_empty_map() throws Exception {
        //given
        List<CurrencyHistoryDayValue> emptyList = new ArrayList<>();
        Map<Integer, Double> emptyMap = new HashMap<>();

        //when
        Map<Integer, Double> result = trendy.calculateMonthPercentageDeviationsForCurrency(emptyList);

        //then
        assertThat(emptyMap, equalTo(result));
    }

    @Test
    public void CURRENCY_TEST_should_return_percentage_deviation_for_first_month() {
        //given
        List<CurrencyHistoryDayValue> listOfOneObject = new ArrayList<>();
        CurrencyHistoryDayValue dayCurrencyObject = new CurrencyHistoryDayValue();
        dayCurrencyObject.setDate(LocalDate.of(2016, 1, 1));
        dayCurrencyObject.setClose(4.0);
        listOfOneObject.add(dayCurrencyObject);
        Map expectedResult = ImmutableMap.of(0, 0.0);

        //when
        Map<Integer, Double> result = trendy.calculateMonthPercentageDeviationsForCurrency(listOfOneObject);

        //then
        assertThat(expectedResult, equalTo(result));
    }

    @Test
    public void CURRENCY_TEST_should_return_percentage_deviations_for_all_months() {
        //given
        List<CurrencyHistoryDayValue> listOfObjectsOf12Months = listOfCurrencyObjects.stream()
                .filter(s -> (s.getDate().getYear() == EXEMPLARY_YEAR))
                .collect(Collectors.toList());

        Map<Integer, Double> expectedResult = expectedResultsForCurrencyData;

        //when
        Map<Integer, Double> result = trendy.calculateMonthPercentageDeviationsForCurrency(listOfObjectsOf12Months);

        //then
        assertThat(expectedResult, equalTo(result));
    }

    @Test
    public void CURRENCY_TEST_should_return_percentage_deviations_for_6_months() {
        //given
        List<CurrencyHistoryDayValue> listOfObjectsOf6Months = listOfCurrencyObjects.stream()
                .filter(s -> (s.getDate().getYear() == EXEMPLARY_YEAR && s.getDate().getMonthValue() % 2 == 1))
                .collect(Collectors.toList());

        Map<Integer, Double> expectedResult = expectedResultsForCurrencyData.entrySet().stream()
                .filter(s -> s.getKey() % 2 == 0)
                .collect(Collectors.toMap(k -> k.getKey(), Map.Entry::getValue));

        //when
        Map<Integer, Double> result = trendy.calculateMonthPercentageDeviationsForCurrency(listOfObjectsOf6Months);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    public void CURRENCY_TEST_should_return_percentage_deviations_for_all_months_in_5_years() {

        //given
        List<CurrencyHistoryDayValue> listOfObjectsOf5Years = listOfCurrencyObjects;

        Map<Integer, Double> expectedResult = expectedResultsForCurrencyData;

        //when
        Map<Integer, Double> result = trendy.calculateMonthPercentageDeviationsForCurrency(listOfObjectsOf5Years);

        //then
        assertEquals(expectedResult, result);
    }

    @Before
    public void setupPetrolData() throws Exception {

        createPetrolDataFor5Years();
    }

    @Test
    public void PETROL_TEST_for_empty_parameter_should_return_empty_map() throws Exception {
        //given
        List<PetrolPrices> emptyList = new ArrayList<>();
        Map<Integer, Double> emptyMap = new HashMap<>();

        //when
        Map<Integer, Double> result = trendy.calculateMonthPercentageDeviationsForPetrol(emptyList, "diesel");

        //then
        assertThat(emptyMap, equalTo(result));
    }

    @Test
    public void PETROL_TEST_should_return_percentage_deviation_for_first_month() {
        //given
        List<PetrolPrices> listOfOneObject = new ArrayList<>();
        PetrolPrices dayPetrolObject = new PetrolPrices();
        dayPetrolObject.setDate(LocalDate.of(2016, 1, 1));
        dayPetrolObject.setDieselPrice(4.0);
        listOfOneObject.add(dayPetrolObject);
        Map expectedResult = ImmutableMap.of(0, 0.0);

        //when
        Map<Integer, Double> result = trendy.calculateMonthPercentageDeviationsForPetrol(listOfOneObject, "diesel");

        //then
        assertThat(expectedResult, equalTo(result));
    }

    @Test
    public void PETROL_TEST_should_return_percentage_deviations_for_all_months() {
        //given
        List<PetrolPrices> listOfObjectsOf12Months = listOfPetrolObjects.stream()
                .filter(s -> (s.getDate().getYear() == EXEMPLARY_YEAR))
                .collect(Collectors.toList());

        Map<Integer, Double> expectedResult = expectedResultsForCurrencyData;

        //when
        Map<Integer, Double> result = trendy.calculateMonthPercentageDeviationsForPetrol(listOfObjectsOf12Months, "diesel");

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    public void PETROL_TEST_should_return_percentage_deviations_for_6_months() {
            //given
            List<PetrolPrices> listOfObjectsOf6Months = listOfPetrolObjects.stream()
                    .filter(s -> (s.getDate().getYear() == EXEMPLARY_YEAR && s.getDate().getMonthValue() % 2 == 1))
                    .collect(Collectors.toList());

            Map<Integer, Double> expectedResult = expectedResultsForPetrolData.entrySet().stream()
                    .filter(s -> s.getKey() % 2 == 0)
                    .collect(Collectors.toMap(k -> k.getKey(), Map.Entry::getValue));

            //when
            Map<Integer, Double> result = trendy.calculateMonthPercentageDeviationsForPetrol(listOfObjectsOf6Months, "diesel");

            //then
            assertEquals(expectedResult, result);
        }

    @Test
    public void PETROL_PRICES_should_return_percentage_deviations_for_all_months_in_5_years() {

        //given
        List<PetrolPrices> listOfObjectsOf5Years = listOfPetrolObjects;

        Map<Integer, Double> expectedResult = expectedResultsForCurrencyData;

        //when
        Map<Integer, Double> result = trendy.calculateMonthPercentageDeviationsForPetrol(listOfObjectsOf5Years, "diesel");

        //then
        assertEquals(expectedResult, result);
    }
}
