package com.bsn.buildingaudit.SchoolDetailsPages;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.ClassDatum;
import com.bsn.buildingaudit.Model.SchoolDetailsModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class School_Details extends AppCompatActivity {
    ApplicationController applicationController;

   List<ClassDatum> classesArrayList =new ArrayList<>();
   List<SchoolDetailsModel> schoolDetailsList =new ArrayList<>();
RecyclerView recViewForPermittedSubjects,recViewForRunningClasses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_details);
        applicationController= (ApplicationController) getApplication();
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        recViewForRunningClasses=findViewById(R.id.recViewForRunningClasses);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Log.d("TAG", "onCreate: "+paraSchoolRequestJson("2",applicationController.getPeriodID()));
        Call<List<JsonObject>> call=apiService.getSchoolDetailsForDIOS(paraSchoolRequestJson("2",applicationController.getPeriodID()));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());
//                schoolDetailsList=response.body();
//                classesArrayList=schoolDetailsList.get(0).getClassData();
//                recViewForRunningClasses.setLayoutManager(layoutManager);
//                recViewForRunningClasses.setAdapter(new SubjectRecviewAdapter(School_Details.this,classesArrayList));
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });

        ArrayList<String> subjectArray=new ArrayList<>();
        subjectArray.add("Hindi");
        subjectArray.add("ENGLISH");
        subjectArray.add("SANSKRIT");
        subjectArray.add("SCIENCE");
        subjectArray.add("Mathematics");
        subjectArray.add("DRAWING");
        subjectArray.add("HOME SCIENCE");
        subjectArray.add("SOCIAL SCIENCE");
        subjectArray.add("COMPUTER SCIENCE");


        recViewForPermittedSubjects=findViewById(R.id.recViewForPermittedSubjects);


        GridLayoutManager layoutManager1=new GridLayoutManager(this,2);

        recViewForPermittedSubjects.setLayoutManager(layoutManager1);




    }

    private JsonObject paraSchoolRequestJson(String schoolId, String periodID) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID",schoolId);
        jsonObject.addProperty("PeriodID",periodID);
        return jsonObject;
    }
}