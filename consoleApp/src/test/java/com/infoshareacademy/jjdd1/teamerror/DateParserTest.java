package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.file_loader.DateParser;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;

/**
 * Created by iga on 19.04.17.
 */
public class DateParserTest {

    @Test
    public void dateFromString_should_parse_one_string_to_local_date(){
        DateParser dateParser = new DateParser();
        LocalDate date = dateParser.DateFromString("20160503");
        Assert.assertEquals(LocalDate.of(2016, 05,03),date);
    }

    @Test
    public void dateFromString_should_parse_two_string_to_local_date(){
        DateParser dateParser = new DateParser();
        LocalDate date = dateParser.DateFromString("2016", "February");
        Assert.assertEquals(LocalDate.of(2016, 02,01),date);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> develop
