package com.grupoy.tpbd2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {

    private Producto producto;
    private Integer cantidad;
    private Double precioUnitarioHistorico;
    private Double subtotal;
}
