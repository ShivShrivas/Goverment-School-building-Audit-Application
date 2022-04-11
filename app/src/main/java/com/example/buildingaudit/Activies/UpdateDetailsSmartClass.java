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

import com.example.buildingaudit.Adapters.ImageAdapter4;
import com.example.buildingaudit.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class UpdateDetailsSmartClass extends AppCompatActivity {
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
    ImageView smartClassImageUploadBtn;
    RecyclerView recyclerViewSmartClass;
Spinner spinnerInstallationYearSmartClass,spinnerUnderSchemeSmartClass,spinnerWorkingStatusSmartClass,
        spinnerTeacherAvailbilitySmartClass,spinnerProjectorSmartClass,spinnerLearningContentSmartClass,
        spinnerDigitalBoardSmartClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update_details_smart_class);
        getSupportActionBar().setTitle("Smart Class");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinnerInstallationYearSmartClass=findViewById(R.id.spinnerInstallationYearSmartClass);
        spinnerUnderSchemeSmartClass=findViewById(R.id.spinnerUnderSchemeSmartClass);
        spinnerWorkingStatusSmartClass=findViewById(R.id.spinnerWorkingStatusSmartClass);
        spinnerTeacherAvailbilitySmartClass=findViewById(R.id.spinnerTeacherAvailbilitySmartClass);
        spinnerProjectorSmartClass=findViewById(R.id.spinnerProjectorSmartClass);
        spinnerLearningContentSmartClass=findViewById(R.id.spinnerLearningContentSmartClass);
        spinnerDigitalBoardSmartClass=findViewById(R.id.spinnerDigitalBoardSmartClass);
        smartClassImageUploadBtn=findViewById(R.id.smartClassImageUploadBtn);
        recyclerViewSmartClass=findViewById(R.id.recyclerViewSmartClass);

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
        spinnerTeacherAvailbilitySmartClass.setAdapter(arrayAdapter);
        spinnerProjectorSmartClass.setAdapter(arrayAdapter);
        spinnerLearningContentSmartClass.setAdapter(arrayAdapter);
        ArrayList<String> arrayListboard=new ArrayList<>();
        arrayListboard.add("White Board");
        arrayListboard.add("Black Board");
        arrayListboard.add("Green Board");


        ArrayAdapter<String> arrayAdapter5=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListboard);
        arrayAdapter5.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerDigitalBoardSmartClass.setAdapter(arrayAdapter5);

        smartClassImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withActivity(UpdateDetailsSmartClass.this)
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
        recyclerViewSmartClass.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new ImageAdapter4(this, arrayListImages1);
        recyclerViewSmartClass.setAdapter(adapter);
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