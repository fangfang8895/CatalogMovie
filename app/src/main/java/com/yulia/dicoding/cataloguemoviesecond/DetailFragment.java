package com.yulia.dicoding.cataloguemoviesecond;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yulia.dicoding.cataloguemoviesecond.db.DatabaseContract;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yulia.dicoding.cataloguemoviesecond.db.DatabaseContract.CONTENT_URI;


public class DetailFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.tv_movie_title)
    TextView tvMovieTitle;
    @BindView(R.id.tv_date_detail)
    TextView tvDateDetail;
    @BindView(R.id.tv_vote_average)
    TextView tvVoteAverage;
    @BindView(R.id.tv_popularity)
    TextView tvPopularity;
    @BindView(R.id.tv_lang_movie)
    TextView tvLangMovie;
    @BindView(R.id.tv_synopsis)
    TextView tvSynopsis;
    @BindView(R.id.img_movie_detail)
    ImageView imgMovieDetail;
    @BindView(R.id.button_favorit)
    Button btnFavorite;

    public static String EXTRA_ID = "extra_id";
    public static String EXTRA_IMG = "extra_image";
    public static String EXTRA_TITLE = "extra_title";
    public static String EXTRA_ABOUT = "extra_about";
    public static String EXTRA_DATE = "extra_date";
    public static String EXTRA_LANG = "extra_lang";
    public static String EXTRA_POP = "extra_pop";
    public static String EXTRA_VOTE ="extra_vote";
   /* public static String IS_FAVORITE="is_favorite";*/
    public static int RESULT_ADD = 101;
    public static int RESULT_DELETE = 301;

    private Context mContext;
    /*private FavoriteHelper favoriteHelper;
    private boolean isFavorite = false;
    private int favorite;*/

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, rootView);
        btnFavorite.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getContext();
        tvMovieTitle.setText(getArguments().getString(EXTRA_TITLE));
        tvSynopsis.setText(getArguments().getString(EXTRA_ABOUT));
        tvLangMovie.setText(getArguments().getString(EXTRA_LANG));
        tvDateDetail.setText(getArguments().getString(EXTRA_DATE));
        tvPopularity.setText(getArguments().getString(EXTRA_POP));
        tvVoteAverage.setText(getArguments().getString(EXTRA_VOTE));
        Glide
                .with(this)
                .load("http://image.tmdb.org/t/p/w185" + getArguments().getString(EXTRA_IMG))
                .into(imgMovieDetail);

    }


  /*  private void FavoriteRemove(){

        mContext.getContentResolver().delete(getArguments().getInt(EXTRA_ID), null,null);
        getActivity().setResult(RESULT_DELETE, null);
        Toast.makeText(getActivity(), R.string.remove_favourite, Toast.LENGTH_SHORT).show();
    }*/



    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.button_favorit){
            ContentValues cv = new ContentValues();
            cv.put(DatabaseContract.FavoriteColumns._ID, getArguments().getInt(EXTRA_ID));
            cv.put(DatabaseContract.FavoriteColumns.TITLE, getArguments().getString(EXTRA_TITLE));
            cv.put(DatabaseContract.FavoriteColumns.POSTERPATH, getArguments().getString(EXTRA_IMG));
            cv.put(DatabaseContract.FavoriteColumns.OVERVIEW, getArguments().getString(EXTRA_ABOUT));
            cv.put(DatabaseContract.FavoriteColumns.RELEASE_DATE,getArguments().getString(EXTRA_DATE));
            cv.put(DatabaseContract.FavoriteColumns.VOTE, getArguments().getString(EXTRA_VOTE));
            cv.put(DatabaseContract.FavoriteColumns.LANGUAGE, getArguments().getString(EXTRA_LANG));
            cv.put(DatabaseContract.FavoriteColumns.POPULARITY, getArguments().getString(EXTRA_POP));

            mContext.getContentResolver().insert(CONTENT_URI, cv);
            getActivity().setResult(RESULT_ADD);
            Toast.makeText(getActivity(), R.string.add_as_favourite, Toast.LENGTH_SHORT).show();
            btnFavorite.setText("Favorited");
        }
    }
}

