package com.infoshareacademy.jjdd1.teamerror;

/**
 * Created by samulilaine-zamojski on 05.04.17.
 */
public class FullCost2 {

    public double calculatePriceSecond(double distance, double fuelUsage, double fuelPrice, double currencyRate) {

        return distance * fuelUsage * fuelPrice * currencyRate;
    }
}



