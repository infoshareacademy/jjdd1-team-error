package com.infoshareacademy.jjdd1.teamerror;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.CurrencyHistoryDayValue;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.PetrolPrices;
import com.infoshareacademy.jjdd1.teamerror.file_loader.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
/**
 * Created by Krystian on 2017-04-20.
 */
public class TripFullCostTest {

    public static final int LAST_MONTH = 12;
    public static final int FIRST_MONTH = 1;
    public static final int FIRST_DAY_OF_MONTH = 1;
    public static final int START_YEAR = 2016;

    private static Map<LocalDate, Double> mapOfCurrencyObjects = new LinkedHashMap<>();
    private static Map<LocalDate, Double> mapOfPetrolObjects = new LinkedHashMap<>();

    private FilesContent filesContent = new OnDemandFilesContent(){
    };
    private PetrolFileFilter petrolFileFilter = new PetrolFileFilter();
    private final CurrencyFileFilter currencyFileFilter = new CurrencyFileFilter();
//    public TripFullCostTest(FilesContent filesContent) {
//        this.filesContent = filesContent;
//    }
    TripFullCost cost = TripFullCost.createTripCostObject(filesContent);
    DateParser dateParser = new DateParser();

    List<CurrencyHistoryDayValue> currencyObjectsList = new ArrayList<>();
    List<PetrolPrices> petrolObjectsList = new ArrayList<>();

    LocalDate testDate = dateParser.DateFromString("20160202");

    @Before
    public void createCurrencyDataFor5Years() {
        for (int year = START_YEAR; year <= START_YEAR + 5; year++) {
            double currencyValue = 3;
            for (int month = FIRST_MONTH; month <= LAST_MONTH; month++) {
                currencyValue+=0.5;
                CurrencyHistoryDayValue someObject = new CurrencyHistoryDayValue();
                someObject.setDate(LocalDate.of(year, month, FIRST_DAY_OF_MONTH));
                someObject.setClose(currencyValue);
                currencyObjectsList.add(someObject);
            }
//            System.out.println(currencyObjectsList.get(year).getClose());
        }
    }

    public void createGasolineDataFor5Years() {
        for (int year = START_YEAR; year <= START_YEAR + 5; year++) {
            double petrolValue = 3;
            for (int month = FIRST_MONTH; month <= LAST_MONTH; month++) {
                petrolValue+=0.5;
                PetrolPrices someObject = new PetrolPrices();
                someObject.setDate(LocalDate.of(year, month, FIRST_DAY_OF_MONTH));
                someObject.setGasolinePrice(petrolValue);
                petrolObjectsList.add(someObject);
            }
//            System.out.println(petrolObjectsList.get(year).getGasolinePrice());
        }
    }

    @Test
    public void costCount_should_count_the_cost_of_the_trip_when_using_USD_and_gasoline(){
        double currencyPriceDate1 = 0;
        double currencyPriceDate2 = 0;
        double fuelPriceDate1 = 0;
        double fuelPriceDate2 = 0;
//        petrolFileFilter.setFilesContent(filesContent);
//        currencyFileFilter.setFilesContent(filesContent);
//        List<CurrencyHistoryDayValue> currencyObjectsList = currencyFileFilter.getListOfCurrencyDataObjects("USD");
//        List<PetrolPrices> petrolObjectsList = petrolFileFilter.getListOfPetrolDataObjects("USA");
        int iterator1 = 0;
        int iterator2 = 0;
        int iterator3 = 0;
        int iterator4 = 0;
        for(PetrolPrices o2 : petrolObjectsList){
            for(CurrencyHistoryDayValue o1: currencyObjectsList) {
                if (o1.getDate().getYear() == o2.getDate().getYear()) {
                    if (testDate.getMonth() == o1.getDate().getMonth()) {
                        currencyPriceDate1 += o1.getClose();
                        iterator1++;
                    }
                    if (testDate.getMonth() == o1.getDate().getMonth()) {
                        currencyPriceDate2 += o1.getClose();
                        iterator2++;
                    }

                    if (testDate.getMonth() == o2.getDate().getMonth()) {
                        fuelPriceDate1 += o2.getGasolinePrice();
                        iterator3++;
                    }
                    if (testDate.getMonth() == o2.getDate().getMonth()) {
                        fuelPriceDate2 += o2.getGasolinePrice();
                        iterator4++;
                    }
                }
            }
        }
        System.out.println(currencyPriceDate1);
        System.out.println(currencyPriceDate2);
        System.out.println(fuelPriceDate1);
        System.out.println(fuelPriceDate2);

        currencyPriceDate1 = currencyPriceDate1 / iterator1;
        currencyPriceDate2 = currencyPriceDate2 / iterator2;
        fuelPriceDate1 = fuelPriceDate1 / iterator3;
        fuelPriceDate2 = fuelPriceDate2 / iterator4;
        double d = ((currencyPriceDate1 + currencyPriceDate2) / 2) *
                ((fuelPriceDate1 + fuelPriceDate2) / 2) *
                (1000 / 100) * 5;
        System.out.println(d);
        assertEquals(133.68,d, 0.001);
    }
}
