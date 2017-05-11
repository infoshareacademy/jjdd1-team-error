package com.infoshareacademy.jjdd1.teamerror.dataBase;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@Singleton
@Startup
public class SavingFuelTypeStatistics {

    @PersistenceContext
    EntityManager entityManager;



    @PostConstruct
    public void setFuelType() {
        FuelTypeStatistics diesel = new FuelTypeStatistics("diesel", 0);
        entityManager.persist(diesel);
        FuelTypeStatistics gasoline = new FuelTypeStatistics("gasoline", 0);
        entityManager.persist(gasoline);
    }


}
