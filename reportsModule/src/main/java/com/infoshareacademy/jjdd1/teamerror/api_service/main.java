package com.infoshareacademy.jjdd1.teamerror.api_service;

import javax.xml.ws.Endpoint;

/**
 * Created by sebastianlos on 19.05.17.
 */
public class main {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/statistics", new UpdateStatistics());
    }
}
