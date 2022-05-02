package com.example.buildingaudit.RetrofitApi;

import com.example.buildingaudit.Model.ClassDetailsResponse;
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








}
