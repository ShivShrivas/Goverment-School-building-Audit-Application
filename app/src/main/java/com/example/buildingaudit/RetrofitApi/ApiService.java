package com.example.buildingaudit.RetrofitApi;

import com.example.buildingaudit.Model.GetAllRoomsList;
import com.example.buildingaudit.Model.GetQuaterType;
import com.example.buildingaudit.Model.GetSchoolDetails;
import com.example.buildingaudit.Model.GetUserType;
import com.example.buildingaudit.Model.LabDetailsResponse;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public  interface ApiService {


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("Home/GetUserList")
    Call<List<GetUserType>> getUserType(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("Home/GetPeriodIdList")
    Call<List<GetQuaterType>> getPeriodList(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("Login/GetLoginDetails")
    Call<List<JsonObject>> getLogin(@Body JsonObject object);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("Login/GetSchoolDetails")
    Call<List<GetSchoolDetails>> getSchoolDetails(@Body JsonObject object);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("Login/GetPhysicalParamList")
    Call<List<GetAllRoomsList>> getRoomList(@Body JsonObject object);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SClassRoomDetails")
    Call<List<JsonObject>> uploadClassRoomDetails(@Body JsonObject object);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewClassRoomDetails")
    Call<List<JsonObject>> checkDetailsOfRooms(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewStaffRoomDetails")
    Call<List<JsonObject>> checkStaffRoomDetails(@Body JsonObject object);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SStaffRoomDetails")
    Call<List<JsonObject>> uploadStaffRoomDetails(@Body JsonObject object);





    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParamLabs/SPracticalLabDetails")
    Call<List<JsonObject>> uploadLabDetails(@Body JsonObject object);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParamLabs/ViewPracticalLabDetails")
    Call<List<LabDetailsResponse>> checkLabDetails(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewOpenGYMDetails")
    Call<List<JsonObject>> checkGymDetails(@Body JsonObject object);




    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SOpenGYMDetails")
    Call<List<JsonObject>> uploadGymDetails(@Body JsonObject object);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SPlayGroundDetails")
    Call<List<JsonObject>> uploadPlaygroundDetails(@Body JsonObject object);






    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewPlayGroundDetails")
    Call<List<JsonObject>> checkPlayGroundDetails(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SLibraryDetails")
    Call<List<JsonObject>> uploadLibraryDetails(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewLibraryDetails")
    Call<List<JsonObject>> checkLibraryDetails(@Body JsonObject object);




    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SDrinkingWaterDetails")
    Call<List<JsonObject>> uploadDrinkingWater(@Body JsonObject object);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewDrinkingWaterDetails")
    Call<List<JsonObject>> checkDrinkingWater(@Body JsonObject object);




    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SBiometricDetails")
    Call<List<JsonObject>> uploadBioMetricDetails(@Body JsonObject object);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewBiometricDetails")
    Call<List<JsonObject>> checkBioMetricDetails(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SSmartClassDetails")
    Call<List<JsonObject>> uploadSmartClassDetails(@Body JsonObject object);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewSmartClassDetails")
    Call<List<JsonObject>> checkSmartClassDetails(@Body JsonObject object);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SMultiPurposeHallDetail")
    Call<List<JsonObject>> uploadMultiPurposeHall(@Body JsonObject object);

    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewMultiPurposeHallDetails")
    Call<List<JsonObject>> checkMultiPurposeHall(@Body JsonObject object);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SSoundSystemBandDetailDetail")
    Call<List<JsonObject>> uploadSoundSystem(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewSoundSystemBandDetails")
    Call<List<JsonObject>> checkSoundSystem(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SCycleStandDetails")
    Call<List<JsonObject>> uploadCycleStand(@Body JsonObject object);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewCycleStandDetails")
    Call<List<JsonObject>> checkCycleStand(@Body JsonObject object);





    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SWifiDetails")
    Call<List<JsonObject>> uploadWifiDetails(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewWifiDetails")
    Call<List<JsonObject>> checkWifiDetails(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParamLabs/SComputerLabDetails")
    Call<List<JsonObject>> uploadComputerLab(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParamLabs/ViewComputerLabDetails")
    Call<List<JsonObject>> checkComputerLab(@Body JsonObject object);

    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SCctvDetails")
    Call<List<JsonObject>> uploadCCTVDetails(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewCctvDetails")
    Call<List<JsonObject>> checkCCTVDetails(@Body JsonObject object);

 @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SRainHarvestingDetails")
    Call<List<JsonObject>> uploadRainHarvest(@Body JsonObject object);


 @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewRainHarvestingDetails")
    Call<List<JsonObject>> checkRainHarvest(@Body JsonObject object);


 @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SFireFightingDetails")
    Call<List<JsonObject>> uploadFireFighting(@Body JsonObject object);



 @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewFireFightingDetails")
    Call<List<JsonObject>> checkFireFighting(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewFireFightingDetails")
    Call<List<JsonObject>> uploadElectricityArrangement(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewElectricityArrDetails")
    Call<List<JsonObject>> checkElectricityArrangement(@Body JsonObject object);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SSolarPanelDetails")
    Call<List<JsonObject>> uploadSolarPanelDetails(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewSolarPanelDetails")
    Call<List<JsonObject>> viewSolarPanelDetails(@Body JsonObject object);

   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SBoundaryWallDetails")
    Call<List<JsonObject>> uploadBoundryWall(@Body JsonObject object);


   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewBoundaryWallDetails")
    Call<List<JsonObject>> checkBoundryWall(@Body JsonObject object);




   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SBoysToiletDetails")
    Call<List<JsonObject>> uploadBoysToilet(@Body JsonObject object);


   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewBoysToiletDetails")
    Call<List<JsonObject>> checkBoysToilet(@Body JsonObject object);


   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SGirlsToiletDetails")
    Call<List<JsonObject>> uploadGirlsToilet(@Body JsonObject object);


   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewGirlsToiletDetail")
    Call<List<JsonObject>> checkGirlsToilet(@Body JsonObject object);


   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SFurniturecDetails")
    Call<List<JsonObject>> uploadFurniture(@Body JsonObject object);

   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewFurnitureDetails")
    Call<List<JsonObject>> checkFurniture(@Body JsonObject object);




}
