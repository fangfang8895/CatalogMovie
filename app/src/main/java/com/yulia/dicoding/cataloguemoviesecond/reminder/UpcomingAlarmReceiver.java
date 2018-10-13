package com.yulia.dicoding.cataloguemoviesecond.reminder;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.yulia.dicoding.cataloguemoviesecond.BuildConfig;
import com.yulia.dicoding.cataloguemoviesecond.MainActivity;
import com.yulia.dicoding.cataloguemoviesecond.MovieItems;
import com.yulia.dicoding.cataloguemoviesecond.R;
import com.yulia.dicoding.cataloguemoviesecond.TaskLoader.TaskLoader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class UpcomingAlarmReceiver extends BroadcastReceiver {
    public static final String TYPE_UPCOMING = "UpcomingAlarm";
    public static final String EXTRA_TYPE = "type";
    public static final String EXTRA_MESSAGE= "message";
    private final int NOTIF_ID_UPCOMING = 201;
    private static final String CHANNEL_ID = "CatalogueMovie";
    private Context context;
    private static final String API_KEY = BuildConfig.API_KEY;
    public List<MovieItems> list = new ArrayList<>();

    public UpcomingAlarmReceiver(Context context) {
        context = context;
    }

    public UpcomingAlarmReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key="+API_KEY+"&language=en-US";
        TaskLoader taskLoader = new TaskLoader(context.getApplicationContext(), url);
        List<MovieItems> items = taskLoader.loadInBackground();
        int index = new Random().nextInt(items.size());
        MovieItems item = items.get(index);

        int notifId = NOTIF_ID_UPCOMING;
        String title = items.get(index).getTitle();
        String message = items.get(index).getOverview();

        showAlarmNotification(context,title, message, notifId);

    }

    private void showAlarmNotification(Context context, String title,String message, int id) {
        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat
                .Builder(context)
                .setSmallIcon(R.drawable.ic_menu_upcoming)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setAutoCancel(true)
                .setSound(alarmSound);

        notificationManagerCompat.notify(id, builder.build());

    }

    public void setRepeatingAlarm(Context context, String type, String time, String message) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, DailyReminder.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_TYPE, type);


        String timeArray[] = time.split(":");
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);


        int requestCode = NOTIF_ID_UPCOMING;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


    }


    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DailyReminder.class);
        int requestCode = NOTIF_ID_UPCOMING;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);
        alarmManager.cancel(pendingIntent);
    }


}
