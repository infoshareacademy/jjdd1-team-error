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

        int iterator101=0;
        int iterator102=0;
        for(PetrolPrices o: petrolObjectsList) {
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
        fuelPriceDate1 = fuelPriceDate1/iterator101;
        fuelPriceDate2 = fuelPriceDate2/iterator102;

           /* if(tripData.getDate1().getMonth() == o.getDate().getMonth()){
                if(tripData.getFuelType().equals("gasoline")){
                    fuelPriceDate1 += o.getGasolinePrice();
                    iterator101++;
                }
                if(tripData.getFuelType().equals("diesel")){
                    fuelPriceDate1 += o.getDieselPrice();
                    iterator201++;
                }
            }
            if(tripData.getDate2().getMonth() == o.getDate().getMonth()){
                if(tripData.getFuelType().equals("gasoline")){
                    fuelPriceDate2 += o.getGasolinePrice();
                    iterator102++;
                }
                if(tripData.getFuelType().equals("diesel")){
                    fuelPriceDate2 += o.getDieselPrice();
                    iterator202++;
                }
            }
        }*/


        System.out.println(fuelPriceDate1 +" "+fuelPriceDate2);

        System.out.println(((currencyPriceDate1+currencyPriceDate2)/2) * ((fuelPriceDate1+fuelPriceDate2)/2) * days);
        return ((currencyPriceDate1+currencyPriceDate2)/2) * ((fuelPriceDate1+fuelPriceDate2)/2) * days;

    }

}
