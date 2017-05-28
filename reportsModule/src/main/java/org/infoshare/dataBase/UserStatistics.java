package org.infoshare.dataBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by igafalkowska on 22.05.17.
 */
@Entity
public class UserStatistics {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String userFirstName;

    @Column
    private String userSecondName;

    @Column
    private String email;

    @Column
    private String recentLoginDate;

    @Column
    private String recentLoginTime;

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserSecondName() {
        return userSecondName;
    }

    public String getEmail() {
        return email;
    }

    public String getRecentLoginDate() {
        return recentLoginDate;
    }

    public String getRecentLoginTime() {
        return recentLoginTime;
    }

    public UserStatistics(String userFirstName, String userSecondName, String email, String recentLoginDate, String recentLoginTime) {
        this.userFirstName = userFirstName;
        this.userSecondName = userSecondName;
        this.email = email;
        this.recentLoginDate = recentLoginDate;
        this.recentLoginTime = recentLoginTime;
    }

    public UserStatistics() {
    }

    @Override
    public String toString() {
        return "UserInformation{" +
                "userFirstName='" + userFirstName + '\'' +
                ", userSecondName='" + userSecondName + '\'' +
                ", email='" + email + '\'' +
                ", recentLoginDate=" + recentLoginDate +
                ", recentLoginTime=" + recentLoginTime +
                '}';
    }
}
