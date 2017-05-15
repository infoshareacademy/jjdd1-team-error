package com.infoshareacademy.jjdd1.teamerror.dataBase;

import com.infoshareacademy.jjdd1.teamerror.file_loader.CachedFilesContent;
import com.infoshareacademy.jjdd1.teamerror.file_loader.CountryAndCurrency;
import com.infoshareacademy.jjdd1.teamerror.file_loader.FilesContent;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iga on 14.05.2017.
 */
@Singleton
@Startup
public class SavingCurrencyStatistics {

    @PersistenceContext
    EntityManager entityManager;

    public void updateCurrencyStatistics(String currency) {
        Query query = entityManager.createQuery("UPDATE CurrencyStatistics cs SET cs.popularity = cs.popularity + 1" +
                "WHERE cs.currency = ?1");
        int result = query.setParameter(1, currency).executeUpdate();
    }

    public int getPopularity(String currency){
        return entityManager.createQuery("SELECT cs.popularity FROM CurrencyStatistics cs " +
                "WHERE cs.currency= ?1", Integer.class).setParameter(1, currency).getSingleResult();
    }

    public List<String> getListOfCurrencies(){
        return entityManager.createQuery("SELECT cs.currency FROM CurrencyStatistics cs "
                , String.class).getResultList();
    }

    public List<Integer> getListOfPopularity(){
        return entityManager.createQuery("SELECT cs.popularity FROM CurrencyStatistics cs "
                , Integer.class).getResultList();
    }


    @PostConstruct
    public void setCurrencies() {
        FilesContent filesContent = new CachedFilesContent();
        CountryAndCurrency countryAndCurrency = new CountryAndCurrency(filesContent);
        List<String> currencies = new ArrayList<>(countryAndCurrency.getCountryAndCurrency().values());

        for(String currency: currencies){
            CurrencyStatistics currencyStatistics = new CurrencyStatistics(currency, 0);
            entityManager.persist(currencyStatistics);
        }
    }

}
