package com.yulia.dicoding.dicodingfavorite;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.yulia.dicoding.dicodingfavorite.adapter.DicodingFavoriteAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yulia.dicoding.dicodingfavorite.db.DatabaseContract.CONTENT_URI;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.lv_favourite)
    ListView lvFavorites;
    private DicodingFavoriteAdapter dicodingFavoriteAdapter;


    private final int LOAD_FAVORITES_ID = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Favorite Film");


        dicodingFavoriteAdapter = new DicodingFavoriteAdapter(this, null, true);
        lvFavorites.setAdapter(dicodingFavoriteAdapter);
        getSupportLoaderManager().initLoader(LOAD_FAVORITES_ID, null, this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        getSupportLoaderManager().restartLoader(LOAD_FAVORITES_ID, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args){
        return new CursorLoader(this, CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        dicodingFavoriteAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        dicodingFavoriteAdapter.swapCursor(null);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportLoaderManager().destroyLoader(LOAD_FAVORITES_ID);
    }




}
