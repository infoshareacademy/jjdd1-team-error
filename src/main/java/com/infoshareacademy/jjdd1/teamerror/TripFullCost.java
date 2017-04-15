package com.infoshareacademy.jjdd1.teamerror;

import com.sun.org.apache.xerces.internal.impl.dv.DatatypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by krystianskrzyszewski on 07.04.17.
 */
public class TripFullCost {

    private LocalDate date1, date2;
    private String country, currency;
    private String fuelType;
    private double distance, fuelUsage;
    private static final Logger LOGGER = LoggerFactory.getLogger(TripFullCost.class);

    String getFuelType() {
        return fuelType;
    }

    //data check added to standard SET method
    void setFuelType(String fuelNumber) {
        try{
            int number = Integer.parseInt(fuelNumber);
            switch(number){
                case 1:
                    this.fuelType = "diesel";
                    LOGGER.info("[{}] chosen", fuelType);
                    break;
                case 2:
                    this.fuelType = "gasoline";
                    LOGGER.info("[{}] chosen", fuelType);
                    break;
                default:
                    LOGGER.error("[{}] fuel type is incorrect", fuelNumber);
                    break;
            }
        }catch (Exception e) {
            LOGGER.error("[{}] is not a number", fuelNumber);
        }
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

    //data check added to standard SET method
    void setDate1(String date1String) {
        boolean isInteger;
        try {
            Integer.parseInt(date1String);
            isInteger=true;
        }
        catch( Exception e ) {
            isInteger=false;
        }
        if(date1String.length()!=8){
            LOGGER.error("Wrong date format");
        }
        if(!isInteger){
            LOGGER.error("Input contains letters");
        }
        if(isInteger && date1String.length()==8){
            LocalDate date1 = LocalDate.parse(date1String, DateTimeFormatter.ofPattern("yyyyMMdd"));
            this.date1 = date1;
        }
    }

    LocalDate getDate2() {
        return date2;
    }

    //data check added to standard SET method
    void setDate2(String date2String) {
        boolean isInteger;
        try {
            Integer.parseInt(date2String);
            isInteger=true;
        }
        catch( Exception e ) {
            isInteger=false;
        }
        if(date2String.length()!=8){
            LOGGER.error("Wrong date format");
        }
        if(!isInteger){
            LOGGER.error("Input contains letters");
        }
        if(isInteger && date2String.length()==8){
            LocalDate date2 = LocalDate.parse(date2String, DateTimeFormatter.ofPattern("yyyyMMdd"));
            try{
                if (date2.isAfter(date1)) {
                    this.date2 = date2;
                }
                else{
                    LOGGER.error("Return date [{}] is before start date [{}]", date2, date1);
                }
            } catch (Exception e) {
            }
        }
    }

    String getCountry() {
        return country;
    }

    //data check added to standard SET method
    void setCountry(String country){
        try{
            if (PetrolFileFilter.loadAvailableCountries().contains(country)) {
                this.country = country;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            LOGGER.error("Country [{}] is not accepted", country);
        }
    }

    String getCurrency() {return currency;}

    //data check added to standard SET method
    void setCurrency(String currency){
        try {
            if(CurrencyNames.loadCurrencies().containsKey(currency)){
                this.currency = currency;
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOGGER.error("Currency [{}] is not accepted", currency);
        }
    }

    Double getDistance() {
        return distance;
    }

    void setDistance(double distance) {
        this.distance = distance;
    }

    //basic constructor
    public TripFullCost() {

    }

    //method which will count the trip's entire cost depending on different variables
    public double costCount(TripFullCost tripData) {
        double currencyPriceDate1 = 0;
        double currencyPriceDate2 = 0;
        double fuelPriceDate1 = 0;
        double fuelPriceDate2 = 0;
        double days = DAYS.between(tripData.getDate1(), tripData.getDate2());

        //creating lists from files, so that they can be searched through
        List<CurrencyHistoryDayValue> currencyObjectsList = FileReader.loadCurrencyFile(tripData.getCurrency());
        List<PetrolPrices> petrolObjectsList = FileReader.loadPetrolFiles(tripData.getCountry());

        //getting average currency values for the specified months of travel if years in files (lists) match
        for(CurrencyHistoryDayValue o1: currencyObjectsList){
            for(PetrolPrices o2 : petrolObjectsList) {
                if (o1.getDate().getYear() == o2.getDate().getYear()) {
                    int iterator1 = 0;
                    int iterator2 = 0;
                    for (CurrencyHistoryDayValue o : currencyObjectsList) {
                        if (tripData.getDate1().getMonth() == o.getDate().getMonth()) {
                            currencyPriceDate1 += o.getClose();
                            iterator1++;
                        }
                        if (tripData.getDate2().getMonth() == o.getDate().getMonth()) {
                            currencyPriceDate2 += o.getClose();
                            iterator2++;
                        }
                    }
                    currencyPriceDate1 = currencyPriceDate1 / iterator1;
                    currencyPriceDate2 = currencyPriceDate2 / iterator2;

                    //checking if the average currencies are counted correctly
                    //System.out.println(currencyPriceDate1 +" "+currencyPriceDate2);

                    //resetting the iterators and getting average fuel price values for the specified months of travel and specified type of fuel
                    iterator1 = 0;
                    iterator2 = 0;
                    for (PetrolPrices o : petrolObjectsList) {
                        if (tripData.getFuelType().equalsIgnoreCase("gasoline")) {
                            if (tripData.getDate1().getMonth() == o.getDate().getMonth()) {
                                fuelPriceDate1 += o.getGasolinePrice();
                                iterator1++;
                            }
                            if (tripData.getDate2().getMonth() == o.getDate().getMonth()) {
                                fuelPriceDate2 += o.getGasolinePrice();
                                iterator2++;
                            }
                        }
                        if (tripData.getFuelType().equalsIgnoreCase("diesel")) {
                            if (tripData.getDate1().getMonth() == o.getDate().getMonth()) {
                                fuelPriceDate1 += o.getDieselPrice();
                                iterator1++;
                            }
                            if (tripData.getDate2().getMonth() == o.getDate().getMonth()) {
                                fuelPriceDate2 += o.getDieselPrice();
                                iterator2++;
                            }
                        }
                    }
                    fuelPriceDate1 = fuelPriceDate1 / iterator1;
                    fuelPriceDate2 = fuelPriceDate2 / iterator2;

                    //checking if the average fuel prices are counted correctly
                    //System.out.println(fuelPriceDate1 + " " + fuelPriceDate2);
                }
            }

        }
        //counting the trip cost using all the necessary data
        return Trendy.round(((currencyPriceDate1 + currencyPriceDate2) / 2) *
                ((fuelPriceDate1 + fuelPriceDate2) / 2) * (tripData.getDistance() / 100) * tripData.getFuelUsage(), 2);
    }

}
