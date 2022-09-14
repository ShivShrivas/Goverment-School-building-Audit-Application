package com.bsn.buildingaudit.DIOS.CampusBeautification;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.ConstantValues.StaticFunctions;
import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.ApproveRejectRemarksDataModel;
import com.bsn.buildingaudit.Model.CampusWhiteWashDetalsModel;
import com.bsn.buildingaudit.Model.Datum;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WhiteWashPage extends AppCompatActivity {
    ApplicationController applicationController;
    Intent i;
    String ParentID,InspectionId;
    ArrayList<Datum> arrayListRemarks=new ArrayList<>();
    Boolean remarkAlreadyDoneFlag=false;

    TextView lastDoneWhiteWashYear,whitewashStatus,whitewashBudget,
        sanctionAmount,expenditureAmount,mantinamceOfDoorTxt,paintingOfBlackBoard,
        repairingOfFurnitures,cleaningOfWaterTank,cleaningOfDrainageSystem,
        cleningOfToilet,maintenanceofElectricalEquipments,
        boardOfHonourForStudent,boardOfHonorForTeacher,
        signBoarDoutSiddeTheSchool,repairWorkOfWall,
        finalRepairWorkYear,repairWorkOnWindowAndDoors,reapirWorkOnToilets,
        yearofLastReapairWork,boundryWallReapairWork,finalRepairWorkOfBoundryWall,
        RepairWorkofTheGate,lastYearOfGateReapair,isRampAvailableForHandicapt,
        rampRepairWorkForDiff,rampRepairWorkForDivyang;
Button White_Wash_Approve_Btn,White_Wash_Reject_Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_wash_page);
        applicationController= (ApplicationController) getApplication();
        rampRepairWorkForDivyang=findViewById(R.id.rampRepairWorkForDivyang);
        White_Wash_Reject_Btn=findViewById(R.id.White_Wash_Reject_Btn);
        White_Wash_Approve_Btn=findViewById(R.id.White_Wash_Approve_Btn);
        rampRepairWorkForDiff=findViewById(R.id.rampRepairWorkForDiff);
        isRampAvailableForHandicapt=findViewById(R.id.isRampAvailableForHandicapt);
        lastYearOfGateReapair=findViewById(R.id.lastYearOfGateReapair);
        RepairWorkofTheGate=findViewById(R.id.RepairWorkofTheGate);
        finalRepairWorkOfBoundryWall=findViewById(R.id.finalRepairWorkOfBoundryWall);
        boundryWallReapairWork=findViewById(R.id.boundryWallReapairWork);
        yearofLastReapairWork=findViewById(R.id.yearofLastReapairWork);
        reapirWorkOnToilets=findViewById(R.id.reapirWorkOnToilets);
        repairWorkOnWindowAndDoors=findViewById(R.id.repairWorkOnWindowAndDoors);
        finalRepairWorkYear=findViewById(R.id.finalRepairWorkYear);
        repairWorkOfWall=findViewById(R.id.repairWorkOfWall);
        signBoarDoutSiddeTheSchool=findViewById(R.id.signBoarDoutSiddeTheSchool);
        boardOfHonorForTeacher=findViewById(R.id.boardOfHonorForTeacher);
        boardOfHonourForStudent=findViewById(R.id.boardOfHonourForStudent);
        maintenanceofElectricalEquipments=findViewById(R.id.maintenanceofElectricalEquipments);
        cleningOfToilet=findViewById(R.id.cleningOfToilet);
        cleaningOfDrainageSystem=findViewById(R.id.cleaningOfDrainageSystem);
        cleaningOfWaterTank=findViewById(R.id.cleaningOfWaterTank);
        repairingOfFurnitures=findViewById(R.id.repairingOfFurnitures);
        paintingOfBlackBoard=findViewById(R.id.paintingOfBlackBoard);
        mantinamceOfDoorTxt=findViewById(R.id.mantinamceOfDoorTxt);
        expenditureAmount=findViewById(R.id.expenditureAmount);
        sanctionAmount=findViewById(R.id.sanctionAmount);
        whitewashBudget=findViewById(R.id.whitewashBudget);
        whitewashStatus=findViewById(R.id.whitewashStatus);
        lastDoneWhiteWashYear=findViewById(R.id.lastDoneWhiteWashYear);
        i=getIntent();
        ParentID=i.getStringExtra("ParamId");
        InspectionId=i.getStringExtra("InspectionId");
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID",applicationController.getSchoolId());
        jsonObject.addProperty("PeriodID",applicationController.getPeriodID());
        Log.d("TAG", "onCreate: "+jsonObject);
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();

        JsonObject json =new JsonObject();
        json.addProperty("SchoolID",applicationController.getSchoolId());
        json.addProperty("PeriodID",applicationController.getPeriodID());
        json.addProperty("ParamId",ParentID);
        json.addProperty("InsRecordId",InspectionId);
        Call<ApproveRejectRemarksDataModel> callz=apiService.getpriviousSubmittedDataByDIOS(json);
        callz.enqueue(new Callback<ApproveRejectRemarksDataModel>() {
            @Override
            public void onResponse(Call<ApproveRejectRemarksDataModel> call, Response<ApproveRejectRemarksDataModel> response) {
                Log.d("TAG", "onResponse: "+response.body());
                ApproveRejectRemarksDataModel approveRejectRemarksDataModel=response.body();
                Log.d("TAG", "onResponse: "+approveRejectRemarksDataModel.getStatus());
                if (!approveRejectRemarksDataModel.getStatus().equals("No Record Found")){

                    Toast.makeText(WhiteWashPage.this, ""+approveRejectRemarksDataModel.getStatus(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onResponse: "+approveRejectRemarksDataModel.getData());
                    arrayListRemarks=approveRejectRemarksDataModel.getData();
                    Dialog dialogForRemark=new Dialog(WhiteWashPage.this);
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
        Call<CampusWhiteWashDetalsModel> call=apiService.getWhiteWashDetails(jsonObject);
        call.enqueue(new Callback<CampusWhiteWashDetalsModel>() {
            @Override
            public void onResponse(Call<CampusWhiteWashDetalsModel> call, Response<CampusWhiteWashDetalsModel> response) {
                Log.d("TAG", "onResponse: "+response.body());
                CampusWhiteWashDetalsModel campusWhiteWashDetalsModel=response.body();
                assert campusWhiteWashDetalsModel != null;
                lastDoneWhiteWashYear.setText(campusWhiteWashDetalsModel.getLastdonefy()==null?" ":campusWhiteWashDetalsModel.getLastdonefy().toString());
                rampRepairWorkForDiff.setText(campusWhiteWashDetalsModel.getRepairworkoframpfordifferently()==null?"No":campusWhiteWashDetalsModel.getRepairworkoframpfordifferently().toString());
                        isRampAvailableForHandicapt.setText(campusWhiteWashDetalsModel.getRampavailableforthehandicapped()==null?"No":campusWhiteWashDetalsModel.getRampavailableforthehandicapped());
                lastYearOfGateReapair.setText(campusWhiteWashDetalsModel.getYearoflastrepairfordivyangjan()==null?" ":campusWhiteWashDetalsModel.getYearoflastrepairfordivyangjan().toString());
                        RepairWorkofTheGate.setText(campusWhiteWashDetalsModel.getHastherepairworkofthegatebeendone()==null?"No":campusWhiteWashDetalsModel.getHastherepairworkofthegatebeendone());
                finalRepairWorkOfBoundryWall.setText(campusWhiteWashDetalsModel.getYearoffinalrepairworkofboundarywall()==null?" ":campusWhiteWashDetalsModel.getYearoffinalrepairworkofboundarywall().toString());
                        boundryWallReapairWork.setText(campusWhiteWashDetalsModel.getHastheboundarywallrepairdone()==null?"No":campusWhiteWashDetalsModel.getHastheboundarywallrepairdone());

                yearofLastReapairWork.setText(campusWhiteWashDetalsModel.getYearoffinalrepair()==null?" ":campusWhiteWashDetalsModel.getYearoffinalrepair().toString());
                        reapirWorkOnToilets.setText(campusWhiteWashDetalsModel.getRepairworkrelatedtotoiletsdrinking()==null?"No":campusWhiteWashDetalsModel.getRepairworkrelatedtotoiletsdrinking());
                repairWorkOnWindowAndDoors.setText(campusWhiteWashDetalsModel.getMainwindowdoors()==null?"No":campusWhiteWashDetalsModel.getMainwindowdoors());
                        finalRepairWorkYear.setText(campusWhiteWashDetalsModel.getLastdonefy()==null?" ":campusWhiteWashDetalsModel.getLastdonefy().toString());

                repairWorkOfWall.setText(campusWhiteWashDetalsModel.getWhetherrepairworkofwall()==null?"No":campusWhiteWashDetalsModel.getWhetherrepairworkofwall().toString());
                        signBoarDoutSiddeTheSchool.setText(campusWhiteWashDetalsModel.getSignboard()==null?"No":campusWhiteWashDetalsModel.getSignboard().toString());
                boardOfHonorForTeacher.setText(campusWhiteWashDetalsModel.getBoardhonurteacher()==null?"No":campusWhiteWashDetalsModel.getBoardhonurteacher().toString());
                        boardOfHonourForStudent.setText(campusWhiteWashDetalsModel.getBoardhonurstudent()==null?"No":campusWhiteWashDetalsModel.getBoardhonurstudent().toString());
                maintenanceofElectricalEquipments.setText(campusWhiteWashDetalsModel.getElect()==null?"No":campusWhiteWashDetalsModel.getElect().toString());
                        cleningOfToilet.setText(campusWhiteWashDetalsModel.getToiletscleaning()==null?"No":campusWhiteWashDetalsModel.getToiletscleaning().toString());
                cleaningOfDrainageSystem.setText(campusWhiteWashDetalsModel.getDrainagecleaning()==null?"No":campusWhiteWashDetalsModel.getDrainagecleaning().toString());
                        cleaningOfWaterTank.setText(campusWhiteWashDetalsModel.getWatertankcleaning()==null?"No":campusWhiteWashDetalsModel.getWatertankcleaning().toString());
                repairingOfFurnitures.setText(campusWhiteWashDetalsModel.getFurniturerepair()==null?"No":campusWhiteWashDetalsModel.getFurniturerepair().toString());
                        paintingOfBlackBoard.setText(campusWhiteWashDetalsModel.getBlackboardpainting()==null?"No":campusWhiteWashDetalsModel.getBlackboardpainting().toString());
                mantinamceOfDoorTxt.setText(campusWhiteWashDetalsModel.getMainwindowdoors()==null?"No":campusWhiteWashDetalsModel.getMainwindowdoors().toString());
                        expenditureAmount.setText(campusWhiteWashDetalsModel.getExpenditureamt()==null?"0":campusWhiteWashDetalsModel.getExpenditureamt().toString());
                sanctionAmount.setText(campusWhiteWashDetalsModel.getSanctionamt()==null?"0":campusWhiteWashDetalsModel.getSanctionamt().toString());
                        whitewashBudget.setText(campusWhiteWashDetalsModel.getBudget()==null?"0":campusWhiteWashDetalsModel.getBudget().toString());
                whitewashStatus.setText(campusWhiteWashDetalsModel.getWhitewashstatus()==null?"No":campusWhiteWashDetalsModel.getWhitewashstatus().toString());

            }

            @Override
            public void onFailure(Call<CampusWhiteWashDetalsModel> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });
        White_Wash_Approve_Btn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogApprove(WhiteWashPage.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });

        White_Wash_Reject_Btn.setOnClickListener(new View.OnClickListener() {
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
                        StaticFunctions.showDialogReject(WhiteWashPage.this,arrayList,applicationController.getPeriodID(),applicationController.getSchoolId(),ParentID, arrayListRemarks,remarkAlreadyDoneFlag,InspectionId);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ApproveRejectRemarkModel>> call, Throwable t) {

                    }
                });
            }
        });
    }
}