package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.CurrencyHistoryDayValue;
import com.infoshareacademy.jjdd1.teamerror.currency_petrol_data.PetrolPrices;
import com.infoshareacademy.jjdd1.teamerror.file_loader.*;
import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;
import static org.junit.Assert.*;

/**
 * Created by Krystian on 2017-04-20.
 */
@RunWith(Arquillian.class)
public class TripFullCostTest {

    private FilesContent filesContent;
    private final PetrolFileFilter petrolFileFilter = new PetrolFileFilter(filesContent);
    private final CurrencyFileFilter currencyFileFilter = new CurrencyFileFilter(filesContent);
    public TripFullCostTest(FilesContent filesContent) {
        this.filesContent = filesContent;
    }
    TripFullCost cost = TripFullCost.createTripCostObject(filesContent);
    DateParser dateParser = new DateParser();


//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }

    @Test
    public void setFuelType_should_set_up_a_fuel_type() {
    }

    @Test
    public void setFuelUsage() throws Exception {
    }

    @Test
    public void setDate1() throws Exception {
    }

    @Test
    public void setDate2() throws Exception {
    }

    @Test
    public void setCountry() throws Exception {
    }

    @Test
    public void setCurrency() throws Exception {
    }

    @Test
    public void setDistance() throws Exception {
    }

    @Test
    public void costCount_should_count_the_cost_of_the_trip_when_using_EUR(){
        double currencyPriceDate1 = 0;
        double currencyPriceDate2 = 0;
        double fuelPriceDate1 = 0;
        double fuelPriceDate2 = 0;

        List<CurrencyHistoryDayValue> currencyObjectsList = currencyFileFilter.getListOfCurrencyDataObjects("USD");
        List<PetrolPrices> petrolObjectsList = petrolFileFilter.getListOfPetrolDataObjects("USA");

        int iterator1 = 0;
        int iterator2 = 0;
        int iterator3 = 0;
        int iterator4 = 0;
        for(PetrolPrices o2 : petrolObjectsList){
            for(CurrencyHistoryDayValue o1: currencyObjectsList) {
                if (o1.getDate().getYear() == o2.getDate().getYear()) {
                    if (dateParser.DateFromString("20160303").getMonth() == o1.getDate().getMonth()) {
                        currencyPriceDate1 += o1.getClose();
                        iterator1++;
                    }
                    if (dateParser.DateFromString("20160403").getMonth() == o1.getDate().getMonth()) {
                        currencyPriceDate2 += o1.getClose();
                        iterator2++;
                    }

//                    if (cost.getFuelType().equalsIgnoreCase("gasoline")) {
                        if (dateParser.DateFromString("20160303").getMonth() == o2.getDate().getMonth()) {
                            fuelPriceDate1 += o2.getGasolinePrice();
                            iterator3++;
                        }
                        if (dateParser.DateFromString("20160403").getMonth() == o2.getDate().getMonth()) {
                            fuelPriceDate2 += o2.getGasolinePrice();
                            iterator4++;
                        }
//                    }
//                    if (cost.getFuelType().equalsIgnoreCase("diesel")) {
//                        if (cost.getDate1().getMonth() == o2.getDate().getMonth()) {
//                            fuelPriceDate1 += o2.getDieselPrice();
//                            iterator3++;
//                        }
//                        if (cost.getDate2().getMonth() == o2.getDate().getMonth()) {
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

        double d = ((currencyPriceDate1 + currencyPriceDate2) / 2) *
                ((fuelPriceDate1 + fuelPriceDate2) / 2) *
                (cost.getDistance() / 100) * cost.getFuelUsage();
        assertEquals(4665,d);

    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(TripFullCost.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
