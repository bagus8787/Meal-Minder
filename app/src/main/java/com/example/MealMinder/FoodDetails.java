package com.example.MealMinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FoodDetails extends AppCompatActivity {

    ImageButton btnCheck;
    TextView foodName;
    ImageView foodImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        btnCheck = (ImageButton) findViewById(R.id.btn_checklist);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainLayout.class);
                startActivity(intent);
            }
        });

        foodName = findViewById(R.id.food_name);

        String s=getIntent().getStringExtra("foodname");
        String fooddescription=getIntent().getStringExtra("foodDesc");

        foodName.setText(s + " " + fooddescription);

        foodImg = findViewById(R.id.food_image);

        int foodImageResource=getIntent().getIntExtra("foodImage", 0);

        foodImg.setImageResource(foodImageResource);

    }
}