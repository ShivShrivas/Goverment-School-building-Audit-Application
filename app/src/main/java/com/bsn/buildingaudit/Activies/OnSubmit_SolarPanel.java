package com.bsn.buildingaudit.Activies;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.OnlineImageRecViewAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.ApproveRejectRemarksDataModel;
import com.bsn.buildingaudit.Model.Datum;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSubmit_SolarPanel extends AppCompatActivity {
    ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName,editSolarPanelDetails;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();

    EditText edtTypeOfSolarPanel,edtcapacityOfSolarPanel,edtSolarPanelScheme,edtSolarPaneltWorkingStatus,edtSolraPanelInstallationYear,edtSolarPanel;
    RecyclerView recyclerViewTwoTypeSolarpanelAndOnSub;
    LinearLayout linearLayout21;
    Call<List<JsonObject>> call;
    Button solarPanelApproveBtn,solarPanelRejectBtn;
    String Type,ParentID;
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
        Intent i=getIntent();
        Type=i.getStringExtra("Type");
        ParentID=i.getStringExtra("ParamId");

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        dialog2.show();
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        solarPanelRejectBtn=findViewById(R.id.solarPanelRejectBtn);
        solarPanelApproveBtn=findViewById(R.id.solarPanelApproveBtn);
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
        linearLayout21=findViewById(R.id.linearLayout21);
        if (Type.equals("D")){
            linearLayout21.setVisibility(View.VISIBLE);
        }
        recyclerViewTwoTypeSolarpanelAndOnSub.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        disableEditbox();
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        JsonObject json =new JsonObject();
        json.addProperty("SchoolID",applicationController.getSchoolId());
        json.addProperty("PeriodID",applicationController.getPeriodID());
        json.addProperty("ParamId",ParentID);
        Call<ApproveRejectRemarksDataModel> callz=apiService.getpriviousSubmittedDataByDIOS(json);
        callz.enqueue(new Callback<ApproveRejectRemarksDataModel>() {
            @Override
            public void onResponse(Call<ApproveRejectRemarksDataModel> call, Response<ApproveRejectRemarksDataModel> response) {
                Log.d("TAG", "onResponse: "+response.body());
                ApproveRejectRemarksDataModel approveRejectRemarksDataModel=response.body();
                Log.d("TAG", "onResponse: "+approveRejectRemarksDataModel.getStatus());
                if (!approveRejectRemarksDataModel.getStatus().equals("No Record Found")){
                    Toast.makeText(OnSubmit_SolarPanel.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                    arrayListRemarks=approveRejectRemarksDataModel.getData();


                }
            }

            @Override
            public void onFailure(Call<ApproveRejectRemarksDataModel> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });
        solarPanelApproveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: "+ParentID);
                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InsType","A");
                jsonObject1.addProperty("ParamId",ParentID);
                Log.d("TAG", "onClick: "+jsonObject1);
                Call<ArrayList<ApproveRejectRemarkModel>> call1=apiService.getApproveRejectRemark(jsonObject1);
                call1.enqueue(new Callback<ArrayList<ApproveRejectRemarkModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ApproveRejectRemarkModel>> call, Response<ArrayList<ApproveRejectRemarkModel>> response) {
                        ArrayList<ApproveRejectRemarkModel> arrayList=response.body();
                        StaticFunctions.showDialogApprove(OnSubmit_SolarPanel.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

        solarPanelRejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: "+ParentID);
                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InsType","R");
                jsonObject1.addProperty("ParamId",ParentID);
                Log.d("TAG", "onClick: "+jsonObject1);
                Call<ArrayList<ApproveRejectRemarkModel>> call1=apiService.getApproveRejectRemark(jsonObject1);
                call1.enqueue(new Callback<ArrayList<ApproveRejectRemarkModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ApproveRejectRemarkModel>> call, Response<ArrayList<ApproveRejectRemarkModel>> response) {
                        ArrayList<ApproveRejectRemarkModel> arrayList=response.body();
                        StaticFunctions.showDialogReject(OnSubmit_SolarPanel.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
        editSolarPanelDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OnSubmit_SolarPanel.this,UpdateDetailsSolarPanel.class);
                intent.putExtra("Action","3");
                startActivity(intent);
                finish();
            }
        });
        if (applicationController.getUsertype().equals("VA")){
            call=apiService.viewSolarPanelDetails(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"14"));
        }else{
            call=apiService.viewSolarPanelDetails(paraGetDetails2("13",applicationController.getSchoolId(), applicationController.getPeriodID(),"14"));
        }        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    if (Type.equals("D")){
                        editSolarPanelDetails.setVisibility(View.GONE);
                    }else{editSolarPanelDetails.setVisibility(View.VISIBLE);}


                }
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
                    OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_SolarPanel.this,StaffPhotoPathList, applicationController.getUsertype());
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