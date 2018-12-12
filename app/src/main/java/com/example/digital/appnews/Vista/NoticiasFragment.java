package com.example.digital.appnews.Vista;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.digital.appnews.Controlador.Controlador;
import com.example.digital.appnews.Modelo.Noticia;
import com.example.digital.appnews.R;
import com.example.digital.appnews.Util.ResultListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoticiasFragment extends Fragment implements NoticiasAdaptador.AdapterListener {

    private String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Noticia> noticias = new ArrayList<>();
    private RecyclerView recyclerViewNoticias;
    private NoticiasAdaptador adaptador;
    private Integer categoria = 0;
    private String buscar;

    public static final String KEY_TODO = "0";
    public static final String KEY_BUSINESS = "1";
    public static final String KEY_SPORTS = "2";
    public static final String KEY_SCIENCE = "3";
    public static final String KEY_ENTERTAINMENT = "4";
    public static final String KEY_TECHNOLOGY = "5";
    public static final String KEY_HEALTH = "6";
    public static final String KEY_SEARCH = "7";
    public static final String KEY_CANAL = "8";
    public static final String KEY_CATEGORIA = "categoria";
    public static final String KEY_BUSCAR = "buscar";


    public NoticiasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Obtengo el Bundle
        Bundle bundle = getArguments();

        //Obtengo los datos del Bundle
        categoria = bundle.getInt(KEY_CATEGORIA);

        if(categoria==7||categoria==8){
            buscar = bundle.getString(KEY_BUSCAR);
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_noticias, container, false);
        //Obtengo el Recycler View del layout
        recyclerViewNoticias = view.findViewById(R.id.recyclerViewNoticias);

        //Genero una instancia del controlador y lo inicializo con todas las noticias (por defecto, sin filtros)
        Controlador controlador = new Controlador(KEY_TODO);

        //"sobreescribo" la instancia del controlador de acuerdo a la categoría que me pasaron por el bundle
        switch (categoria) {
            case 0:
                controlador = new Controlador(KEY_TODO);
                break;
            case 1:
                controlador = new Controlador(KEY_BUSINESS);
                break;
            case 2:
                controlador = new Controlador(KEY_SPORTS);
                break;
            case 3:
                controlador = new Controlador(KEY_SCIENCE);
                break;
            case 4:
                controlador = new Controlador(KEY_ENTERTAINMENT);
                break;
            case 5:
                controlador = new Controlador(KEY_TECHNOLOGY);
                break;
            case 6:
                controlador = new Controlador(KEY_HEALTH);
                break;
            case 7:
                controlador = new Controlador(KEY_SEARCH,buscar);
                break;
            case 8:
                controlador = new Controlador(KEY_CANAL,buscar);
                break;
        }

        Context context = getContext();
        controlador.obtenerNoticias(context, new ResultListener<ArrayList<Noticia>>() {
            @Override
            public void finish(ArrayList<Noticia> noticias) {
                traerDatos(noticias);
            }
        });
        return view;
    }

    public void traerDatos(ArrayList<Noticia> noticias) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerViewNoticias.setHasFixedSize(true);

        adaptador = new NoticiasAdaptador(getActivity(), this, noticias, categoria);
        recyclerViewNoticias.setAdapter(adaptador);
        recyclerViewNoticias.setLayoutManager(layoutManager);
    }

    @Override
    public void irDetalle(String titulo, Integer categoria) {
        Context context = getContext();
        NoticiasAdaptador.AdapterListener listener = (NoticiasAdaptador.AdapterListener) context;

        listener.irDetalle(titulo, categoria);
    }

    //Este método me "fabrica" un NoticiasFragment filtrado por la categoría que se le pasa por argumento
    public static NoticiasFragment noticiasFragment(Integer categoria) {
        NoticiasFragment fragment = new NoticiasFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(NoticiasFragment.KEY_CATEGORIA, categoria);
        fragment.setArguments(bundle);
        return fragment;
    }

}
