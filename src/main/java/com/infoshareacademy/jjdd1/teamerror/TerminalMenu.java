package com.infoshareacademy.jjdd1.teamerror;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static com.infoshareacademy.jjdd1.teamerror.FileReader.*;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by igafalkowska on 31.03.17.
 */
public class TerminalMenu {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
//        LocalDate dateOne = null;
//        LocalDate dateTwo = null;
//        System.out.println(DAYS.between(dateOne, dateTwo));


//        for(Object obj : loadCurrencyFile("EUR")) {
//            System.out.println(obj);
//        }

//        for(Object obj : loadPetrolFiles("iSA-PetrolPrices")) {
//            System.out.println(obj);
//        }

//        System.out.println("Please input a start and end date, both in a YYYYMMDD format");
//        String date1 = scan.nextLine();
//        String date2 = scan.nextLine();
//        LocalDate localDate1 = LocalDate.parse(date1, DateTimeFormatter.ofPattern("yyyyMMdd"));
//        LocalDate localDate2 = LocalDate.parse(date2, DateTimeFormatter.ofPattern("yyyyMMdd"));
//        System.out.println("Please input country");
//        String countryName = scan.nextLine();
//        System.out.println("Please input fuel type");
//        String fuelType = scan.nextLine();
        /*if (fuelType.equalsIgnoreCase(FuelTypes.DIESEL.toString())){

        }*/


//        TripFullCost newTrip = new TripFullCost(localDate1, localDate2, countryName, fuelType);
//        System.out.println(newTrip.costCount(newTrip));

        Trendy.checkTrendy(loadCurrencyFile("Eur"),loadPetrolFiles("iSA-PetrolPrices"));


    }
}
