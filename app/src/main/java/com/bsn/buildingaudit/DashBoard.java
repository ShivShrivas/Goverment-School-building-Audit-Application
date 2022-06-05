package com.bsn.buildingaudit;

import android.Manifest;
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
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Activies.Final_OTP_Submission;
import com.bsn.buildingaudit.Adapters.dashboardRecviewAdapter;
import com.bsn.buildingaudit.Model.BoundryType;
import com.bsn.buildingaudit.Model.DataLocked;
import com.bsn.buildingaudit.Model.GetAllRoomsList;
import com.bsn.buildingaudit.Model.InstallationYear;
import com.bsn.buildingaudit.RetrofitApi.ApiMsg91;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.bsn.buildingaudit.RetrofitApi.RestClientMsg91;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;
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
    public static Button btnBottom;
    ApplicationController applicationController;
    DrawerLayout mainDrawerLayout;
    Dialog dialog;
    public  static  int DataLocked;
    LinearLayout logOutBtn;
    ImageView hamMenu;
    dashboardRecviewAdapter adapter;
    CollapsingToolbarLayout mCollapsingToolbarLayout;

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
        DataLocked=applicationController.getDataLocked();
        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.progress_dialog);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        //  dashboardRecview=findViewById(R.id.dashboardRecview);
        dashboardRecview = findViewById(R.id.recViewDashboard);
        btnBottom = findViewById(R.id.btnBottom);
        userName = findViewById(R.id.userName);
        mCollapsingToolbarLayout = findViewById(R.id.mCollapsingToolbarLayout);
        schoolAddress = findViewById(R.id.schoolAddress);
        logOutBtn = findViewById(R.id.logOutBtn);

        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashBoard.this,MainActivity.class));
                finish();
            }
        });
        btnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestClient restClient=new RestClient();
                ApiService apiService=restClient.getApiService();
                Call<List<DataLocked>> call=apiService.checkLockedData(paraLocakedData("1",applicationController.getSchoolId(),applicationController.getPeriodID(),applicationController.getUserid()));
                call.enqueue(new Callback<List<DataLocked>>() {
                    @Override
                    public void onResponse(Call<List<DataLocked>> call, Response<List<DataLocked>> response) {
                        List<DataLocked> dataLockeds=response.body();
                        if (checkAllPagesIsDone( dataLockeds)){
                            RestClientMsg91 restClientMsg91=new RestClientMsg91();
                            ApiMsg91 apiMsg91=restClientMsg91.getApiService();
                            Call<JsonObject> call1=apiMsg91.getOtp("627cd1d23f61350c60138c24","91"+applicationController.getPhoneNumber(),"376489AgYgO9FDHy362711b3fP1");
                            call1.enqueue(new Callback<JsonObject>() {
                                @Override
                                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                    Log.d("TAG", "onResponse: "+response.body().get("type"));
                                    if (response.body().get("type").getAsString().equals("success")){
                                        startActivity(new Intent(DashBoard.this, Final_OTP_Submission.class));
                                    }else{
                                        Toast.makeText(DashBoard.this, "Something went wrong please try again!!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<JsonObject> call, Throwable t) {

                                }
                            });

                        }
                    }

                    @Override
                    public void onFailure(Call<List<DataLocked>> call, Throwable t) {

                    }
                });
            }
        });
        schoolName = findViewById(R.id.schoolName);
        mainDrawerLayout = findViewById(R.id.mainDrawerLayout);
        hamMenu = findViewById(R.id.hamMenu);
        DrawerLayout navDrawer = findViewById(R.id.mainDrawerLayout);


        dashboardRecview.setLayoutManager(new LinearLayoutManager(DashBoard.this));

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


                adapter=new dashboardRecviewAdapter(DashBoard.this,arrayList,applicationController.getSchoolId(),applicationController.getPeriodID());
                dashboardRecview.setHasFixedSize(true);
                dashboardRecview.setAdapter(adapter);
                dashboardRecview.getViewTreeObserver().addOnPreDrawListener(
                        new ViewTreeObserver.OnPreDrawListener() {

                            @Override
                            public boolean onPreDraw() {
                                dashboardRecview.getViewTreeObserver().removeOnPreDrawListener(this);

                                for (int i = 0; i < dashboardRecview.getChildCount(); i++) {
                                    View v = dashboardRecview.getChildAt(i);
                                    v.setAlpha(0.0f);
                                    v.animate().alpha(1.0f)
                                            .setDuration(600)
                                            .setStartDelay(i * 200)
                                            .start();
                                }

                                return true;
                            }
                        });
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<GetAllRoomsList>> call, Throwable t) {

            }
        });

        setBoundryTypeDynamic();
        setInstallationYearDynamic();

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

    private boolean checkAllPagesIsDone(List<DataLocked> dataLockeds) {
        boolean b=false;
        for (int i=0;i<dataLockeds.size();i++){
            if (dataLockeds.get(i).getSTATUS()==0){
                Toast.makeText(this, ""+dataLockeds.get(i).getStatusName(), Toast.LENGTH_SHORT).show();
                b=false;
                break;
            }
            else {
               b=true;
            }
        }
        return b;
    }

    private JsonObject paraLocakedData(String s, String schoolId, String periodID, String userid) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",s);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodID);
        jsonObject.addProperty("userid",userid);

        return jsonObject;
    }

    private void setInstallationYearDynamic() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action","6");
        Call<List<InstallationYear>> call=apiService.getInstallationYear(jsonObject);
        call.enqueue(new Callback<List<InstallationYear>>() {
            @Override
            public void onResponse(Call<List<InstallationYear>> call, Response<List<InstallationYear>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                applicationController.setInstallationYears(response.body());
                Log.d("TAG", "onResponse: "+applicationController.getInstallationYears().get(0).getYear());
            }

            @Override
            public void onFailure(Call<List<InstallationYear>> call, Throwable t) {

            }
        });

    }

    private void setBoundryTypeDynamic() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action","5");
        Call<List<BoundryType>> call=apiService.getBoundryType(jsonObject);
        call.enqueue(new Callback<List<BoundryType>>() {
            @Override
            public void onResponse(Call<List<BoundryType>> call, Response<List<BoundryType>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                applicationController.setBoundryTypes(response.body());
                Log.d("TAG", "onResponse: "+applicationController.getBoundryTypes().get(0).getBWTypeName());
            }

            @Override
            public void onFailure(Call<List<BoundryType>> call, Throwable t) {

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

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<GetAllRoomsList>> call=apiService.getRoomList(paraRoom("1",applicationController.getSchoolId(),applicationController.getPeriodID()));
        call.enqueue(new Callback<List<GetAllRoomsList>>() {
            @Override
            public void onResponse(Call<List<GetAllRoomsList>> call, Response<List<GetAllRoomsList>> response) {
                Log.d("TAG", "onResponse: getAllrooms "+response.body());
                arrayList=response.body();

                dashboardRecview.setLayoutManager(new LinearLayoutManager(DashBoard.this));

                adapter=new dashboardRecviewAdapter(DashBoard.this,arrayList,applicationController.getSchoolId(),applicationController.getPeriodID());

                dashboardRecview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<GetAllRoomsList>> call, Throwable t) {

            }
        });


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
                        if (longitude.equals(null) || latitude.equals(null) || longitude.equals("") || latitude.equals("")){
                            AlertDialog.Builder builder = new AlertDialog.Builder(DashBoard.this);
                            builder.setMessage("Device Location is not found please check ");
                            builder.setCancelable(false);
                            builder.setPositiveButton("check", new DialogInterface.OnClickListener() {
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
                        }else{
                            applicationController.setLatitude(latitude);
                            applicationController.setLongitude(longitude);
                        }

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