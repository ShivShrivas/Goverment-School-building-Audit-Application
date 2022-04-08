package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.buildingaudit.R;

import java.util.ArrayList;

public class UpdateDetailsFireFighting extends AppCompatActivity {
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    Spinner spinnerFireFightTraining,spinnerFireFightRenewalStatus,spinnerFireFightWorkingStatus,spinnerFireFightingInstallationYear,spinnerFireFightAvailabelty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_fire_fighting);
        getSupportActionBar().setTitle("Fire fighting");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinnerFireFightTraining=findViewById(R.id.spinnerFireFightTraining);
        spinnerFireFightRenewalStatus=findViewById(R.id.spinnerFireFightRenewalStatus);
        spinnerFireFightWorkingStatus=findViewById(R.id.spinnerFireFightWorkingStatus);
        spinnerFireFightingInstallationYear=findViewById(R.id.spinnerFireFightingInstallationYear);
        spinnerFireFightAvailabelty=findViewById(R.id.spinnerFireFightAvailabelty);


        ArrayList<String> arrayList1=new ArrayList<>();
        arrayList1.add("Yes");
        arrayList1.add("No");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList1);
        adapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerFireFightAvailabelty.setAdapter(adapter);


        ArrayList<String> arrayListInstallationYear=new ArrayList<>();
        arrayListInstallationYear.add("2015");
        arrayListInstallationYear.add("2016");
        arrayListInstallationYear.add("2017");
        arrayListInstallationYear.add("2018");
        arrayListInstallationYear.add("2019");
        arrayListInstallationYear.add("2020");
        arrayListInstallationYear.add("2021");
        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListInstallationYear);
        arrayAdapter1.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerFireFightingInstallationYear.setAdapter(arrayAdapter1);



        ArrayList<String> arrayListWorkingStatus=new ArrayList<>();
        arrayListWorkingStatus.add("Functional");
        arrayListWorkingStatus.add("Non Functional");

        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListWorkingStatus);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerFireFightWorkingStatus.setAdapter(arrayAdapter2);



        ArrayList<String> arrayListRenewalStatus=new ArrayList<>();
        arrayListRenewalStatus.add("Valid");
        arrayListRenewalStatus.add("Invalid");

        ArrayAdapter<String> arrayAdapter3=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListRenewalStatus);
        arrayAdapter3.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerFireFightRenewalStatus.setAdapter(arrayAdapter3);
        spinnerFireFightTraining.setAdapter(adapter);



    }
}