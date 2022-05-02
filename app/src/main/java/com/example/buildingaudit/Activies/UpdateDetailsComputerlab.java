package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.buildingaudit.Adapters.ImageAdapter4;
import com.example.buildingaudit.ApplicationController;
import com.example.buildingaudit.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class UpdateDetailsComputerlab extends AppCompatActivity {
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();

        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onStop() {
        super.onStop();

        adapter.notifyDataSetChanged();

    }
    public ArrayList<Bitmap> arrayListImages1 = new ArrayList<>();
    ImageAdapter4 adapter;
Spinner spinnerComputeLabAvailabelty,spinnerInstallationYear,spinnerGrantUnderScheme,spinnerinternet,spinnerPowerBackup,spinnerFurniture,spinnerComputerOperator;
    ImageView ComputerLabImageUploadBtn;
    RecyclerView recyclerViewComputerLab;
    TextView userName,schoolAddress,schoolName;
    ApplicationController applicationController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_computerlab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        spinnerComputeLabAvailabelty=findViewById(R.id.spinnerComputeLabAvailabelty);
        ComputerLabImageUploadBtn=findViewById(R.id.ComputerLabImageUploadBtn);
        recyclerViewComputerLab=findViewById(R.id.recyclerViewComputerLab);
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


        ComputerLabImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withActivity(UpdateDetailsComputerlab.this)
                        .withPermission(Manifest.permission.CAMERA)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                // permission is granted, open the camera

                                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 7);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                // check for permanent denial of permission
                                if (response.isPermanentlyDenied()) {

                                    // navigate user to app settings
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                                    intent.setData(uri);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });
        recyclerViewComputerLab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new ImageAdapter4(this, arrayListImages1);
        recyclerViewComputerLab.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK ) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            arrayListImages1.add(bitmap);


        }
    }
}