package com.example.digital.appnews.Modelo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ContenedorNoticias {

    @SerializedName("articles")
    private ArrayList<Noticia> misNoticias;

    public ArrayList<Noticia> getMisNoticias() {
        return misNoticias;
    }


}
