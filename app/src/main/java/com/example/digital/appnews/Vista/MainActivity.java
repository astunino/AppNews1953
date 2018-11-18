package com.example.digital.appnews.Vista;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.digital.appnews.Modelo.ViewPagerAdapterMain;
import com.example.digital.appnews.R;

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
                                cargarFragment();
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
                Toast.makeText(getApplicationContext(), "QUE PASO eh", Toast.LENGTH_LONG).show();
                return true;
        }
        return false;
    }

    public void cargarViewPager(){
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

    public void cargarFragment(){
        //invisibilizo el ViewPager y visivilizo el viewpger (es mejor cerrarlo pero no sé como)
        idContainer.setVisibility(View.VISIBLE);
        mainViewPager.setVisibility(View.INVISIBLE);

        //cargo el fragment favorito
        Fragment selectedFragment = new FavoritosFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.idContainer, selectedFragment);
        transaction.commit();
    }
}
