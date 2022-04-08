package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.buildingaudit.R;

import java.util.ArrayList;

public class UpdateDetailsDrinkingWater extends AppCompatActivity {
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
Spinner spinnerROInstallationScheme,spinnerROInstallationWokingStatus,
        spinnerROInstallationAvailabiltyDW,spinnerOverheadTankWorkStatsyDW,
        spinnerOverheadTankAvailabiltyDW,spinnerWaterSupplyWorkStatsyDW,
        spinnerWaterSupplyAvailabiltyDW,spinnerSubmersibleWorkStatsyDW,
        spinnerSubmersibleAvailabiltyDW,spinnerHandPumpWorkStatsyDW,
        spinnerHandPumpAvailabiltyDW,spinnerDeparmentDW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_drinking_water);
        getSupportActionBar().setTitle("Drinking Water");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinnerROInstallationScheme=findViewById(R.id.spinnerROInstallationScheme);
        spinnerROInstallationWokingStatus=findViewById(R.id.spinnerROInstallationWokingStatus);
        spinnerROInstallationAvailabiltyDW=findViewById(R.id.spinnerROInstallationAvailabiltyDW);
        spinnerOverheadTankWorkStatsyDW=findViewById(R.id.spinnerOverheadTankWorkStatsyDW);
        spinnerOverheadTankAvailabiltyDW=findViewById(R.id.spinnerOverheadTankAvailabiltyDW);
        spinnerWaterSupplyWorkStatsyDW=findViewById(R.id.spinnerWaterSupplyWorkStatsyDW);
        spinnerWaterSupplyAvailabiltyDW=findViewById(R.id.spinnerWaterSupplyAvailabiltyDW);
        spinnerSubmersibleWorkStatsyDW=findViewById(R.id.spinnerSubmersibleWorkStatsyDW);
        spinnerSubmersibleAvailabiltyDW=findViewById(R.id.spinnerSubmersibleAvailabiltyDW);
        spinnerHandPumpWorkStatsyDW=findViewById(R.id.spinnerHandPumpWorkStatsyDW);
        spinnerHandPumpAvailabiltyDW=findViewById(R.id.spinnerHandPumpAvailabiltyDW);
        spinnerDeparmentDW=findViewById(R.id.spinnerDeparmentDW);


        ArrayList<String> arrayList1=new ArrayList<>();
        arrayList1.add("Yes");
        arrayList1.add("No");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList1);
        adapter.setDropDownViewResource(R.layout.custom_text_spiiner);


        spinnerSubmersibleAvailabiltyDW.setAdapter(adapter);
        spinnerHandPumpAvailabiltyDW.setAdapter(adapter);
        spinnerROInstallationAvailabiltyDW.setAdapter(adapter);
        spinnerOverheadTankAvailabiltyDW.setAdapter(adapter);
        spinnerWaterSupplyAvailabiltyDW.setAdapter(adapter);

        ArrayList<String> arrayListWorkingStatus=new ArrayList<>();
        arrayListWorkingStatus.add("Functional");
        arrayListWorkingStatus.add("Non Functional");

        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListWorkingStatus);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerHandPumpWorkStatsyDW.setAdapter(arrayAdapter2);
        spinnerSubmersibleWorkStatsyDW.setAdapter(arrayAdapter2);
        spinnerWaterSupplyWorkStatsyDW.setAdapter(arrayAdapter2);
        spinnerOverheadTankWorkStatsyDW.setAdapter(arrayAdapter2);
        spinnerROInstallationWokingStatus.setAdapter(arrayAdapter2);


        ArrayList<String> arrayListUnderScheme=new ArrayList<>();
        arrayListUnderScheme.add("RMSA");
        arrayListUnderScheme.add("CSr");
        arrayListUnderScheme.add("MSDP");
        arrayListUnderScheme.add("PM Jan");
        arrayListUnderScheme.add("Vikas");
        arrayListUnderScheme.add("Others");

        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListUnderScheme);
        arrayAdapter1.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerROInstallationScheme.setAdapter(arrayAdapter1);


        ArrayList<String> arrayListDeparment=new ArrayList<>();
        arrayListDeparment.add("Madhyamik Secondary Education");

        ArrayAdapter<String> arrayAdapter4=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListDeparment);
        arrayAdapter4.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerDeparmentDW.setAdapter(arrayAdapter4);



    }
}