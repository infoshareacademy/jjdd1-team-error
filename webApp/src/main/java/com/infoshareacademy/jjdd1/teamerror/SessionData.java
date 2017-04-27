package com.infoshareacademy.jjdd1.teamerror;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by krystianskrzyszewski on 27.04.17.
 */

@SessionScoped
public class SessionData implements Serializable {

    private String word;

    private boolean logged = false;

    public void logUser(String username){
        this.logged = true;
    }

    public boolean isLogged(){
        return logged;
    }
}
