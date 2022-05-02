package com.example.buildingaudit.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildingaudit.Activies.OnSubmitClassRoomPage;
import com.example.buildingaudit.Activies.OnSubmit_BioMetricDetails;
import com.example.buildingaudit.Activies.OnSubmit_DrinkingWaterDetails;
import com.example.buildingaudit.Activies.OnSubmit_LibraryDetails;
import com.example.buildingaudit.Activies.OnSubmit_OpenGYmDetails;
import com.example.buildingaudit.Activies.OnSubmit_PlaygroundDetails;
import com.example.buildingaudit.Activies.OnSubmit_PracticalLabsDetails;
import com.example.buildingaudit.Activies.OnSubmit_StaffRoomDetails;
import com.example.buildingaudit.Activies.UpdateDetailTypeOne;
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
                        context.startActivity(new Intent(context, UpdateDetailsSmartClass.class));
                        break;
                        case "9":
                            checkBiometricDetails();

                        break;
                        case "10":
                        context.startActivity(new Intent(context, UpdateDetailsCCTV.class));
                        break;
                        case "11":
                        context.startActivity(new Intent(context, UpdateDetailsElectricityArrangment.class));
                        break;
                        case "12":
                        context.startActivity(new Intent(context, UpdateDetailsFireFighting.class));
                        break;
                        case "13":
                        context.startActivity(new Intent(context, UpdateDetailsRainHarvest.class));
                        break;
                        case "14":
                        context.startActivity(new Intent(context, UpdateDetailsSolarPanel.class));
                        break;

                        case "15":
                        context.startActivity(new Intent(context, UpdateDetailsBoundryWall.class));
                        break;

                        case "16":
                        context.startActivity(new Intent(context, UpdateDetailsBoysToilet.class));
                        break;

                        case "17":
                        context.startActivity(new Intent(context, UpdateDetailsGirlsToilet.class));
                        break;

                        case "18":
                        context.startActivity(new Intent(context, UpdateDetailsFurnitures.class));
                        break;

                        case "19":
                        context.startActivity(new Intent(context, UpdateDetailsComputerlab.class));
                        break;

                        case "20":
                        context.startActivity(new Intent(context, UpdateDetailsOfExtraThings.class));
                        break;

                        case "21":
                        context.startActivity(new Intent(context, UpdateDetailsCycleStand.class));
                        break;

                        case "22":
                        context.startActivity(new Intent(context, UpdateDetailsSoundSystem.class));
                        break;

                        case "23":
                        context.startActivity(new Intent(context, UpdateDetailsMultipurposeHall.class));
                        break;

                }
            }
        });
    }

    private void checkBiometricDetails() {
        Call<List<JsonObject>> call=apiService.checkBioMetricDetails(paraGetDetails2("2",schoolId, periodId,"9"));
        call.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                Log.d("TAG", "onResponse: "+response.body()+"///////");
                if (response.body().size()==0){
                    context.startActivity(new Intent(context, UpdateDetailsBioMetric.class));
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
                    context.startActivity(new Intent(context, UpdateDetailsTypeFour.class));
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
                    context.startActivity(new Intent(context, UpdateDetailsPlayground.class));
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
                    context.startActivity(new Intent(context, UpdateDetailsTypeTwo.class));
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
                    context.startActivity(new Intent(context, UpdateDetailsGym.class));
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
                    context.startActivity(new Intent(context, UpdateDetailTypeOne.class));
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
