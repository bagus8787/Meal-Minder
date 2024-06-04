package com.example.MealMinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Find the buttons by their IDs

        Button signUpButton = findViewById(R.id.button2);
        Button loginButton = findViewById(R.id.btn_log_in);

        // Set click listeners for the buttons
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the signup activity
                Intent signUpIntent = new Intent(IntroActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the login activity
                Intent loginIntent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
    }
}