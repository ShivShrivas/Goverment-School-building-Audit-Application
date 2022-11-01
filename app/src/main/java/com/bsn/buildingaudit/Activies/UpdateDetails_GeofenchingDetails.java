package com.bsn.buildingaudit.Activies;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.GeofenceAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.GeoTags;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDetails_GeofenchingDetails extends AppCompatActivity implements LocationListener {
    ApplicationController applicationController;
    TextView userName, schoolAddress, schoolName;
    RecyclerView geoFenchingRecview;
    ArrayList<GeoTags> arrayList = new ArrayList<>();
    double lattitude;
    double longitude;
    GeofenceAdapter adapter;
    LocationManager locationManager;
    Dialog dialog;
    Dialog dialog2;
    DateFormat dateFormat;
    Calendar cal;

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(UpdateDetails_GeofenchingDetails.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(UpdateDetails_GeofenchingDetails.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }
    }

    Button addGeoTagBtn,btnSubmitgeoFench;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onsubmit_geofenching_details);
        applicationController = (ApplicationController) getApplication();
         dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         cal = Calendar.getInstance();
        dialog = new Dialog(this);
        dialog2 = new Dialog(this);
        dialog2.setCancelable(false);

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.respons_dialog_onsave);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.progress_dialog);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog.setCancelable(false);
        schoolAddress = findViewById(R.id.schoolAddress);
        geoFenchingRecview = findViewById(R.id.geoFenchingRecview);
        schoolName = findViewById(R.id.schoolName);
        addGeoTagBtn = findViewById(R.id.addGeoTagBtn);
        btnSubmitgeoFench = findViewById(R.id.btnSubmitgeoFench);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (ContextCompat.checkSelfPermission(UpdateDetails_GeofenchingDetails.this, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(UpdateDetails_GeofenchingDetails.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        geoFenchingRecview.setLayoutManager(new GridLayoutManager(this,3));
        adapter=new GeofenceAdapter(this, arrayList);
        geoFenchingRecview.setAdapter(adapter);
        btnSubmitgeoFench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrayList.size()<4){
                    Toast.makeText(UpdateDetails_GeofenchingDetails.this, "Please add tags more than four points", Toast.LENGTH_SHORT).show();
                }else{
                    RestClient restClient=new RestClient();
                    ApiService apiService=restClient.getApiService();
                   
                    Call<JsonArray> call=apiService.uploadGeoFenchingDetails(paraGetJsonOfGeos(applicationController.getSchoolId(),arrayList));
                  call.enqueue(new Callback<JsonArray>() {
                      @Override
                      public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                          JsonObject jsonObject= (JsonObject) response.body().get(0);
                         
                          TextView textView=dialog2.findViewById(R.id.dialogtextResponse);
                          Button button=dialog2.findViewById(R.id.BtnResponseDialoge);
                          try {
                              if (jsonObject.get("StatusCode").toString().equals("1")){
                                 
                                textView.setText("Data Submitted Successfully!!");
                              }else if(jsonObject.get("StatusCode").toString().equals("2")){
                                 
                                  textView.setText("Data already submitted");

                              }else{
                                 
                                  textView.setText("Something went wrong!!");

                              }
                              dialog.dismiss();

                              dialog2.show();
                              button.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view) {
                                      onBackPressed();
                                      dialog2.dismiss();
                                  }
                              });
                          }catch (Exception e){
                              Toast.makeText(getApplicationContext(), "Something went wrong please try again!!", Toast.LENGTH_SHORT).show();
                              dialog.dismiss();

                          }

                      }

                      @Override
                      public void onFailure(Call<JsonArray> call, Throwable t) {
                          Toast.makeText(UpdateDetails_GeofenchingDetails.this, "Something went wrong!! ", Toast.LENGTH_SHORT).show();

                      }
                  });


                }
            }
        });
        addGeoTagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocationLatLong();

            }
        });
    }

    private JsonObject paraGetJsonOfGeos(String schoolId, ArrayList<GeoTags> arrayList) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID",schoolId);
        jsonObject.add("GeoFenceData",getTagsJsonArray(arrayList));
        return jsonObject;
    }

    private JsonArray getTagsJsonArray(ArrayList<GeoTags> arrayList) {
       JsonArray jsonArray=new JsonArray();
        for (int i = 0; i < arrayList.size(); i++) {
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("Lati",arrayList.get(i).getLattitude());
            jsonObject.addProperty("Longi",arrayList.get(i).getLongitute());
            jsonObject.addProperty("CapturedDate",arrayList.get(i).getDateAndTime());
            jsonArray.add(jsonObject);
        }
       return jsonArray;
    }

    private void getLocationLatLong() {
        dialog.show();

        if (getLocation()){
            if (lattitude!=0.0 && longitude!=0.0){
                if (arrayList.size()<11){
                    try{
                        if (!arrayList.get(arrayList.size()-1).getLattitude().equals(String.valueOf(lattitude)) && !arrayList.get(arrayList.size()-1).getLongitute().equals(String.valueOf(longitude))){
                            GeoTags geoTags=new GeoTags(String.valueOf(lattitude), String.valueOf(longitude),  dateFormat.format(cal.getTime()));
                            arrayList.add(geoTags);
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();

                        }else{
                            dialog.dismiss();
                            Toast.makeText(UpdateDetails_GeofenchingDetails.this, "Please change your location", Toast.LENGTH_SHORT).show();

                        }
                    }catch (Exception e){
                        GeoTags geoTags=new GeoTags(String.valueOf(lattitude), String.valueOf(longitude),  dateFormat.format(cal.getTime()));
                        arrayList.add(geoTags);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }


                }else{
                    Toast.makeText(UpdateDetails_GeofenchingDetails.this, "You can not take more than ten tags", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            }else{
                Toast.makeText(UpdateDetails_GeofenchingDetails.this, "we are fetching your location please wait", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getLocationLatLong();
                    }
                },2000);
            }

        }else{
            Toast.makeText(this, "Please enable location of your device", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }

    }


    @SuppressLint("MissingPermission")
    private boolean getLocation() {
        try {
            locationManager=(LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER ,1000,1 ,UpdateDetails_GeofenchingDetails.this);
            return true;
        }catch (Exception e){
           
            dialog.dismiss();
            return false;
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
       if (location!=null){
           lattitude=location.getLatitude();
           longitude=location.getLongitude();
          
       }else{
           Toast.makeText(this, "not getting location", Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}