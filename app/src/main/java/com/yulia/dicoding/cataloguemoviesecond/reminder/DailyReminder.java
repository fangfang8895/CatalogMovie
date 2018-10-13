package com.yulia.dicoding.cataloguemoviesecond.reminder;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.yulia.dicoding.cataloguemoviesecond.MainActivity;
import com.yulia.dicoding.cataloguemoviesecond.R;

import java.util.Calendar;

public class DailyReminder extends BroadcastReceiver {
    public static final String TYPE_REPEATING ="RepeatingAlarm";
    public static final String EXTRA_MESSAGE= "message";
    public static final String EXTRA_TYPE ="type";
    private final int NOTIF_ID_REPEATING = 101;
    private static final String CHANNEL_ID = "CatalogueMovie";
    private Context context;
    public DailyReminder(Context context){
        context = context;
    }
    public DailyReminder(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        String title = context.getResources().getString(R.string.app_name);
        int notifId = NOTIF_ID_REPEATING;


        showAlarmNotification(context,title, message, notifId);


    }

    private void showAlarmNotification(Context context, String title, String message, int notifId) {
        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, context.getResources().getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH );
            channel.enableVibration(true);
            notificationManagerCompat.createNotificationChannel(channel);
        }
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, notifId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat
                .Builder(context, message)
                .setSmallIcon(R.drawable.ic_menu_upcoming)
                .setContentTitle(title)
                .setContentText(context.getResources().getString(R.string.daily_reminder))
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setSound(alarmSound)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setAutoCancel(true)
                .setChannelId(CHANNEL_ID);

                notificationManagerCompat.notify(notifId, builder.build());
    }

    public void setRepeatingAlarm(Context context, String type, String time, String message){
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, DailyReminder.class);
        intent.putExtra(EXTRA_TYPE, type);
        intent.putExtra(EXTRA_MESSAGE, message);

        String timeArray[] = time.split(":");
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);


        int requestCode = NOTIF_ID_REPEATING;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);



    }


    public void cancelAlarm(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DailyReminder.class);
        int requestCode = NOTIF_ID_REPEATING;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);
        alarmManager.cancel(pendingIntent);
    }



}
