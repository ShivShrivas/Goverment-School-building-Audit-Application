package com.bsn.buildingaudit.DIOS.co_curricular_activities;

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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.ScoutAndGuideImageRecViewAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.ApproveRejectRemarksDataModel;
import com.bsn.buildingaudit.Model.Datum;
import com.bsn.buildingaudit.Model.ScoutAndGuideModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Scount_and_Guide extends AppCompatActivity {
RecyclerView districRallyRecview,stateRallyRecview,nationalRallyRecview,socialWorkRallyRecview,expeditureRecview;
TextView STUDENTENROLLED,SCOUTCOMPREGISTED,SCOUTCOMPRENEW,SCOUTMASTERSTATUS,SCOUTMASTERTRAINING,TRAININGMATERIALSTATUS
        ,SCOUTROOMSTATUS,STOCKEXPREGISTER,SCOUTTRAININGPPSDS,SCOUTPARTIRAJYA,SCOUTPARTIRASHTRAPATI,SCOUTPARTIDISTRICTRALLY,
        SCOUTPARTIREGRALLY,SCOUTPARTINATIONAL,SCOUTPARTISOCIALWORK,EXPSCOUTGUIDE;
    Intent i;
    Button scout_and_guideApproveBtn,scout_and_guideRejectBtn;
    String ParentID,InspectionId;
ApplicationController applicationController;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();
    Boolean remarkAlreadyDoneFlag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scount_and_guide);
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        i=getIntent();
        ParentID=i.getStringExtra("ParamId");
        InspectionId=i.getStringExtra("InspectionId");
        applicationController= (ApplicationController) getApplication();
        socialWorkRallyRecview=findViewById(R.id.socialWorkRallyRecview);
        expeditureRecview=findViewById(R.id.expeditureRecview);
        nationalRallyRecview=findViewById(R.id.nationalRallyRecview);
        stateRallyRecview=findViewById(R.id.stateRallyRecview);
        districRallyRecview=findViewById(R.id.districRallyRecview);
        SCOUTMASTERTRAINING=findViewById(R.id.SCOUTMASTERTRAINING);
        SCOUTMASTERSTATUS=findViewById(R.id.SCOUTMASTERSTATUS);
        SCOUTCOMPRENEW=findViewById(R.id.SCOUTCOMPRENEW);
        scout_and_guideRejectBtn=findViewById(R.id.scout_and_guideRejectBtn);
        scout_and_guideApproveBtn=findViewById(R.id.scout_and_guideApproveBtn);
        SCOUTCOMPREGISTED=findViewById(R.id.SCOUTCOMPREGISTED);
        TRAININGMATERIALSTATUS=findViewById(R.id.TRAININGMATERIALSTATUS);
        STUDENTENROLLED=findViewById(R.id.STUDENTENROLLED);
        SCOUTROOMSTATUS=findViewById(R.id.SCOUTROOMSTATUS);
        STOCKEXPREGISTER=findViewById(R.id.STOCKEXPREGISTER);
        SCOUTTRAININGPPSDS=findViewById(R.id.SCOUTTRAININGPPSDS);
        SCOUTPARTIRAJYA=findViewById(R.id.SCOUTPARTIRAJYA);
        SCOUTPARTIRASHTRAPATI=findViewById(R.id.SCOUTPARTIRASHTRAPATI);
        SCOUTPARTIDISTRICTRALLY=findViewById(R.id.SCOUTPARTIDISTRICTRALLY);
        SCOUTPARTIREGRALLY=findViewById(R.id.SCOUTPARTIREGRALLY);
        SCOUTPARTINATIONAL=findViewById(R.id.SCOUTPARTINATIONAL);
        SCOUTPARTISOCIALWORK=findViewById(R.id.SCOUTPARTISOCIALWORK);
        EXPSCOUTGUIDE=findViewById(R.id.EXPSCOUTGUIDE);
        socialWorkRallyRecview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        expeditureRecview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        nationalRallyRecview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        stateRallyRecview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        districRallyRecview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
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

                    Toast.makeText(Scount_and_Guide.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                   
                    arrayListRemarks=approveRejectRemarksDataModel.getData();
                    Dialog dialogForRemark=new Dialog(Scount_and_Guide.this);
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
        Call<ScoutAndGuideModel> call=apiService.getScoutAndGuide(jsonObject);
        call.enqueue(new Callback<ScoutAndGuideModel>() {
            @Override
            public void onResponse(Call<ScoutAndGuideModel> call, Response<ScoutAndGuideModel> response) {
                ScoutAndGuideModel scoutAndGuideModel=response.body();
               
                SCOUTMASTERTRAINING.setText(scoutAndGuideModel.getScoutmastertraining()==null?"No":scoutAndGuideModel.getScoutmastertraining().toString());
                SCOUTMASTERSTATUS.setText(scoutAndGuideModel.getScoutmasterstatus()==null?"No":scoutAndGuideModel.getScoutmasterstatus().toString());
                SCOUTCOMPRENEW.setText(scoutAndGuideModel.getScoutcomprenew()==null?"No":scoutAndGuideModel.getScoutcomprenew().toString());
                SCOUTCOMPREGISTED.setText(scoutAndGuideModel.getScoutcompregisted()==null?"No":scoutAndGuideModel.getScoutcompregisted().toString());
                TRAININGMATERIALSTATUS.setText(scoutAndGuideModel.getTrainingmaterialstatus()==null?"No":scoutAndGuideModel.getTrainingmaterialstatus().toString());
                STUDENTENROLLED.setText(scoutAndGuideModel.getStudentenrolled()==null?"0":scoutAndGuideModel.getStudentenrolled().toString());
                SCOUTROOMSTATUS.setText(scoutAndGuideModel.getScoutroomstatus()==null?"No":scoutAndGuideModel.getScoutroomstatus().toString());
                STOCKEXPREGISTER.setText(scoutAndGuideModel.getStockexpregister()==null?"No":scoutAndGuideModel.getStockexpregister().toString());
                SCOUTTRAININGPPSDS.setText(scoutAndGuideModel.getScouttrainingppsds()==null?"No":scoutAndGuideModel.getScouttrainingppsds().toString());
                SCOUTPARTIRAJYA.setText(scoutAndGuideModel.getScoutpartirajya()==null?"No":scoutAndGuideModel.getScoutpartirajya().toString());
                SCOUTPARTIRASHTRAPATI.setText(scoutAndGuideModel.getScoutpartirashtrapati()==null?"No":scoutAndGuideModel.getScoutpartirashtrapati().toString());
                SCOUTPARTIDISTRICTRALLY.setText(scoutAndGuideModel.getScoutpartidistrictrally()==null?"No":scoutAndGuideModel.getScoutpartidistrictrally().toString());
                SCOUTPARTIREGRALLY.setText(scoutAndGuideModel.getScoutpartiregrally()==null?"No":scoutAndGuideModel.getScoutpartiregrally().toString());
                SCOUTPARTINATIONAL.setText(scoutAndGuideModel.getScoutpartinational()==null?"No":scoutAndGuideModel.getScoutpartinational().toString());
                SCOUTPARTISOCIALWORK.setText(scoutAndGuideModel.getScoutpartisocialwork()==null?"No":scoutAndGuideModel.getScoutpartisocialwork().toString());
                EXPSCOUTGUIDE.setText(scoutAndGuideModel.getExpscoutguide()==null?"0.0":scoutAndGuideModel.getExpscoutguide().toString());
                try {
                    if (scoutAndGuideModel.getScoutpartidistrictrally().equals("No") ){
                        districRallyRecview.setVisibility(View.GONE);
                    }
                }catch (Exception e){
                    districRallyRecview.setVisibility(View.GONE);

                }

                String[] SCOUTPARTIDISTRICTRALLYPHOTO=scoutAndGuideModel.getScoutpartidistrictrallyphoto().split(",");
                ScoutAndGuideImageRecViewAdapter scoutAndGuideImageRecViewAdapter1=new ScoutAndGuideImageRecViewAdapter(Scount_and_Guide.this,SCOUTPARTIDISTRICTRALLYPHOTO);
                districRallyRecview.setAdapter(scoutAndGuideImageRecViewAdapter1);
                scoutAndGuideImageRecViewAdapter1.notifyDataSetChanged();
                try {
                    if (scoutAndGuideModel.getScoutpartiregrally().equals("No") ){
                        stateRallyRecview.setVisibility(View.GONE);
                    }
                }catch (Exception e){
                    stateRallyRecview.setVisibility(View.GONE);

                }

                String[] SCOUTPARTIREGRALLYPHOTO=scoutAndGuideModel.getScoutpartiregrallyphoto().split(",");
                ScoutAndGuideImageRecViewAdapter scoutAndGuideImageRecViewAdapter2=new ScoutAndGuideImageRecViewAdapter(Scount_and_Guide.this,SCOUTPARTIREGRALLYPHOTO);
                stateRallyRecview.setAdapter(scoutAndGuideImageRecViewAdapter2);
                scoutAndGuideImageRecViewAdapter2.notifyDataSetChanged();

                try {
                    if (scoutAndGuideModel.getScoutpartinational().toString().equals("No") ){
                        nationalRallyRecview.setVisibility(View.GONE);
                    }
                }catch (Exception e){
                    nationalRallyRecview.setVisibility(View.GONE);

                }

                String[] SCOUTPARTINATIONALPHOTO=scoutAndGuideModel.getScoutpartinationalphoto().split(",");
                ScoutAndGuideImageRecViewAdapter scoutAndGuideImageRecViewAdapter3=new ScoutAndGuideImageRecViewAdapter(Scount_and_Guide.this,SCOUTPARTINATIONALPHOTO);
                nationalRallyRecview.setAdapter(scoutAndGuideImageRecViewAdapter3);
                scoutAndGuideImageRecViewAdapter3.notifyDataSetChanged();

                try {
                    if (scoutAndGuideModel.getScoutpartisocialwork().toString().equals("No") ){
                        socialWorkRallyRecview.setVisibility(View.GONE);
                    }
                }catch (Exception e){
                    socialWorkRallyRecview.setVisibility(View.GONE);

                }

                String[] SCOUTPARTISOCIALWORKPHOTO=scoutAndGuideModel.getScoutpartisocialworkphoto().split(",");
                ScoutAndGuideImageRecViewAdapter scoutAndGuideImageRecViewAdapter4=new ScoutAndGuideImageRecViewAdapter(Scount_and_Guide.this,SCOUTPARTISOCIALWORKPHOTO);
                socialWorkRallyRecview.setAdapter(scoutAndGuideImageRecViewAdapter4);
                scoutAndGuideImageRecViewAdapter4.notifyDataSetChanged();



                String[] EXPSCOUTGUIDEPHOTO=scoutAndGuideModel.getExpscoutguidephoto().split(",");
                ScoutAndGuideImageRecViewAdapter scoutAndGuideImageRecViewAdapter5=new ScoutAndGuideImageRecViewAdapter(Scount_and_Guide.this,EXPSCOUTGUIDEPHOTO);
                expeditureRecview.setAdapter(scoutAndGuideImageRecViewAdapter5);
                scoutAndGuideImageRecViewAdapter5.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ScoutAndGuideModel> call, Throwable t) {

            }
        });
        scout_and_guideApproveBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(Scount_and_Guide.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

        scout_and_guideRejectBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(Scount_and_Guide.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

    }
}