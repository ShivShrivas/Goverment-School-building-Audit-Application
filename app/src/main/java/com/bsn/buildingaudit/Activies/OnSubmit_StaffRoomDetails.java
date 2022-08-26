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
import androidx.constraintlayout.widget.ConstraintLayout;
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

public class OnSubmit_StaffRoomDetails extends AppCompatActivity {
    private TextView schoolAddress,schoolName,editStaffRoomDetails;
    ApplicationController applicationController;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();
    EditText edtAlmiraAndRacksAvailabiltyOnSubmit,edtFurnitureAvailabiltyOnSubmit,edtStaffRoomStatusOnSubmit,edtStaffRoomAvailabilityOnSubmit;
    RecyclerView recyclerViewStafroomtwoOnSubmit;
    ConstraintLayout constraintLayout22;
    String Type;
    LinearLayout linearLayout11;
    TextView mobnumberTxt,uploadedImagetxt;
    ImageView schoolIcon;
    Button staff_Room_Dios_ApproveBtn,staff_Room_Dios_rejectedBtn;
    String ParentID;
    Call<List<JsonObject>> call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_staff_room_details);
        Dialog dialog2 = new Dialog(this);

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        dialog2.show();
        Intent i=getIntent();
        Type=i.getStringExtra("Type");
        ParentID=i.getStringExtra("ParamId");
        edtAlmiraAndRacksAvailabiltyOnSubmit=findViewById(R.id.edtAlmiraAndRacksAvailabiltyOnSubmit);
        edtFurnitureAvailabiltyOnSubmit=findViewById(R.id.edtFurnitureAvailabiltyOnSubmit);
        edtStaffRoomStatusOnSubmit=findViewById(R.id.edtStaffRoomStatusOnSubmit);
        edtStaffRoomAvailabilityOnSubmit=findViewById(R.id.edtStaffRoomAvailabilityOnSubmit);
        constraintLayout22=findViewById(R.id.constraintLayout22);
        mobnumberTxt=findViewById(R.id.mobnumberTxt);
        linearLayout11=findViewById(R.id.linearLayout11);
        uploadedImagetxt=findViewById(R.id.uploadedImagetxt);
        recyclerViewStafroomtwoOnSubmit=findViewById(R.id.recyclerViewStafroomtwoOnSubmit);
        applicationController= (ApplicationController) getApplication();
        editStaffRoomDetails=findViewById(R.id.editStaffRoomDetails);
        staff_Room_Dios_rejectedBtn=findViewById(R.id.staff_Room_Dios_rejectedBtn);
        staff_Room_Dios_ApproveBtn=findViewById(R.id.staff_Room_Dios_ApproveBtn);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Type.equals("D")){
            toolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
            mobnumberTxt.setTextColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));

            uploadedImagetxt.setTextColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));

            linearLayout11.setVisibility(View.VISIBLE);
            editStaffRoomDetails.setVisibility(View.GONE);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        schoolName=findViewById(R.id.schoolName);
        schoolAddress=findViewById(R.id.schoolAddress);

        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        recyclerViewStafroomtwoOnSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        editStaffRoomDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_StaffRoomDetails.this,UpdateDetailsTypeTwo.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
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
                    Toast.makeText(OnSubmit_StaffRoomDetails.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                    arrayListRemarks=approveRejectRemarksDataModel.getData();


                }
            }

            @Override
            public void onFailure(Call<ApproveRejectRemarksDataModel> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });
        Log.d("TAG", "onCreate: "+paraGetDetails("2",applicationController.getSchoolId(), applicationController.getPeriodID()));
        if (applicationController.getUsertype().equals("VA")){
            call=apiService.checkStaffRoomDetails(paraGetDetails("2",applicationController.getSchoolId(), applicationController.getPeriodID()));
        }else{
            call=apiService.checkStaffRoomDetails(paraGetDetails("11",applicationController.getSchoolId(), applicationController.getPeriodID()));
        }
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                setAllEditTextDisabled();
                if (response.body().get(0).get("DataLocked").toString().equals("0")){
                    if (Type.equals("D")){
                        editStaffRoomDetails.setVisibility(View.GONE);

                    }else{

                        editStaffRoomDetails.setVisibility(View.VISIBLE);
                    }
                }
                if (response.body().get(0).get("SeperateRoomsAvl").getAsString().equals("No")){
                    constraintLayout22.setVisibility(View.GONE);
                    edtStaffRoomAvailabilityOnSubmit.setText(response.body().get(0).get("SeperateRoomsAvl").getAsString());
                    dialog2.dismiss();
                }else {
                    edtStaffRoomAvailabilityOnSubmit.setText(response.body().get(0).get("SeperateRoomsAvl").getAsString());
                    edtAlmiraAndRacksAvailabiltyOnSubmit.setText(response.body().get(0).get("AlmirahRacksAvl").getAsString());
                    edtFurnitureAvailabiltyOnSubmit.setText(response.body().get(0).get("FurnitureAvl").getAsString());
                    edtStaffRoomStatusOnSubmit.setText(response.body().get(0).get("Status").getAsString());
                    String[] StaffPhotoPathList=response.body().get(0).get("StaffPhotoPath").toString().split(",");
                    OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_StaffRoomDetails.this,StaffPhotoPathList, applicationController.getUsertype());
                    recyclerViewStafroomtwoOnSubmit.setAdapter(onlineImageRecViewAdapter);
                    dialog2.dismiss();
                }


            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();

            }
        });
            staff_Room_Dios_ApproveBtn.setOnClickListener(new View.OnClickListener() {
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
                            StaticFunctions.showDialogApprove(OnSubmit_StaffRoomDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks);

                        }

                        @Override
                        public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                        }
                    });
                }
            });
            staff_Room_Dios_rejectedBtn.setOnClickListener(new View.OnClickListener() {
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
                            StaticFunctions.showDialogReject(OnSubmit_StaffRoomDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks);

                        }

                        @Override
                        public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                        }
                    });
                }
            });
    }

    private void setAllEditTextDisabled() {
        edtStaffRoomStatusOnSubmit.setEnabled(false);
        edtAlmiraAndRacksAvailabiltyOnSubmit.setEnabled(false);
        edtFurnitureAvailabiltyOnSubmit.setEnabled(false);
        edtStaffRoomAvailabilityOnSubmit.setEnabled(false);
    }


    private JsonObject paraGetDetails(String action, String schoolId, String periodId) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",action);
        jsonObject.addProperty("ParamId","2");
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodId);
        return jsonObject;
    }

}