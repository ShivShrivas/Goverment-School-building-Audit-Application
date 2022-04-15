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

import com.example.buildingaudit.Adapters.ImageAdapter;
import com.example.buildingaudit.Adapters.ImageAdapter2;
import com.example.buildingaudit.Adapters.ImageAdapter4;
import com.example.buildingaudit.Adapters.updateDetailsTypeThreeAdapter;
import com.example.buildingaudit.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class UpdatedetailsTypeThree extends AppCompatActivity {
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    int BtnType;

Spinner spinnerSciencelabAvailability,spinnerBiologylabAvailability,spinnerHomeMusiclabAvailability,spinnerHomeSciencelabAvailability,spinnerGeographylabAvailability,spinnerChemistrylabAvailability,spinnerPhysicslabAvailability;
Spinner spinnerMusicEquipmentStatus,spinnerHomeScienceEquipmentStatus
        ,spinnerScienceEquipmentStatus,spinnerBilogyEquipmentStatus,spinnerGeographyEquipmentStatus,spinnerChemistryEquipmentStatus
        ,spinnerPhysicsEquipmentStatus;
Spinner spinnerMusicLabCondition,spinnerHomeScienceLabCondition,spinnerGeographyLabCondition,spinnerScienceLabCondition,
        spinnerBiologyLabCondition,spinnerChemistryLabCondition,spinnerPhysicsLabCondition;

RecyclerView recyclerViewMusicLab,recyclerViewHomeScienceLab,recyclerViewGeographyLab,recyclerViewScienceLab
        ,recyclerViewBiologyLab,recyclerViewChemistryLab,recyclerViewPhysicsLab;

ImageView imageUpoadMusicLab,imageUpoadHomeScienceLab,imageUpoadGeographyLab
        ,imageUpoadScienceLab,imageUpoadBiologyLab,imageUpoadChemistryLab,imageUpoadPhysicsLab;

    ImageAdapter adapter1,adapter2,adapter3,adapter4,adapter5,adapter6,adapter7;
    public ArrayList<Bitmap> arrayListImages1 = new ArrayList<>();
    public ArrayList<Bitmap> arrayListImages2 = new ArrayList<>();
    public ArrayList<Bitmap> arrayListImages3= new ArrayList<>();
    public ArrayList<Bitmap> arrayListImages4 = new ArrayList<>();
    public ArrayList<Bitmap> arrayListImages5 = new ArrayList<>();
    public ArrayList<Bitmap> arrayListImages6 = new ArrayList<>();
    public ArrayList<Bitmap> arrayListImages7 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Practical Lab");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_updatedetails_type_three);
        spinnerHomeMusiclabAvailability=findViewById(R.id.spinnerHomeMusiclabAvailability);
        spinnerChemistrylabAvailability=findViewById(R.id.spinnerChemistrylabAvailability);
        spinnerHomeSciencelabAvailability=findViewById(R.id.spinnerHomeSciencelabAvailability);
        spinnerGeographylabAvailability=findViewById(R.id.spinnerGeographylabAvailability);
        spinnerPhysicslabAvailability=findViewById(R.id.spinnerPhysicslabAvailability);
        spinnerBiologylabAvailability=findViewById(R.id.spinnerBiologylabAvailability);
        spinnerSciencelabAvailability=findViewById(R.id.spinnerSciencelabAvailability);
        spinnerMusicEquipmentStatus=findViewById(R.id.spinnerMusicEquipmentStatus);
        spinnerHomeScienceEquipmentStatus=findViewById(R.id.spinnerHomeScienceEquipmentStatus);
        spinnerScienceEquipmentStatus=findViewById(R.id.spinnerScienceEquipmentStatus);
        spinnerBilogyEquipmentStatus=findViewById(R.id.spinnerBilogyEquipmentStatus);
        spinnerGeographyEquipmentStatus=findViewById(R.id.spinnerGeographyEquipmentStatus);
        spinnerChemistryEquipmentStatus=findViewById(R.id.spinnerChemistryEquipmentStatus);
        spinnerPhysicsEquipmentStatus=findViewById(R.id.spinnerPhysicsEquipmentStatus);
        spinnerMusicLabCondition=findViewById(R.id.spinnerMusicLabCondition);
        spinnerHomeScienceLabCondition=findViewById(R.id.spinnerHomeScienceLabCondition);
        spinnerGeographyLabCondition=findViewById(R.id.spinnerGeographyLabCondition);
        spinnerScienceLabCondition=findViewById(R.id.spinnerScienceLabCondition);
        spinnerBiologyLabCondition=findViewById(R.id.spinnerBiologyLabCondition);
        spinnerChemistryLabCondition=findViewById(R.id.spinnerChemistryLabCondition);
        spinnerPhysicsLabCondition=findViewById(R.id.spinnerPhysicsLabCondition);

        recyclerViewMusicLab=findViewById(R.id.recyclerViewMusicLab);
        recyclerViewHomeScienceLab=findViewById(R.id.recyclerViewHomeScienceLab);
        recyclerViewGeographyLab=findViewById(R.id.recyclerViewGeographyLab);
        recyclerViewScienceLab=findViewById(R.id.recyclerViewScienceLab);
        recyclerViewBiologyLab=findViewById(R.id.recyclerViewBiologyLab);
        recyclerViewChemistryLab=findViewById(R.id.recyclerViewChemistryLab);
        recyclerViewPhysicsLab=findViewById(R.id.recyclerViewPhysicsLab);

        imageUpoadMusicLab=findViewById(R.id.imageUpoadMusicLab);
        imageUpoadHomeScienceLab=findViewById(R.id.imageUpoadHomeScienceLab);
        imageUpoadGeographyLab=findViewById(R.id.imageUpoadGeographyLab);
        imageUpoadScienceLab=findViewById(R.id.imageUpoadScienceLab);
        imageUpoadBiologyLab=findViewById(R.id.imageUpoadBiologyLab);
        imageUpoadChemistryLab=findViewById(R.id.imageUpoadChemistryLab);
        imageUpoadPhysicsLab=findViewById(R.id.imageUpoadPhysicsLab);




        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerHomeMusiclabAvailability.setAdapter(arrayAdapter);
                spinnerChemistrylabAvailability.setAdapter(arrayAdapter);
        spinnerHomeSciencelabAvailability.setAdapter(arrayAdapter);
                spinnerGeographylabAvailability.setAdapter(arrayAdapter);
        spinnerPhysicslabAvailability.setAdapter(arrayAdapter);
                spinnerBiologylabAvailability.setAdapter(arrayAdapter);
        spinnerSciencelabAvailability.setAdapter(arrayAdapter);

        ArrayList<String> arrayListSpinner2 = new ArrayList<>();
        arrayListSpinner2.add("Good Condition");
        arrayListSpinner2.add("Minor Repairing");
        arrayListSpinner2.add("Major repairing");

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListSpinner2);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerMusicEquipmentStatus.setAdapter(arrayAdapter2);
                spinnerHomeScienceEquipmentStatus.setAdapter(arrayAdapter2);
        spinnerScienceEquipmentStatus.setAdapter(arrayAdapter2);
                spinnerBilogyEquipmentStatus.setAdapter(arrayAdapter2);
        spinnerGeographyEquipmentStatus.setAdapter(arrayAdapter2);
                spinnerChemistryEquipmentStatus.setAdapter(arrayAdapter2);
        spinnerPhysicsEquipmentStatus.setAdapter(arrayAdapter2);

        ArrayList<String> arrayListSpinner3 = new ArrayList<>();
        arrayListSpinner3.add("Fully Equipped");
        arrayListSpinner3.add("Partially Equipped");
        arrayListSpinner3.add("Not Equipped");

        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListSpinner3);
        arrayAdapter3.setDropDownViewResource(R.layout.custom_text_spiiner);

        spinnerMusicLabCondition.setAdapter(arrayAdapter3);
                spinnerHomeScienceLabCondition.setAdapter(arrayAdapter3);
        spinnerGeographyLabCondition.setAdapter(arrayAdapter3);
                spinnerScienceLabCondition.setAdapter(arrayAdapter3);
        spinnerBiologyLabCondition.setAdapter(arrayAdapter3);
                spinnerChemistryLabCondition.setAdapter(arrayAdapter3);
        spinnerPhysicsLabCondition.setAdapter(arrayAdapter3);

        imageUpoadPhysicsLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnType=1;
                checkPermissions();
            }
        });

        imageUpoadChemistryLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnType=2;
                checkPermissions();
            }
        });

        imageUpoadBiologyLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnType=3;
                checkPermissions();
            }
        });

        imageUpoadBiologyLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnType=3;
                checkPermissions();
            }
        });

        imageUpoadHomeScienceLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnType=4;
                checkPermissions();
            }
        });

        imageUpoadMusicLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnType=5;
                checkPermissions();
            }
        });

        imageUpoadGeographyLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnType=6;
                checkPermissions();
            }
        });

        imageUpoadScienceLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnType=7;
                checkPermissions();
            }
        });

        recyclerViewPhysicsLab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter1 = new ImageAdapter(this, arrayListImages1);
        recyclerViewPhysicsLab.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();


        recyclerViewChemistryLab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter2 = new ImageAdapter(this, arrayListImages2);
        recyclerViewChemistryLab.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();

        recyclerViewBiologyLab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter3 = new ImageAdapter(this, arrayListImages3);
        recyclerViewBiologyLab.setAdapter(adapter3);
        adapter3.notifyDataSetChanged();

        recyclerViewHomeScienceLab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter4= new ImageAdapter(this, arrayListImages4);
        recyclerViewHomeScienceLab.setAdapter(adapter4);
        adapter4.notifyDataSetChanged();

        recyclerViewMusicLab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter5= new ImageAdapter(this, arrayListImages5);
        recyclerViewMusicLab.setAdapter(adapter5);
        adapter5.notifyDataSetChanged();

        recyclerViewGeographyLab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter6= new ImageAdapter(this, arrayListImages6);
        recyclerViewGeographyLab.setAdapter(adapter6);
        adapter6.notifyDataSetChanged();

        recyclerViewScienceLab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter7= new ImageAdapter(this, arrayListImages7);
        recyclerViewScienceLab.setAdapter(adapter7);
        adapter7.notifyDataSetChanged();
    }

    private void checkPermissions() {
        Dexter.withActivity(UpdatedetailsTypeThree.this)
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK ) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            if (BtnType==1){
                arrayListImages1.add(bitmap);
            }else   if (BtnType==2){
                arrayListImages2.add(bitmap);
            }else   if (BtnType==3){
                arrayListImages3.add(bitmap);
            }else   if (BtnType==4){
                arrayListImages4.add(bitmap);
            }else   if (BtnType==5){
                arrayListImages5.add(bitmap);
            }else   if (BtnType==6){
                arrayListImages6.add(bitmap);
            }else   if (BtnType==7){
                arrayListImages7.add(bitmap);
            }

        }
    }
    @Override
    protected void onStart() {
        super.onStart();

        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        adapter3.notifyDataSetChanged();
        adapter4.notifyDataSetChanged();
        adapter5.notifyDataSetChanged();
        adapter6.notifyDataSetChanged();
        adapter7.notifyDataSetChanged();

    }

    @Override
    protected void onStop() {
        super.onStop();

        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        adapter3.notifyDataSetChanged();
        adapter4.notifyDataSetChanged();
        adapter5.notifyDataSetChanged();
        adapter6.notifyDataSetChanged();
        adapter7.notifyDataSetChanged();

    }
}