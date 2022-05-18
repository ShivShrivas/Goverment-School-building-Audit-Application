package com.bsn.buildingaudit.Activies;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.OnlineImageRecViewAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.LabDetailsResponse;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSubmit_PracticalLabsDetails extends AppCompatActivity {
    private TextView schoolAddress,schoolName,editPracticalLabDetails;
    ApplicationController applicationController;
    EditText edtHomeSciencelabAvailability,edtGeographyLabConditionedt,GeographyEquipmentStatus,edtGeographylabAvailability,edtBiologyLabCondition,edtBiologylabAvailability,edtBilogyEquipmentStatus
                    ,edtChemistryLabCondition,edtChemistryEquipmentStatus,edtChemistrylabAvailability,edtPhysicsLabCondition,edtPhysicsEquipmentStatus,edtPhysicslabAvailability
                ,edtHomeScienceLabCondition,edtHomeScienceEquipmentStatus
            ,edtScienceLabCondition,edtSciencelabAvailability,edtScienceEquipmentStatus,edtMusicLabCondition,edtHomeMusiclabAvailability,edtMusicEquipmentStatus
            ;
    RecyclerView recyclerViewMusicLab,recyclerViewHomeScienceLab,recyclerViewGeographyLab,recyclerViewScienceLab
            ,recyclerViewBiologyLab,recyclerViewChemistryLab,recyclerViewPhysicsLab;

    CardView scienceLabBodyCard,scienceLanImageCard,physicsLabBodyCard,physicsLabImageCard,
            chemistryLabBodyCard,chemistryLabImageCard,bioloyLabBodyCard,bioloyLabImageCard,
            homeScienceLabBodyCard,homeScienceLabImageCard,musicLabBodyCard,musicLabImageCard,
            geoGraphyLabImageCard,geoGraphyLabBodyCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_practical_labs_details);
        applicationController= (ApplicationController) getApplication();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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
        scienceLabBodyCard=findViewById(R.id.scienceLabBodyCard);
        scienceLanImageCard=findViewById(R.id.scienceLabImageCard);
        physicsLabImageCard=findViewById(R.id.physicsLabImageCard);
        physicsLabBodyCard=findViewById(R.id.physicsLabBodyCard);
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
        setAllEditTextDisabled();
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<LabDetailsResponse>> call=apiService.checkLabDetails(paraGetDetails("2",applicationController.getSchoolId(), applicationController.getPeriodID()));
        call.enqueue(new Callback<List<LabDetailsResponse>>() {
            @Override
            public void onResponse(Call<List<LabDetailsResponse>> call, Response<List<LabDetailsResponse>> response) {
                Log.d("TAG", "onResponse: "+response.body());
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


                    edtPhysicsLabCondition.setText(list.get(1).getLabCondition().toString());


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
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,scinceLabPhotoPathList);
                recyclerViewScienceLab.setAdapter(onlineImageRecViewAdapter);
            }catch (Exception e){

            }

                try {
                    Log.d("TAG", "onResponse: "+list.get(1).getLabPhotoPath());

                    String[] physicsLabPhotoPathList=list.get(1).getLabPhotoPath().split(",");
                    OnlineImageRecViewAdapter onlineImageRecViewAdapter1=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,physicsLabPhotoPathList);
                    recyclerViewPhysicsLab.setAdapter(onlineImageRecViewAdapter1);
            }catch (Exception e){

            }

                try {
                    Log.d("TAG", "onResponse: "+list.get(2).getLabPhotoPath());

                    String[] chemistryLabPhotoPathLis2=list.get(2).getLabPhotoPath().split(",");
                    OnlineImageRecViewAdapter onlineImageRecViewAdapter3=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,chemistryLabPhotoPathLis2);
                    recyclerViewChemistryLab.setAdapter(onlineImageRecViewAdapter3);
            }catch (Exception e){

            }

                try {
                    Log.d("TAG", "onResponse: "+list.get(3).getLabPhotoPath());

                    String[] biologyLAbList=list.get(3).getLabPhotoPath().split(",");
                    OnlineImageRecViewAdapter onlineImageRecViewAdapter4=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,biologyLAbList);
                    recyclerViewBiologyLab.setAdapter(onlineImageRecViewAdapter4);
            }catch (Exception e){

            }



                try {
                    Log.d("TAG", "onResponse: "+list.get(4).getLabPhotoPath());

                    String[] homscienceLabList=list.get(4).getLabPhotoPath().split(",");
                        OnlineImageRecViewAdapter onlineImageRecViewAdapter5=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,homscienceLabList);
                    recyclerViewHomeScienceLab.setAdapter(onlineImageRecViewAdapter5);
                    }catch (Exception e){

                    }

               try {
                   Log.d("TAG", "onResponse: "+list.get(5).getLabPhotoPath());

                   String[] muscilabList=list.get(5).getLabPhotoPath().split(",");
                   OnlineImageRecViewAdapter onlineImageRecViewAdapter6=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,muscilabList);
                   recyclerViewMusicLab.setAdapter(onlineImageRecViewAdapter6);
               }catch (Exception e){

               }

              try {
                  Log.d("TAG", "onResponse: "+list.get(6).getLabPhotoPath());

                  String[] geoGraphyList=list.get(6).getLabPhotoPath().split(",");
                  OnlineImageRecViewAdapter onlineImageRecViewAdapter7=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,geoGraphyList);
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