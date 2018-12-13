package com.example.digital.appnews.Vista;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.digital.appnews.DAO.Database.DaoNoticia;
import com.example.digital.appnews.DAO.Database.DatabaseHelper;
import com.example.digital.appnews.Modelo.Noticia;
import com.example.digital.appnews.R;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticiaDetalleFragment extends Fragment {

    public static final String KEY_URL = "url";
    public static final String KEY_TITULO = "title";
    public static final String KEY_CATEGORIA = "categoria";
    public static final String KEY_DESCRIPCION = "descripcion";
    public static final String KEY_IMAGEN = "imagen";
    public static final String KEY_BUSCAR = "buscar";
    private FloatingActionButton imageButtonVerMas, imageButtonFav;
    private String titulo,descripcion,url,imagen;

    public static NoticiaDetalleFragment fabrica(Noticia dato) {
        NoticiaDetalleFragment fragment = new NoticiaDetalleFragment();

        Bundle bundle = new Bundle();

        bundle.putString(NoticiaDetalleFragment.KEY_URL, dato.getUrl());
        bundle.putString(NoticiaDetalleFragment.KEY_TITULO, dato.getTitle());
        bundle.putString(NoticiaDetalleFragment.KEY_DESCRIPCION, dato.getContent());
        bundle.putString(NoticiaDetalleFragment.KEY_IMAGEN, dato.getUrlToImage());
        fragment.setArguments(bundle);

        return fragment;
    }

    public NoticiaDetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_noticia_detalle, container, false);

        Bundle bundle = getArguments();
        url = bundle.getString(KEY_URL);
        titulo = bundle.getString(KEY_TITULO);
        descripcion = bundle.getString(KEY_DESCRIPCION);
        imagen = bundle.getString(KEY_IMAGEN);

        ImageView imageViewDetalle = view.findViewById(R.id.imageViewDetalle);
        TextView textViewTitulo = view.findViewById(R.id.textViewTitulo);
        TextView textViewDescripcion = view.findViewById(R.id.textViewDescripcion);
        imageButtonVerMas = view.findViewById(R.id.imageButtonVerMas);
        imageButtonFav = view.findViewById(R.id.imageButtonFav);

        if (imagen == null) {
            imageViewDetalle.setImageResource(R.drawable.no_image);
            imageViewDetalle.setVisibility(View.GONE);
        } else {
            if (imagen.startsWith("//")) {
                imagen = ("http:" + imagen);
            }
            Glide.with(getActivity()).load(imagen).into(imageViewDetalle);
            imageViewDetalle.setVisibility(View.VISIBLE);
        }

        textViewTitulo.setText(titulo);

        DaoNoticia daoNoticia = DatabaseHelper
                .getInstance(getContext())
                .getDaoNoticia();

        Noticia noticiaExiste=daoNoticia.buscarNoticiaTitulo(titulo);
        if(noticiaExiste!=null){
            imageButtonFav.setImageResource(R.drawable.heartred);
            descripcion=noticiaExiste.getDescription();
        }

        String part1 = ""; //se inicializa esta variable ya que puede no ser sobreescrita si no se entra al if de la siguiente línea
        if (descripcion != null) { //La noticia puede no tener descripción. Este if, previene esa situación.
            if (descripcion.length() <= 250) {
                part1 = descripcion.substring(0, descripcion.length() - 10);
            } else {
                part1 = descripcion.substring(0, 250);
            }
        }
        textViewDescripcion.setText(part1 + "...");

        imageButtonVerMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), ViewPageActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString(NoticiaDetalleFragment.KEY_URL, url);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        imageButtonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(FirebaseAuth.getInstance().getCurrentUser()==null){
                    Snackbar mySnackbar = Snackbar.make(v, "Debe estar logueado para agregar un FAV", Snackbar.LENGTH_LONG);
                    mySnackbar.setAction("LOG IN", new LoginListener());
                    mySnackbar.show();
                }else{
                    DaoNoticia daoNoticia = DatabaseHelper
                            .getInstance(getContext())
                            .getDaoNoticia();

                    Noticia noticia = new Noticia(titulo,descripcion,url,imagen);
                    List<Noticia> noticias = daoNoticia.buscarNoticias();

                    Noticia noticiaExiste=daoNoticia.buscarNoticiaTitulo(titulo);

                    if(noticiaExiste!=null){
                        daoNoticia.borrarNoticia(titulo);
                        imageButtonFav.setImageResource(R.drawable.heart);
                        Snackbar mySnackbar = Snackbar.make(v, "Se eliminó la noticia de FAVORITOS", Snackbar.LENGTH_LONG);
                        mySnackbar.show();
                    }else{
                        imageButtonFav.setImageResource(R.drawable.heartred);
                        daoNoticia.insertarNoticia(noticia);
                        Snackbar mySnackbar = Snackbar.make(v, "Se agregó la noticia a FAVORITOS", Snackbar.LENGTH_LONG);
                        mySnackbar.show();
                    }
                }
            }
        });

        return view;
    }

    public class LoginListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getContext(), VentanaRegistro.class);
            startActivity(intent);
        }
    }

}
