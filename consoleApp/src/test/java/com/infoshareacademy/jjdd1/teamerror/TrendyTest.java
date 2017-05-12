package com.infoshareacademy.jjdd1.teamerror;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by sebas on 17.04.2017.
 */

public class TrendyTest {

//    private static final int LIST_OF_MONTHS = 12;
//    private static final int FIRST_MONTH = 1;
//    private static final int FIRST_DAY_OF_MONTH = 1;
//    private static final int EXEMPLARY_YEAR = 2016;
//
////    private Trendy trendy = new Trendy(new PetrolFileFilter(new OnDemandFilesContent()),
////            new CurrencyFileFilter(new OnDemandFilesContent()));
//    private Trendy trendy = new Trendy();
//
//
//
//    private static List<RatesInfo> listOfCurrencyObjects = new ArrayList<>();
//    private static List<RatesInfo> listOfPetrolObjects = new ArrayList<>();
//    private static Map<Integer, Double> expectedResultsForCurrencyData = new LinkedHashMap<>();
//    private static Map<Integer, Double> expectedResultsForPetrolData = new LinkedHashMap<>();
//
//
//    @BeforeClass
//    public static void setupCurrencyHistoryDayValue() {
//
//    }
//
//    private void createCurrencyDataFor5Years() {
//
//        for (int year = EXEMPLARY_YEAR; year <= EXEMPLARY_YEAR + 5; year++) {
//            BigDecimal initialValue = BigDecimal.valueOf(4.0), increment = BigDecimal.valueOf(0.02),
//                    initialResultValue = BigDecimal.valueOf(0.0), resultIncrement = BigDecimal.valueOf(0.5);
//            for (int month = FIRST_MONTH; month <= LIST_OF_MONTHS; month++) {
//                CurrencyRates dayObject = new CurrencyRates();
//                dayObject.setDate(LocalDate.of(year, month, FIRST_DAY_OF_MONTH));
//                dayObject.setRate(initialValue.doubleValue());
//                listOfCurrencyObjects.add(dayObject);
//                expectedResultsForCurrencyData.put(month - 1, initialResultValue.doubleValue());
//                initialValue = initialValue.add(increment);
//                initialResultValue = initialResultValue.add(resultIncrement);
//            }
//        }
//    }
//
//    private void createPetrolDataFor5Years() {
//        for (int year = EXEMPLARY_YEAR; year <= EXEMPLARY_YEAR + 5; year++) {
//            double initialValue = 4.0, increment = 0.02, initialResultValue = 0.0, resultIncrement = 0.5;
//            for (int month = FIRST_MONTH; month <= LIST_OF_MONTHS; month++) {
//                PetrolRates dayObject = new PetrolRates();
//                dayObject.setDate(LocalDate.of(year, month, FIRST_DAY_OF_MONTH));
//                dayObject.setRate(initialValue);
//                listOfPetrolObjects.add(dayObject);
//                expectedResultsForPetrolData.put(month - 1, initialResultValue);
//                initialValue += increment;
//                initialResultValue += resultIncrement;
//            }
//        }
//    }
//
//    @Before
//    public void setupCurrencyData() throws Exception {
//        createCurrencyDataFor5Years();
//    }
//
//    @After
//    public void setOff() throws Exception{
//        listOfCurrencyObjects.clear();
//        expectedResultsForCurrencyData.clear();
//    }
//
//    @Test
//    public void CURRENCY_TEST_for_empty_parameter_should_return_empty_map() throws Exception {
//        //given
//        List<RatesInfo> emptyList = new ArrayList<>();
//        Map<Integer, Double> emptyMap = new HashMap<>();
//
//        //when
//        Map<LocalDate, Double> result = trendy.calculateMonthPercentageDeviations(emptyList);
//
//        //then
//        assertThat(emptyMap, equalTo(result));
//    }
//
//    @Test
//    public void CURRENCY_TEST_should_return_percentage_deviation_for_first_month() {
//        //given
//        List<RatesInfo> listOfOneObject = new ArrayList<>();
//        CurrencyRates dayCurrencyObject = new CurrencyRates();
//        dayCurrencyObject.setDate(LocalDate.of(2016, 1, 1));
//        dayCurrencyObject.setRate(4.0);
//        listOfOneObject.add(dayCurrencyObject);
//        Map expectedResult = ImmutableMap.of(0, 0.0);
//
//        //when
//        Map<LocalDate, Double> result = trendy.calculateMonthPercentageDeviations(listOfOneObject);
//
//        //then
//        assertThat(expectedResult, equalTo(result));
//    }
//
//    @Test
//    public void CURRENCY_TEST_should_return_percentage_deviations_for_all_months() {
//        //given
//        List<RatesInfo> listOfObjectsOf12Months = listOfCurrencyObjects.stream()
//                .filter(s -> (s.getDate().getYear() == EXEMPLARY_YEAR))
//                .collect(Collectors.toList());
//
//        Map<Integer, Double> expectedResult = expectedResultsForCurrencyData;
//
//        //when
//        Map<LocalDate, Double> result = trendy.calculateMonthPercentageDeviations(listOfObjectsOf12Months);
//
//        //then
//        assertThat(expectedResult, equalTo(result));
//    }
//
//    @Test
//    public void CURRENCY_TEST_should_return_percentage_deviations_for_6_months() {
//        //given
//        List<RatesInfo> listOfObjectsOf6Months = listOfCurrencyObjects.stream()
//                .filter(s -> (s.getDate().getYear() == EXEMPLARY_YEAR && s.getDate().getMonthValue() % 2 == 1))
//                .collect(Collectors.toList());
//
//        Map<Integer, Double> expectedResult = expectedResultsForCurrencyData.entrySet().stream()
//                .filter(s -> s.getKey() % 2 == 0)
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//
//        //when
//        Map<LocalDate, Double> result = trendy.calculateMonthPercentageDeviations(listOfObjectsOf6Months);
//
//        //then
//        assertEquals(expectedResult, result);
//    }
//
//    @Test
//    public void CURRENCY_TEST_should_return_percentage_deviations_for_all_months_in_5_years() {
//
//        //given
//        List<RatesInfo> listOfObjectsOf5Years = listOfCurrencyObjects;
//
//        Map<Integer, Double> expectedResult = expectedResultsForCurrencyData;
//
//        //when
//        Map<LocalDate, Double> result = trendy.calculateMonthPercentageDeviations(listOfObjectsOf5Years);
//
//        //then
//        assertEquals(expectedResult, result);
//    }
//
//    @Before
//    public void setupPetrolData() throws Exception {
//
//        createPetrolDataFor5Years();
//    }
//
//    @Test
//    public void PETROL_TEST_for_empty_parameter_should_return_empty_map() throws Exception {
//        //given
//        List<RatesInfo> emptyList = new ArrayList<>();
//        Map<Integer, Double> emptyMap = new HashMap<>();
//
//        //when
//        Map<LocalDate, Double> result = trendy.calculateMonthPercentageDeviations(emptyList);
//
//        //then
//        assertThat(emptyMap, equalTo(result));
//    }
//
//    @Test
//    public void PETROL_TEST_should_return_percentage_deviation_for_first_month() {
//        //given
//        List<RatesInfo> listOfOneObject = new ArrayList<>();
//        PetrolRates dayPetrolObject = new PetrolRates();
//        dayPetrolObject.setDate(LocalDate.of(2016, 1, 1));
//        dayPetrolObject.setRate(4.0);
//        listOfOneObject.add(dayPetrolObject);
//        Map expectedResult = ImmutableMap.of(0, 0.0);
//
//        //when
//        Map<LocalDate, Double> result = trendy.calculateMonthPercentageDeviations(listOfOneObject);
//
//        //then
//        assertThat(expectedResult, equalTo(result));
//    }
//
//    @Test
//    public void PETROL_TEST_should_return_percentage_deviations_for_all_months() {
//        //given
//        List<RatesInfo> listOfObjectsOf12Months = listOfPetrolObjects.stream()
//                .filter(s -> (s.getDate().getYear() == EXEMPLARY_YEAR))
//                .collect(Collectors.toList());
//
//        Map<Integer, Double> expectedResult = expectedResultsForCurrencyData;
//
//        //when
//        Map<LocalDate, Double> result = trendy.calculateMonthPercentageDeviations(listOfObjectsOf12Months);
//
//        //then
//        assertEquals(expectedResult, result);
//    }
//
//    @Test
//    public void PETROL_TEST_should_return_percentage_deviations_for_6_months() {
//            //given
//            List<RatesInfo> listOfObjectsOf6Months = listOfPetrolObjects.stream()
//                    .filter(s -> (s.getDate().getYear() == EXEMPLARY_YEAR && s.getDate().getMonthValue() % 2 == 1))
//                    .collect(Collectors.toList());
//
//            Map<Integer, Double> expectedResult = expectedResultsForPetrolData.entrySet().stream()
//                    .filter(s -> s.getKey() % 2 == 0)
//                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//
//            //when
//            Map<LocalDate, Double> result = trendy.calculateMonthPercentageDeviations(listOfObjectsOf6Months);
//
//            //then
//            assertEquals(expectedResult, result);
//        }
//
//    @Test
//    public void PETROL_PRICES_should_return_percentage_deviations_for_all_months_in_5_years() {
//
//        //given
//        List<RatesInfo> listOfObjectsOf5Years = listOfPetrolObjects;
//
//        Map<Integer, Double> expectedResult = expectedResultsForCurrencyData;
//
//        //when
//        Map<LocalDate, Double> result = trendy.calculateMonthPercentageDeviations(listOfObjectsOf5Years);
//        //then
//        assertEquals(expectedResult, result);
//    }
}
