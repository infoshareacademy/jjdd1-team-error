package com.infoshareacademy.jjdd1.teamerror;

import java.util.Map;

import static com.infoshareacademy.jjdd1.teamerror.FileReader.*;

/**
 * Created by igafalkowska on 31.03.17.
 */
public class TerminalMenu {
    public static void main(String[] args) {

        String[][] output = loadCurrencyFile("Zar");
        for(int i=1; i<output.length; i++){
            for(int j=0; j<output[i].length; j++){
                System.out.print(output[i][j] + " ");
            }
            System.out.println();
        }
    }
}
