package com.map524.anothermovieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WatchList extends AppCompatActivity implements MovieDataBaseClient.DatabaseActionListener{

    ArrayList<Movie> listFromDB;
    ListView listOfMovies;
    MovieDataBaseClient dbClient;
    MovieDatabase movieDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);
        listOfMovies = findViewById(R.id.list_of_movies);
        movieDatabase = MovieDataBaseClient.getDBInstance(this);
        dbClient = ((myApp) getApplication()).getMovieDataBaseClient();
        dbClient.databaseListener = this;
        dbClient.getAllMovies();
    }

    @Override
    public void databaseReturnAllMoviesList(List<Movie> movieList) {
        listFromDB = new ArrayList<>(movieList);
        MovieListAdapter movieAdapter = new MovieListAdapter(listFromDB,this);
        listOfMovies.setAdapter(movieAdapter);

        listOfMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(),SingleMovie.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("movie",listFromDB.get(i));
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
        //movieAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_watchlist,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.search_by_genre: {
                startGenreActivity();
                break;
            }
            case R.id.saved_movies:{
                startSaveMoviesActivity();
                break;
            }
        }
        return true;
    }



    private void startSearchByGenreActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


    private void startGenreActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void startSaveMoviesActivity(){
        Intent intent = new Intent(this,WatchList.class);
        startActivity(intent);
    }

}