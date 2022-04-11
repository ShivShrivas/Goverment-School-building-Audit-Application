package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
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

import com.example.buildingaudit.Adapters.ImageAdapter3;
import com.example.buildingaudit.Adapters.ImageAdapter4;
import com.example.buildingaudit.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class UpdateDetailsBioMetric extends AppCompatActivity {
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public ArrayList<Bitmap> arrayListImages1 = new ArrayList<>();
    public ArrayList<Bitmap> arrayListImages2 = new ArrayList<>();
    int btnType;
    ImageAdapter4 adapter1;
    ImageAdapter3 adapter2;
Spinner spinnerCCTVWorkingStatus,spinnerCCTVInstallationYear,
        spinnerCCTVAvailabelty,spinneruserbiometricStudent,
        spinnerBiometricWorkingStatus,spinneruserbiometricStaff,spinnerBioMetricInstallationYear,
        spinnerBioMetricMachineAvailabelty;
    ImageView CCTVImageUploadBtn,bioMetricImageUploadBtn;
    RecyclerView recyclerViewBioMetric,recyclerViewCCTV;

    @Override
    protected void onStart() {
        super.onStart();
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_bio_metric);
        getSupportActionBar().setTitle("Biometric and CCTV");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerCCTVWorkingStatus=findViewById(R.id.spinnerCCTVWorkingStatus);
        spinnerCCTVInstallationYear=findViewById(R.id.spinnerCCTVInstallationYear);
        spinnerCCTVAvailabelty=findViewById(R.id.spinnerCCTVAvailabelty);
        CCTVImageUploadBtn=findViewById(R.id.CCTVImageUploadBtn);
        bioMetricImageUploadBtn=findViewById(R.id.bioMetricImageUploadBtn);
        spinneruserbiometricStudent=findViewById(R.id.spinneruserbiometricStudent);
        spinnerBiometricWorkingStatus=findViewById(R.id.spinnerBiometricWorkingStatus);
        spinneruserbiometricStaff=findViewById(R.id.spinneruserbiometricStaff);
        spinnerBioMetricInstallationYear=findViewById(R.id.spinnerBioMetricInstallationYear);
        spinnerBioMetricMachineAvailabelty=findViewById(R.id.spinnerBioMetricMachineAvailabelty);
        recyclerViewCCTV=findViewById(R.id.recyclerViewCCTV);
        recyclerViewBioMetric=findViewById(R.id.recyclerViewBioMetric);

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


        CCTVImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnType=2;
                Dexter.withActivity(UpdateDetailsBioMetric.this)
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
        bioMetricImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnType=1;
                Dexter.withActivity(UpdateDetailsBioMetric.this)
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
        recyclerViewBioMetric.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter1 = new ImageAdapter4(this, arrayListImages1);
        recyclerViewBioMetric.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();
        recyclerViewCCTV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter2= new ImageAdapter3(this, arrayListImages2);
        recyclerViewCCTV.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK ) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            if (btnType==1){
                arrayListImages1.add(bitmap);
            }else  arrayListImages2.add(bitmap);


        }
    }
}