package com.yulia.dicoding.cataloguemoviesecond.TaskLoader;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import com.yulia.dicoding.cataloguemoviesecond.BuildConfig;
import com.yulia.dicoding.cataloguemoviesecond.MovieItems;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TaskLoaderSearch extends AsyncTaskLoader<ArrayList<MovieItems>> {
    private ArrayList<MovieItems> mData;
    private boolean mHasResult = false;

    private String mSearchTitle;


    public TaskLoaderSearch(final Context context, String kumpulanMovie) {
        super(context);

        onContentChanged();
        this.mSearchTitle = kumpulanMovie;
    }

    @Override
    protected void onStartLoading(){
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(final ArrayList<MovieItems> data){
        mData = data;
        mHasResult = true;
        super.deliverResult(data);

    }

    @Override
    protected void onReset(){
        super.onReset();
        onStopLoading();
        if (mHasResult){
            onReleaseResources(mData);
            mData = null;
            mHasResult = false;

        }
    }

    private static final String API_KEY = BuildConfig.API_KEY;



    protected void onReleaseResources(ArrayList<MovieItems> mData) {
        //nothing to do
    }

    @Override
    public ArrayList<MovieItems> loadInBackground() {

        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<MovieItems> movieItemses = new ArrayList<>();

        String url = "https://api.themoviedb.org/3/search/movie?api_key="+API_KEY+"&language=en-US&query="+mSearchTitle;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart(){
                super.onStart();
                setUseSynchronousMode(true);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try{
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray results = responseObject.getJSONArray("results");

                    for (int i = 0; i < results.length(); i++){
                        JSONObject movies = results.getJSONObject(i);
                        MovieItems movieItems = new MovieItems(movies);
                        movieItemses.add(movieItems);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //if response failed, then do nothing
            }
        });
        return movieItemses;
    }
}
