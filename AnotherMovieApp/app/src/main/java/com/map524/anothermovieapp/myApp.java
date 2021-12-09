package com.map524.anothermovieapp;

import android.app.Application;

public class myApp extends Application {
    private NetworkingService networkingService = new NetworkingService();
    private JsonService jsonService = new JsonService();

    public NetworkingService getNetworkingService(){
        return this.networkingService;
    }
    public JsonService getJsonService(){return this.jsonService;}
}
