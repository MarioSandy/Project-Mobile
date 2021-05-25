package com.mariosandy.matematikaapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mariosandy.matematikaapps.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        binding.btnLogin.setOnClickListener(login());
        binding.tvLoginRegisterNow.setOnClickListener(registerNow());
    }

    private View.OnClickListener registerNow() {
        return v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        };
    }

    private View.OnClickListener login() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.etLoginEmail.getText().toString();
                String password = binding.etLoginPassword.getText().toString();
                String[] splitEmail = email.split("@", 0);

                if (checkInput(splitEmail, password)) {

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, task -> {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("uId", user.getUid());
                                    System.out.println(user.getUid());
                                    startActivity(intent);
                                } else {
                                    Log.w("Error Auth:", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Login Fail! Please check if the inputted email is registered", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        };
    }

    private boolean checkInput(String[] splitEmail, String password) {

        if (splitEmail.length == 1) {
            Toast.makeText(LoginActivity.this, "Email is required and must include @ and endswith .com", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!splitEmail[1].endsWith(".com")) {
            Toast.makeText(LoginActivity.this, "Email is required and must include @ and endswith .com", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.length() < 6) {
            Toast.makeText(LoginActivity.this, "Password is required & at least 6 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}