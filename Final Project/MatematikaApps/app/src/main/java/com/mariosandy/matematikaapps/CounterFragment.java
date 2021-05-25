package com.mariosandy.matematikaapps;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mariosandy.matematikaapps.databinding.FragmentCounterBinding;

public class CounterFragment extends Fragment {

    private FragmentCounterBinding binding;
    private SharedPreferences sharedPreferences;
    private int counter;
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCounterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        counter = sharedPreferences.getInt("counter_number", 0);
        editor = sharedPreferences.edit();
        binding.tvCounterCounter.setText(String.valueOf(counter));
        binding.btnCounterPlus.setOnClickListener(plus());
        binding.btnCounterMinus.setOnClickListener(minus());
        binding.btnCounterReset.setOnClickListener(reset());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private View.OnClickListener plus() {
        return v -> {
           counter++;
           binding.tvCounterCounter.setText(String.valueOf(counter));
           editor.putInt("counter_number", counter);
           editor.apply();
        };
    }
    private View.OnClickListener minus() {
        return v -> {
           counter--;
           binding.tvCounterCounter.setText(String.valueOf(counter));
           editor.putInt("counter_number", counter);
           editor.apply();
        };
    }
    private View.OnClickListener reset() {
        return v -> {
           counter = 0;
           binding.tvCounterCounter.setText(String.valueOf(counter));
           editor.putInt("counter_number", counter);
           editor.apply();
        };
    }

}