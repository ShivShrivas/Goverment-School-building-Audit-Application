package com.bsn.buildingaudit.DIOS.CampusBeautification;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bsn.buildingaudit.ApplicationController;
import com.bsn.buildingaudit.Model.CampusWhiteWashDetalsModel;
import com.bsn.buildingaudit.R;
import com.bsn.buildingaudit.RetrofitApi.ApiService;
import com.bsn.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WhiteWashPage extends AppCompatActivity {
    ApplicationController applicationController;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_wash_page);
        applicationController= (ApplicationController) getApplication();
        rampRepairWorkForDivyang=findViewById(R.id.rampRepairWorkForDivyang);
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
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("SchoolID","2");
        jsonObject.addProperty("PeriodID",applicationController.getPeriodID());
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<CampusWhiteWashDetalsModel> call=apiService.getWhiteWashDetails(jsonObject);
        call.enqueue(new Callback<CampusWhiteWashDetalsModel>() {
            @Override
            public void onResponse(Call<CampusWhiteWashDetalsModel> call, Response<CampusWhiteWashDetalsModel> response) {
                Log.d("TAG", "onResponse: "+response.body());
                CampusWhiteWashDetalsModel campusWhiteWashDetalsModel=response.body();
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

    }
}