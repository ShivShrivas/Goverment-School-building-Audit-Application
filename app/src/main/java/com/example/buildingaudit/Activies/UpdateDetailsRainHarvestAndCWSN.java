package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.buildingaudit.R;

import java.util.ArrayList;

public class UpdateDetailsRainHarvestAndCWSN extends AppCompatActivity {
Spinner spinnerCWSNWorkingStatus,spinnerCWSNAvailabilty,spinnerRainHavestingWorkStatus,spinnerRainharvestingAvailabilty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_rain_harvest_and_cwsn);
        spinnerRainharvestingAvailabilty=findViewById(R.id.spinnerRainharvestingAvailabilty);
        spinnerRainHavestingWorkStatus=findViewById(R.id.spinnerRainHavestingWorkStatus);
        spinnerCWSNAvailabilty=findViewById(R.id.spinnerCWSNAvailabilty);
        spinnerCWSNWorkingStatus=findViewById(R.id.spinnerCWSNWorkingStatus);

        ArrayList<String> arrayList1=new ArrayList<>();
        arrayList1.add("Yes");
        arrayList1.add("No");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList1);
        adapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerCWSNAvailabilty.setAdapter(adapter);
        spinnerRainharvestingAvailabilty.setAdapter(adapter);

        ArrayList<String> arrayListWorkingStatus=new ArrayList<>();
        arrayListWorkingStatus.add("Functional");
        arrayListWorkingStatus.add("Non Functional");
        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListWorkingStatus);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerCWSNWorkingStatus.setAdapter(arrayAdapter2);
        spinnerRainHavestingWorkStatus.setAdapter(arrayAdapter2);

    }
}