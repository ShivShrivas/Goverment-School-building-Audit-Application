package com.example.buildingaudit.Activies;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
TextView uploadDW,editDrinkingWaterDetails;

    EditText edtNotWorkingRO,edtWorkingRO,edtTotalRO;
    EditText edtNotWorkingOHTanks,edtWorkingOHTanks,edtTotalOHTanks;
    EditText edtNotWorkingSummerSible,edtWorkingSummerSible,edtTotalSummerSible;
    EditText edtNotWorkingHandpump,edtWorkingHandpump,edtTotalHandpump;
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
        recyclerViewDrinkingWater=findViewById(R.id.recyclerViewDrinkingWaterOnSubmit);
        edtROInstallationScheme=findViewById(R.id.edtROInstallationScheme);
        edtNotWorkingRO=findViewById(R.id.edtNotWorkingRO);
        edtWorkingRO=findViewById(R.id.edtWorkingRO);
        edtTotalRO=findViewById(R.id.edtTotalRO);
        edtNotWorkingSummerSible=findViewById(R.id.edtNotWorkingSummerSible);
        edtWorkingSummerSible=findViewById(R.id.edtWorkingSummerSible);
        edtTotalSummerSible=findViewById(R.id.edtTotalSummerSible);
        edtNotWorkingHandpump=findViewById(R.id.edtNotWorkingHandpump);
        edtWorkingHandpump=findViewById(R.id.edtWorkingHandpump);
        edtTotalHandpump=findViewById(R.id.edtTotalHandpump);
        edtWorkingDrinkingwaterTaps=findViewById(R.id.edtWorkingDrinkingwaterTaps);
        edtOverheadTankWorkStatsyDW=findViewById(R.id.edtOverheadTankWorkStatsyDW);
        edtWaterSupplyWorkStatsyDW=findViewById(R.id.edtWaterSupplyWorkStatsyDW);
        edtNotWorkingDrinkingwaterTaps=findViewById(R.id.edtNotWorkingDrinkingwaterTaps);
        edtTotalDrinkingwaterTaps=findViewById(R.id.edtTotalDrinkingwaterTaps);
        edtOverheadTankAvailabiltyDW=findViewById(R.id.edtOverheadTankAvailabiltyDW);
        edtWaterSupplyAvailabiltyDW=findViewById(R.id.edtWaterSupplyAvailabiltyDW);
        editDrinkingWaterDetails=findViewById(R.id.editDrinkingWaterDetails);
        uploadDW=findViewById(R.id.uploadDW);
        editDrinkingWaterDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_DrinkingWaterDetails.this,UpdateDetailsDrinkingWater.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
        disableEditBox();

        recyclerViewDrinkingWater.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkDrinkingWater(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"7"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());

