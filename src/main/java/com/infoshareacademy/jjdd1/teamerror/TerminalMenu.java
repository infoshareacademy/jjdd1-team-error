package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.file_loader.FileReader;
import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.infoshareacademy.jjdd1.teamerror.file_loader.*;

import java.util.Scanner;

/**
 * Created by igafalkowska on 31.03.17.
 */
public class TerminalMenu {

//    CountryAndCurrency countryAndCurrency = new CountryAndCurrency(filesContent);
//
//    public static void main(String[] args) {
//
//        System.out.println(CountryAndCurrency.getCountriesAndCurrency());
//    }


    private static final Logger LOGGER = LoggerFactory.getLogger(TerminalMenu.class);

    public static void main(String[] arg) {
        menu();
    }

    public static void menu() {

        System.out.println("CAR ABROAD CALCULATOR");
        System.out.println("-----------------------------");

        Scanner input = new Scanner(System.in);
        TripFullCost cost = new TripFullCost();

        int badAnswerCountry = 1;
        for (int i = 0; i < badAnswerCountry; i++) {
            LOGGER.info("Enter a country of the trip (e.g. Croatia, USA, France): ");
            String country = input.nextLine();
            cost.setCountry(country);
            if (cost.getCountry() == null) {
                badAnswerCountry++;
            }
        }

//        final CountryAndCurrency countryAndCurrency;
//
//        cost.setCurrency(countryAndCurrency.loadAvailableCurrencyAndCountries().get(cost.getCountry()));
//
//        System.out.println("Currency in chosen country is " + cost.getCurrency());
//

        int badAnswerFuelType = 1;
        for (int i = 0; i < badAnswerFuelType; i++) {
            LOGGER.info("Enter a number for a specific fuel type (1 = diesel, 2 = gasoline): ");
            String fuelType = input.nextLine();
            cost.setFuelType(fuelType);
            if(cost.getFuelType() == null){
                badAnswerFuelType++;
            }
        }

        int badAnswerSelection = 1;
        for (int i = 0; i < badAnswerSelection; i++) {
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("MENU");
            System.out.println("-----------------------------");
            System.out.println("Select:");
            System.out.println("1 - Trip cost calculator");
            System.out.println("2 - Optimal time for travel analysis");
            System.out.println(String.format("3 - Change initial details (country: %s, currency: %s, fuel type: %s)", cost.getCountry(), cost.getCurrency(), cost.getFuelType()));
            System.out.println("0 - EXIT");
            System.out.println("-----------------------------");
            try {
                int selection = Integer.parseInt(input.nextLine());
                switch (selection) {
                    case 1: {
                        System.out.println("--------------------------");
                        System.out.println("TRIP COST CALCULATOR");
                        System.out.println("--------------------------");
                        tripCost(cost);
                        badAnswerSelection++;
                        break;
                    }
                    case 2: {
                        System.out.println("------------------------------------");
                        System.out.println("OPTIMAL TIME FOR TRAVEL ANALYSIS");
                        System.out.println("------------------------------------");
                        LOGGER.info("Country: "+ cost.getCountry());
                        LOGGER.info("Currency: "+ cost.getCurrency());
                        LOGGER.info("Fuel type: "+ cost.getFuelType());
                        System.out.println("");
                        System.out.println(Trendy.optimalTimeForTrip(cost.getCurrency(), cost.getFuelType(), cost.getCountry()));
                        badAnswerSelection++;
                        break;
                    }
                    case 3: {
                        menu();
                        break;
                    }
                    case 0: {
                        LOGGER.debug("You have left a program CAR ABROAD CALCULATOR");
                        System.exit(0);
                        break;
                    }
                    default:
                        LOGGER.warn("Given number is incorrect.");
                        badAnswerSelection++;
                }
            } catch (NumberFormatException e) {
                LOGGER.error("Please select a number");
                badAnswerSelection++;
            }
        }
    }

    public static void tripCost(TripFullCost cost) {
        Scanner input = new Scanner(System.in);

        int badAnswerDate1 = 1;
        for (int i = 0; i < badAnswerDate1; i++) {
            LOGGER.info("Enter a date of departure in the format YYYYMMDD: ");
            String date = input.nextLine();
            cost.setDate1(date);
            if (cost.getDate1() == null) {
                badAnswerDate1++;
            }
        }

        int badAnswerDate2 = 1;
        for (int i = 0; i < badAnswerDate2; i++) {
            LOGGER.info("Enter a date of return in the format YYYYMMDD: ");
            String date = input.nextLine();
            cost.setDate2(date);
            if (cost.getDate2() == null) {
                badAnswerDate2++;
            }
        }

        int badAnswerFuelUsage = 1;
        for (int i = 0; i < badAnswerFuelUsage; i++) {
            LOGGER.info("Enter fuel usage in liters per 100 km: ");
            String fuelUsage = input.nextLine();
            cost.setFuelUsage(fuelUsage);
            if(cost.getFuelUsage() == 0.0){
                badAnswerFuelUsage++;
            }
        }

        int badAnswerDistance = 1;
        for (int i = 0; i < badAnswerDistance; i++) {
            LOGGER.info("Enter the full distance (counted in km) you want to travel during your trip: ");
            String distance = input.nextLine();
            cost.setDistance(distance);
            if (cost.getDistance() == 0.0) {
                badAnswerDistance++;
            }
        }

        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Given values are: ");
        System.out.println("------------------------------------------------------------------------------");
        LOGGER.info("Date of departure: "+ cost.getDate1());
        LOGGER.info("Date of return: "+ cost.getDate2());
        LOGGER.info("Country: "+ cost.getCountry());
        LOGGER.info("Currency: "+ cost.getCurrency());
        LOGGER.info("Fuel type: "+ cost.getFuelType());
        LOGGER.info("Fuel usage: "+ cost.getFuelUsage() + "l/100km");
        LOGGER.info("Distance: "+ cost.getDistance() + "km");
        System.out.println("------------------------------------------------------------------------------");
        LOGGER.info("The cost of renting a car abroad (for the specified data) will be: " + "\n" + cost.costCount(cost) + " PLN" + "\n");
    }
}