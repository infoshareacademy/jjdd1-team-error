package com.infoshareacademy.jjdd1.teamerror;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by krystianskrzyszewski on 04.04.17.
 */
public class DateParser {
    public LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static LocalDate DateFromString (String... dateFragment){
        if(dateFragment.length == 1){
            return LocalDate.parse(dateFragment[0], DateTimeFormatter.ofPattern("yyyyMMdd"));
        }
        else{
            LocalDate date = LocalDate.parse(dateFragment[1].substring(0,3), DateTimeFormatter.ofPattern("MMM"));
            String month = date.toString();
            return LocalDate.parse(dateFragment[0]+dateFragment[1]+"01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        }
    }
}
