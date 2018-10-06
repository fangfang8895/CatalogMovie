package com.yulia.dicoding.cataloguemoviesecond;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.yulia.dicoding.cataloguemoviesecond.TaskLoader.TaskLoaderSearch;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {
    MovieAdapter adapter;

    @BindView(R.id.rv_search)
    RecyclerView rvSearch;
    @BindView(R.id.edit_search)
    EditText editTitle;
    @BindView(R.id.btn_search)
    Button buttonCari;

    public static final String EXTRAS_MOVIE = "EXTRA_MOVIE";

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, rootView);

        adapter = new MovieAdapter(getContext());
        adapter.notifyDataSetChanged();

        rvSearch.setAdapter(adapter);
        rvSearch.setHasFixedSize(true);
        rvSearch.setLayoutManager(new LinearLayoutManager(getContext()));

       buttonCari.setOnClickListener(myListener);


        String kumpulanMovie = editTitle.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, kumpulanMovie);
        getLoaderManager().initLoader(0, bundle,this);
        return rootView;
    }


    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id,  Bundle args){
        String kumpulanMovie = "";
        if (args!= null){
           kumpulanMovie = args.getString(EXTRAS_MOVIE);
        }
        return new TaskLoaderSearch(getContext(), kumpulanMovie);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset( Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);
    }
    View.OnClickListener myListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            String kumpulanMovie = editTitle.getText().toString();

            if (TextUtils.isEmpty(kumpulanMovie))return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE,kumpulanMovie);
            getLoaderManager().restartLoader(0,bundle, SearchFragment.this);
        }
    };
}
