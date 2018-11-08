package com.example.digital.appnews.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.digital.appnews.Controlador.Controlador;
import com.example.digital.appnews.Modelo.Noticia;
import com.example.digital.appnews.R;
import com.example.digital.appnews.Util.ResultListener;

import java.util.ArrayList;

public class DetalleActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private String titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        titulo = bundle.getString(NoticiaDetalleFragment.KEY_TITULO);

        viewPager = findViewById(R.id.viewPager);

        ArrayList<Noticia> noticias = new ArrayList<>();

        Controlador controlador = new Controlador(NoticiasFragment.KEY_TODO);
        controlador.obtenerNoticias(new ResultListener<ArrayList<Noticia>>() {
            @Override
            public void finish(ArrayList<Noticia> noticias) {
                // Adapter
                ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), noticias);
                viewPager.setAdapter(adapter);

                int posicion=0;

                for(int i=0;i<noticias.size();i++){
                    if(noticias.get(i).getTitle().equals(titulo)){
                        posicion=i;
                    }
                }

                viewPager.setCurrentItem(posicion);
                viewPager.setClipToPadding(false);
            }
        });

    }
}
