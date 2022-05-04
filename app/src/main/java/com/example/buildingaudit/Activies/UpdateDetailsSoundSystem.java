package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buildingaudit.Adapters.ImageAdapter4;
import com.example.buildingaudit.ApplicationController;
import com.example.buildingaudit.R;
import com.example.buildingaudit.RetrofitApi.ApiService;
import com.example.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDetailsSoundSystem extends AppCompatActivity {
Spinner spinnerSoundSystem,spinnerSchoolBand,spinnerSchoolBandForGirls;
        ImageView soundSystemImageUploadBtn;
RecyclerView recyclerViewSoundSystm;
Button submitSoundSystemBtn;
    Dialog dialog;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
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
    TextView userName,schoolAddress,schoolName;
    ApplicationController applicationController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_sound_system);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Dialog dialog2 = new Dialog(this);

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        recyclerViewSoundSystm=findViewById(R.id.recyclerViewSoundSystm);
        soundSystemImageUploadBtn=findViewById(R.id.soundSystemImageUploadBtn);
        spinnerSoundSystem=findViewById(R.id.spinnerSoundSystem);
        spinnerSchoolBand=findViewById(R.id.spinnerSchoolBand);
        spinnerSchoolBandForGirls=findViewById(R.id.spinnerSchoolBandForGirls);
        submitSoundSystemBtn=findViewById(R.id.submitSoundSystemBtn);
        dialog = new Dialog(this);
        dialog.setCancelable(false);

        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.respons_dialog);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerSoundSystem.setAdapter(arrayAdapter);
        spinnerSchoolBand.setAdapter(arrayAdapter);
        spinnerSchoolBandForGirls.setAdapter(arrayAdapter);



        soundSystemImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withActivity(UpdateDetailsSoundSystem.this)
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
        recyclerViewSoundSystm.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter6 = new ImageAdapter4(this, arrayListImages1);
        recyclerViewSoundSystm.setAdapter(adapter6);
        adapter6.notifyDataSetChanged();

        submitSoundSystemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrayListImages1.size()==0){
                    Toast.makeText(UpdateDetailsSoundSystem.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();

                }else {
                RestClient restClient=new RestClient();
                ApiService apiService=restClient.getApiService();
                    Call<List<JsonObject>> call=apiService.uploadSoundSystem(paraSoundSystem("1","22","SoundSystemDetails",spinnerSoundSystem.getSelectedItem().toString(),spinnerSchoolBand.getSelectedItem().toString(),spinnerSchoolBandForGirls.getSelectedItem().toString(), applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),arrayListImages1));
                    call.enqueue(new Callback<List<JsonObject>>() {
                        @Override
                        public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                            Log.d("TAG", "onResponse: "+response.body());
                            TextView textView=dialog.findViewById(R.id.dialogtextResponse);
                            Button button=dialog.findViewById(R.id.BtnResponseDialoge);
                            try {
                                if (response.body().get(0).get("Status").getAsString().equals("E")){
                                    textView.setText("You already uploaded details ");

                                }else if(response.body().get(0).get("Status").getAsString().equals("S")){
                                    textView.setText("Your details Submitted successfully ");
                                }
                                dialog.show();
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        onBackPressed();
                                        dialog.dismiss();
                                    }
                                });
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(), "Something went wrong please try again!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<JsonObject>> call, Throwable t) {

                        }
                    });
            }
            }
        });
    }

    private JsonObject paraSoundSystem(String action, String paramid, String paramName, String soundSysAvailability, String schoolBandInstAvlBoys, String schoolBandInstAvlGirls, String latitude, String longitude, String schoolId, String periodID, String usertypeid, String userid, ArrayList<Bitmap> arrayListImages1) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",action);
        jsonObject.addProperty("ParamId",paramid);
        jsonObject.addProperty("ParamName",paramName);
        jsonObject.addProperty("SoundSysAvailability",soundSysAvailability);
        jsonObject.addProperty("SchoolBandInstAvlBoys",schoolBandInstAvlBoys);
        jsonObject.addProperty("SchoolBandInstAvlGirls",schoolBandInstAvlGirls);
        jsonObject.addProperty("Lat",latitude);
        jsonObject.addProperty("Long",longitude);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodID);
        jsonObject.addProperty("CreatedBy",usertypeid);
        jsonObject.addProperty("UserCode",userid);

        JsonArray jsonArray2 = new JsonArray();
        for (int i = 0; i < arrayListImages1.size(); i++) {
            jsonArray2.add(paraGetImageBase64( arrayListImages1.get(i), i));

        }
        jsonObject.add("SoundPhoto", (JsonElement) jsonArray2);
        return jsonObject;
    }
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public static String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }


    private JsonObject paraGetImageBase64( Bitmap bitmap, int i) {
        JsonObject jsonObject = new JsonObject();

        try {
            jsonObject.addProperty("id", String.valueOf(i + 1));
            jsonObject.addProperty("photos", BitMapToString(getResizedBitmap(bitmap, 300)));
//            Log.d("TAG", "paraGetImageBase64: "+BitMapToString(bitmap));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
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