package com.bsn.staffAttendance;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.StaffAttendanceAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.AttendanceStaff;
import com.bsn.buildingaudit.Model.StaffAttendanceSubmitModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDetails_StaffAttendance extends AppCompatActivity implements LocationListener {
    TextView userName,schoolAddress,schoolName,txtdate;
    ApplicationController applicationController;
    RecyclerView recyclerViewStaffAttendance;
    StaffAttendanceAdapter adapter;
    Button buttonSaveAtendance;
    float geoFencheLattitude,getGeoFencheLongitude;
    String formattedDate,formattedDate1;
    Toolbar toolbar;
    float lattitude;
    float longitude;
    String todayDate,fromdate;
    LocationManager locationManager;
    Dialog dialog,dialog2,dialog3;
    ImageView calenderView;
    DatePickerDialog datePickerDialog;
    List<AttendanceStaff> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_staff_attendance);
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        txtdate=findViewById(R.id.txtdate);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        formattedDate = df.format(c);

        SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        formattedDate1 = df1.format(c);
        txtdate.setText(formattedDate1);
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        calenderView=findViewById(R.id.calenderView);

        schoolName=findViewById(R.id.schoolName);
        buttonSaveAtendance=findViewById(R.id.buttonSaveAtendance);
        dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.respons_dialog);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2 = new Dialog(this);
        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        recyclerViewStaffAttendance=findViewById(R.id.recyclerViewStaffAttendance);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        calenderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();
            }
        });

        recyclerViewStaffAttendance.setLayoutManager(new LinearLayoutManager(this));
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
       
       
        getStaffData();



        buttonSaveAtendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.show();
                getLocationLatLong();
               
               
