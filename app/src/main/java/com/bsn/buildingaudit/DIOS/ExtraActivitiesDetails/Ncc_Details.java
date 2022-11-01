package com.bsn.buildingaudit.DIOS.ExtraActivitiesDetails;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.ApproveRejectRemarksDataModel;
import com.bsn.buildingaudit.Model.Datum;
import com.bsn.buildingaudit.Model.GetNCCDetailsModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ncc_Details extends AppCompatActivity {
ApplicationController applicationController;
LinearLayout nccDetailLayout;
ConstraintLayout constraintLayout40;
    Intent i;
    String ParentID,InsRecordId;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();
    Boolean remarkAlreadyDoneFlag=false;

    Button nccDetailsRejectedBtn,nccDetatilsApproveBtn;
TextView NCCRUNNINGSTATUS,NCCYEARRAISINGSTATUS,NCCGROUNDSTATUS,TRAINEDANOSTATUS,SSCDPARTICIPATION,
    WINGSDYEAR, WINGSDNOCADETS, WINGSWYEAR, WINGSWNOCADETS, WINGJDYEAR,WINGJDNOCADETS;
CheckBox WINGARMYSTATUS, WINGNAVALSTATUS, WINGAIRSTATUS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncc_details);
        applicationController= (ApplicationController) getApplication();
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        i=getIntent();
        ParentID=i.getStringExtra("ParamId");
        InsRecordId=i.getStringExtra("InsRecordId");
     NCCRUNNINGSTATUS=findViewById(R.id.NCCRUNNINGSTATUS);
        nccDetailLayout=findViewById(R.id.nccDetailLayout);
        constraintLayout40=findViewById(R.id.constraintLayout40);
        nccDetatilsApproveBtn=findViewById(R.id.nccDetatilsApproveBtn);
        nccDetailsRejectedBtn=findViewById(R.id.nccDetailsRejectedBtn);
     NCCYEARRAISINGSTATUS=findViewById(R.id.NCCYEARRAISINGSTATUS);
     NCCGROUNDSTATUS=findViewById(R.id.NCCGROUNDSTATUS);
     TRAINEDANOSTATUS=findViewById(R.id.TRAINEDANOSTATUS);
     SSCDPARTICIPATION=findViewById(R.id.SSCDPARTICIPATION);
     WINGSDYEAR=findViewById(R.id.WINGSDYEAR);
     WINGSDNOCADETS=findViewById(R.id.WINGSDNOCADETS);
     WINGSWYEAR=findViewById(R.id.WINGSWYEAR);
     WINGSWNOCADETS=findViewById(R.id.WINGSWNOCADETS);
     WINGJDYEAR=findViewById(R.id.WINGJDYEAR);
     WINGJDNOCADETS=findViewById(R.id.WINGJDNOCADETS);
     WINGARMYSTATUS=findViewById(R.id.WINGARMYSTATUS);
     WINGNAVALSTATUS=findViewById(R.id.WINGNAVALSTATUS);
     WINGAIRSTATUS=findViewById(R.id.WINGAIRSTATUS);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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
        json.addProperty("InsRecordId",InsRecordId);
        Call<ApproveRejectRemarksDataModel> callz=apiService.getpriviousSubmittedDataByDIOS(json);
        callz.enqueue(new Callback<ApproveRejectRemarksDataModel>() {
            @Override
            public void onResponse(Call<ApproveRejectRemarksDataModel> call, Response<ApproveRejectRemarksDataModel> response) {
               
                ApproveRejectRemarksDataModel approveRejectRemarksDataModel=response.body();
               
                if (!approveRejectRemarksDataModel.getStatus().equals("No Record Found")){

                    Toast.makeText(Ncc_Details.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                   
                    arrayListRemarks=approveRejectRemarksDataModel.getData();
                    Dialog dialogForRemark=new Dialog(Ncc_Details.this);
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
        Call<GetNCCDetailsModel> call=apiService.getNCCDetails(jsonObject);
        call.enqueue(new Callback<GetNCCDetailsModel>() {
            @Override
            public void onResponse(Call<GetNCCDetailsModel> call, Response<GetNCCDetailsModel> response)
            {
                                          GetNCCDetailsModel getNCCDetailsModel=response.body();
                                      try{
                                          if (getNCCDetailsModel.getNccrunningstatus().equals("No")){
                                              constraintLayout40.setVisibility(View.GONE);
                                              nccDetailLayout.setVisibility(View.GONE);
                                          }
                                      }catch (Exception e){
                                          constraintLayout40.setVisibility(View.GONE);
                                          nccDetailLayout.setVisibility(View.GONE);
                                      }
                                     NCCRUNNINGSTATUS.setText(getNCCDetailsModel.getNccrunningstatus()==null?"":getNCCDetailsModel.getNccrunningstatus());
                                     NCCYEARRAISINGSTATUS.setText(getNCCDetailsModel.getNccyearraisingstatus()==null?"":getNCCDetailsModel.getNccyearraisingstatus());
                                     NCCGROUNDSTATUS.setText(getNCCDetailsModel.getNccgroundstatus()==null?"":getNCCDetailsModel.getNccgroundstatus());
                                     TRAINEDANOSTATUS.setText(getNCCDetailsModel.getTrainedanostatus()==null?"":getNCCDetailsModel.getTrainedanostatus());
                                     SSCDPARTICIPATION.setText(getNCCDetailsModel.getSscdparticipation()==null?"":getNCCDetailsModel.getSscdparticipation());
                                     WINGSDYEAR.setText(getNCCDetailsModel.getWingsdyear()==null?"":getNCCDetailsModel.getWingsdyear());
                                     WINGSDNOCADETS.setText(getNCCDetailsModel.getWingsdnocadets()==null?"":getNCCDetailsModel.getWingsdnocadets().toString());
                                     WINGSWYEAR.setText(getNCCDetailsModel.getWingswyear()==null?"":getNCCDetailsModel.getWingswyear());
                                     WINGSWNOCADETS.setText(getNCCDetailsModel.getWingswnocadets()==null?"":getNCCDetailsModel.getWingswnocadets().toString());
                                     WINGJDYEAR.setText(getNCCDetailsModel.getWingjdyear()==null?"":getNCCDetailsModel.getWingjdyear());
                                     WINGJDNOCADETS.setText(getNCCDetailsModel.getWingjdnocadets()==null?"":getNCCDetailsModel.getWingjdnocadets().toString());
                                if (getNCCDetailsModel.getWingarmystatus().equals("Yes")){
                                    WINGARMYSTATUS.setChecked(true);
                                }else{
                                    WINGARMYSTATUS.setChecked(false);
                                }
                                 if (getNCCDetailsModel.getWingnavalstatus().equals("Yes")){
                                     WINGNAVALSTATUS.setChecked(true);
                                            }else{
                                     WINGNAVALSTATUS.setChecked(false);

                                            }
                                 if (getNCCDetailsModel.getWingairstatus().equals("Yes")){
                                     WINGAIRSTATUS.setChecked(true);
                                            }else{
                                     WINGAIRSTATUS.setChecked(false);

                                            }
            }

            @Override
            public void onFailure(Call<GetNCCDetailsModel> call, Throwable t) {

            }
        });
        nccDetatilsApproveBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(Ncc_Details.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks,remarkAlreadyDoneFlag,InsRecordId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
        nccDetailsRejectedBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(Ncc_Details.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks,remarkAlreadyDoneFlag,InsRecordId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
    }


}