package com.yulia.dicoding.cataloguemoviesecond.SettingReminder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.yulia.dicoding.cataloguemoviesecond.R;
import com.yulia.dicoding.cataloguemoviesecond.reminder.AlarmPreference;
import com.yulia.dicoding.cataloguemoviesecond.reminder.DailyReminder;
import com.yulia.dicoding.cataloguemoviesecond.reminder.UpcomingAlarmReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.switch_daily)
    Switch switchDaily;
    @BindView(R.id.switch_upcoming)
    Switch switchUpcoming;

    public DailyReminder dailyReminder;
    public UpcomingAlarmReceiver upcomingAlarmReceiver;
    public AlarmPreference alarmPreference;
    private boolean isDaily, isUpcoming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        switchDaily.setOnClickListener(this);
        switchUpcoming.setOnClickListener(this);

        alarmPreference = new AlarmPreference(this);
        dailyReminder = new DailyReminder();
        upcomingAlarmReceiver = new UpcomingAlarmReceiver();
        getSupportActionBar().setTitle(R.string.setting_reminder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNotification();
    }

    private void setNotification() {
        if (alarmPreference.isDaily()) {
            switchDaily.setChecked(true);
        } else {
            switchDaily.setChecked(false);
        }

        if (alarmPreference.isUpcoming()) {
            switchUpcoming.setChecked(true);
        } else {
            switchUpcoming.setChecked(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_daily:
                isDaily = switchDaily.isChecked();
                if (isDaily) {
                    switchDaily.setEnabled(true);
                    alarmPreference.setDaily(isDaily);
                    dailyReminder.setRepeatingAlarm(this, DailyReminder.TYPE_REPEATING, "07:00", DailyReminder.EXTRA_MESSAGE);
                    Toast.makeText(this, R.string.activate_daily_reminder, Toast.LENGTH_SHORT).show();
                } else {
                    switchDaily.setChecked(false);
                    alarmPreference.setDaily(isDaily);
                    dailyReminder.cancelAlarm(SettingActivity.this);
                    Toast.makeText(this, R.string.turn_of_daily, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.switch_upcoming:
                isUpcoming = switchUpcoming.isChecked();
                if (isUpcoming){
                    switchUpcoming.setEnabled(true);
                    alarmPreference.setUpcoming(isUpcoming);
                    upcomingAlarmReceiver.setRepeatingAlarm(this, UpcomingAlarmReceiver.TYPE_UPCOMING, "08:00", UpcomingAlarmReceiver.EXTRA_MESSAGE);
                    Toast.makeText(this, R.string.activate_upcoming_reminder, Toast.LENGTH_SHORT).show();
                } else {
                    switchUpcoming.setChecked(false);
                    alarmPreference.setUpcoming(isUpcoming);
                    upcomingAlarmReceiver.cancelAlarm(SettingActivity.this);
                    Toast.makeText(this, R.string.turn_of_upcoming, Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}