int totalRo=Integer.parseInt(response.body().get(0).get("RoWorking").getAsString())+Integer.parseInt(response.body().get(0).get("RoNonWorking").getAsString());
int totalSub=Integer.parseInt(response.body().get(0).get("SubNoWorking").getAsString())+Integer.parseInt(response.body().get(0).get("SubWorking").getAsString());
int totalHand=Integer.parseInt(response.body().get(0).get("HandNonWorking").getAsString())+Integer.parseInt(response.body().get(0).get("HandWorking").getAsString());
                edtNotWorkingRO.setText(response.body().get(0).get("RoNonWorking").getAsString());
                        edtWorkingRO.setText(response.body().get(0).get("RoWorking").getAsString());
                edtTotalRO.setText(String.valueOf(totalRo));
                        edtNotWorkingSummerSible.setText(response.body().get(0).get("SubNoWorking").getAsString());
                edtWorkingSummerSible.setText(response.body().get(0).get("SubWorking").getAsString());
                        edtTotalSummerSible.setText(String.valueOf(totalSub));
                edtNotWorkingHandpump.setText(response.body().get(0).get("HandNonWorking").getAsString());
                        edtWorkingHandpump.setText(response.body().get(0).get("HandWorking").getAsString());
                edtTotalHandpump.setText(String.valueOf(totalHand));







                            edtROInstallationScheme.setText(response.body().get(0).get("ROInsScheme").getAsString());




                        if (response.body().get(0).get("NoOfTaps").getAsString().equals("0") || response.body().get(0).get("NoOfTaps").getAsString().equals("00") || response.body().get(0).get("NoOfTaps").getAsString().equals("000")){
                            edtWorkingDrinkingwaterTaps.setText("0");
                            edtNotWorkingDrinkingwaterTaps.setText("0");
                            edtTotalDrinkingwaterTaps.setText("0");

                        }else{
                            edtWorkingDrinkingwaterTaps.setText(response.body().get(0).get("WorkingTaps").getAsString());
                            edtNotWorkingDrinkingwaterTaps.setText(response.body().get(0).get("NonWorkingTaps").getAsString());
                            edtTotalDrinkingwaterTaps.setText(response.body().get(0).get("NoOfTaps").getAsString());
                        }
                        if (response.body().get(0).get("OverHeadTankAvl").getAsString().equals("No")){
                            edtOverheadTankAvailabiltyDW.setText(response.body().get(0).get("OverHeadTankAvl").getAsString());
                            edtOverheadTankWorkStatsyDW.setVisibility(View.GONE);

                        }else{
                            edtOverheadTankWorkStatsyDW.setText(response.body().get(0).get("OverHeadTankWorkingStatus").getAsString());
                            edtOverheadTankAvailabiltyDW.setText(response.body().get(0).get("OverHeadTankAvl").getAsString());
                        }

                        if (response.body().get(0).get("NNPalikaWaterSupplyAvl").getAsString().equals("No")){

                            edtWaterSupplyAvailabiltyDW.setText(response.body().get(0).get("NNPalikaWaterSupplyAvl").getAsString());
                            edtWaterSupplyWorkStatsyDW.setVisibility(View.GONE);

                        }else{
                            edtWaterSupplyAvailabiltyDW.setText(response.body().get(0).get("NNPalikaWaterSupplyAvl").getAsString());
                            edtWaterSupplyWorkStatsyDW.setText(response.body().get(0).get("NNPalikaWaterSupplyWorkingStatus").getAsString());

                        }


                       try {
                           String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                           OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_DrinkingWaterDetails.this,StaffPhotoPathList);
                           recyclerViewDrinkingWater.setAdapter(onlineImageRecViewAdapter);
                       }catch (Exception e){
                           recyclerViewDrinkingWater.setVisibility(View.GONE);
                           uploadDW.setVisibility(View.GONE);
                       }

                dialog2.dismiss();

            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
dialog2.dismiss();
            }
        });

    }

    private void disableEditBox() {
        edtROInstallationScheme.setEnabled(false);

        edtWorkingDrinkingwaterTaps.setEnabled(false);
                edtOverheadTankWorkStatsyDW.setEnabled(false);
        edtWaterSupplyWorkStatsyDW.setEnabled(false);



        edtNotWorkingDrinkingwaterTaps.setEnabled(false);
                edtTotalDrinkingwaterTaps.setEnabled(false);
        edtOverheadTankAvailabiltyDW.setEnabled(false);
                edtWaterSupplyAvailabiltyDW.setEnabled(false);
        edtROInstallationScheme.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        edtWorkingDrinkingwaterTaps.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                edtOverheadTankWorkStatsyDW.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        edtWaterSupplyWorkStatsyDW.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        edtROInstallationScheme.setEnabled(false);
                edtNotWorkingRO.setEnabled(false);
        edtWorkingRO.setEnabled(false);
                edtTotalRO.setEnabled(false);
        edtNotWorkingSummerSible.setEnabled(false);
                edtWorkingSummerSible.setEnabled(false);
        edtTotalSummerSible.setEnabled(false);
                edtNotWorkingHandpump.setEnabled(false);
        edtWorkingHandpump.setEnabled(false);
                edtTotalHandpump.setEnabled(false);
        edtWorkingDrinkingwaterTaps.setEnabled(false);
                edtOverheadTankWorkStatsyDW.setEnabled(false);
        edtWaterSupplyWorkStatsyDW.setEnabled(false);
                edtNotWorkingDrinkingwaterTaps.setEnabled(false);
        edtTotalDrinkingwaterTaps.setEnabled(false);
                edtOverheadTankAvailabiltyDW.setEnabled(false);
        edtWaterSupplyAvailabiltyDW.setEnabled(false);



        edtNotWorkingDrinkingwaterTaps.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                edtTotalDrinkingwaterTaps.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        edtOverheadTankAvailabiltyDW.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                edtWaterSupplyAvailabiltyDW.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);



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