package com.bsn.buildingaudit.DIOS;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.DIOSDashboardAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.GetDashboardMenuDataModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.bsn.buildingaudit.SchoolDetailsPages.School_Details;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Diios_Panel_Under_School_dashboard extends AppCompatActivity {
    ApplicationController applicationController;
RecyclerView dashBoardmenuRecview;
TextView textView8,schoolAddress,schoolName;
String localSchoolId;
Intent i;
String InspectionId;
ImageView schoolDetailsBtn;
Button btnDIOSBottom;
DIOSDashboardAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diios_panel_under_school_dashboard);
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
        i=getIntent();
        InspectionId=i.getStringExtra("InspectionId");
        Log.d("TAG", "onCreate: "+localSchoolId);
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        dashBoardmenuRecview=findViewById(R.id.dashBoardmenuRecview);
        btnDIOSBottom=findViewById(R.id.btnDIOSBottom);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        textView8=findViewById(R.id.textView8);
        textView8.setText(getString(R.string.welcome_string)+"\n \t\t\t"+applicationController.getUsername());
        schoolDetailsBtn=findViewById(R.id.schoolDetailsBtn);
        dashBoardmenuRecview.setLayoutManager(new GridLayoutManager(this,3));

        schoolDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Diios_Panel_Under_School_dashboard.this, School_Details.class));
            }
        });
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("UserRole",applicationController.getUsertype());
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        JsonObject jsonObjectForFinalButto=new JsonObject();
        jsonObjectForFinalButto.addProperty("SchoolID",applicationController.getSchoolId());
        jsonObjectForFinalButto.addProperty("PeriodID",applicationController.getPeriodID());
        Call<JsonObject> callForButton=apiService.getFinalButtonStatus(jsonObjectForFinalButto);
        callForButton.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                if (response.body().get("StatusCode").toString().equals("1")){
//                    btnDIOSBottom.setVisibility(View.VISIBLE);
//                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
        btnDIOSBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Diios_Panel_Under_School_dashboard.this,DIOS_Final_Submission.class));
            }
        });
        Call<ArrayList<GetDashboardMenuDataModel>> call=apiService.getDiosDashboardCardsData(jsonObject);
        call.enqueue(new Callback<ArrayList<GetDashboardMenuDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GetDashboardMenuDataModel>> call, Response<ArrayList<GetDashboardMenuDataModel>> response) {
                ArrayList<GetDashboardMenuDataModel> arrayList=response.body();
                adapter=new DIOSDashboardAdapter(Diios_Panel_Under_School_dashboard.this,arrayList,InspectionId);
                dashBoardmenuRecview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<GetDashboardMenuDataModel>> call, Throwable t) {

            }
        });


    }
}