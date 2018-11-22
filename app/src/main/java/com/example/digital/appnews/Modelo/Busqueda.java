package com.example.digital.appnews.Modelo;

public class Busqueda {

    private String busqueda;
    private String cantidad;

    public Busqueda() {

    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Busqueda{" +
                "busqueda='" + busqueda + '\'' +
                '}';
    }
}
