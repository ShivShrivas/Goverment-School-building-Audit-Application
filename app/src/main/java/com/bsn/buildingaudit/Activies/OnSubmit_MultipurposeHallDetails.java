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

public class OnSubmit_MultipurposeHallDetails extends AppCompatActivity {
EditText edtMultipurposeHall,edtMultiPurposeHallStatus,edtSittingCapacity;
RecyclerView recyclerViewMultipurposeHallOnSubmit;
    ApplicationController applicationController;
    LinearLayout linearLayout21;
    Button multipurposeApproveBtn,multipurposeRejectBtn;
    String ParentID,InspectionId;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();
    Boolean remarkAlreadyDoneFlag=false;

    Call<List<JsonObject>> call;
    String Type;
    TextView userName,schoolAddress,schoolName,editMultipurposeHallDetails;
    LinearLayout linearLayout5,linearLayout13,constraintLayout9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_multipurpose_hall_details);
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
        multipurposeApproveBtn=findViewById(R.id.multipurposeApproveBtn);
        multipurposeRejectBtn=findViewById(R.id.multipurposeRejectBtn);
        linearLayout21=findViewById(R.id.linearLayout21);
        linearLayout5=findViewById(R.id.linearLayout5);
        constraintLayout9=findViewById(R.id.constraintLayout9);
        linearLayout13=findViewById(R.id.linearLayout13);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        edtMultipurposeHall=findViewById(R.id.edtMultipurposeHall);
        edtMultiPurposeHallStatus=findViewById(R.id.edtMultiPurposeHallStatus);
        edtSittingCapacity=findViewById(R.id.edtMHsittingCapacityOnSub);
        editMultipurposeHallDetails=findViewById(R.id.editMultipurposeHallDetails);
        recyclerViewMultipurposeHallOnSubmit=findViewById(R.id.recyclerViewMultipurposeHallOnSubmit);
        if (Type.equals("D")){
            linearLayout21.setVisibility(View.VISIBLE);
        }
        disableEditBox();
        recyclerViewMultipurposeHallOnSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        editMultipurposeHallDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_MultipurposeHallDetails.this,UpdateDetailsMultipurposeHall.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
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

                        Toast.makeText(OnSubmit_MultipurposeHallDetails.this, "" + approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "onResponse: " + approveRejectRemarksDataModel.getData());
                        arrayListRemarks = approveRejectRemarksDataModel.getData();
                        Dialog dialogForRemark = new Dialog(OnSubmit_MultipurposeHallDetails.this);
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
        multipurposeApproveBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(OnSubmit_MultipurposeHallDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

        multipurposeRejectBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(OnSubmit_MultipurposeHallDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
        if (applicationController.getUsertype().equals("VA")){
            call=apiService.checkMultiPurposeHall(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"23"));
        }else{
            call=apiService.checkMultiPurposeHall(paraGetDetails2("11",applicationController.getSchoolId(), applicationController.getPeriodID(),"23"));
        }
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+response);
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    if (Type.equals("D")){
                        editMultipurposeHallDetails.setVisibility(View.GONE);

                    }else{
                        editMultipurposeHallDetails.setVisibility(View.VISIBLE);

                    }
                }
                if (response.body().get(0).get("Availability").getAsString().equals("No")){
                    linearLayout5.setVisibility(View.GONE);
                            constraintLayout9.setVisibility(View.GONE);
                    linearLayout13.setVisibility(View.GONE);
                    edtMultipurposeHall.setText(response.body().get(0).get("Availability").getAsString());
                    dialog2.dismiss();
                }else{
                    edtMultipurposeHall.setText(response.body().get(0).get("Availability").getAsString());

                    edtMultiPurposeHallStatus.setText(response.body().get(0).get("PhysicalStatus").getAsString());


                    edtSittingCapacity.setText(response.body().get(0).get("SittingCapacity").getAsString());
                    try {String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                        OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_MultipurposeHallDetails.this,StaffPhotoPathList, applicationController.getUsertype());
                        recyclerViewMultipurposeHallOnSubmit.setAdapter(onlineImageRecViewAdapter);

                    }catch (Exception e){
                        recyclerViewMultipurposeHallOnSubmit.setVisibility(View.GONE);
                        linearLayout5.setVisibility(View.GONE);
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

    private void disableEditBox() {
        edtMultipurposeHall.setEnabled(false);
                edtMultiPurposeHallStatus.setEnabled(false);
        edtSittingCapacity.setEnabled(false);
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