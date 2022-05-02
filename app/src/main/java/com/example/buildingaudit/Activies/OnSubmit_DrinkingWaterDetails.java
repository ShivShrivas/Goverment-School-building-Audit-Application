package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class OnSubmit_DrinkingWaterDetails extends AppCompatActivity {
EditText edtROInstallationScheme,edtROInstallationWokingStatus,
        edtROInstallationAvailabiltyDW,edtNotWorkingDrinkingwaterTaps,
        edtWorkingDrinkingwaterTaps,edtTotalDrinkingwaterTaps,
        edtOverheadTankWorkStatsyDW,edtOverheadTankAvailabiltyDW,
        edtWaterSupplyWorkStatsyDW,edtWaterSupplyAvailabiltyDW,
        edtSubmersibleWorkStatsyDW,edtSubmersibleAvailabiltyDW,
        edtHandPumpWorkStatsyDW,edtHandPumpAvailabiltyDW;
    RecyclerView recyclerViewDrinkingWater;
    ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_drinking_water_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        recyclerViewDrinkingWater=findViewById(R.id.recyclerViewDrinkingWaterOnSubmit);
        edtROInstallationScheme=findViewById(R.id.edtROInstallationScheme);
        edtROInstallationAvailabiltyDW=findViewById(R.id.edtROInstallationAvailabiltyDW);
        edtWorkingDrinkingwaterTaps=findViewById(R.id.edtWorkingDrinkingwaterTaps);
        edtOverheadTankWorkStatsyDW=findViewById(R.id.edtOverheadTankWorkStatsyDW);
        edtWaterSupplyWorkStatsyDW=findViewById(R.id.edtWaterSupplyWorkStatsyDW);
        edtSubmersibleWorkStatsyDW=findViewById(R.id.edtSubmersibleWorkStatsyDW);
        edtHandPumpWorkStatsyDW=findViewById(R.id.edtHandPumpWorkStatsyDW);
        edtROInstallationWokingStatus=findViewById(R.id.edtROInstallationWokingStatus);
        edtNotWorkingDrinkingwaterTaps=findViewById(R.id.edtNotWorkingDrinkingwaterTaps);
        edtTotalDrinkingwaterTaps=findViewById(R.id.edtTotalDrinkingwaterTaps);
        edtOverheadTankAvailabiltyDW=findViewById(R.id.edtOverheadTankAvailabiltyDW);
        edtWaterSupplyAvailabiltyDW=findViewById(R.id.edtWaterSupplyAvailabiltyDW);
        edtSubmersibleAvailabiltyDW=findViewById(R.id.edtSubmersibleAvailabiltyDW);
        edtHandPumpAvailabiltyDW=findViewById(R.id.edtHandPumpAvailabiltyDW);

        disableEditBox();

        recyclerViewDrinkingWater.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkDrinkingWater(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"7"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());

                edtROInstallationScheme.setText(response.body().get(0).get("ROInsScheme").getAsString());
                if(response.body().get(0).get("ROInsWorkingStatus").getAsString().equals("F")){

                    edtROInstallationWokingStatus.setText("Functional");
                }else  if(response.body().get(0).get("ROInsWorkingStatus").getAsString().equals("NF")){

                    edtROInstallationWokingStatus.setText("Not Functional");
                }
                if(response.body().get(0).get("OverHeadTankWorkingStatus").getAsString().equals("F")){

                    edtOverheadTankWorkStatsyDW.setText("Functional");
                }else  if(response.body().get(0).get("OverHeadTankWorkingStatus").getAsString().equals("NF")){

                    edtOverheadTankWorkStatsyDW.setText("Not Functional");
                }
  if(response.body().get(0).get("NNPalikaWaterSupplyWorkingStatus").getAsString().equals("F")){

      edtWaterSupplyWorkStatsyDW.setText("Functional");
                }else  if(response.body().get(0).get("NNPalikaWaterSupplyWorkingStatus").getAsString().equals("NF")){

      edtWaterSupplyWorkStatsyDW.setText("Not Functional");
                }
 if(response.body().get(0).get("SubmersibleWorkingStatus").getAsString().equals("F")){

     edtSubmersibleWorkStatsyDW.setText("Functional");
                }else  if(response.body().get(0).get("SubmersibleWorkingStatus").getAsString().equals("NF")){

     edtSubmersibleWorkStatsyDW.setText("Not Functional");
                }
if(response.body().get(0).get("HandPumpWorkingStatus").getAsString().equals("F")){

    edtHandPumpWorkStatsyDW.setText("Functional");
                }else  if(response.body().get(0).get("HandPumpWorkingStatus").getAsString().equals("NF")){

    edtHandPumpWorkStatsyDW.setText("Not Functional");
                }


                        edtROInstallationAvailabiltyDW.setText(response.body().get(0).get("ROInsAvl").getAsString());
                edtWorkingDrinkingwaterTaps.setText(response.body().get(0).get("WorkingTaps").getAsString());




                edtNotWorkingDrinkingwaterTaps.setText(response.body().get(0).get("NonWorkingTaps").getAsString());
                        edtTotalDrinkingwaterTaps.setText(response.body().get(0).get("NoOfTaps").getAsString());
                edtOverheadTankAvailabiltyDW.setText(response.body().get(0).get("OverHeadTankAvl").getAsString());
                        edtWaterSupplyAvailabiltyDW.setText(response.body().get(0).get("NNPalikaWaterSupplyAvl").getAsString());
                edtSubmersibleAvailabiltyDW.setText(response.body().get(0).get("SubmersibleAvl").getAsString());
                        edtHandPumpAvailabiltyDW.setText(response.body().get(0).get("HandPumpAvl").getAsString());
                String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_DrinkingWaterDetails.this,StaffPhotoPathList);
                recyclerViewDrinkingWater.setAdapter(onlineImageRecViewAdapter);


            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });

    }

    private void disableEditBox() {
        edtROInstallationScheme.setEnabled(false);
                edtROInstallationAvailabiltyDW.setEnabled(false);
        edtWorkingDrinkingwaterTaps.setEnabled(false);
                edtOverheadTankWorkStatsyDW.setEnabled(false);
        edtWaterSupplyWorkStatsyDW.setEnabled(false);
                edtSubmersibleWorkStatsyDW.setEnabled(false);
        edtHandPumpWorkStatsyDW.setEnabled(false);
                edtROInstallationWokingStatus.setEnabled(false);
        edtNotWorkingDrinkingwaterTaps.setEnabled(false);
                edtTotalDrinkingwaterTaps.setEnabled(false);
        edtOverheadTankAvailabiltyDW.setEnabled(false);
                edtWaterSupplyAvailabiltyDW.setEnabled(false);
        edtSubmersibleAvailabiltyDW.setEnabled(false);
                edtHandPumpAvailabiltyDW.setEnabled(false);
        edtROInstallationScheme.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                edtROInstallationAvailabiltyDW.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        edtWorkingDrinkingwaterTaps.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                edtOverheadTankWorkStatsyDW.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        edtWaterSupplyWorkStatsyDW.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                edtSubmersibleWorkStatsyDW.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        edtHandPumpWorkStatsyDW.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                edtROInstallationWokingStatus.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        edtNotWorkingDrinkingwaterTaps.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                edtTotalDrinkingwaterTaps.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        edtOverheadTankAvailabiltyDW.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                edtWaterSupplyAvailabiltyDW.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        edtSubmersibleAvailabiltyDW.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                edtHandPumpAvailabiltyDW.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


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