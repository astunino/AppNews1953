package com.example.digital.appnews.Vista;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.digital.appnews.R;

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


        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input

                buscar=editable.toString();

                Fragment selectedFragment = new NoticiasFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(NoticiasFragment.KEY_CATEGORIA, categoria);
                bundle.putString(NoticiasFragment.KEY_BUSCAR,buscar);
                selectedFragment.setArguments(bundle);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, selectedFragment);
                transaction.commit();
            }
        });

    }
}
