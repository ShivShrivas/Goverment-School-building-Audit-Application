package com.bsn.buildingaudit.DIOS.ExtraActivitiesDetails;

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

import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.ApproveRejectRemarksDataModel;
import com.bsn.buildingaudit.Model.Datum;
import com.bsn.buildingaudit.Model.GetNCCParticipationDetailsModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Participation_Details extends AppCompatActivity {
ApplicationController applicationController;
Button participationsRejectBtn,participationsApproveBtn;
TextView REPUBLICDAYSTATUS,THALSENASTATUS,NAUSENASTATUS,   VAYUSENASTATUS,MOUNTCAMPSTATUS, TREKINGCAMPSTATUS
           , EKBHARATSTATUS, SPECIALNICSTATUS, LOCALREPUBLICCAMPSTATUS,CADETAPPEREDA,  CADETPASSEDA, CADETAPPEREDB
            ,CADETPASSEDB,CADETAPPEREDC,  CADETPASSEDC, BESTCADETAWARDSTATUS, CMSCHOLARSHIPSTATUS, CWCSCHOLARSHIPSTATUS
           , CMMEDALSTATUS;
    Intent i;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();
    Boolean remarkAlreadyDoneFlag=false;

    String ParentID,InspectionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participation_details);
        Window window = getWindow();
        applicationController= (ApplicationController) getApplication();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      REPUBLICDAYSTATUS=findViewById(R.id.REPUBLICDAYSTATUS);
      THALSENASTATUS=findViewById(R.id.THALSENASTATUS);
      NAUSENASTATUS=findViewById(R.id.NAUSENASTATUS);
      VAYUSENASTATUS=findViewById(R.id.VAYUSENASTATUS);
      MOUNTCAMPSTATUS=findViewById(R.id.MOUNTCAMPSTATUS);
        i=getIntent();
        ParentID=i.getStringExtra("ParamId");
        InspectionId=i.getStringExtra("InspectionId");
      TREKINGCAMPSTATUS=findViewById(R.id.TREKINGCAMPSTATUS);
      EKBHARATSTATUS=findViewById(R.id.EKBHARATSTATUS);
      SPECIALNICSTATUS=findViewById(R.id.SPECIALNICSTATUS);
      LOCALREPUBLICCAMPSTATUS=findViewById(R.id.LOCALREPUBLICCAMPSTATUS);
      CADETAPPEREDA=findViewById(R.id.CADETAPPEREDA);
      CADETPASSEDA=findViewById(R.id.CADETPASSEDA);
      CADETAPPEREDB=findViewById(R.id.CADETAPPEREDB);
      CADETPASSEDB=findViewById(R.id.CADETPASSEDB);
      CADETAPPEREDC=findViewById(R.id.CADETAPPEREDC);
      CADETPASSEDC=findViewById(R.id.CADETPASSEDC);
        participationsApproveBtn=findViewById(R.id.participationsApproveBtn);
        participationsRejectBtn=findViewById(R.id.participationsRejectBtn);
      BESTCADETAWARDSTATUS=findViewById(R.id.BESTCADETAWARDSTATUS);
      CMSCHOLARSHIPSTATUS=findViewById(R.id.CMSCHOLARSHIPSTATUS);
      CWCSCHOLARSHIPSTATUS=findViewById(R.id.CWCSCHOLARSHIPSTATUS);
      CMMEDALSTATUS=findViewById(R.id.CMMEDALSTATUS);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID",applicationController.getSchoolId());
        jsonObject.addProperty("PeriodID",applicationController.getPeriodID());
        Log.d("TAG", "onCreate: "+jsonObject);
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

                    Toast.makeText(Participation_Details.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onResponse: "+approveRejectRemarksDataModel.getData());
                    arrayListRemarks=approveRejectRemarksDataModel.getData();
                    Dialog dialogForRemark=new Dialog(Participation_Details.this);
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
        Call<GetNCCParticipationDetailsModel> call= apiService.getNCCParticipationDetails(jsonObject);
        call.enqueue(new Callback<GetNCCParticipationDetailsModel>() {
            @Override
            public void onResponse(Call<GetNCCParticipationDetailsModel> call, Response<GetNCCParticipationDetailsModel> response) {
                GetNCCParticipationDetailsModel getNCCParticipationDetailsModel=response.body();
              REPUBLICDAYSTATUS.setText(getNCCParticipationDetailsModel.getRepublicdaystatus()==null?"":getNCCParticipationDetailsModel.getRepublicdaystatus());
              THALSENASTATUS.setText(getNCCParticipationDetailsModel.getThalsenastatus()==null?"":getNCCParticipationDetailsModel.getThalsenastatus());
              NAUSENASTATUS.setText(getNCCParticipationDetailsModel.getNausenastatus()==null?"":getNCCParticipationDetailsModel.getNausenastatus());
              VAYUSENASTATUS.setText(getNCCParticipationDetailsModel.getVayusenastatus()==null?"":getNCCParticipationDetailsModel.getVayusenastatus());
              MOUNTCAMPSTATUS.setText(getNCCParticipationDetailsModel.getMountcampstatus()==null?"":getNCCParticipationDetailsModel.getMountcampstatus());
              TREKINGCAMPSTATUS.setText(getNCCParticipationDetailsModel.getTrekingcampstatus()==null?"":getNCCParticipationDetailsModel.getTrekingcampstatus());
              EKBHARATSTATUS.setText(getNCCParticipationDetailsModel.getEkbharatstatus()==null?"":getNCCParticipationDetailsModel.getEkbharatstatus());
              SPECIALNICSTATUS.setText(getNCCParticipationDetailsModel.getSpecialnicstatus()==null?"":getNCCParticipationDetailsModel.getSpecialnicstatus());
              LOCALREPUBLICCAMPSTATUS.setText(getNCCParticipationDetailsModel.getLocalrepubliccampstatus()==null?"":getNCCParticipationDetailsModel.getLocalrepubliccampstatus());
              CADETAPPEREDA.setText(getNCCParticipationDetailsModel.getCadetappereda()==null?"":getNCCParticipationDetailsModel.getCadetappereda());
              CADETPASSEDA.setText(getNCCParticipationDetailsModel.getCadetpasseda()==null?"":getNCCParticipationDetailsModel.getCadetpasseda());
              CADETAPPEREDB.setText(getNCCParticipationDetailsModel.getCadetappereda()==null?"":getNCCParticipationDetailsModel.getCadetappereda());
              CADETPASSEDB.setText(getNCCParticipationDetailsModel.getCadetpasseda()==null?"":getNCCParticipationDetailsModel.getCadetpasseda());
              CADETAPPEREDC.setText(getNCCParticipationDetailsModel.getCadetapperedc()==null?"":getNCCParticipationDetailsModel.getCadetapperedc());
              CADETPASSEDC.setText(getNCCParticipationDetailsModel.getCadetpasseda()==null?"":getNCCParticipationDetailsModel.getCadetpasseda());
              BESTCADETAWARDSTATUS.setText(getNCCParticipationDetailsModel.getBestcadetawardstatus()==null?"":getNCCParticipationDetailsModel.getBestcadetawardstatus());
              CMSCHOLARSHIPSTATUS.setText(getNCCParticipationDetailsModel.getCmscholarshipstatus()==null?"":getNCCParticipationDetailsModel.getCmscholarshipstatus());
              CWCSCHOLARSHIPSTATUS.setText(getNCCParticipationDetailsModel.getCwcscholarshipstatus()==null?"":getNCCParticipationDetailsModel.getCwcscholarshipstatus());
              CMMEDALSTATUS.setText(getNCCParticipationDetailsModel.getCmmedalstatus()==null?"":getNCCParticipationDetailsModel.getCmmedalstatus());
            }

            @Override
            public void onFailure(Call<GetNCCParticipationDetailsModel> call, Throwable t) {

            }
        });
        participationsRejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: "+ParentID);
                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InsType","R");
                jsonObject1.addProperty("ParamId",ParentID);
                Call<ArrayList<ApproveRejectRemarkModel>> call1=apiService.getApproveRejectRemark(jsonObject1);
                call1.enqueue(new Callback<ArrayList<ApproveRejectRemarkModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ApproveRejectRemarkModel>> call, Response<ArrayList<ApproveRejectRemarkModel>> response) {
                        ArrayList<ApproveRejectRemarkModel> arrayList=response.body();
                        StaticFunctions.showDialogReject(Participation_Details.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

        participationsApproveBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(Participation_Details.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
    }

}