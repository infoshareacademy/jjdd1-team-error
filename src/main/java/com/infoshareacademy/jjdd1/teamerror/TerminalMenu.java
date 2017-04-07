package com.infoshareacademy.jjdd1.teamerror;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.infoshareacademy.jjdd1.teamerror.FileReader.fileFilter;
import static com.infoshareacademy.jjdd1.teamerror.FileReader.loadContent;
import static com.infoshareacademy.jjdd1.teamerror.FileReader.unzipFile;
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
        Scanner input = new Scanner(System.in);
           String selection = input.nextLine();




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



//         FileReader test
//        System.out.println(loadContent("EUR"));
//
//        String[][] s = fileFilter(loadContent("EUR"));
//        for (int i = 0; i < s.length; i++) {
//            for (int j = 0; j < s[i].length; j++)
//                System.out.println(s[i][j]);
//        }
//        // FileReader test
//        System.out.println(loadContent("EUR"));
//
//        String[][] output = fileFilter(loadContent("EUR"));
//        for(int i=0; i<output.length; i++){
//            for(int j=0; j<output[i].length; j++){
//                System.out.println(output[i][j]);
//            }
//        }
//
//        String[][] s = fileFilter(loadContent("EUR"));
//        for (int i = 0; i < s.length; i++) {
//            for (int j = 0; j < s[i].length; j++)
//                System.out.println(s[i][j]);
//        }
//
//        unzipFile("files/omeganbp.zip", "files/");

