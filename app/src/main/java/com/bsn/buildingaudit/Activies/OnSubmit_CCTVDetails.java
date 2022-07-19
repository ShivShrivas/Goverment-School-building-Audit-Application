package com.bsn.buildingaudit.Activies;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class OnSubmit_CCTVDetails extends AppCompatActivity {
    ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName;
    RecyclerView recyclerViewCCTVOnSubmit;
    ConstraintLayout layoutCCTV;
    LinearLayout linearLayout21;
    String Type;
    Call<List<JsonObject>> call;
    TextView uploadCCTV,editCCTVDetails,EdtNoOfNotworkingCCTV;
EditText edtCCTVWorkingStatus,EdtNoOfCCTV,edtCCTVInstallationYear,edtCCTVAvailabelty,edtTotalCCTV,EdtNoNonOfCCTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_cctvdetails);

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
        edtCCTVAvailabelty=findViewById(R.id.edtCCTVAvailabelty);
        linearLayout21=findViewById(R.id.linearLayout21);
        uploadCCTV=findViewById(R.id.uploadCCTV);
        layoutCCTV=findViewById(R.id.layoutCCTV);
        edtCCTVInstallationYear=findViewById(R.id.edtCCTVInstallationYear);
        EdtNoOfCCTV=findViewById(R.id.EdtNoOfCCTV);
        EdtNoNonOfCCTV=findViewById(R.id.EdtNoNonOfCCTV);
        edtTotalCCTV=findViewById(R.id.edtTotalCCTV);
        edtCCTVWorkingStatus=findViewById(R.id.edtCCTVWorkingStatus);
        recyclerViewCCTVOnSubmit=findViewById(R.id.recyclerViewCCTVOnSubmit);
        editCCTVDetails=findViewById(R.id.editCCTVDetails);
        if (Type.equals("D")){
            linearLayout21.setVisibility(View.VISIBLE);
        }
        disbaleEdiBox();
        editCCTVDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_CCTVDetails.this,UpdateDetailsCCTV.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
        recyclerViewCCTVOnSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        if (applicationController.getUsertype().equals("VA")){
            call=apiService.checkCCTVDetails(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"10"));
        }else{
            call=apiService.checkCCTVDetails(paraGetDetails2("11",applicationController.getSchoolId(), applicationController.getPeriodID(),"10"));
        }
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+response);
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    if (Type.equals("D")){
                        editCCTVDetails.setVisibility(View.GONE);

                    }else{
                        editCCTVDetails.setVisibility(View.VISIBLE);

                    }
                }
                if (response.body().get(0).get("Availabilty").getAsString().equals("No")){
                    layoutCCTV.setVisibility(View.GONE);
                    edtCCTVAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());

                    dialog2.dismiss();
                }else
                {
                    edtCCTVAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());
                    edtCCTVInstallationYear.setText(response.body().get(0).get("InstallationYear").getAsString());
                    EdtNoOfCCTV.setText(response.body().get(0).get("WorkingCount").getAsString());
                    edtTotalCCTV.setText(response.body().get(0).get("NoOfCCTV").getAsString());

                    edtCCTVWorkingStatus.setText(response.body().get(0).get("WorkingStatus").getAsString());
                    EdtNoNonOfCCTV.setText(response.body().get(0).get("NonWorkingCount").getAsString());
                    try {
                        String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                        OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_CCTVDetails.this,StaffPhotoPathList, applicationController.getUsertype());
                        recyclerViewCCTVOnSubmit.setAdapter(onlineImageRecViewAdapter);
                    }catch (Exception e){
                        recyclerViewCCTVOnSubmit.setVisibility(View.GONE);
                        uploadCCTV.setVisibility(View.GONE);
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

    private void disbaleEdiBox() {
        edtCCTVAvailabelty.setEnabled(false);
                edtCCTVInstallationYear.setEnabled(false);
        EdtNoOfCCTV.setEnabled(false);
                edtCCTVWorkingStatus.setEnabled(false);
        EdtNoNonOfCCTV.setEnabled(false);
        edtTotalCCTV.setEnabled(false);

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