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

public class OnSubmit_CycleStand extends AppCompatActivity {
EditText edtCycleStandRepairingStatus,edtCycleStandFunctionalStatus,edtCycyleStandCapacity,edtCycleStand;
    ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName;

    RecyclerView recyclerCycleStandOnSub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_cycle_stand);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        edtCycleStandRepairingStatus=findViewById(R.id.edtCycleStandRepairingStatus);
        edtCycleStandFunctionalStatus=findViewById(R.id.edtCycleStandFunctionalStatus);
        edtCycyleStandCapacity=findViewById(R.id.edtCycyleStandCapacity);
        recyclerCycleStandOnSub=findViewById(R.id.recyclerCycleStandOnSub);
        edtCycleStand=findViewById(R.id.edtCycleStand);
        disableEdiBox();
        recyclerCycleStandOnSub.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkCycleStand(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"21"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+response);
                edtCycleStandRepairingStatus.setText(response.body().get(0).get("RepairingStatus").getAsString());
                        edtCycleStandFunctionalStatus.setText(response.body().get(0).get("FunctionalStatus").getAsString());
                edtCycyleStandCapacity.setText(response.body().get(0).get("CycleCapacity").getAsString());
                        edtCycleStand.setText(response.body().get(0).get("Availability").getAsString());
                String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_CycleStand.this,StaffPhotoPathList);
                recyclerCycleStandOnSub.setAdapter(onlineImageRecViewAdapter);

            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });

    }

    private void disableEdiBox() {
        edtCycleStandRepairingStatus.setEnabled(false);
                edtCycleStandFunctionalStatus.setEnabled(false);
        edtCycyleStandCapacity.setEnabled(false);
                edtCycleStand.setEnabled(false);
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