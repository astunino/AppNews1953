package com.example.digital.appnews.Vista;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.digital.appnews.Modelo.Noticia;
import com.example.digital.appnews.R;

import java.util.ArrayList;

public class NoticiasAdaptador extends RecyclerView.Adapter {

    private ArrayList<Noticia> noticias = new ArrayList<>();
    private NoticiaReceptor noticiaReceptor;
    private Context context;
    private AdapterListener listener;
    private Integer categoria;

    public NoticiasAdaptador(Context context, AdapterListener listener, ArrayList<Noticia> noticias,Integer categoria) {
        this.context = context;
        this.listener = listener;
        this.noticias = noticias;
        this.categoria = categoria;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_noticia, null);


        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Noticia noticia = noticias.get(position);

        MyViewHolder myViewHolder = (MyViewHolder) holder;

        context = ((MyViewHolder) holder).imageViewFoto.getContext();

        myViewHolder.bind(noticia);
    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }

    public interface NoticiaReceptor{
        void recibir(Noticia noticia);
    }

    public interface AdapterListener{
        void irDetalle(String nombre,Integer categoria);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageViewFoto;
        private TextView textViewTitulo;
        //private TextView textViewDescripcion;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageViewFoto = itemView.findViewById(R.id.imageViewFoto);
            textViewTitulo = itemView.findViewById(R.id.textViewTitulo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.irDetalle(noticias.get(getAdapterPosition()).getTitle(),categoria);
                }
            });
        }

        public void bind(Noticia noticia){

            if(noticia.getUrlToImagen()==null){
                imageViewFoto.setImageResource(R.drawable.no_image);
            }else{
                if(noticia.getUrlToImagen().startsWith("//")){
                    noticia.setUrlToImagen("http:"+noticia.getUrlToImagen());
                }
                Glide.with(context).load(noticia.getUrlToImagen()).into(imageViewFoto);
            }

            textViewTitulo.setText(noticia.getTitle());
        }

    }

}
