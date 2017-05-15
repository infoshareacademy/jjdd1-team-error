package com.infoshareacademy.jjdd1.teamerror.currency_petrol_data;

import java.time.LocalDate;

/**
 * Created by krystianskrzyszewski on 06.04.17.
 */
public class PetrolRates extends RatesInfo {
    private String countryName;
    private LocalDate date;
    private String currencyCode;
    private String fuelType;
    private Double rate;

    public PetrolRates() {
    }

    public PetrolRates(LocalDate date, Double rate) {
        this.date = date;
        this.rate = rate;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
