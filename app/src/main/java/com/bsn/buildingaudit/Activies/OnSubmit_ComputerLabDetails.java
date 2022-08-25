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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.OnlineImageRecViewAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.ApproveRejectRemarksDataModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSubmit_ComputerLabDetails extends AppCompatActivity {
    ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName,editComputerLabDetails;
RecyclerView recyclerViewComputerLabOnSubmit;
ConstraintLayout layOutComputerLab;
LinearLayout linearLayout51;
Button computerLabApproveBtn,computerLabRejectBtn;
    String ParentID;
    ArrayList<String> arrayListRemarks=new ArrayList<>();

    Call<List<JsonObject>> call;
String Type;
        EditText edtNoOfLab,edtScannerAvailable,edtPrinterAvailable,edtComputerOperator
                ,edtPowerBackup,edtFurniture,edtinternet,edtGrantUnderScheme,edtNoOfWorkingComputer,edtNoOfComputer
                ,edtInstallationYear,edtComputeLabAvailabelty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_computer_lab_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Intent i=getIntent();
        Type=i.getStringExtra("Type");
        ParentID=i.getStringExtra("ParamId");

        Dialog dialog2 = new Dialog(this);

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        dialog2.show();
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        computerLabApproveBtn=findViewById(R.id.computerLabApproveBtn);
        computerLabRejectBtn=findViewById(R.id.computerLabRejectBtn);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());

        edtNoOfLab=findViewById(R.id.edtNoOfLab);
        linearLayout51=findViewById(R.id.linearLayout51);
        edtScannerAvailable=findViewById(R.id.edtScannerAvailable);
        edtPrinterAvailable=findViewById(R.id.edtPrinterAvailable);
        edtComputerOperator=findViewById(R.id.edtComputerOperator);
        edtPowerBackup=findViewById(R.id.edtPowerBackup);
        edtFurniture=findViewById(R.id.edtFurniture);
        layOutComputerLab=findViewById(R.id.layOutComputerLab);
        edtinternet=findViewById(R.id.edtinternet);
        edtGrantUnderScheme=findViewById(R.id.edtGrantUnderScheme);
        edtNoOfWorkingComputer=findViewById(R.id.edtNoOfWorkingComputer);
        edtNoOfComputer=findViewById(R.id.edtNoOfComputer);
        edtInstallationYear=findViewById(R.id.edtInstallationYear);
        edtComputeLabAvailabelty=findViewById(R.id.edtComputeLabAvailabelty);
        recyclerViewComputerLabOnSubmit=findViewById(R.id.recyclerViewComputerLabOnSubmit);
        editComputerLabDetails=findViewById(R.id.editComputerLabDetails);
        if (Type.equals("D")){
            linearLayout51.setVisibility(View.VISIBLE);
        }
        editComputerLabDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_ComputerLabDetails.this,UpdateDetailsComputerlab.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
        disbaleeditbox();
        recyclerViewComputerLabOnSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

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
                    Toast.makeText(OnSubmit_ComputerLabDetails.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                    for (int i=0;i<approveRejectRemarksDataModel.getData().size();i++){
                        arrayListRemarks.add(approveRejectRemarksDataModel.getData().get(i).getInsName().toString());
                    }

                }
            }

            @Override
            public void onFailure(Call<ApproveRejectRemarksDataModel> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });
        computerLabRejectBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(OnSubmit_ComputerLabDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

        computerLabApproveBtn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(OnSubmit_ComputerLabDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
        if (applicationController.getUsertype().equals("VA")){
            call=apiService.checkComputerLab(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"19"));
        }else{
            call=apiService.checkComputerLab(paraGetDetails2("11",applicationController.getSchoolId(), applicationController.getPeriodID(),"19"));
        }
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+response);
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    if (Type.equals("D")){
                        editComputerLabDetails.setVisibility(View.GONE);

                    }else{
                        editComputerLabDetails.setVisibility(View.VISIBLE);

                    }
                }
                if (response.body().get(0).get("Availabilty").getAsString().equals("No")){
                    layOutComputerLab.setVisibility(View.GONE);
                    edtComputeLabAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());
                    dialog2.dismiss();
                }else{
                    edtNoOfLab.setText(response.body().get(0).get("NoOfComputerLab").getAsString());
                    edtScannerAvailable.setText(response.body().get(0).get("ScannerAvl").getAsString());
                    edtPrinterAvailable.setText(response.body().get(0).get("PrinterStatus").getAsString());
                    edtComputerOperator.setText(response.body().get(0).get("ComputerOperator").getAsString());
                    edtPowerBackup.setText(response.body().get(0).get("PowerBackUp").getAsString());
                    edtFurniture.setText(response.body().get(0).get("Furnitures").getAsString());
                    edtinternet.setText(response.body().get(0).get("Internet").getAsString());
                    edtGrantUnderScheme.setText(response.body().get(0).get("Scheme").getAsString());
                    edtNoOfWorkingComputer.setText(response.body().get(0).get("NoOfWorkingComputers").getAsString());
                    edtNoOfComputer.setText(response.body().get(0).get("NoOfComputers").getAsString());
                    edtInstallationYear.setText(response.body().get(0).get("InstallationYear").getAsString());
                    edtComputeLabAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());
                    try{
                    String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                    OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_ComputerLabDetails.this,StaffPhotoPathList, applicationController.getUsertype());
                    recyclerViewComputerLabOnSubmit.setAdapter(onlineImageRecViewAdapter);

                }catch (Exception e){
                        recyclerViewComputerLabOnSubmit.setVisibility(View.GONE);
                    }
                    dialog2.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
dialog2.dismiss();
            }
        });
    }

    private void disbaleeditbox() {
        edtNoOfLab.setEnabled(false);
                edtScannerAvailable.setEnabled(false);
        edtPrinterAvailable.setEnabled(false);
                edtComputerOperator.setEnabled(false);
        edtPowerBackup.setEnabled(false);
                edtFurniture.setEnabled(false);
        edtinternet.setEnabled(false);
                edtGrantUnderScheme.setEnabled(false);
        edtNoOfWorkingComputer.setEnabled(false);
                edtNoOfComputer.setEnabled(false);
        edtInstallationYear.setEnabled(false);
                edtComputeLabAvailabelty.setEnabled(false);
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