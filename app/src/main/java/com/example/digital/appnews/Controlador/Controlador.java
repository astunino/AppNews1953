package com.example.digital.appnews.Controlador;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.digital.appnews.DAO.DAOArchivo;
import com.example.digital.appnews.DAO.DAOInternet;
import com.example.digital.appnews.Modelo.Noticia;
import com.example.digital.appnews.Util.ResultListener;
import com.example.digital.appnews.Vista.NoticiasFragment;

import java.util.ArrayList;
import java.util.List;


public class Controlador{

    private String categoria;

    public Controlador(){
        this.categoria=NoticiasFragment.KEY_TODO;
    }

    public Controlador(String categoria){
        this.categoria=categoria;
    }

    public void obtenerNoticias(final ResultListener<ArrayList<Noticia>> listenerView){

        if(hayInternet()){
            DAOInternet daoInternet = new DAOInternet(categoria);
            daoInternet.obtenerNoticias(new ResultListener<ArrayList<Noticia>>(){

                @Override
                public void finish(ArrayList<Noticia> result) {
                    listenerView.finish(result);
                }
            });


        }else{
            DAOArchivo daoArchivo = new DAOArchivo();
            daoArchivo.obtenerListaDeNoticiasDeArchivo();
        }
    }

    public Boolean hayInternet() {
        return true;
    }
}
