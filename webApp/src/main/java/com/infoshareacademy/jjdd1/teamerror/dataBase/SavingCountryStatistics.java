package com.infoshareacademy.jjdd1.teamerror.dataBase;

import com.infoshareacademy.jjdd1.teamerror.file_loader.CachedFilesContent;
import com.infoshareacademy.jjdd1.teamerror.file_loader.CountryNames;
import com.infoshareacademy.jjdd1.teamerror.file_loader.FilesContent;
import com.infoshareacademy.jjdd1.teamerror.file_loader.PromotedCountries;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

/**
 * Created by igafalkowska on 12.05.17.
 */

@Singleton
@Startup
public class SavingCountryStatistics {

    @PersistenceContext
    EntityManager entityManager;


//    public void setOrUpdateCountryStatistics(String country) {
//        try {
//            if (entityManager.createQuery("SELECT cs.country FROM CountryStatistics cs WHERE " +
//                    "cs.country = ?1", String.class).setParameter(1, country).getSingleResult() == country) {
//                Query query = entityManager.createQuery("UPDATE CountryStatistics cs SET cs.popularity = cs.popularity + 1" +
//                        "WHERE cs.country = ?1");
//                int result = query.setParameter(1, country).executeUpdate();
//            }
//        } catch (NoResultException e){
//            Query query = entityManager.createNativeQuery("INSERT INTO CountryStatistics  VALUES (?1, ?2)");
//            int result = query.setParameter(1, country).setParameter(2, 1).executeUpdate();
//        }
//
//    }

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
        PromotedCountries promotedCountries = new PromotedCountries();
        promotedCountries.setFilesContent(filesContent);
        List<String> countries = promotedCountries.getOrderedPromotedCountries();

        for(String country: countries){
            CountryStatistics countryStatistics = new CountryStatistics(country, 0);
            entityManager.persist(countryStatistics);
        }
    }

}
