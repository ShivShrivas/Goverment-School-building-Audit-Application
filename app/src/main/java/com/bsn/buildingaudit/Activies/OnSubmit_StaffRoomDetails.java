package com.bsn.buildingaudit.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

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

public class OnSubmit_StaffRoomDetails extends AppCompatActivity {
    private TextView schoolAddress,schoolName,editStaffRoomDetails;
    ApplicationController applicationController;

    EditText edtAlmiraAndRacksAvailabiltyOnSubmit,edtFurnitureAvailabiltyOnSubmit,edtStaffRoomStatusOnSubmit,edtStaffRoomAvailabilityOnSubmit;
    RecyclerView recyclerViewStafroomtwoOnSubmit;
    ConstraintLayout constraintLayout22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_staff_room_details);
        Dialog dialog2 = new Dialog(this);

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        dialog2.show();
        edtAlmiraAndRacksAvailabiltyOnSubmit=findViewById(R.id.edtAlmiraAndRacksAvailabiltyOnSubmit);
        edtFurnitureAvailabiltyOnSubmit=findViewById(R.id.edtFurnitureAvailabiltyOnSubmit);
        edtStaffRoomStatusOnSubmit=findViewById(R.id.edtStaffRoomStatusOnSubmit);
        edtStaffRoomAvailabilityOnSubmit=findViewById(R.id.edtStaffRoomAvailabilityOnSubmit);
        constraintLayout22=findViewById(R.id.constraintLayout22);
        recyclerViewStafroomtwoOnSubmit=findViewById(R.id.recyclerViewStafroomtwoOnSubmit);
        applicationController= (ApplicationController) getApplication();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        schoolName=findViewById(R.id.schoolName);
        schoolAddress=findViewById(R.id.schoolAddress);
        editStaffRoomDetails=findViewById(R.id.editStaffRoomDetails);

        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        recyclerViewStafroomtwoOnSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        editStaffRoomDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_StaffRoomDetails.this,UpdateDetailsTypeTwo.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkStaffRoomDetails(paraGetDetails("2",applicationController.getSchoolId(), applicationController.getPeriodID()));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                setAllEditTextDisabled();
                if (response.body().get(0).get("SeperateRoomsAvl").getAsString().equals("No")){
                    constraintLayout22.setVisibility(View.GONE);
                    edtStaffRoomAvailabilityOnSubmit.setText(response.body().get(0).get("SeperateRoomsAvl").getAsString());
                    dialog2.dismiss();
                }else {
                    edtStaffRoomAvailabilityOnSubmit.setText(response.body().get(0).get("SeperateRoomsAvl").getAsString());
                    edtAlmiraAndRacksAvailabiltyOnSubmit.setText(response.body().get(0).get("AlmirahRacksAvl").getAsString());
                    edtFurnitureAvailabiltyOnSubmit.setText(response.body().get(0).get("FurnitureAvl").getAsString());
                    edtStaffRoomStatusOnSubmit.setText(response.body().get(0).get("Status").getAsString());
                    String[] StaffPhotoPathList=response.body().get(0).get("StaffPhotoPath").toString().split(",");
                    OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_StaffRoomDetails.this,StaffPhotoPathList);
                    recyclerViewStafroomtwoOnSubmit.setAdapter(onlineImageRecViewAdapter);
                    dialog2.dismiss();
                }


            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();

            }
        });
    }

    private void setAllEditTextDisabled() {
        edtStaffRoomStatusOnSubmit.setEnabled(false);
        edtAlmiraAndRacksAvailabiltyOnSubmit.setEnabled(false);
        edtFurnitureAvailabiltyOnSubmit.setEnabled(false);
        edtStaffRoomAvailabilityOnSubmit.setEnabled(false);
    }


    private JsonObject paraGetDetails(String action, String schoolId, String periodId) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",action);
        jsonObject.addProperty("ParamId","2");
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodId);
        return jsonObject;
    }

}