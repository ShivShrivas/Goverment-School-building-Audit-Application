package com.bsn.buildingaudit.RetrofitApi;


import com.bsn.buildingaudit.ApplicationController;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient
{
    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + ApplicationController.bearertooken)
                    .build();
            return chain.proceed(newRequest);
        }
    }).build();
// public static String BASE_URL="http://schoolgradingapiservices.bsninfotech.net/api/";
// public static String BASE_URL="http://testimageupload.bsninfotech.org/api/";
 public static String BASE_URL="https://secureapi.schoolgradingmadhyamikshiksha.in/api/";
    private ApiService apiService;
    Retrofit retrofit=null;
    public RestClient()
    {

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService()
    {
        return apiService;
    }
}