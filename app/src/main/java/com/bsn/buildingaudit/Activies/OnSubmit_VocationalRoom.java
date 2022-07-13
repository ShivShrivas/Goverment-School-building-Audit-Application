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
import androidx.cardview.widget.CardView;
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

public class OnSubmit_VocationalRoom extends AppCompatActivity {
    private TextView schoolAddress,schoolName,editVocalRoomDetails;
    ApplicationController applicationController;
    LinearLayout linearLayout21;
    String Type;
    RecyclerView recyclerViewVocalIS,recyclerViewVocalHs;
    CardView scienceLabBodyCard, physicsLabImageCard,physicsLabBodyCard,scienceLabImageCard;

    EditText edtVocalISEquipmentStatus,edtVocalRoomISAvailability,edtVocalISCondition,edtVocalHSLabCondition,edtVocalHSEquipmentStatus,
            edtVocalRoomHSAvailability,edtvocalISAvailability;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_vocational_room);
        applicationController= (ApplicationController) getApplication();
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
        Intent i=getIntent();
        Type=i.getStringExtra("Type");

        schoolName=findViewById(R.id.schoolName);
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolAddress=findViewById(R.id.schoolAddress);
        recyclerViewVocalHs=findViewById(R.id.recyclerViewVocalHs);
        recyclerViewVocalIS=findViewById(R.id.recyclerViewVocalIS);
        editVocalRoomDetails=findViewById(R.id.editVocalRoomDetails);
        edtVocalISEquipmentStatus=findViewById(R.id.edtVocalISEquipmentStatus);
        edtVocalRoomISAvailability=findViewById(R.id.edtVocalRoomISAvailability);
        linearLayout21=findViewById(R.id.linearLayout21);
        edtVocalISCondition=findViewById(R.id.edtVocalISCondition);
        edtVocalRoomHSAvailability=findViewById(R.id.edtVocalRoomHSAvailability);
        edtVocalHSEquipmentStatus=findViewById(R.id.edtVocalHSEquipmentStatus);
        edtVocalHSLabCondition=findViewById(R.id.edtVocalHSLabCondition);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        scienceLabBodyCard=findViewById(R.id.scienceLabBodyCardV);
        physicsLabBodyCard=findViewById(R.id.physicsLabBodyCardV);
        physicsLabImageCard=findViewById(R.id.physicsLabImageCard);
        scienceLabImageCard=findViewById(R.id.scienceLabImageCard);
        if (Type.equals("D")){
            linearLayout21.setVisibility(View.VISIBLE);
        }
        edtVocalISEquipmentStatus.setEnabled(false);
                edtVocalRoomISAvailability.setEnabled(false);
        edtVocalISCondition.setEnabled(false);
                edtVocalRoomHSAvailability.setEnabled(false);
        edtVocalHSEquipmentStatus.setEnabled(false);
                edtVocalHSLabCondition.setEnabled(false);
        editVocalRoomDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(OnSubmit_VocationalRoom.this,UpdateDetails_VocationalEducationRoom.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
        recyclerViewVocalHs.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewVocalIS.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();

        Call<List<JsonObject>> call=apiService.checkVocalRoom(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"26"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    if (Type.equals("D")){
                        editVocalRoomDetails.setVisibility(View.GONE);

                    }else{
                        editVocalRoomDetails.setVisibility(View.VISIBLE);

                    }
                }
                if (response.body().get(0).get("RoomAvailable").getAsString().equals("No")){
                    edtVocalRoomHSAvailability.setText(response.body().get(0).get("RoomAvailable").getAsString());

                    scienceLabImageCard.setVisibility(View.GONE);
                    scienceLabBodyCard.setVisibility(View.GONE);
                }else{

                    edtVocalRoomHSAvailability.setText(response.body().get(0).get("RoomAvailable").getAsString());
                    edtVocalHSEquipmentStatus.setText(response.body().get(0).get("EquipmentStatus").getAsString());
                    edtVocalHSLabCondition.setText(response.body().get(0).get("RoomCondition").getAsString());
                }
                if (response.body().get(1).get("RoomAvailable").getAsString().equals("No")){
                    edtVocalRoomISAvailability.setText(response.body().get(1).get("RoomAvailable").getAsString());

                    physicsLabBodyCard.setVisibility(View.GONE);
                    physicsLabImageCard.setVisibility(View.GONE);
                }else{
                    edtVocalRoomISAvailability.setText(response.body().get(1).get("RoomAvailable").getAsString());
                    edtVocalISEquipmentStatus.setText(response.body().get(1).get("EquipmentStatus").getAsString());
                    edtVocalISCondition.setText(response.body().get(1).get("RoomCondition").getAsString());

                }



                    String[] StaffPhotoPathList=response.body().get(1).get("ClassPhotoPath").toString().split(",");
                    OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_VocationalRoom.this,StaffPhotoPathList, applicationController.getUsertype());
                recyclerViewVocalIS.setAdapter(onlineImageRecViewAdapter);

                    String[] StaffPhotoPathList1=response.body().get(0).get("ClassPhotoPath").toString().split(",");
                    OnlineImageRecViewAdapter onlineImageRecViewAdapter2=new OnlineImageRecViewAdapter(OnSubmit_VocationalRoom.this,StaffPhotoPathList1, applicationController.getUsertype());
                recyclerViewVocalHs.setAdapter(onlineImageRecViewAdapter2);

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