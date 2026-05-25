package com.grupoy.tpbd2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    private Integer idProducto;
    private String codigoNum;
    private String descripcion;
    private String tipo;
    private String laboratorio;
    private Double precioActual;
}
