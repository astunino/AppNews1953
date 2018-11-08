package com.example.digital.appnews.Vista;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.digital.appnews.Modelo.Noticia;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> datos;
    private List<Noticia> objetos;
    private List<String> objetosString;

   public ViewPagerAdapter(FragmentManager fm, List<Noticia> objetos) {
        super(fm);

        // List Fragment
        datos = new ArrayList<>();
        for(Noticia dato: objetos){
            NoticiaDetalleFragment fragment = NoticiaDetalleFragment.fabrica(dato);
            datos.add(fragment);
        }

        this.objetos = objetos;
    }

    /*public ViewPagerAdapter(FragmentManager fm, List<String> objetos) {
        super(fm);

        // List Fragment
        datos = new ArrayList<>();
        for(String dato: objetos){
            NoticiaDetalleFragment fragment = NoticiaDetalleFragment.fabrica(dato);
            datos.add(fragment);
        }

        this.objetosString = objetos;
    }*/

    @Override
    public Fragment getItem(int i) {
        return datos.get(i);
    }

    @Override
    public int getCount() {
        return datos.size();
    }

}
