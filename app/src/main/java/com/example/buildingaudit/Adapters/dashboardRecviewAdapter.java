package com.example.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildingaudit.Activies.OnSubmitClassRoomPage;
import com.example.buildingaudit.Activies.OnSubmit_ArtAndCraft;
import com.example.buildingaudit.Activies.OnSubmit_BioMetricDetails;
import com.example.buildingaudit.Activies.OnSubmit_BoundryWalls;
import com.example.buildingaudit.Activies.OnSubmit_BoysToiletDetails;
import com.example.buildingaudit.Activies.OnSubmit_CCTVDetails;
import com.example.buildingaudit.Activies.OnSubmit_ComputerLabDetails;
import com.example.buildingaudit.Activies.OnSubmit_CycleStand;
import com.example.buildingaudit.Activies.OnSubmit_DrinkingWaterDetails;
import com.example.buildingaudit.Activies.OnSubmit_ElectricityArrangment;
import com.example.buildingaudit.Activies.OnSubmit_FireFighting;
import com.example.buildingaudit.Activies.OnSubmit_FurnitureDetails;
import com.example.buildingaudit.Activies.OnSubmit_GirlsToiletDetails;
import com.example.buildingaudit.Activies.OnSubmit_LibraryDetails;
import com.example.buildingaudit.Activies.OnSubmit_MultipurposeHallDetails;
import com.example.buildingaudit.Activies.OnSubmit_OpenGYmDetails;
import com.example.buildingaudit.Activies.OnSubmit_PlaygroundDetails;
import com.example.buildingaudit.Activies.OnSubmit_PracticalLabsDetails;
import com.example.buildingaudit.Activies.OnSubmit_PrincipalRoom;
import com.example.buildingaudit.Activies.OnSubmit_RainHarvesting;
import com.example.buildingaudit.Activies.OnSubmit_SmartClassDetails;
import com.example.buildingaudit.Activies.OnSubmit_SolarPanel;
import com.example.buildingaudit.Activies.OnSubmit_SoundSystemDetails;
import com.example.buildingaudit.Activies.OnSubmit_StaffRoomDetails;
import com.example.buildingaudit.Activies.OnSubmit_WifiDetails;
import com.example.buildingaudit.Activies.UpdateDetailTypeOne;
import com.example.buildingaudit.Activies.UpdateDetailsArtAndCraft;
import com.example.buildingaudit.Activies.UpdateDetailsBioMetric;
import com.example.buildingaudit.Activies.UpdateDetailsBoundryWall;
import com.example.buildingaudit.Activies.UpdateDetailsBoysToilet;
import com.example.buildingaudit.Activies.UpdateDetailsCCTV;
import com.example.buildingaudit.Activies.UpdateDetailsComputerlab;
import com.example.buildingaudit.Activies.UpdateDetailsCycleStand;
import com.example.buildingaudit.Activies.UpdateDetailsDrinkingWater;
import com.example.buildingaudit.Activies.UpdateDetailsElectricityArrangment;
import com.example.buildingaudit.Activies.UpdateDetailsFireFighting;
import com.example.buildingaudit.Activies.UpdateDetailsFurnitures;
import com.example.buildingaudit.Activies.UpdateDetailsGirlsToilet;
import com.example.buildingaudit.Activies.UpdateDetailsGym;
import com.example.buildingaudit.Activies.UpdateDetailsMultipurposeHall;
import com.example.buildingaudit.Activies.UpdateDetailsOfExtraThings;
import com.example.buildingaudit.Activies.UpdateDetailsPlayground;
import com.example.buildingaudit.Activies.UpdateDetailsPrincipalRoom;
import com.example.buildingaudit.Activies.UpdateDetailsRainHarvest;
import com.example.buildingaudit.Activies.UpdateDetailsSmartClass;
import com.example.buildingaudit.Activies.UpdateDetailsSolarPanel;
import com.example.buildingaudit.Activies.UpdateDetailsSoundSystem;
import com.example.buildingaudit.Activies.UpdateDetailsTypeFour;
import com.example.buildingaudit.Activies.UpdateDetailsTypeTwo;
import com.example.buildingaudit.Activies.UpdatedetailsTypeThree;
import com.example.buildingaudit.Model.GetAllRoomsList;
import com.example.buildingaudit.Model.LabDetailsResponse;
import com.example.buildingaudit.R;
import com.example.buildingaudit.RetrofitApi.ApiService;
import com.example.buildingaudit.RetrofitApi.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dashboardRecviewAdapter extends RecyclerView.Adapter<dashboardRecviewAdapter.dashboardRecViewHolder> {
    Context context;
    RestClient restClient=new RestClient();
    ApiService apiService=restClient.getApiService();
    String schoolId,periodId;
    List<GetAllRoomsList> arrayList=new ArrayList();
    public dashboardRecviewAdapter(Context context, List<GetAllRoomsList> arrayList,String schoolId,String periodId) {
        this.periodId=periodId;
        this.schoolId=schoolId;
   this.context=context;
   this.arrayList=arrayList;
    }


    @NonNull
    @Override
    public dashboardRecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_review_itemcard,parent,false);
        return new dashboardRecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dashboardRecViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.roomTypetxt.setText(arrayList.get(position).getParamName());
        try {
            if (arrayList.get(position).getLastUpdateDateTime().toString().equals("0")){
                holder.updateOntxt.setText("Not Available");

            }else {
                holder.updateOntxt.setText(arrayList.get(position).getLastUpdateDateTime());

            }
        }catch (Exception e){
            holder.updateOntxt.setText("Not Available");
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (arrayList.get(position).getParamId().toString()){
                    case "1":
                        checkDataOfClassRoom();

                        break;
                        case "2":
                            checkStaffRoomData();

                        break;
                        case "3":
                            checkPracticalLab();

                        break;
                        case "4":
                            checkLibraryDetails();

                        break;
                        case "5":
                            checkPlayGroundData();
                        break;
                        case "6":
                            checkGymmData();

                        break;
                        case "7":
                            checkDrinkingWater();

                        break;
                        case "8":
                            checkSmartClass();

                        break;
                        case "9":
                            checkBiometricDetails();

                        break;
                        case "10":
                            checkCCTVDetails();

                        break;
                        case "11":
                            checkElectricity();
                        break;
                        case "12":
                            checkFirefighting();

                        break;
                        case "13":
                            checkRainHarvest();
                        break;
                        case "14":
                            checkSolarPanel();

                        break;

                        case "15":
                            checkBoundry();

                        break;

                        case "16":
                            chechBoysToilet();


                        break;

                        case "17":
                            checkGirlToilet();

                        break;

                        case "18":
                            checkFurniture();

                        break;

                        case "19":
                            checkComputerLab();

                        break;

                        case "20":
                            chechWifiDetails();

                        break;

                        case "21":
                            checkCycleStand();

                        break;

                        case "22":
                            chechSoundSystemDetails();
                        break;

                        case "23":
                            checkMultipurposeHall();

                        break;

                        case "24":
                            checkPrincipal();
                        break;

                        case "25":
                            checkArtAndCraft();
                        break;
                    default:
                        Toast.makeText(context, "Please, Update App for this module", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
    }

    private void checkArtAndCraft() {
        Call<List<JsonObject>> call=apiService.checkArtAndCraft(paraGetDetails2("2",schoolId, periodId,"25"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdateDetailsArtAndCraft.class));
                }else {
                    context.startActivity(new Intent(context, OnSubmit_ArtAndCraft.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkPrincipal() {
        Call<List<JsonObject>> call=apiService.checkPrincipal(paraGetDetails2("2",schoolId, periodId,"24"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdateDetailsPrincipalRoom.class));
                }else {
                    context.startActivity(new Intent(context, OnSubmit_PrincipalRoom.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkFurniture() {
        Call<List<JsonObject>> call=apiService.checkFurniture(paraGetDetails2("2",schoolId, periodId,"18"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdateDetailsFurnitures.class));
                }else {
                    context.startActivity(new Intent(context, OnSubmit_FurnitureDetails.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkGirlToilet() {
        Call<List<JsonObject>> call=apiService.checkGirlsToilet(paraGetDetails2("2",schoolId, periodId,"17"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdateDetailsGirlsToilet.class));
                }else {
                    context.startActivity(new Intent(context, OnSubmit_GirlsToiletDetails.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void chechBoysToilet() {
        Call<List<JsonObject>> call=apiService.checkBoysToilet(paraGetDetails2("2",schoolId, periodId,"16"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdateDetailsBoysToilet.class));
                }else {
                    context.startActivity(new Intent(context, OnSubmit_BoysToiletDetails.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkBoundry() {
        Call<List<JsonObject>> call=apiService.checkBoundryWall(paraGetDetails2("2",schoolId, periodId,"15"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdateDetailsBoundryWall.class));
                }else {
                    context.startActivity(new Intent(context, OnSubmit_BoundryWalls.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkSolarPanel() {
        Call<List<JsonObject>> call=apiService.viewSolarPanelDetails(paraGetDetails2("2",schoolId, periodId,"14"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdateDetailsSolarPanel.class));
                }else {
                    context.startActivity(new Intent(context, OnSubmit_SolarPanel.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkElectricity() {
        Call<List<JsonObject>> call=apiService.checkElectricityArrangement(paraGetDetails2("2",schoolId, periodId,"11"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdateDetailsElectricityArrangment.class));
                }else {
                    context.startActivity(new Intent(context, OnSubmit_ElectricityArrangment.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkFirefighting() {
        Call<List<JsonObject>> call=apiService.checkFireFighting(paraGetDetails2("2",schoolId, periodId,"12"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdateDetailsFireFighting.class));
                }else {
                    context.startActivity(new Intent(context, OnSubmit_FireFighting.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkRainHarvest() {
        Call<List<JsonObject>> call=apiService.checkRainHarvest(paraGetDetails2("2",schoolId, periodId,"13"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdateDetailsRainHarvest.class));
                }else {
                    context.startActivity(new Intent(context, OnSubmit_RainHarvesting.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkCCTVDetails() {
        Call<List<JsonObject>> call=apiService.checkCCTVDetails(paraGetDetails2("2",schoolId, periodId,"10"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdateDetailsCCTV.class));
                }else {
                    context.startActivity(new Intent(context, OnSubmit_CCTVDetails.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkComputerLab() {
        Call<List<JsonObject>> call=apiService.checkComputerLab(paraGetDetails2("2",schoolId, periodId,"19"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdateDetailsComputerlab.class));
                }else {
                    context.startActivity(new Intent(context, OnSubmit_ComputerLabDetails.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void chechWifiDetails() {
        Call<List<JsonObject>> call=apiService.checkWifiDetails(paraGetDetails2("2",schoolId, periodId,"20"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    Intent i= new Intent(context, UpdateDetailsOfExtraThings.class);
                    i.putExtra("Action","1");
                    context.startActivity(i);
                }else {
                    context.startActivity(new Intent(context, OnSubmit_WifiDetails.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkCycleStand() {
        Call<List<JsonObject>> call=apiService.checkCycleStand(paraGetDetails2("2",schoolId, periodId,"21"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdateDetailsCycleStand.class));
                }else {
                    context.startActivity(new Intent(context, OnSubmit_CycleStand.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void chechSoundSystemDetails() {
        Call<List<JsonObject>> call=apiService.checkSoundSystem(paraGetDetails2("2",schoolId, periodId,"22"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdateDetailsSoundSystem.class));
                }else {
                    context.startActivity(new Intent(context, OnSubmit_SoundSystemDetails.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkMultipurposeHall() {
        Call<List<JsonObject>> call=apiService.checkMultiPurposeHall(paraGetDetails2("2",schoolId, periodId,"23"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdateDetailsMultipurposeHall.class));
                }else {
                    context.startActivity(new Intent(context, OnSubmit_MultipurposeHallDetails.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkSmartClass() {
        Call<List<JsonObject>> call=apiService.checkSmartClassDetails(paraGetDetails2("2",schoolId, periodId,"8"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdateDetailsSmartClass.class));
                }else {
                    context.startActivity(new Intent(context, OnSubmit_SmartClassDetails.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkBiometricDetails() {
        Log.d("TAG", "checkBiometricDetails: "+paraGetDetails2("2",schoolId, periodId,"9"));
        Call<List<JsonObject>> call=apiService.checkBioMetricDetails(paraGetDetails2("2",schoolId, periodId,"9"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////"+response);
                if (response.body().size()==0){
                    Intent i=new Intent(context,UpdateDetailsBioMetric.class);
                    i.putExtra("Action","1");
                    context.startActivity(i);
                }else {
                    context.startActivity(new Intent(context, OnSubmit_BioMetricDetails.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkDrinkingWater() {
        Call<List<JsonObject>> call=apiService.checkDrinkingWater(paraGetDetails2("2",schoolId, periodId,"7"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdateDetailsDrinkingWater.class));
                }else {
                    context.startActivity(new Intent(context, OnSubmit_DrinkingWaterDetails.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkLibraryDetails() {
        Call<List<JsonObject>> call=apiService.checkLibraryDetails(paraGetDetails2("2",schoolId, periodId,"4"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    Intent i= new Intent(context, UpdateDetailsTypeFour.class);
                    i.putExtra("Action","1");
                    context.startActivity(i);
                }else {
                    context.startActivity(new Intent(context, OnSubmit_LibraryDetails.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkPlayGroundData() {
        Log.d("TAG", "checkData: "+paraGetDetails2("2",schoolId, periodId,"5"));
        Call<List<JsonObject>> call=apiService.checkPlayGroundDetails(paraGetDetails2("2",schoolId, periodId,"5"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    Intent i=new Intent(context,UpdateDetailsPlayground.class);
                    i.putExtra("Action","1");
                    context.startActivity(i);
                }else {
                    context.startActivity(new Intent(context, OnSubmit_PlaygroundDetails.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkPracticalLab() {
        Log.d("TAG", "checkData: "+paraGetDetails("2",schoolId, periodId));
        Call<List<LabDetailsResponse>> call=apiService.checkLabDetails(paraGetDetails("2",schoolId, periodId));
        call.enqueue(new Callback<List<LabDetailsResponse>>() {
            @Override
            public void onResponse(Call<List<LabDetailsResponse>> call, Response<List<LabDetailsResponse>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdatedetailsTypeThree.class));
                }else {
                    context.startActivity(new Intent(context, OnSubmit_PracticalLabsDetails.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<LabDetailsResponse>> call, Throwable t) {

            }
        });
    }

    private void checkStaffRoomData() {
        Log.d("TAG", "checkData: "+paraGetDetails2("2",schoolId, periodId,"2"));
        Call<List<JsonObject>> call=apiService.checkStaffRoomDetails(paraGetDetails2("2",schoolId, periodId,"2"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    Intent i= new Intent(context, UpdateDetailsTypeTwo.class);
                    i.putExtra("Action","1");
                    context.startActivity(i);
                }else {
                    context.startActivity(new Intent(context, OnSubmit_StaffRoomDetails.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }


    private void checkGymmData() {
        Log.d("TAG", "checkData: "+paraGetDetails2("2",schoolId, periodId,"6"));
        Call<List<JsonObject>> call=apiService.checkGymDetails(paraGetDetails2("2",schoolId, periodId,"6"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    Intent i=new Intent(context,UpdateDetailsGym.class);
                    i.putExtra("Action","1");
                    context.startActivity(i);
                }else {
                    context.startActivity(new Intent(context, OnSubmit_OpenGYmDetails.class));
//                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);
//
//                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private void checkDataOfClassRoom() {
        Log.d("TAG", "checkData: "+paraGetDetails("2",schoolId, periodId));
        Call<List<JsonObject>> call=apiService.checkDetailsOfRooms(paraGetDetails("2",schoolId, periodId));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    Intent i= new Intent(context, UpdateDetailTypeOne.class);
                    i.putExtra("Action","1");
                    context.startActivity(i);
                }else {
                    Intent i=new Intent(context, OnSubmitClassRoomPage.class);

                    context.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
    }

    private JsonObject paraGetDetails(String action, String schoolId, String periodId) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",action);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodId);
        return jsonObject;
    }
    private JsonObject paraGetDetails2(String action, String schoolId, String periodId, String paramId) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",action);
        jsonObject.addProperty("ParamId",paramId);
        jsonObject.addProperty("SchoolId",schoolId);
        jsonObject.addProperty("PeriodID",periodId);
        return jsonObject;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class dashboardRecViewHolder extends RecyclerView.ViewHolder {
        TextView updateOntxt,roomTypetxt;

        public dashboardRecViewHolder(@NonNull View itemView) {
            super(itemView);
            updateOntxt=itemView.findViewById(R.id.updateOntxt);
            roomTypetxt=itemView.findViewById(R.id.roomTypetxt);
        }
    }
}
