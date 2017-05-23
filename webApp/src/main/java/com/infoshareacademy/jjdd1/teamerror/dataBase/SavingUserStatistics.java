package com.infoshareacademy.jjdd1.teamerror.dataBase;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    public List<LocalDate> getListOfUsersRecentLocalDate() {
        return entityManager.createQuery("SELECT us.recentLoginDate FROM UserStatistics us").getResultList();
    }

    public List<LocalTime> getListOfUsersRecentLocalTime() {
        return entityManager.createQuery("SELECT us.recentLoginTime FROM UserStatistics us").getResultList();
    }

    public void setOrUpdateUser(String firstName, String secondName, String email, LocalDate localDate, LocalTime localTime) {
        if (email != null) {
            if (!getListOfUsersEmails().contains(email)) {
                UserStatistics userStatistics = new UserStatistics(firstName, secondName, email, localDate, localTime);
                entityManager.persist(userStatistics);
            } else {
                Query query = entityManager.createQuery("UPDATE UserStatistics us SET us.recentLoginDate = ?1 WHERE " +
                        "us.email =?2");
                int result = query.setParameter(1, localDate).setParameter(2, email).executeUpdate();
                Query query2 = entityManager.createQuery("UPDATE UserStatistics us SET us.recentLoginTime = ?1 WHERE " +
                        "us.email =?2");
                int result2 = query2.setParameter(1, localTime).setParameter(2, email).executeUpdate();
            }
        }
    }
}
