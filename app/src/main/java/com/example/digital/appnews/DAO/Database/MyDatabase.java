package com.example.digital.appnews.DAO.Database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.digital.appnews.Modelo.Noticia;


@Database(entities = {Noticia.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract DaoNoticia getDaoNoticia();
}
