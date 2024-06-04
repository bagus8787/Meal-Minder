package com.example.MealMinder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.MealMinder.databinding.ActivityForgotpasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {

    ActivityForgotpasswordBinding binding;

    FirebaseAuth auth;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityForgotpasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Reset Password");
        progressDialog.setMessage("Sending email...");
        progressDialog.setCancelable(false); // Menghindari pembatalan dialog oleh pengguna

        binding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.editTextTextPersonName2.getText().toString();

                if (email.isEmpty()) {
                    binding.editTextTextPersonName2.setError("Enter Email");
                } else {
                    progressDialog.show(); // Tampilkan ProgressDialog sebelum memulai proses

                    auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressDialog.dismiss(); // Sembunyikan ProgressDialog setelah selesai

                            if (task.isSuccessful()) {
                                Toast.makeText(forgotpassword.this, "Please Check Your Email", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(forgotpassword.this, LoginActivity.class));
                            } else {
                                Toast.makeText(forgotpassword.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(forgotpassword.this, LoginActivity.class));
            }
        });
    }
}
