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
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.ApproveRejectRemarksDataModel;
import com.bsn.buildingaudit.Model.Datum;
import com.bsn.buildingaudit.Model.GetExtraActivitiesModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Extra_Activities_Details extends AppCompatActivity {
    ApplicationController applicationController;
    TextView SPORTDAYSTATUS,INVITEDCGSPORTDAY  ,TXTSPORTSCHIEFTITLE,ANNUALDAYSTATUS ,INVITEDCGANNUALDAY
            ,INVITEDCGANNUALTITLE ,INVITEDCGADATE,YOGADAYSTATUS ,INTERHOUSESTATUS,WEBSITESTATUS
            ,WEBSITEURL,WEBSITEMAILSTATUS ,WEBSITEMAIL,PTAMEETINGSTATUS ,PTAMEETINGDATE,
            MONTHLYTESTSTATUS ,TEACHERDIARYSTATUS,HEATHCHECKUPSTATUS ,HEATHCHECKUPDATE,
            SCHOOLMAGAZINESTATUS,SPECIALGUESTSTATUS,SMDCMEETSTATUS ,SMDCMEETDATE,SMDCMEETTOTAL
            ,ALUMNISTATUSDATE,ALUMNISTATUS;
    CardView ALUMNISTATUSDATELAYOUT,SMDCMEETTOTALLAYOUT,SMDCMEETDATELAYOUT,HEATHCHECKUPDATELAYOUT,PTAMEETINGDATELAYOUT,
            WEBSITEMAILLAYOUT,WEBSITEURLLAYOUT,INVITEDCGADATELAYOUT,INVITEDCGANNUALTITLELAYOUT,INVITEDCGANNUALDAYLAYOUT
            ,TXTSPORTSCHIEFTITLELAYOUT,    INVITEDCGSPORTDAYLAYOUT;
    Button extraActivitiesApproveBtn,extraActivitiesRejectBtn;
    Intent i;
    String ParentID,InspectionId;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();
    Boolean remarkAlreadyDoneFlag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_activities_details);
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
        ALUMNISTATUSDATELAYOUT=findViewById(R.id.ALUMNISTATUSDATELAYOUT);
        SMDCMEETTOTALLAYOUT=findViewById(R.id.SMDCMEETTOTALLAYOUT);
        extraActivitiesApproveBtn=findViewById(R.id.extraActivitiesApproveBtn);
        extraActivitiesRejectBtn=findViewById(R.id.extraActivitiesRejectBtn);
        SMDCMEETDATELAYOUT=findViewById(R.id.SMDCMEETDATELAYOUT);
        HEATHCHECKUPDATELAYOUT=findViewById(R.id.HEATHCHECKUPDATELAYOUT);
        PTAMEETINGDATELAYOUT=findViewById(R.id.PTAMEETINGDATELAYOUT);
        WEBSITEMAILLAYOUT=findViewById(R.id.WEBSITEMAILLAYOUT);
        WEBSITEURLLAYOUT=findViewById(R.id.WEBSITEURLLAYOUT);
        i=getIntent();
        ParentID=i.getStringExtra("ParamId");
        InspectionId=i.getStringExtra("InspectionId");
        INVITEDCGADATELAYOUT=findViewById(R.id.INVITEDCGADATELAYOUT);
        INVITEDCGANNUALTITLELAYOUT=findViewById(R.id.INVITEDCGANNUALTITLELAYOUT);
        INVITEDCGANNUALDAYLAYOUT=findViewById(R.id.INVITEDCGANNUALDAYLAYOUT);
        TXTSPORTSCHIEFTITLELAYOUT=findViewById(R.id.TXTSPORTSCHIEFTITLELAYOUT);
        INVITEDCGSPORTDAYLAYOUT=findViewById(R.id.INVITEDCGSPORTDAYLAYOUT);
        SPORTDAYSTATUS=findViewById(R.id.SPORTDAYSTATUS);
        INVITEDCGSPORTDAY=findViewById(R.id.INVITEDCGSPORTDAY);
        TXTSPORTSCHIEFTITLE=findViewById(R.id.TXTSPORTSCHIEFTITLE);
        ANNUALDAYSTATUS=findViewById(R.id.ANNUALDAYSTATUS);
        INVITEDCGANNUALDAY=findViewById(R.id.INVITEDCGANNUALDAY);
        INVITEDCGANNUALTITLE=findViewById(R.id.INVITEDCGANNUALTITLE);
        INVITEDCGADATE=findViewById(R.id.INVITEDCGADATE);
        YOGADAYSTATUS=findViewById(R.id.YOGADAYSTATUS);
        INTERHOUSESTATUS=findViewById(R.id.INTERHOUSESTATUS);
        WEBSITESTATUS=findViewById(R.id.WEBSITESTATUS);
        WEBSITEURL=findViewById(R.id.WEBSITEURL);
        WEBSITEMAILSTATUS=findViewById(R.id.WEBSITEMAILSTATUS);
        WEBSITEMAIL=findViewById(R.id.WEBSITEMAIL);
        PTAMEETINGSTATUS=findViewById(R.id.PTAMEETINGSTATUS);
        PTAMEETINGDATE=findViewById(R.id.PTAMEETINGDATE);
        MONTHLYTESTSTATUS=findViewById(R.id.MONTHLYTESTSTATUS);
        TEACHERDIARYSTATUS=findViewById(R.id.TEACHERDIARYSTATUS);
        HEATHCHECKUPSTATUS=findViewById(R.id.HEATHCHECKUPSTATUS);
        HEATHCHECKUPDATE=findViewById(R.id.HEATHCHECKUPDATE);
        SCHOOLMAGAZINESTATUS=findViewById(R.id.SCHOOLMAGAZINESTATUS);
        SPECIALGUESTSTATUS=findViewById(R.id.SPECIALGUESTSTATUS);
        SMDCMEETSTATUS=findViewById(R.id.SMDCMEETSTATUS);
        SMDCMEETDATE=findViewById(R.id.SMDCMEETDATE);
        SMDCMEETTOTAL=findViewById(R.id.SMDCMEETTOTAL);
        ALUMNISTATUSDATE=findViewById(R.id.ALUMNISTATUSDATE);
        ALUMNISTATUS=findViewById(R.id.ALUMNISTATUS);
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID",applicationController.getSchoolId());
        jsonObject.addProperty("PeriodID",applicationController.getPeriodID());
        RestClient restClient = new RestClient();
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

                    Toast.makeText(Extra_Activities_Details.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onResponse: "+approveRejectRemarksDataModel.getData());
                    arrayListRemarks=approveRejectRemarksDataModel.getData();
                    Dialog dialogForRemark=new Dialog(Extra_Activities_Details.this);
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
        Call<GetExtraActivitiesModel> call=apiService.getExtraActivities(jsonObject);
        call.enqueue(new Callback<GetExtraActivitiesModel>() {
            @Override
            public void onResponse(Call<GetExtraActivitiesModel> call, Response<GetExtraActivitiesModel> response) {
                GetExtraActivitiesModel activitiesModel=response.body();
              SPORTDAYSTATUS.setText(activitiesModel.getSportdaystatus()==null?"No":activitiesModel.getSportdaystatus().toString());
              INVITEDCGSPORTDAY.setText(activitiesModel.getInvitedcgsportday()==null?"No":activitiesModel.getInvitedcgsportday().toString());
              TXTSPORTSCHIEFTITLE.setText(activitiesModel.getTxtsportschieftitle()==null?"":activitiesModel.getTxtsportschieftitle().toString());
              ANNUALDAYSTATUS.setText(activitiesModel.getAnnualdaystatus()==null?"No":activitiesModel.getAnnualdaystatus().toString());
              INVITEDCGANNUALDAY.setText(activitiesModel.getInvitedcgannualday()==null?"":activitiesModel.getInvitedcgannualday().toString());
              INVITEDCGANNUALTITLE.setText(activitiesModel.getInvitedcgannualtitle()==null?"":activitiesModel.getInvitedcgannualtitle().toString());
              INVITEDCGADATE.setText(activitiesModel.getInvitedcgadate()==null?"":activitiesModel.getInvitedcgadate().toString().split("T")[0]);
              YOGADAYSTATUS.setText(activitiesModel.getYogadaystatus()==null?"No":activitiesModel.getYogadaystatus().toString());
              INTERHOUSESTATUS.setText(activitiesModel.getInterhousestatus()==null?"No":activitiesModel.getInterhousestatus().toString());
              WEBSITESTATUS.setText(activitiesModel.getWebsitestatus()==null?"No":activitiesModel.getWebsitestatus().toString());
              WEBSITEURL.setText(activitiesModel.getWebsiteurl()==null?"":activitiesModel.getWebsiteurl().toString());
              WEBSITEMAILSTATUS.setText(activitiesModel.getWebsitemailstatus()==null?"No":activitiesModel.getWebsitemailstatus().toString());
              WEBSITEMAIL.setText(activitiesModel.getWebsitemail()==null?"":activitiesModel.getWebsitemail().toString());
              PTAMEETINGSTATUS.setText(activitiesModel.getPtameetingstatus()==null?"No":activitiesModel.getPtameetingstatus().toString());
              PTAMEETINGDATE.setText(activitiesModel.getPtameetingdate()==null?"":activitiesModel.getPtameetingdate().toString().split("T")[0]);
              MONTHLYTESTSTATUS.setText(activitiesModel.getMonthlyteststatus()==null?"No":activitiesModel.getMonthlyteststatus().toString());
              TEACHERDIARYSTATUS.setText(activitiesModel.getTeacherdiarystatus()==null?"No":activitiesModel.getTeacherdiarystatus().toString());
              HEATHCHECKUPSTATUS.setText(activitiesModel.getHeathcheckupstatus()==null?"No":activitiesModel.getHeathcheckupstatus().toString());
              HEATHCHECKUPDATE.setText(activitiesModel.getHeathcheckupdate()==null?"":activitiesModel.getHeathcheckupdate().toString().split("T")[0]);
              SCHOOLMAGAZINESTATUS.setText(activitiesModel.getSchoolmagazinestatus()==null?"No":activitiesModel.getSchoolmagazinestatus().toString());
              SPECIALGUESTSTATUS.setText(activitiesModel.getSpecialgueststatus()==null?"No":activitiesModel.getSpecialgueststatus().toString());
              SMDCMEETSTATUS.setText(activitiesModel.getSmdcmeetstatus()==null?"No":activitiesModel.getSmdcmeetstatus().toString());
              SMDCMEETDATE.setText(activitiesModel.getSmdcmeetdate()==null?"":activitiesModel.getSmdcmeetdate().toString().split("T")[0]);
              SMDCMEETTOTAL.setText(activitiesModel.getSmdcmeettotal()==null?"0":activitiesModel.getSmdcmeettotal().toString());
              ALUMNISTATUSDATE.setText(activitiesModel.getAlumnistatusdate()==null?"":activitiesModel.getAlumnistatusdate().toString().split("T")[0]);
              ALUMNISTATUS.setText(activitiesModel.getAlumnistatus()==null?"No":activitiesModel.getAlumnistatus().toString());

              try {
                  if (activitiesModel.getAlumnistatus().equals("No")){
                      ALUMNISTATUSDATELAYOUT.setVisibility(View.GONE);
                  }

              }catch (Exception e){
                  ALUMNISTATUSDATELAYOUT.setVisibility(View.GONE);

              }


                try {
                    if (activitiesModel.getSmdcmeetstatus().equals("No")){
                        SMDCMEETTOTALLAYOUT.setVisibility(View.GONE);
                        SMDCMEETDATELAYOUT.setVisibility(View.GONE);
                    }
                }catch (Exception e){
                    SMDCMEETTOTALLAYOUT.setVisibility(View.GONE);
                    SMDCMEETDATELAYOUT.setVisibility(View.GONE);
                }


                try {
                    if (activitiesModel.getHeathcheckupstatus().equals("No")){
                        HEATHCHECKUPDATELAYOUT.setVisibility(View.GONE);

                    }

                }catch (Exception e){
                    HEATHCHECKUPDATELAYOUT.setVisibility(View.GONE);

                }



                try {
                    if (activitiesModel.getPtameetingstatus().equals("No")){
                        PTAMEETINGDATELAYOUT.setVisibility(View.GONE);

                    }

                }catch (Exception e){
                    PTAMEETINGDATELAYOUT.setVisibility(View.GONE);

                }


                try {
                    if (activitiesModel.getWebsitestatus().equals("No")){
                        WEBSITEURLLAYOUT.setVisibility(View.GONE);

                    }
                }catch (Exception e){
                    WEBSITEURLLAYOUT.setVisibility(View.GONE);

                }



                try {
                    if (activitiesModel.getWebsitemailstatus().equals("No")){
                        WEBSITEMAILLAYOUT.setVisibility(View.GONE);

                    }
                }catch (Exception e){
                    WEBSITEMAILLAYOUT.setVisibility(View.GONE);

                }



                try {
                    if (activitiesModel.getAnnualdaystatus().equals("No")){
                        INVITEDCGANNUALDAYLAYOUT.setVisibility(View.GONE);
                        INVITEDCGANNUALTITLE.setVisibility(View.GONE);
                        INVITEDCGADATELAYOUT.setVisibility(View.GONE);

                    }
                }catch (Exception e){
                    INVITEDCGANNUALDAYLAYOUT.setVisibility(View.GONE);
                    INVITEDCGANNUALTITLE.setVisibility(View.GONE);
                    INVITEDCGADATELAYOUT.setVisibility(View.GONE);

                }



                try {
                    if (activitiesModel.getSportdaystatus().equals("No")){
                        INVITEDCGSPORTDAYLAYOUT.setVisibility(View.GONE);
                        TXTSPORTSCHIEFTITLELAYOUT.setVisibility(View.GONE);
                        INVITEDCGSPORTDAYLAYOUT.setVisibility(View.GONE);

                    }
                }catch (Exception e){
                    INVITEDCGSPORTDAYLAYOUT.setVisibility(View.GONE);
                    TXTSPORTSCHIEFTITLELAYOUT.setVisibility(View.GONE);
                    INVITEDCGSPORTDAYLAYOUT.setVisibility(View.GONE);
                }




            }

            @Override
            public void onFailure(Call<GetExtraActivitiesModel> call, Throwable t) {

            }
        });
        extraActivitiesRejectBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(Extra_Activities_Details.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
        extraActivitiesApproveBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(Extra_Activities_Details.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
    }
}