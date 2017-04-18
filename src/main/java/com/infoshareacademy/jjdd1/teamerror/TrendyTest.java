package com.infoshareacademy.jjdd1.teamerror;

import com.google.common.collect.ImmutableMap;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.CurrencyHistoryDayValue;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.PetrolPrices;
import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by sebas on 17.04.2017.
 */

public class TrendyTest {

    private static List<CurrencyHistoryDayValue> listTest1;
    private static List<CurrencyHistoryDayValue> listTest2;
    private static List<CurrencyHistoryDayValue> listTest3;
    private static List<CurrencyHistoryDayValue> listTest4;
    private static List<CurrencyHistoryDayValue> listTest5;
    private static List<PetrolPrices> listTest6;
    private static List<PetrolPrices> listTest7;
    private static List<PetrolPrices> listTest8;
    private static List<PetrolPrices> listTest9;
    private static List<PetrolPrices> listTest10;
    private static Map<Integer, Double> expectedResult3;
    private static Map<Integer, Double> expectedResult4;
    private static Map<Integer, Double> expectedResult5;
    private static Map<Integer, Double> expectedResult8;
    private static Map<Integer, Double> expectedResult9;
    private static Map<Integer, Double> expectedResult10;

    @BeforeClass
    public static void setupCurrencyHistoryDayValue() {
        listTest1 = new ArrayList<>();
        listTest2 = new ArrayList<>();
        listTest3 = new ArrayList<>();
        listTest4 = new ArrayList<>();
        listTest5 = new ArrayList<>();
        listTest6 = new ArrayList<>();
        listTest7 = new ArrayList<>();
        listTest8 = new ArrayList<>();
        listTest9 = new ArrayList<>();
        listTest10 = new ArrayList<>();
        expectedResult3 = new LinkedHashMap<>();
        expectedResult4 = new LinkedHashMap<>();
        expectedResult5 = new LinkedHashMap<>();
        expectedResult8 = new LinkedHashMap<>();
        expectedResult9 = new LinkedHashMap<>();
        expectedResult10 = new LinkedHashMap<>();
    }

    @Before
    public void setup() throws Exception {
        Double value = 4.0;
        Double result = 0.0;

        // CURRENCY TRENDY
        // test 2
        CurrencyHistoryDayValue dayCurrencyObject1 = new CurrencyHistoryDayValue();
        dayCurrencyObject1.setDate(LocalDate.of(2016, 1, 1));
        dayCurrencyObject1.setClose(value);
        listTest2.add(dayCurrencyObject1);

        // test 3
        for (int month = 1; month <= 12; month++, value += 0.02, result += 0.5) {
            CurrencyHistoryDayValue dayObject = new CurrencyHistoryDayValue();
            dayObject.setDate(LocalDate.of(2016, month, 1));
            dayObject.setClose(value);
            listTest3.add(dayObject);
            expectedResult3.put(month - 1, result);
        }

        value = 4.0;
        result = 0.0;
        // test 4
        for (int month = 1; month <= 12; month += 2, value += 0.02, result += 0.5) {
            CurrencyHistoryDayValue dayObject = new CurrencyHistoryDayValue();
            dayObject.setDate(LocalDate.of(2016, month, 1));
            dayObject.setClose(value);
            listTest4.add(dayObject);
            expectedResult4.put(month - 1, result);
        }

        // test 5
        for (int year = 2016; year < 2020; year++) {
            value = 4.0;
            result = 0.0;
            for (int month = 1; month <= 12; month++, value += 0.02, result += 0.5) {
                CurrencyHistoryDayValue dayObject = new CurrencyHistoryDayValue();
                dayObject.setDate(LocalDate.of(2016, month, 1));
                dayObject.setClose(value);
                listTest5.add(dayObject);
                expectedResult5.putIfAbsent(month - 1, result);
            }
        }

        value = 4.0;
        result = 0.0;

        // PETROL TRENDY
        // test 7
        PetrolPrices dayPetrolObject1 = new PetrolPrices();
        dayPetrolObject1.setDate(LocalDate.of(2016, 1, 1));
        dayPetrolObject1.setDieselPrice(value);
        listTest7.add(dayPetrolObject1);

        // test 8
        for (int month = 1; month <= 12; month++, value += 0.02, result += 0.5) {
            PetrolPrices dayObject = new PetrolPrices();
            dayObject.setDate(LocalDate.of(2016, month, 1));
            dayObject.setDieselPrice(value);
            listTest8.add(dayObject);
            expectedResult8.put(month - 1, result);
        }

        value = 4.0;
        result = 0.0;
        // test 9
        for (int month = 1; month <= 12; month += 2, value += 0.02, result += 0.5) {
            PetrolPrices dayObject = new PetrolPrices();
            dayObject.setDate(LocalDate.of(2016, month, 1));
            dayObject.setDieselPrice(value);
            listTest9.add(dayObject);
            expectedResult9.put(month - 1, result);
        }

        // test 10
        for (int year = 2016; year < 2020; year++) {
            value = 4.0;
            result = 0.0;
            for (int month = 1; month <= 12; month++, value += 0.02, result += 0.5) {
                PetrolPrices dayObject = new PetrolPrices();
                dayObject.setDate(LocalDate.of(2016, month, 1));
                dayObject.setDieselPrice(value);
                listTest10.add(dayObject);
                expectedResult10.putIfAbsent(month - 1, result);
            }
        }
    }

