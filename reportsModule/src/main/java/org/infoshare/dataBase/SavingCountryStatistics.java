package org.infoshare.dataBase;


import javax.ejb.Singleton;
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
@Singleton
public class SavingCountryStatistics {

    @PersistenceContext
    private EntityManager entityManager;

    public void updateCountryStatistics(String country) {
        Map<String, Integer> countries = getCountryStatistics();
        if (!countries.isEmpty() && countries.containsKey(country)) {
            Query query = entityManager.createQuery("UPDATE CountryStatistics cs SET " +
                    "cs.popularity = cs.popularity + 1 WHERE cs.country = ?1");
            query.setParameter(1, country).executeUpdate();
        }
        else {
            entityManager.persist(new CountryStatistics(country, 1));
        }
    }


    public Map<String, Integer> getCountryStatistics(){
        List<String> names = entityManager.createQuery("SELECT cs.country " +
                        "FROM CountryStatistics cs ORDER BY cs.popularity DESC", String.class)
                .setMaxResults(10).getResultList();
        List<Integer> values = entityManager.createQuery("SELECT cs.popularity " +
                        "FROM CountryStatistics cs ORDER BY cs.popularity DESC", Integer.class)
                .setMaxResults(10).getResultList();
        Map<String, Integer> results = new LinkedHashMap<>();
        if (names != null && values != null) {
            for (int i = 0; i < names.size(); i++) {
                results.put(names.get(i), values.get(i));
            }
        }
        return results;
    }
}
