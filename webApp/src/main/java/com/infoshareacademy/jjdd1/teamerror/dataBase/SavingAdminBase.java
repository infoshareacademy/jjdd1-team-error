package com.infoshareacademy.jjdd1.teamerror.dataBase;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.DELETE;
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

    public void addAdmin(String someMail){
        AdminBase adminBase = new AdminBase(someMail);
        entityManager.persist(adminBase);
    }

    public void removeAdmin(String someMail){
        String query = "DELETE FROM AdminBase ab WHERE ab.adminMail LIKE %$" + someMail + "%";
        entityManager.createQuery("DELETE FROM AdminBase ab WHERE ab.adminMail LIKE:anEmail")
                .setParameter("anEmail", someMail)
                .executeUpdate();
    }

    @PostConstruct
    public void setAdmins() {
        AdminBase adminBase = new AdminBase("krystian.skrzyszewski@gmail.com");
        entityManager.persist(adminBase);
    }
}
