package com.infoshareacademy.jjdd1.teamerror;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;


import static com.infoshareacademy.jjdd1.teamerror.CurrencyNames.*;

import java.time.LocalDate;

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
        System.out.println("MENU");
        System.out.println("-----------------------------");
        System.out.println("Select:");
        System.out.println("1 - Trip cost calculator");
//        System.out.println("2 - Month average of currency value");
//        System.out.println("3 - Month average of fuel cost");
        System.out.println("0 - EXIT");
        System.out.println("-----------------------------");

        int badAnswerSelection = 1;
        for (int i = 0; i < badAnswerSelection; i++) {
            try {
                Scanner input = new Scanner(System.in);
                int selection = input.nextInt();
                switch (selection) {
                    case 1: {
                            System.out.println("--------------------------");
                            System.out.println("TRIP COST CALCULATOR");
                            System.out.println("--------------------------");
                            tripCost();
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

//    public static void exit() {
//                System.out.println("You have left a program CAR ABROAD CALCULATOR");
//                System.exit(0);
//            }

    public static void tripCost() {
        Scanner input = new Scanner(System.in);

        TripFullCost cost = new TripFullCost();

        int badAnswerDate1 = 1;
        for (int i = 0; i < badAnswerDate1; i++) {
            System.out.println("Enter a date of departure in the format YYYYMMDD: ");
                try {
                    cost.setDate1(LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("yyyyMMdd")));
                } catch (DateTimeException e) {
                    System.out.println("Given date format is incorrect.");
                    badAnswerDate1++;
                }
            }


        int badAnswerDate2 = 1;
        for (int i = 0; i < badAnswerDate2; i++) {
            System.out.println("Enter a date of return in the format YYYYMMDD: ");
                try {
                        cost.setDate2(LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("yyyyMMdd")));
                } catch (DateTimeException e) {
                    System.out.println("Given date format is incorrect.");
                    badAnswerDate2++;
                }
            }

        int badAnswerCountry = 1;
        for (int i = 0; i < badAnswerCountry; i++) {
            System.out.println("Enter a country of the trip (Croatia, USA, France): ");
            String country = input.nextLine();
            if ("USA".equals(country) || "Croatia".equals(country)  ||
                    "France".equals(country))
            cost.setCountry(country);
            else {
                System.out.println("Given country is incorrect.");
                badAnswerCountry++;
            }
        }

        int badAnswerCurrency = 1;
        for (int i = 0; i < badAnswerCurrency; i++) {
            System.out.println("Enter currency of the selected country (HRK, USD, EUR): ");
            String currency = input.nextLine();
            if ("USD".equals(currency) || "HRK".equals(currency)  ||
                    "EUR".equals(currency)) {
                cost.setCurrency(currency) ;
            } else {
                System.out.println("Given currency is incorrect.");
                badAnswerCurrency++;
            }
        }

        int badAnswerFuelType = 1;
        for (int i = 0; i < badAnswerFuelType; i++) {
            System.out.println("Enter fuel type (gasoline, diesel): ");
            String fuelType = input.nextLine();
            if ("diesel".equals(fuelType) || "gasoline".equals(fuelType)) {
                cost.setFuelType(fuelType);
            } else {
                System.out.println("Given fuel type is incorrect");
                badAnswerFuelType++;
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
        //wywołanie metody obliczającej koszt jako argumenty przyjmującej dane z obiektu cost)
        //FullCost.calculatePrice();
        // print differences in currencies and fuel rates in each month and the best time for cheap travel
        //Trendy.optimalTimeForTrip(cost.getCurrency(), cost.getFuelType(), cost.getCountry());
        System.out.println(cost.costCount(cost));
    }
}