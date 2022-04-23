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

import com.example.buildingaudit.Adapters.ImageAdapter4;
import com.example.buildingaudit.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class UpdateDetailsGym extends AppCompatActivity {
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
    ImageView gymImageUploadBtn;
    RecyclerView recyclerViewGym;
Spinner spinnerGymAvailabelty,gymWorkingStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_gym);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });        spinnerGymAvailabelty=findViewById(R.id.spinnerGymAvailabelty);
        gymWorkingStatus=findViewById(R.id.gymWorkingStatus);
        recyclerViewGym=findViewById(R.id.recyclerViewGym);
        gymImageUploadBtn=findViewById(R.id.gymImageUploadBtn);


        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerGymAvailabelty.setAdapter(arrayAdapter);


        ArrayList<String> arrayListLevellingStatus=new ArrayList<>();
        arrayListLevellingStatus.add("Functional");
        arrayListLevellingStatus.add("Non Functional");

        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListLevellingStatus);
        arrayAdapter1.setDropDownViewResource(R.layout.custom_text_spiiner);
        gymWorkingStatus.setAdapter(arrayAdapter1);

        gymImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withActivity(UpdateDetailsGym.this)
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
        recyclerViewGym.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter6 = new ImageAdapter4(this, arrayListImages1);
        recyclerViewGym.setAdapter(adapter6);
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