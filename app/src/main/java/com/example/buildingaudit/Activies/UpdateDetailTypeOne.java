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
import android.widget.ImageView;

import com.example.buildingaudit.Adapters.ImageAdapter;
import com.example.buildingaudit.Adapters.ImageAdapter2;
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

public class UpdateDetailTypeOne extends AppCompatActivity {
RecyclerView recyclerViewTwoTypeOne,recyclerViewThreeTypeOne,recyclerViewFourTypeOne,recyclerViewOneTypeOne;
ImageView totalRoomImageUploadBtn,goodConditionImageUploadBtn,minorRepairingUploadImageBtn,majorRepairingUploadImageBtn;
int cameraType;
    public ArrayList<Bitmap> arrayListImages1 = new ArrayList<>();
    public ArrayList<Bitmap> arrayListImages2 = new ArrayList<>();
    public ArrayList<Bitmap> arrayListImages3 = new ArrayList<>();
    public ArrayList<Bitmap> arrayListImages4 = new ArrayList<>();
    ImageAdapter adapter;
    ImageAdapter2 adapter2;
    ImageAdapter3 adapter3;
    ImageAdapter4 adapter4;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        adapter3.notifyDataSetChanged();
        adapter4.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        adapter3.notifyDataSetChanged();
        adapter4.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_detail_type_one);

        recyclerViewFourTypeOne=findViewById(R.id.recyclerViewFourTypeOne);
        recyclerViewTwoTypeOne=findViewById(R.id.recyclerViewTwoTypeOne);
        recyclerViewThreeTypeOne=findViewById(R.id.recyclerViewThreeTypeOne);
        recyclerViewOneTypeOne=findViewById(R.id.recyclerViewOnetypeOne);
        totalRoomImageUploadBtn=findViewById(R.id.totalRoomImageUploadBtn);
        goodConditionImageUploadBtn=findViewById(R.id.goodConditionImageUploadBtn);
        minorRepairingUploadImageBtn=findViewById(R.id.minorRepairingUploadImageBtn);
        majorRepairingUploadImageBtn=findViewById(R.id.majorRepairingUploadImageBtn);
        totalRoomImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraType=1;
                Dexter.withActivity(UpdateDetailTypeOne.this)
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
        goodConditionImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraType=2;
                Dexter.withActivity(UpdateDetailTypeOne.this)
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
        minorRepairingUploadImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraType=3;
                Dexter.withActivity(UpdateDetailTypeOne.this)
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
        majorRepairingUploadImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraType=4;
                Dexter.withActivity(UpdateDetailTypeOne.this)
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
        recyclerViewOneTypeOne.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new ImageAdapter(this, arrayListImages1);
        recyclerViewOneTypeOne.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerViewTwoTypeOne.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter2 = new ImageAdapter2(this, arrayListImages2);
        recyclerViewTwoTypeOne.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();
        recyclerViewThreeTypeOne.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter3 = new ImageAdapter3(this, arrayListImages3);
        recyclerViewThreeTypeOne.setAdapter(adapter3);
        adapter3.notifyDataSetChanged();
        recyclerViewFourTypeOne.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter4 = new ImageAdapter4(this, arrayListImages4);
        recyclerViewFourTypeOne.setAdapter(adapter4);
        adapter4.notifyDataSetChanged();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK ) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            if (cameraType==1){
                arrayListImages1.add(bitmap);
            }else if(cameraType==2){
                arrayListImages2.add(bitmap);
            }else if(cameraType==3){
                arrayListImages3.add(bitmap);
            }else if (cameraType==4){
                arrayListImages4.add(bitmap);
            }

        }
    }
}
