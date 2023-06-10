package com.example.projectfinalpraktikummobile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowsResponse {
    @SerializedName("results")
    private List<TvShowsModel> results;
    public List<TvShowsModel> getResults() {
        return results;
    }
}