//

            }
        });
    }
    private void getLocationLatLong() {


        if (getLocation()){
            if (lattitude!=0.0 && longitude!=0.0){
            try{
               
                if (arrayList.size()==StaffAttendanceAdapter.staffAttendanceSubmitModels.size()){
                    if (calculateLocationDifference(geoFencheLattitude,getGeoFencheLongitude,lattitude,longitude)<100) {
                        RestClient restClient=new RestClient();
                        ApiService apiService=restClient.getApiService();
                        Call<JsonArray> call=  apiService.submitAttendance(paraAttendance(StaffAttendanceAdapter.staffAttendanceSubmitModels));

                       
                        call.enqueue(new Callback<JsonArray>() {
                            @Override
                            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                               
                                JsonObject jsonObject=response.body().get(0).getAsJsonObject();
                                dialog2.dismiss();
                                dialog3 = new Dialog(UpdateDetails_StaffAttendance.this);
                                dialog3.setCancelable(false);
                                dialog3.requestWindowFeature (Window.FEATURE_NO_TITLE);
                                dialog3.setContentView (R.layout.respons_dialog_onsave);
                                dialog3.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
                                TextView textView=dialog3.findViewById(R.id.dialogtextResponse);
                                Button button=dialog3.findViewById(R.id.BtnResponseDialoge);
                                try {
                                    textView.setText(jsonObject.get("Status").getAsString());
                                    dialog3.show();
                                    button.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            onBackPressed();
                                            dialog3.dismiss();
                                        }
                                    });
                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(), "Something went wrong please try again!!", Toast.LENGTH_SHORT).show();
                                    dialog2.dismiss();
                                }
                            }
                            @Override
                            public void onFailure(Call<JsonArray> call, Throwable t) {
                               
                                dialog2.dismiss();
                            }
                        });


                    }else{
                        dialog2.dismiss();
                        Toast.makeText(UpdateDetails_StaffAttendance.this, "Please take Attendance in School location", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Please take attendance of all staff!!", Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                dialog2.dismiss();
                Toast.makeText(UpdateDetails_StaffAttendance.this, "Sorry We are unable to get your location please retry!!", Toast.LENGTH_SHORT).show();

            }

                }else{
                Toast.makeText(UpdateDetails_StaffAttendance.this, "we are fetching your location please wait", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getLocationLatLong();
                    }
                },2000);
            }

        }else{
            dialog2.dismiss();
            Toast.makeText(this, "Please enable location of your device", Toast.LENGTH_SHORT).show();

        }

    }
    @SuppressLint("MissingPermission")
    private boolean getLocation() {
        try {
            locationManager=(LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER ,1000,1, UpdateDetails_StaffAttendance.this);
            return true;
        }catch (Exception e){
           
            dialog.dismiss();
            return false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(UpdateDetails_StaffAttendance.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(UpdateDetails_StaffAttendance.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }
    }

    private int calculateLocationDifference(float geoFencheLattitude, float getGeoFencheLongitude, float latitude, float longitude) {

        float[] dist = new float[1];
        Location.distanceBetween(geoFencheLattitude, getGeoFencheLongitude, latitude, longitude, dist);
        return (int) dist[0];

    }

    private void getStaffData() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<AttendanceStaff>> listCall=apiService.getStaff(paraGetStaff(formattedDate,applicationController.getSchoolId()));
        listCall.enqueue(new Callback<List<AttendanceStaff>>() {
            @Override
            public void onResponse(Call<List<AttendanceStaff>> call, Response<List<AttendanceStaff>> response) {
                arrayList=response.body();
                try{
                    if ((arrayList.get(0).getDateofDay() == null ? "" : arrayList.get(0).getDateofDay()).isEmpty()) {
                     try{
                         geoFencheLattitude=Float.parseFloat(arrayList.get(0).getLati());
                         getGeoFencheLongitude=Float.parseFloat(arrayList.get(0).getLongi());
                     }catch (Exception e){
                         dialog2.dismiss();
                         dialog3 = new Dialog(UpdateDetails_StaffAttendance.this);
                         dialog3.setCancelable(false);
                         dialog3.requestWindowFeature (Window.FEATURE_NO_TITLE);
                         dialog3.setContentView (R.layout.respons_dialog_onsave);
                         dialog3.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
                         TextView textView=dialog3.findViewById(R.id.dialogtextResponse);
                         Button button=dialog3.findViewById(R.id.BtnResponseDialoge);

                             textView.setText("Please Do Geofencing Of your School First!!");
                             dialog3.show();
                             button.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View view) {
                                     onBackPressed();
                                     dialog3.dismiss();
                                 }
                             });

                     }

                        recyclerViewStaffAttendance.setAdapter(new StaffAttendanceAdapter(UpdateDetails_StaffAttendance.this, arrayList, formattedDate));
                    } else {
                        TextView textView = dialog.findViewById(R.id.dialogtextResponse);
                        Button button = dialog.findViewById(R.id.BtnResponseDialoge);
                        Button buttonYes = dialog.findViewById(R.id.BtnYesDialoge);
                        try {
                            textView.setText("Attendance for this date has been already submitted \n" +
                                    "Do you want to change date?");
                            dialog.show();
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    onBackPressed();
                                    dialog.dismiss();
                                }
                            });

                            buttonYes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    dialog.dismiss();
                                }
                            });
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Something went wrong please try again!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception e){
                    TextView textView = dialog.findViewById(R.id.dialogtextResponse);
                    Button button = dialog.findViewById(R.id.BtnResponseDialoge);
                    Button buttonYes = dialog.findViewById(R.id.BtnYesDialoge);
                    try {
                        textView.setText("Sorry!! \n We could not find any details of your staff\n" +
                                "Do you want to change date?");
                        dialog.show();
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                onBackPressed();
                                dialog.dismiss();
                            }
                        });
                        buttonYes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                dialog.dismiss();
                            }
                        });
                    } catch (Exception f) {
                        Toast.makeText(getApplicationContext(), "Something went wrong please try again!!", Toast.LENGTH_SHORT).show();
                    }
                }


            }

            @Override
            public void onFailure(Call<List<AttendanceStaff>> call, Throwable t) {

            }
        });

    }

    private boolean setDate() {
            final java.util.Calendar c = java.util.Calendar.getInstance();
            final int mYear = c.get(java.util.Calendar.YEAR);
            final int mDay = c.get(Calendar.MONTH);
            final int cDay = c.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(UpdateDetails_StaffAttendance.this,
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
                            formattedDate= spf.format(newDate);
                            txtdate.setText( spf1.format(newDate1));
                            getStaffData();
                        }
                    }, mYear, mDay, cDay);

            datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            datePickerDialog.show();
            return true;
        }


    private JsonObject paraAttendance(List<StaffAttendanceSubmitModel> staffAttendanceSubmitModels) {
        JsonObject jsonObject1=new JsonObject();

        JsonArray jsonArray=new JsonArray();
        for (int i=0;i<staffAttendanceSubmitModels.size();i++){
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("RecordID",staffAttendanceSubmitModels.get(i).getRecordID());
            jsonObject.addProperty("AttendenceDate",staffAttendanceSubmitModels.get(i).getAttendenceDate());
            jsonObject.addProperty("AttendenceStatusID",staffAttendanceSubmitModels.get(i).getAttendenceStatusID());
            jsonArray.add(jsonObject);
        }
        jsonObject1.add("StaffAttendanceDetailsSubmit",jsonArray);
        return jsonObject1;
    }

    private JsonObject paraGetStaff(String formattedDate, String schoolId) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("AttendenceDate",formattedDate);
        jsonObject.addProperty("SchoolId",schoolId);

        return jsonObject;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if (location!=null){
            lattitude= (float) location.getLatitude();
            longitude= (float) location.getLongitude();
           
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}