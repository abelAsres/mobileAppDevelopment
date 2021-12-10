package com.map524.anothermovieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    interface movieClickListener{
        public void movieClicked(Movie selectedMovie);
    }

    private Context context;
    public List<Movie> movieList;
    movieClickListener movieListener;

    public MoviesAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
        this.movieListener = (movieClickListener)context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_recyclerview_movies, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.movieTextView.setText(movie.getOriginal_title());

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView movieTextView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTextView = itemView.findViewById(R.id.movie);
            itemView.setOnClickListener(this);
            //TODO : setup images for each item
            //itemView.setBackground(movieList.get(getAdapterPosition()).getBackdrop_path());
        }

        @Override
        public void onClick(View v) {
            Movie movie = movieList.get(getAdapterPosition());
            movieListener.movieClicked(movie);
        }
    }

}
