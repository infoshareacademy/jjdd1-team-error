package com.infoshareacademy.jjdd1.teamerror.dataBase;

import com.infoshareacademy.jjdd1.teamerror.dataBase.PromotedCountry;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

/**
 * Created by sebastianlos on 23.04.2017.
 */
@Startup
@Stateless
public class SavingClass {

    @PersistenceContext
    EntityManager entityManager;

    public List<String> getPromotedCountries() {
        return entityManager.createQuery("SELECT s.name from PromotedCountry s", String.class).getResultList();
    }

    @PostConstruct
    public void setCountries() {
        com.infoshareacademy.jjdd1.teamerror.dataBase.PromotedCountry france = new com.infoshareacademy.jjdd1.teamerror.dataBase.PromotedCountry("France");
        entityManager.persist(france);
        com.infoshareacademy.jjdd1.teamerror.dataBase.PromotedCountry croatia = new com.infoshareacademy.jjdd1.teamerror.dataBase.PromotedCountry("Croatia");
        entityManager.persist(croatia);
    }
}
