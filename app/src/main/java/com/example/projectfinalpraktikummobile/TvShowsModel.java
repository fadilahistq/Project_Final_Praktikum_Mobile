package com.example.projectfinalpraktikummobile;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class TvShowsModel implements Parcelable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("overview")
    private String overview;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("vote_average")
    private double voteAverage;

    protected TvShowsModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        firstAirDate = in.readString();
        originalLanguage = in.readString();
        overview = in.readString();
        popularity = in.readDouble();
        voteAverage = in.readDouble();
    }

    public static final Creator<TvShowsModel> CREATOR = new Creator<TvShowsModel>() {
        @Override
        public TvShowsModel createFromParcel(Parcel in) {
            return new TvShowsModel(in);
        }

        @Override
        public TvShowsModel[] newArray(int size) {
            return new TvShowsModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(posterPath);
        parcel.writeString(backdropPath);
        parcel.writeString(firstAirDate);
        parcel.writeString(originalLanguage);
        parcel.writeString(overview);
        parcel.writeDouble(popularity);
        parcel.writeDouble(voteAverage);
    }
}

