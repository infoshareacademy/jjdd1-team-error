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

        public static void tripCost() {
            Scanner input = new Scanner(System.in);

            TripFullCost cost = new TripFullCost();

            int badAnswerDate1 = 1;
            for (int i = 0; i < badAnswerDate1; i++) {
                try {
                    System.out.println("Enter a date of departure in the format YYYYMMDD: ");
                    cost.setDate1(LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("yyyyMMdd")));
                } catch (DateTimeException e) {
                    System.out.println("Date format is incorrect.");
                    badAnswerDate1++;
                }
            }

            int badAnswerDate2 = 1;
            for (int i = 0; i < badAnswerDate2; i++) {
                try {
                    System.out.println("Enter a date of return in the format YYYYMMDD: ");
                    cost.setDate2(LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("yyyyMMdd")));
                } catch (DateTimeException e) {
                    System.out.println("Given date format is incorrect.");
                    badAnswerDate2++;
                }
            }

            int badAnswerCountry = 1;
            for (int i = 0; i < badAnswerCountry; i++) {
                System.out.println("Enter a country of the trip (Honduras, Croatia, USA, France): ");
                String country = input.nextLine();
                if ("USA".equals(country) || "Croatia".equals(country) || "France".equals(country) || "Honduras".equals(country)) {
                    cost.setCountry(country);
                } else {
                    System.out.println("Given country is incorrect.");
                    badAnswerCountry++;
                }
            }

            int badAnswerCurrency = 1;
            for (int i = 0; i < badAnswerCurrency; i++) {
                System.out.println("Enter currency of the selected country (HNL, HRK, USD, EUR): ");
                String currency = input.nextLine();
                if ("USD".equals(currency) || "HRK".equals(currency) || "EUR".equals(currency) || "HNL".equals(currency)) {
                    cost.setCurrency(currency);
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

            int badAnswerDistance = 1;
            for (int i = 0; i < badAnswerDistance; i++) {
                try {
                    System.out.println("Enter the expected average distance in km to be traveled on each day of the trip: ");
                    cost.setDistance(Double.parseDouble(input.nextLine()));
                } catch (NumberFormatException e) {
                    System.out.println("Given value is incorrect.");
                    badAnswerDistance++;
                }
            }

            System.out.println("The cost of a car abroad during departure will be: ");
            //wywołanie metody obliczającej koszt jako argumenty przyjmującej dane z obiektu cost)
            //FullCost.calculatePrice();
        }

    public static void menu() throws IOException {

        int badAnswerSelection = 1;
        for (int i = 0; i < badAnswerSelection; i++) {
            try {
                Scanner input = new Scanner(System.in);
                int selection = input.nextInt();
                switch (selection) {
                    case 1: {

                        System.out.println("Trip cost calculator");
                        tripCost();
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


    public static void main(String[] args) throws IOException {

        System.out.println("CAR ABROAD");
        System.out.println("MENU");
        System.out.println("--------------------------");
        System.out.println("Select:");
        System.out.println("1 - Trip cost calculator");
        System.out.println("--------------------------");

        menu();

        }
    }

//        System.out.println(cost.getDate1());
//        System.out.println(cost.getDate2());
//        System.out.println(cost.getCountry());
//        System.out.println(cost.getCurrency());
//        System.out.println(cost.getFuelType());
//        System.out.println(cost.getDistance());


//        long days = DAYS.between(LocalDate.parse(date1, DateTimeFormatter.ofPattern("yyyyMMdd")),
// LocalDate.parse(date2, DateTimeFormatter.ofPattern("yyyyMMdd")));
//        System.out.println(days);

//        System.out.println(loadCurrencies().get("EUR"));






