package com.example.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSubmit_MultipurposeHallDetails extends AppCompatActivity {
EditText edtMultipurposeHall,edtMultiPurposeHallStatus,edtSittingCapacity;
RecyclerView recyclerViewMultipurposeHallOnSubmit;
    ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName;

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
        edtMultipurposeHall=findViewById(R.id.edtMultipurposeHall);
        edtMultiPurposeHallStatus=findViewById(R.id.edtMultiPurposeHallStatus);
        edtSittingCapacity=findViewById(R.id.edtMHsittingCapacityOnSub);
        recyclerViewMultipurposeHallOnSubmit=findViewById(R.id.recyclerViewMultipurposeHallOnSubmit);
        disableEditBox();
        recyclerViewMultipurposeHallOnSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkMultiPurposeHall(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"23"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+response);
                edtMultipurposeHall.setText(response.body().get(0).get("Availability").getAsString());

                    edtMultiPurposeHallStatus.setText(response.body().get(0).get("PhysicalStatus").getAsString());


                edtSittingCapacity.setText(response.body().get(0).get("SittingCapacity").getAsString());

                String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_MultipurposeHallDetails.this,StaffPhotoPathList);
                recyclerViewMultipurposeHallOnSubmit.setAdapter(onlineImageRecViewAdapter);
                dialog2.dismiss();

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