package com.map524.anothermovieapp;


import java.lang.reflect.Array;

public class Movie {
    String backdrop_path;
    int [] genre_ids;
    int id;
    String original_title;
    String overview;
    String poster_path;
    String release_date;
    String title;
    float vote_average;
    int vote_count;

    public Movie(String backdrop_path, int[] genre_ids, int id, String original_title,
                 String overview, String poster_path, String release_date, String title,
                 float vote_average, int vote_count) {
        this.backdrop_path = backdrop_path;
        this.genre_ids = genre_ids;
        this.id = id;
        this.original_title = original_title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }


}
