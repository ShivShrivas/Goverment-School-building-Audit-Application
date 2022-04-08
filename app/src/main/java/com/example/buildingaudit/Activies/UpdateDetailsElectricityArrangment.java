package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.buildingaudit.R;

import java.util.ArrayList;

public class UpdateDetailsElectricityArrangment extends AppCompatActivity {
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }Spinner spinnerElectricStatus,spinnerSource,spinnerInternalElectrification,spinnerElectricityAvailabelty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_electricity_arrangment);
getSupportActionBar().setTitle("Electricity Arrangement");
getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinnerElectricStatus=findViewById(R.id.spinnerElectricStatus);
        spinnerSource=findViewById(R.id.spinnerSource);
        spinnerInternalElectrification=findViewById(R.id.spinnerInternalElectrification);
        spinnerElectricityAvailabelty=findViewById(R.id.spinnerElectricityAvailabelty);

        ArrayList<String> arrayList1=new ArrayList<>();
        arrayList1.add("Yes");
        arrayList1.add("No");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList1);
        adapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerElectricityAvailabelty.setAdapter(adapter);
        spinnerInternalElectrification.setAdapter(adapter);

        ArrayList<String> arrayListPowerbackup =new ArrayList<>();
        arrayListPowerbackup.add("Generator");
        arrayListPowerbackup.add("Invertor");
        ArrayAdapter<String> arrayAdapter4=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListPowerbackup);
        arrayAdapter4.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerSource.setAdapter(arrayAdapter4);

        ArrayList<String> arrayListWorkingStatus=new ArrayList<>();
        arrayListWorkingStatus.add("Functional");
        arrayListWorkingStatus.add("Non Functional");

        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListWorkingStatus);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerElectricStatus.setAdapter(arrayAdapter2);

    }
}