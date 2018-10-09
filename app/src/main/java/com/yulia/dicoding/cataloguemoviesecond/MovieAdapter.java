package com.yulia.dicoding.cataloguemoviesecond;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<MovieItems> mData = new ArrayList<>();
    private Context context;



    public MovieAdapter(Context context){
        this.context = context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_movie)
        ImageView imgMovie;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_about)
        TextView tvSynopsis;
        @BindView(R.id.btn_detail)
        Button btnDetail;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setData(ArrayList<MovieItems> items){
        mData = items;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(mView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position){
        Glide
                .with(context)
                .load("http://image.tmdb.org/t/p/w185" + mData.get(position).getPosterPath())
                .apply(RequestOptions.centerCropTransform())
                .into(viewHolder.imgMovie);
        viewHolder.tvTitle.setText(mData.get(position).getTitle());
        viewHolder.tvSynopsis.setText(mData.get(position).getOverview());
        viewHolder.tvDate.setText(mData.get(position).getReleaseDate());
        viewHolder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DetailFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(DetailFragment.EXTRA_ID, mData.get(position).getId());
                bundle.putString(DetailFragment.EXTRA_TITLE, mData.get(position).getTitle());
                bundle.putString(DetailFragment.EXTRA_DATE, mData.get(position).getReleaseDate());
                bundle.putString(DetailFragment.EXTRA_ABOUT, mData.get(position).getOverview());
                bundle.putString(DetailFragment.EXTRA_LANG, mData.get(position).getLanguage());
                bundle.putString(DetailFragment.EXTRA_POP, mData.get(position).getPopularityMovie());
                bundle.putString(DetailFragment.EXTRA_VOTE, mData.get(position).getVoteAveragedetail());
                bundle.putString(DetailFragment.EXTRA_IMG, mData.get(position).getPosterPath());
                fragment.setArguments(bundle);
                android.support.v4.app.FragmentManager mFragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });



    }

}

