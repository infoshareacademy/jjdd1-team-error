package com.infoshareacademy.jjdd1.teamerror;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

import static com.infoshareacademy.jjdd1.teamerror.FileReader.fileFilter;
import static com.infoshareacademy.jjdd1.teamerror.FileReader.loadContent;
import static com.infoshareacademy.jjdd1.teamerror.FileReader.unzipFile;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by igafalkowska on 31.03.17.
 */
public class TerminalMenu {
    public static void main(String[] args) {
//         FileReader test
//        System.out.println(loadContent("EUR"));

        unzipFile("files/omeganbp.zip", "files/");

        // FileReader test
        System.out.println(loadContent("EUR"));

        Scanner scan = new Scanner(System.in);
        LocalDate dateOne = null;
        LocalDate dateTwo = null;

        System.out.println("State your starting date in the format: YYYYMMDD");
        String input = scan.nextLine();


        System.out.println(DAYS.between(dateOne, dateTwo));
    }
}
