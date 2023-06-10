package com.example.projectfinalpraktikummobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowsFragment extends Fragment {

    private RecyclerView recyclerView;
    private TvShowsAdapter tvShowsAdapter;
    private ProgressBar progressBar;
    private TextView tvInternet;
    private ImageView ivRetry;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_shows, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        tvInternet = view.findViewById(R.id.tv_internet);
        ivRetry = view.findViewById(R.id.iv_retry);
        recyclerView = view.findViewById(R.id.rv_tv);
        tvShowsAdapter = new TvShowsAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(tvShowsAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


        APIService apiService = APIConfig.getClient();
        String apiKey = APIConfig.getApiKey();
        Call<TvShowsResponse> call = apiService.getOnAirTvShows(apiKey);

        call.enqueue(new Callback<TvShowsResponse>() {
            @Override
            public void onResponse(Call<TvShowsResponse> call, Response<TvShowsResponse> response) {
                if (response.isSuccessful()) {
                    List<TvShowsModel> tvshows = response.body().getResults();
                    tvShowsAdapter.setTvshows(tvshows);
                    tvShowsAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Failed to fetch TV Shows.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TvShowsResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
