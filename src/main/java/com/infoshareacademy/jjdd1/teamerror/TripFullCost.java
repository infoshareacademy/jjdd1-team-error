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
    String fuelType;

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
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
        this.country = country;
    }

    public TripFullCost(LocalDate date1, LocalDate date2, String country, String fuelType){
        this.date1 = date1;
        this.date2 = date2;
        this.country = country;
        this.fuelType = fuelType;
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

        int iterator1=1;
        int iterator2=1;
        for(CurrencyHistoryDayValue o: currencyObjectsList){
            if(tripData.getDate1().getMonth() == o.getDate().getMonth()){
                currencyPriceDate1 += o.getOpen();
                currencyPriceDate1 = currencyPriceDate1 / iterator1;
                iterator1++;
            }
            if(tripData.getDate2() == o.getDate()){
                currencyPriceDate2 += o.getOpen();
                currencyPriceDate2 /= iterator2;
                iterator2++;
            }
        }
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
