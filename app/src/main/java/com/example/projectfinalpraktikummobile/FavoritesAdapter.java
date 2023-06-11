package com.example.projectfinalpraktikummobile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {
    private Context context;
    private List<FavoritesModel> favoritesList;

    public FavoritesAdapter(Context context, List<FavoritesModel> favoritesList) {
        this.context = context;
        this.favoritesList = favoritesList;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorites, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        FavoritesModel favorites = favoritesList.get(position);
        Glide.with(context)
                .load(favorites.getPosterPath())
                .into(holder.posterImageView);
        holder.titleTextView.setText(favorites.getTitle());

        String releaseYear = "";
        if (favorites.getYear() != null && !favorites.getYear().isEmpty()) {
            releaseYear = favorites.getYear().substring(0, 4);
        }
        holder.yearTextView.setText(releaseYear);

        String baseUrl = "https://image.tmdb.org/t/p/";
        String fileSize = "w500";
        String filePath = favorites.getPosterPath();
        String imageUrl = baseUrl + fileSize + filePath;

        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.posterImageView);

    }


    @Override
    public int getItemCount() {
        return favoritesList.size();
    }

    public class FavoritesViewHolder extends RecyclerView.ViewHolder {
        private ImageView posterImageView, iconImageView;
        private TextView titleTextView, yearTextView;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.poster_image);
            titleTextView = itemView.findViewById(R.id.tv_title);
            yearTextView = itemView.findViewById(R.id.tv_year);
            iconImageView = itemView.findViewById(R.id.ic_movie);
        }
    }
}
