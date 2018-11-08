package com.example.digital.appnews.DAO;

import com.example.digital.appnews.Modelo.ContenedorNoticias;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by digitalhouse on 15/01/18.
 */

public interface ServiceNews {
    @GET("/v2/top-headlines?country=ar&apiKey=8b141017cf6848908829489044ed6f71")
    Call<ContenedorNoticias> getNewsContainer(
    );
}
