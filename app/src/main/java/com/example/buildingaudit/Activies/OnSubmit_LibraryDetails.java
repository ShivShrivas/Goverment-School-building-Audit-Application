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
import android.widget.EdgeEffect;
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

public class OnSubmit_LibraryDetails extends AppCompatActivity {
EditText  edtLibraryAvailabelty,edtPhysicalStatus,numberOfAlmira,edtExpenditure,edtLibraryGrantInFY,edtTotalLibraryGrant,edtGrantUnderScheme,edtNewsPaperAndMzin,edtReadingCorner,edtWorkingStatus,edtNumberOfBooksLibrary,edtFurnitureAvailabiltyInLibrary;
RecyclerView recyclerViewTwoTypeFourOnSubmit;
ApplicationController applicationController;
    TextView userName,schoolAddress,schoolName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_submit_library_details);
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
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolName=findViewById(R.id.schoolName);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        numberOfAlmira=findViewById(R.id.numberOfAlmira);
        edtLibraryAvailabelty=findViewById(R.id.edtLibraryAvailabelty);
        edtPhysicalStatus=findViewById(R.id.edtPhysicalStatus);
        edtExpenditure=findViewById(R.id.edtExpenditure);
                edtLibraryGrantInFY=findViewById(R.id.edtLibraryGrantInFY);
        edtTotalLibraryGrant=findViewById(R.id.edtTotalLibraryGrant);
                edtGrantUnderScheme=findViewById(R.id.edtGrantUnderScheme);
        edtGrantUnderScheme=findViewById(R.id.edtGrantUnderScheme);
                edtNewsPaperAndMzin=findViewById(R.id.edtNewsPaperAndMzin);
        edtReadingCorner=findViewById(R.id.edtReadingCorner);
                edtReadingCorner=findViewById(R.id.edtReadingCorner);
        edtWorkingStatus=findViewById(R.id.edtWorkingStatus);
                edtNumberOfBooksLibrary=findViewById(R.id.edtNumberOfBooksLibrary);
        edtFurnitureAvailabiltyInLibrary=findViewById(R.id.edtFurnitureAvailabiltyInLibrary);
        recyclerViewTwoTypeFourOnSubmit=findViewById(R.id.recyclerViewTwoTypeFourOnSubmit);


        disableEditing();
        recyclerViewTwoTypeFourOnSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<List<JsonObject>> call=apiService.checkLibraryDetails(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"4"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());

                edtExpenditure.setText(response.body().get(0).get("ExpenditureRsCFY").getAsString());
                        edtLibraryGrantInFY.setText(response.body().get(0).get("LibrarygrantRsCFY").getAsString());
                edtTotalLibraryGrant.setText(response.body().get(0).get("TotalLibGrant").getAsString());
                        edtGrantUnderScheme.setText(response.body().get(0).get("GrantScheme").getAsString());
                edtNewsPaperAndMzin.setText(response.body().get(0).get("SubscribeNewsMagazines").getAsString());
                edtReadingCorner.setText(response.body().get(0).get("ReadingCorner").getAsString());


                        edtWorkingStatus.setText(response.body().get(0).get("WorkingStatus").getAsString());

                        edtNumberOfBooksLibrary.setText(response.body().get(0).get("NoOfBooks").getAsString());
                numberOfAlmira.setText(response.body().get(0).get("NoOfAlmirah").getAsString());
                edtFurnitureAvailabiltyInLibrary.setText(response.body().get(0).get("FurnitureAvl").getAsString());
                edtPhysicalStatus.setText(response.body().get(0).get("PhysicalStatus").getAsString());
                edtLibraryAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());
                String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
                OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_LibraryDetails.this,StaffPhotoPathList);
                recyclerViewTwoTypeFourOnSubmit.setAdapter(onlineImageRecViewAdapter);


            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });

    }

    private void disableEditing() {
        edtExpenditure.setEnabled(false);
                edtLibraryGrantInFY.setEnabled(false);
        edtTotalLibraryGrant.setEnabled(false);
                edtGrantUnderScheme.setEnabled(false);
        edtGrantUnderScheme.setEnabled(false);
                edtNewsPaperAndMzin.setEnabled(false);
        edtReadingCorner.setEnabled(false);
                edtReadingCorner.setEnabled(false);
        edtWorkingStatus.setEnabled(false);
                edtNumberOfBooksLibrary.setEnabled(false);
        edtFurnitureAvailabiltyInLibrary.setEnabled(false);
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