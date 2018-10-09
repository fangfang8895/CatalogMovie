package com.yulia.dicoding.cataloguemoviesecond.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static com.yulia.dicoding.cataloguemoviesecond.db.DatabaseContract.FavoriteColumns._ID;
import static com.yulia.dicoding.cataloguemoviesecond.db.DatabaseContract.TABLE_NAME;

public class FavoriteHelper {
    private static String DATABASE_TABLE = TABLE_NAME;
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public FavoriteHelper(Context context){
        this.context = context;
    }

    public FavoriteHelper open() throws SQLException{
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return(this);
    }

    public void close(){
        databaseHelper.close();
    }

    /*public ArrayList<FavoriteItems> query(){
        ArrayList<FavoriteItems> arrayList = new ArrayList<FavoriteItems>();
        Cursor cursor = database.query(DATABASE_TABLE, null,null,null,null,null,_ID + " DESC", null );
        cursor.moveToFirst();
        FavoriteItems note;
        if (cursor.getCount()>0){
            do {
                note = new FavoriteItems();
                note.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                note.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(POSTERPATH)));
                note.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                note.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                note.setVoteAveragedetail(cursor.getString(cursor.getColumnIndexOrThrow(VOTE)));
                note.setLanguage(cursor.getString(cursor.getColumnIndexOrThrow(LANGUAGE)));
                note.setPopularityMovie(cursor.getString(cursor.getColumnIndexOrThrow(POPULARITY)));
                arrayList.add(note);
                cursor.moveToNext();
            }
            while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(FavoriteItems note){
        ContentValues initialValues = new ContentValues();
        initialValues.put(TITLE, note.getTitle());
        initialValues.put(POSTERPATH, note.getPosterPath());
        initialValues.put(OVERVIEW, note.getOverview());
        initialValues.put(RELEASE_DATE, note.getReleaseDate());
        initialValues.put(VOTE, note.getVoteAveragedetail());
        initialValues.put(LANGUAGE, note.getLanguage());
        initialValues.put(POPULARITY, note.getPopularityMovie());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }
    public int delete(int id){
        return database.delete(TABLE_NAME, _ID + " = ' "+ id+ " '", null);
    }*/

    public Cursor queryByIdProvider(String id){
        return database.query(DATABASE_TABLE,
                null
        ,_ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null);
    }

    public Cursor queryProvider(){
        return database.query(DATABASE_TABLE
        ,null
        ,null
        ,null
        ,null
        ,null
        ,_ID + " DESC");
    }

    public long insertProvider(ContentValues values){
        return database.insert(DATABASE_TABLE,
                null,
                values);
    }
    public int updateProvider(String id, ContentValues values){
        return database.update(DATABASE_TABLE,
                values,
                _ID + " = ?",
                new String[]{id}
                );
    }

    public int deleteProvider(String id){
        return database.delete(DATABASE_TABLE,
                _ID + " = ?",
                new String[]{id}
                );
    }
}
