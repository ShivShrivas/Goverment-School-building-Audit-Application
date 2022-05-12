package com.example.buildingaudit.RetrofitApi;

import com.example.buildingaudit.Model.BoundryType;
import com.example.buildingaudit.Model.GetAllRoomsList;
import com.example.buildingaudit.Model.GetQuaterType;
import com.example.buildingaudit.Model.GetSchoolDetails;
import com.example.buildingaudit.Model.GetUserType;
import com.example.buildingaudit.Model.InstallationYear;
import com.example.buildingaudit.Model.LabDetailsResponse;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
    @POST("Home/GetUserList")
    Call<List<BoundryType>> getBoundryType(@Body JsonObject object);

 @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("Home/GetUserList")
    Call<List<InstallationYear>> getInstallationYear(@Body JsonObject object);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("Login/GetSchoolDetails")
    Call<List<GetSchoolDetails>> getSchoolDetails(@Body JsonObject object);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("Login/GetPhysicalParamList")
    Call<List<GetAllRoomsList>> getRoomList(@Body JsonObject object);



//    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
//    @POST("PhysicalParam/SClassRoomDetails")
//    Call<List<JsonObject>> uploadClassRoomDetails(@Body JsonObject object);
    @Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SClassRoomDetails")
    Call<List<JsonObject>> uploadClassRoomDetails(@Part MultipartBody.Part[] part,@Part MultipartBody.Part[] part1,@Part MultipartBody.Part[] part2,@Part("RequestData") RequestBody object,@Part("PhotoDeleteGood") RequestBody deletulrs,@Part("PhotoDeleteMajor") RequestBody deletulrs1,@Part("PhotoDeleteMinor") RequestBody deletulrs2);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewClassRoomDetails")
    Call<List<JsonObject>> checkDetailsOfRooms(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewStaffRoomDetails")
    Call<List<JsonObject>> checkStaffRoomDetails(@Body JsonObject object);


//
//    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
//    @POST("PhysicalParam/SStaffRoomDetails")
//    Call<List<JsonObject>> uploadStaffRoomDetails(@Body JsonObject object);
//
//


    @Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SStaffRoomDetails")
    Call<List<JsonObject>> uploadStaffRoomDetails(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody object,@Part("PhotoDeleteData") RequestBody deletulrs);





    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParamLabs/SPracticalLabDetails")
    Call<List<JsonObject>> uploadLabDetails(@Body JsonObject object);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParamLabs/ViewPracticalLabDetails")
    Call<List<LabDetailsResponse>> checkLabDetails(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewOpenGYMDetails")
    Call<List<JsonObject>> checkGymDetails(@Body JsonObject object);




//    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
//    @POST("PhysicalParam/SOpenGYMDetails")
//    Call<List<JsonObject>> uploadGymDetails(@Body JsonObject object);

        @Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SOpenGYMDetails")
    Call<List<JsonObject>> uploadGymDetails(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody requestBody,@Part("PhotoDeleteData") RequestBody deletulrs);


//
//    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
//    @POST("PhysicalParam/SPlayGroundDetails")
//    Call<List<JsonObject>> uploadPlaygroundDetails(@Body JsonObject object);

    @Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SPlayGroundDetails")
    Call<List<JsonObject>> uploadPlaygroundDetails(@Part MultipartBody.Part[] parts,@Part("RequestData") RequestBody requestBody,@Part("PhotoDeleteData") RequestBody deletulrs);






    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewPlayGroundDetails")
    Call<List<JsonObject>> checkPlayGroundDetails(@Body JsonObject object);


//    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
//    @POST("PhysicalParam/SLibraryDetails")
//    Call<List<JsonObject>> uploadLibraryDetails(@Body JsonObject object);

    @Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SLibraryDetails")
    Call<List<JsonObject>> uploadLibraryDetails(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody requestBody,@Part("PhotoDeleteData") RequestBody deletulrs);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewLibraryDetails")
    Call<List<JsonObject>> checkLibraryDetails(@Body JsonObject object);




//    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
//    @POST("PhysicalParam/SDrinkingWaterDetails")
//    Call<List<JsonObject>> uploadDrinkingWater(@Body JsonObject object);

        @Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SDrinkingWaterDetails")
    Call<List<JsonObject>> uploadDrinkingWater(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody requestBody);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewDrinkingWaterDetails")
    Call<List<JsonObject>> checkDrinkingWater(@Body JsonObject object);




    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SBiometricDetails")
    Call<List<JsonObject>> uploadBioMetricDetails(@Body JsonObject object);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewBiometricDetails")
    Call<List<JsonObject>> checkBioMetricDetails(@Body JsonObject object);

//
//    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
//    @POST("PhysicalParam/SSmartClassDetails")
//    Call<List<JsonObject>> uploadSmartClassDetails(@Body JsonObject object);
//

    @Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SSmartClassDetails")
    Call<List<JsonObject>> uploadSmartClassDetails(@Part MultipartBody.Part[] parts,@Part("RequestData") RequestBody requestBody);



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



//
//
//    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
//    @POST("PhysicalParam/SWifiDetails")
//    Call<List<JsonObject>> uploadWifiDetails(@Body JsonObject object);
//

    @Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SWifiDetails")
    Call<List<JsonObject>> uploadWifiDetails(@Part MultipartBody.Part[] parts,@Part("RequestData") RequestBody body,@Part("PhotoDeleteData") RequestBody deletulrs);


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
    @POST("PhysicalParam/SElectricityArrDetails")
    Call<List<JsonObject>> uploadElectricityArrangement(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewElectricityArrDetails")
    Call<List<JsonObject>> checkElectricityArrangement(@Body JsonObject object);



//    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
//    @POST("PhysicalParam/SSolarPanelDetails")
//    Call<List<JsonObject>> uploadSolarPanelDetails(@Body JsonObject object);

    @Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SSolarPanelDetails")
    Call<List<JsonObject>> uploadSolarPanelDetails(@Part MultipartBody.Part[] part, @Part("RequestData") RequestBody requestBody);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewSolarPanelDetails")
    Call<List<JsonObject>> viewSolarPanelDetails(@Body JsonObject object);

//   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
//    @POST("PhysicalParam/SBoundaryWallDetails")
//    Call<List<JsonObject>> uploadBoundryWall(@Body JsonObject object);
   @Multipart
   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SBoundaryWallDetails")
    Call<List<JsonObject>> uploadBoundryWall(@Part MultipartBody.Part[] parts,@Part("RequestData")RequestBody requestBody);


   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewBoundaryWallDetails")
    Call<List<JsonObject>> checkBoundryWall(@Body JsonObject object);




//   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
//    @POST("PhysicalParam/SBoysToiletDetails")
//    Call<List<JsonObject>> uploadBoysToilet(@Body JsonObject object);
//

@Multipart
   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SBoysToiletDetails")
    Call<List<JsonObject>> uploadBoysToilet(@Part MultipartBody.Part[] parts,@Part("RequestData") RequestBody requestBody);


   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewBoysToiletDetails")
    Call<List<JsonObject>> checkBoysToilet(@Body JsonObject object);


//   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
//    @POST("PhysicalParam/SGirlsToiletDetails")
//    Call<List<JsonObject>> uploadGirlsToilet(@Body JsonObject object);
    @Multipart
   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SGirlsToiletDetails")
    Call<List<JsonObject>> uploadGirlsToilet(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody requestBody);


   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewGirlsToiletDetail")
    Call<List<JsonObject>> checkGirlsToilet(@Body JsonObject object);


//   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
//    @POST("PhysicalParam/SFurniturecDetails")
//    Call<List<JsonObject>> uploadFurniture(@Body JsonObject object);

   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SFurniturecDetails")
    Call<List<JsonObject>> uploadFurniture(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody requestBody);

   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewFurnitureDetails")
    Call<List<JsonObject>> checkFurniture(@Body JsonObject object);

    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewPrincipalRoomDetails")
    Call<List<JsonObject>> checkPrincipal(@Body JsonObject object);

  @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SPrincipalRoomDetails")
    Call<List<JsonObject>> uploadPrincipal(@Body JsonObject object);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SArtCraftRoomDetails")
    Call<List<JsonObject>> uploadArtAndCraft(@Body JsonObject object);

    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewArtCraftRoomDetails")
    Call<List<JsonObject>> checkArtAndCraft(@Body JsonObject paraGetDetails2);

    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @Multipart
    @POST("PhysicalParam/ViewArtCraftRoomDetails")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part part, @Part("someData")RequestBody requestBody);

    @Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SBiometricDetails")
    Call<List<JsonObject>> uploadBiometricv2(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody object,@Part("PhotoDeleteData") RequestBody deletulrs);

}
