package com.bsn.buildingaudit.DIOS.CampusBeautification;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.TreeDetailsAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.CampusPlantationDetalsModel;
import com.bsn.buildingaudit.Model.TreeData;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Campus_Beautification_Details extends AppCompatActivity {
ApplicationController applicationController;
TreeDetailsAdapter adapter;
RecyclerView treeDetailsRecview;
TextView spinnerDisplayBoard,spinnerEcoClub,spinnerAvailabilityDustbin,spinnerWallPainting,spinnerPlant1,survivedTree
        ,targetedPlantation,PlantED;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_beautification_details);
        applicationController= (ApplicationController) getApplication();
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        treeDetailsRecview=findViewById(R.id.treeDetailsRecview);
        spinnerDisplayBoard=findViewById(R.id.spinnerDisplayBoard);
        spinnerEcoClub=findViewById(R.id.spinnerEcoClub);
        spinnerAvailabilityDustbin=findViewById(R.id.spinnerAvailabilityDustbin);
        spinnerWallPainting=findViewById(R.id.spinnerWallPainting);
        spinnerPlant1=findViewById(R.id.spinnerPlant1);
        PlantED=findViewById(R.id.PlantED);
        targetedPlantation=findViewById(R.id.targetedPlantation);
        survivedTree=findViewById(R.id.survivedTree);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        treeDetailsRecview.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID","2033");
        jsonObject.addProperty("PeriodID",applicationController.getPeriodID());
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<CampusPlantationDetalsModel> call=apiService.getPlantationDetals(jsonObject);
        call.enqueue(new Callback<CampusPlantationDetalsModel>() {
            @Override
            public void onResponse(Call<CampusPlantationDetalsModel> call, Response<CampusPlantationDetalsModel> response) {
                CampusPlantationDetalsModel campusPlantationDetalsModel=response.body();
                spinnerPlant1.setText(campusPlantationDetalsModel.getPlantation().toString());
                spinnerWallPainting.setText(campusPlantationDetalsModel.getWallslogan().toString());
                spinnerAvailabilityDustbin.setText(campusPlantationDetalsModel.getDustbins().toString());
                spinnerEcoClub.setText(campusPlantationDetalsModel.getEchoclub().toString());
                spinnerDisplayBoard.setText(campusPlantationDetalsModel.getDisplaynotice().toString());
                survivedTree.setText(campusPlantationDetalsModel.getSurvive().toString());
                targetedPlantation.setText(campusPlantationDetalsModel.getPlanttarget().toString());
                PlantED.setText(campusPlantationDetalsModel.getPlanted().toString());

                ArrayList<TreeData> arrayList=new ArrayList<>();
                arrayList.addAll(campusPlantationDetalsModel.getTreeDatas());
                adapter =new TreeDetailsAdapter(Campus_Beautification_Details.this,arrayList);
                treeDetailsRecview.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<CampusPlantationDetalsModel> call, Throwable t) {

            }
        });

    }
}