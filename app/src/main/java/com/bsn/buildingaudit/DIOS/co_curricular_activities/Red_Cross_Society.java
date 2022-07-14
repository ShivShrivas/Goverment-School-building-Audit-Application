package com.bsn.buildingaudit.DIOS.co_curricular_activities;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.RedCrossModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Red_Cross_Society extends AppCompatActivity {
    ApplicationController applicationController;
    TextView SCHOOLBALANCEAMT,DISTRICTLAMT,CURRENTDEPAMTWITHREGNO,AMTAVAIINACC,PROJECTACTIVITYSTATUS,
            INCOMEEXPSTATUS,STOCKREGISTERSTATUS,TRAININGCAMPSTATUS,OFFICEFACILITIESSTATUS,FORMATIONSTATUS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_cross_society);
        applicationController= (ApplicationController) getApplication();
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        SCHOOLBALANCEAMT=findViewById(R.id.SCHOOLBALANCEAMT);
        DISTRICTLAMT=findViewById(R.id.DISTRICTLAMT);
        CURRENTDEPAMTWITHREGNO=findViewById(R.id.CURRENTDEPAMTWITHREGNO);
        AMTAVAIINACC=findViewById(R.id.AMTAVAIINACC);
        PROJECTACTIVITYSTATUS=findViewById(R.id.PROJECTACTIVITYSTATUS);
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
    }
}