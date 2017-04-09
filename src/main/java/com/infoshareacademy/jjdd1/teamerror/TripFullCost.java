package com.infoshareacademy.jjdd1.teamerror;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by krystianskrzyszewski on 07.04.17.
 */
public class TripFullCost {

    LocalDate date1, date2;
    String country;
    String currency;
    String fuelType;
    Double distance;

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        if("diesel".equals(fuelType) || "gasoline".equals(fuelType)) {
            this.fuelType = fuelType;
        }
        else
        throw new IllegalArgumentException("Given fuel type is incorrect");
    }

    public LocalDate getDate1() {
        return date1;
    }

    public void setDate1(LocalDate date1) {
        this.date1 = date1;
    }

    public LocalDate getDate2() {
        return date2;
    }

    public void setDate2(LocalDate date2) {
        this.date2 = date2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if ("USA".equals(country) || "Croatia".equals(country) || "France".equals(country) || "Honduras".equals(country)) {
            this.country = country;
        } else
            throw new IllegalArgumentException("Given country is incorrect");
    }

    public String getCurrency() {return currency;}

    public void setCurrency(String currency) {
        if ("USD".equals(currency) || "HRK".equals(currency) || "EUR".equals(currency) || "HNL".equals(currency)) {
            this.currency = currency;
        } else
            throw new IllegalArgumentException("Given currency is incorrect");
    }

    public Double getDistance() {return distance;}

    public void setDistance(Double distance) {this.distance = distance;}

    public TripFullCost(LocalDate date1, LocalDate date2, String country, String currency, String fuelType, Double distance){
        this.date1 = date1;
        this.date2 = date2;
        this.country = country;
        this.currency = currency;
        this.fuelType = fuelType;
        this.distance = distance;
    }

    public TripFullCost() {

    }

    public static double costCount(TripFullCost tripData){
        double currencyPriceDate1=0;
        double currencyPriceDate2=0;
        double fuelPriceDate1=0;
        double fuelPriceDate2=0;
        double days = DAYS.between(tripData.getDate1(), tripData.getDate2());

        List<CurrencyHistoryDayValue> currencyObjectsList = FileReader.loadCurrencyFile(tripData.getCountry());

        for(CurrencyHistoryDayValue i : currencyObjectsList)
            System.out.println(i);
        List<PetrolPrices> petrolObjectsList = FileReader.loadPetrolFiles("iSA-PetrolPrices");

        int iterator1=0;
        int iterator2=0;
        for(CurrencyHistoryDayValue o: currencyObjectsList){
            if(tripData.getDate1().getMonth() == o.getDate().getMonth()){
                currencyPriceDate1 += o.getClose();
                iterator1++;
            }
            if(tripData.getDate2().getMonth() == o.getDate().getMonth()){
                currencyPriceDate2 += o.getClose();
                iterator2++;
            }
        }
        currencyPriceDate1 = currencyPriceDate1/iterator1;
        currencyPriceDate2 = currencyPriceDate2/iterator2;

        System.out.println(currencyPriceDate1 +" "+currencyPriceDate2);

        for(PetrolPrices o: petrolObjectsList){
            if(tripData.getDate1().getYear() == o.getDate().getYear() && tripData.getDate1().getMonth() == o.getDate().getMonth()){
                if(tripData.getFuelType().equals("gasoline")){
                    fuelPriceDate1 = o.getGasolinePrice();
                }
                if(tripData.getFuelType().equals("diesel")){
                    fuelPriceDate1 = o.getDieselPrice();
                }
            }
            if(tripData.getDate2().getYear() == o.getDate().getYear() && tripData.getDate2().getMonth() == o.getDate().getMonth()){
                if(tripData.getFuelType().equals("gasoline")){
                    fuelPriceDate2 = o.getGasolinePrice();
                }
                if(tripData.getFuelType().equals("diesel")){
                    fuelPriceDate2 = o.getDieselPrice();
                }
            }
        }
        System.out.println(fuelPriceDate1 +" "+fuelPriceDate2);

        System.out.println(((currencyPriceDate1+currencyPriceDate2)/2) * ((fuelPriceDate1+fuelPriceDate2)/2) * days);
        return ((currencyPriceDate1+currencyPriceDate2)/2) * ((fuelPriceDate1+fuelPriceDate2)/2) * days;

    }

}
