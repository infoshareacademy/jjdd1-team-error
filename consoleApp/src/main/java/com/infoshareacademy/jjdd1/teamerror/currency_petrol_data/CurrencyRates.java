package com.infoshareacademy.jjdd1.teamerror.currency_petrol_data;

import java.time.LocalDate;

/**
 * Created by krystianskrzyszewski on 05.04.17.
 */
public class CurrencyRates extends RatesInfo {
    private String currencyCode;
    private LocalDate date;
    private Double rate;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
