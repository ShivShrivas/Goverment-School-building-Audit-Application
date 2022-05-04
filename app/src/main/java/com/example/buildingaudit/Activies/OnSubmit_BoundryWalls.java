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
import android.widget.Toast;

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

public class OnSubmit_BoundryWalls extends AppCompatActivity {
EditText edtWallCondition,edtBoundryScheme,edtWhiteWash,edtTypeBoundaryWall,edtLengthofWall,edtAreaofSchool,edtBoundaryWallAvail;
    ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName;
    RecyclerView recyclerViewTwoTypeBoundarywallOnSub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_boundry_walls);


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

        edtWallCondition=findViewById(R.id.edtWallCondition);
        edtBoundryScheme=findViewById(R.id.edtBoundryScheme);
        edtWhiteWash=findViewById(R.id.edtWhiteWash);
        edtTypeBoundaryWall=findViewById(R.id.edtTypeBoundaryWall);
        edtLengthofWall=findViewById(R.id.edtLengthofWall);
        edtAreaofSchool=findViewById(R.id.edtAreaofSchool);
        edtBoundaryWallAvail=findViewById(R.id.edtBoundaryWallAvail);
        recyclerViewTwoTypeBoundarywallOnSub=findViewById(R.id.recyclerViewTwoTypeBoundarywallOnSub);
        disableEditbox();
        recyclerViewTwoTypeBoundarywallOnSub.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkBoundryWall(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"15"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());


                    edtWallCondition.setText(response.body().get(0).get("Condition").getAsString());

                        edtBoundryScheme.setText(response.body().get(0).get("Scheme").getAsString());
                edtWhiteWash.setText(response.body().get(0).get("WhiteWashStatus").getAsString());
                        edtTypeBoundaryWall.setText(response.body().get(0).get("BoundaryWallType").getAsString());
                edtLengthofWall.setText(response.body().get(0).get("BoundaryWallLength").getAsString());
                        edtAreaofSchool.setText(response.body().get(0).get("SchoolArea").getAsString());
                edtBoundaryWallAvail.setText(response.body().get(0).get("Availabilty").getAsString());
                String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_BoundryWalls.this,StaffPhotoPathList);
                recyclerViewTwoTypeBoundarywallOnSub.setAdapter(onlineImageRecViewAdapter);

            dialog2.dismiss();
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();
                Toast.makeText(getApplicationContext(), "Something went wrong please try again!!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void disableEditbox() {
        edtWallCondition.setEnabled(false);
                edtBoundryScheme.setEnabled(false);
        edtWhiteWash.setEnabled(false);
                edtTypeBoundaryWall.setEnabled(false);
        edtLengthofWall.setEnabled(false);
                edtAreaofSchool.setEnabled(false);
        edtBoundaryWallAvail.setEnabled(false);
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