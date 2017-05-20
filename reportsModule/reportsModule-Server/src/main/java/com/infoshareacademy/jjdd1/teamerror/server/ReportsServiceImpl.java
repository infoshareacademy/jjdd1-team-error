package com.infoshareacademy.jjdd1.teamerror.server;

import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingCountryStatistics;
import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingCurrencyStatistics;
import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingFuelTypeStatistics;
import com.infoshareacademy.jjdd1.teamerror.service.CountryMapWrapper;
import com.infoshareacademy.jjdd1.teamerror.service.CurrencyMapWrapper;
import com.infoshareacademy.jjdd1.teamerror.service.PetrolMapWrapper;
import com.infoshareacademy.jjdd1.teamerror.service.ReportsService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.WebService;
import java.util.Map;

/**
 * Created by sebastianlos on 19.05.17.
 */



@WebService(endpointInterface = "com.infoshareacademy.jjdd1.teamerror.service.ReportsService")
public class ReportsServiceImpl implements ReportsService {

    @EJB
    private SavingCountryStatistics savingCountryStatistics;

    private SavingCurrencyStatistics savingCurrencyStatistics = new SavingCurrencyStatistics();
//    private SavingCountryStatistics savingCountryStatistics = new SavingCountryStatistics();
    private SavingFuelTypeStatistics savingFuelTypeStatistics = new SavingFuelTypeStatistics();


    @Override
    public void updateCurrencyStatistics(String currency) {
        savingCurrencyStatistics.updateCurrencyStatistics(currency);
    }

    @Override
    public void updateCountryStatistics(String country) {
        savingCountryStatistics.setUpTable();
        savingCountryStatistics.updateCountryStatistics(country);
    }

    @Override
    public void updatePetrolStatistics(String fuelType) {
        savingFuelTypeStatistics.updatePetrolStatistics(fuelType);
    }

    @Override
    public CurrencyMapWrapper getCurrencyStatistics() {
        Map<String, Integer> currencyStatistics = savingCurrencyStatistics.getCurrenciesStatistics();
        CurrencyMapWrapper currencyMapWrapper = new CurrencyMapWrapper();
        currencyMapWrapper.setCurrencyStatistics(currencyStatistics);
        return currencyMapWrapper;
    }

    @Override
    public CountryMapWrapper getCountryStatistics() {
        Map<String, Integer> countryStatistics = savingCountryStatistics.getCountryStatistics();
        CountryMapWrapper countryMapWrapper = new CountryMapWrapper();
        countryMapWrapper.setCountryStatistics(countryStatistics);
        return countryMapWrapper;
    }

    @Override
    public PetrolMapWrapper getPetrolStatistics() {
        Map<String, Integer> petrolStatistics = savingFuelTypeStatistics.getPetrolStatistics();
        PetrolMapWrapper petrolMapWrapper = new PetrolMapWrapper();
        petrolMapWrapper.setPetrolStatistics(petrolStatistics);
        return petrolMapWrapper;
    }
}
