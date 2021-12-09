package com.map524.anothermovieapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class JsonService {

    public ArrayList<Movie> getMoviesFromJson(String Json){

        ArrayList<Movie> movies = new ArrayList<Movie>();
        try {
            JSONObject json_movies = new JSONObject(Json);
            JSONArray json_movies_results = json_movies.getJSONArray("results");

            String title = json_movies_results.getJSONObject(3).getString("title");

            Log.d("length of jsonmovies", "getMoviesFromJson: "+json_movies_results.length());
            for (int i = 0; i < json_movies_results.length();i++){
                Log.d("length of jsonmovies", "i:"+i);
                //title = json_movies_results.getJSONObject(i).getString("title");
                String backdrop_path = json_movies_results.getJSONObject(i).getString("backdrop_path");


               JSONArray genre_ids_obj = json_movies_results.getJSONObject(i).getJSONArray("genre_ids");
                int [] genre_ids = new int [genre_ids_obj.length()];
                   for (int j = 0; j < genre_ids_obj.length();j++){
                       genre_ids[j] = Integer.parseInt(genre_ids_obj.getString(j));
                   }

                int id = Integer.parseInt(json_movies_results.getJSONObject(i).getString("id"));
                String original_title = json_movies_results.getJSONObject(i).getString("original_title");
                String overview = json_movies_results.getJSONObject(i).getString("overview");
                String poster_path = json_movies_results.getJSONObject(i).getString("poster_path");
                String release_date = json_movies_results.getJSONObject(i).getString("release_date");
                float vote_average = Float.parseFloat(json_movies_results.getJSONObject(i).getString("vote_average"));
                int vote_count = Integer.parseInt(json_movies_results.getJSONObject(i).getString("vote_count"));

                Movie movie = new Movie(backdrop_path, genre_ids, id, original_title,
                                        overview, poster_path, release_date, title,
                                          vote_average, vote_count);

                movies.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
