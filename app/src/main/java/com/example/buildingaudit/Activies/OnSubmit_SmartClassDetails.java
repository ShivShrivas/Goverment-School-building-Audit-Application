package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class OnSubmit_SmartClassDetails extends AppCompatActivity {
RecyclerView smartClassONSubmitRecView;
RecyclerView recyclerViewSmartClassONSubmit;
    ApplicationController applicationController;
    private TextView schoolAddress,schoolName;

    EditText edtsmartClassAvailabilty,edtDigitalBoardSmartClass,edtLearningContentSmartClass,edtProjectorSmartClass,edtTeacherAvailbilitySmartClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_smart_class_details);
        applicationController= (ApplicationController) getApplication();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        schoolName=findViewById(R.id.schoolName);
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        edtsmartClassAvailabilty=findViewById(R.id.edtsmartClassAvailabilty);
        edtDigitalBoardSmartClass=findViewById(R.id.edtDigitalBoardSmartClass);
        edtLearningContentSmartClass=findViewById(R.id.edtLearningContentSmartClass);
        edtProjectorSmartClass=findViewById(R.id.edtProjectorSmartClass);
        edtTeacherAvailbilitySmartClass=findViewById(R.id.edtTeacherAvailbilitySmartClass);
        recyclerViewSmartClassONSubmit=findViewById(R.id.recyclerViewSmartClassONSubmit);

        recyclerViewSmartClassONSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        disableEditBox();
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();

        Call<List<JsonObject>> call=apiService.checkSmartClassDetails(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"5"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                edtsmartClassAvailabilty.setText(response.body().get(0).get("").getAsString());
                        edtDigitalBoardSmartClass.setText(response.body().get(0).get("").getAsString());
                edtLearningContentSmartClass.setText(response.body().get(0).get("").getAsString());
                        edtProjectorSmartClass.setText(response.body().get(0).get("").getAsString());
                edtTeacherAvailbilitySmartClass.setText(response.body().get(0).get("").getAsString());
                String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_SmartClassDetails.this,StaffPhotoPathList);
                recyclerViewSmartClassONSubmit.setAdapter(onlineImageRecViewAdapter);



            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });


    }

    private void disableEditBox() {
        edtsmartClassAvailabilty.setEnabled(false);
                edtDigitalBoardSmartClass.setEnabled(false);
        edtLearningContentSmartClass.setEnabled(false);
                edtProjectorSmartClass.setEnabled(false);
        edtTeacherAvailbilitySmartClass.setEnabled(false);
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