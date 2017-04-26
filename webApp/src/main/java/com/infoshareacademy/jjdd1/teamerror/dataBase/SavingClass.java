package com.infoshareacademy.jjdd1.teamerror.dataBase;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

/**
 * Created by sebastianlos on 23.04.2017.
 */

@Singleton
@Startup
public class SavingClass {

    @PersistenceContext
    EntityManager entityManager;

    public List<String> getPromotedCountries() {
        return entityManager.createQuery("SELECT s.name from PromotedCountries s", String.class).getResultList();
    }

    @PostConstruct
    public void setCountries() {
        PromotedCountries france = new PromotedCountries("Croatia");
        entityManager.persist(france);
        PromotedCountries croatia = new PromotedCountries("USA");
        entityManager.persist(croatia);
    }
}
