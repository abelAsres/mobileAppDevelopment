package com.map524.anothermovieapp;

import android.app.Application;

public class myApp extends Application {
    private NetworkingService networkingService;

    public NetworkingService getNetworkingService(){
        return this.networkingService;
    }
}
