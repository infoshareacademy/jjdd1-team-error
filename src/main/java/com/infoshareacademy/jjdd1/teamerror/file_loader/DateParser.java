package com.infoshareacademy.jjdd1.teamerror.file_loader;

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
            String month ="";
            switch(dateFragment[1]){
                case "January": month="01";
                    break;
                case "February": month="02";
                    break;
                case "March": month="03";
                    break;
                case "April": month="04";
                    break;
                case "May": month="05";
                    break;
                case "June": month="06";
                    break;
                case "July": month="07";
                    break;
                case "August": month="08";
                    break;
                case "September": month="09";
                    break;
                case "October": month="10";
                    break;
                case "November": month="11";
                    break;
                case "December": month="12";
                    break;
            }
            return LocalDate.parse(dateFragment[0]+month+"01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        }
    }
}
