package com.example.projectfinalpraktikummobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TvShowHelper {

    private static final String DATABASE_TABLE = DatabaseContract.TABLE_NAME_TV_SHOW;
    private static DatabaseHelper databaseHelper;
    private static TvShowHelper INSTANCE;

    private static SQLiteDatabase database;

    TvShowHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static TvShowHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TvShowHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TvShowHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
        if (database.isOpen()) {
            database.close();
        }
    }

    public Cursor queryAll() {
        open();
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                DatabaseContract.TvShowColumns._ID + " ASC"
        );
    }

    public Cursor queryById(String id) {
        return database.query(
                DATABASE_TABLE,
                null,
                DatabaseContract.TvShowColumns._ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null
        );
    }

    public long insertTvShow(FavoritesModel tvShow) {
        ContentValues args = new ContentValues();
        args.put(DatabaseContract.TvShowColumns.TITLE, tvShow.getTitle());
        args.put(DatabaseContract.TvShowColumns.YEAR, tvShow.getYear());
        args.put(DatabaseContract.TvShowColumns.POSTER, tvShow.getPosterPath());

        return database.insert(DATABASE_TABLE, null, args);
    }

    public int deleteById(String id) {
        return database.delete(
                DATABASE_TABLE,
                DatabaseContract.TvShowColumns._ID + " = ?",
                new String[]{id}
        );
    }

    public List<FavoritesModel> getAllTvShows() {
        List<FavoritesModel> tvShowList = new ArrayList<>();
        Cursor cursor = database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            do {
                FavoritesModel tvShow = new FavoritesModel(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns._ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.YEAR)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.POSTER))
                );

                tvShowList.add(tvShow);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return tvShowList;
    }
}
