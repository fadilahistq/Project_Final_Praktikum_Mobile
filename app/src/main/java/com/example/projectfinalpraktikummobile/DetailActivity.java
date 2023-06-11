package com.example.projectfinalpraktikummobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIES = "extra_movies";
    public static final String EXTRA_TVSHOWS = "extra_tvshows";
    public static final String EXTRA_FAVORITES = "extra_favorites";
    private ImageView backdropImageView, posterImageView, starImageView, iconImageView;
    private TextView titleTextView, dateTextView, rateTextView, synopsisTextView, overviewTextView, languageTextView, popularTextView;
    private ImageButton backImageView, btnFavorites;
    private boolean isFavorite;
    private int itemId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        backdropImageView = findViewById(R.id.backdrop_image);
        posterImageView = findViewById(R.id.poster_image);
        starImageView = findViewById(R.id.rate_star);
        backImageView = findViewById(R.id.back_button);
        btnFavorites = findViewById(R.id.btn_favorites);
        iconImageView = findViewById(R.id.ic_movie);
        titleTextView = findViewById(R.id.tv_title);
        dateTextView = findViewById(R.id.tv_date);
        rateTextView = findViewById(R.id.tv_rate);
        synopsisTextView = findViewById(R.id.tv_synopsis);
        overviewTextView = findViewById(R.id.tv_overview);
        languageTextView = findViewById(R.id.tv_language);
        popularTextView = findViewById(R.id.tv_popularity);

        FavoritesModel favorites = getIntent().getParcelableExtra(EXTRA_FAVORITES);
        if (favorites != null) {
            Glide.with(this)
                    .load(favorites.getPosterPath())
                    .into(backdropImageView);
            titleTextView.setText(favorites.getTitle());
            dateTextView.setText(favorites.getYear());
        }
        MoviesModel movie = getIntent().getParcelableExtra(EXTRA_MOVIES);
        TvShowsModel tvshows = getIntent().getParcelableExtra(EXTRA_TVSHOWS);
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
        if (tvshows != null) {
            String bUrl = "https://image.tmdb.org/t/p/";
            String fSize = "w500";
            String fPath = tvshows.getBackdropPath();
            String imagesUrl = bUrl + fSize + fPath;
            Glide.with(this)
                    .load(imagesUrl)
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.baseline_broken_image_24)
                            .error(R.drawable.baseline_broken_image_24))
                    .into(backdropImageView);

            String baseUrl = "https://image.tmdb.org/t/p/";
            String fileSize = "w500";
            String filePath = tvshows.getPosterPath();

            String imageUrl = baseUrl + fileSize + filePath;
            Glide.with(this)
                    .load(imageUrl)
                    .into(posterImageView);

            titleTextView.setText(tvshows.getName());
            dateTextView.setText(tvshows.getFirstAirDate());
            String popularityString = String.format("%.1f", tvshows.getPopularity());
            popularTextView.setText("Popularity : " + popularityString);
            String voteAverageString = String.format("%.1f", tvshows.getVoteAverage());
            rateTextView.setText(voteAverageString);
            languageTextView.setText("Language : " + tvshows.getOriginalLanguage());
            overviewTextView.setText(tvshows.getOverview());
            iconImageView.setImageResource(R.drawable.baseline_tv_24);

            backImageView.setOnClickListener(view -> finish());
        }
        itemId = movie != null ? movie.getId() : (tvshows != null ? tvshows.getId() : -1);
        if (itemId != -1) {
            favouriteState(itemId);
        }

        btnFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavorite) {
                    deleteData();
                } else {
                    insertData();
                }
            }
        });
    }

    private void favouriteState(int id) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursorMovie = db.rawQuery("SELECT * FROM " + DatabaseContract.TABLE_NAME_MOVIE + " WHERE " +
                DatabaseContract.MovieColumns._ID + "=?", new String[]{String.valueOf(id)});

        Cursor cursorTvShows = db.rawQuery("SELECT * FROM " + DatabaseContract.TABLE_NAME_TV_SHOW + " WHERE " +
                DatabaseContract.TvShowColumns._ID + "=?", new String[]{String.valueOf(id)});

        if (cursorMovie.getCount() > 0 || cursorTvShows.getCount() > 0) {
            isFavorite = true;
            btnFavorites.setImageResource(R.drawable.baseline_favorite_24);
        } else {
            isFavorite = false;
            btnFavorites.setImageResource(R.drawable.baseline_favorite_border_24);
        }

        cursorMovie.close();
        cursorTvShows.close();
        db.close();
    }

    private void insertData() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        MoviesModel movie = getIntent().getParcelableExtra(EXTRA_MOVIES);
        TvShowsModel tvshows = getIntent().getParcelableExtra(EXTRA_TVSHOWS);

        ContentValues values = new ContentValues();

        if (movie != null) {
            values.put(DatabaseContract.MovieColumns._ID, movie.getId());
            values.put(DatabaseContract.MovieColumns.TITLE, movie.getTitle());
            values.put(DatabaseContract.MovieColumns.YEAR, movie.getReleaseDate());
            values.put(DatabaseContract.MovieColumns.POSTER, movie.getPosterPath());

            long result = db.insert(DatabaseContract.TABLE_NAME_MOVIE, null, values);

            if (result > 0) {
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
                btnFavorites.setImageResource(R.drawable.baseline_favorite_24);
                isFavorite = true;
            } else {
                Toast.makeText(this, "Failed to add to favorites", Toast.LENGTH_SHORT).show();
            }
        } else if (tvshows != null) {
            values.put(DatabaseContract.TvShowColumns._ID, tvshows.getId());
            values.put(DatabaseContract.TvShowColumns.TITLE, tvshows.getName());
            values.put(DatabaseContract.TvShowColumns.YEAR, tvshows.getFirstAirDate());
            values.put(DatabaseContract.TvShowColumns.POSTER, tvshows.getPosterPath());
            iconImageView.setImageResource(R.drawable.baseline_tv_24);

            long result = db.insert(DatabaseContract.TABLE_NAME_TV_SHOW, null, values);

            if (result > 0) {
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
                btnFavorites.setImageResource(R.drawable.baseline_favorite_24);
                isFavorite = true;
            } else {
                Toast.makeText(this, "Failed to add to favorites", Toast.LENGTH_SHORT).show();
            }
        }

        db.close();
    }

    private void deleteData() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        TvShowsModel tvshows = getIntent().getParcelableExtra(EXTRA_TVSHOWS);
        MoviesModel movie = getIntent().getParcelableExtra(EXTRA_MOVIES);

        if (movie != null) {
            String id = String.valueOf(movie.getId());
            int result = db.delete(DatabaseContract.TABLE_NAME_MOVIE, DatabaseContract.MovieColumns._ID + "=?", new String[]{id});

            if (result > 0) {
                Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
                isFavorite = false;
                btnFavorites.setImageResource(R.drawable.baseline_favorite_border_24);
            } else {
                Toast.makeText(this, "Failed to remove from favorites", Toast.LENGTH_SHORT).show();
            }
        } else if (tvshows != null) {
            String id = String.valueOf(tvshows.getId());
            int result = db.delete(DatabaseContract.TABLE_NAME_TV_SHOW, DatabaseContract.TvShowColumns._ID + "=?", new String[]{id});

            if (result > 0) {
                Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
                isFavorite = false;
                btnFavorites.setImageResource(R.drawable.baseline_favorite_border_24);
            } else {
                Toast.makeText(this, "Failed to remove from favorites", Toast.LENGTH_SHORT).show();
            }
        }
        db.close();
    }

}


