package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.buildingaudit.R;

import java.util.ArrayList;

public class UpdateDetailsGym extends AppCompatActivity {
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
Spinner spinnerGymAvailabelty,gymWorkingStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_gym);
        getSupportActionBar().setTitle("Gym");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinnerGymAvailabelty=findViewById(R.id.spinnerGymAvailabelty);
        gymWorkingStatus=findViewById(R.id.gymWorkingStatus);


        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerGymAvailabelty.setAdapter(arrayAdapter);


        ArrayList<String> arrayListLevellingStatus=new ArrayList<>();
        arrayListLevellingStatus.add("Functional");
        arrayListLevellingStatus.add("Non Functional");

        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListLevellingStatus);
        arrayAdapter1.setDropDownViewResource(R.layout.custom_text_spiiner);
        gymWorkingStatus.setAdapter(arrayAdapter1);
    }
}