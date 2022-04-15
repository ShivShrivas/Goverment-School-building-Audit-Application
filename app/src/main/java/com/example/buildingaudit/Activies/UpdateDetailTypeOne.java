package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.buildingaudit.Adapters.ImageAdapter;
import com.example.buildingaudit.Adapters.ImageAdapter2;
import com.example.buildingaudit.Adapters.ImageAdapter3;
import com.example.buildingaudit.Adapters.ImageAdapter4;
import com.example.buildingaudit.R;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class UpdateDetailTypeOne extends AppCompatActivity {
RecyclerView recyclerViewTwoTypeOne,recyclerViewThreeTypeOne,recyclerViewFourTypeOne;
Spinner spinnerBoardClass;
EditText edtPodiumClass;
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
    ConstraintLayout constratinflayout;
    EditText majorRepairingClassroom,minorRepairingClassroom,goodCondtionClassroom,totalClassRooms;
    Button classRoomSubmitbtn;
    @Override
    protected void onStart() {
        super.onStart();

        adapter2.notifyDataSetChanged();
        adapter3.notifyDataSetChanged();
        adapter4.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();

        adapter2.notifyDataSetChanged();
        adapter3.notifyDataSetChanged();
        adapter4.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
     onBackPressed();
     return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_detail_type_one);
        getSupportActionBar().setTitle("Class Room");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerViewFourTypeOne=findViewById(R.id.recyclerViewFourTypeOne);
        recyclerViewTwoTypeOne=findViewById(R.id.recyclerViewTwoTypeOne);
        constratinflayout=findViewById(R.id.constratinflayout);
        edtPodiumClass =findViewById(R.id.edtPodiumClass);
        recyclerViewThreeTypeOne=findViewById(R.id.recyclerViewThreeTypeOne);
        majorRepairingClassroom=findViewById(R.id.majorRepairingClassroom);
        minorRepairingClassroom=findViewById(R.id.minorRepairingClassroom);
        goodCondtionClassroom=findViewById(R.id.goodCondtionClassroom);
        totalClassRooms=findViewById(R.id.totalClassRooms);
        totalRoomImageUploadBtn=findViewById(R.id.totalRoomImageUploadBtn);
        classRoomSubmitbtn=findViewById(R.id.classRoomSubmitbtn);
        goodConditionImageUploadBtn=findViewById(R.id.goodConditionImageUploadBtn);
        minorRepairingUploadImageBtn=findViewById(R.id.minorRepairingUploadImageBtn);
        majorRepairingUploadImageBtn=findViewById(R.id.majorRepairingUploadImageBtn);
        goodCondtionClassroom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        classRoomSubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int totalClassRoom=Integer.parseInt(goodCondtionClassroom.getText().toString())+Integer.parseInt(minorRepairingClassroom.getText().toString())+Integer.parseInt(majorRepairingClassroom.getText().toString());
                    if (totalClassRoom!=Integer.parseInt(totalClassRooms.getText().toString().trim())){
                        Snackbar.make(constratinflayout, "Please provide correct number of rooms", Snackbar.LENGTH_INDEFINITE)
                                .setAction("Ok", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                })
                                .show();
                    }
                }catch (Exception e){
                    Toast.makeText(UpdateDetailTypeOne.this, "please fill all room count properly", Toast.LENGTH_SHORT).show();
                }

            }
        });
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

        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);



        ArrayList<String> arrayListBoard=new ArrayList<>();
        arrayListBoard.add("White Board");
        arrayListBoard.add("Green Board");
        arrayListBoard.add("Black Board");
        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListBoard);
        arrayAdapter1.setDropDownViewResource(R.layout.custom_text_spiiner);




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
