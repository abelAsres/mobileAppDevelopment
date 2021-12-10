package com.map524.anothermovieapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert
    void insert(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("SELECT * FROM movie")
    List<Movie> getAll();

    @Query("SELECT * FROM movie WHERE movie_id = :movieId")
    Movie getMovieById(int movieId);
}
