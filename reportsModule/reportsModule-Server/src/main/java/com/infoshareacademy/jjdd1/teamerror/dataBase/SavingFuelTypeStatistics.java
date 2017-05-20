package com.infoshareacademy.jjdd1.teamerror.dataBase;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Singleton
public class SavingFuelTypeStatistics {

    @PersistenceContext
    EntityManager entityManager;


    public Map<String, Integer> getPetrolStatistics(){

        List<String> types =  entityManager.createQuery("SELECT p.fuelType FROM FuelTypeStatistics p ",
                String.class).getResultList();
        List<Integer> values = entityManager.createQuery("SELECT p.popularity FROM FuelTypeStatistics p ",
                Integer.class).getResultList();
        Map<String, Integer> results = new LinkedHashMap<>();
        for (int i = 0; i < types.size(); i++) {
            results.put(types.get(i), values.get(i));
        }
        return results;
    }

    public void updatePetrolStatistics(String fuelType){

        Map<String, Integer> fuelTypes = getPetrolStatistics();
        if (!fuelTypes.isEmpty() && fuelTypes.containsKey(fuelType)) {
            Query query = entityManager.createQuery("UPDATE FuelTypesStatistics p SET p.popularity = p.popularity + 1" +
                    "WHERE p.fuelType = ?1");
            query.setParameter(1, fuelType).executeUpdate();
        }
        else {
            entityManager.persist(new CountryStatistics(fuelType, 1));
        }
    }
}
