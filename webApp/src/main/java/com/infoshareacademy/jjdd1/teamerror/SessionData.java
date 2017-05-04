package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.file_loader.*;
import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

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
