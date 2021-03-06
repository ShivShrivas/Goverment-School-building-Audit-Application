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

public class OnSubmit_GirlsToiletDetails extends AppCompatActivity {
    ApplicationController applicationController;
    LinearLayout linearLayoutCWSNfriendlyToilet;
    TextView userName,schoolAddress,schoolName;
    RecyclerView recyclerViewBoysToiletOnSub;
    EditText edtUrinalWithFlushTotalB,edtUrinalWithoutFlushB,edtUrinalWithFlushB,edtCSWNfriendlyTotalB,edtCSWNwithoutfriendlyB,edtCSWNfriendlyB,
            edtwithflushTotal,edtWithoutFlushClean,edtWithFlushClean,edtBoysDustbin,edtBoysDoors,edtCWSNBoysAvailability,edtsgirlsIncinator,edtGirlsSanetoryNapkin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_girls_toilet_details);
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
        edtUrinalWithFlushTotalB=findViewById(R.id.edtUrinalWithFlushTotalB);
        edtUrinalWithoutFlushB=findViewById(R.id.edtUrinalWithoutFlushB);
        edtUrinalWithFlushB=findViewById(R.id.edtUrinalWithFlushB);
        edtCSWNfriendlyTotalB=findViewById(R.id.edtCSWNfriendlyTotalB);
        edtCSWNwithoutfriendlyB=findViewById(R.id.edtCSWNwithoutfriendlyB);
        edtCSWNfriendlyB=findViewById(R.id.edtCSWNfriendlyB);
        edtwithflushTotal=findViewById(R.id.edtwithflushTotal);
        edtWithoutFlushClean=findViewById(R.id.edtWithoutFlushClean);
        edtWithFlushClean=findViewById(R.id.edtWithFlushClean);
        edtBoysDustbin=findViewById(R.id.edtBoysDustbin);
        edtBoysDoors=findViewById(R.id.edtBoysDoors);
        edtCWSNBoysAvailability=findViewById(R.id.edtCWSNBoysAvailability);
        recyclerViewBoysToiletOnSub=findViewById(R.id.recyclerViewBoysToiletOnSub);
        linearLayoutCWSNfriendlyToilet=findViewById(R.id.linearLayoutCWSNfriendlyToilet);
        edtGirlsSanetoryNapkin=findViewById(R.id.edtGirlsSanetoryNapkin);
        edtsgirlsIncinator=findViewById(R.id.edtsgirlsIncinator);

        disableEditbox();
        recyclerViewBoysToiletOnSub.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkGirlsToilet(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"17"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());

                edtUrinalWithFlushTotalB.setText(response.body().get(0).get("TotalUrinals").getAsString());
                edtUrinalWithoutFlushB.setText(response.body().get(0).get("NoOfUrinalsWithOutFlush").getAsString());
                edtUrinalWithFlushB.setText(response.body().get(0).get("NoOfUrinalsWithFlush").getAsString());

                edtwithflushTotal.setText(response.body().get(0).get("TotalExcludingCWSN").getAsString());
                edtWithoutFlushClean.setText(response.body().get(0).get("NoOfSeatsExcludingCWSNWithOutFlush").getAsString());
                edtWithFlushClean.setText(response.body().get(0).get("NoOfSeatsExcludingCWSNWithFlush").getAsString());
                edtBoysDustbin.setText(response.body().get(0).get("Dustbin").getAsString());
                edtBoysDoors.setText(response.body().get(0).get("Door").getAsString());
                if (response.body().get(0).get("AvailabilityCWSN").getAsString().equals("No")){

                    edtCWSNBoysAvailability.setText(response.body().get(0).get("AvailabilityCWSN").getAsString());
                    linearLayoutCWSNfriendlyToilet.setVisibility(View.GONE);

                }else {
                    edtCWSNBoysAvailability.setText(response.body().get(0).get("AvailabilityCWSN").getAsString());
                    edtCSWNfriendlyTotalB.setText(response.body().get(0).get("TotalCWSN").getAsString());
                    edtCSWNwithoutfriendlyB.setText(response.body().get(0).get("NoOfSeatsCWSNWithOutFlush").getAsString());
                    edtCSWNfriendlyB.setText(response.body().get(0).get("NoOfSeatsCWSNWithFlush").getAsString());
                    edtsgirlsIncinator.setText(response.body().get(0).get("Incinerator").getAsString());
                    edtGirlsSanetoryNapkin.setText(response.body().get(0).get("FreeSanitaryNapkins").getAsString());
                }
                String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_GirlsToiletDetails.this,StaffPhotoPathList);
                recyclerViewBoysToiletOnSub.setAdapter(onlineImageRecViewAdapter);
                dialog2.dismiss();

            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
dialog2.dismiss();
            }
        });


    }

    private void disableEditbox() {
        edtUrinalWithFlushTotalB.setEnabled(false);
        edtUrinalWithoutFlushB.setEnabled(false);
        edtUrinalWithFlushB.setEnabled(false);
        edtCSWNfriendlyTotalB.setEnabled(false);
        edtCSWNwithoutfriendlyB.setEnabled(false);
        edtCSWNfriendlyB.setEnabled(false);
        edtwithflushTotal.setEnabled(false);
        edtWithoutFlushClean.setEnabled(false);
        edtWithFlushClean.setEnabled(false);
        edtBoysDustbin.setEnabled(false);
        edtBoysDoors.setEnabled(false);
        edtCWSNBoysAvailability.setEnabled(false);
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