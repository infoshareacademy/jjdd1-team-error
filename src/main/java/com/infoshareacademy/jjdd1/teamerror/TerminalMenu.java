package com.infoshareacademy.jjdd1.teamerror;

import sun.misc.MessageUtils;

import static com.infoshareacademy.jjdd1.teamerror.FileReader.fileFilter;
import static com.infoshareacademy.jjdd1.teamerror.FileReader.loadContent;

/**
 * Created by igafalkowska on 31.03.17.
 */
public class TerminalMenu {
    public static void main(String[] args) {
        // FileReader test
        System.out.println(loadContent("EUR"));

        String[][] output = fileFilter(loadContent("EUR"));
        for(int i=0; i<output.length; i++){
            for(int j=0; j<output[i].length; j++){
                System.out.println(output[i][j]);
            }
        }

    }
}
