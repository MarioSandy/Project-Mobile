package com.mariosandy.matematikaapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mariosandy.matematikaapps.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private ActivityMainBinding binding;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("member").child("user_uid");
        Intent intent = getIntent();
        String uId = intent.getStringExtra("uId");

        FragmentManager fragmentManager = getSupportFragmentManager();
        CounterFragment counterFragment = new CounterFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.fl_fragment_container, counterFragment, "Counter Fragment")
                .commit();

        reference.child(uId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Member member = snapshot.getValue(Member.class);
                setTitle(member.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });

        binding.bottomNavigation.setOnNavigationItemSelectedListener(itemSelected());
    }
    private BottomNavigationView.OnNavigationItemSelectedListener itemSelected() {
        return item -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            CounterFragment counterFragment = new CounterFragment();
            AreaCounterFragment areaCounterFragment = new AreaCounterFragment();
            VolumeCounterFragment volumeCounterFragment = new VolumeCounterFragment();

            switch (item.getItemId()) {
                case R.id.i_counter:
                    fragmentManager.beginTransaction()
                            .replace(R.id.fl_fragment_container, counterFragment, "Counter Fragment")
                            .commit();
                    break;
                case R.id.i_area_counter:
                    fragmentManager.beginTransaction()
                            .replace(R.id.fl_fragment_container, areaCounterFragment, "Area Counter Fragment")
                            .commit();
                    break;
                case R.id.i_volume_counter:
                    fragmentManager.beginTransaction()
                            .replace(R.id.fl_fragment_container, volumeCounterFragment, "Volume Counter Fragment")
                            .commit();
                    break;
            }

            return true;
        };
    }
}