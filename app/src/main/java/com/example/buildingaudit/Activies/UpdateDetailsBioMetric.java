package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.buildingaudit.R;

import java.util.ArrayList;

public class UpdateDetailsBioMetric extends AppCompatActivity {
Spinner spinnerCCTVWorkingStatus,spinnerCCTVInstallationYear,
        spinnerCCTVAvailabelty,spinneruserbiometricStudent,
        spinnerBiometricWorkingStatus,spinneruserbiometricStaff,spinnerBioMetricInstallationYear,
        spinnerBioMetricMachineAvailabelty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_bio_metric);
        spinnerCCTVWorkingStatus=findViewById(R.id.spinnerCCTVWorkingStatus);
        spinnerCCTVInstallationYear=findViewById(R.id.spinnerCCTVInstallationYear);
        spinnerCCTVAvailabelty=findViewById(R.id.spinnerCCTVAvailabelty);
        spinneruserbiometricStudent=findViewById(R.id.spinneruserbiometricStudent);
        spinnerBiometricWorkingStatus=findViewById(R.id.spinnerBiometricWorkingStatus);
        spinneruserbiometricStaff=findViewById(R.id.spinneruserbiometricStaff);
        spinnerBioMetricInstallationYear=findViewById(R.id.spinnerBioMetricInstallationYear);
        spinnerBioMetricMachineAvailabelty=findViewById(R.id.spinnerBioMetricMachineAvailabelty);

        ArrayList<String> arrayList1=new ArrayList<>();
        arrayList1.add("Yes");
        arrayList1.add("No");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList1);
        adapter.setDropDownViewResource(R.layout.custom_text_spiiner);


        spinnerCCTVAvailabelty.setAdapter(adapter);
       spinneruserbiometricStudent.setAdapter(adapter);

        spinneruserbiometricStaff.setAdapter(adapter);

       spinnerBioMetricMachineAvailabelty.setAdapter(adapter);



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
        spinnerCCTVInstallationYear.setAdapter(arrayAdapter1);
        spinnerBioMetricInstallationYear.setAdapter(arrayAdapter1);



        ArrayList<String> arrayListWorkingStatus=new ArrayList<>();
        arrayListWorkingStatus.add("Functional");
        arrayListWorkingStatus.add("Non Functional");

        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListWorkingStatus);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerBiometricWorkingStatus.setAdapter(arrayAdapter2);
        spinnerBiometricWorkingStatus.setAdapter(arrayAdapter2);

    }
}