package com.example.digital.appnews.DAO.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.digital.appnews.Modelo.Noticia;

import java.util.List;

@Dao
public interface DaoNoticia {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertarNoticia(Noticia noticia);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertarNoticias(List<Noticia> noticias);

    @Query("SELECT * FROM Noticia")
    List<Noticia> buscarNoticias();

    @Query("SELECT * FROM Noticia WHERE title LIKE:titulo")
    Noticia buscarNoticiaTitulo(String titulo);

    @Query("DELETE FROM Noticia WHERE title LIKE:titulo")
    void borrarNoticia(String titulo);

}
