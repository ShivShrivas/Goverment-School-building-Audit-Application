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

public class OnSubmit_OpenGYmDetails extends AppCompatActivity {
    TextView userName,schoolAddress,schoolName;
    EditText edtGymAvailabelty,gymWorkingStatus,edtGymArea;
    RecyclerView recyclerViewGymOnSubmit;
    TextView gymUploadtxt,editOpenGymDetails;
    Call<List<JsonObject>> call;
    ConstraintLayout gymLayout;
    LinearLayout  linearLayout21;

    ApplicationController applicationController;

String Type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_open_gym_details);
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
        Intent i=getIntent();
        Type=i.getStringExtra("Type");

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        dialog2.show();
        linearLayout21=findViewById(R.id.linearLayout21);
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        edtGymAvailabelty=findViewById(R.id.edtGymAvailabelty);
        gymWorkingStatus=findViewById(R.id.gymWorkingStatus);
        gymLayout=findViewById(R.id.gymLayout);
        gymUploadtxt=findViewById(R.id.gymUploadtxt);
        editOpenGymDetails=findViewById(R.id.editOpenGymDetails);
        edtGymArea=findViewById(R.id.edtGymArea);
        recyclerViewGymOnSubmit=findViewById(R.id.recyclerViewGymOnSubmit);
        if (Type.equals("D")){
            linearLayout21.setVisibility(View.VISIBLE);
        }
        recyclerViewGymOnSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        disabledEdtBox();
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        editOpenGymDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_OpenGYmDetails.this,UpdateDetailsGym.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
        if (applicationController.getUsertype().equals("VA")){
            call=apiService.checkGymDetails(paraGetDetails2("2","2033", applicationController.getPeriodID(),"6"));
        }else{
            call=apiService.checkGymDetails(paraGetDetails2("11","2033", applicationController.getPeriodID(),"6"));
        }
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                    Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    if (Type.equals("D")){
                        editOpenGymDetails.setVisibility(View.GONE);
                    }else{
                        editOpenGymDetails.setVisibility(View.VISIBLE);

                    }
                }
                    if (response.body().get(0).get("Availabilty").getAsString().equals("No")){
                        edtGymAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());
                            gymLayout.setVisibility(View.GONE);
                            gymUploadtxt.setVisibility(View.GONE);
                            dialog2.dismiss();
                    }else {
                        edtGymArea.setText(response.body().get(0).get("Areainsqmtr").getAsString());
                        edtGymAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());

                        gymWorkingStatus.setText(response.body().get(0).get("WorkingStatus").getAsString());
                        try {
                            String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                            OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_OpenGYmDetails.this,StaffPhotoPathList, applicationController.getUsertype());
                            recyclerViewGymOnSubmit.setAdapter(onlineImageRecViewAdapter);
                        }catch (Exception e){
                            recyclerViewGymOnSubmit.setVisibility(View.GONE);
                            gymUploadtxt.setVisibility(View.GONE);
                        }

                    }

                dialog2.dismiss();



            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();

            }
        });
    }

    private void disabledEdtBox() {
        edtGymAvailabelty.setEnabled(false);
        gymWorkingStatus.setEnabled(false);
        edtGymArea.setEnabled(false);
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