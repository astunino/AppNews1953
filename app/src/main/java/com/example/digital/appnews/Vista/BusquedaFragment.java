package com.example.digital.appnews.Vista;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Comparator;


/**
 * A simple {@link Fragment} subclass.
 */
public class BusquedaFragment extends Fragment {

    private FirebaseDatabase mDatabase;
    private ArrayList<Busqueda> listadoBuscadas = new ArrayList<>();
    private TextView busqueda1,busqueda2,busqueda3,busqueda4,busqueda5;
    private DataSnapshot dataSnapshot;

    public BusquedaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_busqueda, container, false);

        busqueda1 = view.findViewById(R.id.busqueda1);
        busqueda2 = view.findViewById(R.id.busqueda2);
        busqueda3 = view.findViewById(R.id.busqueda3);
        busqueda4 = view.findViewById(R.id.busqueda4);
        busqueda5 = view.findViewById(R.id.busqueda5);

        mDatabase = FirebaseDatabase.getInstance();

        DatabaseReference raiz = mDatabase.getReference("busqueda");
        //DatabaseReference busqueda = raiz.child("busqueda");

        raiz.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapShot : dataSnapshot.getChildren()){

                    Busqueda busqueda = childSnapShot.getValue(Busqueda.class);

                    listadoBuscadas.add(busqueda);
                }

                Collections.sort(listadoBuscadas, new BuscadorComparator());

                busqueda1.setText("#" + listadoBuscadas.get(0).getBusqueda());
                busqueda2.setText("#" + listadoBuscadas.get(1).getBusqueda());
                busqueda3.setText("#" + listadoBuscadas.get(2).getBusqueda());
                busqueda4.setText("#" + listadoBuscadas.get(3).getBusqueda());
                busqueda5.setText("#" + listadoBuscadas.get(4).getBusqueda());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    public class BuscadorComparator implements Comparator<Busqueda>
    {
        public int compare(Busqueda left, Busqueda right) {
            return right.getCantidad().compareTo(left.getCantidad());
        }
    }

}
