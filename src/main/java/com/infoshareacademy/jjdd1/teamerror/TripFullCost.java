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
    String country, currency;
    String fuelType;
    double distance, fuelUsage;

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

    public Double getFuelUsage() {
        return fuelUsage;
    }

    public void setFuelUsage(Double fuelUsage) {
        this.fuelUsage = fuelUsage;
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


    public void setDistance(double distance) {
        this.distance = distance;
    }

    public TripFullCost(LocalDate date1, LocalDate date2, String country, String currency, String fuelType, Double fuelUsage, Double distance){
        this.date1 = date1;
        this.date2 = date2;
        this.country = country;
        this.currency = currency;
        this.fuelType = fuelType;
        this.fuelUsage = fuelUsage;
        this.distance = distance;
    }

    public TripFullCost() {

    }

    public double costCount(TripFullCost tripData) {
        double currencyPriceDate1 = 0;
        double currencyPriceDate2 = 0;
        double fuelPriceDate1 = 0;
        double fuelPriceDate2 = 0;
        double days = DAYS.between(tripData.getDate1(), tripData.getDate2());

        List<CurrencyHistoryDayValue> currencyObjectsList = FileReader.loadCurrencyFile(tripData.getCurrency());
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

        int iterator101 = 0;
        int iterator102 = 0;

        for (PetrolPrices o : petrolObjectsList) {
            if (tripData.getFuelType().equals("gasoline")) {
                if (tripData.getDate1().getMonth() == o.getDate().getMonth()) {
                    fuelPriceDate1 += o.getGasolinePrice();
                    iterator101++;
                }
                if (tripData.getDate2().getMonth() == o.getDate().getMonth()) {
                    fuelPriceDate2 += o.getGasolinePrice();
                    iterator102++;
                }
            }
            if (tripData.getFuelType().equals("diesel")) {
                if (tripData.getDate1().getMonth() == o.getDate().getMonth()) {
                    fuelPriceDate1 += o.getDieselPrice();
                    iterator101++;
                }
                if (tripData.getDate2().getMonth() == o.getDate().getMonth()) {
                    fuelPriceDate2 += o.getDieselPrice();
                    iterator102++;
                }
            }
        }
        fuelPriceDate1 = fuelPriceDate1 / iterator101;
        fuelPriceDate2 = fuelPriceDate2 / iterator102;

        System.out.println(fuelPriceDate1 + " " + fuelPriceDate2);

        System.out.println(((currencyPriceDate1 + currencyPriceDate2) / 2) * ((fuelPriceDate1 + fuelPriceDate2) / 2) * (tripData.getDistance() / 100) * tripData.getFuelUsage());
        return ((currencyPriceDate1 + currencyPriceDate2) / 2) * ((fuelPriceDate1 + fuelPriceDate2) / 2) * (tripData.getDistance() / 100) * tripData.getFuelUsage();
    }


}
