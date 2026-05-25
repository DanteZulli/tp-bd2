package com.grupoy.tpbd2.model;

public class Cliente {

    private Integer idCliente;
    private String apellido;
    private String nombre;
    private String dni;

    // Domicilio
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;

    // Cobertura
    private boolean esPrivado;
    private ObraSocial obraSocial; // Objeto anidado
    private String numeroAfiliado; // Requerimiento del dominio

    // Constructor vacío
    public Cliente() {
    }

    // Constructor con parámetros
    public Cliente(Integer idCliente, String apellido, String nombre, String dni, String calle,
            Integer numero, String localidad, String provincia, boolean esPrivado,
            ObraSocial obraSocial, String numeroAfiliado) {
        this.idCliente = idCliente;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
        this.esPrivado = esPrivado;
        this.obraSocial = obraSocial;
        this.numeroAfiliado = numeroAfiliado;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public boolean isEsPrivado() {
        return esPrivado;
    }

    public void setEsPrivado(boolean esPrivado) {
        this.esPrivado = esPrivado;
    }

    public ObraSocial getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(ObraSocial obraSocial) {
        this.obraSocial = obraSocial;
    }

    public String getNumeroAfiliado() {
        return numeroAfiliado;
    }

    public void setNumeroAfiliado(String numeroAfiliado) {
        this.numeroAfiliado = numeroAfiliado;
    }

}
