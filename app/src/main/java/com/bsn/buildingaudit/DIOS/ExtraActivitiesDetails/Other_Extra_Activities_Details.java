package com.bsn.buildingaudit.DIOS.ExtraActivitiesDetails;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.GetOtherExtraActivitiesModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Other_Extra_Activities_Details extends AppCompatActivity {
TextView Exhibition,Inspired,StudentNational, SkillDev, InnovationEdu, Others, Ironfolic, Schoolparticipated
            ,TeacherNominated,TeacherNominatedprogram, Nominatedteachermobile,Roadsafety, RoadSafetyAwareness
    ,VidyalayaRoadSafety, ChairmanTeacher,PostNameoftheChairman,MobileNoOfChairmain,CommunicableDisease,   CommunicableDiseaseControl
   , TeacherhasbeennominatedbySchool,   Nameoftheteacherprevention, DesignationofTeacher, Mobilenumberofthedesignatedteacher;
    ApplicationController applicationController;
    Button OtherExtraActivitiesRejectBtn,OtherExtraActivitiesApproveBtn;
    Intent i;
    String ParentID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_extra_activities_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        applicationController= (ApplicationController) getApplication();
        i=getIntent();
        ParentID=i.getStringExtra("ParamId");
       Exhibition=findViewById(R.id.Exhibition);
       Inspired=findViewById(R.id.Inspired);
       StudentNational=findViewById(R.id.StudentNational);
       SkillDev=findViewById(R.id.SkillDev);
       InnovationEdu=findViewById(R.id.InnovationEdu);
       Others=findViewById(R.id.Others);
       Ironfolic=findViewById(R.id.Ironfolic);
       Schoolparticipated=findViewById(R.id.Schoolparticipated);
       TeacherNominated=findViewById(R.id.TeacherNominated);
       TeacherNominatedprogram=findViewById(R.id.TeacherNominatedprogram);
       Nominatedteachermobile=findViewById(R.id.Nominatedteachermobile);
        OtherExtraActivitiesRejectBtn=findViewById(R.id.OtherExtraActivitiesRejectBtn);
        OtherExtraActivitiesApproveBtn=findViewById(R.id.OtherExtraActivitiesApproveBtn);
       Roadsafety=findViewById(R.id.Roadsafety);
       RoadSafetyAwareness=findViewById(R.id.RoadSafetyAwareness);
       VidyalayaRoadSafety=findViewById(R.id.VidyalayaRoadSafety);
       ChairmanTeacher=findViewById(R.id.ChairmanTeacher);
       PostNameoftheChairman=findViewById(R.id.PostNameoftheChairman);
       MobileNoOfChairmain=findViewById(R.id.MobileNoOfChairmain);
       CommunicableDisease=findViewById(R.id.CommunicableDisease);
       CommunicableDiseaseControl=findViewById(R.id.CommunicableDiseaseControl);
       TeacherhasbeennominatedbySchool=findViewById(R.id.TeacherhasbeennominatedbySchool);
       Nameoftheteacherprevention=findViewById(R.id.Nameoftheteacherprevention);
       DesignationofTeacher=findViewById(R.id.DesignationofTeacher);
       Mobilenumberofthedesignatedteacher=findViewById(R.id.Mobilenumberofthedesignatedteacher);
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID",applicationController.getSchoolId());
        jsonObject.addProperty("PeriodID",applicationController.getPeriodID());
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<GetOtherExtraActivitiesModel> call=apiService.getOtherExtraActivities(jsonObject);
        call.enqueue(new Callback<GetOtherExtraActivitiesModel>() {
            @Override
            public void onResponse(Call<GetOtherExtraActivitiesModel> call, Response<GetOtherExtraActivitiesModel> response) {
                GetOtherExtraActivitiesModel getOtherExtraActivitiesModel=response.body();
               Exhibition.setText(getOtherExtraActivitiesModel.getExhibition()==null?"No":getOtherExtraActivitiesModel.getExhibition().toString());
               Inspired.setText(getOtherExtraActivitiesModel.getInspired()==null?"No":getOtherExtraActivitiesModel.getInspired().toString());
               StudentNational.setText(getOtherExtraActivitiesModel.getStudentNational()==null?"No":getOtherExtraActivitiesModel.getStudentNational().toString());
               SkillDev.setText(getOtherExtraActivitiesModel.getSkillDev()==null?"No":getOtherExtraActivitiesModel.getSkillDev().toString());
               InnovationEdu.setText(getOtherExtraActivitiesModel.getInnovationEdu()==null?"No":getOtherExtraActivitiesModel.getInnovationEdu().toString());
               Others.setText(getOtherExtraActivitiesModel.getOthers()==null?"No":getOtherExtraActivitiesModel.getOthers().toString());
               Ironfolic.setText(getOtherExtraActivitiesModel.getIronfolic()==null?"No":getOtherExtraActivitiesModel.getIronfolic().toString());
               Schoolparticipated.setText(getOtherExtraActivitiesModel.getSchoolparticipated()==null?"No":getOtherExtraActivitiesModel.getSchoolparticipated().toString());
               TeacherNominated.setText(getOtherExtraActivitiesModel.getTeacherNominated()==null?"No":getOtherExtraActivitiesModel.getTeacherNominated().toString());
               TeacherNominatedprogram.setText(getOtherExtraActivitiesModel.getTeacherNominatedprogram()==null?"No":getOtherExtraActivitiesModel.getTeacherNominatedprogram().toString());
               Nominatedteachermobile.setText(getOtherExtraActivitiesModel.getNominatedteachermobile()==null?"Not Available":getOtherExtraActivitiesModel.getNominatedteachermobile().toString());
               Roadsafety.setText(getOtherExtraActivitiesModel.getRoadsafety()==null?"No":getOtherExtraActivitiesModel.getRoadsafety().toString());
               RoadSafetyAwareness.setText(getOtherExtraActivitiesModel.getRoadSafetyAwareness()==null?"No":getOtherExtraActivitiesModel.getRoadSafetyAwareness().toString());
               VidyalayaRoadSafety.setText(getOtherExtraActivitiesModel.getRoadsafety()==null?"No":getOtherExtraActivitiesModel.getRoadsafety().toString());
               ChairmanTeacher.setText(getOtherExtraActivitiesModel.getChairmanTeacher()==null?"No":getOtherExtraActivitiesModel.getChairmanTeacher().toString());
               PostNameoftheChairman.setText(getOtherExtraActivitiesModel.getPostNameoftheChairman()==null?"No":getOtherExtraActivitiesModel.getPostNameoftheChairman().toString());
               MobileNoOfChairmain.setText(getOtherExtraActivitiesModel.getMobileNoOfChairmain()==null?"Not Available":getOtherExtraActivitiesModel.getMobileNoOfChairmain().toString());
               CommunicableDisease.setText(getOtherExtraActivitiesModel.getCommunicableDisease()==null?"No":getOtherExtraActivitiesModel.getCommunicableDisease().toString());
               CommunicableDiseaseControl.setText(getOtherExtraActivitiesModel.getCommunicableDiseaseControl()==null?"No":getOtherExtraActivitiesModel.getCommunicableDiseaseControl().toString());
               TeacherhasbeennominatedbySchool.setText(getOtherExtraActivitiesModel.getTeacherhasbeennominatedbySchool()==null?"No":getOtherExtraActivitiesModel.getTeacherhasbeennominatedbySchool().toString());
               Nameoftheteacherprevention.setText(getOtherExtraActivitiesModel.getNameoftheteacherprevention()==null?"No":getOtherExtraActivitiesModel.getNameoftheteacherprevention().toString());
               DesignationofTeacher.setText(getOtherExtraActivitiesModel.getDesignationofTeacher()==null?"No":getOtherExtraActivitiesModel.getDesignationofTeacher().toString());
               Mobilenumberofthedesignatedteacher.setText(getOtherExtraActivitiesModel.getMobilenumberofthedesignatedteacher()==null?"Not Available":getOtherExtraActivitiesModel.getMobilenumberofthedesignatedteacher().toString());
            }

            @Override
            public void onFailure(Call<GetOtherExtraActivitiesModel> call, Throwable t) {

            }
        });
        OtherExtraActivitiesRejectBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(Other_Extra_Activities_Details.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

        OtherExtraActivitiesApproveBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(Other_Extra_Activities_Details.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
    }
}