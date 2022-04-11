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

public class UpdateDetailsDrinkingWater extends AppCompatActivity {
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();

        adapter6.notifyDataSetChanged();

    }

    @Override
    protected void onStop() {
        super.onStop();

        adapter6.notifyDataSetChanged();

    }
    public ArrayList<Bitmap> arrayListImages1 = new ArrayList<>();
    ImageAdapter4 adapter6;

    ImageView drinkingWaterImageUploadBtn;
    RecyclerView recyclerViewDrinkingWater;
Spinner spinnerROInstallationScheme,spinnerROInstallationWokingStatus,
        spinnerROInstallationAvailabiltyDW,spinnerOverheadTankWorkStatsyDW,
        spinnerOverheadTankAvailabiltyDW,spinnerWaterSupplyWorkStatsyDW,
        spinnerWaterSupplyAvailabiltyDW,spinnerSubmersibleWorkStatsyDW,
        spinnerSubmersibleAvailabiltyDW,spinnerHandPumpWorkStatsyDW,
        spinnerHandPumpAvailabiltyDW,spinnerDeparmentDW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_drinking_water);
        getSupportActionBar().setTitle("Drinking Water");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinnerROInstallationScheme=findViewById(R.id.spinnerROInstallationScheme);
        spinnerROInstallationWokingStatus=findViewById(R.id.spinnerROInstallationWokingStatus);
        drinkingWaterImageUploadBtn=findViewById(R.id.drinkingWaterImageUploadBtn);
        recyclerViewDrinkingWater=findViewById(R.id.recyclerViewDrinkingWater);
        spinnerROInstallationAvailabiltyDW=findViewById(R.id.spinnerROInstallationAvailabiltyDW);
        spinnerOverheadTankWorkStatsyDW=findViewById(R.id.spinnerOverheadTankWorkStatsyDW);
        spinnerOverheadTankAvailabiltyDW=findViewById(R.id.spinnerOverheadTankAvailabiltyDW);
        spinnerWaterSupplyWorkStatsyDW=findViewById(R.id.spinnerWaterSupplyWorkStatsyDW);
        spinnerWaterSupplyAvailabiltyDW=findViewById(R.id.spinnerWaterSupplyAvailabiltyDW);
        spinnerSubmersibleWorkStatsyDW=findViewById(R.id.spinnerSubmersibleWorkStatsyDW);
        spinnerSubmersibleAvailabiltyDW=findViewById(R.id.spinnerSubmersibleAvailabiltyDW);
        spinnerHandPumpWorkStatsyDW=findViewById(R.id.spinnerHandPumpWorkStatsyDW);
        spinnerHandPumpAvailabiltyDW=findViewById(R.id.spinnerHandPumpAvailabiltyDW);



        ArrayList<String> arrayList1=new ArrayList<>();
        arrayList1.add("Yes");
        arrayList1.add("No");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList1);
        adapter.setDropDownViewResource(R.layout.custom_text_spiiner);


        spinnerSubmersibleAvailabiltyDW.setAdapter(adapter);
        spinnerHandPumpAvailabiltyDW.setAdapter(adapter);
        spinnerROInstallationAvailabiltyDW.setAdapter(adapter);
        spinnerOverheadTankAvailabiltyDW.setAdapter(adapter);
        spinnerWaterSupplyAvailabiltyDW.setAdapter(adapter);

        ArrayList<String> arrayListWorkingStatus=new ArrayList<>();
        arrayListWorkingStatus.add("Functional");
        arrayListWorkingStatus.add("Non Functional");

        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListWorkingStatus);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerHandPumpWorkStatsyDW.setAdapter(arrayAdapter2);
        spinnerSubmersibleWorkStatsyDW.setAdapter(arrayAdapter2);
        spinnerWaterSupplyWorkStatsyDW.setAdapter(arrayAdapter2);
        spinnerOverheadTankWorkStatsyDW.setAdapter(arrayAdapter2);
        spinnerROInstallationWokingStatus.setAdapter(arrayAdapter2);


        ArrayList<String> arrayListUnderScheme=new ArrayList<>();
        arrayListUnderScheme.add("RMSA");
        arrayListUnderScheme.add("CSr");
        arrayListUnderScheme.add("MSDP");
        arrayListUnderScheme.add("PM Jan");
        arrayListUnderScheme.add("Vikas");
        arrayListUnderScheme.add("Others");

        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListUnderScheme);
        arrayAdapter1.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerROInstallationScheme.setAdapter(arrayAdapter1);



        drinkingWaterImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withActivity(UpdateDetailsDrinkingWater.this)
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
        recyclerViewDrinkingWater.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter6 = new ImageAdapter4(this, arrayListImages1);
        recyclerViewDrinkingWater.setAdapter(adapter6);
        adapter6.notifyDataSetChanged();


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