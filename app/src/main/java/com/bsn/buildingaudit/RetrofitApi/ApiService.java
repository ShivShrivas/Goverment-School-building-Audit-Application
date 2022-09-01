package com.bsn.buildingaudit.RetrofitApi;

import com.bsn.buildingaudit.Model.ApproveRejectRemarkModel;
import com.bsn.buildingaudit.Model.ApproveRejectRemarksDataModel;
import com.bsn.buildingaudit.Model.AttendanceStaff;
import com.bsn.buildingaudit.Model.AttendanceType;
import com.bsn.buildingaudit.Model.BoundryType;
import com.bsn.buildingaudit.Model.CampusPlantationDetalsModel;
import com.bsn.buildingaudit.Model.CampusWhiteWashDetalsModel;
import com.bsn.buildingaudit.Model.CurrentYearResultModel;
import com.bsn.buildingaudit.Model.DataLocked;
import com.bsn.buildingaudit.Model.GameDetailsModel;
import com.bsn.buildingaudit.Model.GetAllRoomsList;
import com.bsn.buildingaudit.Model.GetDashboardMenuDataModel;
import com.bsn.buildingaudit.Model.GetExtraActivitiesModel;
import com.bsn.buildingaudit.Model.GetNCCDetailsModel;
import com.bsn.buildingaudit.Model.GetNCCParticipationDetailsModel;
import com.bsn.buildingaudit.Model.GetOtherExtraActivitiesModel;
import com.bsn.buildingaudit.Model.GetQuaterType;
import com.bsn.buildingaudit.Model.GetReciptExpDetailsModel;
import com.bsn.buildingaudit.Model.GetSchoolDetails;
import com.bsn.buildingaudit.Model.GetUserType;
import com.bsn.buildingaudit.Model.InstallationYear;
import com.bsn.buildingaudit.Model.LabDetailsResponse;
import com.bsn.buildingaudit.Model.LastThreeYearsModel;
import com.bsn.buildingaudit.Model.PrincipalAndTeacherTrainingModel;
import com.bsn.buildingaudit.Model.RedCrossModel;
import com.bsn.buildingaudit.Model.SchoolDetailsModel;
import com.bsn.buildingaudit.Model.SchoolListModel;
import com.bsn.buildingaudit.Model.ScoutAndGuideModel;
import com.bsn.buildingaudit.Model.StaffSanctionAndWorkingModel;
import com.bsn.buildingaudit.Model.StudentAbsentDetailsModel;
import com.bsn.buildingaudit.Model.StudentEnrollmentListModel;
import com.bsn.buildingaudit.Model.StudentListModelTopper;
import com.bsn.buildingaudit.Model.SubjectWiseSyllabusModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
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




@Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParamLabs/SPracticalLabDetails")
    Call<List<JsonObject>> uploadLabDetails(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody object,@Part("PhotoDeleteData") RequestBody deletulrs);



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
    Call<List<JsonObject>> uploadDrinkingWater(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody requestBody,@Part("PhotoDeleteData") RequestBody deletulrs);



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
    Call<List<JsonObject>> uploadSmartClassDetails(@Part MultipartBody.Part[] parts,@Part("RequestData") RequestBody requestBody,@Part("PhotoDeleteData") RequestBody deletulrs);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewSmartClassDetails")
    Call<List<JsonObject>> checkSmartClassDetails(@Body JsonObject object);


@Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SMultiPurposeHallDetail")
    Call<List<JsonObject>> uploadMultiPurposeHall(@Part MultipartBody.Part[] parts,@Part("RequestData") RequestBody body,@Part("PhotoDeleteData") RequestBody deletulrs);

    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewMultiPurposeHallDetails")
    Call<List<JsonObject>> checkMultiPurposeHall(@Body JsonObject object);


@Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SSoundSystemBandDetailDetail")
    Call<List<JsonObject>> uploadSoundSystem(@Part MultipartBody.Part[] parts,@Part("RequestData") RequestBody body,@Part("PhotoDeleteData") RequestBody deletulrs);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewSoundSystemBandDetails")
    Call<List<JsonObject>> checkSoundSystem(@Body JsonObject object);

    @Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SCycleStandDetails")
    Call<List<JsonObject>> uploadCycleStand(@Part MultipartBody.Part[] parts,@Part("RequestData") RequestBody body,@Part("PhotoDeleteData") RequestBody deletulrs);



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

@Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParamLabs/SComputerLabDetails")
    Call<List<JsonObject>> uploadComputerLab(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody requestBody,@Part("PhotoDeleteData") RequestBody deletulrs);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParamLabs/ViewComputerLabDetails")
    Call<List<JsonObject>> checkComputerLab(@Body JsonObject object);

    @Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SCctvDetails")
    Call<List<JsonObject>> uploadCCTVDetails(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody requestBody,@Part("PhotoDeleteData") RequestBody deletulrs);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewCctvDetails")
    Call<List<JsonObject>> checkCCTVDetails(@Body JsonObject object);
