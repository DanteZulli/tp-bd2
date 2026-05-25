package com.grupoy.tpbd2.model;

public class Producto {
    
    private Integer codigo;
    private String descripcion;
    private String laboratorio;
    private boolean esMedicamento; 
    private Double precio;

    public Producto() {
    }

    public Producto(Integer codigo, String descripcion, String laboratorio, boolean esMedicamento, Double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.laboratorio = laboratorio;
        this.esMedicamento = esMedicamento;
        this.precio = precio;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public boolean isEsMedicamento() {
        return esMedicamento;
    }

    public void setEsMedicamento(boolean esMedicamento) {
        this.esMedicamento = esMedicamento;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

}