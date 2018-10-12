package com.yulia.dicoding.cataloguemoviesecond.Widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.yulia.dicoding.cataloguemoviesecond.FavoriteItems;
import com.yulia.dicoding.cataloguemoviesecond.R;

import java.util.concurrent.ExecutionException;

import static com.yulia.dicoding.cataloguemoviesecond.db.DatabaseContract.CONTENT_URI;

class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
   private Cursor cursor;
   private Context mContext;
   private int mAppWidgetId;


   public StackRemoteViewsFactory(Context context, Intent intent){
       mContext = context;
       mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

   }
    @Override
    public void onCreate() {
        cursor = mContext.getContentResolver().query(
                CONTENT_URI,
                null,
                null,
                null,
                null
        );



    }

    @Override
    public void onDataSetChanged() {


    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int i) {
       FavoriteItems favoriteItems = getItem(i);
       RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.favorite_widget_item);

       Bitmap bmp = null;
        try {
            bmp = Glide.with(mContext)
                    .asBitmap()
                    .load("http://image.tmdb.org/t/p/w185"+ favoriteItems.getPosterPath())
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();

        }catch (InterruptedException | ExecutionException e){
            Log.d("Widget Load Error","error");
        }
        rv.setImageViewBitmap(R.id.imageView, bmp);

        Bundle extras = new Bundle();
        extras.putInt(FavoriteWidget.EXTRA_ITEM, i);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    private FavoriteItems getItem(int position){
       if (!cursor.moveToPosition(position)){
           throw new IllegalStateException("Position Invalid");
       }
       return new FavoriteItems(cursor);

    }
}
