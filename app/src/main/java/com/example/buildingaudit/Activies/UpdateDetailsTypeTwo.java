package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.buildingaudit.R;

import java.util.ArrayList;

public class UpdateDetailsTypeTwo extends AppCompatActivity {
Spinner spinnerRoomAvailabel,spinnerRoomStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_type_two);
        getSupportActionBar().setTitle("Staff Room");

        spinnerRoomAvailabel=findViewById(R.id.spinnerRoomAvailabel);
        spinnerRoomStatus=findViewById(R.id.spinnerRoomStatus);
        ArrayList<String> arrayListSpinner = new ArrayList<>();

        arrayListSpinner.add("Yes");
        arrayListSpinner.add("No");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListSpinner);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerRoomAvailabel.setAdapter(arrayAdapter);
        ArrayList<String> arrayListSpinner2 = new ArrayList<>();
        arrayListSpinner2.add("Good Condition");
        arrayListSpinner2.add("Minor Repairing");
        arrayListSpinner2.add("Major repairing");

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListSpinner2);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);

        spinnerRoomStatus.setAdapter(arrayAdapter2);



    }
}