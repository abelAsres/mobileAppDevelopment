package com.map524.anothermovieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements NetworkingService.NetworkingListener {

    NetworkingService networkingManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkingManager = ((myApp) getApplication()).getNetworkingService();
        //listen for background thread to complete
        networkingManager.networkingListener= this;
        getMoviesByGenre(28);
    }

    public void getMoviesByGenre(int id){
        //TODO 3: need to handle return movies from networking service
        networkingManager.getMovieByGenre(id);
    }

    @Override
    public void dataListener(String jsonString) {
        System.out.println(jsonString);
    }
}