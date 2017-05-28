package com.infoshareacademy.jjdd1.teamerror.dataBase;

import com.infoshareacademy.jjdd1.teamerror.WelcomeServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by sebastianlos on 23.04.2017.
 */

@Singleton
public class PromotedCountriesSaver {

    private final Logger LOGGER = LoggerFactory.getLogger(PromotedCountriesSaver.class);

    @PersistenceContext
    EntityManager entityManager;

    public List<String> getPromotedCountries() {
        return entityManager.createQuery("SELECT s.name from PromotedCountriesTable s", String.class).getResultList();
    }

    public void addCountry(String someCountry){
        Boolean present = false;
        for(String s : getPromotedCountries()){
            if(s.toUpperCase().equals(someCountry.toUpperCase())){
                present = true;
            }
        }
        if(present==false){
            PromotedCountriesTable promotedCountriesTable = new PromotedCountriesTable(someCountry);
            entityManager.persist(promotedCountriesTable);
            entityManager.flush();
        }
        else{
            entityManager.flush();
        }
    }

    public void removeCountry(String someCountry){
        entityManager.createQuery("DELETE FROM PromotedCountriesTable pct WHERE pct.name LIKE:aName")
                .setParameter("aName", someCountry)
                .executeUpdate();
    }

    @PostConstruct
    public void setCountries() {
        PromotedCountriesTable france = new PromotedCountriesTable("France");
        entityManager.persist(france);
        PromotedCountriesTable croatia = new PromotedCountriesTable("Croatia");
        entityManager.persist(croatia);
    }
}
