package com.example.projectfinalpraktikummobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    MoviesFragment moviesFragment = new MoviesFragment();
    TvShowsFragment tvShowsFragment = new TvShowsFragment();
    FavoritesFragment favoritesFragment = new FavoritesFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, moviesFragment).commit();
        getSupportActionBar().setTitle("Movies");
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.btn_movies:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, moviesFragment).commit();
                        getSupportActionBar().setTitle("Movies");
                        return true;
                    case R.id.btn_tvshows:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, tvShowsFragment).commit();
                        getSupportActionBar().setTitle("TV Shows");
                        return true;
                    case R.id.btn_favorites:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, favoritesFragment).commit();
                        getSupportActionBar().setTitle("Favorites");
                        return true;
                }
                return false;
            }
        });
    }
}