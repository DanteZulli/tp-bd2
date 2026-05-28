package com.grupoy.tpbd2.model;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Field("id_producto")
    private Integer idProducto;

    @Field("codigo_num")
    private String codigoNum;

    @Field("descripcion")
    private String descripcion;

    @Field("tipo")
    private String tipo;

    @Field("laboratorio")
    private String laboratorio;

    @Field("precio_actual")
    private BigDecimal precioActual;
}
