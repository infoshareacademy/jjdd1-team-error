package com.infoshareacademy.jjdd1.teamerror.dataBase;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Singleton
public class SavingAdminBase {

    @PersistenceContext
    EntityManager entityManager;

    public void addAdminToAdminBase(String adminMail) {
        AdminBase adminBase = new AdminBase(adminMail);
        entityManager.persist(adminBase);
    }

    public List<String> getListOfAdmins(){
        return entityManager.createQuery("SELECT ab.adminMail FROM AdminBase ab"
                , String.class).getResultList();
    }

    @PostConstruct
    public void setAdmins() {
        AdminBase adminBase = new AdminBase("krystian.skrzyszewski@gmail.com");
        entityManager.persist(adminBase);
    }
}
