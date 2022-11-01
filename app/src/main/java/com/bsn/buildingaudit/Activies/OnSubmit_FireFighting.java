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

public class OnSubmit_FireFighting extends AppCompatActivity {
    TextView userName,schoolAddress,schoolName;
    ApplicationController applicationController;
        EditText edtFireFightTraining,edtFireFightRenewalStatus,edtFireFightWorkingStatus,edtFireFightingInstallationYear,edtFireFightAvailabelty;
        TextView edtrenewalDate,editFirefightingDetails;
    EditText edtNotWorkingFF,edtWorkingFF,edtTotalFF;
    LinearLayout linearLayout21;
    Call<List<JsonObject>> call;
    String Type;
    String ParentID,InspectionId;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();
    Boolean remarkAlreadyDoneFlag=false;

    Button fireFightingApproveBtn,fireFightingRejectBtn;
        ConstraintLayout constraintLayout30;
        RecyclerView recyclerViewFireFightningoNsUB;
TextView uploadtextFireFighting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_fire_fighting);

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
        InspectionId=i.getStringExtra("InspectionId");

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        dialog2.show();
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        fireFightingApproveBtn=findViewById(R.id.fireFightingApproveBtn);
        fireFightingRejectBtn=findViewById(R.id.fireFightingRejectBtn);
        schoolName=findViewById(R.id.schoolName);
        linearLayout21=findViewById(R.id.linearLayout21);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        edtTotalFF=findViewById(R.id.edtTotalFF);
        edtWorkingFF=findViewById(R.id.edtWorkingFF);
        edtNotWorkingFF=findViewById(R.id.edtNotWorkingFF);
        edtFireFightTraining=findViewById(R.id.edtFireFightTraining);
        uploadtextFireFighting=findViewById(R.id.uploadtextFireFighting);
        editFirefightingDetails=findViewById(R.id.editFirefightingDetails);
        edtrenewalDate=findViewById(R.id.edtrenewalDateOnSub);
        edtFireFightRenewalStatus=findViewById(R.id.edtFireFightRenewalStatus);
        edtFireFightWorkingStatus=findViewById(R.id.edtFireFightWorkingStatus);
        edtFireFightAvailabelty=findViewById(R.id.edtFireFightAvailabelty);
        edtFireFightingInstallationYear=findViewById(R.id.edtFireFightingInstallationYear);
        recyclerViewFireFightningoNsUB=findViewById(R.id.recyclerViewFireFightningoNsUB);
        constraintLayout30=findViewById(R.id.constraintLayout30);
        if (Type.equals("D")){
            linearLayout21.setVisibility(View.VISIBLE);
        }
        editFirefightingDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_FireFighting.this,UpdateDetailsFireFighting.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
        disableEditbox();
        recyclerViewFireFightningoNsUB.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        if (Type.equals("D")) {
            JsonObject json = new JsonObject();
            json.addProperty("SchoolID", applicationController.getSchoolId());
            json.addProperty("PeriodID", applicationController.getPeriodID());
            json.addProperty("ParamId", ParentID);
            json.addProperty("InsRecordId", InspectionId);
            Log.d("TAG", "onCreate: " + json);
            Call<ApproveRejectRemarksDataModel> callz = apiService.getpriviousSubmittedDataByDIOS(json);
            callz.enqueue(new Callback<ApproveRejectRemarksDataModel>() {
                @Override
                public void onResponse(Call<ApproveRejectRemarksDataModel> call, Response<ApproveRejectRemarksDataModel> response) {
                    Log.d("TAG", "onResponse: " + response.body());
                    ApproveRejectRemarksDataModel approveRejectRemarksDataModel = response.body();
                    if (!approveRejectRemarksDataModel.getStatus().equals("No Record Found")) {

                        Toast.makeText(OnSubmit_FireFighting.this, "" + approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "onResponse: " + approveRejectRemarksDataModel.getData());
                        arrayListRemarks = approveRejectRemarksDataModel.getData();
                        Dialog dialogForRemark = new Dialog(OnSubmit_FireFighting.this);
                        dialogForRemark.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialogForRemark.setContentView(R.layout.respons_dialog);
                        dialogForRemark.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        dialogForRemark.setCancelable(false);

                        TextView textView = dialogForRemark.findViewById(R.id.dialogtextResponse);
                        textView.setText(approveRejectRemarksDataModel.getStatus() + "\n Do you want to change it?");
                        Button buttonNo = dialogForRemark.findViewById(R.id.BtnResponseDialoge);
                        Button buttonYes = dialogForRemark.findViewById(R.id.BtnYesDialoge);
                        buttonNo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                onBackPressed();
                                dialogForRemark.dismiss();

                            }
                        });
                        buttonYes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                remarkAlreadyDoneFlag = true;
                                dialogForRemark.dismiss();
                            }
                        });
                        dialogForRemark.show();
                    }
                }

                @Override
                public void onFailure(Call<ApproveRejectRemarksDataModel> call, Throwable t) {

                }
            });
        }
        fireFightingRejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InsType","R");
                jsonObject1.addProperty("ParamId",ParentID);

                Call<ArrayList<ApproveRejectRemarkModel>> call1=apiService.getApproveRejectRemark(jsonObject1);
                call1.enqueue(new Callback<ArrayList<ApproveRejectRemarkModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ApproveRejectRemarkModel>> call, Response<ArrayList<ApproveRejectRemarkModel>> response) {
                        ArrayList<ApproveRejectRemarkModel> arrayList=response.body();
                        StaticFunctions.showDialogReject(OnSubmit_FireFighting.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
    fireFightingApproveBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            JsonObject jsonObject1=new JsonObject();
            jsonObject1.addProperty("InsType","A");
            jsonObject1.addProperty("ParamId",ParentID);

            Call<ArrayList<ApproveRejectRemarkModel>> call1=apiService.getApproveRejectRemark(jsonObject1);
            call1.enqueue(new Callback<ArrayList<ApproveRejectRemarkModel>>() {
                @Override
                public void onResponse(Call<ArrayList<ApproveRejectRemarkModel>> call, Response<ArrayList<ApproveRejectRemarkModel>> response) {
                    ArrayList<ApproveRejectRemarkModel> arrayList=response.body();
                    StaticFunctions.showDialogApprove(OnSubmit_FireFighting.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                }

                @Override
                public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                }
            });
        }
    });

    if (applicationController.getUsertype().equals("VA")){
            call=apiService.checkFireFighting(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"12"));
        }else{
            call=apiService.checkFireFighting(paraGetDetails2("13",applicationController.getSchoolId(), applicationController.getPeriodID(),"12"));
        }
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {

                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    if (Type.equals("D")){
                        editFirefightingDetails.setVisibility(View.GONE);

                    }else{

                        editFirefightingDetails.setVisibility(View.VISIBLE);
                    }
                }                if(response.body().get(0).get("Availabilty").getAsString().equals("No")){
                    constraintLayout30.setVisibility(View.GONE);
                    edtFireFightAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());

                    dialog2.dismiss();
                }else{
                    edtFireFightWorkingStatus.setText(response.body().get(0).get("WorkingStatus").getAsString());

                    int totalRo=Integer.parseInt(response.body().get(0).get("NonWorkingCount")==null?"0":response.body().get(0).get("NonWorkingCount").getAsString())+Integer.parseInt(response.body().get(0).get("WorkingCount")==null?"0":response.body().get(0).get("WorkingCount").getAsString());

                    edtNotWorkingFF.setText(response.body().get(0).get("NonWorkingCount")==null?"0":response.body().get(0).get("NonWorkingCount").getAsString());
                    edtWorkingFF.setText(response.body().get(0).get("WorkingCount")==null?"0":response.body().get(0).get("WorkingCount").getAsString());
                    edtTotalFF.setText(String.valueOf(totalRo));
                    edtFireFightTraining.setText(response.body().get(0).get("Training").getAsString());
                    String[] time=(response.body().get(0).get("RenewalDateStatus").getAsString()).split("T");

                    edtrenewalDate.setText(time[0]);
                    edtFireFightRenewalStatus.setText(response.body().get(0).get("RenewalStatus").getAsString());
                    edtFireFightAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());
                    edtFireFightingInstallationYear.setText(response.body().get(0).get("InstallationYear").getAsString());
try {
    String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
    OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_FireFighting.this,StaffPhotoPathList, applicationController.getUsertype());
    recyclerViewFireFightningoNsUB.setAdapter(onlineImageRecViewAdapter);
}catch (Exception e){
    recyclerViewFireFightningoNsUB.setVisibility(View.GONE);
    uploadtextFireFighting.setVisibility(View.GONE);
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
        edtFireFightTraining.setEnabled(false);
                edtrenewalDate.setEnabled(false);
        edtFireFightRenewalStatus.setEnabled(false);
                edtFireFightWorkingStatus.setEnabled(false);
        edtFireFightAvailabelty.setEnabled(false);
                edtFireFightingInstallationYear.setEnabled(false);
        edtTotalFF.setEnabled(false);
        edtWorkingFF.setEnabled(false);
        edtNotWorkingFF.setEnabled(false);

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