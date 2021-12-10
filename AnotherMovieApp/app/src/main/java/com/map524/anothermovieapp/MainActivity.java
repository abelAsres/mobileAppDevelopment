package com.map524.anothermovieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NetworkingService.NetworkingListener,
        GenresAdapter.genreClickListener{

    NetworkingService networkingManager;
    JsonService jsonService;

    GenresAdapter genresAdapter;
    RecyclerView genreRecyclerView;
    ArrayList<Genre> genres;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkingManager = ((myApp) getApplication()).getNetworkingService();
        jsonService = ((myApp) getApplication()).getJsonService();
        //listen for background thread to complete
        networkingManager.networkingListener= this;

        genres = new ArrayList<Genre>() ;

        genreRecyclerView = findViewById(R.id.genreList);
        genreRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        genresAdapter = new GenresAdapter(this, genres);
        genreRecyclerView.setAdapter(genresAdapter);
        networkingManager.getMovieGenres();
    }

    @Override
    public void dataListener(String jsonString) {
        genres=jsonService.getGenresFromJson(jsonString);
        genresAdapter = new GenresAdapter(this,genres);
        genreRecyclerView.setAdapter(genresAdapter);
        genresAdapter.notifyDataSetChanged();
    }

    @Override
    public void genreClicked(Genre selectedGenre) {
        Log.d("Genre", "genreClicked: clicked"+selectedGenre.getId());
        Log.d("Genre", "genreClicked: clicked");
        Intent intent = new Intent(this,MovieListByGenre.class);
        Bundle bundle = new Bundle();
        bundle.putInt("genreId",selectedGenre.getId());
        intent.putExtra("bundle",bundle);
        startActivity(intent);
    }
}