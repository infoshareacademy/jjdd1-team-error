package com.infoshareacademy.jjdd1.teamerror;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by samulilaine on 27/04/2017.
 */
public class CountryStatistics {


    public static void main(String args[]) {

       Map<String, Long> country;

       String getCountry = new String("Croatia");

        HashMap<String, Long> countCountry = new HashMap<String, Long>();

        countCountry.put ("USA", (long) 0);
        countCountry.put("Croatia", (long) 0);
        countCountry.put("France", (long) 0);

        //System.out.println(countCountry);

        for (String key : countCountry.keySet()){

           // System.out.println(key);

           if(!countCountry.containsKey(getCountry)) {
                System.out.println(getCountry);
            } else
               System.out.println(key);
        }


    }





















//    public Map<String, Long> getCountry() {
//        return country;
//    }
//
//    public void countryCounter(String) {
//        if(!getCountry().containsKey(country) {
//        country.put(c);
//
//        }
//
//        this.country=country;
//    }



}
