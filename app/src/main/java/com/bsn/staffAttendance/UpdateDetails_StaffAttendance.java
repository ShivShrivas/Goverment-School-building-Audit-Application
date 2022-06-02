package com.bsn.staffAttendance;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDetails_StaffAttendance extends AppCompatActivity {
    TextView userName,schoolAddress,schoolName;
    ApplicationController applicationController;
    RecyclerView recyclerViewStaffAttendance;
    StaffAttendanceAdapter adapter;
    Button buttonSaveAtendance;
    String formattedDate;
    Toolbar toolbar;
    Dialog dialog;
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
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
         formattedDate = df.format(c);
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        buttonSaveAtendance=findViewById(R.id.buttonSaveAtendance);
        dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.respons_dialog);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        recyclerViewStaffAttendance=findViewById(R.id.recyclerViewStaffAttendance);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        recyclerViewStaffAttendance.setLayoutManager(new LinearLayoutManager(this));
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Log.d("TAG", "onCreate: "+formattedDate);
        Call<List<AttendanceStaff>> listCall=apiService.getStaff(paraGetStaff(formattedDate,applicationController.getSchoolId()));
        listCall.enqueue(new Callback<List<AttendanceStaff>>() {
            @Override
            public void onResponse(Call<List<AttendanceStaff>> call, Response<List<AttendanceStaff>> response) {
                arrayList=response.body();
                if (arrayList.get(0).getDateofDay()==null){
                    Log.d("TAG", "onResponse: "+response.body().get(0).getAttendenceStatusID());
                    recyclerViewStaffAttendance.setAdapter(new StaffAttendanceAdapter(UpdateDetails_StaffAttendance.this,arrayList,formattedDate));
                }else{
                    TextView textView=dialog.findViewById(R.id.dialogtextResponse);
                    Button button=dialog.findViewById(R.id.BtnResponseDialoge);
                    try {
                        textView.setText("Today's Attendance already submitted \n" +
                                "please check on Date wise attendance page");
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


            }

            @Override
            public void onFailure(Call<List<AttendanceStaff>> call, Throwable t) {

            }
        });



        buttonSaveAtendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Call<JsonArray> call=  apiService.submitAttendance(paraAttendance(StaffAttendanceAdapter.staffAttendanceSubmitModels));

                Log.d("TAG", "onClick: "+paraAttendance(StaffAttendanceAdapter.staffAttendanceSubmitModels));
                call.enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        Log.d("TAG", "onResponse: "+response.body());
                        JsonObject jsonObject=response.body().get(0).getAsJsonObject();
                        TextView textView=dialog.findViewById(R.id.dialogtextResponse);
                        Button button=dialog.findViewById(R.id.BtnResponseDialoge);
                        try {
                            textView.setText(jsonObject.get("Status").getAsString());
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
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.d("TAG", "onFailure: "+t.getMessage());
                    }
                });
            }
        });
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
}