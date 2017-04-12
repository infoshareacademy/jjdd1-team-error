package com.infoshareacademy.jjdd1.teamerror;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

/**
 * Created by krystianskrzyszewski on 05.04.17.
 */
public class CurrencyHistoryDayValue {
    String name;
    LocalDate date;
    double open, high, low, close, volume;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "CurrencyHistoryDayValue{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                '}';
    }


    public LocalDate getBeginOfMonth() {
        return getDate().withDayOfMonth(1);
    }

}
