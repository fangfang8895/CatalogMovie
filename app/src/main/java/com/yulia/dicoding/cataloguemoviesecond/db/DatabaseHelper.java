package com.yulia.dicoding.cataloguemoviesecond.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.yulia.dicoding.cataloguemoviesecond.db.DatabaseContract.FavoriteColumns.LANGUAGE;
import static com.yulia.dicoding.cataloguemoviesecond.db.DatabaseContract.FavoriteColumns.OVERVIEW;
import static com.yulia.dicoding.cataloguemoviesecond.db.DatabaseContract.FavoriteColumns.POPULARITY;
import static com.yulia.dicoding.cataloguemoviesecond.db.DatabaseContract.FavoriteColumns.POSTERPATH;
import static com.yulia.dicoding.cataloguemoviesecond.db.DatabaseContract.FavoriteColumns.RELEASE_DATE;
import static com.yulia.dicoding.cataloguemoviesecond.db.DatabaseContract.FavoriteColumns.TITLE;
import static com.yulia.dicoding.cataloguemoviesecond.db.DatabaseContract.FavoriteColumns.VOTE;
import static com.yulia.dicoding.cataloguemoviesecond.db.DatabaseContract.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "catalogue_movie";
    private static final int DATABASE_VERSION = 1;

    private static String CREATE_TABLE_FAVORITE= String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_NAME,
            DatabaseContract.FavoriteColumns._ID,
            TITLE,
            POSTERPATH,
            OVERVIEW,
            RELEASE_DATE,
            VOTE,
            LANGUAGE,
           POPULARITY);

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FAVORITE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
