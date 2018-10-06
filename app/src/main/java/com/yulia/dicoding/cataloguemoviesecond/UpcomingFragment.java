package com.yulia.dicoding.cataloguemoviesecond;


import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class UpcomingFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {

    MovieAdapter adapter;

    @BindView(R.id.rv_upcoming)
    RecyclerView rvUpcoming;
    public static final String EXTRA_UPCOMING = "UPCOMING";

    public UpcomingFragment() {
        // Required empty public constructor
}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_upcoming, container, false);
        ButterKnife.bind(this, rootView);

        adapter = new MovieAdapter(getContext());
        adapter.notifyDataSetChanged();

        rvUpcoming.setAdapter(adapter);
        rvUpcoming.setHasFixedSize(true);
        rvUpcoming.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = new Bundle();
        String url ="https://api.themoviedb.org/3/movie/upcoming?api_key="+API_KEY+"&language=en-US&query=";
        bundle.putString(EXTRA_UPCOMING, url);
        getLoaderManager().initLoader(0, bundle,this);
        return rootView;
    }

    @NonNull
    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, @Nullable Bundle args){
        String Url = "";
        if (args!= null){
            Url = args.getString(EXTRA_UPCOMING);
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
