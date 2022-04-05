package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.buildingaudit.R;

import java.util.ArrayList;

public class UpdateDetailsTypeFour extends AppCompatActivity {
Spinner spinnerPhysicalStatus,spinnerNewsPaperAndMzin,spinnerGrantUnderScheme,spinnerRoomAvailabelty,spinnerWorkingStatus,spinnerReadingCorner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_type_four);
        spinnerPhysicalStatus=findViewById(R.id.spinnerPhysicalStatus);
        spinnerReadingCorner=findViewById(R.id.spinnerReadingCorner);
        spinnerNewsPaperAndMzin=findViewById(R.id.spinnerNewsPaperAndMzin);
        spinnerRoomAvailabelty=findViewById(R.id.spinnerRoomAvailabelty);
        spinnerWorkingStatus=findViewById(R.id.spinnerWorkingStatus);
        spinnerGrantUnderScheme=findViewById(R.id.spinnerGrantUnderScheme);

        ArrayList<String> arrayListSpinner = new ArrayList<>();

        arrayListSpinner.add("Good Condition");
        arrayListSpinner.add("Minor repairing requored");
        arrayListSpinner.add("major repairing required");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListSpinner);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerPhysicalStatus.setAdapter(arrayAdapter);

        ArrayList<String> arrayListspinnerRoomAvailabelty =new ArrayList<>();
        arrayListspinnerRoomAvailabelty.add("Yes");
        arrayListspinnerRoomAvailabelty.add("No");
        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,arrayListspinnerRoomAvailabelty);
        arrayAdapter1.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerRoomAvailabelty.setAdapter(arrayAdapter1);
        ArrayList<String> arrayListWorkingStatus =new ArrayList<>();
        arrayListWorkingStatus.add("Functional");
        arrayListWorkingStatus.add("Non-Functional");

        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListWorkingStatus);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerWorkingStatus.setAdapter(arrayAdapter2);

        ArrayList<String> arrayListReadingCorner=new ArrayList<>();
        arrayListReadingCorner.add("Yes");
        arrayListReadingCorner.add("No");
        ArrayAdapter<String> arrayAdapter3 =new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListReadingCorner);
        arrayAdapter3.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerReadingCorner.setAdapter(arrayAdapter3);

        ArrayList<String> arrayListnewsMgzin=new ArrayList<>();
        arrayListnewsMgzin.add("Yes");
        arrayListnewsMgzin.add("No");
        ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListnewsMgzin);
        arrayAdapter4.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerNewsPaperAndMzin.setAdapter(arrayAdapter4);

        ArrayList<String> arrayListGrantUnderScheme=new ArrayList<>();
        arrayListGrantUnderScheme.add("RMSA");
        arrayListGrantUnderScheme.add("CSR");
        arrayListGrantUnderScheme.add("MSDP");
        arrayListGrantUnderScheme.add("PM Jan Vikas");
        arrayListGrantUnderScheme.add("Others");

        ArrayAdapter<String> arrayAdapter5=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListGrantUnderScheme);
        arrayAdapter5.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerGrantUnderScheme.setAdapter(arrayAdapter5);
    }
}