package com.example.digital.appnews.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.digital.appnews.Controlador.Controlador;
import com.example.digital.appnews.Modelo.Noticia;
import com.example.digital.appnews.R;
import com.example.digital.appnews.Util.ResultListener;

import java.util.ArrayList;

public class DetalleActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private FloatingActionButton imageButtonVerMas,imageButtonFav;
    private String titulo,buscar;
    private Integer categoria;
    private String url;
    private Controlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Toolbar toolbar = findViewById(R.id.toolbarId);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        titulo = bundle.getString(NoticiaDetalleFragment.KEY_TITULO);
        categoria = bundle.getInt(NoticiaDetalleFragment.KEY_CATEGORIA);
        buscar = bundle.getString(NoticiaDetalleFragment.KEY_BUSCAR);

        viewPager = findViewById(R.id.viewPager);

        imageButtonVerMas = findViewById(R.id.imageButtonVerMas);

        final ArrayList<Noticia> noticias = new ArrayList<>();

        if(categoria==7||categoria==8){
            controlador = new Controlador(String.valueOf(categoria),buscar);
        }else{
            controlador = new Controlador(String.valueOf(categoria));
        }

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
                        url=noticias.get(i).getUrl();
                    }
                }
                viewPager.setCurrentItem(posicion);
                viewPager.setClipToPadding(false);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_detalle, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Mirate esta nota en Noticias Now -> " + titulo);
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);
                return true;
        }
        return false;
    }

}
