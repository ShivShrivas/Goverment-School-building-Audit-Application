package com.bsn.buildingaudit.DIOS.SubjectWiseSyllabus;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bsn.buildingaudit.Adapters.TabAdapter;
import com.bsn.buildingaudit.ApplicationController;
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

    private TabAdapter adapter;
    ApplicationController applicationController;
ArrayList<SubjectWiseSyllabusModel> arrayList=new ArrayList<>();
    private TabLayout tab;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus_details_page);
        applicationController= (ApplicationController) getApplication();
        viewPager =  findViewById(R.id.viewPager);
        tab = findViewById(R.id.tabLayout);



        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID","2033");
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
    }
}

