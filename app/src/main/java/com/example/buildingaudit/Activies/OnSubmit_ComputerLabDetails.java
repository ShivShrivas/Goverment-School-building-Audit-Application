package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.buildingaudit.Adapters.OnlineImageRecViewAdapter;
import com.example.buildingaudit.ApplicationController;
import com.example.buildingaudit.R;
import com.example.buildingaudit.RetrofitApi.ApiService;
import com.example.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.jar.JarEntry;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSubmit_ComputerLabDetails extends AppCompatActivity {
    ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName;
RecyclerView recyclerViewComputerLabOnSubmit;
ConstraintLayout layOutComputerLab;
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

        Dialog dialog2 = new Dialog(this);

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

        edtNoOfLab=findViewById(R.id.edtNoOfLab);
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
        disbaleeditbox();
        recyclerViewComputerLabOnSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkComputerLab(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"19"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+response);
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
                    OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_ComputerLabDetails.this,StaffPhotoPathList);
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