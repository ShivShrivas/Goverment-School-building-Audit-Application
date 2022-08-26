package com.bsn.buildingaudit.DIOS.co_curricular_activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.ApproveRejectRemarksDataModel;
import com.bsn.buildingaudit.Model.Datum;
import com.bsn.buildingaudit.Model.RedCrossModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Red_Cross_Society extends AppCompatActivity {
    ApplicationController applicationController;
    Intent i;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();

    String ParentID;
    Button redCrossApproveBtn,redCrossRejectBtn;
    TextView SCHOOLBALANCEAMT,DISTRICTLAMT,CURRENTDEPAMTWITHREGNO,AMTAVAIINACC,PROJECTACTIVITYSTATUS,
            INCOMEEXPSTATUS,STOCKREGISTERSTATUS,TRAININGCAMPSTATUS,OFFICEFACILITIESSTATUS,FORMATIONSTATUS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_cross_society);
        applicationController= (ApplicationController) getApplication();
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        i=getIntent();
        ParentID=i.getStringExtra("ParamId");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        SCHOOLBALANCEAMT=findViewById(R.id.SCHOOLBALANCEAMT);
        DISTRICTLAMT=findViewById(R.id.DISTRICTLAMT);
        CURRENTDEPAMTWITHREGNO=findViewById(R.id.CURRENTDEPAMTWITHREGNO);
        AMTAVAIINACC=findViewById(R.id.AMTAVAIINACC);
        PROJECTACTIVITYSTATUS=findViewById(R.id.PROJECTACTIVITYSTATUS);
        redCrossRejectBtn=findViewById(R.id.redCrossRejectBtn);
        redCrossApproveBtn=findViewById(R.id.redCrossApproveBtn);
        INCOMEEXPSTATUS=findViewById(R.id.INCOMEEXPSTATUS);
        STOCKREGISTERSTATUS=findViewById(R.id.STOCKREGISTERSTATUS);
        TRAININGCAMPSTATUS=findViewById(R.id.TRAININGCAMPSTATUS);
        OFFICEFACILITIESSTATUS=findViewById(R.id.OFFICEFACILITIESSTATUS);
        FORMATIONSTATUS=findViewById(R.id.FORMATIONSTATUS);
        setSupportActionBar(toolbar);
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID",applicationController.getSchoolId());
        jsonObject.addProperty("PeriodID",applicationController.getPeriodID());
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        JsonObject json =new JsonObject();
        json.addProperty("SchoolID",applicationController.getSchoolId());
        json.addProperty("PeriodID",applicationController.getPeriodID());
        json.addProperty("ParamId",ParentID);
        Call<ApproveRejectRemarksDataModel> callz=apiService.getpriviousSubmittedDataByDIOS(json);
        callz.enqueue(new Callback<ApproveRejectRemarksDataModel>() {
            @Override
            public void onResponse(Call<ApproveRejectRemarksDataModel> call, Response<ApproveRejectRemarksDataModel> response) {
                Log.d("TAG", "onResponse: "+response.body());
                ApproveRejectRemarksDataModel approveRejectRemarksDataModel=response.body();
                Log.d("TAG", "onResponse: "+approveRejectRemarksDataModel.getStatus());
                if (!approveRejectRemarksDataModel.getStatus().equals("No Record Found")){
                    Toast.makeText(Red_Cross_Society.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                    arrayListRemarks=approveRejectRemarksDataModel.getData();


                }
            }

            @Override
            public void onFailure(Call<ApproveRejectRemarksDataModel> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });
        Call<RedCrossModel> call=apiService.getRedCrossSociety(jsonObject);
        call.enqueue(new Callback<RedCrossModel>() {
            @Override
            public void onResponse(Call<RedCrossModel> call, Response<RedCrossModel> response) {
                RedCrossModel redCrossModel=response.body();
                SCHOOLBALANCEAMT.setText(redCrossModel.getSchoolbalanceamt()==null?"0":redCrossModel.getSchoolbalanceamt().toString());
                DISTRICTLAMT.setText(redCrossModel.getDistrictlamt()==null?"0":redCrossModel.getDistrictlamt().toString());
                        CURRENTDEPAMTWITHREGNO.setText(redCrossModel.getCurrentdepamtwithregno()==null?"No":redCrossModel.getCurrentdepamtwithregno().toString());
                AMTAVAIINACC.setText(redCrossModel.getAmtavaiinacc()==null?"No":redCrossModel.getAmtavaiinacc().toString());
                        PROJECTACTIVITYSTATUS.setText(redCrossModel.getProjectactivitystatus()==null?"No":redCrossModel.getProjectactivitystatus().toString());
                INCOMEEXPSTATUS.setText(redCrossModel.getIncomeexpstatus()==null?"No":redCrossModel.getIncomeexpstatus().toString());
                        STOCKREGISTERSTATUS.setText(redCrossModel.getStockregisterstatus()==null?"No":redCrossModel.getStockregisterstatus().toString());
                TRAININGCAMPSTATUS.setText(redCrossModel.getTrainingcampstatus()==null?"No":redCrossModel.getTrainingcampstatus().toString());
                        OFFICEFACILITIESSTATUS.setText(redCrossModel.getOfficefacilitiesstatus()==null?"No":redCrossModel.getOfficefacilitiesstatus().toString());
                FORMATIONSTATUS.setText(redCrossModel.getFormationstatus()==null?"No":redCrossModel.getFormationstatus().toString());

            }

            @Override
            public void onFailure(Call<RedCrossModel> call, Throwable t) {

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        redCrossApproveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: "+ParentID);
                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InsType","A");
                jsonObject1.addProperty("ParamId",ParentID);
                Call<ArrayList<ApproveRejectRemarkModel>> call1=apiService.getApproveRejectRemark(jsonObject1);
                call1.enqueue(new Callback<ArrayList<ApproveRejectRemarkModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ApproveRejectRemarkModel>> call, Response<ArrayList<ApproveRejectRemarkModel>> response) {
                        ArrayList<ApproveRejectRemarkModel> arrayList=response.body();
                        StaticFunctions.showDialogApprove(Red_Cross_Society.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

        redCrossRejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: "+ParentID);
                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InsType","R");
                jsonObject1.addProperty("ParamId",ParentID);
                Call<ArrayList<ApproveRejectRemarkModel>> call1=apiService.getApproveRejectRemark(jsonObject1);
                call1.enqueue(new Callback<ArrayList<ApproveRejectRemarkModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ApproveRejectRemarkModel>> call, Response<ArrayList<ApproveRejectRemarkModel>> response) {
                        ArrayList<ApproveRejectRemarkModel> arrayList=response.body();
                        StaticFunctions.showDialogReject(Red_Cross_Society.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });


    }
}