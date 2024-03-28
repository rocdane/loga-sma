package com.loga.api;

import javax.xml.ws.Endpoint;

public class Server {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9494/manage/",new ManageService());
        Endpoint.publish("http://localhost:9494/repair",new RepairService());
        Endpoint.publish("http://localhost:9494/market/",new MarketService());
    }
}
