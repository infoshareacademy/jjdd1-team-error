package org.infoshare.dataBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by sebastianlos on 23.04.2017.
 */
@Entity
public class PromotedCountriesTable {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    public PromotedCountriesTable() {
    }

    public PromotedCountriesTable(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
