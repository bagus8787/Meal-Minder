package com.example.MealMinder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;

import com.example.MealMinder.fragment.AddJadwalFragment;
import com.example.MealMinder.fragment.HomePageFragment;
import com.example.MealMinder.fragment.RekapMakananFragment;
import com.example.MealMinder.model.MealData;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class MainLayout extends AppCompatActivity
    implements BottomNavigationView
    .OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    com.example.MealMinder.fragment.RekapMakananFragment RekapMakananFragment = new RekapMakananFragment();
    AddJadwalFragment addJadwalFragment = new AddJadwalFragment();
    HomePageFragment homePageFragment = new HomePageFragment();
    profileFragment profileFragment = new profileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);

        createNotificationChannel();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        // Check if the activity was started with an extra parameter
        int selectedFragment = getIntent().getIntExtra("selectedFragment", R.id.home);
        bottomNavigationView.setSelectedItemId(selectedFragment);

    }

    private void createNotificationChannel()
    {
        String name = "Notif Channel";
        String desc = "A Description of the Channel";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("channel1", name, importance);

        channel.setDescription(desc);
        NotificationManager notificationManager = (NotificationManager) getSystemService(AppCompatActivity.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, homePageFragment)
                    .commit();
            return true;
        } else if (id == R.id.add) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, addJadwalFragment)
                    .commit();
            return true;
        } else if (id == R.id.note) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, RekapMakananFragment)
                    .commit();
            return true;
        } else if (id == R.id.profil) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, profileFragment)
                    .commit();
            return true;
        }
        return false;
    }
}