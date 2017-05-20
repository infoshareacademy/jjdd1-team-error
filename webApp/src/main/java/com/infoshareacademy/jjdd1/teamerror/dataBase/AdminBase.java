package com.infoshareacademy.jjdd1.teamerror.dataBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AdminBase {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String adminMail;

//    @Column
//    private String adminPassword;

    public AdminBase(){
    }

    public AdminBase(String adminMail) {
        this.adminMail = adminMail;
    }

    public String getAdminMail() {
        return adminMail;
    }

    public void setAdminMail(String adminName) {
        this.adminMail = adminName;
    }

//    public String getAdminPasswordPassword() {
//        return adminPassword;
//    }
//
//    public void setAdminPassword(String adminPassword) {
//        this.adminPassword = adminPassword;
//    }
}
