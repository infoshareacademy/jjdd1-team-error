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

        System.out.println("Podaj datę wyjazdu w formacie YYYYMMDD: ");
        try {
            cost.setDate1(LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("yyyyMMdd")));
        } catch (DateTimeException e) {
            System.out.println("Podano błędny format daty");
        }

        System.out.println("Podaj datę powrotu w formacie YYYYMMDD: ");
        try {
                cost.setDate2(LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("yyyyMMdd")));
            } catch(DateTimeException e) {
                System.out.println("Podano błędny format daty");
            }

        System.out.println("Podaj kraj wyjazdu (Honduras, Croatia, USA, France): ");
        cost.setCountry(input.nextLine());

        System.out.println("Podaj walutę kraju (HNL, HRK, USD, EUR): ");
        cost.setCurrency(input.nextLine());

        System.out.println("Podaj rodzaj paliwa (gasoline, diesel): ");
        cost.setFuelType(input.nextLine());

        System.out.println("Podaj przewidywany średni dystans w km, który zostanie przejechany w ciągu każdego dnia wyjazdu: ");
        try {
                cost.setDistance(input.nextDouble());
            } catch(InputMismatchException e) {
                System.out.println("Podano nieprawidłową wartość");
            }

        System.out.println("Koszt auta za granicą podczas wyjazdu będzie wynosił: ");
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







