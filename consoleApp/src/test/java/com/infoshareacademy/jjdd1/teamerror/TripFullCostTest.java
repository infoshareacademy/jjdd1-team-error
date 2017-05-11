package com.infoshareacademy.jjdd1.teamerror;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.CurrencyRates;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.PetrolRates;
import com.infoshareacademy.jjdd1.teamerror.file_loader.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * Created by Krystian on 2017-04-20.
 */
public class TripFullCostTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripFullCostTest.class);
    public static final int FIRST_DAY_OF_MONTH = 1;
    public static final int CURRENCIES_START_YEAR = 2010;
    public static final int CURRENCIES_END_YEAR = 2021;
    public static final int PETROL_START_YEAR = 2010;
    public static final int PETROL_END_YEAR = 2021;
    DateParser dateParser = new DateParser();
    public final LocalDate START_DATE = dateParser.DateFromString("20160220");
    public final LocalDate END_DATE = dateParser.DateFromString("20160321");
    public static final String FUEL_TYPE = "gasoline";

    List<CurrencyRates> currencyObjectsList = new ArrayList<>();
    List<PetrolRates> petrolObjectsList = new ArrayList<>();

//    private static Map<LocalDate, Double> mapOfCurrencyObjects = new LinkedHashMap<>();
//    private static Map<LocalDate, Double> mapOfPetrolObjects = new LinkedHashMap<>();
//    private FilesContent filesContent = new OnDemandFilesContent(){
//    };
//    private PetrolFileFilter petrolFileFilter = new PetrolFileFilter();
//    private final CurrencyFileFilter currencyFileFilter = new CurrencyFileFilter();
//    TripFullCost cost = TripFullCost.createTripCostObject(filesContent);

    @Before
    public void createCurrencyData() {
        for (int year = CURRENCIES_START_YEAR; year <= CURRENCIES_END_YEAR; year++) {
            double currencyValue = 3;
            for (int month = 1; month <= 12; month++) {
                int days = LocalDate.of(year,month,1).lengthOfMonth();
                for (int i = 1; i<=days; i++){
                    currencyValue+=0.01;
                    CurrencyRates someObject = new CurrencyRates();
                    someObject.setDate(LocalDate.of(year, month, i));
                    someObject.setRate(currencyValue);
                    currencyObjectsList.add(someObject);
                }
            }
        }
//        for(CurrencyRates o : currencyObjectsList){
//            System.out.println("Currency:  " + o.getDate() + "  " + o.getClose());
//        }
    }

    @Before
    public void createGasolineData() {
        for (int year = PETROL_START_YEAR; year <= PETROL_END_YEAR; year++) {
            double gasolineValue = 3;
            double dieselValue = 2;
            for (int month = 1; month <= 12; month++) {
                gasolineValue += 0.5;
                dieselValue += 0.5;
                PetrolRates someObject = new PetrolRates();
                someObject.setDate(LocalDate.of(year, month, FIRST_DAY_OF_MONTH));
                someObject.setRate(gasolineValue);
//                someObject.setDieselPrice(dieselValue);
                petrolObjectsList.add(someObject);
            }
        }
//        for(PetrolRates o : petrolObjectsList){
//            System.out.println("Petrol:  " + o.getDate() + "  " + o.getGasolinePrice() + "  " + o.getDieselPrice());
//        }
    }

    @Test
    public void costCount_should_count_the_cost_of_the_trip_when_using_USD_and_gasoline(){
        double currencyPriceDate1 = 0;
        double currencyPriceDate2 = 0;
        double fuelPriceDate1 = 0;
        double fuelPriceDate2 = 0;
        List<CurrencyRates> currencyObjectsList = this.currencyObjectsList;
        List<PetrolRates> petrolObjectsList = this.petrolObjectsList;
        int iterator1 = 0;
        int iterator2 = 0;
        int iterator3 = 0;
        int iterator4 = 0;
        for(PetrolRates o2 : petrolObjectsList){
            for(CurrencyRates o1: currencyObjectsList) {
                if (o1.getDate().getYear() == o2.getDate().getYear()) {
                    if (START_DATE.getMonth() == o1.getDate().getMonth()) {
                        currencyPriceDate1 += o1.getRate();
                        iterator1++;
                    }
                    if (END_DATE.getMonth() == o1.getDate().getMonth()) {
                        currencyPriceDate2 += o1.getRate();
                        iterator2++;
                    }
                    if (FUEL_TYPE.equals("gasoline")){
                        if (START_DATE.getMonth() == o2.getDate().getMonth()) {
                            fuelPriceDate1 += o2.getRate();
                            iterator3++;
                        }
                        if (END_DATE.getMonth() == o2.getDate().getMonth()) {
                            fuelPriceDate2 += o2.getRate();
                            iterator4++;
                        }
                    }
//                    else{
//                        if (START_DATE.getMonth() == o2.getDate().getMonth()) {
//                            fuelPriceDate1 += o2.getDieselPrice();
//                            iterator3++;
//                        }
//                        if (END_DATE.getMonth() == o2.getDate().getMonth()) {
//                            fuelPriceDate2 += o2.getDieselPrice();
//                            iterator4++;
//                        }
//                    }
                }
            }
        }
        currencyPriceDate1 = currencyPriceDate1 / iterator1;
        currencyPriceDate2 = currencyPriceDate2 / iterator2;
        fuelPriceDate1 = fuelPriceDate1 / iterator3;
        fuelPriceDate2 = fuelPriceDate2 / iterator4;

        LOGGER.debug("Average currency price for the start date is: [{}]", currencyPriceDate1);
        LOGGER.debug("Average currency price for the end date is: [{}]", currencyPriceDate2);
        LOGGER.debug("Average fuel price for the start date is: [{}]", fuelPriceDate1);
        LOGGER.debug("Average fuel price for the end date is: [{}]", fuelPriceDate2);

        double d = ((currencyPriceDate1 + currencyPriceDate2) / 2) *
                ((fuelPriceDate1 + fuelPriceDate2) / 2) *
                (1000 / 100) * 5;
        LOGGER.debug("The estimated cost will be: [{}] PLN", d);
        assertEquals(765.93,d, 0.01);
    }

    @After
    public void setOff() throws Exception{
        currencyObjectsList.clear();
        petrolObjectsList.clear();
    }
}
