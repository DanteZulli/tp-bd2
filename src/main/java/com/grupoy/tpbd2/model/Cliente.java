package com.grupoy.tpbd2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private Integer idCliente;
    private String apellido;
    private String nombre;
    private String dni;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;
    private boolean esPrivado;
    private ObraSocial obraSocial;
    private String numeroAfiliado;
}
