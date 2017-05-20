package com.infoshareacademy.jjdd1.teamerror.dataBase;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Singleton
//@Startup
public class SavingFuelTypeStatistics {

    @PersistenceContext
    EntityManager entityManager;


    public int getPopularity(String fuelType){
        return entityManager.createQuery("SELECT p.popularity from FuelTypeStatistics p " +
                "WHERE p.fuelType = ?1", Integer.class).setParameter(1, fuelType).getSingleResult();
    }

    public void updatePopularity(String fuelType){
       Query query =  entityManager.createQuery("UPDATE FuelTypeStatistics p SET p.popularity = p.popularity + 1" +
                "WHERE p.fuelType = ?1 ");
       int result = query.setParameter(1, fuelType).executeUpdate();
    }


    @PostConstruct
    public void setFuelType() {
        FuelTypeStatistics diesel = new FuelTypeStatistics("diesel", 0);
        entityManager.persist(diesel);
        FuelTypeStatistics gasoline = new FuelTypeStatistics("gasoline", 0);
        entityManager.persist(gasoline);
    }


}
