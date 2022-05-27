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
import com.bsn.buildingaudit.Adapters.SmartClassAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.SmartClassesCard;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSubmit_SmartClassDetails extends AppCompatActivity {
RecyclerView smartClassONSubmitRecView;
RecyclerView recyclerViewSmartClassONSubmit;
    ApplicationController applicationController;
    private TextView schoolAddress,schoolName,editSmartClassDetails;
    ConstraintLayout layoutSmartClass;

    EditText edtsmartClassAvailabilty,edtDigitalBoardSmartClass,edtLearningContentSmartClass,edtProjectorSmartClass,edtTeacherAvailbilitySmartClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_smart_class_details);
        applicationController= (ApplicationController) getApplication();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Dialog dialog2 = new Dialog(this);

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        dialog2.show();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        schoolName=findViewById(R.id.schoolName);
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        edtsmartClassAvailabilty=findViewById(R.id.edtsmartClassAvailabilty);
        edtDigitalBoardSmartClass=findViewById(R.id.edtDigitalBoardSmartClass);
        edtLearningContentSmartClass=findViewById(R.id.edtLearningContentSmartClass);
        edtProjectorSmartClass=findViewById(R.id.edtProjectorSmartClass);
        edtTeacherAvailbilitySmartClass=findViewById(R.id.edtTeacherAvailbilitySmartClass);
        smartClassONSubmitRecView=findViewById(R.id.smartClassONSubmitRecView);
        recyclerViewSmartClassONSubmit=findViewById(R.id.recyclerViewSmartClassONSubmit);
        layoutSmartClass=findViewById(R.id.layoutSmartClass);
        editSmartClassDetails=findViewById(R.id.editSmartClassDetails);
        recyclerViewSmartClassONSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        editSmartClassDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_SmartClassDetails.this,UpdateDetailsSmartClass.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
        disableEditBox();
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();

        Call<List<JsonObject>> call=apiService.checkSmartClassDetails(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"8"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
                public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                    Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    editSmartClassDetails.setVisibility(View.VISIBLE);
                }
                    if (response.body().get(0).get("Availablity").getAsString().equals("No")){
                        edtsmartClassAvailabilty.setText(response.body().get(0).get("Availablity").getAsString());

                        layoutSmartClass.setVisibility(View.GONE);
                        dialog2.dismiss();

                    }else{

                        ArrayList<SmartClassesCard> smartClassesCards=new ArrayList<>();
                        edtsmartClassAvailabilty.setText(response.body().get(0).get("Availablity").getAsString());
                        edtDigitalBoardSmartClass.setText(response.body().get(0).get("DigitalBoard").getAsString());
                        edtLearningContentSmartClass.setText(response.body().get(0).get("LearningContent").getAsString());
                        edtProjectorSmartClass.setText(response.body().get(0).get("Projector").getAsString());
                        edtTeacherAvailbilitySmartClass.setText(response.body().get(0).get("TeacherAvl").getAsString());
                        for (int i=0;i<response.body().size();i++){
                            SmartClassesCard smartClassesCard=new SmartClassesCard();

                            String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                            OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_SmartClassDetails.this,StaffPhotoPathList);
                            recyclerViewSmartClassONSubmit.setAdapter(onlineImageRecViewAdapter);
                            smartClassesCard.setCompanyName(response.body().get(i).get("CompanyName").getAsString());
                            smartClassesCard.setName(response.body().get(i).get("Name").getAsString());
                            smartClassesCard.setScheme(response.body().get(i).get("Scheme").getAsString());
                            smartClassesCard.setInstallationYear(response.body().get(i).get("InstallationYear").getAsString());
                            smartClassesCard.setWorkingStatus(response.body().get(i).get("WorkingStatus").getAsString());
                            smartClassesCard.setSrno(response.body().get(i).get("Srno").getAsString());
                            smartClassesCards.add(smartClassesCard);

                        }
                        smartClassONSubmitRecView.setLayoutManager(new LinearLayoutManager(OnSubmit_SmartClassDetails.this,RecyclerView.HORIZONTAL,false));
                        smartClassONSubmitRecView.setAdapter(new SmartClassAdapter(OnSubmit_SmartClassDetails.this,smartClassesCards));
                        (new SmartClassAdapter(OnSubmit_SmartClassDetails.this,smartClassesCards)).notifyDataSetChanged();

                        String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                        OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_SmartClassDetails.this,StaffPhotoPathList);
                        recyclerViewSmartClassONSubmit.setAdapter(onlineImageRecViewAdapter);
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
        edtsmartClassAvailabilty.setEnabled(false);
                edtDigitalBoardSmartClass.setEnabled(false);
        edtLearningContentSmartClass.setEnabled(false);
                edtProjectorSmartClass.setEnabled(false);
        edtTeacherAvailbilitySmartClass.setEnabled(false);
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