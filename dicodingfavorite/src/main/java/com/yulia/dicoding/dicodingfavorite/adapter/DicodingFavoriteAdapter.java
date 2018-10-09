package com.yulia.dicoding.dicodingfavorite.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.yulia.dicoding.dicodingfavorite.R;

import butterknife.BindView;

import static com.yulia.dicoding.dicodingfavorite.db.DatabaseContract.FavoriteColumns.OVERVIEW;
import static com.yulia.dicoding.dicodingfavorite.db.DatabaseContract.FavoriteColumns.RELEASE_DATE;
import static com.yulia.dicoding.dicodingfavorite.db.DatabaseContract.FavoriteColumns.TITLE;
import static com.yulia.dicoding.dicodingfavorite.db.DatabaseContract.getColumnString;

public class DicodingFavoriteAdapter extends CursorAdapter {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_about)
    TextView tvAbout;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.btn_detail)
    Button btnDetail;
    public DicodingFavoriteAdapter(Context context, Cursor c, boolean autoRequery){
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup){
        View view = LayoutInflater.from(context).inflate(R.layout.item_dicoding_favorite,viewGroup, false);
        return view;
    }

    @Override
    public Cursor getCursor(){
        return super.getCursor();
    }

    @Override
    public void bindView(View view , Context context, Cursor cursor){
        if(cursor != null){
            tvTitle.setText(getColumnString(cursor, TITLE));
            tvAbout.setText(getColumnString(cursor, OVERVIEW));
            tvDate.setText(getColumnString(cursor, RELEASE_DATE));


        }
    }
}