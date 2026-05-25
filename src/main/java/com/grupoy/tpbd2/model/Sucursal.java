package com.grupoy.tpbd2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {

    private Integer idSucursal;
    private String puntoVenta;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;
}
