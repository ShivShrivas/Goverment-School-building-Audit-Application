package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.buildingaudit.Adapters.OnlineImageRecViewAdapter;
import com.example.buildingaudit.ApplicationController;
import com.example.buildingaudit.R;
import com.example.buildingaudit.RetrofitApi.ApiService;
import com.example.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSubmit_RainHarvesting extends AppCompatActivity {
EditText edtRainharvestingAvailabilty,edtRainHavestingWorkStatus;
    ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName;
    RecyclerView recyclerViewRAinHarveOnSub;
    LinearLayout workingStatusLayout,linearLayout5;

TextView workingStatusHearOnSub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_rain_harvesting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Dialog dialog2 = new Dialog(this);

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        dialog2.show();
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        edtRainharvestingAvailabilty=findViewById(R.id.edtRainharvestingAvailabilty);
        edtRainHavestingWorkStatus=findViewById(R.id.edtRainHavestingWorkStatus);
        recyclerViewRAinHarveOnSub=findViewById(R.id.recyclerViewRAinHarveOnSub);
        workingStatusLayout=findViewById(R.id.workingStatusLayout);
        workingStatusHearOnSub=findViewById(R.id.workingStatusHearOnSub);
        linearLayout5=findViewById(R.id.linearLayout5);
        edtRainharvestingAvailabilty.setEnabled(false);
        edtRainHavestingWorkStatus.setEnabled(false);
        recyclerViewRAinHarveOnSub.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();

        Call<List<JsonObject>> call=apiService.checkRainHarvest(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"13"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().get(0).get("RainHarvestingAvl").getAsString().equals("No")){
                    edtRainharvestingAvailabilty.setText(response.body().get(0).get("RainHarvestingAvl").getAsString());
                    recyclerViewRAinHarveOnSub.setVisibility(View.GONE);
                    linearLayout5.setVisibility(View.GONE);
                    workingStatusHearOnSub.setVisibility(View.GONE);
                    dialog2.dismiss();


                }else{



                edtRainHavestingWorkStatus.setText(response.body().get(0).get("WorkingStatus").getAsString());
                    edtRainharvestingAvailabilty.setText(response.body().get(0).get("RainHarvestingAvl").getAsString());

                String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_RainHarvesting.this,StaffPhotoPathList);
                recyclerViewRAinHarveOnSub.setAdapter(onlineImageRecViewAdapter);

                dialog2.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();

            }
        });


    }

    private JsonObject paraGetDetails2(String action, String schoolId, String periodId, String paramId) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",action);
        jsonObject.addProperty("ParamId",paramId);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodId);
        return jsonObject;
    }
}