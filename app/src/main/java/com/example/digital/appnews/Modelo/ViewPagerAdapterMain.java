package com.example.digital.appnews.Modelo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.digital.appnews.Vista.NoticiasFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapterMain extends FragmentPagerAdapter {
    private List<Fragment> datos;

    public ViewPagerAdapterMain(FragmentManager fm) {
        super(fm);

        //Armo la lista de Framents. Cada fragment estará filtrado por categoría y será una página del ViewPager
        datos = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            NoticiasFragment fragment = NoticiasFragment.noticiasFragment(i);
            datos.add(fragment);
        }
    }


    @Override
    public Fragment getItem(int position) {
        return datos.get(position);
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    //Este método simplemente devuelve el nombre de las pestañas del TabLayout
    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "#Noticias";
            case 1:
                return "#Finanzas";
            case 2:
                return "#Deportes";
            case 3:
                return "#Ciencia";
            case 4:
                return "#Entretenimiento";
            case 5:
                return "#Tecnología";
            case 6:
                return "#Salud";
        }
        return null;
    }

}
