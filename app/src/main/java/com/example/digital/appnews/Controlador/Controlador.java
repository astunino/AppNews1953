package com.example.digital.appnews.Controlador;

import android.content.Context;
import android.widget.Toast;

import com.example.digital.appnews.DAO.DAOArchivo;
import com.example.digital.appnews.DAO.DAOInternet;
import com.example.digital.appnews.Modelo.Noticia;
import com.example.digital.appnews.Util.ResultListener;
import com.example.digital.appnews.Util.Util;
import com.example.digital.appnews.Vista.NoticiasFragment;

import java.util.ArrayList;


public class Controlador{

    private String categoria;
    private String buscar;

    public Controlador(){
        this.categoria=NoticiasFragment.KEY_TODO;
    }

    public Controlador(String categoria){
        this.categoria=categoria;
    }

    public Controlador(String categoria,String buscar){
        this.categoria=categoria;
        this.buscar=buscar;
    }

    public void obtenerNoticias(Context context, final ResultListener<ArrayList<Noticia>> listenerView){
        if(Util.isOnline(context)){
            DAOInternet daoInternet = new DAOInternet(categoria,buscar);
            daoInternet.obtenerNoticias(new ResultListener<ArrayList<Noticia>>(){

                @Override
                public void finish(ArrayList<Noticia> result) {
                    listenerView.finish(result);
                }
            });


        }else{
            DAOArchivo daoArchivo = new DAOArchivo();
            daoArchivo.obtenerListaDeNoticiasDeArchivo();
            Toast.makeText(context,"No hay conexión internet", Toast.LENGTH_SHORT).show();
        }
    }
}
