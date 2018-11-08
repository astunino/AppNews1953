package com.example.digital.appnews.Vista;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toolbar;

import com.example.digital.appnews.R;

public class MainActivity extends AppCompatActivity implements NoticiasAdaptador.AdapterListener {

    private RecyclerView recyclerViewCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbarId);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottomNavigationViewId);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.navigation_inicio:
                                selectedFragment = new NoticiasFragment();
                                break;
                            case R.id.navigation_favorito:
                                selectedFragment = new FavoritosFragment();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.idContainer, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });


        NoticiasFragment noticiasFragment = new NoticiasFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.idContainer, noticiasFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void irDetalle(String titulo){
        //public void irDetalle(){

        Intent intent = new Intent(MainActivity.this, DetalleActivity.class);

        Bundle bundle = new Bundle();
        //bundle.putString(NoticiaDetalleFragment.KEY_TITULO, titulo);
        bundle.putString(NoticiaDetalleFragment.KEY_TITULO, "titulo");

        intent.putExtras(bundle);
        startActivity(intent);
    }
}
