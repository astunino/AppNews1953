package com.example.digital.appnews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digital.appnews.Modelo.Busqueda;


/**
 * A simple {@link Fragment} subclass.
 */
public class BusquedaFragment extends Fragment {


    public BusquedaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_busqueda, container, false);
    }

    public class BuscadorComparator implements Comparator<Busqueda>
    {
        public int compare(Fish left, Fish right) {
            return left.name.compareTo(right.name);
        }
    }

    Collections.sort(fishes, new FishNameComparator());
}
