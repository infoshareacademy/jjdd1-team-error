package com.infoshareacademy.jjdd1.teamerror.dataBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime recentLoginDateTime;

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserSecondName() {
        return userSecondName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getRecentLoginDateTime() {
        return recentLoginDateTime;
    }

    public UserStatistics(String userFirstName, String userSecondName, String email, LocalDateTime localDateTime) {
        this.userFirstName = userFirstName;
        this.userSecondName = userSecondName;
        this.email = email;
        this.recentLoginDateTime = localDateTime ;
    }

    public UserStatistics() {
    }
}
