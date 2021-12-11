package com.map524.anothermovieapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class SingleMovie extends AppCompatActivity {

    Movie movie;
    TextView movieTitle;
    TextView movieOverview;
    ImageView imageView;

    MovieDatabase movieDatabase;
    MovieDataBaseClient movieDataBaseClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);


        movieDatabase = MovieDataBaseClient.getDBInstance(this);
        movieDataBaseClient = ((myApp)getApplication()).getMovieDataBaseClient();

        Intent intentFromMovieListByGenre = getIntent();
        Bundle bundle = intentFromMovieListByGenre.getBundleExtra("bundle");
        movie = bundle.getParcelable("movie");
        imageView = findViewById(R.id.movie_image);
        movieTitle = findViewById(R.id.movie_title);
        movieOverview = findViewById(R.id.movie_overview);
        movieTitle.setText(movie.getOriginal_title());
        movieOverview.setText(movie.getOverview());
        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+movie.getPoster_path()).into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save_movie,menu);
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
                movieDataBaseClient.insertNewMovie(movie);
                break;
            }
        }
        return true;
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