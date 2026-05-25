package com.grupoy.tpbd2.model;

public class ObraSocial {
    
    private Integer idObraSocial;
    private String nombre;

    // Constructor vacío
    public ObraSocial() {
    }

    // Constructor con parámetros
    public ObraSocial(Integer idObraSocial, String nombre) {
        this.idObraSocial = idObraSocial;
        this.nombre = nombre;
    }

    public Integer getIdObraSocial() {
        return idObraSocial;
    }

    public void setIdObraSocial(Integer idObraSocial) {
        this.idObraSocial = idObraSocial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}