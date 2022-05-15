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

import com.example.buildingaudit.ApplicationController;
import com.example.buildingaudit.R;
import com.example.buildingaudit.RetrofitApi.ApiService;
import com.example.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSubmit_VocationalRoom extends AppCompatActivity {
    private TextView schoolAddress,schoolName,editVocalRoomDetails;
    ApplicationController applicationController;
    RecyclerView recyclerViewVocalIS,recyclerViewVocalHs;
    EditText edtVocalISEquipmentStatus,edtVocalRoomISAvailability,edtVocalHSLabCondition,edtVocalHSEquipmentStatus,
            edtVocalRoomHSAvailability,edtvocalISAvailability;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_vocational_room);
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
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolAddress=findViewById(R.id.schoolAddress);
        recyclerViewVocalHs=findViewById(R.id.recyclerViewVocalHs);
        recyclerViewVocalIS=findViewById(R.id.recyclerViewVocalIS);
        editVocalRoomDetails=findViewById(R.id.editVocalRoomDetails);
        edtvocalISAvailability=findViewById(R.id.edtVocalRoomISAvailability);
        editVocalRoomDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(OnSubmit_VocationalRoom.this,UpdateDetails_VocationalEducationRoom.class);
                i.putExtra("Action","3");
                startActivity(i);
            }
        });
        recyclerViewVocalHs.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewVocalIS.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();

        Call<List<JsonObject>> call=apiService.checkVocalRoom(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"27"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
//                if (response.body().get(0).get("RainHarvestingAvl").getAsString().equals("No")){
//                    .setText(response.body().get(0).get("RainHarvestingAvl").getAsString());
//                    recyclerViewRAinHarveOnSub.setVisibility(View.GONE);
//                    linearLayout5.setVisibility(View.GONE);
//                    workingStatusHearOnSub.setVisibility(View.GONE);
//                    dialog2.dismiss();
//
//
//                }else{
//
//
//
//                    edtRainHavestingWorkStatus.setText(response.body().get(0).get("WorkingStatus").getAsString());
//                    edtRainharvestingAvailabilty.setText(response.body().get(0).get("RainHarvestingAvl").getAsString());
//
//                    String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
//                    OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_RainHarvesting.this,StaffPhotoPathList);
//                    recyclerViewRAinHarveOnSub.setAdapter(onlineImageRecViewAdapter);
//
//                    dialog2.dismiss();

             //   }
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