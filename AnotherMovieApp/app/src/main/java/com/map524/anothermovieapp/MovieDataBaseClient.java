package com.map524.anothermovieapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MovieDataBaseClient {
    static MovieDatabase dbClient;

    interface DatabaseActionListener{
        public void databaseReturnAllMoviesList(List<Movie> movieList);
    }
    public DatabaseActionListener databaseListener;


    public static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);
    //gives access to main thread
    Handler handler = new Handler(Looper.getMainLooper());

    private static void BuildDBInstance (Context context) {
        dbClient = Room.databaseBuilder(context,MovieDatabase.class,"database-movies").build();
    }

    public static MovieDatabase getDBInstance(Context context){
        if (dbClient == null){
            BuildDBInstance(context);
        }
        return dbClient;
    }

    //needs to run in background thread otherwise activity will lock
    public void insertNewMovie(Movie movie){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if (dbClient.getMovieDao().checkIfMovieInWatchList(movie.getMovieId()) == false){
                    dbClient.getMovieDao().insert(movie);
                }
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
