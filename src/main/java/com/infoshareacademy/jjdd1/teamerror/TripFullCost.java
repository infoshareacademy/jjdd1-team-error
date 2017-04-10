package com.infoshareacademy.jjdd1.teamerror;

import com.sun.org.apache.xerces.internal.impl.dv.DatatypeException;

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

    private LocalDate date1, date2;
    private String country, currency;
    private String fuelType;
    private double distance, fuelUsage;

    String getFuelType() {
        return fuelType;
    }

    void setFuelType(String fuelType) {
        if("diesel".equals(fuelType) || "gasoline".equals(fuelType)) {
            this.fuelType = fuelType;
        }
        else
            throw new IllegalArgumentException("Given fuel type is incorrect");
    }

    Double getFuelUsage() {
        return fuelUsage;
    }

    void setFuelUsage(Double fuelUsage) {
        this.fuelUsage = fuelUsage;
    }


    LocalDate getDate1() {
        return date1;
    }

    void setDate1(LocalDate date1) {
        this.date1 = date1;
    }

    LocalDate getDate2() {
        return date2;
    }

    void setDate2(LocalDate date2) {
        if (date2.isBefore(date1)) {
            throw new IllegalArgumentException("Date of return must be after date of departue");
        } else
            this.date2 = date2;
    }

    String getCountry() {
        return country;
    }

    void setCountry(String country) {
        if ("USA".equals(country) || "Croatia".equals(country) || "France".equals(country) || "Honduras".equals(country)) {
            this.country = country;
        } else
            throw new IllegalArgumentException("Given country is incorrect");
    }

    String getCurrency() {return currency;}

    void setCurrency(String currency) {
        if ("USD".equals(currency) || "HRK".equals(currency) || "EUR".equals(currency) || "HNL".equals(currency)) {
            this.currency = currency;
        } else
            throw new IllegalArgumentException("Given currency is incorrect");
    }

    Double getDistance() {
        return distance;
    }

    void setDistance(double distance) {
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
        List<PetrolPrices> petrolObjectsList = FileReader.loadPetrolFiles(tripData.getCountry());

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

        //System.out.println(currencyPriceDate1 +" "+currencyPriceDate2);

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

        //System.out.println(fuelPriceDate1 + " " + fuelPriceDate2);

        return Trendy.round (((currencyPriceDate1 + currencyPriceDate2) / 2) *
                ((fuelPriceDate1 + fuelPriceDate2) / 2) * (tripData.getDistance() / 100) * tripData.getFuelUsage(), 2);
    }

}
