package com.map524.anothermovieapp;


import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;

public class Movie implements Parcelable {
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

    String imageUrl ="https://image.tmdb.org/t/p/w500";

    public Movie(String backdrop_path, int[] genre_ids, int id, String original_title,
                 String overview, String poster_path, String release_date, String title,
                 float vote_average, int vote_count) {
        this.backdrop_path = imageUrl+ backdrop_path;
        this.genre_ids = genre_ids;
        this.id = id;
        this.original_title = original_title;
        this.overview = overview;
        this.poster_path = imageUrl + poster_path;
        this.release_date = release_date;
        this.title = title;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    protected Movie(Parcel in) {
        backdrop_path = in.readString();
        //in.readIntArray(genre_ids);
        id = in.readInt();
        original_title = in.readString();
        overview = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        title = in.readString();
        vote_average = in.readFloat();
        vote_count = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(backdrop_path);
        //dest.writeIntArray(genre_ids);
        dest.writeInt(id);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeString(title);
        dest.writeFloat(vote_average);
        dest.writeInt(vote_count);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public int getId() {
        return id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public float getVote_average() {
        return vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }
}
