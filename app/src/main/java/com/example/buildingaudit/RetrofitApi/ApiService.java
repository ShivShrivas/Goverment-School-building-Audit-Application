package com.example.buildingaudit.RetrofitApi;

import com.example.buildingaudit.Model.ClassDetailsResponse;
import com.example.buildingaudit.Model.GetAllRoomsList;
import com.example.buildingaudit.Model.GetQuaterType;
import com.example.buildingaudit.Model.GetSchoolDetails;
import com.example.buildingaudit.Model.GetUserType;
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
    Call<List<ClassDetailsResponse>> uploadClassRoomDetails(@Body JsonObject object);


}
