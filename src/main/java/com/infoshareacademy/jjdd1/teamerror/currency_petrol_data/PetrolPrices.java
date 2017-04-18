package com.infoshareacademy.jjdd1.teamerror.currency_petrol_data;

import java.time.LocalDate;

/**
 * Created by krystianskrzyszewski on 06.04.17.
 */
public class PetrolPrices {
    String countryName;
    LocalDate date;
    String currencyCode;
    double gasolinePrice, dieselPrice;

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

    public double getGasolinePrice() {
        return gasolinePrice;
    }

    public void setGasolinePrice(double gasolinePrice) {
        this.gasolinePrice = gasolinePrice;
    }

    public double getDieselPrice() {
        return dieselPrice;
    }

    public void setDieselPrice(double dieselPrice) {
        this.dieselPrice = dieselPrice;
    }

}
