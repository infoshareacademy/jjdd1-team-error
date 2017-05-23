package com.infoshareacademy.jjdd1.teamerror.dataBase;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by igafalkowska on 22.05.17.
 */

@Singleton
public class SavingUserStatistics {

    @PersistenceContext
    EntityManager entityManager;

    public void setOrUpdateUser(String firstName, String secondName, String email, LocalDate localDate, LocalTime localTime) {
        if (email != null) {
            if (!getListOfUsersEmails().contains(email)) {
                UserStatistics userStatistics = new UserStatistics(firstName, secondName, email, localDate, localTime);
                entityManager.persist(userStatistics);
            } else {
                Query query = entityManager.createQuery("UPDATE UserStatistics us SET us.recentLoginDate = ?1, us.recentLoginTime = ?2" +
                        " WHERE us.email =?3");
                int result = query.setParameter(1, localDate).setParameter(2, localTime).setParameter(3, email).executeUpdate();
            }
        }
    }

    public List<String> getListOfUsersEmails() {
        return entityManager.createQuery("SELECT us.email FROM UserStatistics us", String.class).getResultList();
    }

    public List<String> getListOfUsersFirstName() {
        return entityManager.createQuery("SELECT us.userFirstName FROM UserStatistics us").getResultList();
    }

    public List<String> getListOfUsersSecondName() {
        return entityManager.createQuery("SELECT us.userSecondName FROM UserStatistics us").getResultList();
    }

    public List<LocalDate> getListOfUsersRecentLocalDate() {
        return entityManager.createQuery("SELECT us.recentLoginDate FROM UserStatistics us").getResultList();
    }

    public List<LocalTime> getListOfUsersRecentLocalTime() {
        return entityManager.createQuery("SELECT us.recentLoginTime FROM UserStatistics us").getResultList();
    }

    public List<Integer> getListOfId(){
        return entityManager.createQuery("SELECT us.id FROM UserStatistics us").getResultList();
    }
//TODO correct two below methods
    public List<List<String>> getListOfUsers(){
        List users = new ArrayList();
        for (int id: getListOfId()) {
            users.add(entityManager.createQuery("SELECT us FROM UserStatistics us WHERE id=?1").setParameter(1, id).getResultList());
        }
        return users;
    }
//    public List<String> getListOfUser(){
//            return entityManager.createQuery("SELECT us FROM UserStatistics us WHERE id=?1").getResultList());
//        }
//
//    query = em.createQuery("SELECT d FROM Department d");
//    List<Department> dList = (List<Department>) query.getResultList();
//    System.out.println(dList);

}