    @Test // test 1
    public void CURRENCY_TEST_for_empty_parameter_should_return_empty_map() throws Exception {
        assertEquals(new HashMap<>(), Trendy.calculateMonthPercentageDeviationsForCurrency(listTest1));
    }

    @Test // test2
    public void CURRENCY_TEST_should_return_percentage_deviation_for_first_month() {
        assertEquals(ImmutableMap.of(0, 0.0), Trendy.calculateMonthPercentageDeviationsForCurrency(listTest2));
    }

    @Test // test 3
    public void CURRENCY_TEST_should_return_percentage_deviations_for_all_months() {
        assertEquals(expectedResult3,
                Trendy.calculateMonthPercentageDeviationsForCurrency(listTest3));
    }

    @Test // test 4
    public void CURRENCY_TEST_should_return_percentage_deviations_for_6_months() {
        assertEquals(expectedResult4,
                Trendy.calculateMonthPercentageDeviationsForCurrency(listTest4));
    }

    @Test // test 5
    public void CURRENCY_TEST_should_return_percentage_deviations_for_all_months_in_4_years() {
        assertEquals(expectedResult5,
                Trendy.calculateMonthPercentageDeviationsForCurrency(listTest5));
    }

    @Test // test6
    public void PETROL_TEST_for_empty_parameter_should_return_empty_map() throws Exception {
        assertEquals(new HashMap<>(), Trendy.calculateMonthPercentageDeviationsForPetrol(listTest6, "diesel"));
    }

    @Test // test7
    public void PETROL_TEST_should_return_percentage_deviation_for_first_month() {
        assertEquals(ImmutableMap.of(0, 0.0), Trendy.calculateMonthPercentageDeviationsForPetrol(listTest7, "diesel"));
    }

    @Test // test 8
    public void PETROL_TEST_should_return_percentage_deviations_for_all_months() {
        assertEquals(expectedResult8,
                Trendy.calculateMonthPercentageDeviationsForPetrol(listTest8, "diesel"));
    }

    @Test // test 9
    public void PETROL_TEST_should_return_percentage_deviations_for_6_months() {
        assertEquals(expectedResult9,
                Trendy.calculateMonthPercentageDeviationsForPetrol(listTest9, "diesel"));
    }

    @Test // test 10
    public void should_return_percentage_deviations_for_all_months_in_4_years() {
        assertEquals(expectedResult10,
                Trendy.calculateMonthPercentageDeviationsForPetrol(listTest10, "diesel"));
    }
}
