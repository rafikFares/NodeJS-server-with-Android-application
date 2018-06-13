package com.example.paramedics.myrxjava.NodeJS;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //in order for our calls to return type Observabl
                    .addConverterFactory(GsonConverterFactory.create()) //tell Retrofit which sort of converter I want it to use for serializing the JSON.
                    .build();
        }
        return retrofit;
    }
}