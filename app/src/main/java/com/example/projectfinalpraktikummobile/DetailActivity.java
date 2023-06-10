package com.example.projectfinalpraktikummobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIES = "extra_movies";
    private ImageView backdropImageView, posterImageView, starImageView, iconImageView;
    private TextView titleTextView, dateTextView, rateTextView, synopsisTextView,overviewTextView, languageTextView, popularTextView;
    private ImageButton backImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        backdropImageView = findViewById(R.id.backdrop_image);
        posterImageView = findViewById(R.id.poster_image);
        starImageView = findViewById(R.id.rate_star);
        backImageView = findViewById(R.id.back_button);
        iconImageView = findViewById(R.id.ic_movie);
        titleTextView = findViewById(R.id.tv_title);
        dateTextView = findViewById(R.id.tv_date);
        rateTextView = findViewById(R.id.tv_rate);
        synopsisTextView = findViewById(R.id.tv_synopsis);
        overviewTextView = findViewById(R.id.tv_overview);
        languageTextView = findViewById(R.id.tv_language);
        popularTextView =findViewById(R.id.tv_popularity);

        MoviesModel movie = getIntent().getParcelableExtra(EXTRA_MOVIES);
        if (movie != null) {
            String bUrl = "https://image.tmdb.org/t/p/";
            String fSize = "w500";
            String fPath = movie.getBackdropPath();
            String imagesUrl = bUrl + fSize + fPath;
            Glide.with(this)
                    .load(imagesUrl)
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.baseline_broken_image_24)
                            .error(R.drawable.baseline_broken_image_24))
                    .into(backdropImageView);

            String baseUrl = "https://image.tmdb.org/t/p/";
            String fileSize = "w500";
            String filePath = movie.getPosterPath();

            String imageUrl = baseUrl + fileSize + filePath;
            Glide.with(this)
                    .load(imageUrl)
                    .into(posterImageView);

            // Set text values
            titleTextView.setText(movie.getTitle());
            dateTextView.setText(movie.getReleaseDate());
            String popularityString = String.format("%.1f", movie.getPopularity());
            popularTextView.setText("Popularity : " + popularityString);
            String voteAverageString = String.format("%.1f", movie.getVoteAverage());
            rateTextView.setText(voteAverageString);
            languageTextView.setText("Language : " + movie.getOriginalLanguage());
            overviewTextView.setText(movie.getOverview());

            backImageView.setOnClickListener(view -> finish());
        }
    }

    public void onCustomToggleClick(View view) {
        Toast.makeText(this, "Add to favorite", Toast.LENGTH_SHORT).show();
    }
}