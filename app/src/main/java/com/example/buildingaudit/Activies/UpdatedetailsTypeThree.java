package com.example.buildingaudit.Activies;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildingaudit.Adapters.ImageAdapter5;
import com.example.buildingaudit.Adapters.OnlineImageRecViewAdapterEditable;
import com.example.buildingaudit.Adapters.OnlineImageRecViewAdapterEditable1;
import com.example.buildingaudit.Adapters.OnlineImageRecViewAdapterEditable2;
import com.example.buildingaudit.Adapters.OnlineImageRecViewAdapterEditable3;
import com.example.buildingaudit.Adapters.OnlineImageRecViewAdapterEditable4;
import com.example.buildingaudit.Adapters.OnlineImageRecViewAdapterEditable5;
import com.example.buildingaudit.Adapters.OnlineImageRecViewAdapterEditable6;
import com.example.buildingaudit.ApplicationController;
import com.example.buildingaudit.CompressLib.Compressor;
import com.example.buildingaudit.ConstantValues.ConstantFile;
import com.example.buildingaudit.Model.LabCondition;
import com.example.buildingaudit.Model.LabDetailsResponse;
import com.example.buildingaudit.R;
import com.example.buildingaudit.RetrofitApi.ApiService;
import com.example.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatedetailsTypeThree extends AppCompatActivity {




    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    String[] StaffPhotoPathList;
    ArrayList<String> aList=new ArrayList<>();
    ArrayList<String> bList=new ArrayList<>();
    ArrayList<String> cList=new ArrayList<>();
    ArrayList<String> dList=new ArrayList<>();
    ArrayList<String> eList=new ArrayList<>();
    ArrayList<String> fList=new ArrayList<>();
    ArrayList<String> gList=new ArrayList<>();
    String currentImagePath=null;
    File imageFile=null;
    String action;
    int BtnType;
    Dialog dialog,dialog2;
Spinner spinnerSciencelabAvailability,spinnerBiologylabAvailability,spinnerHomeMusiclabAvailability,spinnerHomeSciencelabAvailability,spinnerGeographylabAvailability,spinnerChemistrylabAvailability,spinnerPhysicslabAvailability;
Spinner spinnerMusicEquipmentStatus,spinnerHomeScienceEquipmentStatus
        ,spinnerScienceEquipmentStatus,spinnerBilogyEquipmentStatus,spinnerGeographyEquipmentStatus,spinnerChemistryEquipmentStatus
        ,spinnerPhysicsEquipmentStatus;
Spinner spinnerMusicLabCondition,spinnerHomeScienceLabCondition,spinnerGeographyLabCondition,spinnerScienceLabCondition,
        spinnerBiologyLabCondition,spinnerChemistryLabCondition,spinnerPhysicsLabCondition;

RecyclerView recyclerViewMusicLab,recyclerViewHomeScienceLab,recyclerViewGeographyLab,recyclerViewScienceLab
        ,recyclerViewBiologyLab,recyclerViewChemistryLab,recyclerViewPhysicsLab;

RecyclerView recyclerViewMusicLabFromServer,recyclerViewHomeScienceLabFromServer,recyclerViewGeographyLabFromServer,recyclerViewScienceLabFromServer
        ,recyclerViewBiologyLabFromServer,recyclerViewChemistryLabFromServer,recyclerViewPhysicsLabFromServer;

ImageView imageUpoadMusicLab,imageUpoadHomeScienceLab,imageUpoadGeographyLab
        ,imageUpoadScienceLab,imageUpoadBiologyLab,imageUpoadChemistryLab,imageUpoadPhysicsLab;
TextView scienceLabNametxt,physicsLabtxt,chemistryLabtxt,biologyLabTxt,homeScienceLabTxt,musicLabTxt,georgaphyLabTxt;
TextView userName,schoolAddress,schoolName;
Button submitLabBtn;

    ArrayAdapter<String> arrayAdapter,arrayAdapter2,arrayAdapter3;
ArrayList<LabCondition> scienceLabDetailsArray=new ArrayList<>();
ArrayList<LabCondition> physicLabDetailsArray=new ArrayList<>();
ArrayList<LabCondition> chemistryLabDetailsArray=new ArrayList<>();
ArrayList<LabCondition> biologyLabDetailsArray=new ArrayList<>();
ArrayList<LabCondition> musicLabDetailsArray=new ArrayList<>();
ArrayList<LabCondition> homeScienceLabDetailsArray=new ArrayList<>();
ArrayList<LabCondition> geoGraphyLabDetailsArray=new ArrayList<>();
ApplicationController applicationController;
    ImageAdapter5 adapter1,adapter2,adapter3,adapter4,adapter5,adapter6,adapter7;
    public ArrayList<File> arrayListImages1 = new ArrayList<>();
    public ArrayList<File> arrayListImages2 = new ArrayList<>();
    public ArrayList<File> arrayListImages3= new ArrayList<>();
    public ArrayList<File> arrayListImages4 = new ArrayList<>();
    public ArrayList<File> arrayListImages5 = new ArrayList<>();
    public ArrayList<File> arrayListImages6 = new ArrayList<>();
    public ArrayList<File> arrayListImages7 = new ArrayList<>();
    public ArrayList<File> arrayListImagesFinal = new ArrayList<>();

    CardView scienceLabBodyCard,scienceLanImageCard,physicsLabBodyCard,physicsLabImageCard,
            chemistryLabBodyCard,chemistryLabImageCard,bioloyLabBodyCard,bioloyLabImageCard,
            homeScienceLabBodyCard,homeScienceLabImageCard,musicLabBodyCard,musicLabImageCard,
            geoGraphyLabImageCard,geoGraphyLabBodyCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedetails_type_three);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Intent i1=getIntent();
        action=i1.getStringExtra("Action");
        dialog = new Dialog(this);
        dialog.setCancelable(false);

        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.respons_dialog);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2 = new Dialog(this);

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        spinnerHomeMusiclabAvailability=findViewById(R.id.spinnerHomeMusiclabAvailability);
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
        spinnerChemistrylabAvailability=findViewById(R.id.spinnerChemistrylabAvailability);
        spinnerHomeSciencelabAvailability=findViewById(R.id.spinnerHomeSciencelabAvailability);
        spinnerGeographylabAvailability=findViewById(R.id.spinnerGeographylabAvailability);
        spinnerPhysicslabAvailability=findViewById(R.id.spinnerPhysicslabAvailability);
        spinnerBiologylabAvailability=findViewById(R.id.spinnerBiologylabAvailability);
        spinnerSciencelabAvailability=findViewById(R.id.spinnerSciencelabAvailability);
        spinnerMusicEquipmentStatus=findViewById(R.id.spinnerMusicEquipmentStatus);
        spinnerHomeScienceEquipmentStatus=findViewById(R.id.spinnerHomeScienceEquipmentStatus);
        spinnerScienceEquipmentStatus=findViewById(R.id.spinnerScienceEquipmentStatus);
        spinnerBilogyEquipmentStatus=findViewById(R.id.spinnerBilogyEquipmentStatus);
        spinnerGeographyEquipmentStatus=findViewById(R.id.spinnerGeographyEquipmentStatus);
        spinnerChemistryEquipmentStatus=findViewById(R.id.spinnerChemistryEquipmentStatus);
        spinnerPhysicsEquipmentStatus=findViewById(R.id.spinnerPhysicsEquipmentStatus);
        spinnerMusicLabCondition=findViewById(R.id.spinnerMusicLabCondition);
        spinnerHomeScienceLabCondition=findViewById(R.id.spinnerHomeScienceLabCondition);
        spinnerGeographyLabCondition=findViewById(R.id.spinnerGeographyLabCondition);
        spinnerScienceLabCondition=findViewById(R.id.spinnerScienceLabCondition);
        spinnerBiologyLabCondition=findViewById(R.id.spinnerBiologyLabCondition);
        spinnerChemistryLabCondition=findViewById(R.id.spinnerChemistryLabCondition);
        spinnerPhysicsLabCondition=findViewById(R.id.spinnerPhysicsLabCondition);

        scienceLabNametxt=findViewById(R.id.scienceLabNametxt);
        physicsLabtxt=findViewById(R.id.physicsLabtxt);
        chemistryLabtxt=findViewById(R.id.chemistryLabtxt);
        biologyLabTxt=findViewById(R.id.biologyLabTxt);
        homeScienceLabTxt=findViewById(R.id.homeScienceLabTxt);
        musicLabTxt=findViewById(R.id.musicLabTxt);
        georgaphyLabTxt=findViewById(R.id.georgaphyLabTxt);
        recyclerViewMusicLab=findViewById(R.id.recyclerViewMusicLab);
        recyclerViewHomeScienceLab=findViewById(R.id.recyclerViewHomeScienceLab);
        recyclerViewGeographyLab=findViewById(R.id.recyclerViewGeographyLab);
        recyclerViewScienceLab=findViewById(R.id.recyclerViewScienceLab);
        recyclerViewBiologyLab=findViewById(R.id.recyclerViewBiologyLab);
        recyclerViewChemistryLab=findViewById(R.id.recyclerViewChemistryLab);
        recyclerViewPhysicsLab=findViewById(R.id.recyclerViewPhysicsLab);

        recyclerViewMusicLabFromServer=findViewById(R.id.recyclerViewMusicLabFromServer);
        recyclerViewHomeScienceLabFromServer=findViewById(R.id.recyclerViewHomeScienceLabFromServer);
        recyclerViewGeographyLabFromServer=findViewById(R.id.recyclerViewGeographyLabFromServer);
        recyclerViewScienceLabFromServer=findViewById(R.id.recyclerViewScienceLabFromServer);
        recyclerViewBiologyLabFromServer=findViewById(R.id.recyclerViewBiologyLabFromServer);
        recyclerViewChemistryLabFromServer=findViewById(R.id.recyclerViewChemistryLabFromServer);
        recyclerViewPhysicsLabFromServer=findViewById(R.id.recyclerViewPhysicsLabFromServer);

        imageUpoadMusicLab=findViewById(R.id.imageUpoadMusicLab);
        imageUpoadHomeScienceLab=findViewById(R.id.imageUpoadHomeScienceLab);
        imageUpoadGeographyLab=findViewById(R.id.imageUpoadGeographyLab);
        imageUpoadScienceLab=findViewById(R.id.imageUpoadScienceLab);
        imageUpoadBiologyLab=findViewById(R.id.imageUpoadBiologyLab);
        imageUpoadChemistryLab=findViewById(R.id.imageUpoadChemistryLab);
        imageUpoadPhysicsLab=findViewById(R.id.imageUpoadPhysicsLab);
        submitLabBtn=findViewById(R.id.submitLabBtn);



        ArrayList<String> arrayListAvailbilty=new ArrayList<>();
        arrayListAvailbilty.add("Yes");
        arrayListAvailbilty.add("No");
        arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayListAvailbilty);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerHomeMusiclabAvailability.setAdapter(arrayAdapter);
                spinnerChemistrylabAvailability.setAdapter(arrayAdapter);
        spinnerHomeSciencelabAvailability.setAdapter(arrayAdapter);
                spinnerGeographylabAvailability.setAdapter(arrayAdapter);
        spinnerPhysicslabAvailability.setAdapter(arrayAdapter);
                spinnerBiologylabAvailability.setAdapter(arrayAdapter);
        spinnerSciencelabAvailability.setAdapter(arrayAdapter);

        ArrayList<String> arrayListSpinner2 = new ArrayList<>();
        arrayListSpinner2.add("Fully Equipped");
        arrayListSpinner2.add("Partially Equipped");
        arrayListSpinner2.add("Not Equipped");

         arrayAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListSpinner2);
        arrayAdapter2.setDropDownViewResource(R.layout.custom_text_spiiner);
        spinnerMusicEquipmentStatus.setAdapter(arrayAdapter2);
                spinnerHomeScienceEquipmentStatus.setAdapter(arrayAdapter2);
        spinnerScienceEquipmentStatus.setAdapter(arrayAdapter2);
                spinnerBilogyEquipmentStatus.setAdapter(arrayAdapter2);
        spinnerGeographyEquipmentStatus.setAdapter(arrayAdapter2);
                spinnerChemistryEquipmentStatus.setAdapter(arrayAdapter2);
        spinnerPhysicsEquipmentStatus.setAdapter(arrayAdapter2);
        String[] StaffPhotoPathList;
        ArrayList<String> aList=new ArrayList<>();
        ArrayList<String> arrayListSpinner3 = new ArrayList<>();

        arrayListSpinner3.add("Good Condition");
        arrayListSpinner3.add("Minor Repairing");
        arrayListSpinner3.add("Major repairing");

         arrayAdapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListSpinner3);
        arrayAdapter3.setDropDownViewResource(R.layout.custom_text_spiiner);

        spinnerMusicLabCondition.setAdapter(arrayAdapter3);
                spinnerHomeScienceLabCondition.setAdapter(arrayAdapter3);
        spinnerGeographyLabCondition.setAdapter(arrayAdapter3);
                spinnerScienceLabCondition.setAdapter(arrayAdapter3);
        spinnerBiologyLabCondition.setAdapter(arrayAdapter3);
                spinnerChemistryLabCondition.setAdapter(arrayAdapter3);
        spinnerPhysicsLabCondition.setAdapter(arrayAdapter3);

        if (action.equals("3")){
            fetchAllDataFromServer();
        }

        imageUpoadPhysicsLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnType=1;
                checkPermissions(1);
            }
        });

        imageUpoadChemistryLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnType=2;
                checkPermissions(2);
            }
        });

        imageUpoadBiologyLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnType=3;
                checkPermissions(3);
            }
        });


        imageUpoadHomeScienceLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnType=4;
                checkPermissions(4);
            }
        });

        imageUpoadMusicLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnType=5;
                checkPermissions(5);
            }
        });

        imageUpoadGeographyLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnType=6;
                checkPermissions(6);
            }
        });

        imageUpoadScienceLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnType=7;
                checkPermissions(7);
            }
        });

        recyclerViewPhysicsLab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter1 = new ImageAdapter5(this, arrayListImages1);
        recyclerViewPhysicsLab.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();


        recyclerViewChemistryLab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter2 = new ImageAdapter5(this, arrayListImages2);
        recyclerViewChemistryLab.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();

        recyclerViewBiologyLab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter3 = new ImageAdapter5(this, arrayListImages3);
        recyclerViewBiologyLab.setAdapter(adapter3);
        adapter3.notifyDataSetChanged();

        recyclerViewHomeScienceLab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter4= new ImageAdapter5(this, arrayListImages4);
        recyclerViewHomeScienceLab.setAdapter(adapter4);
        adapter4.notifyDataSetChanged();

        recyclerViewMusicLab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter5= new ImageAdapter5(this, arrayListImages5);
        recyclerViewMusicLab.setAdapter(adapter5);
        adapter5.notifyDataSetChanged();

        recyclerViewGeographyLab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter6= new ImageAdapter5(this, arrayListImages6);
        recyclerViewGeographyLab.setAdapter(adapter6);
        adapter6.notifyDataSetChanged();

        recyclerViewScienceLab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter7= new ImageAdapter5(this, arrayListImages7);
        recyclerViewScienceLab.setAdapter(adapter7);
        adapter7.notifyDataSetChanged();

    spinnerSciencelabAvailability.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hideShowCards(scienceLabBodyCard,scienceLanImageCard,adapterView);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    spinnerPhysicslabAvailability.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hideShowCards(physicsLabBodyCard,physicsLabImageCard,adapterView);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    spinnerChemistrylabAvailability.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hideShowCards(chemistryLabBodyCard,chemistryLabImageCard,adapterView);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    spinnerBiologylabAvailability.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hideShowCards(bioloyLabBodyCard,bioloyLabImageCard,adapterView);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    spinnerHomeSciencelabAvailability.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hideShowCards(homeScienceLabBodyCard,homeScienceLabImageCard,adapterView);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    spinnerHomeMusiclabAvailability.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hideShowCards(musicLabBodyCard,musicLabImageCard,adapterView);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    spinnerGeographylabAvailability.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hideShowCards(geoGraphyLabBodyCard,geoGraphyLabImageCard,adapterView);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        spinnerSciencelabAvailability.getSelectedItem().toString(),spinnerScienceEquipmentStatus.getSelectedItem().toString(),spinnerScienceLabCondition.getSelectedItem().toString(),
