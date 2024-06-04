package com.example.MealMinder;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchFoodActivity extends AppCompatActivity {

    private RecyclerView foodRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchfood);

        foodRecyclerView = findViewById(R.id.foodRecycler);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Tambahkan logika untuk menginisialisasi RecyclerView dengan adapter yang sesuai
    }
}

