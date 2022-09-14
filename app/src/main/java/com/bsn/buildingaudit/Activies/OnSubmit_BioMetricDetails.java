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

public class OnSubmit_BioMetricDetails extends AppCompatActivity {
    RecyclerView recyclerViewBioMetricOnSubmit;
    ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName,editBioMetricDetails;
    ConstraintLayout layoutBioMetric;Call<List<JsonObject>> call;
    LinearLayout linearLayout21;
    String Type;
    Button bioMetricApproveBtn,bioMetricRejectBtn;
    String ParentID,InspectionId;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();
    Boolean remarkAlreadyDoneFlag=false;

    EditText edtBioMetricMachinecountOnSubmit,edtBioMetricMachineAvailabelty,edtBioMetricInstallationYear,edtuserbiometricStaff,edtuserbiometricStudent,edtBiometricWorkingStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_bio_metric_details);

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
        bioMetricRejectBtn=findViewById(R.id.bioMetricRejectBtn);
        bioMetricApproveBtn=findViewById(R.id.bioMetricApproveBtn);
        schoolName=findViewById(R.id.schoolName);
        linearLayout21=findViewById(R.id.linearLayout21);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        recyclerViewBioMetricOnSubmit=findViewById(R.id.recyclerViewBioMetricOnSubmit);
        layoutBioMetric=findViewById(R.id.layoutBioMetric);
        edtBioMetricMachinecountOnSubmit=findViewById(R.id.edtBioMetricMachinecountOnSubmit);

        edtBioMetricMachineAvailabelty=findViewById(R.id.edtBioMetricMachineAvailabelty);
                edtBioMetricInstallationYear=findViewById(R.id.edtBioMetricInstallationYear);
        edtuserbiometricStaff=findViewById(R.id.edtuserbiometricStaff);
        editBioMetricDetails=findViewById(R.id.editBioMetricDetails);
                edtuserbiometricStudent=findViewById(R.id.edtuserbiometricStudent);
                edtBiometricWorkingStatus=findViewById(R.id.edtBiometricWorkingStatus);

                disableEditbox();
            if (Type.equals("D")){
                linearLayout21.setVisibility(View.VISIBLE);
            }
        editBioMetricDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_BioMetricDetails.this,UpdateDetailsBioMetric.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
        recyclerViewBioMetricOnSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        JsonObject json =new JsonObject();
        json.addProperty("SchoolID",applicationController.getSchoolId());
        json.addProperty("PeriodID",applicationController.getPeriodID());
        json.addProperty("ParamId",ParentID);
        json.addProperty("InsRecordId",InspectionId);
        Call<ApproveRejectRemarksDataModel> callz=apiService.getpriviousSubmittedDataByDIOS(json);
        callz.enqueue(new Callback<ApproveRejectRemarksDataModel>() {
            @Override
            public void onResponse(Call<ApproveRejectRemarksDataModel> call, Response<ApproveRejectRemarksDataModel> response) {
                Log.d("TAG", "onResponse: "+response.body());
                ApproveRejectRemarksDataModel approveRejectRemarksDataModel=response.body();
                Log.d("TAG", "onResponse: "+approveRejectRemarksDataModel.getStatus());
                if (!approveRejectRemarksDataModel.getStatus().equals("No Record Found")){

                    Toast.makeText(OnSubmit_BioMetricDetails.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onResponse: "+approveRejectRemarksDataModel.getData());
                    arrayListRemarks=approveRejectRemarksDataModel.getData();
                    Dialog dialogForRemark=new Dialog(OnSubmit_BioMetricDetails.this);
                    dialogForRemark.requestWindowFeature (Window.FEATURE_NO_TITLE);
                    dialogForRemark.setContentView (R.layout.respons_dialog);
                    dialogForRemark.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
                    dialogForRemark.setCancelable(false);

                    TextView textView=dialogForRemark.findViewById(R.id.dialogtextResponse);
                    textView.setText(approveRejectRemarksDataModel.getStatus()+"\n Do you want to change it?");
                    Button buttonNo=dialogForRemark.findViewById(R.id.BtnResponseDialoge);
                    Button buttonYes=dialogForRemark.findViewById(R.id.BtnYesDialoge);
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
                            remarkAlreadyDoneFlag=true;
                            dialogForRemark.dismiss();
                        }
                    });
                    dialogForRemark.show();
                }
            }

            @Override
            public void onFailure(Call<ApproveRejectRemarksDataModel> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });
        bioMetricApproveBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(OnSubmit_BioMetricDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

        bioMetricRejectBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(OnSubmit_BioMetricDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
        if (applicationController.getUsertype().equals("VA")){
            call=apiService.checkBioMetricDetails(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"9"));
        }else{
            call=apiService.checkBioMetricDetails(paraGetDetails2("11",applicationController.getSchoolId(), applicationController.getPeriodID(),"9"));
        }
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    if (Type.equals("D")){
                        editBioMetricDetails.setVisibility(View.GONE);

                    }else{
                        editBioMetricDetails.setVisibility(View.VISIBLE);

                    }
                }
                if (response.body().get(0).get("Availabilty").getAsString().equals("No")){
                    layoutBioMetric.setVisibility(View.GONE);
                    dialog2.dismiss();
                    edtBioMetricMachineAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());


                }else {
                    edtBioMetricMachineAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());
                    edtBioMetricInstallationYear.setText(response.body().get(0).get("InstallationYear").getAsString());
                    edtuserbiometricStaff.setText(response.body().get(0).get("BiometricUseStaff").getAsString());
                    edtuserbiometricStudent.setText(response.body().get(0).get("BiometricUseStudent").getAsString());
                    edtBioMetricMachinecountOnSubmit.setText(response.body().get(0).get("NoOfMachines").getAsString());
                    edtBiometricWorkingStatus.setText(response.body().get(0).get("WorkingStatus").getAsString());
                    try {
                        String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                        OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_BioMetricDetails.this,StaffPhotoPathList, applicationController.getUsertype());
                        recyclerViewBioMetricOnSubmit.setAdapter(onlineImageRecViewAdapter);

                    }catch (Exception e){
                        recyclerViewBioMetricOnSubmit.setVisibility(View.GONE);
                    }
                    dialog2.dismiss();


                }


            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
dialog2.dismiss();
                Toast.makeText(OnSubmit_BioMetricDetails.this, "Something went wrong please try again!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void disableEditbox() {
        edtBioMetricMachineAvailabelty.setEnabled(false);
                edtBioMetricInstallationYear.setEnabled(false);
        edtuserbiometricStaff.setEnabled(false);
                edtuserbiometricStudent.setEnabled(false);
        edtBiometricWorkingStatus.setEnabled(false);

        edtBioMetricMachineAvailabelty.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                edtBioMetricInstallationYear.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        edtuserbiometricStaff.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                edtuserbiometricStudent.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        edtBiometricWorkingStatus.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

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