package com.example.buildingaudit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buildingaudit.Activies.NoInternetConnection;
import com.example.buildingaudit.Activies.UpdateDetailTypeOne;
import com.example.buildingaudit.Activies.UpdateDetailsBioMetric;
import com.example.buildingaudit.Activies.UpdateDetailsBoundryWall;
import com.example.buildingaudit.Activies.UpdateDetailsBoysToilet;
import com.example.buildingaudit.Activies.UpdateDetailsCCTV;
import com.example.buildingaudit.Activies.UpdateDetailsCWSNRamp;
import com.example.buildingaudit.Activies.UpdateDetailsComputerlab;
import com.example.buildingaudit.Activies.UpdateDetailsCycleStand;
import com.example.buildingaudit.Activies.UpdateDetailsDrinkingWater;
import com.example.buildingaudit.Activies.UpdateDetailsElectricityArrangment;
import com.example.buildingaudit.Activies.UpdateDetailsFireFighting;
import com.example.buildingaudit.Activies.UpdateDetailsFurnitures;
import com.example.buildingaudit.Activies.UpdateDetailsGirlsToilet;
import com.example.buildingaudit.Activies.UpdateDetailsGym;
import com.example.buildingaudit.Activies.UpdateDetailsMultipurposeHall;
import com.example.buildingaudit.Activies.UpdateDetailsOfExtraThings;
import com.example.buildingaudit.Activies.UpdateDetailsPlayground;
import com.example.buildingaudit.Activies.UpdateDetailsRainHarvest;
import com.example.buildingaudit.Activies.UpdateDetailsSmartClass;
import com.example.buildingaudit.Activies.UpdateDetailsSolarPanel;
import com.example.buildingaudit.Activies.UpdateDetailsSoundSystem;
import com.example.buildingaudit.Activies.UpdateDetailsTypeFour;
import com.example.buildingaudit.Activies.UpdateDetailsTypeTwo;
import com.example.buildingaudit.Activies.UpdatedetailsTypeThree;
import com.example.buildingaudit.Adapters.dashboardRecviewAdapter;
import com.example.buildingaudit.Model.GetAllRoomsList;
import com.example.buildingaudit.Model.RecModel;
import com.example.buildingaudit.RetrofitApi.ApiService;
import com.example.buildingaudit.RetrofitApi.RestClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.JsonObject;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoard extends AppCompatActivity {
    LocationManager locationManager;
    FusedLocationProviderClient fusedLocationProviderClient;
    String longitude, latitude;
    RecyclerView dashboardRecview;
    TextView userName, schoolAddress, schoolName;
    ProgressDialog progress;
    ApplicationController applicationController;
    DrawerLayout mainDrawerLayout;
    Dialog dialog;
    ImageView hamMenu;
    dashboardRecviewAdapter adapter;

    List<GetAllRoomsList> arrayList = new ArrayList<>();
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            // wifi is enabled

            connected = (nInfo != null && nInfo.isAvailable() && nInfo.isConnected()) ||wifiManager.isWifiEnabled();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        applicationController = (ApplicationController) getApplication();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
         dialog = new Dialog (this);
        dialog.setCancelable(false);

        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.progress_dialog);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        //  dashboardRecview=findViewById(R.id.dashboardRecview);
        dashboardRecview = findViewById(R.id.recViewDashboard);
        userName = findViewById(R.id.userName);
        schoolAddress = findViewById(R.id.schoolAddress);
        schoolName = findViewById(R.id.schoolName);
        mainDrawerLayout = findViewById(R.id.mainDrawerLayout);
        hamMenu = findViewById(R.id.hamMenu);
        DrawerLayout navDrawer = findViewById(R.id.mainDrawerLayout);


        Log.d("TAG", "onCreate: " + applicationController.getUsername() + applicationController.getSchoolName());
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        userName.setText(applicationController.getUsername());
        Log.d("TAG", "onCreate: " + latitude+longitude);
        dialog.show();
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<GetAllRoomsList>> call=apiService.getRoomList(paraRoom("1",applicationController.getSchoolId(),applicationController.getPeriodID()));
        call.enqueue(new Callback<List<GetAllRoomsList>>() {
            @Override
            public void onResponse(Call<List<GetAllRoomsList>> call, Response<List<GetAllRoomsList>> response) {
                Log.d("TAG", "onResponse: getAllrooms "+response.body());
                arrayList=response.body();

                dashboardRecview.setLayoutManager(new LinearLayoutManager(DashBoard.this));

                adapter=new dashboardRecviewAdapter(DashBoard.this,arrayList);

                dashboardRecview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<GetAllRoomsList>> call, Throwable t) {

            }
        });


        hamMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // If navigation drawer is not open yet, open it else close it.
                if (!navDrawer.isDrawerOpen(GravityCompat.START))
                    navDrawer.openDrawer(Gravity.LEFT);
                else navDrawer.closeDrawer(GravityCompat.END);
            }
        });

    }

    private void getAllRoomList() {

    }

    private JsonObject paraRoom(String action, String schoolId, String periodID) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",action);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodID);
        return jsonObject;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter!=null)
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        if (adapter!=null)
        adapter.notifyDataSetChanged();
        super.onStart();
        if (locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)) {
            getLocations();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Device Location is turn off ");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    finish();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    onBackPressed();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    private void getLocations() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        longitude = String.valueOf(location.getLongitude());
                        latitude = String.valueOf(location.getLatitude());
                        Log.d("TAG", "onSuccess: "+latitude+longitude);
                        applicationController.setLatitude(latitude);
                        applicationController.setLongitude(longitude);
                    }
                }
            });
        } else {
            requestPermission();
        }
    }

    private void requestPermission() {
        Dexter.withContext(DashBoard.this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // permission is granted,
                        if (ActivityCompat.checkSelfPermission(DashBoard.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(DashBoard.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    if (location != null) {
                                        longitude = String.valueOf(location.getLongitude());
                                        latitude = String.valueOf(location.getLatitude());
                                        applicationController.setLatitude(latitude);
                                        applicationController.setLongitude(longitude);
                                    }
                                }
                            });
                            return;
                        }

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
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},10);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==10){
            if (grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getLocations();
            }else {
                Toast.makeText(this, "Please enable location from app permissions setting!!", Toast.LENGTH_SHORT).show();
            }
        }
    }



}