@Multipart
 @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SRainHarvestingDetails")
    Call<List<JsonObject>> uploadRainHarvest(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody requestBody,@Part("PhotoDeleteData") RequestBody deletulrs);


 @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewRainHarvestingDetails")
    Call<List<JsonObject>> checkRainHarvest(@Body JsonObject object);

@Multipart
 @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SFireFightingDetails")
    Call<List<JsonObject>> uploadFireFighting(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody requestBody,@Part("PhotoDeleteData") RequestBody deletulrs);



 @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewFireFightingDetails")
    Call<List<JsonObject>> checkFireFighting(@Body JsonObject object);

    @Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SElectricityArrDetails")
    Call<List<JsonObject>> uploadElectricityArrangement(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody requestBody,@Part("PhotoDeleteData") RequestBody deletulrs);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewElectricityArrDetails")
    Call<List<JsonObject>> checkElectricityArrangement(@Body JsonObject object);



//    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
//    @POST("PhysicalParam/SSolarPanelDetails")
//    Call<List<JsonObject>> uploadSolarPanelDetails(@Body JsonObject object);

    @Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SSolarPanelDetails")
    Call<List<JsonObject>> uploadSolarPanelDetails(@Part MultipartBody.Part[] part, @Part("RequestData") RequestBody requestBody,@Part("PhotoDeleteData") RequestBody deletulrs);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewSolarPanelDetails")
    Call<List<JsonObject>> viewSolarPanelDetails(@Body JsonObject object);

//   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
//    @POST("PhysicalParam/SBoundaryWallDetails")
//    Call<List<JsonObject>> uploadBoundryWall(@Body JsonObject object);
   @Multipart
   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SBoundaryWallDetails")
    Call<List<JsonObject>> uploadBoundryWall(@Part MultipartBody.Part[] parts,@Part("RequestData")RequestBody requestBody,@Part("PhotoDeleteData") RequestBody deletulrs);


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
    Call<List<JsonObject>> uploadBoysToilet(@Part MultipartBody.Part[] parts,@Part("RequestData") RequestBody requestBody,@Part("PhotoDeleteData") RequestBody deletulrs);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewBoysToiletDetails")
    Call<List<JsonObject>> checkBoysToilet(@Body JsonObject object);


//   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
//    @POST("PhysicalParam/SGirlsToiletDetails")
//    Call<List<JsonObject>> uploadGirlsToilet(@Body JsonObject object);
    @Multipart
   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SGirlsToiletDetails")
    Call<List<JsonObject>> uploadGirlsToilet(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody requestBody,@Part("PhotoDeleteData") RequestBody deletulrs);


   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewGirlsToiletDetail")
    Call<List<JsonObject>> checkGirlsToilet(@Body JsonObject object);


//   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
//    @POST("PhysicalParam/SFurniturecDetails")
//    Call<List<JsonObject>> uploadFurniture(@Body JsonObject object);
    @Multipart
   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SFurniturecDetails")
    Call<List<JsonObject>> uploadFurniture(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody requestBody,@Part("PhotoDeleteData") RequestBody deletulrs);

   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewFurnitureDetails")
    Call<List<JsonObject>> checkFurniture(@Body JsonObject object);

    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewPrincipalRoomDetails")
    Call<List<JsonObject>> checkPrincipal(@Body JsonObject object);
