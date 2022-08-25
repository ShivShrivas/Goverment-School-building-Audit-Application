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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.OnlineImageRecViewAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSubmit_GirlsToiletDetails extends AppCompatActivity {
    ApplicationController applicationController;
    LinearLayout linearLayoutCWSNfriendlyToilet;
    TextView userName,schoolAddress,schoolName,editGirlsToiletDetails;
    LinearLayout linearLayout21;
    RecyclerView recyclerViewBoysToiletOnSub;
    String Type;
    String ParentID;
    Button girlsToiletApproveBtn,girlsToiletRejectBtn;
    Call<List<JsonObject>> call;
    EditText edtUrinalWithFlushTotalB,edtUrinalWithoutFlushB,edtUrinalWithFlushB,edtCSWNfriendlyTotalB,edtCSWNwithoutfriendlyB,edtCSWNfriendlyB,
            edtwithflushTotal,edtWithoutFlushClean,edtWithFlushClean,edtBoysDustbin,edtBoysDoors,edtCWSNBoysAvailability,edtsgirlsIncinator,edtGirlsSanetoryNapkin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_girls_toilet_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Dialog dialog2 = new Dialog(this);
        Intent i=getIntent();
        Type=i.getStringExtra("Type");
        ParentID=i.getStringExtra("ParamId");

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        dialog2.show();
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        edtUrinalWithFlushTotalB=findViewById(R.id.edtUrinalWithFlushTotalB);
        edtUrinalWithoutFlushB=findViewById(R.id.edtUrinalWithoutFlushB);
        edtUrinalWithFlushB=findViewById(R.id.edtUrinalWithFlushB);
        edtCSWNfriendlyTotalB=findViewById(R.id.edtCSWNfriendlyTotalB);
        edtCSWNwithoutfriendlyB=findViewById(R.id.edtCSWNwithoutfriendlyB);
        linearLayout21=findViewById(R.id.linearLayout21);
        edtCSWNfriendlyB=findViewById(R.id.edtCSWNfriendlyB);
        girlsToiletApproveBtn=findViewById(R.id.girlsToiletApproveBtn);
        girlsToiletRejectBtn=findViewById(R.id.girlsToiletRejectBtn);
        edtwithflushTotal=findViewById(R.id.edtwithflushTotal);
        edtWithoutFlushClean=findViewById(R.id.edtWithoutFlushClean);
        edtWithFlushClean=findViewById(R.id.edtWithFlushClean);
        edtBoysDustbin=findViewById(R.id.edtBoysDustbin);
        edtBoysDoors=findViewById(R.id.edtBoysDoors);
        edtCWSNBoysAvailability=findViewById(R.id.edtCWSNBoysAvailability);
        recyclerViewBoysToiletOnSub=findViewById(R.id.recyclerViewBoysToiletOnSub);
        linearLayoutCWSNfriendlyToilet=findViewById(R.id.linearLayoutCWSNfriendlyToilet);
        edtGirlsSanetoryNapkin=findViewById(R.id.edtGirlsSanetoryNapkin);
        edtsgirlsIncinator=findViewById(R.id.edtsgirlsIncinator);
        editGirlsToiletDetails=findViewById(R.id.editGirlsToiletDetails);
        if (Type.equals("D")){
            linearLayout21.setVisibility(View.VISIBLE);
        }
        editGirlsToiletDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_GirlsToiletDetails.this,UpdateDetailsGirlsToilet.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
        disableEditbox();
        recyclerViewBoysToiletOnSub.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        girlsToiletApproveBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(OnSubmit_GirlsToiletDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

        girlsToiletRejectBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(OnSubmit_GirlsToiletDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
        if (applicationController.getUsertype().equals("VA")){
            call=apiService.checkGirlsToilet(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"17"));
        }else{
            call=apiService.checkGirlsToilet(paraGetDetails2("11",applicationController.getSchoolId(), applicationController.getPeriodID(),"17"));
        }
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    if (Type.equals("D")){
                        editGirlsToiletDetails.setVisibility(View.GONE);

                    }else{
                        editGirlsToiletDetails.setVisibility(View.VISIBLE);

                    }
                }
                edtUrinalWithFlushTotalB.setText(response.body().get(0).get("TotalUrinals").getAsString());
                edtUrinalWithoutFlushB.setText(response.body().get(0).get("NoOfUrinalsWithOutFlush").getAsString());
                edtUrinalWithFlushB.setText(response.body().get(0).get("NoOfUrinalsWithFlush").getAsString());

                edtwithflushTotal.setText(response.body().get(0).get("TotalExcludingCWSN").getAsString());
                edtWithoutFlushClean.setText(response.body().get(0).get("NoOfSeatsExcludingCWSNWithOutFlush").getAsString());
                edtWithFlushClean.setText(response.body().get(0).get("NoOfSeatsExcludingCWSNWithFlush").getAsString());
                edtBoysDustbin.setText(response.body().get(0).get("Dustbin").getAsString());
                edtBoysDoors.setText(response.body().get(0).get("Door").getAsString());
                if (response.body().get(0).get("AvailabilityCWSN").getAsString().equals("No")){

                    edtCWSNBoysAvailability.setText(response.body().get(0).get("AvailabilityCWSN").getAsString());
                    linearLayoutCWSNfriendlyToilet.setVisibility(View.GONE);

                }else {
                    edtCWSNBoysAvailability.setText(response.body().get(0).get("AvailabilityCWSN").getAsString());
                    edtCSWNfriendlyTotalB.setText(response.body().get(0).get("TotalCWSN").getAsString());
                    edtCSWNwithoutfriendlyB.setText(response.body().get(0).get("NoOfSeatsCWSNWithOutFlush").getAsString());
                    edtCSWNfriendlyB.setText(response.body().get(0).get("NoOfSeatsCWSNWithFlush").getAsString());
                }
                edtsgirlsIncinator.setText(response.body().get(0).get("Incinerator").getAsString());
                edtGirlsSanetoryNapkin.setText(response.body().get(0).get("FreeSanitaryNapkins").getAsString());
                String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_GirlsToiletDetails.this,StaffPhotoPathList, applicationController.getUsertype());
                recyclerViewBoysToiletOnSub.setAdapter(onlineImageRecViewAdapter);
                dialog2.dismiss();

            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
dialog2.dismiss();
            }
        });


    }

    private void disableEditbox() {
        edtUrinalWithFlushTotalB.setEnabled(false);
        edtUrinalWithoutFlushB.setEnabled(false);
        edtUrinalWithFlushB.setEnabled(false);
        edtCSWNfriendlyTotalB.setEnabled(false);
        edtCSWNwithoutfriendlyB.setEnabled(false);
        edtCSWNfriendlyB.setEnabled(false);
        edtwithflushTotal.setEnabled(false);
        edtWithoutFlushClean.setEnabled(false);
        edtWithFlushClean.setEnabled(false);
        edtBoysDustbin.setEnabled(false);
        edtBoysDoors.setEnabled(false);
        edtCWSNBoysAvailability.setEnabled(false);
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