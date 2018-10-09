package com.yulia.dicoding.cataloguemoviesecond;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private Cursor listFavorite;
    private Context context;


    public FavoriteAdapter(Context context){
        this.context = context;

    }
    public void setListFavorite(Cursor listFavorite){
        this.listFavorite = listFavorite;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_items, parent, false);
        FavoriteAdapter.ViewHolder viewHolder = new FavoriteAdapter.ViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, final int position) {
        final FavoriteItems note = getItem(position);
        Glide
                .with(context)
                .load("http://image.tmdb.org/t/p/w185" + note.getPosterPath())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imgMovie);
       holder.tvTitle.setText(note.getTitle());
        holder.tvSynopsis.setText(note.getOverview());
        holder.tvDate.setText(note.getReleaseDate());
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DetailFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(DetailFragment.EXTRA_ID, note.getId());
                bundle.putString(DetailFragment.EXTRA_TITLE, note.getTitle());
                bundle.putString(DetailFragment.EXTRA_DATE, note.getReleaseDate());
                bundle.putString(DetailFragment.EXTRA_ABOUT, note.getOverview());
                bundle.putString(DetailFragment.EXTRA_LANG, note.getLanguage());
                bundle.putString(DetailFragment.EXTRA_POP, note.getPopularityMovie());
                bundle.putString(DetailFragment.EXTRA_VOTE, note.getVoteAveragedetail());
                bundle.putString(DetailFragment.EXTRA_IMG, note.getPosterPath());
                fragment.setArguments(bundle);
                android.support.v4.app.FragmentManager mFragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (listFavorite == null )
        return 0;
        return listFavorite.getCount();
    }

    private FavoriteItems getItem(int position) {
        if (!listFavorite.moveToPosition(position)){
            throw new IllegalStateException("Position invalid");
        }
        return new FavoriteItems(listFavorite);
    }


    class ViewHolder extends RecyclerView.ViewHolder{
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

        ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
