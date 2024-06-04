package com.example.MealMinder;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class feedback extends AppCompatActivity {

    Button btnSendFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        btnSendFeedback = findViewById(R.id.btnSendFeedback);
        btnSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start MainLayout activity
                Intent intent = new Intent(feedback.this, MainLayout.class);
                // Pass an extra parameter to indicate profile fragment should be selected
                intent.putExtra("selectedFragment", R.id.profil);
                startActivity(intent);
            }
        });
    }
}
