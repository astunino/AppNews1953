package com.example.digital.appnews.Vista;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.digital.appnews.Controlador.Controlador;
import com.example.digital.appnews.DAO.Database.DaoNoticia;
import com.example.digital.appnews.DAO.Database.DatabaseHelper;
import com.example.digital.appnews.Modelo.Noticia;
import com.example.digital.appnews.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NoticiasAdaptador.AdapterListener {

    private ViewPager mainViewPager;
    private FrameLayout idContainer;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbarId);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottomNavigationViewId);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_inicio:
                                tabLayout.setVisibility(View.VISIBLE);
                                cargarViewPager();
                                break;
                            case R.id.navigation_favorito:
                                tabLayout.setVisibility(View.GONE);

                                Fragment fragment=null;

                                DaoNoticia daoNoticia = DatabaseHelper
                                        .getInstance(getApplicationContext())
                                        .getDaoNoticia();

                                List<Noticia> noticias = daoNoticia.buscarNoticias();
                                if(noticias.isEmpty()|| FirebaseAuth.getInstance().getCurrentUser()==null){
                                    fragment = new FavoritosFragment();
                                }else{
                                    fragment = new NoticiasFragment();
                                    Bundle bundle = new Bundle();
                                    bundle.putInt(NoticiasFragment.KEY_CATEGORIA,9);
                                    fragment.setArguments(bundle);
                                }

                                cargarFragment(fragment);
                                break;

                            case R.id.navigation_login:
                                tabLayout.setVisibility(View.GONE);
                                Intent intent = new Intent(MainActivity.this, VentanaRegistro.class);
                                startActivity(intent);

                                break;
                        }

                        return true;
                    }
                });


        //Busco el ViewPager en el Layout
        mainViewPager = findViewById(R.id.mainViewPager);
        idContainer = findViewById(R.id.idContainer);

        cargarViewPager();

        //Agrego el TabLayout
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mainViewPager);
    }

    @Override
    public void irDetalle(String titulo, Integer categoria) {

        Intent intent = new Intent(MainActivity.this, DetalleActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString(NoticiaDetalleFragment.KEY_TITULO, titulo);
        bundle.putInt(NoticiaDetalleFragment.KEY_CATEGORIA, categoria);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_search:
                Intent buscarActivity = new Intent(MainActivity.this, BuscarActivity.class);
                startActivity(buscarActivity);
                return true;
        }
        return false;
    }

    public void cargarViewPager() {
        //invisibilizo el Fragment y visivilizo el viewpger (es mejor cerrarlo pero no sé como)
        idContainer.setVisibility(View.INVISIBLE);
        mainViewPager.setVisibility(View.VISIBLE);

        //Confirguro el Adapter del ViewPager buscado en la línea anterior

        ViewPagerAdapterMain adapterMain = new ViewPagerAdapterMain(getSupportFragmentManager());
        mainViewPager.setAdapter(adapterMain);

        //Configuro la vista inicial
        mainViewPager.setCurrentItem(0);
        mainViewPager.setClipToPadding(false);
    }

    public void cargarFragment(Fragment fragment) {
        //invisibilizo el ViewPager y visivilizo el viewpger (es mejor cerrarlo pero no sé como)
        idContainer.setVisibility(View.VISIBLE);
        mainViewPager.setVisibility(View.INVISIBLE);

        //cargo el fragment favorito
        Fragment selectedFragment =fragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.idContainer, selectedFragment);
        transaction.commit();
    }
}
