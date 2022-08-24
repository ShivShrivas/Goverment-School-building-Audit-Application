package com.bsn.buildingaudit.DIOS.SchoolResultDetails;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.AdapterStudentGameDetails;
import com.bsn.buildingaudit.Adapters.StudentResultAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.LastThreeYearsModel;
import com.bsn.buildingaudit.Model.StudentListModelTopper;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student_Result_Details_last_three_years extends AppCompatActivity {
RecyclerView recyclerViewFOrStudentResult;
StudentResultAdapter adapter;
ConstraintLayout constraintLayout46,lastThreeYearResultLayout;
    ApplicationController applicationController;
    String ResultDetailsID,OtherCompExamName;
    ArrayList<StudentListModelTopper> myImageNameList = new ArrayList<>();
    ImageView districLevelTopperImg,stateLevelToperImg,comptativeExamSelectionImg;
    TextView districLevelTopper,stateLevelTopper,comptativeExamSelection;
    Intent i;
    String ParentID;
Button last_threeYearResultApproveBtn,last_threeYearResultRejectBtn;
ArrayList<LastThreeYearsModel> result=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_result_details);
        applicationController= (ApplicationController) getApplication();
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
        i=getIntent();
        ParentID=i.getStringExtra("ParamId");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recyclerViewFOrStudentResult=findViewById(R.id.recyclerViewFOrStudentResult);
        last_threeYearResultRejectBtn=findViewById(R.id.last_threeYearResultRejectBtn);
        lastThreeYearResultLayout=findViewById(R.id.lastThreeYearResultLayout);
        constraintLayout46=findViewById(R.id.constraintLayout46);
        last_threeYearResultApproveBtn=findViewById(R.id.last_threeYearResultApproveBtn);
        districLevelTopper=findViewById(R.id.districLevelTopper);
        stateLevelTopper=findViewById(R.id.stateLevelTopper);
        districLevelTopperImg=findViewById(R.id.districLevelTopperImg);
        comptativeExamSelectionImg=findViewById(R.id.comptativeExamSelectionImg);
        stateLevelToperImg=findViewById(R.id.stateLevelToperImg);
        comptativeExamSelection=findViewById(R.id.comptativeExamSelection);
        recyclerViewFOrStudentResult.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID",applicationController.getSchoolId());
        jsonObject.addProperty("PeriodID",applicationController.getPeriodID());
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<ArrayList<LastThreeYearsModel>> call=apiService.getThreeYearResult(jsonObject);
        call.enqueue(new Callback<ArrayList<LastThreeYearsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<LastThreeYearsModel>> call, Response<ArrayList<LastThreeYearsModel>> response) {
                result=response.body();
                Log.d("TAG", "onResponse: "+response.body());
//               try{
                   adapter=new StudentResultAdapter(Student_Result_Details_last_three_years.this,result);
                   recyclerViewFOrStudentResult.setAdapter(adapter);
                   adapter.notifyDataSetChanged();
                   comptativeExamSelection.setText(result.get(0).getCompExamSelection()==null?"No":result.get(0).getCompExamSelection().toString());
                   districLevelTopper.setText(result.get(0).getDistrictLevelTopperStatus()==null?"No":result.get(0).getDistrictLevelTopperStatus().toString());
                   stateLevelTopper.setText(result.get(0).getStateLevelTopperStatus()==null?"No":result.get(0).getStateLevelTopperStatus().toString());
                   ResultDetailsID=result.get(0).getResultDetailsID()==null?"No":result.get(0).getResultDetailsID().toString();
                OtherCompExamName=result.get(0).getOtherCompExamName()==null?"No":result.get(0).getOtherCompExamName().toString();

//               }catch (Exception e){
//                   constraintLayout46.setVisibility(View.GONE);
//                   lastThreeYearResultLayout.setBackground(ContextCompat.getDrawable(Student_Result_Details_last_three_years.this,R.drawable.ic_no_internet));
//               }
            }

            @Override
            public void onFailure(Call<ArrayList<LastThreeYearsModel>> call, Throwable t) {

            }
        });

        comptativeExamSelectionImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(Student_Result_Details_last_three_years.this,ResultDetailsID,OtherCompExamName);
            }
        });

        districLevelTopperImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(Student_Result_Details_last_three_years.this, ResultDetailsID, "D");
            }
        });

        stateLevelToperImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(Student_Result_Details_last_three_years.this, ResultDetailsID, "S");
            }
        });

        ArrayList<String> myImageNameList = new ArrayList<>();
        myImageNameList.add("All Given Information is correct");


        ArrayList<String> myImageNameList2 = new ArrayList<>();
        myImageNameList2.add("Student Data is not correct");
        myImageNameList2.add("Toppers Data is not correct");
        myImageNameList2.add("No. of attached All Data is not correct");


        last_threeYearResultApproveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: "+ParentID);
                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InsType","A");
                jsonObject1.addProperty("ParamId",ParentID);
                Call<ArrayList<ApproveRejectRemarkModel>> call1=apiService.getApproveRejectRemark(jsonObject1);
                call1.enqueue(new Callback<ArrayList<ApproveRejectRemarkModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ApproveRejectRemarkModel>> call, Response<ArrayList<ApproveRejectRemarkModel>> response) {
                        ArrayList<ApproveRejectRemarkModel> arrayList=response.body();
                        StaticFunctions.showDialogApprove(Student_Result_Details_last_three_years.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

        last_threeYearResultRejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: "+ParentID);
                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InsType","R");
                jsonObject1.addProperty("ParamId",ParentID);
                Call<ArrayList<ApproveRejectRemarkModel>> call1=apiService.getApproveRejectRemark(jsonObject1);
                call1.enqueue(new Callback<ArrayList<ApproveRejectRemarkModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ApproveRejectRemarkModel>> call, Response<ArrayList<ApproveRejectRemarkModel>> response) {
                        ArrayList<ApproveRejectRemarkModel> arrayList=response.body();
                        StaticFunctions.showDialogReject(Student_Result_Details_last_three_years.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
    }
    public void showDialog(Context activity, String detailsID, String resultDetailsID) {
        Dialog dialog = new Dialog(activity);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.student_list_dialog);

        Button btndialog = (Button) dialog.findViewById(R.id.btndialog);
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("ResultDetailID",detailsID);
        jsonObject.addProperty("LevelType",resultDetailsID);
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Log.d("TAG", "showDialog: "+jsonObject);
        Call<ArrayList<StudentListModelTopper>> call=apiService.getStudentDataExamWise(jsonObject);
        call.enqueue(new Callback<ArrayList<StudentListModelTopper>>() {
            @Override
            public void onResponse(Call<ArrayList<StudentListModelTopper>> call, Response<ArrayList<StudentListModelTopper>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                myImageNameList=response.body();
                RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
                recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                AdapterStudentGameDetails adapterRe = new AdapterStudentGameDetails(activity, myImageNameList);
                recyclerView.setAdapter(adapterRe);
            }

            @Override
            public void onFailure(Call<ArrayList<StudentListModelTopper>> call, Throwable t) {

            }
        });



        dialog.show();
    }
}