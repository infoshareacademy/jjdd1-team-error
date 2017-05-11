package com.infoshareacademy.jjdd1.teamerror.currency_petrol_data;

import java.time.LocalDate;

/**
 * Created by sebastian_los on 03.05.17.
 */
public abstract class RatesInfo {
    LocalDate date;
    String currencyCode;
    Double rate;

    public abstract LocalDate getDate();

    public abstract void setDate(LocalDate date);

    public abstract String getCurrencyCode();

    public abstract void setCurrencyCode(String currencyCode);

    public abstract Double getRate();

    public abstract void setRate(Double rate);
}
