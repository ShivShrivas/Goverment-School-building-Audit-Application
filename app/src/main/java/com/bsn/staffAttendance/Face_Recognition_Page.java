package com.bsn.staffAttendance;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.FacerecognizationStaffAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.AttendanceStaff;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Face_Recognition_Page extends AppCompatActivity {
        RecyclerView recyclerViewStaffFaceRecognization;
    Toolbar toolbar;
    ApplicationController applicationController;
    String formattedDate,formattedDate1;
    FacerecognizationStaffAdapter adapter;
    TextView userName,schoolAddress,schoolName,txtdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_recognition_page);
        toolbar =  findViewById(R.id.toolbar);
        applicationController= (ApplicationController) getApplication();
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        txtdate=findViewById(R.id.txtdate);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        formattedDate = df.format(c);
        recyclerViewStaffFaceRecognization=findViewById(R.id.recyclerViewStaffFaceRecognization);
        recyclerViewStaffFaceRecognization.setLayoutManager(new LinearLayoutManager(this));
        getStaffData();
    }

    private void getStaffData() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Log.d("TAG", "getStaffData: "+paraGetStaff(formattedDate,applicationController.getSchoolId()));
        Call<List<AttendanceStaff>> listCall=apiService.getStaff(paraGetStaff(formattedDate,applicationController.getSchoolId()));
       listCall.enqueue(new Callback<List<AttendanceStaff>>() {
           @Override
           public void onResponse(Call<List<AttendanceStaff>> call, Response<List<AttendanceStaff>> response) {
               List<AttendanceStaff> attendanceStaff=response.body();
               Log.d("TAG", "onResponse: "+attendanceStaff);
               adapter= new FacerecognizationStaffAdapter(Face_Recognition_Page.this,attendanceStaff);
               recyclerViewStaffFaceRecognization.setAdapter(adapter);
               adapter.notifyDataSetChanged();
           }

           @Override
           public void onFailure(Call<List<AttendanceStaff>> call, Throwable t) {

           }
       });

    }


    private JsonObject paraGetStaff(String formattedDate, String schoolId) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("AttendenceDate",formattedDate);
        jsonObject.addProperty("SchoolId",schoolId);

        return jsonObject;
    }
}