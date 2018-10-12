package com.yulia.dicoding.cataloguemoviesecond;


import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yulia.dicoding.cataloguemoviesecond.db.DatabaseContract.CONTENT_URI;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.rv_favorite)
    RecyclerView rvFavorite;
    private FavoriteAdapter adapter;
    private Cursor list;
    private Context context;

    public FavoriteFragment() {
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        context = rootView.getContext();
        ButterKnife.bind(this, rootView);
        adapter = new FavoriteAdapter(getContext());
        adapter.notifyDataSetChanged();
        rvFavorite.setHasFixedSize(true);
        rvFavorite.setLayoutManager(new LinearLayoutManager(context));
        rvFavorite.setAdapter(adapter);
        new LoadFavoriteAsync().execute();
        return rootView;

    }
    @Override
    public void onResume(){
        super.onResume();
        new LoadFavoriteAsync().execute();
    }


    @Override
    public void onClick(View view) {

    }

    private class LoadFavoriteAsync extends AsyncTask<Void, Void, Cursor>{

        @Override
        protected Cursor doInBackground(Void... voids){
            return getContext().getContentResolver().query(
                    CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            );
        }
        @Override
        protected void onPostExecute(Cursor cursor){
            super.onPostExecute(cursor);

            list = cursor;
            adapter.setListFavorite(list);
            adapter.notifyDataSetChanged();
            if(list.getCount() == 0){
                Toast.makeText(getActivity(), "tidak ada data", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }

}
