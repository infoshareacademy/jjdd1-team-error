package com.infoshareacademy.jjdd1.teamerror.dataBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by sebastianlos on 23.04.2017.
 */
@Entity
public class PromotedCountry {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    public PromotedCountry() {
    }

    public PromotedCountry(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
