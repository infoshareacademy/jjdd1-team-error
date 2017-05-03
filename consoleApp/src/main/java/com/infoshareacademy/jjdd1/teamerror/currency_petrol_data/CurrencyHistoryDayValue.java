package com.infoshareacademy.jjdd1.teamerror.currency_petrol_data;

import java.time.LocalDate;

/**
 * Created by krystianskrzyszewski on 05.04.17.
 */
public class CurrencyHistoryDayValue extends RatesInfo {
    private String currencyCode;
    private LocalDate date;
    private String rateType;
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

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
