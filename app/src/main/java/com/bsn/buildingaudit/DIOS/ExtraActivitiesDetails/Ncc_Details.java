package com.bsn.buildingaudit.DIOS.ExtraActivitiesDetails;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.DIOS.StudentEnrollment.Student_Enrollment_Details;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
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
    String ParentID;
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
                Log.d("TAG", "onClick: "+ParentID);
                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InsType","A");
                jsonObject1.addProperty("ParamId",ParentID);
                Call<ArrayList<ApproveRejectRemarkModel>> call1=apiService.getApproveRejectRemark(jsonObject1);
                call1.enqueue(new Callback<ArrayList<ApproveRejectRemarkModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ApproveRejectRemarkModel>> call, Response<ArrayList<ApproveRejectRemarkModel>> response) {
                        ArrayList<ApproveRejectRemarkModel> arrayList=response.body();
                        StaticFunctions.showDialogApprove(Ncc_Details.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID);

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
                Log.d("TAG", "onClick: "+ParentID);
                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InsType","R");
                jsonObject1.addProperty("ParamId",ParentID);
                Call<ArrayList<ApproveRejectRemarkModel>> call1=apiService.getApproveRejectRemark(jsonObject1);
                call1.enqueue(new Callback<ArrayList<ApproveRejectRemarkModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ApproveRejectRemarkModel>> call, Response<ArrayList<ApproveRejectRemarkModel>> response) {
                        ArrayList<ApproveRejectRemarkModel> arrayList=response.body();
                        StaticFunctions.showDialogReject(Ncc_Details.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
    }


}