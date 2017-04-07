package com.infoshareacademy.jjdd1.teamerror;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by sebastianlos on 07.04.17.
 */
public class Trendy {
    public static void checkTrendy(List<CurrencyHistoryDayValue> currencyList, List<PetrolPrices> petrolList) {

        for (Integer x = 1; x <= 12; x++) {

            LocalDate month = LocalDate.parse(x.toString(), DateTimeFormatter.ofPattern("mm"));


            for (CurrencyHistoryDayValue obj : currencyList) {
                if (obj.getDate().getMonth() == month.getMonth()) {

                }
            }
        }
    }
}
