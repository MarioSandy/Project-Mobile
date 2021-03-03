package com.example.mentolapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import com.example.mentolapp2.databinding.ActivityMainBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnSave.setOnClickListener(Save());
        binding.btnSubmit.setOnClickListener(Submit());
        binding.btnTestDrive.setOnClickListener(TestDrive());

    }

    private View.OnClickListener Save(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kendaraan = binding.etTipeKendaraan.getText().toString();

                if(kendaraan.equalsIgnoreCase("Mobil")){
                    binding.llInput.setVisibility(View.VISIBLE);
                    binding.tvType.setText("Type [SUV/Supercar/Minivan]:");
                    binding.tvEntertainmentHelm.setText("Entertainment System amount:");
                }
                else if(kendaraan.equalsIgnoreCase("Motor")){
                    binding.llInput.setVisibility(View.VISIBLE);
                    binding.tvType.setText("Type [Automatic/Manual]:");
                    binding.tvEntertainmentHelm.setText("Jumlah Helm [>=1]");
                }
                else{
                    binding.etTipeKendaraan.setError("Please choose Mobil or Motor");
                    binding.llInput.setVisibility(View.GONE);
                    binding.llTestDrive.setVisibility(View.GONE);
                    binding.llPrice.setVisibility(View.GONE);
                    binding.tvPrice.setVisibility(View.GONE);
                    Reset();
                }

            }
        };
    }

    private View.OnClickListener Submit(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kendaraan = binding.etTipeKendaraan.getText().toString();
                String brand = binding.etBrand.getText().toString();
                String name = binding.etName.getText().toString();
                String license = binding.etLicense.getText().toString();
                String[] licenseSplit = new String[3] ;
                int topSpeed;
                int gasCap;
                int wheel;
                try{
                    topSpeed = Integer.parseInt(binding.etTopSpeed.getText().toString());
                }
                catch (Exception e){
                    topSpeed = 0;
                }
                try{
                    gasCap = Integer.parseInt(binding.etGasCapacity.getText().toString());
                }
                catch (Exception e){
                    gasCap = 0;
                }
                try{
                    wheel = Integer.parseInt(binding.etWheel.getText().toString());
                }
                catch (Exception e){
                    wheel = 0;
                }
                String type = binding.etType.getText().toString();
                int helmOrEntertainment;
                try{
                    helmOrEntertainment = Integer.parseInt(binding.etEntertainmentHelm.getText().toString());
                }
                catch(Exception e){
                    helmOrEntertainment = 0;
                }

                if(kendaraan.equalsIgnoreCase("Mobil")){
                    if(MobilChecking(brand, name, license, licenseSplit, topSpeed, gasCap, wheel, type, helmOrEntertainment)){
                        binding.llTestDrive.setVisibility(View.VISIBLE);
                        binding.tvDescription.setText("Car " + name);
                        Description(kendaraan, name, brand, license, type, gasCap, topSpeed, wheel, helmOrEntertainment);
                    }
                    else{
                        binding.llTestDrive.setVisibility(View.GONE);
                    }

                }
                else if(kendaraan.equalsIgnoreCase("Motor")){
                    if(MotorChecking(brand, name, license, licenseSplit, topSpeed, gasCap, wheel, type, helmOrEntertainment)){
                        binding.llTestDrive.setVisibility(View.VISIBLE);
                        binding.tvDescription.setText("Motorcycle " + name);
                        binding.llPrice.setVisibility(View.VISIBLE);
                        Description(kendaraan, name, brand, license, type, gasCap, topSpeed, wheel, helmOrEntertainment);
                    }
                    else{
                        binding.llTestDrive.setVisibility(View.GONE);
                        binding.llPrice.setVisibility(View.GONE);
                        binding.etPrice.setText("");
                        binding.tvPrice.setVisibility(View.GONE);
                    }
                }
                else{
                    binding.etTipeKendaraan.setError("Please choose Mobil or Motor");
                    Reset();
                }
            }
        };
    }

    private boolean MobilChecking(String brand, String name, String license, String[] licenseSplit, int topSpeed, int gasCap, int wheel, String type, int helmOrEntertainment){

        if(brand.length() < 5){
            binding.etBrand.setError("Character must be >= 5");
            return false;
        }
        else if(name.length() < 5){
            binding.etName.setError("Character must be >= 5");
            return false;
        }
        else if(!(licenseChecking(license, licenseSplit))){
            binding.etLicense.setError("License must use 'A 0000 AAA' format, A : Alphabet[A-Z] Min 1 Max Total A, 0 : Number[0-9] Min 1 Max Total 0");
            return false;
        }
        else if(topSpeed < 100 || topSpeed > 250){
            binding.etTopSpeed.setError("Top Speed must be >= 100 or <= 250");
            return false;
        }
        else if(gasCap < 30 || gasCap > 60){
            binding.etGasCapacity.setError("Gas Capacity must be >= 30 or <= 60");
            return false;
        }
        else if(wheel < 4 || wheel > 6){
            binding.etWheel.setError("Wheel must be >= 4 or <= 6");
            return false;
        }
        else if(!(type.equals("SUV") || type.equals("Supercar") || type.equals("Minivan"))){
            binding.etType.setError("Please choose SUV/Supercar/Minivan [Case Sensitive]");
            return false;
        }
        else if(helmOrEntertainment < 1){
            binding.etEntertainmentHelm.setError("Entertainment System must be >= 1");
            return false;
        }
        return true;
    }

    private boolean MotorChecking(String brand, String name, String license, String[] licenseSplit, int topSpeed, int gasCap, int wheel, String type, int helmOrEntertainment){

        if(brand.length() < 5){
            binding.etBrand.setError("Character must be >= 5");
            return false;
        }
        else if(name.length() < 5){
            binding.etName.setError("Character must be >= 5");
            return false;
        }
        else if(!(licenseChecking(license, licenseSplit))){
            binding.etLicense.setError("License must use 'A 0000 AAA' format, A : Alphabet[A-Z] Min 1 Max Total A, 0 : Number[0-9] Min 1 Max Total 0");
            return false;
        }
        else if(topSpeed < 100 || topSpeed > 250){
            binding.etTopSpeed.setError("Top Speed must be >= 100 or <= 250");
            return false;
        }
        else if(gasCap < 30 || gasCap > 60){
            binding.etGasCapacity.setError("Gas Capacity must be >= 30 or <= 60");
            return false;
        }
        else if(wheel < 2 || wheel > 3){
            binding.etWheel.setError("Wheel must be >= 2 or <= 3");
            return false;
        }
        else if(!(type.equals("Automatic") || type.equals("Manual"))){
            binding.etType.setError("Please choose Automatic/Manual [Case Sensitive]");
            return false;
        }
        else if(helmOrEntertainment < 1){
            binding.etEntertainmentHelm.setError("Helmet must be >= 1");
            return false;
        }
        return true;
    }

    private boolean licenseChecking(String license, String[] licenseSplit){

        try{
            String[] temp = license.split(" ");
            for(int h = 0; h < temp.length; h++){
                licenseSplit[h] = temp[h];
            }
        }
        catch(Exception e){
            return false;
        }
        if(license.length() < 5){
            return false;
        }
        else if (licenseSplit.length > 0){
            for(int i = 0; i < licenseSplit.length; i++){
                if(licenseSplit[0].length() == 1){
                    if(licenseSplit[0].charAt(0) < 65 || licenseSplit[0].charAt(0) > 90){
                        return false;
                    }
                }
                else{
                        return false;
                }
                if(licenseSplit[1].length() < 1 || licenseSplit[1].length() > 4){
                        return false;
                }
                else{
                    for(int j = 0; j < licenseSplit[1].length(); j++ ){
                        if(licenseSplit[1].charAt(j) < 48 || licenseSplit[1].charAt(j) > 57){
                                return false;
                        }
                    }
                }
                if(licenseSplit[2].length() < 1 || licenseSplit[2].length() > 3){
                        return false;
                }
                else{
                    for(int k = 0; k < licenseSplit[2].length(); k++){
                        if(licenseSplit[2].charAt(k) < 65 || licenseSplit[2].charAt(k) > 90){
                                return false;
                        }
                    }
                }
            }
        }
        return true;
    };

    private void Reset(){
        binding.llInput.setVisibility(View.GONE);
        binding.etBrand.setText("");
        binding.etName.setText("");
        binding.etLicense.setText("");
        binding.etTopSpeed.setText("");
        binding.etGasCapacity.setText("");
        binding.etWheel.setText("");
        binding.etType.setText("");
        binding.etEntertainmentHelm.setText("");
        binding.llTestDrive.setVisibility(View.GONE);
        binding.etPrice.setText("");
    }
    private View.OnClickListener TestDrive(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = binding.tvTypeDescription.getText().toString();
                String[] typeSplit = new String[2];
                typeSplit = type.split(": ");
                String helmOrEntertainment = binding.tvEntertainmentHelmDescription.getText().toString();
                String[] helmOrEntertainmentSplit = new String[2];
                helmOrEntertainmentSplit = helmOrEntertainment.split(": ");
                String name = binding.tvName.getText().toString();
                String[] nameSplit = new String[2];
                nameSplit = name.split(": ");

                if(typeSplit[1].equals("SUV") || typeSplit[1].equals("Minivan")){
                    Toast.makeText(getApplicationContext(),"Turning on entertainment...",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"Entertainment System Quantity: " + helmOrEntertainmentSplit[1],Toast.LENGTH_SHORT).show();
                }
                else if(typeSplit[1].equals("Supercar")){
                    Toast.makeText(getApplicationContext(),"Turning on entertainment...",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"Entertainment System Quantity: " + helmOrEntertainmentSplit[1],Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"Boosting!" ,Toast.LENGTH_SHORT).show();

                }
                else if(typeSplit[1].equals("Automatic") || typeSplit[1].equals("Manual")){
                    Toast.makeText(getApplicationContext(),nameSplit[1] + " is standing!",Toast.LENGTH_SHORT).show();
                    if(binding.etPrice.length() > 0){
                        int price = Integer.parseInt(binding.etPrice.getText().toString());
                        Locale localeID = new Locale("in","ID");
                        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                        binding.tvPrice.setText(formatRupiah.format(price));
                        binding.tvPrice.setVisibility(View.VISIBLE);
                    }
                }
            }
        };
    }
    private void Description(String kendaraan, String name, String brand, String license, String type, int gasCap, int topSpeed, int wheel, int helmOrEntertainment){
        if(kendaraan.equalsIgnoreCase("Mobil")){
            binding.tvBrand.setText("Brand: " + brand);
            binding.tvName.setText("Name: " + name);
            binding.tvLicensePlate.setText("License Plate: " + license);
            binding.tvTypeDescription.setText("Type: " + type);
            binding.tvGasCapacity.setText("Gas Capacity: " + gasCap);
            binding.tvTopSpeed.setText("Top Speed: " + topSpeed);
            binding.tvWheel.setText("Wheel(s): " + wheel);
            binding.tvEntertainmentHelmDescription.setText("Entertainment System: " + helmOrEntertainment);
        }
        else if(kendaraan.equalsIgnoreCase("Motor")){
            binding.tvBrand.setText("Brand: " + brand);
            binding.tvName.setText("Name: " + name);
            binding.tvLicensePlate.setText("License Plate: " + license);
            binding.tvTypeDescription.setText("Type: " + type);
            binding.tvGasCapacity.setText("Gas Capacity: " + gasCap);
            binding.tvTopSpeed.setText("Top Speed: " + topSpeed);
            binding.tvWheel.setText("Wheel(s): " + wheel);
            binding.tvEntertainmentHelmDescription.setText("Helmet: " + helmOrEntertainment);
        }
    }
}