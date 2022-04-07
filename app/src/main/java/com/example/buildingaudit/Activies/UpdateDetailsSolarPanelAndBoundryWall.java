package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.buildingaudit.R;

import java.util.ArrayList;

public class UpdateDetailsSolarPanelAndBoundryWall extends AppCompatActivity {
Spinner spinnerWallCondition,spinnerWhiteWash,spinnerTypeBoundaryWall,spinnerBoundaryWallAvail,
        spinnerSolarPaneltWorkingStatus,spinnerSolraPanelInstallationYear,spinnerSolarPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_solar_panel_and_boundry_wall);

        spinnerWallCondition=findViewById(R.id.spinnerWallCondition);
        spinnerWhiteWash=findViewById(R.id.spinnerWhiteWash);
        spinnerTypeBoundaryWall=findViewById(R.id.spinnerTypeBoundaryWall);
        spinnerBoundaryWallAvail=findViewById(R.id.spinnerBoundaryWallAvail);
        spinnerSolarPaneltWorkingStatus=findViewById(R.id.spinnerSolarPaneltWorkingStatus);
        spinnerSolraPanelInstallationYear=findViewById(R.id.spinnerSolraPanelInstallationYear);
        spinnerSolarPanel=findViewById(R.id.spinnerSolarPanel);


        ArrayList<String> arrayList1=new ArrayList<>();
        arrayList1.add("Yes");
        arrayList1.add("No");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList1);
        adapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerSolarPanel.setAdapter(adapter);
        spinnerBoundaryWallAvail.setAdapter(adapter);
        spinnerWhiteWash.setAdapter(adapter);


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
        spinnerSolraPanelInstallationYear.setAdapter(arrayAdapter1);


        ArrayList<String> arrayListWorkingStatus=new ArrayList<>();
        arrayListWorkingStatus.add("Functional");
        arrayListWorkingStatus.add("Non Functional");
        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListWorkingStatus);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerSolarPaneltWorkingStatus.setAdapter(arrayAdapter2);
        ArrayList<String> arrayListSpinner = new ArrayList<>();

        arrayListSpinner.add("Good Condition");
        arrayListSpinner.add("Minor repairing requored");
        arrayListSpinner.add("major repairing required");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListSpinner);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerWallCondition.setAdapter(arrayAdapter);

        ArrayList<String> arrayListWallType = new ArrayList<>();

        arrayListWallType.add("Pucca");
        arrayListWallType.add("Pucca but broken");
        arrayListWallType.add("Barbed Wire");
        arrayListWallType.add("Fencing");
        arrayListWallType.add("Hedges");
        arrayListWallType.add("No Boundary Walls");
        arrayListWallType.add("Partial");
        arrayListWallType.add("Under Construction");
        arrayListWallType.add("Others");


        ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListWallType);
        arrayAdapter4.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerTypeBoundaryWall.setAdapter(arrayAdapter4);

    }
}