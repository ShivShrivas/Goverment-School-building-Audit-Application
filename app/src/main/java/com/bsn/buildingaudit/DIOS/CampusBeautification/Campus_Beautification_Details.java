package com.bsn.buildingaudit.DIOS.CampusBeautification;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.TreeDetailsAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.ApproveRejectRemarksDataModel;
import com.bsn.buildingaudit.Model.CampusPlantationDetalsModel;
import com.bsn.buildingaudit.Model.Datum;
import com.bsn.buildingaudit.Model.TreeData;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Campus_Beautification_Details extends AppCompatActivity {
ApplicationController applicationController;
TreeDetailsAdapter adapter;
RecyclerView treeDetailsRecview;
    Intent i;
    String ParentID,InspectionId;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();
    Boolean remarkAlreadyDoneFlag=false;

    Button campusBeautificationApproveBtn,campusBeautificationRejectBtn;
TextView spinnerDisplayBoard,spinnerEcoClub,spinnerAvailabilityDustbin,spinnerWallPainting,spinnerPlant1,survivedTree
        ,targetedPlantation,PlantED;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_beautification_details);
        applicationController= (ApplicationController) getApplication();
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        treeDetailsRecview=findViewById(R.id.treeDetailsRecview);
        spinnerDisplayBoard=findViewById(R.id.spinnerDisplayBoard);
        spinnerEcoClub=findViewById(R.id.spinnerEcoClub);
        spinnerAvailabilityDustbin=findViewById(R.id.spinnerAvailabilityDustbin);
        spinnerWallPainting=findViewById(R.id.spinnerWallPainting);
        spinnerPlant1=findViewById(R.id.spinnerPlant1);
        i=getIntent();
        ParentID=i.getStringExtra("ParamId");
        InspectionId=i.getStringExtra("InspectionId");
        campusBeautificationApproveBtn=findViewById(R.id.campusBeautificationApproveBtn);
        campusBeautificationRejectBtn=findViewById(R.id.campusBeautificationRejectBtn);
        PlantED=findViewById(R.id.PlantED);
        targetedPlantation=findViewById(R.id.targetedPlantation);
        survivedTree=findViewById(R.id.survivedTree);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        treeDetailsRecview.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID",applicationController.getSchoolId());
        jsonObject.addProperty("PeriodID",applicationController.getPeriodID());
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
               
                ApproveRejectRemarksDataModel approveRejectRemarksDataModel=response.body();
               
                if (!approveRejectRemarksDataModel.getStatus().equals("No Record Found")){

                    Toast.makeText(Campus_Beautification_Details.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                   
                    arrayListRemarks=approveRejectRemarksDataModel.getData();
                    Dialog dialogForRemark=new Dialog(Campus_Beautification_Details.this);
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
               
            }
        });
        Call<CampusPlantationDetalsModel> call=apiService.getPlantationDetals(jsonObject);
        call.enqueue(new Callback<CampusPlantationDetalsModel>() {
            @Override
            public void onResponse(Call<CampusPlantationDetalsModel> call, Response<CampusPlantationDetalsModel> response) {
                CampusPlantationDetalsModel campusPlantationDetalsModel=response.body();
                spinnerPlant1.setText(campusPlantationDetalsModel.getPlantation().toString());
                spinnerWallPainting.setText(campusPlantationDetalsModel.getWallslogan().toString());
                spinnerAvailabilityDustbin.setText(campusPlantationDetalsModel.getDustbins().toString());
                spinnerEcoClub.setText(campusPlantationDetalsModel.getEchoclub().toString());
                spinnerDisplayBoard.setText(campusPlantationDetalsModel.getDisplaynotice().toString());
                survivedTree.setText(campusPlantationDetalsModel.getSurvive().toString());
                targetedPlantation.setText(campusPlantationDetalsModel.getPlanttarget().toString());
                PlantED.setText(campusPlantationDetalsModel.getPlanted().toString());

                ArrayList<TreeData> arrayList=new ArrayList<>();
                arrayList.addAll(campusPlantationDetalsModel.getTreeDatas());
                adapter =new TreeDetailsAdapter(Campus_Beautification_Details.this,arrayList);
                treeDetailsRecview.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<CampusPlantationDetalsModel> call, Throwable t) {

            }
        });
        campusBeautificationApproveBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(Campus_Beautification_Details.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

        campusBeautificationRejectBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(Campus_Beautification_Details.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
    }
}