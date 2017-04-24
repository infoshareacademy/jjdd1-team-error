package com.infoshareacademy.jjdd1.teamerror.dataBase;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sebastianlos on 23.04.2017.
 */
public class SavingClass {
    @PersistenceContext
    private static EntityManager entityManager;

//    public List<String> getPromotedCountries()
//    {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
//        entityManager = entityManagerFactory.createEntityManager();
//
//        Query q = entityManager.createQuery("SELECT name from PromotedCountries s");
//
//        return q.getResultList();
//    }

    public void setCountries() {
        PromotedCountry france = new PromotedCountry("France");
        entityManager.persist(france);
        PromotedCountry croatia = new PromotedCountry("Croatia");
        entityManager.persist(croatia);

        entityManager.getTransaction().commit();
    }
}
