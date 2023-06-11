package com.example.projectfinalpraktikummobile;

import android.os.Parcel;
import android.os.Parcelable;

public class FavoritesModel implements Parcelable {
    private int id;
    private String title;
    private String year;
    private String posterPath;

    public FavoritesModel(int id, String title, String year, String posterPath) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.posterPath = posterPath;
    }

    protected FavoritesModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        year = in.readString();
        posterPath = in.readString();
    }

    public static final Parcelable.Creator<FavoritesModel> CREATOR = new Creator<FavoritesModel>() {
        @Override
        public FavoritesModel createFromParcel(Parcel in) {
            return new FavoritesModel(in);
        }

        @Override
        public FavoritesModel[] newArray(int size) {
            return new FavoritesModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getPosterPath() {
        return posterPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(posterPath);
    }
}
