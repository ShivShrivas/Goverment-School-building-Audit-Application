package com.bsn.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.bsn.buildingaudit.Adapters.OnlineImageRecViewAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSubmitClassRoomPage extends AppCompatActivity {
private TextView schoolAddress,schoolName,editClassRoomDetails;
Dialog dialog;
ApplicationController applicationController;
EditText totalClassRooms,edtPodiumClassAfterSubmit,majorRepairingClassroom,
        minorRepairingClassroomAfterSubmit,greenBoardCountAfterSubmit,goodCondtionClassroomAfterSubmit,whiteBoardContAfterSubmit,blackBoardCountAfterSubmit;
RecyclerView recyclerViewTwoTypeOneAfterSubmit,recyclerViewFourTypeOneAfterSubmit,recyclerViewThreeTypeOneAfterSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_on_submit_class_room_page);
        applicationController= (ApplicationController) getApplication();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        dialog = new Dialog(this);
        dialog.setCancelable(false);

        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.respons_dialog);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        Dialog dialog2 = new Dialog(this);

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        dialog2.show();
        schoolName=findViewById(R.id.schoolName);
        editClassRoomDetails=findViewById(R.id.editClassRoomDetails);
        schoolAddress=findViewById(R.id.schoolAddress);
        totalClassRooms=findViewById(R.id.totalClassRoomsAfterSubmit);
        edtPodiumClassAfterSubmit=findViewById(R.id.edtPodiumClassAfterSubmit);
        majorRepairingClassroom=findViewById(R.id.majorRepairingClassroomAfterSubmit);
        minorRepairingClassroomAfterSubmit=findViewById(R.id.minorRepairingClassroomAfterSubmit);
        greenBoardCountAfterSubmit=findViewById(R.id.greenBoardCountAfterSubmit);
        whiteBoardContAfterSubmit=findViewById(R.id.whiteBoardContAfterSubmit);
        blackBoardCountAfterSubmit=findViewById(R.id.blackBoardCountAfterSubmit);
        goodCondtionClassroomAfterSubmit=findViewById(R.id.goodCondtionClassroomAfterSubmit);
        recyclerViewTwoTypeOneAfterSubmit=findViewById(R.id.recyclerViewTwoTypeOneAfterSubmit);
        recyclerViewThreeTypeOneAfterSubmit=findViewById(R.id.recyclerViewThreeTypeOneAfterSubmit);
        recyclerViewFourTypeOneAfterSubmit=findViewById(R.id.recyclerViewFourTypeOneAfterSubmit);
        recyclerViewTwoTypeOneAfterSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewThreeTypeOneAfterSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewFourTypeOneAfterSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        editClassRoomDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmitClassRoomPage.this,UpdateDetailTypeOne.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();

            }
        });

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkDetailsOfRooms(paraGetDetails("2",applicationController.getSchoolId(), applicationController.getPeriodID()));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                getAllUnEditable();
                Log.d("TAG", "onResponse: "+response.body()+"///////"+response.body().get(0).get("TotalRooms"));
                totalClassRooms.setText(response.body().get(0).get("TotalRooms").toString());
                minorRepairingClassroomAfterSubmit.setText(response.body().get(0).get("MinorRepairing").toString());
                if (response.body().get(0).get("MinorRepairing").toString().equals("0")){
                    recyclerViewThreeTypeOneAfterSubmit.setVisibility(View.GONE);
                }

                if (response.body().get(0).get("MajorRepairing").toString().equals("0")){
                    recyclerViewFourTypeOneAfterSubmit.setVisibility(View.GONE);
                }

                if (response.body().get(0).get("GoodCondition").toString().equals("0")){
                    recyclerViewTwoTypeOneAfterSubmit.setVisibility(View.GONE);
                }
                majorRepairingClassroom.setText(response.body().get(0).get("MajorRepairing").toString());
                minorRepairingClassroomAfterSubmit.setText(response.body().get(0).get("MinorRepairing").toString());
                edtPodiumClassAfterSubmit.setText(response.body().get(0).get("ClassRoomsWithPodium").toString());
                blackBoardCountAfterSubmit.setText(response.body().get(0).get("BlackBoard").toString());
                whiteBoardContAfterSubmit.setText(response.body().get(0).get("WhiteBoard").toString());
                greenBoardCountAfterSubmit.setText(response.body().get(0).get("GreenBoard").toString());
                goodCondtionClassroomAfterSubmit.setText(response.body().get(0).get("GoodCondition").toString());
                Log.d("TAG", "onResponse: "+response.body().get(0).get("GoodConditionPhotos").toString());
                Log.d("TAG", "onResponse: "+response.body().get(0).get("MajorRepairingPhotos").toString());
                Log.d("TAG", "onResponse: "+response.body().get(0).get("MinorRepairingPhotos").toString());
                String goodRoomsList=response.body().get(0).get("GoodConditionPhotos").toString();
                String[] goodConditionsList=goodRoomsList.split(",");
                String majorRepairing=response.body().get(0).get("MajorRepairingPhotos").toString();
                String[] majorRepairingList=majorRepairing.split(",");
                String minorRepairing=response.body().get(0).get("MinorRepairingPhotos").toString();
                String[] minorRepairingList=minorRepairing.split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmitClassRoomPage.this,goodConditionsList);
                recyclerViewTwoTypeOneAfterSubmit.setAdapter(onlineImageRecViewAdapter);


                OnlineImageRecViewAdapter onlineImageRecViewAdapter2=new OnlineImageRecViewAdapter(OnSubmitClassRoomPage.this,minorRepairingList);
                recyclerViewThreeTypeOneAfterSubmit.setAdapter(onlineImageRecViewAdapter2);


              OnlineImageRecViewAdapter onlineImageRecViewAdapter3=new OnlineImageRecViewAdapter(OnSubmitClassRoomPage.this,majorRepairingList);
                recyclerViewFourTypeOneAfterSubmit.setAdapter(onlineImageRecViewAdapter3);
                onlineImageRecViewAdapter.notifyDataSetChanged();

                dialog2.dismiss();





            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();

            }
        });





    }

    private void getAllUnEditable() {
        totalClassRooms.setEnabled(false);
                minorRepairingClassroomAfterSubmit.setEnabled(false);
        majorRepairingClassroom.setEnabled(false);
                minorRepairingClassroomAfterSubmit.setEnabled(false);
        edtPodiumClassAfterSubmit.setEnabled(false);
                blackBoardCountAfterSubmit.setEnabled(false);
        whiteBoardContAfterSubmit.setEnabled(false);
                greenBoardCountAfterSubmit.setEnabled(false);
        goodCondtionClassroomAfterSubmit.setEnabled(false);
    }

    private JsonObject paraGetDetails(String action, String schoolId, String periodId) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",action);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodId);
        return jsonObject;
    }

}