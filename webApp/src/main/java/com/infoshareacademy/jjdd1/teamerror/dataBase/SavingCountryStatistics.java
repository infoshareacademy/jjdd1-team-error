package com.infoshareacademy.jjdd1.teamerror.dataBase;

import com.infoshareacademy.jjdd1.teamerror.file_loader.CachedFilesContent;
import com.infoshareacademy.jjdd1.teamerror.file_loader.CountryAndCurrency;
import com.infoshareacademy.jjdd1.teamerror.file_loader.FilesContent;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by igafalkowska on 12.05.17.
 */

@Singleton
public class SavingCountryStatistics {

    @PersistenceContext
    EntityManager entityManager;

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void updateCountryStatistics(String country) {

          Query query = entityManager.createQuery("UPDATE CountryStatistics cs SET cs.popularity = cs.popularity + 1" +
                        "WHERE cs.country = ?1");
           int result = query.setParameter(1, country).executeUpdate();
    }

    public int getPopularity(String country){
        return entityManager.createQuery("SELECT cs.popularity FROM CountryStatistics cs " +
                "WHERE cs.country = ?1", Integer.class).setParameter(1, country).getSingleResult();
    }


    public List<String> getListOfCountries(){
        return entityManager.createQuery("SELECT cs.country FROM CountryStatistics cs "
                , String.class).getResultList();
    }

    public List<Integer> getListOfPopularity(){
        return entityManager.createQuery("SELECT cs.popularity FROM CountryStatistics cs "
                , Integer.class).getResultList();
    }

    @PostConstruct
    public void setCountries() {
        FilesContent filesContent = new CachedFilesContent();
        CountryAndCurrency countryAndCurrency = new CountryAndCurrency(filesContent);
        List<String> countries = new ArrayList<>(countryAndCurrency.getCountryAndCurrency().keySet());

        for(String country: countries){
            CountryStatistics countryStatistics = new CountryStatistics(country, 0);
            entityManager.persist(countryStatistics);
        }
    }

}
