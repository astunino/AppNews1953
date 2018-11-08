package com.example.digital.appnews.DAO;

import com.example.digital.appnews.Modelo.Noticia;
import java.util.ArrayList;

public class DAOArchivo {

    private ArrayList<Noticia> noticias = new ArrayList<>();

    public ArrayList<Noticia> obtenerListaDeNoticiasDeArchivo(){
        Noticia noticia1 = new Noticia("Titulo 1 Archivo","Descripcion 1");
        Noticia noticia2 = new Noticia("Titulo 2 Archivo","Descripcion 2");
        Noticia noticia3 = new Noticia("Titulo 3 Archivo","Descripcion 3");

        noticias.add(noticia1);
        noticias.add(noticia2);
        noticias.add(noticia3);

        return noticias;
    }
}
