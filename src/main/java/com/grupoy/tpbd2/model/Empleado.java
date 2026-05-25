package com.grupoy.tpbd2.model;

public class Empleado {
    
    private Integer idEmpleado;
    private String apellido;
    private String nombre;
    private String dni;
    private String cuil;
    
    // Domicilio
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;
    
    private ObraSocial obraSocial; // Objeto anidado
    private boolean esEncargado;

    public Empleado() {
    }

    public Empleado(Integer idEmpleado, String apellido, String nombre, String dni, String cuil, 
                    String calle, Integer numero, String localidad, String provincia, 
                    ObraSocial obraSocial, boolean esEncargado) {
        this.idEmpleado = idEmpleado;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.cuil = cuil;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
        this.obraSocial = obraSocial;
        this.esEncargado = esEncargado;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
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

    public ObraSocial getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(ObraSocial obraSocial) {
        this.obraSocial = obraSocial;
    }

    public boolean isEsEncargado() {
        return esEncargado;
    }

    public void setEsEncargado(boolean esEncargado) {
        this.esEncargado = esEncargado;
    }


}