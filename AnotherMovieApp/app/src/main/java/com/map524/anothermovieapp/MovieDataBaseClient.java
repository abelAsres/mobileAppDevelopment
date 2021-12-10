package com.map524.anothermovieapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MovieDataBaseClient {
    MovieDatabase dbClient;
    Context db_context;

    interface DatabaseActionListener{
        public void databaseReturnAllMoviesList(List<Movie> movieList);
    }
    DatabaseActionListener databaseListener;


    public static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);
    //gives access to main thread
    Handler handler = new Handler(Looper.getMainLooper());

    MovieDataBaseClient (Context context){
        db_context = context;
        //instance of a database
        dbClient = Room.databaseBuilder(context,MovieDatabase.class,"database-movies").build();
    }

    public MovieDatabase getDBClient(){
        if(dbClient == null){
            dbClient = new MovieDataBaseClient(db_context).dbClient;
        }

        return dbClient;
    }

    //needs to run in background thread otherwise activity will lock
    public void insertNewMovie(Movie movie){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dbClient.getMovieDao().insert(movie);
            }
        });
    }

    public void getAllMovies(){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List <Movie> movies = dbClient.getMovieDao().getAll();
                //will run on thread attached to the handler
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //run on main thread
                        databaseListener.databaseReturnAllMoviesList(movies);
                    }
                });
            }
        });
    }
}
