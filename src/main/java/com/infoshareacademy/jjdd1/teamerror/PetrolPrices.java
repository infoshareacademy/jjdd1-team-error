package com.infoshareacademy.jjdd1.teamerror;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by krystianskrzyszewski on 06.04.17.
 */
public class PetrolPrices {
    String countryName;
    LocalDate year;
    LocalDate month;
    String currencyCode;
    double gasolinePrice, dieselPrice;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public LocalDate getMonth() {
        return month;
    }

    public void setMonth(LocalDate month) {
        this.month = month;
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

    @Override
    public String toString() {
        return "PetrolPrices{" +
                "countryName='" + countryName + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", currencyCode='" + currencyCode + '\'' +
                ", gasolinePrice=" + gasolinePrice +
                ", dieselPrice=" + dieselPrice +
                '}';
    }

}
