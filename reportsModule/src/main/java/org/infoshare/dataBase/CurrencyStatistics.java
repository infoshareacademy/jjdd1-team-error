package org.infoshare.dataBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by igafalkowska on 11.05.17.
 */

@Entity
public class CurrencyStatistics {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String currency;

    @Column
    private int popularity;

    public CurrencyStatistics(String currency, int popularity) {
        this.currency = currency;
        this.popularity = popularity;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
