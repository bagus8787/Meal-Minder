package com.example.MealMinder;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MealMinder.adapter.RecyclerviewAdapter;
import com.example.MealMinder.model.FoodData;

import java.util.ArrayList;
import java.util.List;

public class SearchFood extends AppCompatActivity {

    RecyclerView foodRecycler;
    RecyclerviewAdapter recyclerviewAdapter;
    EditText searchView;
    CharSequence search="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchfood);

        searchView = findViewById(R.id.search_bar);

        List<FoodData> foodDataList = new ArrayList<>();
        foodDataList.add(new FoodData("Nasi Goreng", "168 kkal", R.drawable.nasi_goreng));
        foodDataList.add(new FoodData("Nasi Kuning", "100 kkal", R.drawable.nasi_kuning));
        foodDataList.add(new FoodData("Nasi Padang", "664 kkal", R.drawable.nasi_padang));
        foodDataList.add(new FoodData("Mie Goreng", "380 kkal", R.drawable.mie_goreng));
        foodDataList.add(new FoodData("Mie Ayam", "421 kkal", R.drawable.mie_ayam));
        foodDataList.add(new FoodData("Bakso", "218 kkal", R.drawable.bakso));

        setFoodRecycler(foodDataList);


        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                recyclerviewAdapter.getFilter().filter(charSequence);
                search = charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void setFoodRecycler(List<FoodData> foodDataList){
        foodRecycler = findViewById(R.id.foodRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        foodRecycler.setLayoutManager(layoutManager);
        recyclerviewAdapter = new RecyclerviewAdapter(this, foodDataList);
        foodRecycler.setAdapter(recyclerviewAdapter);
    }
}
