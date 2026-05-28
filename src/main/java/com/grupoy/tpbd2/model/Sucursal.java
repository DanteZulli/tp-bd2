package com.grupoy.tpbd2.model;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {

    @Field("id_sucursal")
    private Integer idSucursal;

    @Field("punto_venta")
    private String puntoVenta;

    @Field("domicilio")
    private Domicilio domicilio;

    @Field("encargado")
    private Empleado encargado;
}
