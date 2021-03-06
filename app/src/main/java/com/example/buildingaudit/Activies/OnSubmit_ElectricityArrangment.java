package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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

public class OnSubmit_ElectricityArrangment extends AppCompatActivity {
    ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName;
    EditText edtElectricityAvailabelty,edtInternalElectrification,edtSource,edtElectricStatus,edtnoOfTubeLight,
            edtnoOfFans;
    RecyclerView recyclerViewElectricityArrangmentOnSub;
    ConstraintLayout constraintLayoutEA;
    TextView uploadImageTxt;
    Button submitBtnElectricityArrangeOnSub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_electricity_arrangment);

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

        edtElectricityAvailabelty=findViewById(R.id.edtElectricityAvailabelty);
        uploadImageTxt=findViewById(R.id.uploadImageTxt);
        edtInternalElectrification=findViewById(R.id.edtInternalElectrification);
        edtSource=findViewById(R.id.edtSource);
        edtElectricStatus=findViewById(R.id.edtElectricStatus);
        edtnoOfTubeLight=findViewById(R.id.edtnoOfTubeLight);
        edtnoOfFans=findViewById(R.id.edtnoOfFans);
        recyclerViewElectricityArrangmentOnSub=findViewById(R.id.recyclerViewElectricityArrangmentOnSub);
        submitBtnElectricityArrangeOnSub=findViewById(R.id.submitBtnElectricityArrangeOnSub);
        constraintLayoutEA=findViewById(R.id.constraintLayoutEA);
        disableEditbox();
        recyclerViewElectricityArrangmentOnSub.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkElectricityArrangement(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"11"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                if (response.body().get(0).get("Availabilty").getAsString().equals("No")){
                    constraintLayoutEA.setVisibility(View.GONE);
                    edtElectricityAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());
            dialog2.dismiss();
                }else{
                    edtElectricityAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());
                    edtInternalElectrification.setText(response.body().get(0).get("InternalElectrification").getAsString());
                    edtSource.setText(response.body().get(0).get("Source").getAsString());



                    edtElectricStatus.setText(response.body().get(0).get("WorkingStatus").getAsString());

                    edtnoOfTubeLight.setText(response.body().get(0).get("NoOfBulbsTLight").getAsString());
                    edtnoOfFans.setText(response.body().get(0).get("NoOfFans").getAsString());
try{
    String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
    OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_ElectricityArrangment.this,StaffPhotoPathList);
    recyclerViewElectricityArrangmentOnSub.setAdapter(onlineImageRecViewAdapter);
}catch (Exception e){
    recyclerViewElectricityArrangmentOnSub.setVisibility(View.GONE);
    uploadImageTxt.setVisibility(View.GONE);
}


                    dialog2.dismiss();
                }


            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();

            }
        });

    }

    private void disableEditbox() {

        edtElectricityAvailabelty.setEnabled(false);
        edtInternalElectrification.setEnabled(false);
        edtSource.setEnabled(false);
          edtElectricStatus.setEnabled(false);
        edtnoOfTubeLight.setEnabled(false);
          edtnoOfFans.setEnabled(false);
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