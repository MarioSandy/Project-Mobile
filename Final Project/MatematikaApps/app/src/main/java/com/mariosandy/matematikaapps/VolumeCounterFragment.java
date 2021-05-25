package com.mariosandy.matematikaapps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mariosandy.matematikaapps.databinding.FragmentVolumeCounterBinding;

import java.util.Arrays;

public class VolumeCounterFragment extends Fragment {

    private FragmentVolumeCounterBinding binding;
    private boolean[] buttons = new boolean[3];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVolumeCounterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnVolumeCounterBeams.setOnClickListener(beams());
        binding.btnVolumeCounterPyramid.setOnClickListener(pyramid());
        binding.btnVolumeCounterTube.setOnClickListener(tube());
        binding.btnVolumeCounterCalculate.setOnClickListener(calculate());
    }

    private View.OnClickListener beams() {
        return v -> {
            binding.tvVolumeCounterLengthRadius.setVisibility(View.GONE);
            binding.etVolumeCounterLengthRadius.setVisibility(View.GONE);
            binding.tvVolumeCounterWidthHeight.setVisibility(View.GONE);
            binding.etVolumeCounterWidthHeight.setVisibility(View.GONE);
            binding.tvVolumeCounterHeight.setVisibility(View.GONE);
            binding.etVolumeCounterHeight.setVisibility(View.GONE);
            binding.etVolumeCounterLengthRadius.setText("");
            binding.etVolumeCounterWidthHeight.setText("");
            binding.etVolumeCounterHeight.setText("");
            Arrays.fill(buttons, false);
            buttons[0] = true;
            binding.tvVolumeCounterLengthRadius.setText("Length");
            binding.tvVolumeCounterWidthHeight.setText("Width");
            binding.tvVolumeCounterHeight.setText("Height");
            binding.llVolumeCounterLayoutForm.setWeightSum(3);
            binding.tvVolumeCounterLengthRadius.setVisibility(View.VISIBLE);
            binding.tvVolumeCounterWidthHeight.setVisibility(View.VISIBLE);
            binding.tvVolumeCounterHeight.setVisibility(View.VISIBLE);
            binding.etVolumeCounterLengthRadius.setVisibility(View.VISIBLE);
            binding.etVolumeCounterWidthHeight.setVisibility(View.VISIBLE);
            binding.etVolumeCounterHeight.setVisibility(View.VISIBLE);
        };
    }

    private View.OnClickListener pyramid() {
        return v -> {
            binding.tvVolumeCounterLengthRadius.setVisibility(View.GONE);
            binding.etVolumeCounterLengthRadius.setVisibility(View.GONE);
            binding.tvVolumeCounterWidthHeight.setVisibility(View.GONE);
            binding.etVolumeCounterWidthHeight.setVisibility(View.GONE);
            binding.tvVolumeCounterHeight.setVisibility(View.GONE);
            binding.etVolumeCounterHeight.setVisibility(View.GONE);
            binding.etVolumeCounterLengthRadius.setText("");
            binding.etVolumeCounterWidthHeight.setText("");
            binding.etVolumeCounterHeight.setText("");
            Arrays.fill(buttons, false);
            buttons[1] = true;
            binding.tvVolumeCounterLengthRadius.setText("Length");
            binding.tvVolumeCounterWidthHeight.setText("Width");
            binding.tvVolumeCounterHeight.setText("Height");
            binding.llVolumeCounterLayoutForm.setWeightSum(3);
            binding.tvVolumeCounterLengthRadius.setVisibility(View.VISIBLE);
            binding.tvVolumeCounterWidthHeight.setVisibility(View.VISIBLE);
            binding.tvVolumeCounterHeight.setVisibility(View.VISIBLE);
            binding.etVolumeCounterLengthRadius.setVisibility(View.VISIBLE);
            binding.etVolumeCounterWidthHeight.setVisibility(View.VISIBLE);
            binding.etVolumeCounterHeight.setVisibility(View.VISIBLE);
        };
    }

    private View.OnClickListener tube() {
        return v -> {
            binding.tvVolumeCounterLengthRadius.setVisibility(View.GONE);
            binding.etVolumeCounterLengthRadius.setVisibility(View.GONE);
            binding.tvVolumeCounterWidthHeight.setVisibility(View.GONE);
            binding.etVolumeCounterWidthHeight.setVisibility(View.GONE);
            binding.tvVolumeCounterHeight.setVisibility(View.GONE);
            binding.etVolumeCounterHeight.setVisibility(View.GONE);
            binding.etVolumeCounterLengthRadius.setText("");
            binding.etVolumeCounterWidthHeight.setText("");
            binding.etVolumeCounterHeight.setText("");
            Arrays.fill(buttons, false);
            buttons[2] = true;
            binding.tvVolumeCounterLengthRadius.setText("Radius");
            binding.tvVolumeCounterWidthHeight.setText("Height");
            binding.llVolumeCounterLayoutForm.setWeightSum(2);
            binding.tvVolumeCounterLengthRadius.setVisibility(View.VISIBLE);
            binding.tvVolumeCounterWidthHeight.setVisibility(View.VISIBLE);
            binding.etVolumeCounterLengthRadius.setVisibility(View.VISIBLE);
            binding.etVolumeCounterWidthHeight.setVisibility(View.VISIBLE);
        };
    }

    private View.OnClickListener calculate() {
        return v -> {
            if (buttons[0]) {
                int length = Integer.parseInt(binding.etVolumeCounterLengthRadius.getText().toString());
                int width = Integer.parseInt(binding.etVolumeCounterWidthHeight.getText().toString());
                int height = Integer.parseInt(binding.etVolumeCounterHeight.getText().toString());
                int result = beamsVolume(length, width, height);
                binding.tvVolumeCounterResultNumber.setText(String.valueOf(result));
                binding.tvVolumeCounterResult.setVisibility(View.VISIBLE);
                binding.tvVolumeCounterResultNumber.setVisibility(View.VISIBLE);
            }
            if (buttons[1]) {
                int length = Integer.parseInt(binding.etVolumeCounterLengthRadius.getText().toString());
                int width = Integer.parseInt(binding.etVolumeCounterWidthHeight.getText().toString());
                int height = Integer.parseInt(binding.etVolumeCounterHeight.getText().toString());
                int result = pyramidVolume(length, width, height);
                binding.tvVolumeCounterResultNumber.setText(String.valueOf(result));
                binding.tvVolumeCounterResult.setVisibility(View.VISIBLE);
                binding.tvVolumeCounterResultNumber.setVisibility(View.VISIBLE);
            }
            if (buttons[2]) {
                int radius = Integer.parseInt(binding.etVolumeCounterLengthRadius.getText().toString());
                int height = Integer.parseInt(binding.etVolumeCounterWidthHeight.getText().toString());
                int result = tubeArea(radius, height);
                binding.tvVolumeCounterResultNumber.setText(String.valueOf(result));
                binding.tvVolumeCounterResult.setVisibility(View.VISIBLE);
                binding.tvVolumeCounterResultNumber.setVisibility(View.VISIBLE);
            }
        };
    }

    private int beamsVolume(int length, int width, int height) {
        return length * width * height;
    }

    private int pyramidVolume(int length, int width, int height) {
        return length * width * height / 3;
    }

    private int tubeArea(int radius, int height) {
        return (int)Math.round(3.14 * radius * radius * height);
    }
}