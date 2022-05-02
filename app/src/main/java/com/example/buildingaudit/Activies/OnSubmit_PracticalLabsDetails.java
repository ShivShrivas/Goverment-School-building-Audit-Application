package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.buildingaudit.Adapters.OnlineImageRecViewAdapter;
import com.example.buildingaudit.ApplicationController;
import com.example.buildingaudit.Model.LabDetailsResponse;
import com.example.buildingaudit.R;
import com.example.buildingaudit.RetrofitApi.ApiService;
import com.example.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.security.spec.ECField;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSubmit_PracticalLabsDetails extends AppCompatActivity {
    private TextView schoolAddress,schoolName;
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
                if (list.get(0).getEquipmentStatus().toString().equals("PE")){
                    edtScienceEquipmentStatus.setText("Partially Equipped");
                }else  if (list.get(0).getEquipmentStatus().toString().equals("FE")){
                    edtScienceEquipmentStatus.setText("Fully Equipped");
                }if (list.get(0).getEquipmentStatus().toString().equals("NE")){
                    edtScienceEquipmentStatus.setText("Not Equipped");
                }
                if (list.get(0).getLabCondition().toString().equals("Major")){
                    edtScienceLabCondition.setText("Major Repairing");
                }else  if (list.get(0).getLabCondition().toString().equals("Minor")){
                    edtScienceEquipmentStatus.setText("Minor Repairing");
                }if (list.get(0).getLabCondition().toString().equals("Good")){
                    edtScienceEquipmentStatus.setText("Good Condition");
                }


                if (list.get(1).getLabYN().toString().equals("Yes")){
                    edtPhysicslabAvailability.setText(list.get(1).getLabYN());
                }else {
                    hidecards(physicsLabBodyCard,physicsLabImageCard);
                    edtPhysicslabAvailability.setText(list.get(1).getLabYN());

                }

                if (list.get(1).getEquipmentStatus().toString().equals("PE")){
                    edtPhysicsEquipmentStatus.setText("Partially Equipped");
                }else  if (list.get(1).getEquipmentStatus().toString().equals("FE")){
                    edtPhysicsEquipmentStatus.setText("Fully Equipped");
                }if (list.get(1).getEquipmentStatus().toString().equals("NE")){
                    edtPhysicsEquipmentStatus.setText("Not Equipped");
                }
                if (list.get(1).getLabCondition().toString().equals("Major")){
                    edtPhysicsLabCondition.setText("Major Repairing");
                }else  if (list.get(1).getLabCondition().toString().equals("Minor")){
                    edtPhysicsLabCondition.setText("Minor Repairing");
                }if (list.get(1).getLabCondition().toString().equals("Good")){
                    edtPhysicsLabCondition.setText("Good Condition");
                }

                if (list.get(2).getLabYN().toString().equals("Yes")){
                    edtChemistrylabAvailability.setText(list.get(1).getLabYN());
                }else {
                    hidecards(chemistryLabBodyCard,chemistryLabImageCard);
                    edtChemistrylabAvailability.setText(list.get(1).getLabYN());

                }

                if (list.get(2).getEquipmentStatus().toString().equals("PE")){
                    edtChemistryEquipmentStatus.setText("Partially Equipped");
                }else  if (list.get(2).getEquipmentStatus().toString().equals("FE")){
                    edtChemistryEquipmentStatus.setText("Fully Equipped");
                }if (list.get(2).getEquipmentStatus().toString().equals("NE")){
                    edtChemistryEquipmentStatus.setText("Not Equipped");
                }
                if (list.get(2).getLabCondition().toString().equals("Major")){
                    edtChemistryLabCondition.setText("Major Repairing");
                }else  if (list.get(2).getLabCondition().toString().equals("Minor")){
                    edtChemistryLabCondition.setText("Minor Repairing");
                }if (list.get(2).getLabCondition().toString().equals("Good")){
                    edtChemistryLabCondition.setText("Good Condition");
                }

                if (list.get(2).getLabYN().toString().equals("Yes")){
                    edtBiologylabAvailability.setText(list.get(3).getLabYN());
                }else {
                    hidecards(bioloyLabBodyCard,bioloyLabImageCard);
                    edtBiologylabAvailability.setText(list.get(3).getLabYN());

                }

                if (list.get(3).getEquipmentStatus().toString().equals("PE")){
                    edtBilogyEquipmentStatus.setText("Partially Equipped");
                }else  if (list.get(3).getEquipmentStatus().toString().equals("FE")){
                    edtBilogyEquipmentStatus.setText("Fully Equipped");
                }if (list.get(3).getEquipmentStatus().toString().equals("NE")){
                    edtBilogyEquipmentStatus.setText("Not Equipped");
                }
                if (list.get(3).getLabCondition().toString().equals("Major")){
                    edtBiologyLabCondition.setText("Major Repairing");
                }else  if (list.get(3).getLabCondition().toString().equals("Minor")){
                    edtBiologyLabCondition.setText("Minor Repairing");
                }if (list.get(3).getLabCondition().toString().equals("Good")){
                    edtBiologyLabCondition.setText("Good Condition");
                }

                if (list.get(4).getLabYN().toString().equals("Yes")){
                    edtHomeSciencelabAvailability.setText(list.get(4).getLabYN());
                }else {
                    hidecards(homeScienceLabBodyCard,homeScienceLabImageCard);
                    edtHomeSciencelabAvailability.setText(list.get(4).getLabYN());

                }


                if (list.get(4).getEquipmentStatus().toString().equals("PE")){
                    edtHomeScienceEquipmentStatus.setText("Partially Equipped");
                }else  if (list.get(4).getEquipmentStatus().toString().equals("FE")){
                    edtHomeScienceEquipmentStatus.setText("Fully Equipped");
                }if (list.get(4).getEquipmentStatus().toString().equals("NE")){
                    edtHomeScienceEquipmentStatus.setText("Not Equipped");
                }
                if (list.get(4).getLabCondition().toString().equals("Major")){
                    edtHomeScienceLabCondition.setText("Major Repairing");
                }else  if (list.get(4).getLabCondition().toString().equals("Minor")){
                    edtHomeScienceLabCondition.setText("Minor Repairing");
                }if (list.get(4).getLabCondition().toString().equals("Good")){
                    edtHomeScienceLabCondition.setText("Good Condition");
                }

                if (list.get(5).getLabYN().toString().equals("Yes")){
                    edtHomeMusiclabAvailability.setText(list.get(5).getLabYN());
                }else {
                    hidecards(musicLabBodyCard,musicLabImageCard);
                    edtHomeMusiclabAvailability.setText(list.get(5).getLabYN());

                }


                if (list.get(5).getEquipmentStatus().toString().equals("PE")){
                    edtMusicEquipmentStatus.setText("Partially Equipped");
                }else  if (list.get(5).getEquipmentStatus().toString().equals("FE")){
                    edtMusicEquipmentStatus.setText("Fully Equipped");
                }if (list.get(5).getEquipmentStatus().toString().equals("NE")){
                    edtMusicEquipmentStatus.setText("Not Equipped");
                }
                if (list.get(5).getLabCondition().toString().equals("Major")){
                    edtMusicLabCondition.setText("Major Repairing");
                }else  if (list.get(5).getLabCondition().toString().equals("Minor")){
                    edtMusicLabCondition.setText("Minor Repairing");
                }if (list.get(5).getLabCondition().toString().equals("Good")){
                    edtMusicLabCondition.setText("Good Condition");
                }


                if (list.get(6).getLabYN().toString().equals("Yes")){
                    edtGeographylabAvailability.setText(list.get(6).getLabYN());
                }else {
                    hidecards(geoGraphyLabBodyCard,geoGraphyLabImageCard);
                    edtGeographylabAvailability.setText(list.get(6).getLabYN());

                }



                if (list.get(6).getEquipmentStatus().toString().equals("PE")){
                    GeographyEquipmentStatus.setText("Partially Equipped");
                }else  if (list.get(6).getEquipmentStatus().toString().equals("FE")){
                    GeographyEquipmentStatus.setText("Fully Equipped");
                }if (list.get(6).getEquipmentStatus().toString().equals("NE")){
                    GeographyEquipmentStatus.setText("Not Equipped");
                }
                if (list.get(6).getLabCondition().toString().equals("Major")){
                    edtGeographyLabConditionedt.setText("Major Repairing");
                }else  if (list.get(6).getLabCondition().toString().equals("Minor")){
                    edtGeographyLabConditionedt.setText("Minor Repairing");
                }if (list.get(6).getLabCondition().toString().equals("Good")){
                    edtGeographyLabConditionedt.setText("Good Condition");
                }
                recyclerViewScienceLab.setLayoutManager(new LinearLayoutManager(OnSubmit_PracticalLabsDetails.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerViewPhysicsLab.setLayoutManager(new LinearLayoutManager(OnSubmit_PracticalLabsDetails.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerViewChemistryLab.setLayoutManager(new LinearLayoutManager(OnSubmit_PracticalLabsDetails.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerViewBiologyLab.setLayoutManager(new LinearLayoutManager(OnSubmit_PracticalLabsDetails.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerViewHomeScienceLab.setLayoutManager(new LinearLayoutManager(OnSubmit_PracticalLabsDetails.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerViewMusicLab.setLayoutManager(new LinearLayoutManager(OnSubmit_PracticalLabsDetails.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerViewGeographyLab.setLayoutManager(new LinearLayoutManager(OnSubmit_PracticalLabsDetails.this,LinearLayoutManager.HORIZONTAL,false));






            try {
                String[] scinceLabPhotoPathList=list.get(0).getLabPhotoPath().split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,scinceLabPhotoPathList);
                recyclerViewScienceLab.setAdapter(onlineImageRecViewAdapter);
            }catch (Exception e){

            }

                try {
                    String[] physicsLabPhotoPathList=list.get(1).getLabPhotoPath().split(",");
                    OnlineImageRecViewAdapter onlineImageRecViewAdapter1=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,physicsLabPhotoPathList);
                    recyclerViewPhysicsLab.setAdapter(onlineImageRecViewAdapter1);
            }catch (Exception e){

            }

                try {

                    String[] chemistryLabPhotoPathLis2=list.get(2).getLabPhotoPath().split(",");
                    OnlineImageRecViewAdapter onlineImageRecViewAdapter3=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,chemistryLabPhotoPathLis2);
                    recyclerViewChemistryLab.setAdapter(onlineImageRecViewAdapter3);
            }catch (Exception e){

            }

                try {

                    String[] biologyLAbList=list.get(3).getLabPhotoPath().split(",");
                    OnlineImageRecViewAdapter onlineImageRecViewAdapter4=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,biologyLAbList);
                    recyclerViewBiologyLab.setAdapter(onlineImageRecViewAdapter4);
            }catch (Exception e){

            }



                try {
                    String[] homscienceLabList=list.get(4).getLabPhotoPath().split(",");
                        OnlineImageRecViewAdapter onlineImageRecViewAdapter5=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,homscienceLabList);
                    recyclerViewHomeScienceLab.setAdapter(onlineImageRecViewAdapter5);
                    }catch (Exception e){

                    }

               try {
                   String[] muscilabList=list.get(5).getLabPhotoPath().split(",");
                   OnlineImageRecViewAdapter onlineImageRecViewAdapter6=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,muscilabList);
                   recyclerViewMusicLab.setAdapter(onlineImageRecViewAdapter6);
               }catch (Exception e){

               }

              try {
                  String[] geoGraphyList=list.get(6).getLabPhotoPath().split(",");
                  OnlineImageRecViewAdapter onlineImageRecViewAdapter7=new OnlineImageRecViewAdapter(OnSubmit_PracticalLabsDetails.this,geoGraphyList);
                  recyclerViewGeographyLab.setAdapter(onlineImageRecViewAdapter7);
              }catch (Exception e){

              }



            }

            @Override
            public void onFailure(Call<List<LabDetailsResponse>> call, Throwable t) {

            }
        });

    }

    private void hidecards(CardView scienceLabBodyCard, CardView scienceLanImageCard) {


                scienceLabBodyCard.setVisibility(View.GONE);
                scienceLanImageCard.setVisibility(View.GONE);


    }

    private void setAllEditTextDisabled() {
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
        return jsonObject;
    }
}