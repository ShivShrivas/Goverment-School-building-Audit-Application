package com.bsn.buildingaudit.DIOS;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.DiosSubMenuDashboardAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.GetDashboardMenuDataModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dios_InnerDashboard extends AppCompatActivity {
    public static ApplicationController applicationController;
    TextView textView8,schoolAddress,schoolName;
    RecyclerView diosSubMenuRecView;
    String ParentID,InspectionId;
    DiosSubMenuDashboardAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dios_inner_dashboard);
        applicationController= (ApplicationController) getApplication();
        Window window = getWindow();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Intent i=getIntent();
        ParentID=i.getStringExtra("ParentID");
        InspectionId=i.getStringExtra("InspectionId");
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        textView8=findViewById(R.id.textView8);
        textView8.setText(getString(R.string.welcome_string)+"\n \t\t\t"+applicationController.getUsername());
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        diosSubMenuRecView=findViewById(R.id.diosSubMenuRecView);
        diosSubMenuRecView.setLayoutManager(new LinearLayoutManager(this));
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("UserRole",applicationController.getUsertype());
        jsonObject.addProperty("ParentID",ParentID);
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
       
        Call<ArrayList<GetDashboardMenuDataModel>> call=apiService.getDiosSubmenuCardsData(jsonObject);
        call.enqueue(new Callback<ArrayList<GetDashboardMenuDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GetDashboardMenuDataModel>> call, Response<ArrayList<GetDashboardMenuDataModel>> response) {
                ArrayList<GetDashboardMenuDataModel> arrayList=response.body();
               
                adapter=new DiosSubMenuDashboardAdapter(Dios_InnerDashboard.this,arrayList,InspectionId);
                diosSubMenuRecView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<GetDashboardMenuDataModel>> call, Throwable t) {

            }
        });



    }
}