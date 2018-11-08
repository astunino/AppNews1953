package com.example.digital.appnews.Vista;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.digital.appnews.Modelo.Noticia;
import com.example.digital.appnews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticiaDetalleFragment extends Fragment {

    public static final String KEY_URL = "url";
    public static final String KEY_TITULO = "title";
    public static final String KEY_CATEGORIA = "categoria";


    public static NoticiaDetalleFragment fabrica(Noticia dato){
        NoticiaDetalleFragment fragment = new NoticiaDetalleFragment();

        Bundle bundle = new Bundle();

        bundle.putString(NoticiaDetalleFragment.KEY_URL, dato.getUrl());

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
        View view =  inflater.inflate(R.layout.fragment_noticia_detalle, container, false);

        Bundle bundle = getArguments();
        String url = bundle.getString(KEY_URL);

        WebView myWebView = (WebView) view.findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl(url);

        return view;
    }

}
