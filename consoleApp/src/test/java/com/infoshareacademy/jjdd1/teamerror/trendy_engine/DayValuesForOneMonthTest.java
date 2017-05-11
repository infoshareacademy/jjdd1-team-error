package com.infoshareacademy.jjdd1.teamerror.trendy_engine;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class DayValuesForOneMonthTest {

    private DayValuesForOneMonth dayValuesForOneMonth;


    @Test
    public void for_empty_object_result_should_be_zero() throws Exception {

        //given
        dayValuesForOneMonth = new DayValuesForOneMonth(LocalDate.of(2016, 12, 01));
        Double expectedResult = 0.0;

        //when
        Double result = dayValuesForOneMonth.getAverageMonthValue();

        //then
        assertThat(expectedResult, equalTo(result));
    }

    @Test
    public void for_one_value_result_should_be_equal_to_this_value() throws Exception {

        //given
        dayValuesForOneMonth = new DayValuesForOneMonth(LocalDate.of(2016, 12, 01));
        dayValuesForOneMonth.setDayValue(10.0);
        Double expectedResult = 10.0;

        //when
        Double result = dayValuesForOneMonth.getAverageMonthValue();

        //then
        assertThat(expectedResult, equalTo(result));
    }

    @Test
    public void for_some_values_should_return_average_value() {

        //given
        dayValuesForOneMonth = new DayValuesForOneMonth(LocalDate.of(2016, 12, 01));
        for (Double i = 0.0; i <= 20; i++ ) {
            dayValuesForOneMonth.setDayValue(i);
        }
        Double expectedResult = 10.0;


        //when
        Double result = dayValuesForOneMonth.getAverageMonthValue();

        //then
        assertThat(expectedResult, equalTo(result));
    }

}