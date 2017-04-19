package com.infoshareacademy.jjdd1.teamerror;

import com.google.common.collect.ImmutableMap;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.CurrencyHistoryDayValue;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.PetrolPrices;
import com.infoshareacademy.jjdd1.teamerror.file_loader.CurrencyFileFilter;
import com.infoshareacademy.jjdd1.teamerror.file_loader.OnDemandFilesContent;
import com.infoshareacademy.jjdd1.teamerror.file_loader.PetrolFileFilter;
import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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

    public void createCurrencyDataFor5Years(Double initialValue, Double increment, Double initialResultValue, Double resultIncrement) {

        for (int year = EXEMPLARY_YEAR; year < year + 5; year++) {
            for (int month = FIRST_MONTH; month <= LIST_OF_MONTHS;
                 month++, initialValue += increment, initialResultValue += resultIncrement) {
                CurrencyHistoryDayValue dayObject = new CurrencyHistoryDayValue();
                dayObject.setDate(LocalDate.of(EXEMPLARY_YEAR, month, FIRST_DAY_OF_MONTH));
                dayObject.setClose(initialValue);
                listOfCurrencyObjects.add(dayObject);
                expectedResultsForCurrencyData.put(month - 1, initialResultValue);
            }
        }
    }

    public void createPetrolDataFor5Years(Double initialValue, Double increment, Double initialResultValue, Double resultIncrement) {
        for (int year = EXEMPLARY_YEAR; year < year + 5; year++) {
            for (int month = FIRST_MONTH; month <= LIST_OF_MONTHS;
                 month++, initialValue += increment, initialResultValue += resultIncrement) {
                PetrolPrices dayObject = new PetrolPrices();
                dayObject.setDate(LocalDate.of(EXEMPLARY_YEAR, month, FIRST_DAY_OF_MONTH));
                dayObject.setDieselPrice(initialValue);
                listOfPetrolObjects.add(dayObject);
                expectedResultsForPetrolData.put(month - 1, initialResultValue);
            }
        }
    }

    @Before
    public void setupCurrencyData() throws Exception {

        createCurrencyDataFor5Years(4.0, 0.02, 0.0, 0.5);
    }

    @Test // test 1
    public void CURRENCY_TEST_for_empty_parameter_should_return_empty_map() throws Exception {

        //given
        Map<Integer, Double> emptyMap = new HashMap<>();

        //when
        Map<Integer, Double> result = trendy.calculateMonthPercentageDeviationsForCurrency(listTest1);

        //then
        assertThat(emptyMap, equalTo(result));
    }

    @Test
    public void CURRENCY_TEST_should_return_percentage_deviation_for_first_month() {

        //given
        List<CurrencyHistoryDayValue> listTest2 = new ArrayList<>();
        CurrencyHistoryDayValue dayCurrencyObject1 = new CurrencyHistoryDayValue();
        dayCurrencyObject1.setDate(LocalDate.of(2016, 1, 1));
        dayCurrencyObject1.setClose(4.0);
        Map expectedResult = ImmutableMap.of(0, 0.0);

        //when
        Map<Integer, Double> result = trendy.calculateMonthPercentageDeviationsForCurrency(listTest2);

        //then
        assertThat(expectedResult, equalTo(result));
    }

    @Test
    public void CURRENCY_TEST_should_return_percentage_deviations_for_all_months() {
        //given

        Map<Integer,Double> listOf12DayObjects = listOfCurrencyObjects.stream()
                .filter(s -> (s.getDate().getYear() == EXEMPLARY_YEAR))
                .collect(Collectors.toMap(s -> (s.getDate())))


        // when

        assertEquals(expectedResult3,
                trendy.calculateMonthPercentageDeviationsForCurrency(listTest3));
    }

    @Test // test 4
    public void CURRENCY_TEST_should_return_percentage_deviations_for_6_months() {
        assertEquals(expectedResult4,
                trendy.calculateMonthPercentageDeviationsForCurrency(listTest4));
    }

    @Test // test 5
    public void CURRENCY_TEST_should_return_percentage_deviations_for_all_months_in_4_years() {
        assertEquals(expectedResult5,
                trendy.calculateMonthPercentageDeviationsForCurrency(listTest5));
    }

    @Before
    public void setupPetrolData() throws Exception {

        createPetrolDataFor5Years(4.0, 0.02, 0.0, 0.5);
    }

    @Test // test6
    public void PETROL_TEST_for_empty_parameter_should_return_empty_map() throws Exception {
        assertEquals(new HashMap<>(), trendy.calculateMonthPercentageDeviationsForPetrol(listTest6, "diesel"));
    }

    @Test // test7
    public void PETROL_TEST_should_return_percentage_deviation_for_first_month() {
        assertEquals(ImmutableMap.of(0, 0.0), trendy.calculateMonthPercentageDeviationsForPetrol(listTest7, "diesel"));
    }

    @Test // test 8
    public void PETROL_TEST_should_return_percentage_deviations_for_all_months() {
        assertEquals(expectedResult8,
                trendy.calculateMonthPercentageDeviationsForPetrol(listTest8, "diesel"));
    }

    @Test // test 9
    public void PETROL_TEST_should_return_percentage_deviations_for_6_months() {
        assertEquals(expectedResult9,
                trendy.calculateMonthPercentageDeviationsForPetrol(listTest9, "diesel"));
    }

    @Test // test 10
    public void should_return_percentage_deviations_for_all_months_in_4_years() {
        assertEquals(expectedResult10,
                trendy.calculateMonthPercentageDeviationsForPetrol(listTest10, "diesel"));
    }
}
