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
import com.bsn.buildingaudit.Adapters.SmartClassAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.ApproveRejectRemarksDataModel;
import com.bsn.buildingaudit.Model.Datum;
import com.bsn.buildingaudit.Model.SmartClassesCard;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSubmit_SmartClassDetails extends AppCompatActivity {
RecyclerView smartClassONSubmitRecView;
LinearLayout linearLayout21;
RecyclerView recyclerViewSmartClassONSubmit;
    ApplicationController applicationController;
    private TextView schoolAddress,schoolName,editSmartClassDetails;
    ConstraintLayout layoutSmartClass;
    String Type;
    String ParentID;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();
    Boolean remarkAlreadyDoneFlag=false;

    Button smartClassRejectBtn,smartClassApproveBtn;
    Call<List<JsonObject>> call;
    EditText edtsmartClassAvailabilty,edtDigitalBoardSmartClass,edtLearningContentSmartClass,edtProjectorSmartClass,edtTeacherAvailbilitySmartClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_smart_class_details);
        applicationController= (ApplicationController) getApplication();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Dialog dialog2 = new Dialog(this);

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        dialog2.show();
        Intent i=getIntent();
        Type=i.getStringExtra("Type");
        ParentID=i.getStringExtra("ParamId");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        schoolName=findViewById(R.id.schoolName);
        smartClassRejectBtn=findViewById(R.id.smartClassRejectBtn);
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        edtsmartClassAvailabilty=findViewById(R.id.edtsmartClassAvailabilty);
        linearLayout21=findViewById(R.id.linearLayout21);
        smartClassApproveBtn=findViewById(R.id.smartClassApproveBtn);
        edtDigitalBoardSmartClass=findViewById(R.id.edtDigitalBoardSmartClass);
        edtLearningContentSmartClass=findViewById(R.id.edtLearningContentSmartClass);
        edtProjectorSmartClass=findViewById(R.id.edtProjectorSmartClass);
        edtTeacherAvailbilitySmartClass=findViewById(R.id.edtTeacherAvailbilitySmartClass);
        smartClassONSubmitRecView=findViewById(R.id.smartClassONSubmitRecView);
        recyclerViewSmartClassONSubmit=findViewById(R.id.recyclerViewSmartClassONSubmit);
        layoutSmartClass=findViewById(R.id.layoutSmartClass);
        editSmartClassDetails=findViewById(R.id.editSmartClassDetails);
        if (Type.equals("D")){
            linearLayout21.setVisibility(View.VISIBLE);
        }
        recyclerViewSmartClassONSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        editSmartClassDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_SmartClassDetails.this,UpdateDetailsSmartClass.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
        disableEditBox();
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

                    Toast.makeText(OnSubmit_SmartClassDetails.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onResponse: "+approveRejectRemarksDataModel.getData());
                    arrayListRemarks=approveRejectRemarksDataModel.getData();
                    Dialog dialogForRemark=new Dialog(OnSubmit_SmartClassDetails.this);
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
        smartClassApproveBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(OnSubmit_SmartClassDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

        smartClassRejectBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(OnSubmit_SmartClassDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
        if (applicationController.getUsertype().equals("VA")){
            call=apiService.checkSmartClassDetails(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"8"));
        }else{
            call=apiService.checkSmartClassDetails(paraGetDetails2("13",applicationController.getSchoolId(), applicationController.getPeriodID(),"8"));
        }
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
                public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                    Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    editSmartClassDetails.setVisibility(View.VISIBLE);
                }
                    if (response.body().get(0).get("Availablity").getAsString().equals("No")){
                        edtsmartClassAvailabilty.setText(response.body().get(0).get("Availablity").getAsString());

                        layoutSmartClass.setVisibility(View.GONE);
                        dialog2.dismiss();

                    }else{

                        ArrayList<SmartClassesCard> smartClassesCards=new ArrayList<>();
                        edtsmartClassAvailabilty.setText(response.body().get(0).get("Availablity").getAsString());
                        edtDigitalBoardSmartClass.setText(response.body().get(0).get("DigitalBoard").getAsString());
                        edtLearningContentSmartClass.setText(response.body().get(0).get("LearningContent").getAsString());
                        edtProjectorSmartClass.setText(response.body().get(0).get("Projector").getAsString());
                        edtTeacherAvailbilitySmartClass.setText(response.body().get(0).get("TeacherAvl").getAsString());
                        for (int i=0;i<response.body().size();i++){
                            SmartClassesCard smartClassesCard=new SmartClassesCard();

                            String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                            OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_SmartClassDetails.this,StaffPhotoPathList, applicationController.getUsertype());
                            recyclerViewSmartClassONSubmit.setAdapter(onlineImageRecViewAdapter);
                            smartClassesCard.setCompanyName(response.body().get(i).get("CompanyName").getAsString());
                            smartClassesCard.setName(response.body().get(i).get("Name").getAsString());
                            smartClassesCard.setScheme(response.body().get(i).get("Scheme").getAsString());
                            smartClassesCard.setInstallationYear(response.body().get(i).get("InstallationYear").getAsString());
                            smartClassesCard.setWorkingStatus(response.body().get(i).get("WorkingStatus").getAsString());
                            smartClassesCard.setSrno(response.body().get(i).get("Srno").getAsString());
                            smartClassesCards.add(smartClassesCard);

                        }
                        smartClassONSubmitRecView.setLayoutManager(new LinearLayoutManager(OnSubmit_SmartClassDetails.this,RecyclerView.HORIZONTAL,false));
                        smartClassONSubmitRecView.setAdapter(new SmartClassAdapter(OnSubmit_SmartClassDetails.this,smartClassesCards));
                        (new SmartClassAdapter(OnSubmit_SmartClassDetails.this,smartClassesCards)).notifyDataSetChanged();

                        String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                        OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_SmartClassDetails.this,StaffPhotoPathList, applicationController.getUsertype());
                        recyclerViewSmartClassONSubmit.setAdapter(onlineImageRecViewAdapter);
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
        edtsmartClassAvailabilty.setEnabled(false);
                edtDigitalBoardSmartClass.setEnabled(false);
        edtLearningContentSmartClass.setEnabled(false);
                edtProjectorSmartClass.setEnabled(false);
        edtTeacherAvailbilitySmartClass.setEnabled(false);
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