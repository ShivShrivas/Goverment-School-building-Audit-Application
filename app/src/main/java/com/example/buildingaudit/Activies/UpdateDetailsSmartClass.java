package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.buildingaudit.R;

import java.util.ArrayList;

public class UpdateDetailsSmartClass extends AppCompatActivity {
Spinner spinnerInstallationYearSmartClass,spinnerUnderSchemeSmartClass,spinnerWorkingStatusSmartClass,
        spinnerTeacherAvailbilitySmartClass,spinnerProjectorSmartClass,spinnerLearningContentSmartClass,
        spinnerDigitalBoardSmartClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_smart_class);
        spinnerInstallationYearSmartClass=findViewById(R.id.spinnerInstallationYearSmartClass);
        spinnerUnderSchemeSmartClass=findViewById(R.id.spinnerUnderSchemeSmartClass);
        spinnerWorkingStatusSmartClass=findViewById(R.id.spinnerWorkingStatusSmartClass);
        spinnerTeacherAvailbilitySmartClass=findViewById(R.id.spinnerTeacherAvailbilitySmartClass);
        spinnerProjectorSmartClass=findViewById(R.id.spinnerProjectorSmartClass);
        spinnerLearningContentSmartClass=findViewById(R.id.spinnerLearningContentSmartClass);
        spinnerDigitalBoardSmartClass=findViewById(R.id.spinnerDigitalBoardSmartClass);

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
        spinnerInstallationYearSmartClass.setAdapter(arrayAdapter1);


        ArrayList<String> arrayListUnderScheme=new ArrayList<>();
        arrayListUnderScheme.add("RMSA");
        arrayListUnderScheme.add("CSr");
        arrayListUnderScheme.add("MSDP");
        arrayListUnderScheme.add("PM Jan");
        arrayListUnderScheme.add("Vikas");
        arrayListUnderScheme.add("Others");

        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListUnderScheme);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerUnderSchemeSmartClass.setAdapter(arrayAdapter2);



        ArrayList<String> arrayListLevellingStatus=new ArrayList<>();
        arrayListLevellingStatus.add("Functional");
        arrayListLevellingStatus.add("Non Functional");

        ArrayAdapter<String> arrayAdapter3=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListLevellingStatus);
        arrayAdapter3.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerWorkingStatusSmartClass.setAdapter(arrayAdapter3);


        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerTeacherAvailbilitySmartClass.setAdapter(arrayAdapter);

        ArrayList<String> arrayListProjector=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        ArrayAdapter<String> arrayAdapter4=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListProjector);
        arrayAdapter4.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerProjectorSmartClass.setAdapter(arrayAdapter4);

        ArrayList<String> arrayListLearningContent=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        ArrayAdapter<String> arrayAdapter5=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListLearningContent);
        arrayAdapter5.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerLearningContentSmartClass.setAdapter(arrayAdapter5);

        ArrayList<String> arrayListDigitalBoard=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        ArrayAdapter<String> arrayAdapter6=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListDigitalBoard);
        arrayAdapter6.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerLearningContentSmartClass.setAdapter(arrayAdapter6);


    }
}