package com.yulia.dicoding.cataloguemoviesecond;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailFragment extends Fragment {
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


    public static String EXTRA_IMG = "extra_image";
    public static String EXTRA_TITLE = "extra_title";
    public static String EXTRA_ABOUT = "extra_about";
    public static String EXTRA_DATE = "extra_date";
    public static String EXTRA_LANG = "extra_lang";
    public static String EXTRA_POP = "extra_pop";
    public static String EXTRA_VOTE ="extra_vote";


    public DetailFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, rootView);


        return rootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvMovieTitle.setText(getArguments().getString(EXTRA_TITLE));
        tvSynopsis.setText(getArguments().getString(EXTRA_ABOUT));
        tvLangMovie.setText(getArguments().getString(EXTRA_LANG));
        tvDateDetail.setText(getArguments().getString(EXTRA_DATE));
       tvPopularity.setText(getArguments().getString(EXTRA_POP));
        tvVoteAverage.setText(getArguments().getString(EXTRA_VOTE));
        Glide
                .with(this)
                .load("http://image.tmdb.org/t/p/w185"+getArguments().getString(EXTRA_IMG))
                .into(imgMovieDetail);

    }

}
