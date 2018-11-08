package com.example.digital.appnews.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.digital.appnews.Modelo.ContenedorNoticias;
import com.example.digital.appnews.Modelo.Noticia;
import com.example.digital.appnews.Util.ResultListener;
import com.example.digital.appnews.Vista.MainActivity;
import com.example.digital.appnews.Vista.NoticiasFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DAOInternet extends DaoHelper {
        private ServiceNews serviceNews;
        private ServiceNewsScience serviceNewsScience;
        private ServiceNewsBusiness serviceNewsBusiness;
        private ServiceNewsSports serviceNewsSports;
        private ServiceNewsEntertainment serviceNewsEntertainment;
        private ServiceNewsTechnology serviceNewsTechnology;
        private ServiceNewsHealth serviceNewsHealth;
        private String categoria;
        private Call<ContenedorNoticias> call;

        public DAOInternet(String categoria) {
            super("https://newsapi.org/");
            this.categoria=categoria;
            if(categoria.equals(NoticiasFragment.KEY_TODO)){
                serviceNews = retrofit.create(ServiceNews.class);
            }else if(categoria.equals(NoticiasFragment.KEY_BUSINESS)){
                serviceNewsBusiness = retrofit.create(ServiceNewsBusiness.class);
            }else if(categoria.equals(NoticiasFragment.KEY_SPORTS)){
                serviceNewsSports = retrofit.create(ServiceNewsSports.class);
            }else if(categoria.equals(NoticiasFragment.KEY_SCIENCE)){
                serviceNewsScience = retrofit.create(ServiceNewsScience.class);
            }else if(categoria.equals(NoticiasFragment.KEY_ENTERTAINMENT)){
                serviceNewsEntertainment = retrofit.create(ServiceNewsEntertainment.class);
            }else if(categoria.equals(NoticiasFragment.KEY_TECHNOLOGY)){
                serviceNewsTechnology = retrofit.create(ServiceNewsTechnology.class);
            }else if(categoria.equals(NoticiasFragment.KEY_HEALTH)){
                serviceNewsHealth = retrofit.create(ServiceNewsHealth.class);
            }
        }


        public void obtenerNoticias(final ResultListener<ArrayList<Noticia>> listenerDelController){
            if(categoria.equals(NoticiasFragment.KEY_TODO)){
                call = serviceNews.getNewsContainer();
            }else if(categoria.equals(NoticiasFragment.KEY_BUSINESS)){
                call = serviceNewsBusiness.getNewsContainer();
            }else if(categoria.equals(NoticiasFragment.KEY_SPORTS)){
                call = serviceNewsSports.getNewsContainer();
            }else if(categoria.equals(NoticiasFragment.KEY_SCIENCE)){
                call = serviceNewsScience.getNewsContainer();
            }else if(categoria.equals(NoticiasFragment.KEY_ENTERTAINMENT)){
                call = serviceNewsEntertainment.getNewsContainer();
            }else if(categoria.equals(NoticiasFragment.KEY_TECHNOLOGY)){
                call = serviceNewsTechnology.getNewsContainer();
            }else if(categoria.equals(NoticiasFragment.KEY_HEALTH)){
                call = serviceNewsHealth.getNewsContainer();
            }




            call.enqueue(new Callback<ContenedorNoticias>() {
                @Override
                public void onResponse(Call<ContenedorNoticias> call, Response<ContenedorNoticias> response) {

                    ContenedorNoticias newsContainer = response.body();
                    ArrayList<Noticia> noticias = newsContainer.getMisNoticias();
                    listenerDelController.finish(noticias);
                }

                @Override
                public void onFailure(Call<ContenedorNoticias> call, Throwable t) {
                    // TODO Completar
                }
            });
        }
    }

