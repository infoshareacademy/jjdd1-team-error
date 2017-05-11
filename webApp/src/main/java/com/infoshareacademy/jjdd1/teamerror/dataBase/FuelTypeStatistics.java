package com.infoshareacademy.jjdd1.teamerror.dataBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by igafalkowska on 11.05.17.
 */
@Entity
public class FuelTypeStatistics {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String fuelType;

    @Column
    private int popularity;

    public FuelTypeStatistics(String fuelType, int popularity) {
        this.fuelType = fuelType;
        this.popularity = popularity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}