//                spinnerPhysicslabAvailability.getSelectedItem().toString(),spinnerPhysicsEquipmentStatus.getSelectedItem().toString(),spinnerPhysicsLabCondition.getSelectedItem().toString(),
//                spinnerChemistrylabAvailability.getSelectedItem().toString(),spinnerChemistryEquipmentStatus.getSelectedItem().toString(),spinnerChemistryLabCondition.getSelectedItem().toString(),
//                spinnerBiologylabAvailability.getSelectedItem().toString(),spinnerBilogyEquipmentStatus.getSelectedItem().toString(),spinnerBiologyLabCondition.getSelectedItem().toString(),
//                spinnerHomeSciencelabAvailability.getSelectedItem().toString(),spinnerHomeScienceEquipmentStatus.getSelectedItem().toString(),spinnerHomeScienceLabCondition.getSelectedItem().toString(),
//                spinnerHomeMusiclabAvailability.getSelectedItem().toString(),spinnerMusicEquipmentStatus.getSelectedItem().toString(),spinnerMusicLabCondition.getSelectedItem().toString(),
//                spinnerGeographylabAvailability.getSelectedItem().toString(),spinnerGeographyEquipmentStatus.getSelectedItem().toString(),spinnerGeographyLabCondition.getSelectedItem().toString(),

