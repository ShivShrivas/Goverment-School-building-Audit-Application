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

public class OnSubmit_BioMetricDetails extends AppCompatActivity {
    RecyclerView recyclerViewBioMetricOnSubmit;
    ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName;
    EditText edtBioMetricMachinecountOnSubmit,edtBioMetricMachineAvailabelty,edtBioMetricInstallationYear,edtuserbiometricStaff,edtuserbiometricStudent,edtBiometricWorkingStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_bio_metric_details);

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
        recyclerViewBioMetricOnSubmit=findViewById(R.id.recyclerViewBioMetricOnSubmit);
        edtBioMetricMachinecountOnSubmit=findViewById(R.id.edtBioMetricMachinecountOnSubmit);

        edtBioMetricMachineAvailabelty=findViewById(R.id.edtBioMetricMachineAvailabelty);
                edtBioMetricInstallationYear=findViewById(R.id.edtBioMetricInstallationYear);
        edtuserbiometricStaff=findViewById(R.id.edtuserbiometricStaff);
                edtuserbiometricStudent=findViewById(R.id.edtuserbiometricStudent);
                edtBiometricWorkingStatus=findViewById(R.id.edtBiometricWorkingStatus);

                disableEditbox();


        recyclerViewBioMetricOnSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkBioMetricDetails(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"9"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());

                edtBioMetricMachineAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());
                        edtBioMetricInstallationYear.setText(response.body().get(0).get("InstallationYear").getAsString());
                edtuserbiometricStaff.setText(response.body().get(0).get("BiometricUseStaff").getAsString());
                        edtuserbiometricStudent.setText(response.body().get(0).get("BiometricUseStudent").getAsString());
                edtBioMetricMachinecountOnSubmit.setText(response.body().get(0).get("NoOfMachines").getAsString());
                    edtBiometricWorkingStatus.setText(response.body().get(0).get("WorkingStatus").getAsString());
                String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_BioMetricDetails.this,StaffPhotoPathList);
                recyclerViewBioMetricOnSubmit.setAdapter(onlineImageRecViewAdapter);


            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });

    }

    private void disableEditbox() {
        edtBioMetricMachineAvailabelty.setEnabled(false);
                edtBioMetricInstallationYear.setEnabled(false);
        edtuserbiometricStaff.setEnabled(false);
                edtuserbiometricStudent.setEnabled(false);
        edtBiometricWorkingStatus.setEnabled(false);

        edtBioMetricMachineAvailabelty.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                edtBioMetricInstallationYear.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        edtuserbiometricStaff.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                edtuserbiometricStudent.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        edtBiometricWorkingStatus.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

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