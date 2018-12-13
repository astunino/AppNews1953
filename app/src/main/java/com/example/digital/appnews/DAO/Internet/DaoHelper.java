package com.example.digital.appnews.DAO;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by digitalhouse on 15/01/18.
 */


public class DaoHelper {
    protected Retrofit retrofit;

    public DaoHelper(String base_url) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        retrofit =
                new Retrofit.Builder()
                        .baseUrl(base_url)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        )
                        .client(
                                httpClient.build()
                        )
                        .build();

    }
}

