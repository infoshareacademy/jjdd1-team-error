package org.infoshare.dataBase;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Iga on 14.05.2017.
 */

@Singleton
public class SavingCurrencyStatistics {

    @PersistenceContext
    private EntityManager entityManager;

    public void updateCurrencyStatistics(String currency) {
        Map<String, Integer> currencies = getCurrenciesStatistics();
        if (!currencies.isEmpty() && currencies.containsKey(currency)) {
            Query query = entityManager.createQuery("UPDATE CurrencyStatistics cs SET cs.popularity = cs.popularity + 1" +
                    "WHERE cs.currency = ?1");
            query.setParameter(1, currency).executeUpdate();
        }
        else {
            entityManager.persist(new CurrencyStatistics(currency, 1));
        }
    }

    public Map<String, Integer> getCurrenciesStatistics(){
        List<String> names =  entityManager.createQuery("SELECT cs.currency FROM CurrencyStatistics cs ",
                String.class).getResultList();
        List<Integer> values = entityManager.createQuery("SELECT cs.popularity FROM CurrencyStatistics cs ",
                Integer.class).getResultList();
        Map<String, Integer> results = new LinkedHashMap<>();
        if (names != null && values != null) {
            for (int i = 0; i < names.size(); i++) {
                results.put(names.get(i), values.get(i));
            }
        }
        return results;
    }
}
