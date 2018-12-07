package com.example.digital.appnews.DAO;

import com.example.digital.appnews.Modelo.ContenedorNoticias;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceNoticias {

    @GET("top-headlines")
    Call<ContenedorNoticias> getNewsContainerCountry(@Query("country") String country,
                                              @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<ContenedorNoticias> getArticulosPorCategoria(@Query("country") String country,
                                                      @Query("category") String category,
                                                      @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<ContenedorNoticias> getArticulosPorCanal(@Query("sources") String sources,
                                                  @Query("apiKey") String apiKey);

    @GET("everything")
    Call<ContenedorNoticias> getArticulosPorTema(@Query("q") String tema,
                                                 @Query("language") String idioma,
                                                 @Query("apiKey") String apiKey);
}
