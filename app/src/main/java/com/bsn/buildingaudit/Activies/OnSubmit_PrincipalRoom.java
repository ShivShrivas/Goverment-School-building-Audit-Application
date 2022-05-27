package com.bsn.buildingaudit.Activies;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
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

public class OnSubmit_PrincipalRoom extends AppCompatActivity {
    ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName;
    ConstraintLayout constraintLayoutPR;
    EditText edtRoomWorkingStatus,edtPrincipalRoomAvailabelty;
    RecyclerView recyclerViewprincipalOnSub;
    TextView  uploadPrincipal,editPrincipalRoomDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_principal_room);
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
        edtRoomWorkingStatus=findViewById(R.id.edtRoomWorkingStatus);
        edtPrincipalRoomAvailabelty=findViewById(R.id.edtPrincipalRoomAvailabelty);
        recyclerViewprincipalOnSub=findViewById(R.id.recyclerViewprincipalOnSub);
        constraintLayoutPR=findViewById(R.id.constraintLayoutAC);
        uploadPrincipal=findViewById(R.id.uploadPrincipal);
        editPrincipalRoomDetails=findViewById(R.id.editPrincipalRoomDetails);
        recyclerViewprincipalOnSub.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        edtRoomWorkingStatus.setEnabled(false);
                edtPrincipalRoomAvailabelty.setEnabled(false);
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();

        Call<List<JsonObject>> call=apiService.checkPrincipal(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"24"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    editPrincipalRoomDetails.setVisibility(View.VISIBLE);
                }
                if (response.body().get(0).get("SeperateRoomsAvl").getAsString().equals("No")){
                 edtPrincipalRoomAvailabelty.setText(response.body().get(0).get("SeperateRoomsAvl").getAsString());
                    constraintLayoutPR.setVisibility(View.GONE);
                    dialog2.dismiss();

                }else{

                    edtPrincipalRoomAvailabelty.setText(response.body().get(0).get("SeperateRoomsAvl").getAsString());
                    edtRoomWorkingStatus.setText(response.body().get(0).get("Status").getAsString());

                    }
                    String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                    OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_PrincipalRoom.this,StaffPhotoPathList);
                    recyclerViewprincipalOnSub.setAdapter(onlineImageRecViewAdapter);
                    dialog2.dismiss();

                }



            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();

            }
        });
        editPrincipalRoomDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_PrincipalRoom.this,UpdateDetailsPrincipalRoom.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });

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