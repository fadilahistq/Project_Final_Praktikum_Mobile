package com.example.projectfinalpraktikummobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MovieHelper {

    private static final String DATABASE_TABLE = DatabaseContract.TABLE_NAME_MOVIE;
    private static DatabaseHelper databaseHelper;
    private static MovieHelper INSTANCE;

    private static SQLiteDatabase database;

    MovieHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static MovieHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (MovieHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieHelper(context);
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
                DatabaseContract.MovieColumns._ID + " ASC"
        );
    }

    public Cursor queryById(String id) {
        return database.query(
                DATABASE_TABLE,
                null,
                DatabaseContract.MovieColumns._ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null
        );
    }

    public long insertMovie(FavoritesModel movie) {
        ContentValues args = new ContentValues();
        args.put(DatabaseContract.MovieColumns.TITLE, movie.getTitle());
        args.put(DatabaseContract.MovieColumns.YEAR, movie.getYear());
        args.put(DatabaseContract.MovieColumns.POSTER, movie.getPosterPath());

        return database.insert(DATABASE_TABLE, null, args);
    }

    public int deleteById(String id) {
        return database.delete(
                DATABASE_TABLE,
                DatabaseContract.MovieColumns._ID + " = ?",
                new String[]{id}
        );
    }

    public List<FavoritesModel> getAllMovies() {
        List<FavoritesModel> movieList = new ArrayList<>();
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
                FavoritesModel movie = new FavoritesModel(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns._ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.YEAR)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.POSTER))
                );

                movieList.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return movieList;
    }

}
