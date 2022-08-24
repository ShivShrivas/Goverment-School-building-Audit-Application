package com.bsn.buildingaudit.DIOS.SubjectWiseSyllabus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.bsn.buildingaudit.Adapters.TabAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.SubjectWiseSyllabusModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Syllabus_Details_page extends AppCompatActivity {
    Button btnApprovalStudentpresence,btnRejectStudentpresence;
    private TabAdapter adapter;
    ApplicationController applicationController;
ArrayList<SubjectWiseSyllabusModel> arrayList=new ArrayList<>();
    private TabLayout tab;
    Intent i;
    String ParentID;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus_details_page);
        applicationController= (ApplicationController) getApplication();
        i=getIntent();
        ParentID=i.getStringExtra("ParamId");
        viewPager =  findViewById(R.id.viewPager);
        btnRejectStudentpresence=findViewById(R.id.btnRejectStaffSanction);
        btnApprovalStudentpresence=findViewById(R.id.btnApprovalStaffSanction);
        tab = findViewById(R.id.tabLayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID",applicationController.getSchoolId());
        jsonObject.addProperty("PeriodID",applicationController.getPeriodID());
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<ArrayList<SubjectWiseSyllabusModel>> call= apiService.getSubjectSyllabus(jsonObject);
        call.enqueue(new Callback<ArrayList<SubjectWiseSyllabusModel>>() {
            @Override
            public void onResponse(Call<ArrayList<SubjectWiseSyllabusModel>> call, Response<ArrayList<SubjectWiseSyllabusModel>> response) {
                arrayList=response.body();
                ArrayList<String> arrayList1=new ArrayList<>();
                HashSet hs = new HashSet();
                for (int l = 0; l <arrayList.size(); l++) {
                    hs.add(arrayList.get(l).getClassName());
                }
                arrayList1.addAll(hs);
             // demoArrayList= name of arrayList from which u want to remove duplicates

//                subjectEleventhToTwelveth.clear();
//                subjectEleventhToTwelveth.addAll(hs);

                for (int k = 0; k <arrayList1.size(); k++) {
                    tab.addTab(tab.newTab().setText( arrayList1.get(k)));
                }
                adapter = new TabAdapter
                        (getSupportFragmentManager(), tab.getTabCount(),arrayList,arrayList1);
                viewPager.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<SubjectWiseSyllabusModel>> call, Throwable t) {

            }
        });
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        btnApprovalStudentpresence.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(Syllabus_Details_page.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });


        btnRejectStudentpresence.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(Syllabus_Details_page.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
    }
}

