package com.infoshareacademy.jjdd1.teamerror;

/**
 * Created by samulilaine-zamojski on 04.04.17.
 */
public class FullCost {

    public static double calculatePrice(double distance, double fuelUsage, double fuelPrice, double currencyRate){

        return distance * (fuelUsage/100) * fuelPrice * currencyRate;

    }


}

