package com.example.digital.appnews.Modelo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class Noticia {

    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
    private Boolean imageNull=false;

    public Noticia(String author, String title, String description, String url, String urlToImage, String publishedAt, String content) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        if(urlToImage.startsWith("//")){
            urlToImage = "http:"+urlToImage;
        }
        if(urlToImage.equals("null")){
            urlToImage="https://desaku636.files.wordpress.com/2017/05/ilustrasi-koran.jpg?w=693&h=312&crop=1";
        }
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public Noticia(String title,String description){
        this.title=title;
        this.description=description;
        this.urlToImage="null";
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImagen() {
        return urlToImage;
    }

    public void setUrlToImagen(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getpublishedAt() {
        return publishedAt;
    }

    public void setpublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getImageNull() {
        return imageNull;
    }
}
