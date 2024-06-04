package com.example.MealMinder;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MealMinder.model.MealData;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddJadwalActivity extends AppCompatActivity {

    private Spinner spinnerMealType;
    private Button buttonAddAlarm;
    private Button buttonSearchFood;
    private EditText deskripsi;
    private Button buttonSaveData;
    private String selectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        spinnerMealType = findViewById(R.id.spinner_meal_type);
        buttonAddAlarm = findViewById(R.id.button_add_alarm);
        buttonSearchFood = findViewById(R.id.button_search_food);
        buttonSaveData = findViewById(R.id.button_save_data);
        deskripsi = findViewById(R.id.deskripsi);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.custom_spinner,getResources().getStringArray(R.array.meal_types));
        spinnerMealType.setAdapter(adapter);
        buttonAddAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        buttonSearchFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddJadwalActivity.this, SearchFood.class);
                startActivity(intent);
            }
        });

        buttonSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                MealData.setMealData(AddJadwalActivity.this,spinnerSelection(),deskripsi.getText().toString(),hari,tanggal,bulan,waktu);
                AlarmManager alarmMgr;
                PendingIntent alarmIntent;

                alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//                Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                Intent intent = new Intent(getApplicationContext(), Notification.class);

                intent.putExtra("titleExtra", spinnerSelection());
                intent.putExtra("messageExtra", deskripsi.getText().toString());

                alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

// Set the alarm to start at 8:30 a.m.
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, jam);
                calendar.set(Calendar.MINUTE, menit);

// setRepeating() lets you specify a precise custom interval--in this case,
// 20 minutes.
//                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                        1000 * 60 * 10, alarmIntent);

                alarmMgr.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        alarmIntent
                );

                Toast.makeText(getApplicationContext(), "Meal plan added", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void save(){
    }
    private String hari,bulan,tanggal,waktu;

    @Override
    protected void onResume() {
        super.onResume();
        if(MealData.currentMeal != null){
            buttonSearchFood.setText(MealData.currentMeal);
        }
    }

    private void getDate(){
        Date newDate = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat day = new SimpleDateFormat("EEE");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat month = new SimpleDateFormat("MMM");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat date = new SimpleDateFormat("d");
        hari = day.format(newDate);
        bulan = month.format(newDate);
        tanggal = date.format(newDate);
    }

    private int jam = 0;
    private int menit = 0;

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        jam = hour;

        TimePickerDialog timePickerDialog = new TimePickerDialog(AddJadwalActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedTime = hourOfDay + ":" + minute;
                        waktu = selectedTime;
                        jam = hourOfDay;
                        menit = minute;
                        getDate();
                        String date = selectedTime +"\n"+hari+", "+tanggal+" "+bulan;
                        buttonAddAlarm.setText(date);
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }

    private String spinnerSelection() {
        return spinnerMealType.getSelectedItem().toString();
    }
}
