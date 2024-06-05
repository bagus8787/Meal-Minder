package com.example.MealMinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.MealMinder.databinding.ActivityLoginBinding;
import com.example.MealMinder.model.MealData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private ProgressDialog progressDialog;

    private FirebaseAuth.AuthStateListener mAuthListener;

    FirebaseUser user;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase instances
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        // Setup progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Please Wait");

        // Set login button click listener
        binding.btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get email and password from input fields
                String email = binding.editTextTextPersonName2.getText().toString();
                String password = binding.editTextTextPersonName3.getText().toString();

                // Validate input fields
                if (email.isEmpty()) {
                    binding.editTextTextPersonName2.setError("Enter Your Email");
                } else if (password.isEmpty()) {
                    binding.editTextTextPersonName3.setError("Enter Your Password");
                } else {
                    // Show progress dialog
                    progressDialog.show();

                    // Attempt to sign in with Firebase Auth
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(task -> {
                                // Log the completion of the task
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.getResult().getUser().getEmail());

                                user = task.getResult().getUser();

                                // Dismiss progress dialog
                                progressDialog.dismiss();

                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(LoginActivity.this, MainLayout.class));
                                        finish();
                                    } else {
                                        // Log the error
                                        Log.e(TAG, "signInWithEmail:failure", task.getException());
                                        // Show error message on failure
                                        Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                            }
                            );
                }
            }
        });

        // Set forgot password text click listener
        binding.textForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ForgotPassword activity
                Intent intent = new Intent(LoginActivity.this, forgotpassword.class);
                startActivity(intent);
            }
        });
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        auth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            auth.removeAuthStateListener(mAuthListener);
//        }
//    }
}