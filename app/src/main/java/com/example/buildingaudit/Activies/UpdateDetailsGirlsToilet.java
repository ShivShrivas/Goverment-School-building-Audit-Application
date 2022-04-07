package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.buildingaudit.R;

import java.util.ArrayList;

public class UpdateDetailsGirlsToilet extends AppCompatActivity {
Spinner spinnerGirlsIncinerator,spinnerGirlsDustbin,spinnerGirlsDoorFacility,spinnerCWSNGirlstoiletAvalabilty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_girls_toilet);
        spinnerGirlsIncinerator=findViewById(R.id.spinnerGirlsIncinerator);
        spinnerGirlsDustbin=findViewById(R.id.spinnerGirlsDustbin);
        spinnerGirlsDoorFacility=findViewById(R.id.spinnerGirlsDoorFacility);
        spinnerCWSNGirlstoiletAvalabilty=findViewById(R.id.spinnerCWSNGirlstoiletAvalabilty);

        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);


        spinnerGirlsIncinerator.setAdapter(arrayAdapter);
       spinnerGirlsDustbin.setAdapter(arrayAdapter);
        spinnerGirlsDoorFacility.setAdapter(arrayAdapter);
        spinnerCWSNGirlstoiletAvalabilty.setAdapter(arrayAdapter);

    }
}