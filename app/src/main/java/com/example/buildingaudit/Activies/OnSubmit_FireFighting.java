package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
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

public class OnSubmit_FireFighting extends AppCompatActivity {
    TextView userName,schoolAddress,schoolName;
    ApplicationController applicationController;
        EditText edtFireFightTraining,edtFireFightRenewalStatus,edtFireFightWorkingStatus,edtFireFightingInstallationYear,edtFireFightAvailabelty;
        TextView edtrenewalDate;
        RecyclerView recyclerViewFireFightningoNsUB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_fire_fighting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Dialog dialog2 = new Dialog(this);

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        dialog2.show();
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());

        edtFireFightTraining=findViewById(R.id.edtFireFightTraining);
        edtrenewalDate=findViewById(R.id.edtrenewalDateOnSub);
        edtFireFightRenewalStatus=findViewById(R.id.edtFireFightRenewalStatus);
        edtFireFightWorkingStatus=findViewById(R.id.edtFireFightWorkingStatus);
        edtFireFightAvailabelty=findViewById(R.id.edtFireFightAvailabelty);
        edtFireFightingInstallationYear=findViewById(R.id.edtFireFightingInstallationYear);
        recyclerViewFireFightningoNsUB=findViewById(R.id.recyclerViewFireFightningoNsUB);

        disableEditbox();
        recyclerViewFireFightningoNsUB.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkFireFighting(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"12"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());



                    edtFireFightWorkingStatus.setText(response.body().get(0).get("WorkingStatus").getAsString());

                edtFireFightTraining.setText(response.body().get(0).get("Training").getAsString());
                String[] time=(response.body().get(0).get("RenewalDateStatus").getAsString()).split("T");

                edtrenewalDate.setText(time[0]);
                edtFireFightRenewalStatus.setText(response.body().get(0).get("RenewalStatus").getAsString());
                edtFireFightAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());
                        edtFireFightingInstallationYear.setText(response.body().get(0).get("InstallationYear").getAsString());

                String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_FireFighting.this,StaffPhotoPathList);
                recyclerViewFireFightningoNsUB.setAdapter(onlineImageRecViewAdapter);
                dialog2.dismiss();


            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();

            }
        });


    }

    private void disableEditbox() {
        edtFireFightTraining.setEnabled(false);
                edtrenewalDate.setEnabled(false);
        edtFireFightRenewalStatus.setEnabled(false);
                edtFireFightWorkingStatus.setEnabled(false);
        edtFireFightAvailabelty.setEnabled(false);
                edtFireFightingInstallationYear.setEnabled(false);

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