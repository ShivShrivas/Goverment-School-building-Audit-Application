package com.bsn.buildingaudit.Activies;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.OnlineImageRecViewAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSubmit_FireFighting extends AppCompatActivity {
    TextView userName,schoolAddress,schoolName;
    ApplicationController applicationController;
        EditText edtFireFightTraining,edtFireFightRenewalStatus,edtFireFightWorkingStatus,edtFireFightingInstallationYear,edtFireFightAvailabelty;
        TextView edtrenewalDate,editFirefightingDetails;
    EditText edtNotWorkingFF,edtWorkingFF,edtTotalFF;
    LinearLayout linearLayout21;
    String Type;
        ConstraintLayout constraintLayout30;
        RecyclerView recyclerViewFireFightningoNsUB;
TextView uploadtextFireFighting;
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
        Intent i=getIntent();
        Type=i.getStringExtra("Type");

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        dialog2.show();
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        linearLayout21=findViewById(R.id.linearLayout21);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        edtTotalFF=findViewById(R.id.edtTotalFF);
        edtWorkingFF=findViewById(R.id.edtWorkingFF);
        edtNotWorkingFF=findViewById(R.id.edtNotWorkingFF);
        edtFireFightTraining=findViewById(R.id.edtFireFightTraining);
        uploadtextFireFighting=findViewById(R.id.uploadtextFireFighting);
        editFirefightingDetails=findViewById(R.id.editFirefightingDetails);
        edtrenewalDate=findViewById(R.id.edtrenewalDateOnSub);
        edtFireFightRenewalStatus=findViewById(R.id.edtFireFightRenewalStatus);
        edtFireFightWorkingStatus=findViewById(R.id.edtFireFightWorkingStatus);
        edtFireFightAvailabelty=findViewById(R.id.edtFireFightAvailabelty);
        edtFireFightingInstallationYear=findViewById(R.id.edtFireFightingInstallationYear);
        recyclerViewFireFightningoNsUB=findViewById(R.id.recyclerViewFireFightningoNsUB);
        constraintLayout30=findViewById(R.id.constraintLayout30);
        if (Type.equals("D")){
            linearLayout21.setVisibility(View.VISIBLE);
        }
        editFirefightingDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_FireFighting.this,UpdateDetailsFireFighting.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
        disableEditbox();
        recyclerViewFireFightningoNsUB.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkFireFighting(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"12"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    if (Type.equals("D")){
                        editFirefightingDetails.setVisibility(View.GONE);

                    }else{

                        editFirefightingDetails.setVisibility(View.VISIBLE);
                    }
                }                if(response.body().get(0).get("Availabilty").getAsString().equals("No")){
                    constraintLayout30.setVisibility(View.GONE);
                    edtFireFightAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());

                    dialog2.dismiss();
                }else{
                    edtFireFightWorkingStatus.setText(response.body().get(0).get("WorkingStatus").getAsString());

                    int totalRo=Integer.parseInt(response.body().get(0).get("NonWorkingCount").getAsString())+Integer.parseInt(response.body().get(0).get("WorkingCount").getAsString());

                    edtNotWorkingFF.setText(response.body().get(0).get("NonWorkingCount").getAsString());
                    edtWorkingFF.setText(response.body().get(0).get("WorkingCount").getAsString());
                    edtTotalFF.setText(String.valueOf(totalRo));
                    edtFireFightTraining.setText(response.body().get(0).get("Training").getAsString());
                    String[] time=(response.body().get(0).get("RenewalDateStatus").getAsString()).split("T");

                    edtrenewalDate.setText(time[0]);
                    edtFireFightRenewalStatus.setText(response.body().get(0).get("RenewalStatus").getAsString());
                    edtFireFightAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());
                    edtFireFightingInstallationYear.setText(response.body().get(0).get("InstallationYear").getAsString());
try {
    String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
    OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_FireFighting.this,StaffPhotoPathList, applicationController.getUsertype());
    recyclerViewFireFightningoNsUB.setAdapter(onlineImageRecViewAdapter);
}catch (Exception e){
    recyclerViewFireFightningoNsUB.setVisibility(View.GONE);
    uploadtextFireFighting.setVisibility(View.GONE);
}

                    dialog2.dismiss();

                }





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
        edtTotalFF.setEnabled(false);
        edtWorkingFF.setEnabled(false);
        edtNotWorkingFF.setEnabled(false);

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