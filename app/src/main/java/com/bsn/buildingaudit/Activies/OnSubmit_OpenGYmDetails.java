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

public class OnSubmit_OpenGYmDetails extends AppCompatActivity {
    TextView userName,schoolAddress,schoolName;
    EditText edtGymAvailabelty,gymWorkingStatus,edtGymArea;
    RecyclerView recyclerViewGymOnSubmit;
    TextView gymUploadtxt,editOpenGymDetails;
    Call<List<JsonObject>> call;
    ConstraintLayout gymLayout;
    LinearLayout  linearLayout21;
    Button openGymApproveBtn,openGymRejectBtn;
    ApplicationController applicationController;
    String ParentID,InspectionId;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();
    Boolean remarkAlreadyDoneFlag=false;

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
        ParentID=i.getStringExtra("ParamId");
        InspectionId=i.getStringExtra("InspectionId");

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
        openGymApproveBtn=findViewById(R.id.openGymApproveBtn);
        openGymRejectBtn=findViewById(R.id.openGymRejectBtn);
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

                    Toast.makeText(OnSubmit_OpenGYmDetails.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onResponse: "+approveRejectRemarksDataModel.getData());
                    arrayListRemarks=approveRejectRemarksDataModel.getData();
                    Dialog dialogForRemark=new Dialog(OnSubmit_OpenGYmDetails.this);
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
        openGymApproveBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(OnSubmit_OpenGYmDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

        openGymRejectBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(OnSubmit_OpenGYmDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
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
            call=apiService.checkGymDetails(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"6"));
        }else{
            call=apiService.checkGymDetails(paraGetDetails2("11",applicationController.getSchoolId(), applicationController.getPeriodID(),"6"));
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