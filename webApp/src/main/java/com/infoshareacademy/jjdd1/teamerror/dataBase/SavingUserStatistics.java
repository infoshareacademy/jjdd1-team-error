package com.infoshareacademy.jjdd1.teamerror.dataBase;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by igafalkowska on 22.05.17.
 */

@Singleton
public class SavingUserStatistics {

    @PersistenceContext
    EntityManager entityManager;

    public void updateUserStatistics(String email, LocalDate localDate){
        Query query = entityManager.createQuery("UPDATE UserStatistics us SET us.recentLoginDate = ?1 WHERE " +
                "us.email =?2");
        int result = query.setParameter(1, localDate).setParameter(2, email).executeUpdate();
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

    public List<LocalDateTime> getListOfUsersRecentLocalDateTime() {
        return entityManager.createQuery("SELECT us.recentLoginDateTime FROM UserStatistics us").getResultList();
    }

    public void setOrUpdateUser(String firstName, String secondName, String email, LocalDateTime localDateTime){
        if (!getListOfUsersEmails().contains(email)) {
            UserStatistics userStatistics = new UserStatistics(firstName, secondName, email, localDateTime);
            entityManager.persist(userStatistics);
        }
        else {
            Query query = entityManager.createQuery("UPDATE UserStatistics us SET us.recentLoginDateTime = ?1 WHERE " +
                    "us.email =?2");
            int result = query.setParameter(1, localDateTime).setParameter(2, email).executeUpdate();
        }
    }
}
