package com.example.MealMinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class addjadwal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addjadwal);


    }

    public void onTambahJadwalClicked(View view) {
        Intent intent = new Intent(this, AturAlarm.class);
        startActivity(intent);
    }
}
