package com.infoshareacademy.jjdd1.teamerror;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;


import static com.infoshareacademy.jjdd1.teamerror.CurrencyNames.*;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by igafalkowska on 31.03.17.
 */
public class TerminalMenu {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Podaj datę wyjazdu w formacie YYYYMMDD: ");
        String date1 = input.nextLine();

        System.out.println("Podaj datę powrotu w formacie YYYYMMDD: ");
        String date2 = input.nextLine();

        System.out.println("Podaj kraj wyjazdu (Honduras, Croatia, USA, France): ");
        String country = input.nextLine();

        System.out.println("Podaj walutę kraju: ");
        String currency = input.nextLine();

        System.out.println("Podaj rodzaj paliwa (gasoline, diesel): ");
        String fuelType = input.nextLine();

        System.out.println("Podaj przewidywany średni dystans w km, który zostanie przejechany w ciągu każdego dnia wyjazdu: ");
        Double distance = input.nextDouble();


//        System.out.println(date1);
//        System.out.println(date2);
//        System.out.println(country);
//        System.out.println(currency);
//        System.out.println(fuelType);
//        System.out.println(distance);


        TripFullCost cost = new TripFullCost(LocalDate.parse(date1, DateTimeFormatter.ofPattern("yyyyMMdd")),
                LocalDate.parse(date2, DateTimeFormatter.ofPattern("yyyyMMdd")), country, currency, fuelType, distance);

        System.out.println("Koszt auta za granicą podczas wyjazdu będzie wynosił: ");
        //wywołanie metody obliczającej koszt jako argumenty przyjmującej dane z obiektu cost)


//        long days = DAYS.between(LocalDate.parse(date1, DateTimeFormatter.ofPattern("yyyyMMdd")),
// LocalDate.parse(date2, DateTimeFormatter.ofPattern("yyyyMMdd")));
//        System.out.println(days);

//        loadCurrencies();
//        System.out.println(Currencies.get(currency));



//        TripFullCost cost = new TripFullCost(LocalDate.of(2016,05,02), LocalDate.of(2016,05,06), "euro", "diesel", 300.0);
//        System.out.println(cost.costCount(cost));
////

//        System.out.println("Menu");
//        System.out.println("------------------");
//        System.out.println("Options:");
//        System.out.println("1 - Option 1");
//        System.out.println("2 - Option 2");
//        System.out.println("3 - Option 3");
//        System.out.println("4 - Option 4");
//        System.out.println("------------------");
//        System.out.println("Select option: ");
//
//
//        try {
//            Scanner input = new Scanner(System.in);
//            int selection = input.nextInt();
//
//            switch (selection) {
//                case 1:
//                    System.out.println("Option 1 selected");
//                    break;
//                case 2:
//                    System.out.println("Option 2 selected");
//                    break;
//                case 3:
//                    System.out.println("Option 3 selected");
//                    break;
//                case 4:
//                    System.out.println("Option 4 selected");
//                    break;
//                default:
//                    System.out.println("Selected invalid number");
//            }
//        } catch (InputMismatchException e) {
//            System.out.println("You didn't select a number");
//        }
    }
}



