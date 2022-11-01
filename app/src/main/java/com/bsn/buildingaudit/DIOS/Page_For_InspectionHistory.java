package com.bsn.buildingaudit.DIOS;

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
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.InspectionHistoryAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.AllInspectionDataModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Page_For_InspectionHistory extends AppCompatActivity {
CardView chemistryLabBodyCard;
Button addNewInspectioBtn;
    ApplicationController applicationController;
    RecyclerView inspectionHistoryRecView;
    TextView textView8,schoolAddress,schoolName;
    String localSchoolId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_for_inspection_history);
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
       
        schoolAddress=findViewById(R.id.schoolAddress);
        addNewInspectioBtn=findViewById(R.id.addNewInspectioBtn);
        schoolName=findViewById(R.id.schoolName);
        inspectionHistoryRecView=findViewById(R.id.inspectionHistoryRecView);

        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID",applicationController.getSchoolId());
        jsonObject.addProperty("PeriodID",applicationController.getPeriodID());
       

        Call<ArrayList<AllInspectionDataModel>>  call=apiService.getListOfOldInspection(jsonObject);
        call.enqueue(new Callback<ArrayList<AllInspectionDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<AllInspectionDataModel>> call, Response<ArrayList<AllInspectionDataModel>> response) {
                ArrayList<AllInspectionDataModel> arrayList=response.body();
                inspectionHistoryRecView.setLayoutManager(new LinearLayoutManager(Page_For_InspectionHistory.this));
                inspectionHistoryRecView.setAdapter(new InspectionHistoryAdapter(Page_For_InspectionHistory.this,arrayList));
            }

            @Override
            public void onFailure(Call<ArrayList<AllInspectionDataModel>> call, Throwable t) {

            }
        });


        addNewInspectioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<JsonObject> call=apiService.addnewInspection(jsonObject);
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.body().get("StatusCode").equals("1")){
                            Toast.makeText(Page_For_InspectionHistory.this, "New Inspection Added", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(Page_For_InspectionHistory.this,Diios_Panel_Under_School_dashboard.class);
                            i.putExtra("InspectionId",response.body().get("Status").toString());
                            startActivity(i);
                        }else{
                            Toast.makeText(Page_For_InspectionHistory.this, "Please complete your previous inspection", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });

            }
        });

        textView8=findViewById(R.id.textView8);
        textView8.setText(getString(R.string.welcome_string)+"\n \t\t\t"+applicationController.getUsername());


    }
}