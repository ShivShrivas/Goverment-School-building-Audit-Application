package com.example.buildingaudit.GetLocation;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import java.util.List;

public class GpsTracker extends Service implements LocationListener {
    Context context;
    LocationManager locationManager;
    boolean isNetworkEnabled;
    boolean isGpsEnabled;
    boolean canGetLocation;

    Location location;
    Double longitude;
    Double latitude;

    public GpsTracker(Context context) {
        this.context=context;
//        getLocation();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

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

//    public  Location getLocation(){
//        try {
//
//            locationManager= (LocationManager) context.getSystemService(LOCATION_SERVICE);
//
//                isGpsEnabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//
//                isNetworkEnabled=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//                if (!isNetworkEnabled&& !isGpsEnabled){
//                    Toast.makeText(context, "No network provider enabled", Toast.LENGTH_SHORT).show();
//                }else{
//                    this.canGetLocation=true;
//                    if (isNetworkEnabled){
//                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                                ActivityCompat.checkSelfPermission(context,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//
//                        }
//                    }
//                }
//        }catch (Exception e){
//
//        }
//    }
}
