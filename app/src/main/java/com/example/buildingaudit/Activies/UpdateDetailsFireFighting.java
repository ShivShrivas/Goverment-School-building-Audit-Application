package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DatePickerDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDetailsFireFighting extends AppCompatActivity {
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
    DatePickerDialog datePickerDialog;
    ImageView firefightingImageUploadBtn;
    RecyclerView recyclerViewFireFightning;
    TextView userName,schoolAddress,schoolName;
    String todayDate,fromdate;
    Dialog dialog;
    ConstraintLayout constraintLayout30;
    Button btnUpdateFireFighting;
    ApplicationController applicationController;
    Spinner spinnerFireFightTraining,spinnerFireFightRenewalStatus,spinnerFireFightWorkingStatus,spinnerFireFightingInstallationYear,spinnerFireFightAvailabelty;
    TextView edtrenewalDate;
    Dialog dialog2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_fire_fighting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        dialog2 = new Dialog(this);
        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        dialog = new Dialog(this);
        dialog.setCancelable(false);

        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.respons_dialog);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        spinnerFireFightTraining=findViewById(R.id.spinnerFireFightTraining);
        recyclerViewFireFightning=findViewById(R.id.recyclerViewFireFightning);
        firefightingImageUploadBtn=findViewById(R.id.firefightingImageUploadBtn);
        spinnerFireFightRenewalStatus=findViewById(R.id.spinnerFireFightRenewalStatus);
        spinnerFireFightWorkingStatus=findViewById(R.id.spinnerFireFightWorkingStatus);
        spinnerFireFightingInstallationYear=findViewById(R.id.spinnerFireFightingInstallationYear);
        spinnerFireFightAvailabelty=findViewById(R.id.spinnerFireFightAvailabelty);
        btnUpdateFireFighting=findViewById(R.id.btnUpdateFireFighting);
        constraintLayout30=findViewById(R.id.constraintLayout30);
        edtrenewalDate=findViewById(R.id.edtrenewalDate);


        ArrayList<String> arrayList1=new ArrayList<>();
        arrayList1.add("Yes");
        arrayList1.add("No");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList1);
        adapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerFireFightAvailabelty.setAdapter(adapter);


        ArrayList<String> arrayListInstallationYear=new ArrayList<>();
        arrayListInstallationYear.add("2015");
        arrayListInstallationYear.add("2016");
        arrayListInstallationYear.add("2017");
        arrayListInstallationYear.add("2018");
        arrayListInstallationYear.add("2019");
        arrayListInstallationYear.add("2020");
        arrayListInstallationYear.add("2021");
        arrayListInstallationYear.add("2022");
        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListInstallationYear);
        arrayAdapter1.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerFireFightingInstallationYear.setAdapter(arrayAdapter1);



        ArrayList<String> arrayListWorkingStatus=new ArrayList<>();
        arrayListWorkingStatus.add("Functional");
        arrayListWorkingStatus.add("Non Functional");

        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListWorkingStatus);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerFireFightWorkingStatus.setAdapter(arrayAdapter2);



        ArrayList<String> arrayListRenewalStatus=new ArrayList<>();
        arrayListRenewalStatus.add("Valid");
        arrayListRenewalStatus.add("Invalid");

        ArrayAdapter<String> arrayAdapter3=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListRenewalStatus);
        arrayAdapter3.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerFireFightRenewalStatus.setAdapter(arrayAdapter3);
        spinnerFireFightTraining.setAdapter(adapter);




        firefightingImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withActivity(UpdateDetailsFireFighting.this)
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
        edtrenewalDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final java.util.Calendar c = java.util.Calendar.getInstance();
                final int mYear = c.get(java.util.Calendar.YEAR);
                final int mDay = c.get(Calendar.MONTH);
                final int cDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(UpdateDetailsFireFighting.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                String todaysDate=dayOfMonth+ "-"+ (monthOfYear + 1) + "-" + year;
                                SimpleDateFormat spf=new SimpleDateFormat("dd-MM-yyyy");
                                SimpleDateFormat spf1=new SimpleDateFormat("dd-MM-yyyy");
                                Date newDate= null;
                                Date newDate1= null;
                                try {
                                    newDate = spf.parse(todaysDate);
                                    newDate1 = spf.parse(todaysDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                spf= new SimpleDateFormat("yyyy-MM-dd");
                                spf1= new SimpleDateFormat("dd-MM-yyyy");
                                fromdate= spf.format(newDate);
                                edtrenewalDate.setText( spf1.format(newDate1));

                            }
                        }, mYear, mDay, cDay);
                datePickerDialog.show();
            }
        });
        recyclerViewFireFightning.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter6 = new ImageAdapter4(this, arrayListImages1);
        recyclerViewFireFightning.setAdapter(adapter6);
        adapter6.notifyDataSetChanged();
            spinnerFireFightAvailabelty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (spinnerFireFightAvailabelty.getSelectedItem().toString().equals("No")){
                        constraintLayout30.setVisibility(View.GONE);
                    }else {
                        constraintLayout30.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        btnUpdateFireFighting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.show();
                if (!spinnerFireFightAvailabelty.getSelectedItem().toString().equals("No")){
                    if (arrayListImages1.size()==0){
                        dialog2.dismiss();

                        Toast.makeText(UpdateDetailsFireFighting.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();

                    }else {
                        runService();

                    }
                }else{
                    runService();
                }

            }
        });
    }

    private void runService() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Log.d("TAG", "onClick: "+paraFireFight("1","12","FireFighting",spinnerFireFightWorkingStatus.getSelectedItem().toString(),
                spinnerFireFightRenewalStatus.getSelectedItem().toString(),fromdate,spinnerFireFightTraining.getSelectedItem().toString(),spinnerFireFightingInstallationYear.getSelectedItem().toString(),spinnerFireFightAvailabelty.getSelectedItem().toString(), applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),arrayListImages1));
        Call<List<JsonObject>> call=apiService.uploadFireFighting(paraFireFight("1","12","FireFighting",spinnerFireFightWorkingStatus.getSelectedItem().toString(),
                spinnerFireFightRenewalStatus.getSelectedItem().toString(),fromdate,spinnerFireFightTraining.getSelectedItem().toString(),spinnerFireFightingInstallationYear.getSelectedItem().toString(),spinnerFireFightAvailabelty.getSelectedItem().toString(), applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),arrayListImages1));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+response);
                TextView textView=dialog.findViewById(R.id.dialogtextResponse);
                Button button=dialog.findViewById(R.id.BtnResponseDialoge);
                try {
                    if (response.body().get(0).get("Status").getAsString().equals("E")){
                        textView.setText("You already uploaded details ");

                    }else if(response.body().get(0).get("Status").getAsString().equals("S")){
                        textView.setText("Your details Submitted successfully ");
                    }
                    dialog2.dismiss();

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
                    dialog2.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();

            }
        });
    }

    private JsonObject paraFireFight(String s, String s1, String fireFighting, String toString, String toString1, String toString2, String toString3, String toString4, String toString5, String latitude, String longitude, String schoolId, String periodID, String usertypeid, String userid, ArrayList<Bitmap> arrayListImages1) {

        JsonObject jsonObject=new JsonObject();
        if (toString5.equals("No")){
            jsonObject.addProperty("Action",s);
            jsonObject.addProperty("ParamId",s1);
            jsonObject.addProperty("ParamName",fireFighting);
            jsonObject.addProperty("SchoolId",schoolId);
            jsonObject.addProperty("PeriodID",periodID);
            jsonObject.addProperty("InstallationYear","0");
            jsonObject.addProperty("RenewalStatus","");
            jsonObject.addProperty("RenewalDateStatus","1800-08-08");
            jsonObject.addProperty("Training","");
            jsonObject.addProperty("WorkingStatus","");
            jsonObject.addProperty("Availabilty",toString5);
            jsonObject.addProperty("Lat",latitude);
            jsonObject.addProperty("Long",longitude);
            jsonObject.addProperty("CreatedBy",usertypeid);
            jsonObject.addProperty("UserCode",userid);

            JsonArray jsonArray2 = new JsonArray();
            for (int i = 0; i < arrayListImages1.size(); i++) {
                jsonArray2.add(paraGetImageBase64( arrayListImages1.get(i), i));

            }
            jsonObject.add("FireFightingPhoto", (JsonElement) jsonArray2);
        }else{
            jsonObject.addProperty("Action",s);
            jsonObject.addProperty("ParamId",s1);
            jsonObject.addProperty("ParamName",fireFighting);
            jsonObject.addProperty("SchoolId",schoolId);
            jsonObject.addProperty("PeriodID",periodID);
            jsonObject.addProperty("InstallationYear",toString4);
            jsonObject.addProperty("RenewalStatus",toString1);
            jsonObject.addProperty("RenewalDateStatus",toString2);
            jsonObject.addProperty("Training",toString3);
            jsonObject.addProperty("WorkingStatus",toString);
            jsonObject.addProperty("Availabilty",toString5);
            jsonObject.addProperty("Lat",latitude);
            jsonObject.addProperty("Long",longitude);
            jsonObject.addProperty("CreatedBy",usertypeid);
            jsonObject.addProperty("UserCode",userid);

            JsonArray jsonArray2 = new JsonArray();
            for (int i = 0; i < arrayListImages1.size(); i++) {
                jsonArray2.add(paraGetImageBase64( arrayListImages1.get(i), i));

            }
            jsonObject.add("FireFightingPhoto", (JsonElement) jsonArray2);
        }

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