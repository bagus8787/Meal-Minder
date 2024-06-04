package com.example.MealMinder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView harianTextView;
    private TextView mingguanTextView;
    private TextView bulananTextView;
    private ListView listView;
    private BarChart barChart;
    private ArrayList<String> makananHarianList = new ArrayList<>();
    private ArrayList<String> makananMingguanList = new ArrayList<>();
    private ArrayList<String> makananBulananList = new ArrayList<>();


    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PICK_IMAGE = 2;

    private void showImageDialog() {
        // Tampilkan dialog atau pilihan untuk mengambil gambar dari kamera atau galeri
        // Implementasikan dialog atau pengambilan gambar di sini
        // Misalnya, jika ingin langsung menggunakan kamera:
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

    private void updateListView(ArrayList<String> list) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

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
}
