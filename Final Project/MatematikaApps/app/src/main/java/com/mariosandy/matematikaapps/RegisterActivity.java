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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mariosandy.matematikaapps.databinding.ActivityRegisterBinding;

import java.util.Arrays;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        reference = database.getReference().child("member").child("user_uid");

        binding.btnRegister.setOnClickListener(register());
    }

    private View.OnClickListener register() {
        return v -> {

            String idBimbel = binding.etRegisterBimbelId.getText().toString();
            String email = binding.etRegisterEmail.getText().toString();
            String name = binding.etRegisterName.getText().toString();
            String password = binding.etRegisterPassword.getText().toString();
            String confirmPassword = binding.etRegisterConfirmPassword.getText().toString();
            String[] splitEmail = email.split("@", 0);;

            if (checkInput(idBimbel, splitEmail, name, password, confirmPassword)) {
                Member member = new Member(idBimbel, email, name, password);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                reference.child(user.getUid()).setValue(member);
                                Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Log.w("Error Auth:", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Register Fail", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        };
    }

    private boolean checkInput(String idBimbel, String[] splitEmail, String name, String password, String  confirmPassword) {

        if (idBimbel.length() == 0) {
            Toast.makeText(RegisterActivity.this, "Bimbel_ID is required", Toast.LENGTH_SHORT).show();
            return false;
        } else if (splitEmail.length == 1) {
            Toast.makeText(RegisterActivity.this, "Email is required and must include @ and endswith .com", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!splitEmail[1].endsWith(".com")) {
            Toast.makeText(RegisterActivity.this, "Email is required and must include @ and endswith .com", Toast.LENGTH_SHORT).show();
            return false;
        } else if (name.length() < 5) {
            Toast.makeText(RegisterActivity.this, "Name is required and at least 5 character", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.length() < 6) {
            Toast.makeText(RegisterActivity.this, "Password is required & at least 6 characters", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!confirmPassword.equals(password)) {
            Toast.makeText(RegisterActivity.this, "Confirm password should be the same with Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }
}