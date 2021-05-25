package com.mariosandy.matematikaapps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mariosandy.matematikaapps.databinding.FragmentAreaCounterBinding;

import java.util.Arrays;

public class AreaCounterFragment extends Fragment {

    private FragmentAreaCounterBinding binding;
    private boolean[] buttons = new boolean[3];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAreaCounterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnAreaCounterSquare.setOnClickListener(square());
        binding.btnAreaCounterTriangle.setOnClickListener(triangle());
        binding.btnAreaCounterCircle.setOnClickListener(circle());
        binding.btnAreaCounterCalculate.setOnClickListener(calculate());
    }

    private View.OnClickListener square() {
        return v -> {
            binding.tvAreaCounterSideBaseRadius.setVisibility(View.GONE);
            binding.etAreaCounterSideBaseRadius.setVisibility(View.GONE);
            binding.tvAreaCounterHeight.setVisibility(View.GONE);
            binding.etAreaCounterHeight.setVisibility(View.GONE);
            binding.tvAreaCounterResult.setVisibility(View.GONE);
            binding.tvAreaCounterResultNumber.setVisibility(View.GONE);
            binding.etAreaCounterSideBaseRadius.setText("");
            binding.etAreaCounterHeight.setText("");
            Arrays.fill(buttons, false);
            buttons[0] = true;
            binding.tvAreaCounterSideBaseRadius.setText("Side");
            binding.tvAreaCounterSideBaseRadius.setVisibility(View.VISIBLE);
            binding.etAreaCounterSideBaseRadius.setVisibility(View.VISIBLE);
        };
    }

    private View.OnClickListener triangle() {
        return v -> {
            binding.tvAreaCounterSideBaseRadius.setVisibility(View.GONE);
            binding.etAreaCounterSideBaseRadius.setVisibility(View.GONE);
            binding.tvAreaCounterHeight.setVisibility(View.GONE);
            binding.etAreaCounterHeight.setVisibility(View.GONE);
            binding.tvAreaCounterResult.setVisibility(View.GONE);
            binding.tvAreaCounterResultNumber.setVisibility(View.GONE);
            binding.etAreaCounterSideBaseRadius.setText("");
            binding.etAreaCounterHeight.setText("");
            Arrays.fill(buttons, false);
            buttons[1] = true;
            binding.tvAreaCounterSideBaseRadius.setText("Base");
            binding.tvAreaCounterHeight.setText("Height");
            binding.tvAreaCounterSideBaseRadius.setVisibility(View.VISIBLE);
            binding.etAreaCounterSideBaseRadius.setVisibility(View.VISIBLE);
            binding.tvAreaCounterHeight.setVisibility(View.VISIBLE);
            binding.etAreaCounterHeight.setVisibility(View.VISIBLE);
        };
    }

    private View.OnClickListener circle() {
        return v -> {
            binding.tvAreaCounterSideBaseRadius.setVisibility(View.GONE);
            binding.etAreaCounterSideBaseRadius.setVisibility(View.GONE);
            binding.tvAreaCounterHeight.setVisibility(View.GONE);
            binding.etAreaCounterHeight.setVisibility(View.GONE);
            binding.tvAreaCounterResult.setVisibility(View.GONE);
            binding.tvAreaCounterResultNumber.setVisibility(View.GONE);
            binding.etAreaCounterSideBaseRadius.setText("");
            binding.etAreaCounterHeight.setText("");
            Arrays.fill(buttons, false);
            buttons[2] = true;
            binding.tvAreaCounterSideBaseRadius.setText("Radius");
            binding.tvAreaCounterSideBaseRadius.setVisibility(View.VISIBLE);
            binding.etAreaCounterSideBaseRadius.setVisibility(View.VISIBLE);
        };
    }

    private View.OnClickListener calculate() {
        return v -> {
            if (buttons[0]) {
                int side = Integer.parseInt(binding.etAreaCounterSideBaseRadius.getText().toString());
                int result = squareArea(side);
                binding.tvAreaCounterResultNumber.setText(String.valueOf(result));
                binding.tvAreaCounterResult.setVisibility(View.VISIBLE);
                binding.tvAreaCounterResultNumber.setVisibility(View.VISIBLE);
            }
            if (buttons[1]) {
                int base = Integer.parseInt(binding.etAreaCounterSideBaseRadius.getText().toString());
                int height = Integer.parseInt(binding.etAreaCounterHeight.getText().toString());
                int result = triangleArea(base, height);
                binding.tvAreaCounterResultNumber.setText(String.valueOf(result));
                binding.tvAreaCounterResult.setVisibility(View.VISIBLE);
                binding.tvAreaCounterResultNumber.setVisibility(View.VISIBLE);
            }
            if (buttons[2]) {
                int radius = Integer.parseInt(binding.etAreaCounterSideBaseRadius.getText().toString());
                int result = circleArea(radius);
                binding.tvAreaCounterResultNumber.setText(String.valueOf(result));
                binding.tvAreaCounterResult.setVisibility(View.VISIBLE);
                binding.tvAreaCounterResultNumber.setVisibility(View.VISIBLE);
            }
        };
    }

    private int squareArea(int side) {
        return side * side;
    }

    private int triangleArea(int base, int height) {
        return base * height / 2;
    }

    private int circleArea(int radius) {
        return (int)Math.round(3.14 * radius * radius);
    }

}