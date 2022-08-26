package com.bsn.buildingaudit.DIOS.SchoolStaff;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.StaffSanctionAndWorkingAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.ApproveRejectRemarksDataModel;
import com.bsn.buildingaudit.Model.Datum;
import com.bsn.buildingaudit.Model.StaffSanctionAndWorkingModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Staff_Sanction_and_Working extends AppCompatActivity {
Button btnApprovalStaffSanction,btnRejectStaffSanction;
RecyclerView staffSanctionRecview;
    ApplicationController applicationController;
    Intent i;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();

    String ParentID;
    List<StaffSanctionAndWorkingModel> arrayList=new ArrayList<>();
    StaffSanctionAndWorkingAdapter staffSanctionAndWorkingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_sanction_and_working);
        Window window = getWindow();
        applicationController= (ApplicationController) getApplication();

        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        staffSanctionRecview=findViewById(R.id.staffSanctionRecview);
        staffSanctionRecview.setLayoutManager(new LinearLayoutManager(this));
        i=getIntent();
        ParentID=i.getStringExtra("ParamId");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnRejectStaffSanction=findViewById(R.id.btnRejectStaffSanction);
        btnApprovalStaffSanction=findViewById(R.id.btnApprovalStaffSanction);

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
                    Toast.makeText(Staff_Sanction_and_Working.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                    arrayListRemarks=approveRejectRemarksDataModel.getData();


                }
            }

            @Override
            public void onFailure(Call<ApproveRejectRemarksDataModel> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });
        Call<List<StaffSanctionAndWorkingModel>> call=apiService.getSanctionAndWorking(paraStaffSanctionJson(applicationController.getSchoolId(),applicationController.getPeriodID()));
        call.enqueue(new Callback<List<StaffSanctionAndWorkingModel>>() {
            @Override
            public void onResponse(Call<List<StaffSanctionAndWorkingModel>> call, Response<List<StaffSanctionAndWorkingModel>> response) {
                arrayList=response.body();
                staffSanctionAndWorkingAdapter=new StaffSanctionAndWorkingAdapter(Staff_Sanction_and_Working.this,arrayList);
                staffSanctionRecview.setAdapter(staffSanctionAndWorkingAdapter);
                staffSanctionAndWorkingAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<StaffSanctionAndWorkingModel>> call, Throwable t) {

            }
        });

        ArrayList<String> myImageNameList = new ArrayList<>();
        myImageNameList.add("All Given Information is correct");


        ArrayList<String> myImageNameList2 = new ArrayList<>();
        myImageNameList2.add("Sanctioned Data is not correct");
        myImageNameList2.add("Posted Data is not correct");
        myImageNameList2.add("Working Data is not correct");
        myImageNameList2.add("No. of attached staff Data is not correct");


        btnApprovalStaffSanction.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(Staff_Sanction_and_Working.this,arrayList, applicationController.getPeriodID(), applicationController.getSchoolId(),ParentID, arrayListRemarks);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });            }
        });

        btnRejectStaffSanction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InsType","A");
                jsonObject1.addProperty("ParamId",ParentID);
                Call<ArrayList<ApproveRejectRemarkModel>> call1=apiService.getApproveRejectRemark(jsonObject1);
                call1.enqueue(new Callback<ArrayList<ApproveRejectRemarkModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ApproveRejectRemarkModel>> call, Response<ArrayList<ApproveRejectRemarkModel>> response) {
                        ArrayList<ApproveRejectRemarkModel> arrayList=response.body();
                        StaticFunctions.showDialogReject(Staff_Sanction_and_Working.this,arrayList, applicationController.getPeriodID(), applicationController.getSchoolId(),ParentID, arrayListRemarks);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });            }
        });
    }

    private JsonObject paraStaffSanctionJson(String schoolId, String periodID) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID",schoolId);
        jsonObject.addProperty("PeriodID",periodID);

        return jsonObject;
    }
}