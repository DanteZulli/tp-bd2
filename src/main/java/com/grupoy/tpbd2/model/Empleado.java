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
    private Domicilio domicilio;
    private ObraSocial obraSocial;
    private String numeroAfiliado;
    private boolean esEncargado;
}
