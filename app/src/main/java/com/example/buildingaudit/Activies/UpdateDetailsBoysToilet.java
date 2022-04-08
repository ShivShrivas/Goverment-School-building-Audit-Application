package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.buildingaudit.R;

import java.util.ArrayList;

public class UpdateDetailsBoysToilet extends AppCompatActivity {
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
Spinner spinnerCWSNBoysAvailability,spinnerBoysDoors,spinnerBoysDustbin,spinnerBoysIncinerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_boys_toilet);
        spinnerCWSNBoysAvailability=findViewById(R.id.spinnerCWSNBoysAvailability);
        spinnerBoysDoors=findViewById(R.id.spinnerBoysDoors);
        spinnerBoysDustbin=findViewById(R.id.spinnerBoysDustbin);
        getSupportActionBar().setTitle("Boys Toilet");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);

        spinnerCWSNBoysAvailability.setAdapter(arrayAdapter);
                spinnerBoysDoors.setAdapter(arrayAdapter);
        spinnerBoysDustbin.setAdapter(arrayAdapter);
                spinnerBoysIncinerator.setAdapter(arrayAdapter);
    }
}