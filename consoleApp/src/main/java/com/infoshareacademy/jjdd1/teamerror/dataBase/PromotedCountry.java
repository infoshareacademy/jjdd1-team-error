package com.infoshareacademy.jjdd1.teamerror.dataBase;

/**
 * Created by sebas on 23.04.2017.
 */
//@Entity
//@Table
public class PromotedCountry {

//    @Id
//    @GeneratedValue
    private int id;
//
//    @Column
    private String name;
//
    public void setName(String name) {
        this.name = name;
    }

    public PromotedCountry(String name) {
        this.name = name;
    }
}