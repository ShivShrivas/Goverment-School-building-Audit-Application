package com.bsn.buildingaudit.Activies;

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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.OnlineImageRecViewAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSubmit_PlaygroundDetails extends AppCompatActivity {
EditText EditTextPlaygroundAvailabelty,EditTextLevellingStatus,edtAreaOfPlayGround,EditTexttrackAvalabiltyStatus;
    ApplicationController applicationController;
    RecyclerView recyclerViewPlayground;
ConstraintLayout playGroundlayout;
TextView PGImageUploadTxt,editPlayGroundDetails;

    TextView userName,schoolAddress,schoolName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_playground_details);
        applicationController= (ApplicationController) getApplication();
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
        EditTextPlaygroundAvailabelty=findViewById(R.id.EditTextPlaygroundAvailabelty);
        EditTextLevellingStatus=findViewById(R.id.EditTextLevellingStatus);
        edtAreaOfPlayGround=findViewById(R.id.edtAreaOfPlayGround);
        EditTexttrackAvalabiltyStatus=findViewById(R.id.EditTexttrackAvalabiltyStatus);
        recyclerViewPlayground=findViewById(R.id.recyclerViewPlayground);
        playGroundlayout=findViewById(R.id.playGroundlayout);
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        PGImageUploadTxt=findViewById(R.id.PGImageUploadTxt);
        editPlayGroundDetails=findViewById(R.id.editPlayGroundDetails);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        recyclerViewPlayground.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        disabledEdtBox();
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();

        Call<List<JsonObject>> call=apiService.checkPlayGroundDetails(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"5"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().get(0).get("Availabilty").getAsString().equals("No")){
                    playGroundlayout.setVisibility(View.GONE);
                    EditTextPlaygroundAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());
                    dialog2.dismiss();
                }else {
                    edtAreaOfPlayGround.setText(response.body().get(0).get("Areainsqmtr").getAsString());
                    EditTextPlaygroundAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());
                    EditTextLevellingStatus.setText(response.body().get(0).get("LevellingStatus").getAsString());
                    EditTexttrackAvalabiltyStatus.setText(response.body().get(0).get("AvailabiltyStandardTrack").getAsString());
                    try {
                        String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                        OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_PlaygroundDetails.this,StaffPhotoPathList);
                        recyclerViewPlayground.setAdapter(onlineImageRecViewAdapter);
                    }catch (Exception e){
                        PGImageUploadTxt.setVisibility(View.GONE);
                        recyclerViewPlayground.setVisibility(View.GONE);
                    }

                    dialog2.dismiss();
                }




            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();

            }
        });

        editPlayGroundDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_PlaygroundDetails.this,UpdateDetailsPlayground.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });

    }

    private void disabledEdtBox() {
        EditTextPlaygroundAvailabelty.setEnabled(false);
                EditTextLevellingStatus.setEnabled(false);
        EditTexttrackAvalabiltyStatus.setEnabled(false);
        edtAreaOfPlayGround.setEnabled(false);
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