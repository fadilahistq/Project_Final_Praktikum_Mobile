package com.example.projectfinalpraktikummobile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {

    @SerializedName("results")
    private List<MoviesModel> results;
    public List<MoviesModel> getResults() {
        return results;
    }
}
