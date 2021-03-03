package com.example.mentolapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mentolapp1.databinding.ActivitySigninBinding;

public class SigninActivity extends AppCompatActivity {

    private ActivitySigninBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnLogin.setOnClickListener(goHome());
        binding.imgbtnSignup.setOnClickListener(goRegister());
        
        
    }
    
    private View.OnClickListener goHome(){
       return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SigninActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }

    private View.OnClickListener goRegister(){
       return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SigninActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        };
    }
}