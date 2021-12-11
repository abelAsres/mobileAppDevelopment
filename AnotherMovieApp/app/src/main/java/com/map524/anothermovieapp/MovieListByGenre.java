package com.map524.anothermovieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieListByGenre extends AppCompatActivity implements NetworkingService.NetworkingListener,
        MoviesAdapter.movieClickListener{

    RecyclerView movieRecyclerView;
    MoviesAdapter moviesAdapter;

    NetworkingService networkingManager;
    JsonService jsonService;
    ArrayList<Movie> movies;
    int genreId;

    Paginator page;
    TextView currentPage;
    TextView lastPage;

    Button prevBtn;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list_by_genre);

        networkingManager = ((myApp) getApplication()).getNetworkingService();
        jsonService = ((myApp) getApplication()).getJsonService();
        //listen for background thread to complete
        networkingManager.networkingListener= this;

        page = new Paginator();
        movies = new ArrayList<Movie>();

        movieRecyclerView = findViewById(R.id.movieList);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        moviesAdapter = new MoviesAdapter(this, movies);
        movieRecyclerView.setAdapter(moviesAdapter);

        currentPage = findViewById(R.id.currentPage);
        lastPage = findViewById(R.id.lastPage);

        prevBtn = findViewById(R.id.prevBtn);
        nextBtn = findViewById(R.id.nextBtn);

        if(getIntent().hasExtra("bundle")){
            Bundle bundleFromMain = getIntent().getBundleExtra("bundle");
            genreId = bundleFromMain.getInt("genreId");
        }
        getMoviesByGenre(genreId);

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
        // System.out.println(jsonString);
        int numberOfItems = 0;
        try {
            numberOfItems = Integer.parseInt(new JSONObject(jsonString).getString("total_results"));
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            page.setTotalNumberOfItems(numberOfItems);
            currentPage.setText(""+page.getCurrentPage());
            lastPage.setText(""+page.getLastPage());
            //Log.d("datalistener", "page: "+page.getCurrentPage());
        }
        movies = jsonService.getMoviesFromJson(jsonString);
        moviesAdapter = new MoviesAdapter(this,movies);
        movieRecyclerView.setAdapter(moviesAdapter);
        moviesAdapter.notifyDataSetChanged();
    }

    public void clickPrevBtn(View view){
        if (page.getCurrentPage() != 1){
            page.setCurrentPage(page.getCurrentPage() - 1);
            getMoviesByGenre(28,page.getCurrentPage());
        }

        if(page.currentPage == 1){
            prevBtn.setClickable(false);
        }
        if(page.getCurrentPage() != page.getLastPage()){
            nextBtn.setClickable(true);
        }
    }

    public void clickNextBtn(View view){
        if (page.getCurrentPage() != page.getLastPage()){
            page.setCurrentPage(page.getCurrentPage() + 1);
            getMoviesByGenre(28,page.getCurrentPage());
        }

        if(page.currentPage != 1){
            prevBtn.setClickable(true);
        }

        if(page.getCurrentPage() == page.getLastPage()){
            nextBtn.setClickable(false);
        }
    }
    @Override
    public void movieClicked(Movie selectedMovie) {
        Log.d("clicked", "movieClicked");
        Intent intent = new Intent(this,SingleMovie.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie",selectedMovie);
        intent.putExtra("bundle",bundle);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_scrolling,menu);
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

    private void startGenreActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void startSaveMoviesActivity(){
        Intent intent = new Intent(this,WatchList.class);
        startActivity(intent);
    }
}