package com.infoshareacademy.jjdd1.teamerror.server;

import javax.xml.ws.Endpoint;

/**
 * Created by sebastianlos on 19.05.17.
 */
public class Main {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9996/statistics", new ReportsServiceImpl());
        System.out.println("Server started...");
    }
}
