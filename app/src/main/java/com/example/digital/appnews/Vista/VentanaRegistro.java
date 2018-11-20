package com.example.digital.appnews.Vista;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digital.appnews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VentanaRegistro extends Fragment {


    public VentanaRegistro() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ventana_registro, container, false);
    }

}
