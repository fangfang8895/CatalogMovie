package com.yulia.dicoding.cataloguemoviesecond;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yulia.dicoding.cataloguemoviesecond.TaskLoader.TaskLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yulia.dicoding.cataloguemoviesecond.BuildConfig.API_KEY;


/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {

    MovieAdapter adapter;

    @BindView(R.id.rv_nowplay)
    RecyclerView rvNowPlay;

    public static final String EXTRA_NOW_PLAY = "EXTRA_NOW";

    public NowPlayingFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // keep the fragment and all its data across screen rotation
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_now_playing, container, false);
        ButterKnife.bind(this, rootView);

        adapter = new MovieAdapter(getContext());
        adapter.notifyDataSetChanged();

        rvNowPlay.setAdapter(adapter);
        rvNowPlay.setHasFixedSize(true);
        rvNowPlay.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = new Bundle();
        String url ="https://api.themoviedb.org/3/movie/now_playing?api_key="+API_KEY+"&language=en-US&query=";
        bundle.putString(EXTRA_NOW_PLAY, url);
        getLoaderManager().initLoader(0, bundle,this);
        return rootView;
    }

    @NonNull
    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, @Nullable Bundle args){
        String Url = "";
        if (args!= null){
            Url = args.getString(EXTRA_NOW_PLAY);
        }
        return new TaskLoader(getContext(), Url);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);
    }

}
