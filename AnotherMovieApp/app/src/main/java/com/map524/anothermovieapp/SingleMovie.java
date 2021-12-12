package com.map524.anothermovieapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

public class SingleMovie extends AppCompatActivity{

    Movie movie;
    TextView movieTitle;
    TextView movieOverview;
    ImageView imageView;
    Switch switch_watchlist;
    MovieDatabase movieDatabase;
    MovieDataBaseClient movieDataBaseClient;

    Boolean fromWatchlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);

        Intent intentFromMovieListByGenre = getIntent();
        Bundle bundle = intentFromMovieListByGenre.getBundleExtra("bundle");
        movie = bundle.getParcelable("movie");

        movieDatabase = MovieDataBaseClient.getDBInstance(this);
        movieDataBaseClient = ((myApp)getApplication()).getMovieDataBaseClient();
        //movieDataBaseClient.isMovieInDB(movie.getMovieId());

        imageView = findViewById(R.id.movie_image);
        movieTitle = findViewById(R.id.movie_title);
        movieOverview = findViewById(R.id.movie_overview);
        switch_watchlist = findViewById(R.id.switch_watchlist);


        movieTitle.setText(movie.getOriginal_title());
        movieOverview.setText(movie.getOverview());
        fromWatchlist = bundle.getBoolean("fromWatchlist");
        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+movie.getPoster_path()).into(imageView);
        switch_watchlist.setChecked(fromWatchlist);
        if(switch_watchlist.isChecked()){
            switch_watchlist.setText("Remove from Watchlist");
        }else{
            switch_watchlist.setText("Add to Watchlist");
        }
        switch_watchlist.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    movieDataBaseClient.insertNewMovie(movie, movieTitle);
                    switch_watchlist.setText("Remove from Watchlist");
                }else{
                    movieDataBaseClient.deleteMovie(movie,movieTitle);
                    switch_watchlist.setText("Add to Watchlist");
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if(fromWatchlist == true){
            inflater.inflate(R.menu.menu_movie_from_watchlist,menu);
        }else {
            inflater.inflate(R.menu.menu_save_movie,menu);
        }
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
            case R.id.save_to_db:{
                movieDataBaseClient.insertNewMovie(movie, movieTitle);
                break;
            }
            case R.id.delete_from_db:{
                movieDataBaseClient.deleteMovie(movie,movieTitle);
                break;
            }
        }
        return true;
    }

    private void startGenreActivity(){
        Intent intent = new Intent(this, GenreList.class);
        startActivity(intent);
    }

    private void startSaveMoviesActivity(){
        Intent intent = new Intent(this,WatchList.class);
        startActivity(intent);
    }
}