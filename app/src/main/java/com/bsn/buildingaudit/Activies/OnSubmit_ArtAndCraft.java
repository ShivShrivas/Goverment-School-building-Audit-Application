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

public class OnSubmit_ArtAndCraft extends AppCompatActivity {
    ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName;
    ConstraintLayout constraintLayoutAC;
    EditText edtArtAndCraftRoomPhysicalStatus,ArtAndCraftRoomWorkingStatus,edtArtAndCraftRoomAvailabelty;
    TextView UploadedImageAC,editArtAndCraftDetails;
    RecyclerView recyclerViewArtAndCraftOnSub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_art_and_craft);
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
        constraintLayoutAC=findViewById(R.id.constraintLayoutAC);
        recyclerViewArtAndCraftOnSub=findViewById(R.id.recyclerViewArtAndCraftOnSub);
        edtArtAndCraftRoomPhysicalStatus=findViewById(R.id.edtArtAndCraftRoomPhysicalStatus);
        ArtAndCraftRoomWorkingStatus=findViewById(R.id.ArtAndCraftRoomWorkingStatus);
        edtArtAndCraftRoomAvailabelty=findViewById(R.id.edtArtAndCraftRoomAvailabelty);
        UploadedImageAC=findViewById(R.id.UploadedImageAC);
        editArtAndCraftDetails=findViewById(R.id.editArtAndCraftDetails);

        edtArtAndCraftRoomPhysicalStatus.setEnabled(false);
                ArtAndCraftRoomWorkingStatus.setEnabled(false);
        edtArtAndCraftRoomAvailabelty.setEnabled(false);
        editArtAndCraftDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(OnSubmit_ArtAndCraft.this,UpdateDetailsArtAndCraft.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();

            }

        });
        recyclerViewArtAndCraftOnSub.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();

        Call<List<JsonObject>> call=apiService.checkArtAndCraft(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"25"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    editArtAndCraftDetails.setVisibility(View.VISIBLE);
                }
                if (response.body().get(0).get("SeperateRoomsAvl").getAsString().equals("No")){
                    edtArtAndCraftRoomAvailabelty.setText(response.body().get(0).get("SeperateRoomsAvl").getAsString());
                    constraintLayoutAC.setVisibility(View.GONE);
                    dialog2.dismiss();

                }else{

                    edtArtAndCraftRoomAvailabelty.setText(response.body().get(0).get("SeperateRoomsAvl").getAsString());
                    ArtAndCraftRoomWorkingStatus.setText(response.body().get(0).get("WorkingStatus").getAsString());
                    edtArtAndCraftRoomPhysicalStatus.setText(response.body().get(0).get("PhysicalStatus").getAsString());


                }
                String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_ArtAndCraft.this,StaffPhotoPathList);
                recyclerViewArtAndCraftOnSub.setAdapter(onlineImageRecViewAdapter);
                dialog2.dismiss();

            }



            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();

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