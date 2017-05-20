package com.infoshareacademy.jjdd1.teamerror.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sebastianlos on 18.05.17.
 */
public class PetrolMapWrapper {
    Map<String, Integer> petrolStatistics = new HashMap<>();

    public PetrolMapWrapper() {
    }

    public Map<String, Integer> getPetrolStatistics() {
        return petrolStatistics;
    }

    public void setPetrolStatistics(Map<String, Integer> petrolStatistics) {
        this.petrolStatistics = petrolStatistics;
    }
}
