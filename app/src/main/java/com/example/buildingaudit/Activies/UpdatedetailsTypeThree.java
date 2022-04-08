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
RecyclerView recyclerviewOfupdatedetailsTypeThree,recyclerViewTwoTypeThree;
String[] subjectUpdateDetailsThree={"Physics","Chemistry","Biology","Vocational","Science","Geography","Home Science","Psychology","Music"};
    updateDetailsTypeThreeAdapter adapter;
    ImageAdapter4 adapter1;
    ImageView typeThreeImageUploadBtn;

    @Override
    protected void onStart() {
        super.onStart();

        adapter.notifyDataSetChanged();
        adapter1.notifyDataSetChanged();

    }

    @Override
    protected void onStop() {
        super.onStop();

        adapter.notifyDataSetChanged();
        adapter1.notifyDataSetChanged();

    }
    public ArrayList<Bitmap> arrayListImages1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Practical Lab");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_updatedetails_type_three);
        recyclerviewOfupdatedetailsTypeThree=findViewById(R.id.recyclerviewOfupdatedetailsTypeThree);
        recyclerViewTwoTypeThree=findViewById(R.id.recyclerViewTwoTypeThree);
        typeThreeImageUploadBtn=findViewById(R.id.typeThreeImageUploadBtn);
        recyclerviewOfupdatedetailsTypeThree.setLayoutManager(new LinearLayoutManager(this));
        adapter=new updateDetailsTypeThreeAdapter(this,subjectUpdateDetailsThree);
        recyclerviewOfupdatedetailsTypeThree.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        typeThreeImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
        });
        recyclerViewTwoTypeThree.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter1 = new ImageAdapter4(this, arrayListImages1);
        recyclerViewTwoTypeThree.setAdapter(adapter1);
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