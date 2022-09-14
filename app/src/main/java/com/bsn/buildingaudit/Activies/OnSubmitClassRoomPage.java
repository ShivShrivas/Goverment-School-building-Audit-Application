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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.OnlineImageRecViewAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.ApproveRejectRemarksDataModel;
import com.bsn.buildingaudit.Model.Datum;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSubmitClassRoomPage extends AppCompatActivity {
private TextView schoolAddress,schoolName,editClassRoomDetails;
Dialog dialog;
ApplicationController applicationController;
TextView mobnumberTxt,numberOfRoomText,boardinClassRoom,availableInRooms,boardType;
EditText totalClassRooms,edtPodiumClassAfterSubmit,majorRepairingClassroom,
        minorRepairingClassroomAfterSubmit,greenBoardCountAfterSubmit,goodCondtionClassroomAfterSubmit,whiteBoardContAfterSubmit,blackBoardCountAfterSubmit;
RecyclerView recyclerViewTwoTypeOneAfterSubmit,recyclerViewFourTypeOneAfterSubmit,recyclerViewThreeTypeOneAfterSubmit;
String Type;
ArrayList<Datum> arrayListRemarks=new ArrayList<>();
ImageView schoolIcon;
    Call<List<JsonObject>> call;
    String ParentID,InspectionId;
    Boolean remarkAlreadyDoneFlag=false;

    Button classroomApproveBtn,classroomRejectBtn;
LinearLayout diosButtonLayout,linearLayout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_on_submit_class_room_page);
        applicationController= (ApplicationController) getApplication();
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        schoolIcon=findViewById(R.id.schoolIcon);
        mobnumberTxt=findViewById(R.id.mobnumberTxt);
        numberOfRoomText=findViewById(R.id.numberOfRoomText);
        boardinClassRoom=findViewById(R.id.boardinClassRoom);
        diosButtonLayout=findViewById(R.id.linearLayout11);
        availableInRooms=findViewById(R.id.availableInRooms);
        linearLayout2=findViewById(R.id.linearLayout2);
        classroomApproveBtn=findViewById(R.id.classroomApproveBtn);
        classroomRejectBtn=findViewById(R.id.classroomRejectBtn);
        editClassRoomDetails=findViewById(R.id.editClassRoomDetails);
        boardType=findViewById(R.id.boardType);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i=getIntent();
        Type=i.getStringExtra("Type");
        ParentID=i.getStringExtra("ParamId");
        InspectionId=i.getStringExtra("InspectionId");
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

                    Toast.makeText(OnSubmitClassRoomPage.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onResponse: "+approveRejectRemarksDataModel.getData());
                    arrayListRemarks=approveRejectRemarksDataModel.getData();
                    Dialog dialogForRemark=new Dialog(OnSubmitClassRoomPage.this);
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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        if (Type.equals("D")){
            toolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            schoolIcon.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.schoolicon_dios_panel));
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
            mobnumberTxt.setTextColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            numberOfRoomText.setBackgroundColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            linearLayout2.setBackgroundColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            boardType.setTextColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            availableInRooms.setTextColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            boardinClassRoom.setTextColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            diosButtonLayout.setVisibility(View.VISIBLE);
            editClassRoomDetails.setVisibility(View.GONE);
        }
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


        classroomApproveBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(OnSubmitClassRoomPage.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
        classroomRejectBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(OnSubmitClassRoomPage.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
        Log.d("TAG", "onCreate: "+paraGetDetails("12",applicationController.getSchoolId(), applicationController.getPeriodID())+applicationController.getUsertype());
        if (applicationController.getUsertype().equals("VA")){
            call=apiService.checkDetailsOfRooms(paraGetDetails("2",applicationController.getSchoolId(), applicationController.getPeriodID()));
        }else{
            call=apiService.checkDetailsOfRooms(paraGetDetails("12",applicationController.getSchoolId(), applicationController.getPeriodID()));
        }
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                getAllUnEditable();
                try {
                    if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                        if (Type.equals("D")){
                            editClassRoomDetails.setVisibility(View.GONE);
                        }else{
                            editClassRoomDetails.setVisibility(View.VISIBLE);
                        }

                    }
                }catch (Exception e){
                    editClassRoomDetails.setVisibility(View.GONE);

                }

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
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmitClassRoomPage.this,goodConditionsList,applicationController.getUsertype());
                recyclerViewTwoTypeOneAfterSubmit.setAdapter(onlineImageRecViewAdapter);


                OnlineImageRecViewAdapter onlineImageRecViewAdapter2=new OnlineImageRecViewAdapter(OnSubmitClassRoomPage.this,minorRepairingList, applicationController.getUsertype());
                recyclerViewThreeTypeOneAfterSubmit.setAdapter(onlineImageRecViewAdapter2);


              OnlineImageRecViewAdapter onlineImageRecViewAdapter3=new OnlineImageRecViewAdapter(OnSubmitClassRoomPage.this,majorRepairingList, applicationController.getUsertype());
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