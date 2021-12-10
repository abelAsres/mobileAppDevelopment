package com.map524.anothermovieapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class SingleMovie extends AppCompatActivity {

    Movie movie;
    TextView movieTitle;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);

        Intent intentFromMovieListByGenre = getIntent();
        Bundle bundle = intentFromMovieListByGenre.getBundleExtra("bundle");
        movie = bundle.getParcelable("movie");
        imageView = findViewById(R.id.movie_image);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+movie.getPoster_path()).into(imageView);
        movieTitle = findViewById(R.id.movie_title);

        movieTitle.setText(movie.getOriginal_title());
    }
}