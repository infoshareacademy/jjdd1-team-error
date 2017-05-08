package com.infoshareacademy.jjdd1.teamerror;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CountryStatisticsTest {

    CountryStatistics sut = new CountryStatistics();

    @Test
    public void shouldAddCountryOnFirstTime() throws Exception {
        // given
        assertThat(sut.getStatistics()).isEmpty();

        // when
        sut.selectedCountry("USA");

        // then
        assertThat(sut.getStatistics()).hasSize(1);
        assertThat(sut.getStatistics()).containsKeys("USA");
    }

    @Test
    public void shouldHaveCounterOneForNewCountry() throws Exception {
        // given

        // when
        sut.selectedCountry("USA");

        // then
        assertThat(sut.getStatistics()).containsKey("USA");
        assertThat(sut.getStatistics().get("USA")).isEqualTo(1);

    }

//    @Test
//    public void shouldHaveTwoCountriesInMap() throws Exception {
//        //given
//
//        // when
//
//        sut.selectedCountryOne("USA");
//        sut.selectedCountryTwo("Croatia");
//
//        //then
//        assertThat(sut.getStatistics()).containsKey("USA");
//        assertThat(sut.getStatistics()).containsKey("Croatia");
//        assertThat(sut.getStatistics().get("USA")).isEqualTo(2);
//        assertThat(sut.getStatistics().get("Croatia")).isEqualTo(1);
//
//    }

    @Test
    public void shouldAddCountryToMapAndMakeItFiveTimes() throws Exception {
    //given

        assertThat(sut.getStatistics()).isEmpty();

    //when
        sut.selectedCountry("USA");
        sut.selectedCountry("USA");
        sut.selectedCountry("USA");
        sut.selectedCountry("USA");
        sut.selectedCountry("USA");


    //then
        assertThat(sut.getStatistics()).containsKey("USA");
        assertThat(sut.getStatistics().get("USA")).isEqualTo(5);



    }

    @Test
    public void shouldAddDifferendCountriesAndValues() throws Exception {

        //given

        assertThat(sut.getStatistics()).isEmpty();

        //when
        sut.selectedCountry("USA");
        sut.selectedCountry("France");
        sut.selectedCountry("USA");
        sut.selectedCountry("France");
        sut.selectedCountry("USA");


        //then
        assertThat(sut.getStatistics()).containsKey("USA");
        assertThat(sut.getStatistics().get("USA")).isEqualTo(3);
        assertThat(sut.getStatistics()).containsKey("France");
        assertThat(sut.getStatistics().get("France")).isEqualTo(2);
    }
}