package com.infoshareacademy.jjdd1.teamerror;

import java.util.Map;

import static com.infoshareacademy.jjdd1.teamerror.FileReader.*;

/**
 * Created by igafalkowska on 31.03.17.
 */
public class TerminalMenu {
    public static void main(String[] args) {
//         FileReader test
//        System.out.println(loadContent("EUR"));

//        String[][] s = fileFilter(loadContent("EUR"));
//        for (int i = 0; i < s.length; i++) {
//            for (int j = 0; j < s[i].length; j++)
//                System.out.println(s[i][j]);
//        }
//        // FileReader test
//        System.out.println(loadContent("EUR"));
//
//        String[][] output = fileFilter(loadContent("EUR"));
//        for(int i=0; i<output.length; i++){
//            for(int j=0; j<output[i].length; j++){
//                System.out.println(output[i][j]);
//            }
//        }

//        String[][] s = fileFilter(loadContent("EUR"));
//        for (int i = 0; i < s.length; i++) {
//            for (int j = 0; j < s[i].length; j++)
//                System.out.println(s[i][j]);
//        }

        // unzipFile("src/main/resources/files/omeganbp.zip", "src/main/resources/files/");

        CurrencyNames.loadCurrencies();
        for (Map.Entry i : CurrencyNames.Currencies.entrySet())
            System.out.println(i.getKey() + " --> " + i.getValue());

        // removeExtractedFiles();
    }
}
