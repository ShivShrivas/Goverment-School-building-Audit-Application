package com.bsn.buildingaudit.DIOS.reciept_and_expenditure;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.RecieptAndExpenditureAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.GetReciptExpDetailsModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Reciept_And_Expenditures extends AppCompatActivity {
RecyclerView recyclerViewRecieptAndExpenditure;
ArrayList<GetReciptExpDetailsModel> arrayList=new ArrayList<>();
ApplicationController applicationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciept_and_expenditures);
        applicationController= (ApplicationController) getApplication();
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID",applicationController.getSchoolId());
        jsonObject.addProperty("PeriodID",applicationController.getPeriodID());
       
        recyclerViewRecieptAndExpenditure=findViewById(R.id.recyclerViewRecieptAndExpenditure);
        recyclerViewRecieptAndExpenditure.setLayoutManager(new LinearLayoutManager(this));
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<ArrayList<GetReciptExpDetailsModel>> call=apiService.getReciptExpDetails(jsonObject);
        call.enqueue(new Callback<ArrayList<GetReciptExpDetailsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GetReciptExpDetailsModel>> call, Response<ArrayList<GetReciptExpDetailsModel>> response) {
                ArrayList<GetReciptExpDetailsModel> arrayList=response.body();

                recyclerViewRecieptAndExpenditure.setAdapter(new RecieptAndExpenditureAdapter(Reciept_And_Expenditures.this,arrayList));

            }

            @Override
            public void onFailure(Call<ArrayList<GetReciptExpDetailsModel>> call, Throwable t) {

            }
        });



    }
}