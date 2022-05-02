package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.buildingaudit.Adapters.OnlineImageRecViewAdapter;
import com.example.buildingaudit.ApplicationController;
import com.example.buildingaudit.R;
import com.example.buildingaudit.RetrofitApi.ApiService;
import com.example.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSubmit_OpenGYmDetails extends AppCompatActivity {
    TextView userName,schoolAddress,schoolName;
    EditText edtGymAvailabelty,gymWorkingStatus,edtGymArea;
    RecyclerView recyclerViewGymOnSubmit;
    ApplicationController applicationController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_open_gym_details);
        applicationController= (ApplicationController) getApplication();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        edtGymAvailabelty=findViewById(R.id.edtGymAvailabelty);
        gymWorkingStatus=findViewById(R.id.gymWorkingStatus);
        edtGymArea=findViewById(R.id.edtGymArea);
        recyclerViewGymOnSubmit=findViewById(R.id.recyclerViewGymOnSubmit);
        recyclerViewGymOnSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        disabledEdtBox();
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();

        Call<List<JsonObject>> call=apiService.checkGymDetails(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"6"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                    Log.d("TAG", "onResponse: "+response.body()+"///////");

                    edtGymArea.setText(response.body().get(0).get("Areainsqmtr").getAsString());
                    edtGymAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());
                    if (response.body().get(0).get("WorkingStatus").getAsString().equals("NF")){
                        gymWorkingStatus.setText("Not Functional");
                    }else if(response.body().get(0).get("WorkingStatus").getAsString().equals("F")){
                        gymWorkingStatus.setText("Functional");

                    }
                String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_OpenGYmDetails.this,StaffPhotoPathList);
                recyclerViewGymOnSubmit.setAdapter(onlineImageRecViewAdapter);



            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void disabledEdtBox() {
        edtGymAvailabelty.setEnabled(false);
        gymWorkingStatus.setEnabled(false);
        edtGymArea.setEnabled(false);
    }

    private JsonObject paraGetDetails2(String action, String schoolId, String periodId, String paramId) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",action);
        jsonObject.addProperty("ParamId",paramId);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodId);
        return jsonObject;
    }
}