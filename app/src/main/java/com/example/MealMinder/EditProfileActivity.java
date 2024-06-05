package com.example.MealMinder;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MealMinder.helper.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditProfileActivity extends AppCompatActivity {

    String dateSelect = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        SessionManager sessionManager = new SessionManager(this);

        LinearLayout keluar = findViewById(R.id.keluar);
        EditText edtName = findViewById(R.id.edtName);
        EditText edtNomorTelepon = findViewById(R.id.edtNomorTelepon);
        EditText edtTglLahir = findViewById(R.id.edtTglLahir);

        Button btnSave = findViewById(R.id.btn_save);

        edtName.setText(sessionManager.getNamaUser());
        edtNomorTelepon.setText(sessionManager.getNoUser());
        edtTglLahir.setText(sessionManager.getTanggalLahir());

        edtTglLahir.setOnClickListener(v->{
            final Calendar newCalendar = Calendar.getInstance();
            final DatePickerDialog StartTime = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(year, monthOfYear, dayOfMonth);

                    Locale formatLocaleIdn = new Locale("in", "ID");
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", formatLocaleIdn);

                    edtTglLahir.setText(dateFormatter.format(newDate.getTime()));
                    dateSelect = edtTglLahir.getText().toString();
                }

            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

            StartTime.show();
        });

        btnSave.setOnClickListener(v->{
            String name = edtName.getText().toString();
            String nomorTlp = edtNomorTelepon.getText().toString();
            String tglLahir = edtTglLahir.getText().toString();

            sessionManager.setNameUser(name);
            sessionManager.setNoUser(nomorTlp);
            sessionManager.setTanggalLahir(tglLahir);

            Toast.makeText(this, "Profile berhasil diupdate", Toast.LENGTH_SHORT).show();
        });


        keluar.setOnClickListener(v->{
            onBackPressed();
        });
    }
}