package com.bsn.buildingaudit.Activies;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.OnlineImageRecViewAdapter;
import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.ApproveRejectRemarksDataModel;
import com.bsn.buildingaudit.Model.Datum;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnSubmit_LibraryDetails extends AppCompatActivity {
EditText  edtLibraryAvailabelty,edtPhysicalStatus,numberOfAlmira,edtExpenditure,edtLibraryGrantInFY,edtTotalLibraryGrant,edtGrantUnderScheme,edtNewsPaperAndMzin,edtReadingCorner,edtWorkingStatus,edtNumberOfBooksLibrary,edtFurnitureAvailabiltyInLibrary;
RecyclerView recyclerViewTwoTypeFourOnSubmit;
ApplicationController applicationController;
ConstraintLayout libraryLayout;
    String Type;
    String ParentID;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();

    LinearLayout linearLayout21;
    Button libraryApproveBtn,libraryRejectBtn;
TextView uploadLibrary,editLibraryDetails,mobnumberTxt;
ImageView schoolIcon;
    Call<List<JsonObject>> call;
    TextView userName,schoolAddress,schoolName;
    Boolean remarkAlreadyDoneFlag=false;

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
        Intent i=getIntent();
        Type=i.getStringExtra("Type");
        ParentID=i.getStringExtra("ParamId");

        dialog2.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog2.setContentView (R.layout.progress_dialog);
        dialog2.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
        dialog2.setCancelable(false);
        dialog2.show();
        applicationController= (ApplicationController) getApplication();
        schoolAddress=findViewById(R.id.schoolAddress);
        schoolIcon=findViewById(R.id.schoolIcon);
        mobnumberTxt=findViewById(R.id.mobnumberTxt);
        schoolName=findViewById(R.id.schoolName);
        libraryApproveBtn=findViewById(R.id.libraryApproveBtn);
        libraryRejectBtn=findViewById(R.id.libraryRejectBtn);
        schoolName.setText(applicationController.getSchoolName());
        schoolAddress.setText(applicationController.getSchoolAddress());
        numberOfAlmira=findViewById(R.id.numberOfAlmira);
        linearLayout21=findViewById(R.id.linearLayout21);
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
        libraryLayout=findViewById(R.id.libraryLayout);
        uploadLibrary=findViewById(R.id.uploadLibrary);
        editLibraryDetails=findViewById(R.id.editLibraryDetails);
        if (Type.equals("D")){
            toolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            schoolIcon.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.schoolicon_dios_panel));
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimaryDark));
            mobnumberTxt.setTextColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            uploadLibrary.setTextColor(ContextCompat.getColor(this,R.color.DIOS_ColorPrimary));
            linearLayout21.setVisibility(View.VISIBLE);
            editLibraryDetails.setVisibility(View.GONE);
        }

        disableEditing();
        recyclerViewTwoTypeFourOnSubmit.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();

        JsonObject json =new JsonObject();
        json.addProperty("SchoolID",applicationController.getSchoolId());
        json.addProperty("PeriodID",applicationController.getPeriodID());
        json.addProperty("ParamId",ParentID);
        Call<ApproveRejectRemarksDataModel> callz=apiService.getpriviousSubmittedDataByDIOS(json);
        callz.enqueue(new Callback<ApproveRejectRemarksDataModel>() {
            @Override
            public void onResponse(Call<ApproveRejectRemarksDataModel> call, Response<ApproveRejectRemarksDataModel> response) {
                Log.d("TAG", "onResponse: "+response.body());
                ApproveRejectRemarksDataModel approveRejectRemarksDataModel=response.body();
                Log.d("TAG", "onResponse: "+approveRejectRemarksDataModel.getStatus());
                if (!approveRejectRemarksDataModel.getStatus().equals("No Record Found")){

                    Toast.makeText(OnSubmit_LibraryDetails.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onResponse: "+approveRejectRemarksDataModel.getData());
                    arrayListRemarks=approveRejectRemarksDataModel.getData();
                    Dialog dialogForRemark=new Dialog(OnSubmit_LibraryDetails.this);
                    dialogForRemark.requestWindowFeature (Window.FEATURE_NO_TITLE);
                    dialogForRemark.setContentView (R.layout.respons_dialog);
                    dialogForRemark.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);
                    dialogForRemark.setCancelable(false);

                    TextView textView=dialogForRemark.findViewById(R.id.dialogtextResponse);
                    textView.setText(approveRejectRemarksDataModel.getStatus()+"\n Do you want to change it?");
                    Button buttonNo=dialogForRemark.findViewById(R.id.BtnResponseDialoge);
                    Button buttonYes=dialogForRemark.findViewById(R.id.BtnYesDialoge);
                    buttonNo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onBackPressed();
                            dialogForRemark.dismiss();

                        }
                    });
                    buttonYes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            remarkAlreadyDoneFlag=true;
                            dialogForRemark.dismiss();
                        }
                    });
                    dialogForRemark.show();
                }
            }

            @Override
            public void onFailure(Call<ApproveRejectRemarksDataModel> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });
        libraryApproveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: "+ParentID);
                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InsType","A");
                jsonObject1.addProperty("ParamId",ParentID);
                Log.d("TAG", "onClick: "+jsonObject1);
                Call<ArrayList<ApproveRejectRemarkModel>> call1=apiService.getApproveRejectRemark(jsonObject1);
                call1.enqueue(new Callback<ArrayList<ApproveRejectRemarkModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ApproveRejectRemarkModel>> call, Response<ArrayList<ApproveRejectRemarkModel>> response) {
                        ArrayList<ApproveRejectRemarkModel> arrayList=response.body();
                        StaticFunctions.showDialogApprove(OnSubmit_LibraryDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

        libraryRejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: "+ParentID);
                JsonObject jsonObject1=new JsonObject();
                jsonObject1.addProperty("InsType","R");
                jsonObject1.addProperty("ParamId",ParentID);
                Log.d("TAG", "onClick: "+jsonObject1);
                Call<ArrayList<ApproveRejectRemarkModel>> call1=apiService.getApproveRejectRemark(jsonObject1);
                call1.enqueue(new Callback<ArrayList<ApproveRejectRemarkModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ApproveRejectRemarkModel>> call, Response<ArrayList<ApproveRejectRemarkModel>> response) {
                        ArrayList<ApproveRejectRemarkModel> arrayList=response.body();
                        StaticFunctions.showDialogReject(OnSubmit_LibraryDetails.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID,arrayListRemarks,remarkAlreadyDoneFlag);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
        if (applicationController.getUsertype().equals("VA")){
            call=apiService.checkLibraryDetails(paraGetDetails2("2",applicationController.getSchoolId(), applicationController.getPeriodID(),"4"));
        }else{
            call=apiService.checkLibraryDetails(paraGetDetails2("11",applicationController.getSchoolId(), applicationController.getPeriodID(),"4"));
        }
        editLibraryDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnSubmit_LibraryDetails.this,UpdateDetailsTypeFour.class);
                i.putExtra("Action","3");
                startActivity(i);
                finish();
            }
        });
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                if (response.body().get(0).get("DataLocked").getAsString().equals("0")){
                    if (Type.equals("D")){
                        editLibraryDetails.setVisibility(View.GONE);

                    }else{
                        editLibraryDetails.setVisibility(View.VISIBLE);

                    }
                }
if (response.body().get(0).get("Availabilty").getAsString().equals("No")){
    libraryLayout.setVisibility(View.GONE);
    edtLibraryAvailabelty.setText(response.body().get(0).get("Availabilty").getAsString());
        dialog2.dismiss();
}else{
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

    try{
        String[] StaffPhotoPathList=response.body().get(0).get("PhotoPath").toString().split(",");
        OnlineImageRecViewAdapter onlineImageRecViewAdapter=new OnlineImageRecViewAdapter(OnSubmit_LibraryDetails.this,StaffPhotoPathList, applicationController.getUsertype());
        recyclerViewTwoTypeFourOnSubmit.setAdapter(onlineImageRecViewAdapter);
    }catch (Exception e){
        recyclerViewTwoTypeFourOnSubmit.setVisibility(View.GONE);
        uploadLibrary.setVisibility(View.GONE);
    }



    dialog2.dismiss();
}


            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                dialog2.dismiss();

            }
        });

    }

    private void disableEditing() {
        edtExpenditure.setEnabled(false);
        numberOfAlmira.setEnabled(false);
        edtPhysicalStatus.setEnabled(false);
        edtLibraryAvailabelty.setEnabled(false);
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