package com.infoshareacademy.jjdd1.teamerror;

import static com.infoshareacademy.jjdd1.teamerror.FileReader.fileFilter;
import static com.infoshareacademy.jjdd1.teamerror.FileReader.loadContent;
import static com.infoshareacademy.jjdd1.teamerror.FileReader.unzipFile;

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

        unzipFile("files/omeganbp.zip", "files/");
    }
}