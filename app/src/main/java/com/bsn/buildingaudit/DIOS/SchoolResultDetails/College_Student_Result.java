package com.bsn.buildingaudit.DIOS.SchoolResultDetails;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.StudentCollegeWiseResultAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.CurrentYearResultModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class College_Student_Result extends AppCompatActivity {
RecyclerView recyclerViewStudent_college_result;
    ApplicationController applicationController;
StudentCollegeWiseResultAdapter adapter;
Button approveBtnCollegeResult,rejectBtnCollegeResult;
    ArrayList<CurrentYearResultModel> currentYearResultL=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_student_result);
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
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID","2033");
        jsonObject.addProperty("PeriodID",applicationController.getPeriodID());
        recyclerViewStudent_college_result=findViewById(R.id.recyclerViewStudent_college_result);
        rejectBtnCollegeResult=findViewById(R.id.rejectBtnCollegeResult);
        approveBtnCollegeResult=findViewById(R.id.approveBtnCollegeResult);
        recyclerViewStudent_college_result.setLayoutManager(new LinearLayoutManager(this));
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<ArrayList<CurrentYearResultModel>> call=apiService.getCurrentYearResult(jsonObject);
        call.enqueue(new Callback<ArrayList<CurrentYearResultModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CurrentYearResultModel>> call, Response<ArrayList<CurrentYearResultModel>> response) {
                currentYearResultL=response.body();
                adapter=new StudentCollegeWiseResultAdapter(College_Student_Result.this,currentYearResultL);
                recyclerViewStudent_college_result.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ArrayList<CurrentYearResultModel>> call, Throwable t) {

            }
        });



        ArrayList<String> myImageNameList = new ArrayList<>();
        myImageNameList.add("All Given Information is correct");


        ArrayList<String> myImageNameList2 = new ArrayList<>();
        myImageNameList2.add("Student Data is not correct");
        myImageNameList2.add("Toppers Data is not correct");
        myImageNameList2.add("No. of attached All Data is not correct");


        approveBtnCollegeResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticFunctions.showDialogApprove(College_Student_Result.this,myImageNameList);
            }
        });

        rejectBtnCollegeResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticFunctions.showDialogReject(College_Student_Result.this,myImageNameList2);
            }
        });
    }
}