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
        foodDataList.add(new FoodData("Tahu Tek", "387 kkal", R.drawable.tahutek));
        foodDataList.add(new FoodData("Kerak Telor", "400 kkal", R.drawable.keraktelor));
        foodDataList.add(new FoodData("Soto Betawi", "135 kkal", R.drawable.sotobetawi));
        foodDataList.add(new FoodData("Pizza 1 Slice", "192 kkal", R.drawable.pizza));
        foodDataList.add(new FoodData("Lasagna", "510 kkal", R.drawable.lasagna));
        foodDataList.add(new FoodData("Gado Gado", "318 kkal", R.drawable.gado_gado));
        foodDataList.add(new FoodData("Rawon", "288 kkal", R.drawable.rawon));
        foodDataList.add(new FoodData("Sate Ayam", "225 kkal", R.drawable.sate_ayam));
        foodDataList.add(new FoodData("Sate Kambing", "216 kkal", R.drawable.sate_kambing));
        foodDataList.add(new FoodData("Pempek", "234 kkal", R.drawable.pempek));
        foodDataList.add(new FoodData("Rendang", "195 kkal", R.drawable.rendang));
        foodDataList.add(new FoodData("Sop Buntut", "68 kkal", R.drawable.sop_buntut));
        foodDataList.add(new FoodData("Ikan Bakar", "260 kkal", R.drawable.ikan_bakar));
        foodDataList.add(new FoodData("Capcay", "120 kkal", R.drawable.capcay));
        foodDataList.add(new FoodData("Bubur Ayam", "372 kkal", R.drawable.bubur_ayam));
        foodDataList.add(new FoodData("Pecel Lele", "292 kkal", R.drawable.pecel_lele));
        foodDataList.add(new FoodData("Siomay", "103 kkal", R.drawable.siomay));
        foodDataList.add(new FoodData("Mie Aceh", "238 kkal", R.drawable.mie_aceh));
        foodDataList.add(new FoodData("Bebek Goreng", "674 kkal", R.drawable.bebek));
        foodDataList.add(new FoodData("Soto Ayam", "312 kkal", R.drawable.soto_ayam));
        foodDataList.add(new FoodData("Air Mineral", "0 kkal", R.drawable.airmineral));
        foodDataList.add(new FoodData("Jus Stroberi", "38 kkal", R.drawable.jusstroberi));
        foodDataList.add(new FoodData("Pop Ice", "100 kkal", R.drawable.popice));
        foodDataList.add(new FoodData("Es Teh", "90 kkal", R.drawable.esteh));
        foodDataList.add(new FoodData("Matcha Latte", "190 kkal", R.drawable.matchalatte));
        foodDataList.add(new FoodData("Kopi Gula Aren", "150 kkal", R.drawable.kopisusugulaaren));


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
