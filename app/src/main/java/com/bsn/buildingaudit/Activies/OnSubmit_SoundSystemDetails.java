package com.bsn.buildingaudit.Activies;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

public class OnSubmit_SoundSystemDetails extends AppCompatActivity {
EditText edtSchoolBandForGirls,edtSchoolBand,edtSoundSystem;
RecyclerView recyclerViewSoundSystmOnSub;
    ApplicationController applicationController;
    LinearLayout linearLayout21;
    String Type;
    TextView userName,schoolAddress,schoolName,editSoundAndBandDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_sound_system_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Dialog dialog2 = new Dialog(this);
        Intent i=getIntent();
        Type=i.getStringExtra("Type");

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
        edtSchoolBandForGirls=findViewById(R.id.edtSchoolBandForGirls);
        edtSchoolBand=findViewById(R.id.edtSchoolBand);
        edtSoundSystem=findViewById(R.id.edtSoundSystem);
        linearLayout21=findViewById(R.id.linearLayout21);
        recyclerViewSoundSystmOnSub=findViewById(R.id.recyclerViewSoundSystmOnSub);
        editSoundAndBandDetails=findViewById(R.id.editSoundAndBandDetails);
        disableEditBox();
        if (Type.equals("D")){
            linearLayout21.setVisibility(View.VISIBLE);
        }
        editSoundAndBandDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_SoundSystemDetails.this,UpdateDetailsSoundSystem.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
        recyclerViewSoundSystmOnSub.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkSoundSystem(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"22"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+response);
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    if (Type.equals("D")){
                        editSoundAndBandDetails.setVisibility(View.GONE);

                    }else{
                        editSoundAndBandDetails.setVisibility(View.VISIBLE);

                    }
                }
                edtSchoolBandForGirls.setText(response.body().get(0).get("SchoolBandInstAvlGirls").getAsString());
                        edtSchoolBand.setText(response.body().get(0).get("SchoolBandInstAvlBoys").getAsString());
                edtSoundSystem.setText(response.body().get(0).get("SoundSysAvailability").getAsString());

                String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_SoundSystemDetails.this,StaffPhotoPathList, applicationController.getUsertype());
                recyclerViewSoundSystmOnSub.setAdapter(onlineImageRecViewAdapter);
                dialog2.dismiss();

            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();

            }
        });




    }

    private void disableEditBox() {
        edtSchoolBandForGirls.setEnabled(false);
                edtSchoolBand.setEnabled(false);
        edtSoundSystem.setEnabled(false);
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