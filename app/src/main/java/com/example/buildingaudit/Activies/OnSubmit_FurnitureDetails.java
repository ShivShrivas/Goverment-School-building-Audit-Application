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
import android.widget.LinearLayout;
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

public class OnSubmit_FurnitureDetails extends AppCompatActivity {
    ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName;

    EditText edtFurnitureRequired,edtTripleSeatesStatus,edtTrippelSeated,edtDoubleSeatesStatus,edtDoubleSeated
            ,edtSingleSeated,edtsingleSeatesStatus;
    LinearLayout constraintLayout9,constraintLayout59,constraintLayout49;
    TextView edtTotalFurnirtureStrenght;
    RecyclerView recyclerViewFurnituresOnSub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_furniture_details);


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

        edtFurnitureRequired=findViewById(R.id.edtFurnitureRequired);
        edtTotalFurnirtureStrenght=findViewById(R.id.edtTotalFurnirtureStrenght);
        edtTripleSeatesStatus=findViewById(R.id.edtTripleSeatesStatus);
        edtTrippelSeated=findViewById(R.id.edtTrippelSeated);
        edtDoubleSeated=findViewById(R.id.edtDoubleSeated);
        edtDoubleSeatesStatus=findViewById(R.id.edtDoubleSeatesStatus);
        edtSingleSeated=findViewById(R.id.edtSingleSeated);
        edtsingleSeatesStatus=findViewById(R.id.edtsingleSeatesStatus);
        recyclerViewFurnituresOnSub=findViewById(R.id.recyclerViewFurnituresOnSub);
        constraintLayout49=findViewById(R.id.constraintLayout49);
        constraintLayout59=findViewById(R.id.constraintLayout59);
        constraintLayout9=findViewById(R.id.constraintLayout9);

        disableEditText();
        recyclerViewFurnituresOnSub.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkFurniture(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"18"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                edtFurnitureRequired.setText(response.body().get(0).get("AdditionalFurniture").getAsString());
                        edtTotalFurnirtureStrenght.setText(response.body().get(0).get("TotalStrength").getAsString());




                    if (response.body().get(0).get("TotalCnt").getAsString().equals("0")){
                        constraintLayout9.setVisibility(View.GONE);
                        edtSingleSeated.setText(response.body().get(0).get("TotalCnt").getAsString());

                    }else {
                        edtsingleSeatesStatus.setText(response.body().get(0).get("Condition").getAsString());
                        edtSingleSeated.setText(response.body().get(0).get("TotalCnt").getAsString());

                    }
  if (response.body().get(1).get("TotalCnt").getAsString().equals("0")){
                        constraintLayout59.setVisibility(View.GONE);
                        edtDoubleSeated.setText(response.body().get(1).get("TotalCnt").getAsString());

                    }else {
                        edtDoubleSeatesStatus.setText(response.body().get(1).get("Condition").getAsString());
                        edtDoubleSeated.setText(response.body().get(1).get("TotalCnt").getAsString());

                    }
  if (response.body().get(2).get("TotalCnt").getAsString().equals("0")){
                        constraintLayout49.setVisibility(View.GONE);
                        edtTrippelSeated.setText(response.body().get(2).get("TotalCnt").getAsString());

                    }else {
                        edtTripleSeatesStatus.setText(response.body().get(2).get("Condition").getAsString());
                        edtTrippelSeated.setText(response.body().get(2).get("TotalCnt").getAsString());

                    }




                String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_FurnitureDetails.this,StaffPhotoPathList);
                recyclerViewFurnituresOnSub.setAdapter(onlineImageRecViewAdapter);

                dialog2.dismiss();

            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();

            }
        });

    }

    private void disableEditText() {
        edtFurnitureRequired.setEnabled(false);
                edtTotalFurnirtureStrenght.setEnabled(false);
        edtTripleSeatesStatus.setEnabled(false);
                edtTrippelSeated.setEnabled(false);
        edtDoubleSeated.setEnabled(false);
                edtDoubleSeatesStatus.setEnabled(false);
        edtSingleSeated.setEnabled(false);
                edtsingleSeatesStatus.setEnabled(false);
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