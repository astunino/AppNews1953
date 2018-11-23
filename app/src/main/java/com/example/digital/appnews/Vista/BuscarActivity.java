package com.example.digital.appnews.Vista;

import android.content.DialogInterface;
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

public class BuscarActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private FrameLayout contenedor;
    private Integer categoria=7;
    private String buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        editTextSearch = findViewById(R.id.editTextSearch);
        contenedor = findViewById(R.id.contenedor);

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

                            buscar=editTextSearch.getText().toString();

                            Fragment selectedFragment = new NoticiasFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(NoticiasFragment.KEY_CATEGORIA, categoria);
                            bundle.putString(NoticiasFragment.KEY_BUSCAR,buscar);
                            selectedFragment.setArguments(bundle);

                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.contenedor, selectedFragment);
                            transaction.commit();

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
}
