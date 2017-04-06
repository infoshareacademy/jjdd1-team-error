package com.infoshareacademy.jjdd1.teamerror;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

import static com.infoshareacademy.jjdd1.teamerror.FileReader.*;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by igafalkowska on 31.03.17.
 */
public class TerminalMenu {
    public static void main(String[] args) {

//        Scanner scan = new Scanner(System.in);
//        LocalDate dateOne = null;
//        LocalDate dateTwo = null;
//        System.out.println(DAYS.between(dateOne, dateTwo));


//        for(Object obj : loadCurrencyFile("EUR")) {
//            System.out.println(obj);
//        }

        for(Object obj : loadPetrolFiles("iSA-PetrolPrices")) {
            System.out.println(obj);
        }


    }
}
