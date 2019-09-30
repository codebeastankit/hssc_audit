package com.audit.health.ssc.webservices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.audit.health.ssc.utils.Constant.serverurl;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
            retrofit = new Retrofit.Builder()
                    .baseUrl(serverurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }

}
