package com.bsn.buildingaudit.RetrofitApi;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiMsg91 {


    @GET("v5/otp")
    Call<JsonObject> getOtp(@Query("template_id") String template_id, @Query("mobile") String mobile, @Query("authkey") String authkey );



    @GET("v5/otp/verify")
    Call<JsonObject> verifyOTP(@Query("otp") String otp, @Query("authkey") String authkey, @Query("mobile") String mobile );


    @GET("v5/otp/retry")
    Call<JsonObject> resendOTP(@Query("authkey") String authkey, @Query("retrytype") String retrytype, @Query("mobile") String mobile );


}
