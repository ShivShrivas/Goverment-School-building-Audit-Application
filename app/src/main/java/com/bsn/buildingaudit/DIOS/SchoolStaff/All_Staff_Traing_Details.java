package com.bsn.buildingaudit.DIOS.SchoolStaff;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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

import com.bsn.buildingaudit.Adapters.PrincipalTrainingAdapter;
import com.bsn.buildingaudit.Adapters.StaffTrainingAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.ApproveRejectRemarksDataModel;
import com.bsn.buildingaudit.Model.Datum;
import com.bsn.buildingaudit.Model.P;
import com.bsn.buildingaudit.Model.PrincipalAndTeacherTrainingModel;
import com.bsn.buildingaudit.Model.T;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class All_Staff_Traing_Details extends AppCompatActivity {
RecyclerView recyclerView,recyclerView2;
ApplicationController applicationController;
TextView principalTraingingProposedTxt,principalTraingingCompTxt,staffTraingingProposedTxt,staffTraingingCompTxt;
    Button approveAllStaffTraing,rejectAllStaffTraing;
PrincipalAndTeacherTrainingModel arrayList;
    ArrayList<P> arrayListPrincipal=new ArrayList<>();
    ArrayList<T> arrayListTeacher=new ArrayList<>();
    StaffTrainingAdapter adapter;
    Intent i;
    String ParentID,InspectionId;
    Boolean remarkAlreadyDoneFlag=false;

    ArrayList<Datum> arrayListRemarks=new ArrayList<>();
    PrincipalTrainingAdapter adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all_staff_traing_details);
        applicationController= (ApplicationController) getApplication();
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        i=getIntent();
        InspectionId=i.getStringExtra("InspectionId");
        ParentID=i.getStringExtra("ParamId");
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView2=findViewById(R.id.recyclerView2);
        staffTraingingCompTxt=findViewById(R.id.staffTraingingCompTxt);
        staffTraingingProposedTxt=findViewById(R.id.staffTraingingProposedTxt);
        principalTraingingCompTxt=findViewById(R.id.principalTraingingCompTxt);
        principalTraingingProposedTxt=findViewById(R.id.principalTraingingProposedTxt);
        approveAllStaffTraing=findViewById(R.id.approveAllStaffTraing);
        rejectAllStaffTraing=findViewById(R.id.rejectAllStaffTraing);
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID",applicationController.getSchoolId());
        jsonObject.addProperty("PeriodID",applicationController.getPeriodID());
       
        recyclerView.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView2.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        arrayList=new PrincipalAndTeacherTrainingModel();
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

                    Toast.makeText(All_Staff_Traing_Details.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                   
                    arrayListRemarks=approveRejectRemarksDataModel.getData();
                    Dialog dialogForRemark=new Dialog(All_Staff_Traing_Details.this);
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
        Call<PrincipalAndTeacherTrainingModel> call=apiService.getPrincipalAndTeacherTraining(jsonObject);
        call.enqueue(new Callback<PrincipalAndTeacherTrainingModel>() {
            @Override
            public void onResponse(Call<PrincipalAndTeacherTrainingModel> call, Response<PrincipalAndTeacherTrainingModel> response) {
               
                arrayList=response.body();
                arrayListPrincipal=arrayList.getP();

                arrayListTeacher=arrayList.getT();
               
               
                adapter=new StaffTrainingAdapter(All_Staff_Traing_Details.this,arrayListTeacher);
                adapter1=new PrincipalTrainingAdapter(All_Staff_Traing_Details.this,arrayListPrincipal);
                recyclerView.setAdapter(adapter1);
                recyclerView2.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                adapter1.notifyDataSetChanged();
                principalTraingingProposedTxt.setText(arrayListPrincipal.get(0).getProposedModule().toString());
                principalTraingingCompTxt.setText(arrayListPrincipal.get(0).getCompletedModule().toString());

  staffTraingingCompTxt.setText(arrayListTeacher.get(0).getProposedModule().toString());
                staffTraingingProposedTxt.setText(arrayListTeacher.get(0).getCompletedModule().toString());

            }

            @Override
            public void onFailure(Call<PrincipalAndTeacherTrainingModel> call, Throwable t) {

            }
        });




        ArrayList<String> myImageNameList = new ArrayList<>();
        myImageNameList.add("All Given Information is correct");



        ArrayList<String> myImageNameList2 = new ArrayList<>();
        myImageNameList2.add("for Principal no. of Proposed training details are not correct");
        myImageNameList2.add("for Principal no. of Completed training details are not correct");
        myImageNameList2.add("Principal training details are not correct");
        myImageNameList2.add("for Teachers no. of Proposed training details are not correct");
        myImageNameList2.add("for Teachers no. of Completed training details are not correct");
        myImageNameList2.add("Teachers training details are not correct");


        approveAllStaffTraing.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(All_Staff_Traing_Details.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

        rejectAllStaffTraing.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(All_Staff_Traing_Details.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
    }
}