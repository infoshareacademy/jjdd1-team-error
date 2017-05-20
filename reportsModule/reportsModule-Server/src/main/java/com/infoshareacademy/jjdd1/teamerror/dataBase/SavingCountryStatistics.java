package com.infoshareacademy.jjdd1.teamerror.dataBase;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by igafalkowska on 12.05.17.
 */
@Stateless
public class SavingCountryStatistics {

    @PersistenceContext(unitName = "ReportsSoapServer")
    private EntityManager entityManager;

    public void updateCountryStatistics(String country) {
        Map<String, Integer> countries = getCountryStatistics();
        if (!countries.isEmpty() && countries.containsKey(country)) {
            Query query = entityManager.createQuery("UPDATE CountryStatistics cs SET cs.popularity = cs.popularity + 1" +
                    "WHERE cs.country = ?1");
            query.setParameter(1, country).executeUpdate();
        }
        else {
            entityManager.persist(new CountryStatistics(country, 1));
        }
    }


    public Map<String, Integer> getCountryStatistics(){
        List<String> names = new ArrayList<>();
        if (entityManager.createQuery("SELECT cs.country FROM CountryStatistics cs ",
                String.class) != null) {
            names = entityManager.createQuery("SELECT cs.country FROM CountryStatistics cs ",
                    String.class).getResultList();
        }
        List<Integer> values = entityManager.createQuery("SELECT cs.popularity FROM CountryStatistics cs ",
                Integer.class).getResultList();
        Map<String, Integer> results = new LinkedHashMap<>();
        for (int i = 0; i < names.size(); i++) {
            results.put(names.get(i), values.get(i));
        }
        return results;
    }

    @PostConstruct
    public void setUpTable() {
        entityManager.persist(new CountryStatistics("USA", 2));
        System.out.println("Post contruct");
    }

}
