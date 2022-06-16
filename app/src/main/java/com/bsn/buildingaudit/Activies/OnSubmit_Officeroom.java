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

public class OnSubmit_Officeroom extends AppCompatActivity {
    TextView userName,schoolAddress,schoolName;
    EditText edtGymAvailabelty,gymWorkingStatus,edtGymArea;
    ApplicationController applicationController;
    EditText OfficeRoomWorkingStatus,spinnerOfficeRoomAvailabelty,OfficeRoomAlmirah,OfficeRoomFurniture;
    ConstraintLayout constraintLayoutPR;
    String Type;
    TextView editOfficeRoomDetails;
    LinearLayout linearLayout21;
    RecyclerView recyclerViewOffice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_officeroom);
        applicationController= (ApplicationController) getApplication();
        spinnerOfficeRoomAvailabelty=findViewById(R.id.spinnerOfficeRoomAvailabelty);
        constraintLayoutPR=findViewById(R.id.constraintLayoutPR);
        OfficeRoomWorkingStatus=findViewById(R.id.OfficeRoomWorkingStatus);
        recyclerViewOffice=findViewById(R.id.recyclerViewOffice);
        OfficeRoomAlmirah=findViewById(R.id.OfficeRoomAlmirah);
        OfficeRoomFurniture=findViewById(R.id.OfficeRoomFurniture);
        editOfficeRoomDetails=findViewById(R.id.editOfficeRoomDetails);
        linearLayout21=findViewById(R.id.linearLayout21);
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
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
            if (Type.equals("D")){
                linearLayout21.setVisibility(View.VISIBLE);
                editOfficeRoomDetails.setVisibility(View.GONE);
            }
        recyclerViewOffice.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        disabledEdtBox();
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        editOfficeRoomDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_Officeroom.this,UpdateDetails_OfficeRoom.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });

        Call<List<JsonObject>> call=apiService.checkOfficeRoom(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"27"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    if (Type.equals("D")){
                        editOfficeRoomDetails.setVisibility(View.GONE);

                    }else{
                        editOfficeRoomDetails.setVisibility(View.VISIBLE);

                    }
                }
                if (response.body().get(0).get("SeperateRoomsAvl").getAsString().equals("No")){
                    spinnerOfficeRoomAvailabelty.setText(response.body().get(0).get("SeperateRoomsAvl").getAsString());
                    constraintLayoutPR.setVisibility(View.GONE);

                    dialog2.dismiss();
                }else {
                    spinnerOfficeRoomAvailabelty.setText(response.body().get(0).get("SeperateRoomsAvl").getAsString());
                    try {

                        OfficeRoomFurniture.setText(response.body().get(0).get("FurnitureAvl").getAsString());
                    }catch (Exception e){
                        OfficeRoomFurniture.setText("No");

                    }

                    try {
                        OfficeRoomAlmirah.setText(response.body().get(0).get("AlmirahRacksAvl").getAsString());

                    }catch (Exception e){

                        OfficeRoomAlmirah.setText("No");
                    }


                    OfficeRoomWorkingStatus.setText(response.body().get(0).get("Status").getAsString());
                    try {
                        String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                        OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_Officeroom.this,StaffPhotoPathList);
                        recyclerViewOffice.setAdapter(onlineImageRecViewAdapter);
                    }catch (Exception e){
                        recyclerViewOffice.setVisibility(View.GONE);

                    }

                }

                dialog2.dismiss();



            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();

            }
        });
    }

    private void disabledEdtBox() {
        spinnerOfficeRoomAvailabelty.setEnabled(false);
        OfficeRoomWorkingStatus.setEnabled(false);
        OfficeRoomAlmirah.setEnabled(false);
        OfficeRoomFurniture.setEnabled(false);
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