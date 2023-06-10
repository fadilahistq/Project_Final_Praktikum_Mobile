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

import java.util.List;

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.ViewHolder>{
    private List<TvShowsModel> tvshows;
    private Context context;

    public TvShowsAdapter(Context context, List<MoviesModel> movies) {
        this.context = context;
        this.tvshows = tvshows;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movies, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TvShowsModel tvShows = tvshows.get(position);
        holder.titleTextView.setText(tvShows.getName());

        String releaseYear = "";
        if (tvShows.getFirstAirDate() != null && !tvShows.getFirstAirDate().isEmpty()) {
            releaseYear = tvShows.getFirstAirDate().substring(0, 4);
        }
        holder.dateTextView.setText(releaseYear);

        String baseUrl = "https://image.tmdb.org/t/p/";
        String fileSize = "w500";
        String filePath = tvShows.getPosterPath();

        String imageUrl = baseUrl + fileSize + filePath;

        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.posterImageView);

        holder.cardmovies.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_TVSHOWS, tvShows);
            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        if (tvshows != null) {
            return tvshows.size();
        } else {
            return 0;
        }
    }

    public void setTvshows(List<TvShowsModel> tvshows) {
        this.tvshows = tvshows;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView, dateTextView;
        public ImageView posterImageView;
        public CardView cardmovies;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_title);
            dateTextView = itemView.findViewById(R.id.tv_year);
            posterImageView = itemView.findViewById(R.id.poster_image);
            cardmovies = itemView.findViewById(R.id.cv_movies);
        }
    }
}