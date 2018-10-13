package com.yulia.dicoding.cataloguemoviesecond;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.yulia.dicoding.cataloguemoviesecond.SettingReminder.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    private Cursor list;
    public static Context contextOfApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.upcoming_movies);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        contextOfApplication = getApplicationContext();

        navigationView.setNavigationItemSelectedListener(this);
        
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
       if (savedInstanceState == null){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UpcomingFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_upcoming);
       }



    }


    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }

        @Override
        public void onBackPressed () {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override

        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                Intent mIntent = new Intent (Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
            }

            return super.onOptionsItemSelected(item);
        }





    @Override
    public boolean onNavigationItemSelected(@Nullable  MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_upcoming:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UpcomingFragment()).commit();
            getSupportActionBar().setTitle(R.string.upcoming_movies);
            break;
                
            case R.id.nav_nowplaying:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NowPlayingFragment()).commit();
                getSupportActionBar().setTitle(R.string.now_playing);
                break;

            case R.id.nav_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();
                getSupportActionBar().setTitle(R.string.search);
                break;

            case R.id.nav_favorit:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavoriteFragment()).commit();
                getSupportActionBar().setTitle(R.string.favorit_item);
                break;

            case R.id.nav_setting:
                Intent mIntent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(mIntent);

                
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
        
        

     
    }



}
