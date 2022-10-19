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

public class OnSubmit_SoundSystemDetails extends AppCompatActivity {
EditText edtSchoolBandForGirls,edtSchoolBand,edtSoundSystem;
RecyclerView recyclerViewSoundSystmOnSub;
    ApplicationController applicationController;
    Call<List<JsonObject>> call;
    Button soundSystemApproveBtn,soundSystemRejectBtn;
    LinearLayout linearLayout21;
    String ParentID,InspectionId;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();
    Boolean remarkAlreadyDoneFlag=false;
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
        ParentID=i.getStringExtra("ParamId");
        InspectionId=i.getStringExtra("InspectionId");

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        dialog2.show();
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        soundSystemRejectBtn=findViewById(R.id.soundSystemRejectBtn);
        soundSystemApproveBtn=findViewById(R.id.soundSystemApproveBtn);
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
        if (Type.equals("D")) {
            JsonObject json = new JsonObject();
            json.addProperty("SchoolID", applicationController.getSchoolId());
            json.addProperty("PeriodID", applicationController.getPeriodID());
            json.addProperty("ParamId", ParentID);
            json.addProperty("InsRecordId", InspectionId);
            Call<ApproveRejectRemarksDataModel> callz = apiService.getpriviousSubmittedDataByDIOS(json);
            callz.enqueue(new Callback<ApproveRejectRemarksDataModel>() {
                @Override
                public void onResponse(Call<ApproveRejectRemarksDataModel> call, Response<ApproveRejectRemarksDataModel> response) {
                    Log.d("TAG", "onResponse: " + response.body());
                    ApproveRejectRemarksDataModel approveRejectRemarksDataModel = response.body();
                    Log.d("TAG", "onResponse: " + approveRejectRemarksDataModel.getStatus());
                    if (!approveRejectRemarksDataModel.getStatus().equals("No Record Found")) {

                        Toast.makeText(OnSubmit_SoundSystemDetails.this, "" + approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "onResponse: " + approveRejectRemarksDataModel.getData());
                        arrayListRemarks = approveRejectRemarksDataModel.getData();
                        Dialog dialogForRemark = new Dialog(OnSubmit_SoundSystemDetails.this);
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
                    Log.d("TAG", "onFailure: " + t.getMessage());
                }
            });
        }
        soundSystemApproveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: "+ParentID);
                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InsType","A");
                jsonObject1.addProperty("ParamId",ParentID);
                Call<ArrayList<ApproveRejectRemarkModel>> call1=apiService.getApproveRejectRemark(jsonObject1);
                call1.enqueue(new Callback<ArrayList<ApproveRejectRemarkModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ApproveRejectRemarkModel>> call, Response<ArrayList<ApproveRejectRemarkModel>> response) {
                        ArrayList<ApproveRejectRemarkModel> arrayList=response.body();
                        StaticFunctions.showDialogApprove(OnSubmit_SoundSystemDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
        soundSystemRejectBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(OnSubmit_SoundSystemDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
        if (applicationController.getUsertype().equals("VA")){
            call=apiService.checkSoundSystem(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"22"));
        }else{
            call=apiService.checkSoundSystem(paraGetDetails2("11",applicationController.getSchoolId(), applicationController.getPeriodID(),"22"));
        }
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