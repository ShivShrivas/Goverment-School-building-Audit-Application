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

public class OnSubmit_BoundryWalls extends AppCompatActivity {
EditText edtWallCondition,edtBoundryScheme,edtWhiteWash,edtTypeBoundaryWall,edtLengthofWall,edtAreaofSchool,edtBoundaryWallAvail;
    ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName;
    RecyclerView recyclerViewTwoTypeBoundarywallOnSub;
    ConstraintLayout layoutBoundry;
    TextView uploadBoundary,editBoundryWallsDetails;
    Call<List<JsonObject>> call;
    LinearLayout linearLayout21;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();
    Boolean remarkAlreadyDoneFlag=false;

    Button boundryWallRejectBtn,boundryWallApproveBtn;
    String Type;
    String ParentID,InspectionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_boundry_walls);


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
        boundryWallApproveBtn=findViewById(R.id.boundryWallApproveBtn);
        boundryWallRejectBtn=findViewById(R.id.boundryWallRejectBtn);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        Intent i=getIntent();
        Type=i.getStringExtra("Type");
        ParentID=i.getStringExtra("ParamId");
        InspectionId=i.getStringExtra("InspectionId");

        edtWallCondition=findViewById(R.id.edtWallCondition);
        uploadBoundary=findViewById(R.id.uploadBoundary);
        linearLayout21=findViewById(R.id.linearLayout21);
        layoutBoundry=findViewById(R.id.layoutBoundry);
        edtBoundryScheme=findViewById(R.id.edtBoundryScheme);
        edtWhiteWash=findViewById(R.id.edtWhiteWash);
        edtTypeBoundaryWall=findViewById(R.id.edtTypeBoundaryWall);
        edtLengthofWall=findViewById(R.id.edtLengthofWall);
        edtAreaofSchool=findViewById(R.id.edtAreaofSchool);
        edtBoundaryWallAvail=findViewById(R.id.edtBoundaryWallAvail);
        editBoundryWallsDetails=findViewById(R.id.editBoundryWallsDetails);
        recyclerViewTwoTypeBoundarywallOnSub=findViewById(R.id.recyclerViewTwoTypeBoundarywallOnSub);
        disableEditbox();
        if (Type.equals("D")){
            linearLayout21.setVisibility(View.VISIBLE);
        }
        editBoundryWallsDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_BoundryWalls.this,UpdateDetailsBoundryWall.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
        recyclerViewTwoTypeBoundarywallOnSub.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
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

                        Toast.makeText(OnSubmit_BoundryWalls.this, "" + approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "onResponse: " + approveRejectRemarksDataModel.getData());
                        arrayListRemarks = approveRejectRemarksDataModel.getData();
                        Dialog dialogForRemark = new Dialog(OnSubmit_BoundryWalls.this);
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
        boundryWallRejectBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(OnSubmit_BoundryWalls.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

        boundryWallApproveBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(OnSubmit_BoundryWalls.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
        if (applicationController.getUsertype().equals("VA")){
            call=apiService.checkBoundryWall(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"15"));
        }else{
            call=apiService.checkBoundryWall(paraGetDetails2("12",applicationController.getSchoolId(), applicationController.getPeriodID(),"15"));
        }
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    if (Type.equals("D")){
                        editBoundryWallsDetails.setVisibility(View.GONE);

                    }else{
                        editBoundryWallsDetails.setVisibility(View.VISIBLE);

                    }
                }
                if (response.body().get(0).get("Availabilty").getAsString().equals("No")){
                    edtBoundaryWallAvail.setText(response.body().get(0).get("Availabilty").getAsString());
                    layoutBoundry.setVisibility(View.GONE);
                    dialog2.dismiss();
                }else {
                    edtWallCondition.setText(response.body().get(0).get("Condition").getAsString());

                    edtBoundryScheme.setText(response.body().get(0).get("Scheme").getAsString());
                    edtWhiteWash.setText(response.body().get(0).get("WhiteWashStatus").getAsString());
                    edtTypeBoundaryWall.setText(response.body().get(0).get("BoundaryWallType").getAsString());
                    edtLengthofWall.setText(response.body().get(0).get("BoundaryWallLength").getAsString());
                    edtAreaofSchool.setText(response.body().get(0).get("SchoolArea").getAsString());
                    edtBoundaryWallAvail.setText(response.body().get(0).get("Availabilty").getAsString());
                    try {
                        String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                        OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_BoundryWalls.this,StaffPhotoPathList, applicationController.getUsertype());
                        recyclerViewTwoTypeBoundarywallOnSub.setAdapter(onlineImageRecViewAdapter);
                    }catch (Exception e){
                        recyclerViewTwoTypeBoundarywallOnSub.setVisibility(View.GONE);
                        uploadBoundary.setVisibility(View.GONE);
                    }


                    dialog2.dismiss();
                }


            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();
                Toast.makeText(getApplicationContext(), "Something went wrong please try again!!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void disableEditbox() {
        edtWallCondition.setEnabled(false);
                edtBoundryScheme.setEnabled(false);
        edtWhiteWash.setEnabled(false);
                edtTypeBoundaryWall.setEnabled(false);
        edtLengthofWall.setEnabled(false);
                edtAreaofSchool.setEnabled(false);
        edtBoundaryWallAvail.setEnabled(false);
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