package com.example.MealMinder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.widget.ListView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;
import java.util.List;

public class homepage extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PICK_IMAGE = 2;

    public void onImageButtonClick(View view) {
        showImageDialog();
    }

    private void showImageDialog() {
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void dispatchPickImageIntent() {
        Intent pickImageIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageIntent.setType("image/*");
        startActivityForResult(pickImageIntent, REQUEST_PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                // Lakukan sesuatu dengan gambar yang diambil dari kamera
            } else if (requestCode == REQUEST_PICK_IMAGE) {
                Uri selectedImageUri = data.getData();
                // Lakukan sesuatu dengan gambar yang dipilih dari galeri
            }
        }
    }

    private void tambahkanMakanan(String tab, ArrayList<String> list) {
        list.add("Makanan baru"); // Ganti ini dengan cara Anda menambahkan data makanan

        // Tampilkan pesan sesuai dengan tab yang dipilih
        String pesan = "Tambahkan makanan pada tab " + tab;
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();

        // Perbarui tampilan list jika tab harian yang dipilih
        if (tab.equals("Harian")) {
            updateListView(list);
        } else if (tab.equals("Mingguan")) {
            updateBarChart(list);
        }
    }

    ListView listView;

    private void updateListView(ArrayList<String> list) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

    BarChart barChart;
    private void updateBarChart(ArrayList<String> list) {
        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            entries.add(new BarEntry(i, 4));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Label Data");
        dataSet.setColor(Color.BLUE);

        BarData data = new BarData(dataSet);
        barChart.setData(data);
        barChart.invalidate();

        Legend legend = barChart.getLegend();
        legend.setEnabled(false);
    }

    Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        btnSearch = (Button) findViewById(R.id.buttonTambah);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchFood.class);
                startActivity(intent);
            }
        });

    }
}