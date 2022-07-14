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

public class OnSubmit_MultipurposeHallDetails extends AppCompatActivity {
EditText edtMultipurposeHall,edtMultiPurposeHallStatus,edtSittingCapacity;
RecyclerView recyclerViewMultipurposeHallOnSubmit;
    ApplicationController applicationController;
    LinearLayout linearLayout21;
    Call<List<JsonObject>> call;
    String Type;
    TextView userName,schoolAddress,schoolName,editMultipurposeHallDetails;
    LinearLayout linearLayout5,linearLayout13,constraintLayout9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_multipurpose_hall_details);
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
        linearLayout21=findViewById(R.id.linearLayout21);
        linearLayout5=findViewById(R.id.linearLayout5);
        constraintLayout9=findViewById(R.id.constraintLayout9);
        linearLayout13=findViewById(R.id.linearLayout13);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        edtMultipurposeHall=findViewById(R.id.edtMultipurposeHall);
        edtMultiPurposeHallStatus=findViewById(R.id.edtMultiPurposeHallStatus);
        edtSittingCapacity=findViewById(R.id.edtMHsittingCapacityOnSub);
        editMultipurposeHallDetails=findViewById(R.id.editMultipurposeHallDetails);
        recyclerViewMultipurposeHallOnSubmit=findViewById(R.id.recyclerViewMultipurposeHallOnSubmit);
        if (Type.equals("D")){
            linearLayout21.setVisibility(View.VISIBLE);
        }
        disableEditBox();
        recyclerViewMultipurposeHallOnSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        editMultipurposeHallDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_MultipurposeHallDetails.this,UpdateDetailsMultipurposeHall.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        if (applicationController.getUsertype().equals("VA")){
            call=apiService.checkMultiPurposeHall(paraGetDetails2("2","2033", applicationController.getPeriodID(),"23"));
        }else{
            call=apiService.checkMultiPurposeHall(paraGetDetails2("11","2033", applicationController.getPeriodID(),"23"));
        }
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+response);
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    if (Type.equals("D")){
                        editMultipurposeHallDetails.setVisibility(View.GONE);

                    }else{
                        editMultipurposeHallDetails.setVisibility(View.VISIBLE);

                    }
                }
                if (response.body().get(0).get("Availability").getAsString().equals("No")){
                    linearLayout5.setVisibility(View.GONE);
                            constraintLayout9.setVisibility(View.GONE);
                    linearLayout13.setVisibility(View.GONE);
                    edtMultipurposeHall.setText(response.body().get(0).get("Availability").getAsString());
                    dialog2.dismiss();
                }else{
                    edtMultipurposeHall.setText(response.body().get(0).get("Availability").getAsString());

                    edtMultiPurposeHallStatus.setText(response.body().get(0).get("PhysicalStatus").getAsString());


                    edtSittingCapacity.setText(response.body().get(0).get("SittingCapacity").getAsString());
                    try {String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                        OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_MultipurposeHallDetails.this,StaffPhotoPathList, applicationController.getUsertype());
                        recyclerViewMultipurposeHallOnSubmit.setAdapter(onlineImageRecViewAdapter);

                    }catch (Exception e){
                        recyclerViewMultipurposeHallOnSubmit.setVisibility(View.GONE);
                        linearLayout5.setVisibility(View.GONE);
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

    private void disableEditBox() {
        edtMultipurposeHall.setEnabled(false);
                edtMultiPurposeHallStatus.setEnabled(false);
        edtSittingCapacity.setEnabled(false);
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