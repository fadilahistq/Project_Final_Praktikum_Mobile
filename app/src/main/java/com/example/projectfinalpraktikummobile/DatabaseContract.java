package com.example.projectfinalpraktikummobile;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String TABLE_NAME_MOVIE = "movie";
    public static final String TABLE_NAME_TV_SHOW = "tv_show";


    public static final class MovieColumns implements BaseColumns {
        public static final String TITLE = "title";
        public static final String YEAR = "year";
        public static final String POSTER = "poster";
    }

    public static final class TvShowColumns implements BaseColumns {
        public static final String TITLE = "title";
        public static final String YEAR = "year";
        public static final String POSTER = "poster";
    }
}

