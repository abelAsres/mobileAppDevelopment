package com.map524.anothermovieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.GenreViewHolder>{

    interface genreClickListener{
        public void genreClicked(Genre selectedGenre);
    }

    private Context context;
    public List<Genre> genreList;
    genreClickListener genreListener;

    public GenresAdapter(Context context, List<Genre> genreList) {
        this.context = context;
        this.genreList = genreList;
        this.genreListener = (genreClickListener)context;
    }

    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_recyclerview_genres, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GenresAdapter.GenreViewHolder holder, int position) {
        Genre genre = genreList.get(position);
        holder.genreTextView.setText(genre.getName());
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    class GenreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView genreTextView;

        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);
            genreTextView = itemView.findViewById(R.id.genre);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Genre genre = genreList.get(getAdapterPosition());
            genreListener.genreClicked(genre);
        }
    }

}
