package com.grupoy.tpbd2.model;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {

    @Field("producto")
    private Producto producto;

    @Field("cantidad")
    private Integer cantidad;

    @Field("precio_unitario_historico")
    private BigDecimal precioUnitarioHistorico;

    @Field("subtotal")
    private BigDecimal subtotal;
}
