package org.infoshare;

import org.infoshare.dataBase.SavingCountryStatistics;
import org.infoshare.dataBase.SavingCurrencyStatistics;
import org.infoshare.dataBase.SavingFuelTypeStatistics;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/")
public class ReportsService {

    @Inject
    private SavingCountryStatistics savingCountryStatistics;

    @Inject
    private SavingCurrencyStatistics savingCurrencyStatistics;

    @Inject
    private SavingFuelTypeStatistics savingFuelTypeStatistics;


    public ReportsService() {
    }

    @POST
    @Path("/statisticsUpdate")
    public Response updateStatistics(@FormParam("country") String country,
                                     @FormParam("currency") String currency,
                                     @FormParam("fuelType") String fuelType
    ) {
        savingCountryStatistics.updateCountryStatistics(country);
        savingCurrencyStatistics.updateCurrencyStatistics(currency);
        savingFuelTypeStatistics.updatePetrolStatistics(fuelType);

        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/currencyStatistics")
    public Response getCurrencyStatistics() {
        Map<String, Integer> currencyStatistics = savingCurrencyStatistics.getCurrenciesStatistics();
        return Response.ok(currencyStatistics).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/countryStatistics")
    public Response getCountryStatistics() {
        Map<String, Integer> countryStatistics = savingCountryStatistics.getCountryStatistics();
        return Response.ok(countryStatistics).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/petrolStatistics")
    public Response getPetrolStatistics() {
        Map<String, Integer> petrolStatistics = savingFuelTypeStatistics.getPetrolStatistics();
//        ReportsSender reportsSender = new ReportsSender();
//        reportsSender.sendReportEmail();
        return Response.ok(petrolStatistics).build();
    }
}