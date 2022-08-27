package com.bsn.buildingaudit.Activies;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

public class OnSubmit_FurnitureDetails extends AppCompatActivity {
    ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName;
    LinearLayout linearLayout21;
    EditText edtMajorConditionForSingle,edtMajorConditionForDouble,edtMajorConditionForTripple,edtMinorConditionForSingle,edtMinorConditionForDouble,edtMinorConditionForTripple,edtgoodConditionForSingle,
            edtgoodConditionForDouble,edtgoodConditionForTripple;
    EditText edtFurnitureRequired,edtTripleSeatesStatus,edtTrippelSeated,edtDoubleSeatesStatus,edtDoubleSeated
            ,edtSingleSeated,edtsingleSeatesStatus;
    LinearLayout constraintLayout9,constraintLayout59,constraintLayout49;
    TextView edtTotalFurnirtureStrenght,editFurnituresDetails;
    RecyclerView recyclerViewFurnituresOnSub;
    Call<List<JsonObject>> call;
    String Type;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();
    Boolean remarkAlreadyDoneFlag=false;

    String ParentID;
    Button furnitureApprovedBtn,furnitureRejectedBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_furniture_details);


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
        Intent i=getIntent();
        Type=i.getStringExtra("Type");
        ParentID=i.getStringExtra("ParamId");

        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());

        edtFurnitureRequired=findViewById(R.id.edtFurnitureRequired);
        edtTotalFurnirtureStrenght=findViewById(R.id.edtTotalFurnirtureStrenght);
        linearLayout21=findViewById(R.id.linearLayout21);
        edtTripleSeatesStatus=findViewById(R.id.edtTripleSeatesStatus);
        edtTrippelSeated=findViewById(R.id.edtTrippelSeated);
        edtDoubleSeated=findViewById(R.id.edtDoubleSeated);
        edtDoubleSeatesStatus=findViewById(R.id.edtDoubleSeatesStatus);
        edtSingleSeated=findViewById(R.id.edtSingleSeated);
        edtsingleSeatesStatus=findViewById(R.id.edtsingleSeatesStatus);
        recyclerViewFurnituresOnSub=findViewById(R.id.recyclerViewFurnituresOnSub);
        constraintLayout49=findViewById(R.id.constraintLayout49);
        constraintLayout59=findViewById(R.id.constraintLayout59);
        constraintLayout9=findViewById(R.id.constraintLayout9);
        editFurnituresDetails=findViewById(R.id.editFurnituresDetails);
        edtMajorConditionForSingle=findViewById(R.id.edtMajorConditionForSingle);
        edtMajorConditionForDouble=findViewById(R.id.edtMajorConditionForDoubel);
        edtMajorConditionForTripple=findViewById(R.id.edtMajorConditionForTripple);
        edtMinorConditionForDouble=findViewById(R.id.edtMinorConditionForDoubel);
        edtMinorConditionForSingle=findViewById(R.id.edtMinorConditionForSingle);
        edtMinorConditionForTripple=findViewById(R.id.edtMinorConditionForTripple);
        edtgoodConditionForSingle=findViewById(R.id.edtgoodConditionForSingle);
        edtgoodConditionForTripple=findViewById(R.id.edtgoodConditionForTripple);
        edtgoodConditionForDouble=findViewById(R.id.edtgoodConditionForDoubel);
        furnitureApprovedBtn=findViewById(R.id.furnitureApprovedBtn);
        furnitureRejectedBtn=findViewById(R.id.furnitureRejectedBtn);
        if (Type.equals("D")){
            linearLayout21.setVisibility(View.VISIBLE);
        }
        disableEditText();
        editFurnituresDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_FurnitureDetails.this,UpdateDetailsFurnitures.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }

        });

        recyclerViewFurnituresOnSub.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
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

                    Toast.makeText(OnSubmit_FurnitureDetails.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onResponse: "+approveRejectRemarksDataModel.getData());
                    arrayListRemarks=approveRejectRemarksDataModel.getData();
                    Dialog dialogForRemark=new Dialog(OnSubmit_FurnitureDetails.this);
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
        furnitureRejectedBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(OnSubmit_FurnitureDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
       furnitureApprovedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: "+ParentID);
                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InsType","A");
                jsonObject1.addProperty("ParamId",ParentID);
                Log.d("TAG", "onClick: "+jsonObject1);
                Call<ArrayList<ApproveRejectRemarkModel>> call1=apiService.getApproveRejectRemark(jsonObject1);
                call1.enqueue(new Callback<ArrayList<ApproveRejectRemarkModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ApproveRejectRemarkModel>> call, Response<ArrayList<ApproveRejectRemarkModel>> response) {
                        ArrayList<ApproveRejectRemarkModel> arrayList=response.body();
                        StaticFunctions.showDialogApprove(OnSubmit_FurnitureDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
        if (applicationController.getUsertype().equals("VA")){
            call=apiService.checkFurniture(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"18"));
        }else{
            call=apiService.checkFurniture(paraGetDetails2("11",applicationController.getSchoolId(), applicationController.getPeriodID(),"18"));
        }
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    if (Type.equals("D")){
                        editFurnituresDetails.setVisibility(View.GONE);

                    }else{
                        editFurnituresDetails.setVisibility(View.VISIBLE);

                    }
                }
                edtFurnitureRequired.setText(response.body().get(0).get("AdditionalFurniture")==null?"0":response.body().get(0).get("AdditionalFurniture").getAsString());
                edtTotalFurnirtureStrenght.setText(response.body().get(0).get("TotalStrength")==null?"0":response.body().get(0).get("TotalStrength").getAsString());
                edtgoodConditionForSingle.setText(response.body().get(0).get("GoodCount")==null?"0":response.body().get(0).get("GoodCount").getAsString());
                edtMinorConditionForSingle.setText(response.body().get(0).get("MinorCount")==null?"0":response.body().get(0).get("MinorCount").getAsString());
                edtMajorConditionForSingle.setText(response.body().get(0).get("MajorCount")==null?"0":response.body().get(0).get("MajorCount").getAsString());
                edtgoodConditionForDouble.setText(response.body().get(1).get("GoodCount")==null?"0":response.body().get(1).get("GoodCount").getAsString());
                edtMinorConditionForDouble.setText(response.body().get(1).get("MinorCount")==null?"0":response.body().get(1).get("MinorCount").getAsString());
                edtMajorConditionForDouble.setText(response.body().get(1).get("MajorCount")==null?"0":response.body().get(1).get("MajorCount").getAsString());
                edtgoodConditionForTripple.setText(response.body().get(2).get("GoodCount")==null?"0":response.body().get(2).get("GoodCount").getAsString());
                edtMinorConditionForTripple.setText(response.body().get(2).get("MinorCount")==null?"0":response.body().get(2).get("MinorCount").getAsString());
                edtMajorConditionForTripple.setText(response.body().get(2).get("MajorCount")==null?"0":response.body().get(2).get("MajorCount").getAsString());


                    if (response.body().get(0).get("TotalCnt").getAsString().equals("0")){
                        constraintLayout9.setVisibility(View.GONE);
                        edtSingleSeated.setText(response.body().get(0).get("TotalCnt")==null?"0":response.body().get(0).get("TotalCnt").getAsString());


                    }else {
                        edtsingleSeatesStatus.setText(response.body().get(0).get("Condition")==null?"0":response.body().get(0).get("Condition").getAsString());
                        edtSingleSeated.setText(response.body().get(0).get("TotalCnt")==null?"0":response.body().get(0).get("TotalCnt").getAsString());


                    }
  if (response.body().get(1).get("TotalCnt").getAsString().equals("0")){
                        constraintLayout59.setVisibility(View.GONE);
                        edtDoubleSeated.setText(response.body().get(1).get("TotalCnt")==null?"0":response.body().get(1).get("TotalCnt").getAsString());

                    }else {
                        edtDoubleSeatesStatus.setText(response.body().get(1).get("Condition").getAsString()==null?"0":response.body().get(1).get("Condition").getAsString());
                        edtDoubleSeated.setText(response.body().get(1).get("TotalCnt").getAsString()==null?"0":response.body().get(1).get("TotalCnt").getAsString());

                    }
  if (response.body().get(2).get("TotalCnt").getAsString().equals("0")){
                        constraintLayout49.setVisibility(View.GONE);
                        edtTrippelSeated.setText(response.body().get(2).get("TotalCnt")==null?"0":response.body().get(2).get("TotalCnt").getAsString());

                    }else {
                        edtTripleSeatesStatus.setText(response.body().get(2).get("Condition")==null?"0":response.body().get(2).get("Condition").getAsString());
                        edtTrippelSeated.setText(response.body().get(2).get("TotalCnt")==null?"0":response.body().get(2).get("TotalCnt").getAsString());

                    }




                String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_FurnitureDetails.this,StaffPhotoPathList, applicationController.getUsertype());
                recyclerViewFurnituresOnSub.setAdapter(onlineImageRecViewAdapter);

                dialog2.dismiss();

            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();

            }
        });

    }

    private void disableEditText() {
        edtFurnitureRequired.setEnabled(false);
                edtTotalFurnirtureStrenght.setEnabled(false);
        edtTripleSeatesStatus.setEnabled(false);
                edtTrippelSeated.setEnabled(false);
        edtDoubleSeated.setEnabled(false);
                edtDoubleSeatesStatus.setEnabled(false);
        edtSingleSeated.setEnabled(false);
                edtsingleSeatesStatus.setEnabled(false);
        edtMajorConditionForSingle.setEnabled(false);
                edtMajorConditionForDouble.setEnabled(false);
        edtMajorConditionForTripple.setEnabled(false);
                edtMinorConditionForDouble.setEnabled(false);
        edtMinorConditionForSingle.setEnabled(false);
                edtMinorConditionForTripple.setEnabled(false);
        edtgoodConditionForSingle.setEnabled(false);
                edtgoodConditionForTripple.setEnabled(false);
        edtgoodConditionForDouble.setEnabled(false);
    }
    private JsonObject paraGetDetails2(String action, String schoolId, String periodId, String paramId) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",action);
        jsonObject.addProperty("ParamId",paramId);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodId);
        return jsonObject;
    }
}