package com.grupoy.tpbd2.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {

    private Producto producto;
    private Integer cantidad;
    private BigDecimal precioUnitarioHistorico;
    private BigDecimal subtotal;
}
