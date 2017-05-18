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
    private String userName;
    private String email;
    private boolean logged = false;

    public void logUser(String userName, String email){
        this.userName = userName;
        this.email = email;
        this.logged = true;
    }

    public void logoutUser(){
        userName = null;
        email = null;
        logged = false;
    }


    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

}