//        "action","paramId","PracticalLabsDetail","spinnerSciencelabAvailability","spinnerScienceEquipmentStatus","spinnerScienceLabCondition",
//                "spinnerPhysicslabAvailability","spinnerPhysicsEquipmentStatus","spinnerPhysicsLabCondition",
//                "spinnerChemistrylabAvailability","spinnerChemistryEquipmentStatus","spinnerChemistryLabCondition",
//                "spinnerBiologylabAvailability","spinnerBilogyEquipmentStatus","spinnerBiologyLabCondition",
//                "spinnerHomeSciencelabAvailability","spinnerHomeScienceEquipmentStatus","spinnerHomeScienceLabCondition",
//                "spinnerHomeMusiclabAvailability","spinnerMusicEquipmentStatus","spinnerMusicLabCondition",
//                "spinnerGeographylabAvailability","spinnerGeographyEquipmentStatus","spinnerGeographyLabCondition",
        submitLabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
dialog2.show();
                LabCondition scienceLabCondition=new LabCondition();
                scienceLabCondition.setLabName("ScienceLab");
                scienceLabCondition.setLabCondition(spinnerScienceLabCondition.getSelectedItem().toString());
                scienceLabCondition.setLabYN(spinnerSciencelabAvailability.getSelectedItem().toString());
                scienceLabCondition.setSrno("1");
                scienceLabCondition.setEquipmentStatus(spinnerScienceEquipmentStatus.getSelectedItem().toString());

                LabCondition physicsLabCondition=new LabCondition();
                physicsLabCondition.setLabName("PhysicsLab");
                physicsLabCondition.setLabCondition(spinnerPhysicsLabCondition.getSelectedItem().toString());
                physicsLabCondition.setLabYN(spinnerPhysicslabAvailability.getSelectedItem().toString());
                physicsLabCondition.setSrno("2");
                physicsLabCondition.setEquipmentStatus(spinnerPhysicsEquipmentStatus.getSelectedItem().toString());



                LabCondition chemistryLabCondition=new LabCondition();
                chemistryLabCondition.setLabName("ChemistryLab");
                chemistryLabCondition.setLabCondition((String) spinnerChemistryLabCondition.getSelectedItem());
                chemistryLabCondition.setLabYN((String) spinnerChemistrylabAvailability.getSelectedItem());
                chemistryLabCondition.setSrno("3");
                chemistryLabCondition.setEquipmentStatus(spinnerChemistryEquipmentStatus.getSelectedItem().toString());




                LabCondition biologyLabCondition=new LabCondition();
                biologyLabCondition.setLabName("BiologyLab");
                biologyLabCondition.setLabCondition(spinnerBiologyLabCondition.getSelectedItem().toString());
                biologyLabCondition.setLabYN(spinnerBiologylabAvailability.getSelectedItem().toString());
                biologyLabCondition.setSrno("4");
                biologyLabCondition.setEquipmentStatus(spinnerBilogyEquipmentStatus.getSelectedItem().toString());




                LabCondition homeScienceLabCondition=new LabCondition();
               homeScienceLabCondition.setLabName("HomeScienceLab");
               homeScienceLabCondition.setLabCondition(spinnerHomeScienceLabCondition.getSelectedItem().toString());
               homeScienceLabCondition.setLabYN(spinnerHomeSciencelabAvailability.getSelectedItem().toString());
               homeScienceLabCondition.setSrno("5");
               homeScienceLabCondition.setEquipmentStatus(spinnerHomeScienceEquipmentStatus.getSelectedItem().toString());



                LabCondition musicLabCondition=new LabCondition();
              musicLabCondition.setLabName("MusicLab");
              musicLabCondition.setLabCondition(spinnerMusicLabCondition.getSelectedItem().toString());
              musicLabCondition.setLabYN(spinnerHomeMusiclabAvailability.getSelectedItem().toString());
              musicLabCondition.setSrno("6");
              musicLabCondition.setEquipmentStatus(spinnerMusicEquipmentStatus.getSelectedItem().toString());



                LabCondition geographyLabCondition=new LabCondition();
               geographyLabCondition.setLabName("GeographyLab");
               geographyLabCondition.setLabCondition(spinnerGeographyLabCondition.getSelectedItem().toString());
               geographyLabCondition.setLabYN(spinnerGeographylabAvailability.getSelectedItem().toString());
               geographyLabCondition.setSrno("7");
               geographyLabCondition.setEquipmentStatus(spinnerGeographyEquipmentStatus.getSelectedItem().toString());

                    if (spinnerSciencelabAvailability.getSelectedItem().toString().equals("Yes") || spinnerPhysicslabAvailability.getSelectedItem().toString().equals("Yes") ||
                            spinnerChemistrylabAvailability.getSelectedItem().toString().equals("Yes") || spinnerHomeMusiclabAvailability.getSelectedItem().toString().equals("Yes") || spinnerGeographylabAvailability.getSelectedItem().toString().equals("Yes")||
                            spinnerHomeSciencelabAvailability.getSelectedItem().toString().equals("Yes") || spinnerGeographylabAvailability.getSelectedItem().toString().equals("Yes")
                    ){


                if (arrayListImages1.size()==0 &&arrayListImages2.size()==0 &&arrayListImages3.size()==0 &&arrayListImages4.size()==0 &&arrayListImages5.size()==0 &&arrayListImages6.size()==0 &&arrayListImages7.size()==0 ){
                    dialog2.dismiss();

                    Toast.makeText(UpdatedetailsTypeThree.this, "Please Capture minimum one Image!!", Toast.LENGTH_SHORT).show();

                }else {
                        runService(scienceLabCondition,physicsLabCondition,chemistryLabCondition,biologyLabCondition,homeScienceLabCondition,musicLabCondition,geographyLabCondition);
            }
            }else {
                        runService(scienceLabCondition,physicsLabCondition,chemistryLabCondition,biologyLabCondition,homeScienceLabCondition,musicLabCondition,geographyLabCondition);

                    }
            }
        });

    }


    private void fetchAllDataFromServer() {
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<LabDetailsResponse>> call=apiService.checkLabDetails(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"3"));
        call.enqueue(new Callback<List<LabDetailsResponse>>() {
            @Override
            public void onResponse(Call<List<LabDetailsResponse>> call, Response<List<LabDetailsResponse>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                int spinnerAvalabiltyScience = arrayAdapter.getPosition(response.body().get(0).getLabYN());
                int spinnerAvalabiltyPhysics = arrayAdapter.getPosition(response.body().get(1).getLabYN());
                int spinnerAvalabiltyChemistry = arrayAdapter.getPosition(response.body().get(2).getLabYN());
                int spinnerAvalabiltybiology = arrayAdapter.getPosition(response.body().get(3).getLabYN());
                int spinnerAvalabiltyHomeScience = arrayAdapter.getPosition(response.body().get(4).getLabYN());
                int spinnerAvalabiltyMusics = arrayAdapter.getPosition(response.body().get(5).getLabYN());
                int spinnerAvalabiltyGeo = arrayAdapter.getPosition(response.body().get(6).getLabYN());

                int spinnerEquipmentStatusScience = arrayAdapter.getPosition(response.body().get(0).getEquipmentStatus());
                int spinnerEquipmentStatusPhysics = arrayAdapter.getPosition(response.body().get(1).getEquipmentStatus());
                int spinnerEquipmentStatusChemistry = arrayAdapter.getPosition(response.body().get(2).getEquipmentStatus());
                int spinnerEquipmentStatusbiology = arrayAdapter.getPosition(response.body().get(3).getEquipmentStatus());
                int spinnerEquipmentStatusHomeScience = arrayAdapter.getPosition(response.body().get(4).getEquipmentStatus());
                int spinnerEquipmentStatusMusics = arrayAdapter.getPosition(response.body().get(5).getEquipmentStatus());
                int spinnerEquipmentStatusGeo = arrayAdapter.getPosition(response.body().get(6).getEquipmentStatus());

                int spinnerLabConditionScience = arrayAdapter.getPosition(response.body().get(0).getLabCondition());
                int spinnerLabConditionPhysics = arrayAdapter.getPosition(response.body().get(1).getLabCondition());
                int spinnerLabConditionChemistry = arrayAdapter.getPosition(response.body().get(2).getLabCondition());
                int spinnerLabConditionbiology = arrayAdapter.getPosition(response.body().get(3).getLabCondition());
                int spinnerLabConditionHomeScience = arrayAdapter.getPosition(response.body().get(4).getLabCondition());
                int spinnerLabConditionMusics = arrayAdapter.getPosition(response.body().get(5).getLabCondition());
                int spinnerLabConditionGeo = arrayAdapter.getPosition(response.body().get(6).getLabCondition());

                spinnerSciencelabAvailability.setSelection(spinnerAvalabiltyScience);
                spinnerPhysicslabAvailability.setSelection(spinnerAvalabiltyPhysics);
                spinnerChemistrylabAvailability.setSelection(spinnerAvalabiltyChemistry);
                spinnerBiologylabAvailability.setSelection(spinnerAvalabiltybiology);
                spinnerHomeSciencelabAvailability.setSelection(spinnerAvalabiltyHomeScience);
                spinnerHomeMusiclabAvailability.setSelection(spinnerAvalabiltyMusics);
                spinnerGeographylabAvailability.setSelection(spinnerAvalabiltyGeo);

                spinnerScienceEquipmentStatus.setSelection(spinnerEquipmentStatusScience);
                spinnerPhysicsEquipmentStatus.setSelection(spinnerEquipmentStatusPhysics);
                spinnerChemistryEquipmentStatus.setSelection(spinnerEquipmentStatusChemistry);
                spinnerBilogyEquipmentStatus.setSelection(spinnerEquipmentStatusbiology);
                spinnerHomeScienceEquipmentStatus.setSelection(spinnerEquipmentStatusHomeScience);
                spinnerMusicEquipmentStatus.setSelection(spinnerEquipmentStatusMusics);
                spinnerGeographyEquipmentStatus.setSelection(spinnerEquipmentStatusGeo);


                spinnerScienceLabCondition.setSelection(spinnerLabConditionScience);
                spinnerPhysicsLabCondition.setSelection(spinnerLabConditionPhysics);
                spinnerChemistryLabCondition.setSelection(spinnerLabConditionChemistry);
                spinnerBiologyLabCondition.setSelection(spinnerLabConditionbiology);
                spinnerHomeScienceLabCondition.setSelection(spinnerLabConditionHomeScience);
                spinnerMusicLabCondition.setSelection(spinnerLabConditionMusics);
                spinnerGeographyLabCondition.setSelection(spinnerLabConditionGeo);


                aList = new ArrayList<String>(Arrays.asList(response.body().get(0).getLabPhotoPath()));
                bList = new ArrayList<String>(Arrays.asList(response.body().get(1).getLabPhotoPath()));
                cList = new ArrayList<String>(Arrays.asList(response.body().get(2).getLabPhotoPath()));
                dList = new ArrayList<String>(Arrays.asList(response.body().get(3).getLabPhotoPath()));
                eList = new ArrayList<String>(Arrays.asList(response.body().get(4).getLabPhotoPath()));
                fList = new ArrayList<String>(Arrays.asList(response.body().get(5).getLabPhotoPath()));
                gList = new ArrayList<String>(Arrays.asList(response.body().get(6).getLabPhotoPath()));

                recyclerViewMusicLabFromServer.setLayoutManager(new LinearLayoutManager(UpdatedetailsTypeThree.this,LinearLayoutManager.HORIZONTAL,false));
                        recyclerViewHomeScienceLabFromServer.setLayoutManager(new LinearLayoutManager(UpdatedetailsTypeThree.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerViewGeographyLabFromServer.setLayoutManager(new LinearLayoutManager(UpdatedetailsTypeThree.this,LinearLayoutManager.HORIZONTAL,false));
                        recyclerViewScienceLabFromServer.setLayoutManager(new LinearLayoutManager(UpdatedetailsTypeThree.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerViewBiologyLabFromServer.setLayoutManager(new LinearLayoutManager(UpdatedetailsTypeThree.this,LinearLayoutManager.HORIZONTAL,false));
                        recyclerViewChemistryLabFromServer.setLayoutManager(new LinearLayoutManager(UpdatedetailsTypeThree.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerViewPhysicsLabFromServer.setLayoutManager(new LinearLayoutManager(UpdatedetailsTypeThree.this,LinearLayoutManager.HORIZONTAL,false));

                OnlineImageRecViewAdapterEditable onlineImageRecViewAdapter=new OnlineImageRecViewAdapterEditable(UpdatedetailsTypeThree.this,aList);
                OnlineImageRecViewAdapterEditable1 onlineImageRecViewAdapter1=new OnlineImageRecViewAdapterEditable1(UpdatedetailsTypeThree.this,bList);
                OnlineImageRecViewAdapterEditable2 onlineImageRecViewAdapter2=new OnlineImageRecViewAdapterEditable2(UpdatedetailsTypeThree.this,cList);
                OnlineImageRecViewAdapterEditable3 onlineImageRecViewAdapter3=new OnlineImageRecViewAdapterEditable3(UpdatedetailsTypeThree.this,dList);
                OnlineImageRecViewAdapterEditable4 onlineImageRecViewAdapter4=new OnlineImageRecViewAdapterEditable4(UpdatedetailsTypeThree.this,eList);
                OnlineImageRecViewAdapterEditable5 onlineImageRecViewAdapter5=new OnlineImageRecViewAdapterEditable5(UpdatedetailsTypeThree.this,fList);
                OnlineImageRecViewAdapterEditable6 onlineImageRecViewAdapter6=new OnlineImageRecViewAdapterEditable6(UpdatedetailsTypeThree.this,gList);


                recyclerViewScienceLabFromServer.setAdapter(onlineImageRecViewAdapter);
                recyclerViewPhysicsLabFromServer.setAdapter(onlineImageRecViewAdapter1);
                recyclerViewChemistryLabFromServer.setAdapter(onlineImageRecViewAdapter2);
                recyclerViewBiologyLabFromServer.setAdapter(onlineImageRecViewAdapter3);
                recyclerViewHomeScienceLabFromServer.setAdapter(onlineImageRecViewAdapter4);
                recyclerViewMusicLabFromServer.setAdapter(onlineImageRecViewAdapter5);
                recyclerViewGeographyLabFromServer.setAdapter(onlineImageRecViewAdapter6);

            }

            @Override
            public void onFailure(Call<List<LabDetailsResponse>> call, Throwable t) {

            }
        });
    }
    private JsonObject paraGetDetails2(String action, String schoolId, String periodId, String paramId) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",action);
        jsonObject.addProperty("ParamId",paramId);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodId);
        return jsonObject;
    }

    private void runService(LabCondition scienceLabCondition, LabCondition physicsLabCondition, LabCondition chemistryLabCondition, LabCondition biologyLabCondition, LabCondition homeScienceLabCondition, LabCondition musicLabCondition, LabCondition geographyLabCondition) {

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        arrayListImagesFinal.addAll(arrayListImages1);
        arrayListImagesFinal.addAll(arrayListImages2);
        arrayListImagesFinal.addAll(arrayListImages3);
        arrayListImagesFinal.addAll(arrayListImages4);
        arrayListImagesFinal.addAll(arrayListImages5);
        arrayListImagesFinal.addAll(arrayListImages6);
        arrayListImagesFinal.addAll(arrayListImages7);
        MultipartBody.Part[] surveyImagesParts = new MultipartBody.Part[arrayListImagesFinal.size()];
        for (int i = 0; i < arrayListImagesFinal.size(); i++) {
            Log.d("TAG","requestUploadSurvey: survey image " + i +"  " + arrayListImagesFinal.get(i).getPath());
            File compressedImage = new Compressor.Builder(UpdatedetailsTypeThree.this)
                    .setMaxWidth(720)
                    .setMaxHeight(720)
                    .setQuality(75)
                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES).getAbsolutePath())
                    .build()
                    .compressToFile(new File(arrayListImagesFinal.get(i).getPath()));
            RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"),
                    compressedImage);
            surveyImagesParts[i] = MultipartBody.Part.createFormData("FileData",compressedImage.getName(),surveyBody);

        }
        RequestBody deletUrl;
        Log.d("TAG", "runService: "+paraDeletUlrs());
        if (action.equals("3")){
            deletUrl = RequestBody.create(MediaType.parse("multipart/form-data"),paraDeletUlrs());
        }else {
            deletUrl=null;
        }
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"),paraLabDetails(action,"3","PracticalLabsDetail",scienceLabCondition,physicsLabCondition,chemistryLabCondition,biologyLabCondition,homeScienceLabCondition,musicLabCondition,geographyLabCondition, applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUserid(),arrayListImages7,arrayListImages1,arrayListImages2,arrayListImages3,arrayListImages4,arrayListImages5,arrayListImages6));
        Log.d("TAG", "onClick: "+paraLabDetails(action,"3","PracticalLabsDetail",scienceLabCondition,physicsLabCondition,chemistryLabCondition,biologyLabCondition,homeScienceLabCondition,musicLabCondition,geographyLabCondition, applicationController.getLatitude(),applicationController.getLongitude(),applicationController.getSchoolId(),applicationController.getPeriodID(), applicationController.getUsertypeid(),applicationController.getUsertype(),arrayListImages7,arrayListImages1,arrayListImages2,arrayListImages3,arrayListImages4,arrayListImages5,arrayListImages6));
        Call<List<JsonObject>> call=apiService.uploadLabDetails(surveyImagesParts,description);
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());
//                        Toast.makeText(UpdatedetailsTypeThree.this, ""+response.body(), Toast.LENGTH_SHORT).show();
                TextView textView=dialog.findViewById(R.id.dialogtextResponse);
                Button button=dialog.findViewById(R.id.BtnResponseDialoge);
                try {
                    if (response.body().get(0).get("Status").getAsString().equals("E")){
                        textView.setText("You already uploaded details ");

                    }else if(response.body().get(0).get("Status").getAsString().equals("S")){
                        textView.setText("Your details Submitted successfully ");
                    }
                    dialog2.dismiss();

                    dialog.show();
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onBackPressed();
                            dialog.dismiss();
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Something went wrong please try again!!", Toast.LENGTH_SHORT).show();
                    dialog2.dismiss();

                }

            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                TextView textView=dialog.findViewById(R.id.dialogtextResponse);
                Button button=dialog.findViewById(R.id.BtnResponseDialoge);


                textView.setText("Something went wrong please try again!! ");
                dialog2.dismiss();

                dialog.show();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                        dialog.dismiss();
                    }
                });
            }
        });

    }

    private String paraDeletUlrs() {
        JsonArray jsonArray=new JsonArray();
        ArrayList<String> deleteUrlsFinal=new ArrayList<String>();
        deleteUrlsFinal.addAll(OnlineImageRecViewAdapterEditable.deletedUrls);
        deleteUrlsFinal.addAll(OnlineImageRecViewAdapterEditable1.deletedUrls);
        deleteUrlsFinal.addAll(OnlineImageRecViewAdapterEditable2.deletedUrls);
        deleteUrlsFinal.addAll(OnlineImageRecViewAdapterEditable3.deletedUrls);
        deleteUrlsFinal.addAll(OnlineImageRecViewAdapterEditable4.deletedUrls);
        deleteUrlsFinal.addAll(OnlineImageRecViewAdapterEditable5.deletedUrls);
        deleteUrlsFinal.addAll(OnlineImageRecViewAdapterEditable6.deletedUrls);
        Log.d("TAG", "paraDeletUlrs: "+ deleteUrlsFinal.size());

        for (int i = 0; i < deleteUrlsFinal.size(); i++) {
            JsonObject jsonObject=new JsonObject();
            Log.d("TAG", "paraDeletUlrs: "+deleteUrlsFinal.get(i));
            String newUrl2=deleteUrlsFinal.get(i).replaceAll("\"","");
            jsonObject.addProperty("PhotoUrl",newUrl2);
            jsonArray.add(jsonObject);
        }


        return jsonArray.toString();
    }

    private String paraLabDetails(String action, String paramId, String practicalLabsDetail, LabCondition scienceLabCondition, LabCondition physicsLabCondition, LabCondition chemistryLabCondition, LabCondition biologyLabCondition, LabCondition homeScienceLabCondition, LabCondition musicLabCondition, LabCondition geographyLabCondition, String latitude, String longitude, String schoolId, String periodID, String userTypeId, String usertype, ArrayList<File> arrayListImages7, ArrayList<File> arrayListImages1, ArrayList<File> arrayListImages2, ArrayList<File> arrayListImages3, ArrayList<File> arrayListImages4, ArrayList<File> arrayListImages5, ArrayList<File> arrayListImages6) {
        int x=0;
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",action);
        jsonObject.addProperty("ParamId",paramId);
        jsonObject.addProperty("ParamName",practicalLabsDetail);

        JsonArray jsonArrayLabRecords = new JsonArray();
        jsonArrayLabRecords.add(getLabConditionJson(scienceLabCondition));
        jsonArrayLabRecords.add(getLabConditionJson(physicsLabCondition));
        jsonArrayLabRecords.add(getLabConditionJson(chemistryLabCondition));
        jsonArrayLabRecords.add(getLabConditionJson(biologyLabCondition));
        jsonArrayLabRecords.add(getLabConditionJson(homeScienceLabCondition));
        jsonArrayLabRecords.add(getLabConditionJson(musicLabCondition));
        jsonArrayLabRecords.add(getLabConditionJson(geographyLabCondition));
        jsonObject.add("LabRecords",(JsonElement) jsonArrayLabRecords);
        jsonObject.addProperty("Lat",latitude);
        jsonObject.addProperty("Long",longitude);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodID);
        jsonObject.addProperty("CreatedBy",userTypeId);
        jsonObject.addProperty("UserCode",usertype);