@Multipart
  @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SPrincipalRoomDetails")
    Call<List<JsonObject>> uploadPrincipal(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody object,@Part("PhotoDeleteData") RequestBody deletulrs);

    @Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SArtCraftRoomDetails")
    Call<List<JsonObject>> uploadArtAndCraft(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody object,@Part("PhotoDeleteData") RequestBody deletulrs);

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

    @Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParamLabs/SVocEduDetails")
    Call<List<JsonObject>> uploadVocalRoom(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody object,@Part("PhotoDeleteData") RequestBody deletulrs);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParamLabs/ViewVRoomDetails")
    Call<List<JsonObject>> checkVocalRoom(@Body JsonObject paraGetDetails2);


    @Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/SOfficeRoomDetails")
    Call<List<JsonObject>> uploadOfficeRoom(@Part MultipartBody.Part[] part,@Part("RequestData") RequestBody object,@Part("PhotoDeleteData") RequestBody deletulrs);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("PhysicalParam/ViewOfficeRoomDetails")
    Call<List<JsonObject>> checkOfficeRoom(@Body JsonObject paraGetDetails2);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("Login/GetInsertFinalDataLock")
    Call<List<DataLocked>> checkLockedData(@Body JsonObject paraGetDetails2);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetAttendenceMaster")
    Call<List<AttendanceType>> getAttendanceType();



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetStaffAttendenceData")
    Call<List<AttendanceStaff>> getStaff(@Body JsonObject jsonObject);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/UpdateStaffAttendenceData")
    Call<JsonArray> submitAttendance(@Body JsonObject jsonObject);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetSchoolListDistrictWise")
    Call<List<SchoolListModel>> getSchoolListDistricWise(@Body JsonObject jsonObject);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/AddGeoFenceData")
    Call<JsonArray> uploadGeoFenchingDetails(@Body JsonObject jsonObject);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetStudentStrengthData")
    Call<List<StudentEnrollmentListModel>> getStudentEnrollmentDetails(@Body JsonObject jsonObject);

   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetAbsentStudentData")
    Call<List<StudentAbsentDetailsModel>> getStudentAbsentDetails(@Body JsonObject jsonObject);

   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetSchoolDetails")
    Call<SchoolDetailsModel> getSchoolDetailsForDIOS(@Body JsonObject jsonObject);


   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetStaffSanctionWorkingData")
    Call<List<StaffSanctionAndWorkingModel>> getSanctionAndWorking(@Body JsonObject jsonObject);


   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetThreeYearResultData")
    Call<ArrayList<LastThreeYearsModel>> getThreeYearResult(@Body JsonObject jsonObject);


   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetThreeYearResultStudentData")
    Call<ArrayList<StudentListModelTopper>> getStudentDataExamWise(@Body JsonObject jsonObject);



   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetCurrentResultData")
    Call<ArrayList<CurrentYearResultModel>> getCurrentYearResult(@Body JsonObject jsonObject);


   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetSyllabusData")
    Call<ArrayList<SubjectWiseSyllabusModel>> getSubjectSyllabus(@Body JsonObject jsonObject);



   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetTrainingData")
    Call<PrincipalAndTeacherTrainingModel> getPrincipalAndTeacherTraining(@Body JsonObject jsonObject);


   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetCampusBeautyData")
    Call<CampusPlantationDetalsModel> getPlantationDetals(@Body JsonObject jsonObject);



   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetSchoolBuildingWhiteWash")
    Call<CampusWhiteWashDetalsModel> getWhiteWashDetails(@Body JsonObject jsonObject);



   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetGameDetails")
    Call<GameDetailsModel> getGameDetails(@Body JsonObject jsonObject);



   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetRedCrossData")
    Call<RedCrossModel> getRedCrossSociety(@Body JsonObject jsonObject);


   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetScoutGuideData")
    Call<ScoutAndGuideModel> getScoutAndGuide(@Body JsonObject jsonObject);


   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetExtraActivitiesDetails")
    Call<GetExtraActivitiesModel> getExtraActivities(@Body JsonObject jsonObject);

   @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/GetOtherExtraActivitiesDetails")
    Call<GetOtherExtraActivitiesModel> getOtherExtraActivities(@Body JsonObject jsonObject);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
   @POST("SchoolGrading/GetDashboardMenu")
   Call<ArrayList<GetDashboardMenuDataModel>> getDiosDashboardCardsData(@Body JsonObject jsonObject);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
   @POST("SchoolGrading/GetDashboardSubMenu")
   Call<ArrayList<GetDashboardMenuDataModel>> getDiosSubmenuCardsData(@Body JsonObject jsonObject);

    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
   @POST("SchoolGrading/GetNCCDetails")
   Call<GetNCCDetailsModel> getNCCDetails(@Body JsonObject jsonObject);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
   @POST("SchoolGrading/GetNCCParticipationDetails")
   Call<GetNCCParticipationDetailsModel> getNCCParticipationDetails(@Body JsonObject jsonObject);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
   @POST("SchoolGrading/GetReciptExpDetails")
   Call<ArrayList<GetReciptExpDetailsModel>> getReciptExpDetails(@Body JsonObject jsonObject);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
   @POST("SchoolGrading/GetInsAppInspectionParameter")
   Call<ArrayList<ApproveRejectRemarkModel>> getApproveRejectRemark(@Body JsonObject jsonObject);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
   @POST("SchoolGrading/InsertInspectionData")
   Call<JsonArray> submitRemarkByDios(@Body JsonArray jsonArray);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/UpdateInspectionData")
    Call<JsonArray> updateRemarkByDios(@Body JsonArray jsonArray);



    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
   @POST("SchoolGrading/GetPreviousInspectionData")
   Call<ApproveRejectRemarksDataModel> getpriviousSubmittedDataByDIOS(@Body JsonObject jsonObject);


    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
   @POST("SchoolGrading/CheckInspectionAppValid")
   Call<JsonObject> getFinalButtonStatus(@Body JsonObject jsonObject);


    @Multipart
    @Headers("ApiKey:A1413083489FA750112FEE859535F76CF7086151344535324538")
    @POST("SchoolGrading/InsertInsAppFinalSubmit")
    Call<JsonObject> uploadFinalSubmisionDetailsDIOS(@Part MultipartBody.Part parts,@Part("RequestData") RequestBody requestBody);


}
