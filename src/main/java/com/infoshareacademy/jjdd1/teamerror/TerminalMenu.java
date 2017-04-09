package com.infoshareacademy.jjdd1.teamerror;

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
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        TripFullCost cost = new TripFullCost();

        System.out.println("State your departure date in this format YYYYMMDD: ");
        try {
            cost.setDate1(LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("yyyyMMdd")));
        } catch (DateTimeException e) {
            System.out.println("Wrong date format input");
        }

        System.out.println("State your return date in this format YYYYMMDD: ");
        try {
                cost.setDate2(LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("yyyyMMdd")));
            } catch(DateTimeException e) {
                System.out.println("Wrong date format input");
            }

        System.out.println("State the country you wish to travel to (Honduras, Croatia, USA, France): ");
        cost.setCountry(input.nextLine());

        System.out.println("State the country's currency (HNL, HRK, USD, EUR): ");
        cost.setCurrency(input.nextLine());

        System.out.println("State your fuel type (gasoline / diesel): ");
        cost.setFuelType(input.nextLine());

        System.out.println("State an average distance of km, which you are going to travel, once abroad: ");
        try {
                cost.setDistance(input.nextDouble());
            } catch(InputMismatchException e) {
                System.out.println("Wrong value input");
            }

        System.out.println("State how much fuel your car will burn on average  for 100km: ");
        try {
            cost.setFuellUse(input.nextDouble());
        } catch(InputMismatchException e) {
            System.out.println("Wrong value input");
        }

        System.out.println("The trip cost will be approximately: ");
        cost.costCount(cost);
        //wywołanie metody obliczającej koszt jako argumenty przyjmującej dane z obiektu cost)


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

    }
}
