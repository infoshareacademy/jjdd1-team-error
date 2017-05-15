package com.infoshareacademy.jjdd1.teamerror.dataBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by igafalkowska on 11.05.17.
 */
@Entity
public class CountryStatistics {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String country;

    @Column
    private int popularity;

    public CountryStatistics(String country, int popularity) {
        this.country = country;
        this.popularity = popularity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
