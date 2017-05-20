package com.infoshareacademy.jjdd1.teamerror.dataBase;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Singleton
public class SavingAdminBase {

    @PersistenceContext
    EntityManager entityManager;

    AdminBase adminBase = new AdminBase();

    public void addAdminToAdminBase(String adminName, String adminPassword) {
        adminBase.setAdminName(adminName);
        adminBase.setAdminPassword(adminPassword);
        entityManager.getTransaction().begin();
        entityManager.persist(adminBase);
        entityManager.getTransaction().commit();
//
//          Query query = entityManager.createQuery("INSERT INTO AdminBase (adminName, adminPassword) VALUES (?,?)");
//           int result = query.setParameter(1, adminName).executeUpdate();
    }

    public List<String> getListOfAdmins(){
        return entityManager.createQuery("SELECT ab.adminName FROM AdminBase ab"
                , String.class).setMaxResults(10).getResultList();
    }

    @PostConstruct
    public void setAdmins() {
        adminBase.setAdminName("TE");
        adminBase.setAdminPassword("TE");
        entityManager.getTransaction().begin();
        entityManager.persist(adminBase);
        entityManager.getTransaction().commit();
    }
}
