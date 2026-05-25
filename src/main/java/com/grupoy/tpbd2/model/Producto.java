package com.grupoy.tpbd2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    private Integer codigo;
    private String descripcion;
    private String laboratorio;
    private boolean esMedicamento;
    private Double precio;
}
