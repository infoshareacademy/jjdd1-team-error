package com.infoshareacademy.jjdd1.teamerror;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.zip.DataFormatException;

import static java.time.temporal.ChronoUnit.DAYS;
/**
 * Created by igafalkowska on 31.03.17.
 */
public class TerminalMenu {

    private static final Logger LOGGER = LoggerFactory.getLogger(TerminalMenu.class);

    public static void main(String[] arg) {
        menu();
    }

    public static void menu() {
        String StringInput;
        int intInput;

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

        int badAnswerCurrency = 1;
        for (int i = 0; i < badAnswerCurrency; i++) {
            LOGGER.info("Enter currency of the selected country (e.g. HRK, USD, EUR): ");
            String currency = input.nextLine().toUpperCase();
            cost.setCurrency(currency);
            if(cost.getCurrency() == null){
                badAnswerCurrency++;
            }
        }

        int badAnswerFuelType = 1;
        for (int i = 0; i < badAnswerFuelType; i++) {
            LOGGER.info("Enter a number for a specific fuel type (1 = diesel, 2 = gasoline): ");
            try {
                cost.setFuelType(Integer.parseInt(input.nextLine()));
                if(cost.getFuelType() == null){
                    badAnswerFuelType++;
                }
            } catch (NumberFormatException e) {
                LOGGER.info("Input [{}] is not a number");
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
            System.out.println("3 - Change initial details");
            System.out.println("0 - EXIT");
            System.out.println("-----------------------------");
            try {
                int selection = input.nextInt();
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
                        Trendy.optimalTimeForTrip(cost.getCurrency(), cost.getFuelType(), cost.getCountry());
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
                        menu();
                }
            } catch (InputMismatchException e) {
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
            try {
                LOGGER.info("Enter fuel usage per 100 km: ");
                cost.setFuelUsage(Double.parseDouble(input.nextLine()));
            } catch (NumberFormatException e) {
                LOGGER.error("Given value is incorrect.");
                badAnswerFuelUsage++;
            }
        }

        int badAnswerDistance = 1;
        for (int i = 0; i < badAnswerDistance; i++) {
            try {
                LOGGER.info("Enter the expected distance in km that you plan to travel during your trip: ");
                cost.setDistance(Double.parseDouble(input.nextLine()));
            } catch (NumberFormatException e) {
                LOGGER.error("Given value is incorrect.");
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
        LOGGER.info("Fuel usage: "+ cost.getFuelUsage());
        LOGGER.info("Distance: "+ cost.getDistance());
        System.out.println("------------------------------------------------------------------------------");
        LOGGER.info("The cost of a car abroad during departure for given values will be: " + "\n" + cost.costCount(cost) + " PLN" + "\n");

        System.out.println(cost.costCount(cost) + "PLN");
    }
}