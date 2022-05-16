package com.example.buildingaudit.Activies;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    TextView userName,schoolAddress,schoolName,editBioMetricDetails;
    ConstraintLayout layoutBioMetric;
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
        recyclerViewBioMetricOnSubmit=findViewById(R.id.recyclerViewBioMetricOnSubmit);
        layoutBioMetric=findViewById(R.id.layoutBioMetric);
        edtBioMetricMachinecountOnSubmit=findViewById(R.id.edtBioMetricMachinecountOnSubmit);

        edtBioMetricMachineAvailabelty=findViewById(R.id.edtBioMetricMachineAvailabelty);
                edtBioMetricInstallationYear=findViewById(R.id.edtBioMetricInstallationYear);
        edtuserbiometricStaff=findViewById(R.id.edtuserbiometricStaff);
        editBioMetricDetails=findViewById(R.id.editBioMetricDetails);
                edtuserbiometricStudent=findViewById(R.id.edtuserbiometricStudent);
                edtBiometricWorkingStatus=findViewById(R.id.edtBiometricWorkingStatus);

                disableEditbox();

        editBioMetricDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_BioMetricDetails.this,UpdateDetailsBioMetric.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
        recyclerViewBioMetricOnSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkBioMetricDetails(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"9"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                if (response.body().get(0).get("Availabilty").getAsString().equals("No")){
                    layoutBioMetric.setVisibility(View.GONE);
                    dialog2.dismiss();
                    edtBioMetricMachineAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());


                }else {
                    edtBioMetricMachineAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());
                    edtBioMetricInstallationYear.setText(response.body().get(0).get("InstallationYear").getAsString());
                    edtuserbiometricStaff.setText(response.body().get(0).get("BiometricUseStaff").getAsString());
                    edtuserbiometricStudent.setText(response.body().get(0).get("BiometricUseStudent").getAsString());
                    edtBioMetricMachinecountOnSubmit.setText(response.body().get(0).get("NoOfMachines").getAsString());
                    edtBiometricWorkingStatus.setText(response.body().get(0).get("WorkingStatus").getAsString());
                    try {
                        String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                        OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_BioMetricDetails.this,StaffPhotoPathList);
                        recyclerViewBioMetricOnSubmit.setAdapter(onlineImageRecViewAdapter);

                    }catch (Exception e){
                        recyclerViewBioMetricOnSubmit.setVisibility(View.GONE);
                    }
                    dialog2.dismiss();


                }


            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
dialog2.dismiss();
                Toast.makeText(OnSubmit_BioMetricDetails.this, "Something went wrong please try again!!", Toast.LENGTH_SHORT).show();
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