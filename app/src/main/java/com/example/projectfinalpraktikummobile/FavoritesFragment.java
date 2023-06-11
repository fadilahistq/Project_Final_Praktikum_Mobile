package com.example.projectfinalpraktikummobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    private RecyclerView recyclerView;
    private FavoritesAdapter favoritesAdapter;
    private List<FavoritesModel> favoritesList;
    private MovieHelper movieHelper;
    private TvShowHelper tvShowHelper;
    private TextView tvEmptyMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = view.findViewById(R.id.rv_favorites);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        favoritesList = new ArrayList<>();
        favoritesAdapter = new FavoritesAdapter(getContext(), favoritesList);

        recyclerView.setAdapter(favoritesAdapter);

        movieHelper = new MovieHelper(getContext());
        movieHelper.open();
        tvShowHelper = new TvShowHelper(getContext());
        tvShowHelper.open();

        loadFavoritesData();

        return view;
    }

    private void loadFavoritesData() {
        favoritesList.clear();

        List<FavoritesModel> movieList = movieHelper.getAllMovies();
        favoritesList.addAll(movieList);

        List<FavoritesModel> tvShowList = tvShowHelper.getAllTvShows();
        favoritesList.addAll(tvShowList);

        favoritesAdapter.notifyDataSetChanged();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        movieHelper.close();
        tvShowHelper.close();
    }
}
