package com.map524.anothermovieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieListAdapter extends BaseAdapter {
    ArrayList<Movie> listOfMovies;
    Context context;

    public MovieListAdapter(ArrayList<Movie> listOfMovies, Context context) {
        this.listOfMovies = listOfMovies;
        this.context = context;
    }


    @Override
    public int getCount() {
        return listOfMovies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.movie_item,null);
        TextView movie_realease_date = convertView.findViewById(R.id.release_date);
        TextView movie_title = convertView.findViewById(R.id.movie_title);
        ImageView movie_image = convertView.findViewById(R.id.movie_image);
        movie_title.setText(listOfMovies.get(position).getOriginal_title());
        movie_realease_date.setText(listOfMovies.get(position).getRelease_date());
        Glide.with(convertView).load(listOfMovies.get(position).getBackdrop_path()).into(movie_image);
        return convertView;
    }
}
