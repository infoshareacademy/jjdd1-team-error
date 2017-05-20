package com.infoshareacademy.jjdd1.teamerror.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by sebastian_los on 18.05.17.
 */
@WebService
public interface ReportsService {

    @WebMethod
    void updatePetrolStatistics(@WebParam(name = "fuelType") String fuelType);

    @WebMethod
    void updateCurrencyStatistics(@WebParam(name = "currency") String currency);

    @WebMethod
    void updateCountryStatistics(@WebParam(name = "country") String country);

    @WebMethod
    CurrencyMapWrapper getCurrencyStatistics();

    @WebMethod
    CountryMapWrapper getCountryStatistics();

    @WebMethod
    PetrolMapWrapper getPetrolStatistics();
}
