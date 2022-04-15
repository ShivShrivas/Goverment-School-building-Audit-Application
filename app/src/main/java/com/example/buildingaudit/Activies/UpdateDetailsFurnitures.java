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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buildingaudit.Adapters.ImageAdapter4;
import com.example.buildingaudit.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class UpdateDetailsFurnitures extends AppCompatActivity {
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
    ImageView furniutresImageUploadBtn;
    RecyclerView recyclerViewFurnitures;
    EditText edtTrippelSeated,edtDoubleSeated,edtSingleSeated;
    TextView edtTotalFurnirtureStrenght;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_furnitures);
        getSupportActionBar().setTitle("Furnitures");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        furniutresImageUploadBtn=findViewById(R.id.furniutresImageUploadBtn);
        recyclerViewFurnitures=findViewById(R.id.recyclerViewFurnitures);
        edtTotalFurnirtureStrenght=findViewById(R.id.edtTotalFurnirtureStrenght);
        edtTrippelSeated=findViewById(R.id.edtTrippelSeated);
        edtDoubleSeated=findViewById(R.id.edtDoubleSeated);
        edtSingleSeated=findViewById(R.id.edtSingleSeated);
        edtSingleSeated.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int singleSeated=0;
                int tripleSeated=0;
                int doubleSeated=0;

                try {
                     singleSeated=Integer.parseInt(edtSingleSeated.getText().toString().trim());
                }catch (Exception e){

                }
                try {
                    tripleSeated=Integer.parseInt(edtTrippelSeated.getText().toString().trim());
                }catch (Exception e){

                }
                try {
                    doubleSeated=Integer.parseInt(edtDoubleSeated.getText().toString().trim());

                }catch (Exception e){

                }

                edtTotalFurnirtureStrenght.setText(String.valueOf(singleSeated+(tripleSeated*3)+(doubleSeated*2)));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtDoubleSeated.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int singleSeated=0;
                int tripleSeated=0;
                int doubleSeated=0;

                try {
                     singleSeated=Integer.parseInt(edtSingleSeated.getText().toString().trim());
                }catch (Exception e){

                }
                try {
                    tripleSeated=Integer.parseInt(edtTrippelSeated.getText().toString().trim());
                }catch (Exception e){

                }
                try {
                    doubleSeated=Integer.parseInt(edtDoubleSeated.getText().toString().trim());

                }catch (Exception e){

                }

                edtTotalFurnirtureStrenght.setText(String.valueOf(singleSeated+(tripleSeated*3)+(doubleSeated*2)));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtTrippelSeated.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int singleSeated=0;
                int tripleSeated=0;
                int doubleSeated=0;

                try {
                     singleSeated=Integer.parseInt(edtSingleSeated.getText().toString().trim());
                }catch (Exception e){

                }
                try {
                    tripleSeated=Integer.parseInt(edtTrippelSeated.getText().toString().trim());
                }catch (Exception e){

                }
                try {
                    doubleSeated=Integer.parseInt(edtDoubleSeated.getText().toString().trim());

                }catch (Exception e){

                }

                edtTotalFurnirtureStrenght.setText(String.valueOf(singleSeated+(tripleSeated*3)+(doubleSeated*2)));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        furniutresImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withActivity(UpdateDetailsFurnitures.this)
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
        recyclerViewFurnitures.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter6 = new ImageAdapter4(this, arrayListImages1);
        recyclerViewFurnitures.setAdapter(adapter6);
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