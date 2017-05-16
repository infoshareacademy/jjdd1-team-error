package com.infoshareacademy.jjdd1.teamerror.dataBase;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.*;
import java.util.List;

/**
 * Created by sebastianlos on 23.04.2017.
 */

@Singleton
//@Startup
public class SavingClass {

    @PersistenceContext
    EntityManager entityManager;

    public List<String> getPromotedCountries() {
        return entityManager.createQuery("SELECT s.name from PromotedCountriesTable s", String.class).getResultList();
    }

    @PostConstruct
    public void setCountries() {
        PromotedCountriesTable france = new PromotedCountriesTable("France");
        entityManager.persist(france);
        PromotedCountriesTable croatia = new PromotedCountriesTable("Croatia");
        entityManager.persist(croatia);
    }
}
