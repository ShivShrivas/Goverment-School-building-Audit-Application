package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.buildingaudit.R;

import java.util.ArrayList;

public class UpdateDetailsPlayground extends AppCompatActivity {
Spinner spinnerLevelingStatus,spinnerRoomAvailabelty,spinnertrackAvalabiltyStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_type_five);
        spinnerLevelingStatus=findViewById(R.id.spinnerLevellingStatus);
        spinnerRoomAvailabelty=findViewById(R.id.spinnerPlaygroundAvailabelty);
        spinnertrackAvalabiltyStatus=findViewById(R.id.spinnertrackAvalabiltyStatus);

        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerRoomAvailabelty.setAdapter(arrayAdapter);
        spinnertrackAvalabiltyStatus.setAdapter(arrayAdapter);


        ArrayList<String> arrayListLevellingStatus=new ArrayList<>();
        arrayListLevellingStatus.add("Good");
        arrayListLevellingStatus.add("Average");
        arrayListLevellingStatus.add("Bad");

        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListLevellingStatus);
        arrayAdapter1.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerLevelingStatus.setAdapter(arrayAdapter1);



    }

}