package com.infoshareacademy.jjdd1.teamerror.api_service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by sebastianlos on 19.05.17.
 */
@WebService
public class UpdateStatistics {

    @WebMethod
    public void updateCurrencyStatistics(String currency) {

    }

    @WebMethod
    public void updateCountryStatistics(String country) {

    }

    @WebMethod
    public void updatePetrolStatistics(String currency) {

    }

    @WebMethod
    public CurrencyMapWrapper getCurrencyStatistics() {
        return new CurrencyMapWrapper();
    }

    @WebMethod
    public CountryMapWrapper getCountryStatistics() {
        return new CountryMapWrapper();
    }

    @WebMethod
    public PetrolMapWrapper getPetrolStatistics() {
        return new PetrolMapWrapper();
    }
}
