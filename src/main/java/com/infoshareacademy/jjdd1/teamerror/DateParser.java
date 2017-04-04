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

    public static LocalDate DateFromString (String dateNumber){
        return LocalDate.parse(dateNumber, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

}
