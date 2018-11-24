package com.example.digital.appnews.Vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digital.appnews.Modelo.Busqueda;
import com.example.digital.appnews.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class BuscarActivity extends AppCompatActivity implements BusquedaFragment.OnFragmentInterface {

    private EditText editTextSearch;
    private FrameLayout contenedor;
    private Integer categoria=7;
    private String buscar;
    private FirebaseDatabase mDatabase;
    private ArrayList<Busqueda> listadoBuscadas = new ArrayList<>();
    private DatabaseReference tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        editTextSearch = findViewById(R.id.editTextSearch);
        contenedor = findViewById(R.id.contenedor);

        mDatabase = FirebaseDatabase.getInstance();

        tt = mDatabase.getReference("tt");

        tt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapShot : dataSnapshot.getChildren()){

                    Busqueda busqueda = childSnapShot.getValue(Busqueda.class);

                    listadoBuscadas.add(busqueda);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        editTextSearch.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:

                            buscar=editTextSearch.getText().toString().toUpperCase();

                            Busqueda buscado = existeBusqueda(buscar);

                            if(buscado!=null){
                                tt.child(buscar).setValue(buscado);
                            }else{
                                tt.child(buscar).setValue(new Busqueda(buscar,"1"));
                            }

                            reemplazarFragment(buscar);

                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        Fragment selectedFragment = new BusquedaFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor, selectedFragment);
        transaction.commit();
    }

    public Busqueda existeBusqueda(String busqueda){

        for(int i=0;i<listadoBuscadas.size();i++){
            if(listadoBuscadas.get(i).getBusqueda().equals(busqueda)){
                listadoBuscadas.get(i).setCantidad(String.valueOf(Integer.valueOf(listadoBuscadas.get(i).getCantidad())+1));
                return listadoBuscadas.get(i);
            }
        }

        return null;
    }

    public void reemplazarFragment(String buscar){
        Fragment selectedFragment = new NoticiasFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(NoticiasFragment.KEY_CATEGORIA,categoria);
        bundle.putString(NoticiasFragment.KEY_BUSCAR,buscar);
        selectedFragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor, selectedFragment);
        transaction.commit();
    }

    @Override
    public void click(String buscar){

        Busqueda buscado = existeBusqueda(buscar);

        if(buscado!=null){
            tt.child(buscar).setValue(buscado);
        }else{
            tt.child(buscar).setValue(new Busqueda(buscar,"1"));
        }

        reemplazarFragment(buscar);
    }

    @Override
    public void clickCanal(String source){
        reemplazarFragment(source);
    }

}
