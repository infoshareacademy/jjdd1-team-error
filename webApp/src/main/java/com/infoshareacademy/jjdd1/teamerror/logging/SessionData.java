package com.infoshareacademy.jjdd1.teamerror.logging;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by igafalkowska on 18.05.17.
 */

@Named
@SessionScoped
public class SessionData implements Serializable{
    private String userFirstName;
    private String userSecondName;
    private String email;
    private boolean logged = false;

    public void logUser(String userFirstName, String userSecondName, String email){
        this.userFirstName = userFirstName;
        this.userSecondName = userSecondName;
        this.email = email;
        this.logged = true;
    }

    public void logoutUser(){
        userFirstName = null;
        userSecondName = null;
        email = null;
        logged = false;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserSecondName() {
        return userSecondName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isLogged() {
        return logged;
    }
}
