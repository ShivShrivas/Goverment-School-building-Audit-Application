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

public class UpdateDetailsTypeFour extends AppCompatActivity {
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    } @Override
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
    ImageView typeFourImageUploadBtn;
    ImageAdapter4 adapter;
Spinner spinnerFurnitureAvailabiltyInLibrary,spinnerPhysicalStatus,spinnerNewsPaperAndMzin,spinnerGrantUnderScheme,spinnerRoomAvailabelty,spinnerWorkingStatus,spinnerReadingCorner;
RecyclerView recyclerViewTwoTypeFour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_type_four);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        spinnerPhysicalStatus=findViewById(R.id.spinnerPhysicalStatus);
        spinnerReadingCorner=findViewById(R.id.spinnerReadingCorner);
        spinnerNewsPaperAndMzin=findViewById(R.id.spinnerNewsPaperAndMzin);
        spinnerRoomAvailabelty=findViewById(R.id.spinnerRoomAvailabelty);
        spinnerWorkingStatus=findViewById(R.id.spinnerWorkingStatus);
        spinnerGrantUnderScheme=findViewById(R.id.spinnerGrantUnderScheme);
        spinnerFurnitureAvailabiltyInLibrary=findViewById(R.id.spinnerFurnitureAvailabiltyInLibrary);
        typeFourImageUploadBtn=findViewById(R.id.typeFourImageUploadBtn);
        recyclerViewTwoTypeFour=findViewById(R.id.recyclerViewTwoTypeFour);

        ArrayList<String> arrayListSpinner = new ArrayList<>();

        arrayListSpinner.add("Good Condition");
        arrayListSpinner.add("Minor repairing requored");
        arrayListSpinner.add("major repairing required");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListSpinner);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerPhysicalStatus.setAdapter(arrayAdapter);

        ArrayList<String> arrayListspinnerRoomAvailabelty =new ArrayList<>();
        arrayListspinnerRoomAvailabelty.add("Yes");
        arrayListspinnerRoomAvailabelty.add("No");
        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,arrayListspinnerRoomAvailabelty);
        arrayAdapter1.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerRoomAvailabelty.setAdapter(arrayAdapter1);
        spinnerFurnitureAvailabiltyInLibrary.setAdapter(arrayAdapter1);
        ArrayList<String> arrayListWorkingStatus =new ArrayList<>();
        arrayListWorkingStatus.add("Functional");
        arrayListWorkingStatus.add("Non-Functional");

        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListWorkingStatus);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerWorkingStatus.setAdapter(arrayAdapter2);

        ArrayList<String> arrayListReadingCorner=new ArrayList<>();
        arrayListReadingCorner.add("Yes");
        arrayListReadingCorner.add("No");
        ArrayAdapter<String> arrayAdapter3 =new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListReadingCorner);
        arrayAdapter3.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerReadingCorner.setAdapter(arrayAdapter3);

        ArrayList<String> arrayListnewsMgzin=new ArrayList<>();
        arrayListnewsMgzin.add("Yes");
        arrayListnewsMgzin.add("No");
        ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListnewsMgzin);
        arrayAdapter4.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerNewsPaperAndMzin.setAdapter(arrayAdapter4);

        ArrayList<String> arrayListGrantUnderScheme=new ArrayList<>();
        arrayListGrantUnderScheme.add("RMSA");
        arrayListGrantUnderScheme.add("CSR");
        arrayListGrantUnderScheme.add("MSDP");
        arrayListGrantUnderScheme.add("PM Jan Vikas");
        arrayListGrantUnderScheme.add("Others");

        ArrayAdapter<String> arrayAdapter5=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListGrantUnderScheme);
        arrayAdapter5.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerGrantUnderScheme.setAdapter(arrayAdapter5);
        typeFourImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withActivity(UpdateDetailsTypeFour.this)
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
        recyclerViewTwoTypeFour.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new ImageAdapter4(this, arrayListImages1);
        recyclerViewTwoTypeFour.setAdapter(adapter);
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