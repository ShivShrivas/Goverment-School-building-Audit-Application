package com.example.buildingaudit.Activies;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
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

public class OnSubmit_SolarPanel extends AppCompatActivity {
    ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName,editSolarPanelDetails;

    EditText edtTypeOfSolarPanel,edtcapacityOfSolarPanel,edtSolarPanelScheme,edtSolarPaneltWorkingStatus,edtSolraPanelInstallationYear,edtSolarPanel;
    RecyclerView recyclerViewTwoTypeSolarpanelAndOnSub;
    ConstraintLayout constraintLayout23;
    Button buttonSolarePanelOnSub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_solar_panel);

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

        edtTypeOfSolarPanel=findViewById(R.id.edtTypeOfSolarPanel);
        edtcapacityOfSolarPanel=findViewById(R.id.edtcapacityOfSolarPanel);
        constraintLayout23=findViewById(R.id.constraintLayout23);
        edtSolarPanelScheme=findViewById(R.id.edtSolarPanelScheme);
        edtSolarPaneltWorkingStatus=findViewById(R.id.edtSolarPaneltWorkingStatus);
        edtSolraPanelInstallationYear=findViewById(R.id.edtSolraPanelInstallationYear);
        edtSolarPanel=findViewById(R.id.edtSolarPanel);
        editSolarPanelDetails=findViewById(R.id.editSolarPanelDetails);
        recyclerViewTwoTypeSolarpanelAndOnSub=findViewById(R.id.recyclerViewTwoTypeSolarpanelAndOnSub);
        buttonSolarePanelOnSub=findViewById(R.id.buttonSolarePanelOnSub);
        recyclerViewTwoTypeSolarpanelAndOnSub.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        disableEditbox();
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        editSolarPanelDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OnSubmit_SolarPanel.this,UpdateDetailsSolarPanel.class);
                intent.putExtra("Action","3");
                startActivity(intent);
                finish();
            }
        });
        Call<List<JsonObject>> call=apiService.viewSolarPanelDetails(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"14"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().get(0).get("Availabilty").getAsString().equals("No")){
                    edtSolarPanel.setText(response.body().get(0).get("Availabilty").getAsString());
                    constraintLayout23.setVisibility(View.GONE);
                    dialog2.dismiss();


                }else{
                    edtTypeOfSolarPanel.setText(response.body().get(0).get("SolarPanelType").getAsString());
                    edtcapacityOfSolarPanel.setText(response.body().get(0).get("Kilowatt").getAsString());
                    edtSolarPanelScheme.setText(response.body().get(0).get("Scheme").getAsString());
                    edtSolarPaneltWorkingStatus.setText(response.body().get(0).get("WorkingStatus").getAsString());
                    edtSolraPanelInstallationYear.setText(response.body().get(0).get("InstallationYear").getAsString());
                    edtSolarPanel.setText(response.body().get(0).get("Availabilty").getAsString());
                    String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                    OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_SolarPanel.this,StaffPhotoPathList);
                    recyclerViewTwoTypeSolarpanelAndOnSub.setAdapter(onlineImageRecViewAdapter);
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
        edtTypeOfSolarPanel.setEnabled(false);
                edtcapacityOfSolarPanel.setEnabled(false);
        edtSolarPanelScheme.setEnabled(false);
                edtSolarPaneltWorkingStatus.setEnabled(false);
        edtSolraPanelInstallationYear.setEnabled(false);
                edtSolarPanel.setEnabled(false);
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