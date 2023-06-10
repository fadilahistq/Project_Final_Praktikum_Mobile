package com.example.projectfinalpraktikummobile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);
    @GET("tv/on_the_air")
    Call<TvShowsResponse> getOnAirTvShows(@Query("api_key") String apiKey);
}
