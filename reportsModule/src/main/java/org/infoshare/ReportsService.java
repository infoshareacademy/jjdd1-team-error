package org.infoshare;

import org.infoshare.dataBase.SavingCountryStatistics;
import org.infoshare.dataBase.SavingCurrencyStatistics;
import org.infoshare.dataBase.SavingFuelTypeStatistics;
import org.infoshare.dataBase.SavingUserStatistics;
import org.infoshare.dataBase.UserStatistics;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Path("/")
public class ReportsService {

    @Inject
    private SavingCountryStatistics savingCountryStatistics;

    @Inject
    private SavingCurrencyStatistics savingCurrencyStatistics;

    @Inject
    private SavingFuelTypeStatistics savingFuelTypeStatistics;

    @Inject
    private SavingUserStatistics savingUserStatistics;


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

    @POST
    @Path("/statisticsUserUpdate")
    public Response updateUserStatistics(@FormParam("userFirstName") String firstName,
                                         @FormParam("userSecondName") String secondName,
                                         @FormParam("email") String email,
                                         @FormParam("recentLoginDate") String localDate,
                                         @FormParam("recentLoginTime") String localTime
    ) {
        savingUserStatistics.setOrUpdateUser(firstName, secondName, email, LocalDate.parse(localDate), LocalTime.parse(localTime));

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
        return Response.ok(petrolStatistics).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/userStatistics")
    public Response getUserStatistics() {
        List<UserStatistics> userStatisticsList = savingUserStatistics.getListOfUsers();
        return Response.ok(userStatisticsList).build();
    }
}