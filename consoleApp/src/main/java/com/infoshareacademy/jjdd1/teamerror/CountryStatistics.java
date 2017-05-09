package com.infoshareacademy.jjdd1.teamerror;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by samulilaine on 27/04/2017.
 */
public class CountryStatistics {

    private Map<String,Long> statistics = new HashMap<>();

    public static void main(String args[]) {
//
//       Map<String, Long> country;
//
//       String getCountry = new String("USA");
//
//        HashMap<String, Long> countCountry = new HashMap<String, Long>();
//
//        countCountry.put ("USA", (long) 0);
//        countCountry.put("Croatia", (long) 0);
//        countCountry.put("France", (long) 0);
//
//        //System.out.println(countCountry);
//
//        for (String key : countCountry.keySet()){
//
//           // System.out.println(key);
//
//           if(!countCountry.containsKey(getCountry)) {
//                System.out.println(getCountry);
//
//            } else
//               System.out.println(key);
//        }
//
//        for (Long value : countCountry.values()) {
//            System.out.println(value);
//    }

    }

    public Map<String, Long> getStatistics() {
        return statistics;
    }

    public void selectedCountry(String countryCode) {

        Long countryCounter = statistics.getOrDefault(countryCode, 0l);
        statistics.put(countryCode, ++countryCounter);




    }






    }






//        if(!getCountry().containsKey(country) {
//        country.put(c);





