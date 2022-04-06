package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.buildingaudit.R;

import java.util.ArrayList;

public class UpdateDetailsComputerlab extends AppCompatActivity {
Spinner spinnerComputeLabAvailabelty,spinnerInstallationYear,spinnerGrantUnderScheme,spinnerinternet,spinnerPowerBackup,spinnerFurniture,spinnerComputerOperator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_computerlab);

        spinnerComputeLabAvailabelty=findViewById(R.id.spinnerComputeLabAvailabelty);
        spinnerInstallationYear=findViewById(R.id.spinnerInstallationYear);
        spinnerGrantUnderScheme=findViewById(R.id.spinnerGrantUnderScheme);
        spinnerinternet=findViewById(R.id.spinnerinternet);
        spinnerPowerBackup=findViewById(R.id.spinnerPowerBackup);
        spinnerFurniture=findViewById(R.id.spinnerFurniture);
        spinnerComputerOperator=findViewById(R.id.spinnerComputerOperator);


        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerComputeLabAvailabelty.setAdapter(arrayAdapter);

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
        spinnerInstallationYear.setAdapter(arrayAdapter1);

        ArrayList<String> arrayListUnderScheme=new ArrayList<>();
        arrayListUnderScheme.add("RMSA");
        arrayListUnderScheme.add("CSr");
        arrayListUnderScheme.add("MSDP");
        arrayListUnderScheme.add("PM Jan");
        arrayListUnderScheme.add("Vikas");
        arrayListUnderScheme.add("Others");

        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListUnderScheme);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerGrantUnderScheme.setAdapter(arrayAdapter2);

        ArrayList<String> arrayListInternet =new ArrayList<>();
        arrayListInternet.add("yes");
        arrayListInternet.add("No");
        ArrayAdapter<String> arrayAdapter3=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListInternet);
        arrayAdapter3.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerinternet.setAdapter(arrayAdapter3);

        ArrayList<String> arrayListPowerbackup =new ArrayList<>();
        arrayListPowerbackup.add("Generator");
        arrayListPowerbackup.add("Invertor");
        arrayListPowerbackup.add("UPS");
        ArrayAdapter<String> arrayAdapter4=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListPowerbackup);
        arrayAdapter4.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerPowerBackup.setAdapter(arrayAdapter4);

        ArrayList<String> arrayListFurnitures=new ArrayList<>();
        arrayListFurnitures.add("Yes");
        arrayListFurnitures.add("No");
        ArrayAdapter<String> arrayAdapter5=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListFurnitures);
        arrayAdapter5.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerFurniture.setAdapter(arrayAdapter5);

        ArrayList<String> arrayListComputerOperator =new ArrayList<>();
        arrayListComputerOperator.add("Yes");
        arrayListComputerOperator.add("No");
        ArrayAdapter<String> arrayAdapter6=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListComputerOperator);
        arrayAdapter6.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerComputerOperator.setAdapter(arrayAdapter6);
    }
}