//        JsonArray jsonArrayLabPhoto = new JsonArray();
//
//        for (int i=0;i<arrayListImages7.size();i++){
//            jsonArrayLabPhoto.add( getLabImage(arrayListImages7.get(i),"Science Lab",x));
//            x++;
//        }for (int i=0;i<arrayListImages1.size();i++){
//            jsonArrayLabPhoto.add( getLabImage(arrayListImages1.get(i),"Physics Lab",x));
//            x++;
//        }for (int i=0;i<arrayListImages2.size();i++){
//            jsonArrayLabPhoto.add( getLabImage(arrayListImages2.get(i),"Chemistry Lab",x));
//            x++;
//        }for (int i=0;i<arrayListImages3.size();i++){
//            jsonArrayLabPhoto.add( getLabImage(arrayListImages3.get(i),"Biology Lab",x));
//            x++;
//        }for (int i=0;i<arrayListImages4.size();i++){
//            jsonArrayLabPhoto.add( getLabImage(arrayListImages4.get(i),"Home Science Lab",x));
//            x++;
//        }for (int i=0;i<arrayListImages5.size();i++){
//            jsonArrayLabPhoto.add( getLabImage(arrayListImages5.get(i),"Music Lab",x));
//            x++;
//        }for (int i=0;i<arrayListImages6.size();i++){
//            jsonArrayLabPhoto.add( getLabImage(arrayListImages6.get(i),"Geography Lab",x));
//            x++;
//        }
//        jsonArrayLabPhoto.add(getLabImagesDynamic(arrayListImages7,"Science Lab",arrayListImages7.size()));
//        jsonArrayLabPhoto.add(getLabImagesDynamic(arrayListImages1,"Physics Lab",arrayListImages7.size()+arrayListImages1.size()));
//        jsonArrayLabPhoto.add(getLabImagesDynamic(arrayListImages2,"Chemistry Lab",arrayListImages7.size()+arrayListImages1.size()+arrayListImages2.size()));
//        jsonArrayLabPhoto.add(getLabImagesDynamic(arrayListImages3,"Biology Lab",arrayListImages7.size()+arrayListImages1.size()+arrayListImages2.size()+arrayListImages3.size()));
//        jsonArrayLabPhoto.add(getLabImagesDynamic(arrayListImages4,"Home Science Lab",arrayListImages7.size()+arrayListImages1.size()+arrayListImages2.size()+arrayListImages3.size()+arrayListImages4.size()));
//        jsonArrayLabPhoto.add(getLabImagesDynamic(arrayListImages5,"Music Lab",arrayListImages7.size()+arrayListImages1.size()+arrayListImages2.size()+arrayListImages3.size()+arrayListImages4.size()+arrayListImages5.size()));
//        jsonArrayLabPhoto.add(getLabImagesDynamic(arrayListImages6,"Geography Lab",arrayListImages7.size()+arrayListImages1.size()+arrayListImages2.size()+arrayListImages3.size()+arrayListImages4.size()+arrayListImages5.size()+arrayListImages6.size()));
//        jsonObject.add("LabPhotos",(JsonElement)jsonArrayLabPhoto);

        return jsonObject.toString();
    }

    private JsonArray getLabImagesDynamic(ArrayList<Bitmap> arrayListImages7, String name_lab,int i1) {
        JsonArray jsonArray = new JsonArray();
        for (int i=0;i<arrayListImages7.size();i++){
            JsonObject jsonObject=new JsonObject();
            try {
                jsonObject.addProperty("id", String.valueOf(i1 + 1));
                jsonObject.addProperty("photos", "BitMapToString(getResizedBitmap(arrayListImages7.get(i), 300))"+i);
                jsonObject.addProperty("LabName", name_lab);

//            Log.d("TAG", "paraGetImageBase64: "+BitMapToString(bitmap));
            } catch (Exception e) {
                e.printStackTrace();
            }
           jsonArray.add(jsonObject);
        }

        return jsonArray;
    }

    private JsonObject getLabImage(Bitmap arrayListImages7, String name_lab,int i1) {


            JsonObject jsonObject=new JsonObject();
            try {
                jsonObject.addProperty("id", String.valueOf(i1 + 1));
                jsonObject.addProperty("photos", BitMapToString(getResizedBitmap(arrayListImages7, 300)));
                jsonObject.addProperty("LabName", name_lab);

//            Log.d("TAG", "paraGetImageBase64: "+BitMapToString(bitmap));
            } catch (Exception e) {
                e.printStackTrace();
            }



        return jsonObject;
    }
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public static String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
        // return "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";
    }


    private JsonObject paraGetImageBase64( Bitmap bitmap, int i) {
        JsonObject jsonObject = new JsonObject();

        try {
            jsonObject.addProperty("id", String.valueOf(i + 1));
            jsonObject.addProperty("photos", BitMapToString(getResizedBitmap(bitmap, 300)));
//            Log.d("TAG", "paraGetImageBase64: "+BitMapToString(bitmap));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    private JsonObject getLabConditionJson(LabCondition lab) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Srno",lab.getSrno());
        jsonObject.addProperty("LabName",lab.getLabName());
        jsonObject.addProperty("LabYN",lab.getLabYN());
        if (lab.getLabYN().toString().equals("No")){
            jsonObject.addProperty("EquipmentStatus","");
            jsonObject.addProperty("LabCondition","");
        }else {

                jsonObject.addProperty("EquipmentStatus",lab.getEquipmentStatus());


                jsonObject.addProperty("LabCondition",lab.getLabCondition());

        }


        return  jsonObject;
    }

    private void hideShowCards(CardView scienceLabBodyCard, CardView scienceLanImageCard, AdapterView<?> adapterView) {
        if (adapterView.getSelectedItem().toString().equals("No")){
            scienceLabBodyCard.setVisibility(View.GONE);
            scienceLanImageCard.setVisibility(View.GONE);
        }else if(adapterView.getSelectedItem().toString().equals("Yes")){
            scienceLabBodyCard.setVisibility(View.VISIBLE);
            scienceLanImageCard.setVisibility(View.VISIBLE);
        }
    }

    private void checkPermissions(int BtnType) {
        Dexter.withContext(UpdatedetailsTypeThree.this)
                .withPermissions(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if (multiplePermissionsReport.areAllPermissionsGranted()){
                            Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if (i.resolveActivity(getPackageManager())!=null){

                                try {

                                    imageFile =getImageFile(BtnType);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if (imageFile!=null){
//                                            File compressedImage = new Compressor.Builder(UpdateDetailsBioMetric.this)
//                                                    .setMaxWidth(720)
//                                                    .setMaxHeight(720)
//                                                    .setQuality(75)
//                                                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
//                                                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
//                                                            Environment.DIRECTORY_PICTURES).getAbsolutePath())
//                                                    .build()
//                                                    .compressToFile(imageFile);
                                    if (BtnType==1){
                                        arrayListImages1.add(imageFile);
                                    }else   if (BtnType==2){
                                        arrayListImages2.add(imageFile);
                                    }else   if (BtnType==3){
                                        arrayListImages3.add(imageFile);
                                    }else   if (BtnType==4){
                                        arrayListImages4.add(imageFile);
                                    }else   if (BtnType==5){
                                        arrayListImages5.add(imageFile);
                                    }else   if (BtnType==6){
                                        arrayListImages6.add(imageFile);
                                    }else   if (BtnType==7){
                                        arrayListImages7.add(imageFile);
                                    }

                                    Uri imageUri= FileProvider.getUriForFile(UpdatedetailsTypeThree.this, ConstantFile.PROVIDER_STRING,imageFile);
                                    i.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                                    startActivityForResult(i,2);
                                }
                            }

                        }else if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()){
                            AlertDialog.Builder builder = new AlertDialog.Builder(UpdatedetailsTypeThree.this);

                            // below line is the title
                            // for our alert dialog.
                            builder.setTitle("Need Permissions");

                            // below line is our message for our dialog
                            builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
                            builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // this method is called on click on positive
                                    // button and on clicking shit button we
                                    // are redirecting our user from our app to the
                                    // settings page of our app.
                                    dialog.cancel();
                                    // below is the intent from which we
                                    // are redirecting our user.
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                                    intent.setData(uri);
                                    startActivityForResult(intent, 101);
                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // this method is called when
                                    // user click on negative button.
                                    dialog.cancel();
                                }
                            });
                            // below line is used
                            // to display our dialog
                            builder.show();
                        }
                    }


                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

    }

    private File getImageFile(int btnType) throws IOException {
        String picName = "";
        if (btnType==7){
            picName="Sci_";
        }else   if (btnType==1){
            picName="Phy_";

        }else   if (btnType==2){
            picName="Che_";

        }else   if (btnType==3){
            picName="Bio_";

        }else   if (btnType==4){
            picName="Hom_   ";
        }else   if (btnType==5){
            picName="Mus_";
        }else   if (btnType==6){
            picName="Geo_";
        }

        String timeStamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String imageName=picName+timeStamp+"_";
        File storageDir=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile=File.createTempFile(imageName,".jpg",storageDir);

        currentImagePath=imageFile.getAbsolutePath();
        Log.d("TAG", "getImageFile: "+currentImagePath);
        return imageFile;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK ) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");


        }
    }
    @Override
    protected void onStart() {
        super.onStart();

        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        adapter3.notifyDataSetChanged();
        adapter4.notifyDataSetChanged();
        adapter5.notifyDataSetChanged();
        adapter6.notifyDataSetChanged();
        adapter7.notifyDataSetChanged();

    }

    @Override
    protected void onStop() {
        super.onStop();

        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        adapter3.notifyDataSetChanged();
        adapter4.notifyDataSetChanged();
        adapter5.notifyDataSetChanged();
        adapter6.notifyDataSetChanged();
        adapter7.notifyDataSetChanged();

    }
}