package com.yulia.dicoding.cataloguemoviesecond.reminder;

import android.content.Context;
import android.content.SharedPreferences;

public class AlarmPreference {
    private final String PREF_NAME ="AlarmPreference";
    private final String KEY_DAILY_MESSAGE = "daily";
    private final String KEY_UPCOMING_MESSAGE ="upcoming";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    public AlarmPreference(Context context){
        mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }

   public void setDaily(boolean dailyStatus){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(KEY_DAILY_MESSAGE, dailyStatus);
        editor.apply();
    }

    public boolean isDaily(){
        return mSharedPreferences.getBoolean(KEY_DAILY_MESSAGE, false);
    }

    public void setUpcoming(boolean upcomingStatus){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(KEY_UPCOMING_MESSAGE, upcomingStatus);
        editor.apply();
    }
    public boolean isUpcoming(){
        return mSharedPreferences.getBoolean(KEY_UPCOMING_MESSAGE, false);
    }
}
