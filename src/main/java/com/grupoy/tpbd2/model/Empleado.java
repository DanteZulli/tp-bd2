package com.grupoy.tpbd2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {

    private Integer idEmpleado;
    private String apellido;
    private String nombre;
    private String dni;
    private String cuil;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;
    private ObraSocial obraSocial;
    private boolean esEncargado;
}
