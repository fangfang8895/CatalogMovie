package com.yulia.dicoding.cataloguemoviesecond.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_NAME ="favorite";

    public static final class FavoriteColumns implements BaseColumns {
        public static String _ID = "_id";
        public static String TITLE ="title";
        public static String POSTERPATH = "poster_path";
        public static String OVERVIEW = "overview";
        public static String RELEASE_DATE = "release_date";
        public static String VOTE = "vote_average";
        public static String LANGUAGE = "language";
        public static String POPULARITY = "popularity";
    }

    public static final String AUTHORITY = "com.yulia.dicoding.cataloguemoviesecond";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build();

    public static String getColumnString (Cursor cursor, String columnName){
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt (Cursor cursor, String columnName){
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
    public static double getColumnDouble (Cursor cursor, String columnName){
        return cursor.getDouble(cursor.getColumnIndex(columnName));
    }
    public static long getColumnLong (Cursor cursor, String columnName){
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

}
