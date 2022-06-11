package com.bsn.buildingaudit.Activies;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.GeofenceAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.GeoTags;
import com.bsn.buildingaudit.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UpdateDetails_GeofenchingDetails extends AppCompatActivity implements LocationListener {
    ApplicationController applicationController;
    TextView userName, schoolAddress, schoolName;
    RecyclerView geoFenchingRecview;
    ArrayList<GeoTags> arrayList = new ArrayList<>();
    double lattitude;
    double longitude;
    GeofenceAdapter adapter;
    LocationManager locationManager;

Button addGeoTagBtn,btnSubmitgeoFench;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onsubmit_geofenching_details);
        applicationController = (ApplicationController) getApplication();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy \n HH:mm:ss");
        Calendar cal = Calendar.getInstance();

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

        geoFenchingRecview.setLayoutManager(new LinearLayoutManager(this));
        adapter=new GeofenceAdapter(this, arrayList);
        geoFenchingRecview.setAdapter(adapter);
        btnSubmitgeoFench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrayList.size()<4){
                    Toast.makeText(UpdateDetails_GeofenchingDetails.this, "Please add tags more than four points", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UpdateDetails_GeofenchingDetails.this, "Data Submitted ", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });
        addGeoTagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getLocation()){
                    if (lattitude!=0.0 && longitude!=0.0){
                        if (arrayList.size()<11){
                            try{
                                if (!arrayList.get(arrayList.size()-1).getLattitude().equals(String.valueOf(lattitude)) && !arrayList.get(arrayList.size()-1).getLongitute().equals(String.valueOf(longitude))){
                                    GeoTags geoTags=new GeoTags(String.valueOf(lattitude), String.valueOf(longitude),  dateFormat.format(cal.getTime()));
                                    arrayList.add(geoTags);
                                    adapter.notifyDataSetChanged();
                                }else{
                                    Toast.makeText(UpdateDetails_GeofenchingDetails.this, "Please change your location", Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e){
                                GeoTags geoTags=new GeoTags(String.valueOf(lattitude), String.valueOf(longitude),  dateFormat.format(cal.getTime()));
                                arrayList.add(geoTags);
                                adapter.notifyDataSetChanged();
                            }

                           
                        }else{
                            Toast.makeText(UpdateDetails_GeofenchingDetails.this, "You can not take more than ten tags", Toast.LENGTH_SHORT).show();
                        }
                      
                    }else{
                        Toast.makeText(UpdateDetails_GeofenchingDetails.this, "we are fetching your location please retry", Toast.LENGTH_SHORT).show();
                    }
                   
                }


            }
        });
    }

    @SuppressLint("MissingPermission")
    private boolean getLocation() {
        try {
            locationManager=(LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER ,1000,1,UpdateDetails_GeofenchingDetails.this);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
       if (location!=null){
           lattitude=location.getLatitude();
           longitude=location.getLongitude();
           Log.d("TAG", "onLocationChanged: "+lattitude+"/////"+longitude);
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