package com.example.projectfinalpraktikummobile;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<MoviesModel> movies;
    private Context context;

    public MoviesAdapter(Context context, List<MoviesModel> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movies, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MoviesModel movie = movies.get(position);
        holder.titleTextView.setText(movie.getTitle());

        String releaseYear = "";
        if (movie.getReleaseDate() != null && !movie.getReleaseDate().isEmpty()) {
            releaseYear = movie.getReleaseDate().substring(0, 4);
        }
        holder.dateTextView.setText(releaseYear);

        String baseUrl = "https://image.tmdb.org/t/p/";
        String fileSize = "w500";
        String filePath = movie.getPosterPath();

        String imageUrl = baseUrl + fileSize + filePath;

        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.posterImageView);

        holder.cardmovies.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_MOVIES, movie);
            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        if (movies != null) {
            return movies.size();
        } else {
            return 0;
        }
    }

    public void setMovies(List<MoviesModel> movies) {
        this.movies = movies;
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

