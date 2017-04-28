package com.infoshareacademy.jjdd1.teamerror.logging;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by igafalkowska on 28.04.17.
 */
@Named
@SessionScoped
public class SessionData implements Serializable{
    private boolean logged = false;
    private String username;

    public void logUser(String username) {
        this.username = username;
        this.logged = true;
    }

    public boolean isLogged() {
        return logged;
    }

    public String getUsername() {
        return username;
    }
}
