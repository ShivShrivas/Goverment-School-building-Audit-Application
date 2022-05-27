package com.bsn.buildingaudit.RetrofitApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClientMsg91 {


    public static String BASE_URL="https://api.msg91.com/api/";
    private ApiMsg91 apiService;
    Retrofit retrofit=null;
    public RestClientMsg91()
    {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiMsg91.class);
    }

    public ApiMsg91 getApiService()
    {
        return apiService;
    }
}

