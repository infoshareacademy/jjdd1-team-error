package org.infoshare.dataBase;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by igafalkowska on 22.05.17.
 */

@Singleton
public class SavingUserStatistics {

    @PersistenceContext
    EntityManager entityManager;

    public void setOrUpdateUser(String firstName, String secondName, String email, String localDate, String localTime) {
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

    public List<List<String>> getListOfUsers(){
        TypedQuery<UserStatistics> typedQuery = entityManager.createQuery
                ("SELECT NEW UserStatistics(us.userFirstName, us.userSecondName, us.email, us.recentLoginDate, us.recentLoginTime)" +
                " FROM UserStatistics us" , UserStatistics.class);

        List<UserStatistics> userStatisticsList =  typedQuery.getResultList();
        List<List<String>> userList = new ArrayList<>();
        for (UserStatistics user: userStatisticsList) {
            userList.add(Arrays.asList(user.getUserFirstName(), user.getUserSecondName(), user.getEmail(), user.getRecentLoginDate(),
                    user.getRecentLoginTime()));
        }
        return userList;
    }

    public UserStatistics getUsersInformation(String email){
        TypedQuery<UserStatistics> typedQuery = entityManager.createQuery
                ("SELECT NEW UserStatistics(us.userFirstName, us.userSecondName, us.email, us.recentLoginDate, us.recentLoginTime)" +
                        " FROM UserStatistics us WHERE us.email=?1" , UserStatistics.class);
        return typedQuery.setParameter(1, email).getSingleResult();
    }

}
