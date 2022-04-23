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

    int btnType;
    ImageAdapter4 adapter1;

Spinner spinneruserbiometricStudent,
        spinnerBiometricWorkingStatus,spinneruserbiometricStaff,spinnerBioMetricInstallationYear,
        spinnerBioMetricMachineAvailabelty;
    ImageView bioMetricImageUploadBtn;
    RecyclerView recyclerViewBioMetric;

    @Override
    protected void onStart() {
        super.onStart();
        adapter1.notifyDataSetChanged();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter1.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_bio_metric);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        bioMetricImageUploadBtn=findViewById(R.id.bioMetricImageUploadBtn);
        spinneruserbiometricStudent=findViewById(R.id.spinneruserbiometricStudent);
        spinnerBiometricWorkingStatus=findViewById(R.id.spinnerBiometricWorkingStatus);
        spinneruserbiometricStaff=findViewById(R.id.spinneruserbiometricStaff);
        spinnerBioMetricInstallationYear=findViewById(R.id.spinnerBioMetricInstallationYear);
        spinnerBioMetricMachineAvailabelty=findViewById(R.id.spinnerBioMetricMachineAvailabelty);

        recyclerViewBioMetric=findViewById(R.id.recyclerViewBioMetric);

        ArrayList<String> arrayList1=new ArrayList<>();
        arrayList1.add("Yes");
        arrayList1.add("No");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList1);
        adapter.setDropDownViewResource(R.layout.custom_text_spiiner);



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

        spinnerBioMetricInstallationYear.setAdapter(arrayAdapter1);



        ArrayList<String> arrayListWorkingStatus=new ArrayList<>();
        arrayListWorkingStatus.add("Functional");
        arrayListWorkingStatus.add("Non Functional");

        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListWorkingStatus);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerBiometricWorkingStatus.setAdapter(arrayAdapter2);
        spinnerBiometricWorkingStatus.setAdapter(arrayAdapter2);


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