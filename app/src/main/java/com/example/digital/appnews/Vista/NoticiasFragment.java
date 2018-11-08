package com.example.digital.appnews.Vista;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.digital.appnews.Controlador.Controlador;
import com.example.digital.appnews.DAO.HttpHandler;
import com.example.digital.appnews.Modelo.Noticia;
import com.example.digital.appnews.R;
import com.example.digital.appnews.Util.ResultListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoticiasFragment extends Fragment implements NoticiasAdaptador.AdapterListener{

    private String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Noticia> noticias = new ArrayList<>();
    private RecyclerView recyclerViewNoticias;
    private NoticiasAdaptador adaptador;

    public static final String KEY_TODO = "0";
    public static final String KEY_BUSINESS = "1";
    public static final String KEY_SPORTS = "2";
    public static final String KEY_SCIENCE = "3";
    public static final String KEY_ENTERTAINMENT = "4";
    public static final String KEY_TECHNOLOGY = "5";
    public static final String KEY_HEALTH = "6";

    public NoticiasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_noticias, container, false);

        recyclerViewNoticias = view.findViewById(R.id.recyclerViewNoticias);

        final ImageButton imageButtonBussines = view.findViewById(R.id.imageButtonBussines);
        final ImageButton imageButtonSports = view.findViewById(R.id.imageButtonSports);
        final ImageButton imageButtonScience = view.findViewById(R.id.imageButtonScience);
        final ImageButton imageButtonEntertainment = view.findViewById(R.id.imageButtonEntertainment);
        final ImageButton imageButtonTechnology = view.findViewById(R.id.imageButtonTechnology);
        final ImageButton imageButtonHealth = view.findViewById(R.id.imageButtonHealth);

        Controlador controlador = new Controlador(KEY_TODO);
        controlador.obtenerNoticias(new ResultListener<ArrayList<Noticia>>() {
            @Override
            public void finish(ArrayList<Noticia> noticias) {
                traerDatos(noticias);
            }
        });

        imageButtonBussines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageButtonBussines.setBackgroundColor(getResources().getColor(R.color.selected));
                imageButtonEntertainment.setBackgroundColor(Color.TRANSPARENT);
                imageButtonHealth.setBackgroundColor(Color.TRANSPARENT);
                imageButtonScience.setBackgroundColor(Color.TRANSPARENT);
                imageButtonSports.setBackgroundColor(Color.TRANSPARENT);
                imageButtonTechnology.setBackgroundColor(Color.TRANSPARENT);

                Controlador controlador = new Controlador(KEY_BUSINESS);
                controlador.obtenerNoticias(new ResultListener<ArrayList<Noticia>>() {
                    @Override
                    public void finish(ArrayList<Noticia> noticias) {
                        traerDatos(noticias);
                    }
                });
            }
        });

        imageButtonSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageButtonBussines.setBackgroundColor(Color.TRANSPARENT);
                imageButtonEntertainment.setBackgroundColor(Color.TRANSPARENT);
                imageButtonHealth.setBackgroundColor(Color.TRANSPARENT);
                imageButtonScience.setBackgroundColor(Color.TRANSPARENT);
                imageButtonSports.setBackgroundColor(getResources().getColor(R.color.selected));
                imageButtonTechnology.setBackgroundColor(Color.TRANSPARENT);

                Controlador controlador = new Controlador(KEY_SPORTS);
                controlador.obtenerNoticias(new ResultListener<ArrayList<Noticia>>() {
                    @Override
                    public void finish(ArrayList<Noticia> noticias) {
                        traerDatos(noticias);
                    }
                });
            }
        });

        imageButtonScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageButtonBussines.setBackgroundColor(Color.TRANSPARENT);
                imageButtonEntertainment.setBackgroundColor(Color.TRANSPARENT);
                imageButtonHealth.setBackgroundColor(Color.TRANSPARENT);
                imageButtonScience.setBackgroundColor(getResources().getColor(R.color.selected));
                imageButtonSports.setBackgroundColor(Color.TRANSPARENT);
                imageButtonTechnology.setBackgroundColor(Color.TRANSPARENT);

                Controlador controlador = new Controlador(KEY_SCIENCE);
                controlador.obtenerNoticias(new ResultListener<ArrayList<Noticia>>() {
                    @Override
                    public void finish(ArrayList<Noticia> noticias) {
                        traerDatos(noticias);
                    }
                });
            }
        });

        imageButtonEntertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageButtonBussines.setBackgroundColor(Color.TRANSPARENT);
                imageButtonEntertainment.setBackgroundColor(getResources().getColor(R.color.selected));
                imageButtonHealth.setBackgroundColor(Color.TRANSPARENT);
                imageButtonScience.setBackgroundColor(Color.TRANSPARENT);
                imageButtonSports.setBackgroundColor(Color.TRANSPARENT);
                imageButtonTechnology.setBackgroundColor(Color.TRANSPARENT);

                Controlador controlador = new Controlador(KEY_ENTERTAINMENT);
                controlador.obtenerNoticias(new ResultListener<ArrayList<Noticia>>() {
                    @Override
                    public void finish(ArrayList<Noticia> noticias) {
                        traerDatos(noticias);
                    }
                });
            }
        });

        imageButtonTechnology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageButtonBussines.setBackgroundColor(Color.TRANSPARENT);
                imageButtonEntertainment.setBackgroundColor(Color.TRANSPARENT);
                imageButtonHealth.setBackgroundColor(Color.TRANSPARENT);
                imageButtonScience.setBackgroundColor(Color.TRANSPARENT);
                imageButtonSports.setBackgroundColor(Color.TRANSPARENT);
                imageButtonTechnology.setBackgroundColor(getResources().getColor(R.color.selected));

                Controlador controlador = new Controlador(KEY_TECHNOLOGY);
                controlador.obtenerNoticias(new ResultListener<ArrayList<Noticia>>() {
                    @Override
                    public void finish(ArrayList<Noticia> noticias) {
                        traerDatos(noticias);
                    }
                });
            }
        });

        imageButtonHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageButtonBussines.setBackgroundColor(Color.TRANSPARENT);
                imageButtonEntertainment.setBackgroundColor(Color.TRANSPARENT);
                imageButtonHealth.setBackgroundColor(getResources().getColor(R.color.selected));
                imageButtonScience.setBackgroundColor(Color.TRANSPARENT);
                imageButtonSports.setBackgroundColor(Color.TRANSPARENT);
                imageButtonTechnology.setBackgroundColor(Color.TRANSPARENT);


                Controlador controlador = new Controlador(KEY_HEALTH);
                controlador.obtenerNoticias(new ResultListener<ArrayList<Noticia>>() {
                    @Override
                    public void finish(ArrayList<Noticia> noticias) {
                        traerDatos(noticias);
                    }
                });
            }
        });
        return view;
    }

    public void traerDatos(ArrayList<Noticia> noticias){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerViewNoticias.setHasFixedSize(true);

        adaptador = new NoticiasAdaptador(getActivity(),this, noticias);
        recyclerViewNoticias.setAdapter(adaptador);
        recyclerViewNoticias.setLayoutManager(layoutManager);
    }

    @Override
    public void irDetalle(String titulo) {
        Context context = getContext();
        NoticiasAdaptador.AdapterListener listener = (NoticiasAdaptador.AdapterListener) context;

        listener.irDetalle(titulo);
    }
}
