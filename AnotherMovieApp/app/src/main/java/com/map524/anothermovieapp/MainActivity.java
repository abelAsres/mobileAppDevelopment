package com.map524.anothermovieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NetworkingService.NetworkingListener {

    NetworkingService networkingManager;
    JsonService jsonService;
    ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkingManager = ((myApp) getApplication()).getNetworkingService();
        jsonService = ((myApp) getApplication()).getJsonService();
        //listen for background thread to complete
        networkingManager.networkingListener= this;
        getMoviesByGenre(28,2);
        movies = new ArrayList<Movie>();
    }

    public void getMoviesByGenre(int id){
        //TODO 3: need to handle return movies from networking service
        networkingManager.getMovieByGenre(id,1);
    }

    public void getMoviesByGenre(int id,int genrePage){
        //TODO 3: need to handle return movies from networking service
        networkingManager.getMovieByGenre(id,genrePage);
    }

    @Override
    public void dataListener(String jsonString) {
        System.out.println(jsonString);
        movies = jsonService.getMoviesFromJson(jsonString);
    }
}