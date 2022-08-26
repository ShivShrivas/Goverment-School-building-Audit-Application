package com.bsn.buildingaudit.DIOS.co_curricular_activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.AdapterGameDetails;
import com.bsn.buildingaudit.Adapters.GamesAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.ApproveRejectRemarksDataModel;
import com.bsn.buildingaudit.Model.Datum;
import com.bsn.buildingaudit.Model.GameDatum;
import com.bsn.buildingaudit.Model.GameDetailsModel;
import com.bsn.buildingaudit.Model.GameName;
import com.bsn.buildingaudit.Model.GameStudentName;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Game_Details extends AppCompatActivity {
    RecyclerView recViewIndoorGames, recViewOutDoorGames;
    TextView GAMEFACILITYSTATUS,GRANTRECIPTAMT,GRANTEXPENAMT,SCHEMENAME,SPORTTEACHERSTATUS,
            FITINDIASTATUS,FITINDIASTDCOUNT,KHELOINDIASTATUS,KHELOINDIASTDCOUNT;
    GamesAdapter adapter, adapter2;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();

    TextView countD,countS,countN;
    Dialog dialog;
    Intent i;
    String ParentID;
    Button game_details_Approve_Btn,game_details_rejectBtn;
    ArrayList<GameStudentName> studentListDistricLevel = new ArrayList<>();
    ArrayList<GameStudentName> studentListStateLevel = new ArrayList<>();
    ArrayList<GameStudentName> studentListNatioanalLevel = new ArrayList<>();
    ApplicationController applicationController;
    ImageView showDetailsS,showDetailsD,showDetailsN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);
        Window window = getWindow();
        applicationController= (ApplicationController) getApplication();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        i=getIntent();
        ParentID=i.getStringExtra("ParamId");

        countD=findViewById(R.id.countD);
        countS=findViewById(R.id.countS);
        countN=findViewById(R.id.countN);
        GAMEFACILITYSTATUS=findViewById(R.id.GAMEFACILITYSTATUS);
        GRANTRECIPTAMT=findViewById(R.id.GRANTRECIPTAMT);
        GRANTEXPENAMT=findViewById(R.id.GRANTEXPENAMT);
        SCHEMENAME=findViewById(R.id.SCHEMENAME);
        SPORTTEACHERSTATUS=findViewById(R.id.SPORTTEACHERSTATUS);
        game_details_rejectBtn=findViewById(R.id.game_details_rejectBtn);
        game_details_Approve_Btn=findViewById(R.id.game_details_Approve_Btn);
        FITINDIASTATUS=findViewById(R.id.FITINDIASTATUS);
        FITINDIASTDCOUNT=findViewById(R.id.FITINDIASTDCOUNT);
        KHELOINDIASTATUS=findViewById(R.id.KHELOINDIASTATUS);
        KHELOINDIASTDCOUNT=findViewById(R.id.KHELOINDIASTDCOUNT);
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
        recViewOutDoorGames = findViewById(R.id.recViewOutDoorGames);
        showDetailsD = findViewById(R.id.showDetailsD);
        showDetailsN = findViewById(R.id.showDetailsN);
        showDetailsS = findViewById(R.id.showDetailsS);
        recViewIndoorGames = findViewById(R.id.recViewIndoorGames);
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        JsonObject json =new JsonObject();
        json.addProperty("SchoolID",applicationController.getSchoolId());
        json.addProperty("PeriodID",applicationController.getPeriodID());
        json.addProperty("ParamId",ParentID);
        Call<ApproveRejectRemarksDataModel> callz=apiService.getpriviousSubmittedDataByDIOS(json);
        callz.enqueue(new Callback<ApproveRejectRemarksDataModel>() {
            @Override
            public void onResponse(Call<ApproveRejectRemarksDataModel> call, Response<ApproveRejectRemarksDataModel> response) {
                Log.d("TAG", "onResponse: "+response.body());
                ApproveRejectRemarksDataModel approveRejectRemarksDataModel=response.body();
                Log.d("TAG", "onResponse: "+approveRejectRemarksDataModel.getStatus());
                if (!approveRejectRemarksDataModel.getStatus().equals("No Record Found")){
                    Toast.makeText(Game_Details.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                    arrayListRemarks=approveRejectRemarksDataModel.getData();


                }
            }

            @Override
            public void onFailure(Call<ApproveRejectRemarksDataModel> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });
        Log.d("TAG", "onCreate: "+jsonObject);
        Call<GameDetailsModel> call=apiService.getGameDetails(jsonObject);
        call.enqueue(new Callback<GameDetailsModel>() {
            @Override
            public void onResponse(Call<GameDetailsModel> call, Response<GameDetailsModel> response) {
                GameDetailsModel gameDetailsModel=response.body();
                List<GameDatum> arrayListgameData=gameDetailsModel.getGameData();
                List<GameName> arrayListGames=gameDetailsModel.getGameName();
                List<GameStudentName> arrayListGameStudentName=gameDetailsModel.getGameStudentName();
                ArrayList<String> indoorGames = new ArrayList<>();
                ArrayList<String> outDoorGames = new ArrayList<>();
                //GAMEFACILITYSTATUS,GRANTRECIPTAMT,GRANTEXPENAMT,SCHEMENAME,SPORTTEACHERSTATUS,
                //            FITINDIASTATUS,FITINDIASTDCOUNT,KHELOINDIASTATUS,KHELOINDIASTDCOUNT;
                GAMEFACILITYSTATUS.setText(arrayListgameData.get(0).getGamefacilitystatus().toString());
                GRANTRECIPTAMT.setText(arrayListgameData.get(0).getGrantreciptamt().toString());
                GRANTEXPENAMT.setText(arrayListgameData.get(0).getGrantexpenamt().toString());
                SCHEMENAME.setText(arrayListgameData.get(0).getSchemename().toString());
                SPORTTEACHERSTATUS.setText(arrayListgameData.get(0).getTeachernomistatus().toString());
                FITINDIASTATUS.setText(arrayListgameData.get(0).getFitindiastatus().toString());
                FITINDIASTDCOUNT.setText(arrayListgameData.get(0).getFitindiastdcount().toString());
                KHELOINDIASTATUS.setText(arrayListgameData.get(0).getKheloindiastatus().toString());
                KHELOINDIASTDCOUNT.setText(arrayListgameData.get(0).getKheloindiastdcount().toString());
                for (int i=0;i<arrayListGames.size();i++){
                   if (arrayListGames.get(i).getGametype().equals("I")){
                       indoorGames.add(arrayListGames.get(i).getGamename());
                   }else{
                       outDoorGames.add(arrayListGames.get(i).getGamename());

                   }
               }

                for (int j=0;j<arrayListGameStudentName.size();j++){
                    if (arrayListGameStudentName.get(j).getPartlevel().equals("S")){
                        studentListStateLevel.add(arrayListGameStudentName.get(j));
                    }else if(arrayListGameStudentName.get(j).getPartlevel().equals("D")){
                        studentListDistricLevel.add(arrayListGameStudentName.get(j));

                    }else{
                        studentListNatioanalLevel.add(arrayListGameStudentName.get(j));

                    }
                }
                countN.setText(studentListNatioanalLevel.size()+"");
                countS.setText(studentListStateLevel.size()+"");
                countD.setText(studentListDistricLevel.size()+"");
                if (studentListNatioanalLevel.size()<1){
                    showDetailsN.setVisibility(View.INVISIBLE);
                }else{
                    showDetailsN.setVisibility(View.VISIBLE);

                }

                if (studentListDistricLevel.size()<1){
                    showDetailsD.setVisibility(View.INVISIBLE);
                }else{
                    showDetailsD.setVisibility(View.VISIBLE);

                }

                if (studentListStateLevel.size()<1){
                    showDetailsS.setVisibility(View.INVISIBLE);
                }else{
                    showDetailsS.setVisibility(View.VISIBLE);

                }


                recViewOutDoorGames.setLayoutManager(new LinearLayoutManager(Game_Details.this, RecyclerView.HORIZONTAL, false));
                recViewIndoorGames.setLayoutManager(new LinearLayoutManager(Game_Details.this, RecyclerView.HORIZONTAL, false));
                adapter = new GamesAdapter(Game_Details.this, indoorGames);
                adapter2 = new GamesAdapter(Game_Details.this, outDoorGames);
                recViewIndoorGames.setAdapter(adapter);
                recViewOutDoorGames.setAdapter(adapter2);
            }

            @Override
            public void onFailure(Call<GameDetailsModel> call, Throwable t) {

            }
        });





        showDetailsD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(Game_Details.this,studentListDistricLevel);
            }
        });

        showDetailsN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(Game_Details.this,studentListNatioanalLevel);
            }
        });

        showDetailsS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(Game_Details.this,studentListStateLevel);
            }
        });
    }

    public void showDialog(Activity activity, ArrayList<GameStudentName> studentList) {

        dialog = new Dialog(activity);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.student_list_dialog);

        Button btndialog = (Button) dialog.findViewById(R.id.btndialog);
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
        AdapterGameDetails adapterRe = new AdapterGameDetails(Game_Details.this, studentList);
        recyclerView.setAdapter(adapterRe);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        dialog.show();

        game_details_Approve_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: "+ParentID);
                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InsType","A");
                jsonObject1.addProperty("ParamId",ParentID);
                RestClient restClient=new RestClient();
                ApiService apiService=restClient.getApiService();
                Call<ArrayList<ApproveRejectRemarkModel>> call1=apiService.getApproveRejectRemark(jsonObject1);
                call1.enqueue(new Callback<ArrayList<ApproveRejectRemarkModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ApproveRejectRemarkModel>> call, Response<ArrayList<ApproveRejectRemarkModel>> response) {
                        ArrayList<ApproveRejectRemarkModel> arrayList=response.body();
                        StaticFunctions.showDialogApprove(Game_Details.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

        game_details_rejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: "+ParentID);
                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InsType","R");
                jsonObject1.addProperty("ParamId",ParentID);
                RestClient restClient=new RestClient();
                ApiService apiService=restClient.getApiService();
                Call<ArrayList<ApproveRejectRemarkModel>> call1=apiService.getApproveRejectRemark(jsonObject1);
                call1.enqueue(new Callback<ArrayList<ApproveRejectRemarkModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ApproveRejectRemarkModel>> call, Response<ArrayList<ApproveRejectRemarkModel>> response) {
                        ArrayList<ApproveRejectRemarkModel> arrayList=response.body();
                        StaticFunctions.showDialogReject(Game_Details.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
    }
}