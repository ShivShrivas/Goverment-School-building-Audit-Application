package com.bsn.buildingaudit.Activies;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.OnlineImageRecViewAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.ApproveRejectRemarksDataModel;
import com.bsn.buildingaudit.Model.Datum;
import com.bsn.buildingaudit.Model.LabDetailsResponse;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSubmit_PracticalLabsDetails extends AppCompatActivity {
    private TextView schoolAddress,schoolName,editPracticalLabDetails;
    ApplicationController applicationController;
    Call<List<LabDetailsResponse>> call;
    LinearLayout linearLayout21,physicsLabHeader,socialScienceLabHeader,musicLabheader,homeScienceLabHeader,chemistryLabHeader,scienceLabHeader,bioLabHeader;
    EditText edtHomeSciencelabAvailability,edtGeographyLabConditionedt,GeographyEquipmentStatus,edtGeographylabAvailability,edtBiologyLabCondition,edtBiologylabAvailability,edtBilogyEquipmentStatus
                    ,edtChemistryLabCondition,edtChemistryEquipmentStatus,edtChemistrylabAvailability,edtPhysicsLabCondition,edtPhysicsEquipmentStatus,edtPhysicslabAvailability
                ,edtHomeScienceLabCondition,edtHomeScienceEquipmentStatus
            ,edtScienceLabCondition,edtSciencelabAvailability,edtScienceEquipmentStatus,edtMusicLabCondition,edtHomeMusiclabAvailability,edtMusicEquipmentStatus;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();
    Boolean remarkAlreadyDoneFlag=false;

    String Type;
    String ParentID,InspectionId;
    RecyclerView recyclerViewMusicLab,recyclerViewHomeScienceLab,recyclerViewGeographyLab,recyclerViewScienceLab
            ,recyclerViewBiologyLab,recyclerViewChemistryLab,recyclerViewPhysicsLab;
ImageView schoolIcon;
    CardView scienceLabBodyCard,scienceLanImageCard,physicsLabBodyCard,physicsLabImageCard,
            chemistryLabBodyCard,chemistryLabImageCard,bioloyLabBodyCard,bioloyLabImageCard,
            homeScienceLabBodyCard,homeScienceLabImageCard,musicLabBodyCard,musicLabImageCard,
            geoGraphyLabImageCard,geoGraphyLabBodyCard;
    TextView mobnumberTxt;
    Button practicalLabApproveBtn,practicalLabRejectBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_practical_labs_details);
        applicationController= (ApplicationController) getApplication();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i=getIntent();
        Type=i.getStringExtra("Type");
        ParentID=i.getStringExtra("ParamId");
        InspectionId=i.getStringExtra("InspectionId");

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
        schoolName=findViewById(R.id.schoolName);
        schoolAddress=findViewById(R.id.schoolAddress);
        scienceLabHeader=findViewById(R.id.scienceLabHeader);
        chemistryLabHeader=findViewById(R.id.chemistryLabHeader);
        linearLayout21=findViewById(R.id.linearLayout21);
        bioLabHeader=findViewById(R.id.bioLabHeader);
        musicLabheader=findViewById(R.id.musicLabheader);
        homeScienceLabHeader=findViewById(R.id.homeScienceLabHeader);
        socialScienceLabHeader=findViewById(R.id.socialScienceLabHeader);
        mobnumberTxt=findViewById(R.id.mobnumberTxt);
        schoolIcon=findViewById(R.id.schoolIcon);
        practicalLabRejectBtn=findViewById(R.id.practicalLabRejectBtn);
        practicalLabApproveBtn=findViewById(R.id.practicalLabApproveBtn);
        scienceLabBodyCard=findViewById(R.id.scienceLabBodyCard);
        scienceLanImageCard=findViewById(R.id.scienceLabImageCard);
        physicsLabImageCard=findViewById(R.id.physicsLabImageCard);
        physicsLabBodyCard=findViewById(R.id.physicsLabBodyCard);
        physicsLabHeader=findViewById(R.id.physicsLabHeader);
        chemistryLabBodyCard=findViewById(R.id.chemistryLabBodyCard);
        chemistryLabImageCard=findViewById(R.id.chemistryLabImageCard);
        bioloyLabBodyCard=findViewById(R.id.bioloyLabBodyCard);
        bioloyLabImageCard=findViewById(R.id.bioloyLabImageCard);
        homeScienceLabBodyCard=findViewById(R.id.homeScienceLabBodyCard);
        homeScienceLabImageCard=findViewById(R.id.homeScienceLabImageCard);
        musicLabBodyCard=findViewById(R.id.musicLabBodyCard);
        musicLabImageCard=findViewById(R.id.musicLabImageCard);
        geoGraphyLabBodyCard=findViewById(R.id.geoGraphyLabBodyCard);
        geoGraphyLabImageCard=findViewById(R.id.geoGraphyLabImageCard);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        recyclerViewMusicLab=findViewById(R.id.recyclerViewMusicLab);
        recyclerViewHomeScienceLab=findViewById(R.id.recyclerViewHomeScienceLab);
        recyclerViewGeographyLab=findViewById(R.id.recyclerViewGeographyLab);
        recyclerViewScienceLab=findViewById(R.id.recyclerViewScienceLab);
        recyclerViewBiologyLab=findViewById(R.id.recyclerViewBiologyLab);
        recyclerViewChemistryLab=findViewById(R.id.recyclerViewChemistryLab);
        recyclerViewPhysicsLab=findViewById(R.id.recyclerViewPhysicsLab);
        edtHomeSciencelabAvailability=findViewById(R.id.edtHomeSciencelabAvailability);
        edtMusicEquipmentStatus=findViewById(R.id.edtMusicEquipmentStatus);
        edtHomeMusiclabAvailability=findViewById(R.id.edtHomeMusiclabAvailability);
        edtMusicLabCondition=findViewById(R.id.edtMusicLabCondition);
        edtSciencelabAvailability=findViewById(R.id.edtSciencelabAvailability);
        edtHomeScienceLabCondition=findViewById(R.id.edtHomeScienceLabCondition);
        edtHomeScienceEquipmentStatus=findViewById(R.id.edtHomeScienceEquipmentStatus);
        edtSciencelabAvailability=findViewById(R.id.edtSciencelabAvailability);
                edtGeographyLabConditionedt=findViewById(R.id.edtGeographyLabCondition);
        GeographyEquipmentStatus=findViewById(R.id.edtGeographyEquipmentStatus);
                edtGeographylabAvailability=findViewById(R.id.edtGeographyAvaialabilty);
        edtBiologyLabCondition=findViewById(R.id.edtBiologyLabCondition);
                edtBiologylabAvailability=findViewById(R.id.edtBiologylabAvailability);
        edtBilogyEquipmentStatus=findViewById(R.id.edtBilogyEquipmentStatus);
                edtChemistryLabCondition=findViewById(R.id.edtChemistryLabCondition);
        edtChemistryEquipmentStatus=findViewById(R.id.edtChemistryEquipmentStatus);
                edtChemistrylabAvailability=findViewById(R.id.edtChemistrylabAvailability);
        edtPhysicsLabCondition=findViewById(R.id.edtPhysicsLabCondition);
                edtPhysicsEquipmentStatus=findViewById(R.id.edtPhysicsEquipmentStatus);
        edtPhysicslabAvailability=findViewById(R.id.edtPhysicslabAvailability);
                edtScienceLabCondition=findViewById(R.id.edtScienceLabCondition);
                edtScienceEquipmentStatus=findViewById(R.id.edtScienceEquipmentStatus);
        editPracticalLabDetails=findViewById(R.id.editPracticalLabDetails);
        editPracticalLabDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_PracticalLabsDetails.this,UpdatedetailsTypeThree.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });

        if (Type.equals("D")){
            toolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            schoolIcon.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.schoolicon_dios_panel));
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
            mobnumberTxt.setTextColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            scienceLabHeader.setBackgroundColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            physicsLabHeader.setBackgroundColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            chemistryLabHeader.setBackgroundColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            bioLabHeader.setBackgroundColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            homeScienceLabHeader.setBackgroundColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            musicLabheader.setBackgroundColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            socialScienceLabHeader.setBackgroundColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            linearLayout21.setVisibility(View.VISIBLE);
            editPracticalLabDetails.setVisibility(View.GONE);
        }
        setAllEditTextDisabled();
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        if (Type.equals("D")) {
            JsonObject json = new JsonObject();
            json.addProperty("SchoolID", applicationController.getSchoolId());
            json.addProperty("PeriodID", applicationController.getPeriodID());
            json.addProperty("ParamId", ParentID);
            json.addProperty("InsRecordId", InspectionId);
            Call<ApproveRejectRemarksDataModel> callz = apiService.getpriviousSubmittedDataByDIOS(json);
            callz.enqueue(new Callback<ApproveRejectRemarksDataModel>() {
                @Override
                public void onResponse(Call<ApproveRejectRemarksDataModel> call, Response<ApproveRejectRemarksDataModel> response) {
                    Log.d("TAG", "onResponse: " + response.body());
                    ApproveRejectRemarksDataModel approveRejectRemarksDataModel = response.body();
                    Log.d("TAG", "onResponse: " + approveRejectRemarksDataModel.getStatus());
                    if (!approveRejectRemarksDataModel.getStatus().equals("No Record Found")) {

                        Toast.makeText(OnSubmit_PracticalLabsDetails.this, "" + approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "onResponse: " + approveRejectRemarksDataModel.getData());
                        arrayListRemarks = approveRejectRemarksDataModel.getData();
                        Dialog dialogForRemark = new Dialog(OnSubmit_PracticalLabsDetails.this);
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
        if (applicationController.getUsertype().equals("VA")){
            call=apiService.checkLabDetails(paraGetDetails("2",applicationController.getSchoolId(), applicationController.getPeriodID()));
        }else{
            call=apiService.checkLabDetails(paraGetDetails("10",applicationController.getSchoolId(), applicationController.getPeriodID()));
        }
        call.enqueue(new Callback<List<LabDetailsResponse>>() {
            @Override
            public void onResponse(Call<List<LabDetailsResponse>> call, Response<List<LabDetailsResponse>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                if (response.body().get(0).getDataLocked().equals("0")){
                    if (Type.equals("D")){
                        editPracticalLabDetails.setVisibility(View.GONE);

                    }else{
                        editPracticalLabDetails.setVisibility(View.VISIBLE);

                    }
                }
               List<LabDetailsResponse> list=response.body();

               if (list.get(0).getLabYN().toString().equals("Yes")){
                   edtSciencelabAvailability.setText(list.get(0).getLabYN());
               }else {
                   edtSciencelabAvailability.setText(list.get(0).getLabYN());

                   hidecards(scienceLabBodyCard,scienceLanImageCard);
               }

                    edtScienceEquipmentStatus.setText(list.get(0).getEquipmentStatus());


                    edtScienceLabCondition.setText(list.get(0).getLabCondition());



                if (list.get(1).getLabYN().toString().equals("Yes")){
                    edtPhysicslabAvailability.setText(list.get(1).getLabYN());
                }else {
                    hidecards(physicsLabBodyCard,physicsLabImageCard);
                    edtPhysicslabAvailability.setText(list.get(1).getLabYN());

                }


                    edtPhysicsEquipmentStatus.setText(list.get(1).getEquipmentStatus());


                    edtPhysicsLabCondition.setText(list.get(1).getLabCondition());


                if (list.get(2).getLabYN().toString().equals("Yes")){
                    edtChemistrylabAvailability.setText(list.get(1).getLabYN());
                }else {
                    hidecards(chemistryLabBodyCard,chemistryLabImageCard);
                    edtChemistrylabAvailability.setText(list.get(1).getLabYN());

                }


                    edtChemistryEquipmentStatus.setText(list.get(2).getEquipmentStatus());


                    edtChemistryLabCondition.setText(list.get(2).getLabCondition());


                if (list.get(3).getLabYN().toString().equals("Yes")){
                    edtBiologylabAvailability.setText(list.get(3).getLabYN());
                }else {
                    hidecards(bioloyLabBodyCard,bioloyLabImageCard);
                    edtBiologylabAvailability.setText(list.get(3).getLabYN());

                }


                    edtBilogyEquipmentStatus.setText(list.get(3).getEquipmentStatus());


                    edtBiologyLabCondition.setText(list.get(3).getLabCondition());


                if (list.get(4).getLabYN().toString().equals("Yes")){
                    edtHomeSciencelabAvailability.setText(list.get(4).getLabYN());
                }else {
                    hidecards(homeScienceLabBodyCard,homeScienceLabImageCard);
                    edtHomeSciencelabAvailability.setText(list.get(4).getLabYN());

                }



                    edtHomeScienceEquipmentStatus.setText(list.get(4).getEquipmentStatus());


                    edtHomeScienceLabCondition.setText(list.get(4).getLabCondition());


                if (list.get(5).getLabYN().toString().equals("Yes")){
                    edtHomeMusiclabAvailability.setText(list.get(5).getLabYN());
                }else {
                    hidecards(musicLabBodyCard,musicLabImageCard);
                    edtHomeMusiclabAvailability.setText(list.get(5).getLabYN());

                }



                    edtMusicEquipmentStatus.setText(list.get(5).getEquipmentStatus());


                    edtMusicLabCondition.setText(list.get(5).getLabCondition());



                if (list.get(6).getLabYN().toString().equals("Yes")){
                    edtGeographylabAvailability.setText(list.get(6).getLabYN());
                }else {
                    hidecards(geoGraphyLabBodyCard,geoGraphyLabImageCard);
                    edtGeographylabAvailability.setText(list.get(6).getLabYN());

                }




                    GeographyEquipmentStatus.setText(list.get(6).getEquipmentStatus());


                    edtGeographyLabConditionedt.setText(list.get(6).getLabCondition());

                recyclerViewScienceLab.setLayoutManager(new LinearLayoutManager(OnSubmit_PracticalLabsDetails.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerViewPhysicsLab.setLayoutManager(new LinearLayoutManager(OnSubmit_PracticalLabsDetails.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerViewChemistryLab.setLayoutManager(new LinearLayoutManager(OnSubmit_PracticalLabsDetails.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerViewBiologyLab.setLayoutManager(new LinearLayoutManager(OnSubmit_PracticalLabsDetails.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerViewHomeScienceLab.setLayoutManager(new LinearLayoutManager(OnSubmit_PracticalLabsDetails.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerViewMusicLab.setLayoutManager(new LinearLayoutManager(OnSubmit_PracticalLabsDetails.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerViewGeographyLab.setLayoutManager(new LinearLayoutManager(OnSubmit_PracticalLabsDetails.this,LinearLayoutManager.HORIZONTAL,false));






            try {
                Log.d("TAG", "onResponse: "+list.get(0).getLabPhotoPath());
                String[] scinceLabPhotoPathList=list.get(0).getLabPhotoPath().split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,scinceLabPhotoPathList, applicationController.getUsertype());
                recyclerViewScienceLab.setAdapter(onlineImageRecViewAdapter);
            }catch (Exception e){

            }

                try {
                    Log.d("TAG", "onResponse: "+list.get(1).getLabPhotoPath());

                    String[] physicsLabPhotoPathList=list.get(1).getLabPhotoPath().split(",");
                    OnlineImageRecViewAdapter onlineImageRecViewAdapter1=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,physicsLabPhotoPathList, applicationController.getUsertype());
                    recyclerViewPhysicsLab.setAdapter(onlineImageRecViewAdapter1);
            }catch (Exception e){

            }

                try {
                    Log.d("TAG", "onResponse: "+list.get(2).getLabPhotoPath());

                    String[] chemistryLabPhotoPathLis2=list.get(2).getLabPhotoPath().split(",");
                    OnlineImageRecViewAdapter onlineImageRecViewAdapter3=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,chemistryLabPhotoPathLis2, applicationController.getUsertype());
                    recyclerViewChemistryLab.setAdapter(onlineImageRecViewAdapter3);
            }catch (Exception e){

            }

                try {
                    Log.d("TAG", "onResponse: "+list.get(3).getLabPhotoPath());

                    String[] biologyLAbList=list.get(3).getLabPhotoPath().split(",");
                    OnlineImageRecViewAdapter onlineImageRecViewAdapter4=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,biologyLAbList, applicationController.getUsertype());
                    recyclerViewBiologyLab.setAdapter(onlineImageRecViewAdapter4);
            }catch (Exception e){

            }



                try {
                    Log.d("TAG", "onResponse: "+list.get(4).getLabPhotoPath());

                    String[] homscienceLabList=list.get(4).getLabPhotoPath().split(",");
                        OnlineImageRecViewAdapter onlineImageRecViewAdapter5=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,homscienceLabList, applicationController.getUsertype());
                    recyclerViewHomeScienceLab.setAdapter(onlineImageRecViewAdapter5);
                    }catch (Exception e){

                    }

               try {
                   Log.d("TAG", "onResponse: "+list.get(5).getLabPhotoPath());

                   String[] muscilabList=list.get(5).getLabPhotoPath().split(",");
                   OnlineImageRecViewAdapter onlineImageRecViewAdapter6=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,muscilabList, applicationController.getUsertype());
                   recyclerViewMusicLab.setAdapter(onlineImageRecViewAdapter6);
               }catch (Exception e){

               }

              try {
                  Log.d("TAG", "onResponse: "+list.get(6).getLabPhotoPath());

                  String[] geoGraphyList=list.get(6).getLabPhotoPath().split(",");
                  OnlineImageRecViewAdapter onlineImageRecViewAdapter7=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,geoGraphyList, applicationController.getUsertype());
                  recyclerViewGeographyLab.setAdapter(onlineImageRecViewAdapter7);
              }catch (Exception e){

              }

                dialog2.dismiss();


            }

            @Override
            public void onFailure(Call<List<LabDetailsResponse>> call, Throwable t) {
                dialog2.dismiss();

            }
        });
        practicalLabApproveBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(OnSubmit_PracticalLabsDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });


        practicalLabRejectBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(OnSubmit_PracticalLabsDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void hidecards(CardView scienceLabBodyCard, CardView scienceLanImageCard) {


                scienceLabBodyCard.setVisibility(View.GONE);
                scienceLanImageCard.setVisibility(View.GONE);


    }

    private void setAllEditTextDisabled() {
       edtHomeMusiclabAvailability.setEnabled(false);
       edtMusicEquipmentStatus.setEnabled(false);
        edtHomeMusiclabAvailability.setEnabled(false);
                edtMusicLabCondition.setEnabled(false);
        edtSciencelabAvailability.setEnabled(false);
                edtHomeScienceLabCondition.setEnabled(false);
        edtHomeScienceEquipmentStatus.setEnabled(false);
                edtSciencelabAvailability.setEnabled(false);
        edtGeographyLabConditionedt.setEnabled(false);
                GeographyEquipmentStatus.setEnabled(false);
        edtGeographylabAvailability.setEnabled(false);
                edtBiologyLabCondition.setEnabled(false);
        edtBiologylabAvailability.setEnabled(false);
                edtBilogyEquipmentStatus.setEnabled(false);
        edtChemistryLabCondition.setEnabled(false);
                edtChemistryEquipmentStatus.setEnabled(false);
        edtChemistrylabAvailability.setEnabled(false);
                edtPhysicsLabCondition.setEnabled(false);
        edtPhysicsEquipmentStatus.setEnabled(false);
                edtPhysicslabAvailability.setEnabled(false);
        edtScienceLabCondition.setEnabled(false);
                edtScienceEquipmentStatus.setEnabled(false);
                edtHomeSciencelabAvailability.setEnabled(false);
    }

    private JsonObject paraGetDetails(String action, String schoolId, String periodId) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",action);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodId);
        jsonObject.addProperty("ParamId","3");
        return jsonObject;
    }
}