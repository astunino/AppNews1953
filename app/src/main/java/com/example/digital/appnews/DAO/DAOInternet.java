package com.example.digital.appnews.DAO;

import com.example.digital.appnews.Modelo.ContenedorNoticias;
import com.example.digital.appnews.Modelo.Noticia;
import com.example.digital.appnews.Util.ResultListener;
import com.example.digital.appnews.Vista.NoticiasFragment;

import java.util.ArrayList;
import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

public class DAOInternet extends DaoHelper {
        private ServiceNoticias serviceNoticias;
        private String categoria;
        private String buscar;
        private Call<ContenedorNoticias> call;
        private String country = "ar";
        private String languaje = "es";
        private static String apikey = "8b141017cf6848908829489044ed6f71";
        private ArrayList<String> categorias = new ArrayList<>();

        public DAOInternet(String categoria) {
            super("https://newsapi.org/V2/");
                serviceNoticias = retrofit.create(ServiceNoticias.class);
                this.categoria=categoria;
                categorias.add("Todo");
                categorias.add("business");
                categorias.add("sports");
                categorias.add("science");
                categorias.add("entertainment");
                categorias.add("technology");
                categorias.add("health");

        }

        public DAOInternet(String categoria,String buscar) {
            super("https://newsapi.org/V2/");
            serviceNoticias = retrofit.create(ServiceNoticias.class);
            this.categoria=categoria;
            this.buscar=buscar;
            categorias.add("Todo");
            categorias.add("business");
            categorias.add("sports");
            categorias.add("science");
            categorias.add("entertainment");
            categorias.add("technology");
            categorias.add("health");
        }

        public void obtenerNoticias(final ResultListener<ArrayList<Noticia>> listenerDelController) {
            if (categoria.equals(NoticiasFragment.KEY_TODO)) {
                call = serviceNoticias.getNewsContainerCountry(country,apikey);
            }
            else if(categoria.equals(NoticiasFragment.KEY_SEARCH))
            {
                call = serviceNoticias.getArticulosPorTema(buscar,languaje,apikey);
            }
            else if(categoria.equals(NoticiasFragment.KEY_CANAL))
            {
                call = serviceNoticias.getArticulosPorCanal(buscar,apikey);
            }
            else
            {
                call = serviceNoticias.getArticulosPorCategoria(country,categorias.get(Integer.valueOf(categoria)),apikey);
            }

            call.enqueue(new Callback<ContenedorNoticias>() {
                @Override
                public void onResponse(Call<ContenedorNoticias> call, Response<ContenedorNoticias> response) {

                    ContenedorNoticias newsContainer = response.body();
                    assert newsContainer != null;
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

