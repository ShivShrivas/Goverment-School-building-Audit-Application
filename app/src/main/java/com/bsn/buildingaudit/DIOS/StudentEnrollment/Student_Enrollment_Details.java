package com.bsn.buildingaudit.DIOS.StudentEnrollment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.Student_PresenceAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.StudentEnrollmentListModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student_Enrollment_Details extends AppCompatActivity {
    Student_PresenceAdapter adapter;
    RecyclerView recyclerViewStudentpresence;
    ApplicationController applicationController;
    Button btnApprovalStudentpresence,btnRejectStudentpresence;
    ArrayList<StudentEnrollmentListModel> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_enrollment_details);
        applicationController= (ApplicationController) getApplication();


        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        recyclerViewStudentpresence=findViewById(R.id.recyclerViewStudentpresence);
        btnRejectStudentpresence=findViewById(R.id.btnRejectStudentpresence);
        btnApprovalStudentpresence=findViewById(R.id.btnApprovalStudentpresence);
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID",applicationController.getDiosLocalSchoolId());
        jsonObject.addProperty("PeriodID",applicationController.getPeriodID());

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Log.d("TAG", "onCreate: "+jsonObject);
        Call<List<StudentEnrollmentListModel>> call=apiService.getStudentEnrollmentDetails(jsonObject);
        call.enqueue(new Callback<List<StudentEnrollmentListModel>>() {
            @Override
            public void onResponse(Call<List<StudentEnrollmentListModel>> call, Response<List<StudentEnrollmentListModel>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                    arrayList.addAll(response.body());

                adapter=new Student_PresenceAdapter(Student_Enrollment_Details.this,arrayList);
                recyclerViewStudentpresence.setLayoutManager(new LinearLayoutManager(Student_Enrollment_Details.this));
                recyclerViewStudentpresence.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<StudentEnrollmentListModel>> call, Throwable t) {

            }
        });



        ArrayList<String> myImageNameList = new ArrayList<>();
        myImageNameList.add("All things are in good position");
        myImageNameList.add("some things are in good position");
        myImageNameList.add("most of things are in good position");
        myImageNameList.add("noting is in good position but data is correct");


        ArrayList<String> myImageNameList2 = new ArrayList<>();
        myImageNameList2.add("All things are not in good position");
        myImageNameList2.add("some things are not in good position");
        myImageNameList2.add("most of things are not in good position");
        myImageNameList2.add("All things are in good position but data is not correct");
        btnApprovalStudentpresence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticFunctions.showDialogApprove(Student_Enrollment_Details.this,myImageNameList);
            }
        });


        btnRejectStudentpresence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticFunctions.showDialogReject(Student_Enrollment_Details.this, myImageNameList2);
            }
        });
    }

}
