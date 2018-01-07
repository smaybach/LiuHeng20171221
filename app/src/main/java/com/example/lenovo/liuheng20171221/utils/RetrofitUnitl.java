package com.example.lenovo.liuheng20171221.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2017/12/21.
 */

public class RetrofitUnitl {
    private Retrofit mRetrofit;
    private String baseUrl;
    OkHttpClient client;
    private static RetrofitUnitl mRetrofitManager;

    public RetrofitUnitl(String baseUrl, OkHttpClient client) {
        this.baseUrl = baseUrl;
        this.client = client;
        initRetrofit();
    }
    public static synchronized RetrofitUnitl getInstance(String baseUrl,OkHttpClient client){
        if (mRetrofitManager == null){
            mRetrofitManager = new RetrofitUnitl(baseUrl,client);
        }
        return mRetrofitManager;
    }
    private void initRetrofit() {
        mRetrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
    public <T> T setCreate(Class<T> reqServer) {
        return mRetrofit.create(reqServer);
    }

}
