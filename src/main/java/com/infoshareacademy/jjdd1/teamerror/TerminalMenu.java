package com.infoshareacademy.jjdd1.teamerror;

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


    public static void main(String[] arg) {
        menu();
    }

    public static void menu() {

        System.out.println("CAR ABROAD CALCULATOR");

        Scanner input = new Scanner(System.in);
        TripFullCost cost = new TripFullCost();

        int badAnswerCountry = 1;
        for (int i = 0; i < badAnswerCountry; i++) {
            System.out.println("Enter a country of the trip (e.g. Croatia, USA, France): ");
            String country = input.nextLine();
            if (PetrolFileFilter.loadAvailableCountries().contains(country))
                cost.setCountry(country);
            else {
                System.out.println("Given country is incorrect.");
                badAnswerCountry++;
            }
        }

        int badAnswerCurrency = 1;
        for (int i = 0; i < badAnswerCurrency; i++) {
            System.out.println("Enter currency of the selected country (e.g. HRK, USD, EUR): ");
            String currency = input.nextLine().toUpperCase();
            if (CurrencyNames.loadCurrencies().containsKey(currency)) {
                cost.setCurrency(currency) ;
            } else {
                System.out.println("Given currency is incorrect.");
                badAnswerCurrency++;
            }
        }

        int badAnswerFuelType = 1;
        for (int i = 0; i < badAnswerFuelType; i++) {
            System.out.println("Enter fuel type (gasoline, diesel): ");
            String fuelType = input.nextLine().toLowerCase();
            if ("diesel".equals(fuelType) || "gasoline".equals(fuelType)) {
                cost.setFuelType(fuelType);
            } else {
                System.out.println("Given fuel type is incorrect");
                badAnswerFuelType++;
            }
        }



        int badAnswerSelection = 1;
        for (int i = 0; i < badAnswerSelection; i++) {
            System.out.println();
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
                        new Trendy().optimalTimeForTrip(cost.getCurrency(), cost.getFuelType(), cost.getCountry());
                        badAnswerSelection++;
                        break;
                    }
                    case 3: {
                        menu();
                        break;
                    }
                    case 0: {
                        System.out.println("You have left a program CAR ABROAD CALCULATOR");
                        System.exit(0);
                        break;
                    }
                    default:
                        System.out.println("Given number is incorrect.");
                        menu();
                }
            } catch (InputMismatchException e) {
                System.out.println("Please select a number");
                badAnswerSelection++;
            }
        }
    }

    public static void tripCost(TripFullCost cost) {
        Scanner input = new Scanner(System.in);

        int badAnswerDate1 = 1;
        for (int i = 0; i < badAnswerDate1; i++) {
            System.out.println("Enter a date of departure in the format YYYYMMDD: ");
                try {
                    String date = input.nextLine();
                    cost.setDate1(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd")));
                    int check = Integer.parseInt(date.substring(6));
                    if(check!=cost.getDate1().getDayOfMonth()){
                        System.out.println("Wrong number of days");
                        badAnswerDate1++;
                    }
                } catch (DateTimeException e) {
                    System.out.println("Given date format is incorrect.");
                    badAnswerDate1++;
                }
        }

        int badAnswerDate2 = 1;
        for (int i = 0; i < badAnswerDate2; i++) {
            System.out.println("Enter a date of return in the format YYYYMMDD: ");
                try {
                    String date = input.nextLine();
                    cost.setDate2(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd")));
                    int check = Integer.parseInt(date.substring(6));
                    if(check!=cost.getDate2().getDayOfMonth()){
                        System.out.println("Wrong number of days");
                        badAnswerDate2++;
                    }
                } catch (DateTimeException e) {
                    System.out.println("Given date format is incorrect.");
                    badAnswerDate2++;
                }
                catch (IllegalArgumentException e) {
                    System.out.println("Date of return must be after date of departure");
                    badAnswerDate2++;
                }
        }

        int badAnswerFuelUsage = 1;
        for (int i = 0; i < badAnswerFuelUsage; i++) {
            try {
                System.out.println("Enter fuel usage per 100 km: ");
                cost.setFuelUsage(Double.parseDouble(input.nextLine()));
            } catch (NumberFormatException e) {
                System.out.println("Given value is incorrect.");
                badAnswerFuelUsage++;
            }
        }

        int badAnswerDistance = 1;
        for (int i = 0; i < badAnswerDistance; i++) {
            try {
                System.out.println("Enter the expected average distance in km to be traveled summary on the trip: ");
                cost.setDistance(Double.parseDouble(input.nextLine()));
            } catch (NumberFormatException e) {
                System.out.println("Given value is incorrect.");
                badAnswerDistance++;
            }
        }

        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Given values are: ");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Date of departure: "+ cost.getDate1());
        System.out.println("Date of return: "+ cost.getDate2());
        System.out.println("Country: "+ cost.getCountry());
        System.out.println("Currency: "+ cost.getCurrency());
        System.out.println("Fuel type: "+ cost.getFuelType());
        System.out.println("Fuel usage: "+ cost.getFuelUsage());
        System.out.println("Distance: "+ cost.getDistance());
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("The cost of a car abroad during departure for given values will be: ");

        System.out.println(cost.costCount(cost) + "PLN");
    }
}