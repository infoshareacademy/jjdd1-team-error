package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.CurrencyHistoryDayValue;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.PetrolPrices;
import com.infoshareacademy.jjdd1.teamerror.file_loader.CountryNames;
import com.infoshareacademy.jjdd1.teamerror.file_loader.CurrencyNames;
import com.infoshareacademy.jjdd1.teamerror.file_loader.FileReader;
import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    void setFuelUsage(String fuelUsage) {
        try{
            double usage = Double.parseDouble(fuelUsage);
            if(usage>0){
                this.fuelUsage = usage;
            }
            else{
                LOGGER.error("[{}] is not a positive number", fuelUsage);
            }
        }
        catch(Exception e) {
            LOGGER.error("[{}] is not a number", fuelUsage);
        }
    }

    LocalDate getDate1() {
        return date1;
    }

    //data check added to standard SET method
    void setDate1(String date1String) {
        try {
            if(date1String.length()==8){
                int integerCheck = Integer.parseInt(date1String);
                LocalDate date1 = LocalDate.parse(date1String, DateTimeFormatter.ofPattern("yyyyMMdd"));
                this.date1 = date1;
            }
            else{
                LOGGER.error("Wrong date format");
            }
        }
        catch (NumberFormatException e){
            LOGGER.error("Input contains letters");
        }
        catch( Exception e ) {
            LOGGER.error("No such year/month/day exists");
        }
    }

    LocalDate getDate2() {
        return date2;
    }

    //data check added to standard SET method
    void setDate2(String date2String) {
        try {
            if(date2String.length()==8){
                int integerCheck = Integer.parseInt(date2String);
                LocalDate date2 = LocalDate.parse(date2String, DateTimeFormatter.ofPattern("yyyyMMdd"));
                if (date2.isAfter(date1)) {
                    this.date2 = date2;
                }
                else{
                    LOGGER.error("Return date [{}] is before start date [{}]", date2, date1);
                }
            }
            else{
                LOGGER.error("Wrong date format");
            }
        }
        catch (NumberFormatException e){
            LOGGER.error("Input contains letters");
        }
        catch( Exception e ) {
            LOGGER.error("No such year/month/day exists");
        }
    }

    String getCountry() {
        return country;
    }

    //data check added to standard SET method
    void setCountry(String country){
        try{
            if (CountryNames.getCountryNames().contains(country)) {
                this.country = country;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            LOGGER.error("Country [{}] is not accepted", country);
        }
    }

    String getCurrency() {
        return currency;
    }

    //data check added to standard SET method
    void setCurrency(String currency){
        try {
            if(CurrencyNames.getCurrencies().containsKey(currency)){
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

    void setDistance(String distance) {
        try{
            double distanceDouble = Double.parseDouble(distance);
            if(distanceDouble>0){
                this.distance = distanceDouble;
            }
            else{
                LOGGER.error("[{}] is not a positive number", distance);
            }
        }
        catch(Exception e) {
            LOGGER.error("[{}] is not a number", distance);
        }
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

        //getting average currency values for the specified months of travel if years match in files (lists)
        int iterator1 = 0;
        int iterator2 = 0;
        int iterator3 = 0;
        int iterator4 = 0;
        for(PetrolPrices o2 : petrolObjectsList){
            for(CurrencyHistoryDayValue o1: currencyObjectsList) {
                if (o1.getDate().getYear() == o2.getDate().getYear()) {
                    //LOGGER.info("The o1 year is: [{}] ", o1.getDate().getYear());
                    //LOGGER.info("The o2 year is: [{}] ", o2.getDate().getYear());

                    //getting average currency price values for the specified months of travel
                    if (tripData.getDate1().getMonth() == o1.getDate().getMonth()) {
                        currencyPriceDate1 += o1.getClose();
                        iterator1++;
                    }
                    if (tripData.getDate2().getMonth() == o1.getDate().getMonth()) {
                        currencyPriceDate2 += o1.getClose();
                        iterator2++;
                    }

                    //getting average fuel price values for the specified months of travel and specified type of fuel
                    if (tripData.getFuelType().equalsIgnoreCase("gasoline")) {
                        if (tripData.getDate1().getMonth() == o2.getDate().getMonth()) {
                            fuelPriceDate1 += o2.getGasolinePrice();
                            iterator3++;
                        }
                        if (tripData.getDate2().getMonth() == o2.getDate().getMonth()) {
                            fuelPriceDate2 += o2.getGasolinePrice();
                            iterator4++;
                        }
                    }
                    if (tripData.getFuelType().equalsIgnoreCase("diesel")) {
                        if (tripData.getDate1().getMonth() == o2.getDate().getMonth()) {
                            fuelPriceDate1 += o2.getDieselPrice();
                            iterator3++;
                        }
                        if (tripData.getDate2().getMonth() == o2.getDate().getMonth()) {
                            fuelPriceDate2 += o2.getDieselPrice();
                            iterator4++;
                        }
                    }
                }
            }
        }

        //creating averages of the data and logging it in
        currencyPriceDate1 = currencyPriceDate1 / iterator1;
        currencyPriceDate2 = currencyPriceDate2 / iterator2;
        LOGGER.info("Currency prices for the begining an the end of the trip are: [{}] [{}]",
                currencyPriceDate1, currencyPriceDate2);
        fuelPriceDate1 = fuelPriceDate1 / iterator3;
        fuelPriceDate2 = fuelPriceDate2 / iterator4;
        LOGGER.info("Fuel prices for the begining an the end of the trip are: [{}] [{}]",
                fuelPriceDate1, fuelPriceDate2);

        //counting the trip cost using all the necessary data
        return Trendy.round(((currencyPriceDate1 + currencyPriceDate2) / 2) *
                ((fuelPriceDate1 + fuelPriceDate2) / 2) *
                (tripData.getDistance() / 100) * tripData.getFuelUsage(), 2);
    }
}
