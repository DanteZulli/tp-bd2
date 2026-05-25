package com.grupoy.tpbd2.model;

public class Sucursal {
    
    private Integer idSucursal;
    private String puntoVenta; // Ej: "0001"
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;

    public Sucursal() {
    }

    public Sucursal(Integer idSucursal, String puntoVenta, String calle, Integer numero, String localidad, String provincia) {
        this.idSucursal = idSucursal;
        this.puntoVenta = puntoVenta;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getPuntoVenta() {
        return puntoVenta;
    }

    public void setPuntoVenta(String puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

}