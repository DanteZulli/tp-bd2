package com.grupoy.tpbd2.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ventas")
public class Venta {

    @Id
    @Field("id_venta")
    private Integer idVenta;

    @Field("nro_ticket")
    private String nroTicket;

    @Field("fecha")
    private LocalDate fecha;

    @Field("total_venta")
    private BigDecimal totalVenta;

    @Field("forma_pago")
    private String formaPago;

    @Field("cliente")
    private Cliente cliente;

    @Field("vendedor")
    private Empleado vendedor;

    @Field("cajero")
    private Empleado cajero;

    @Field("sucursal")
    private Sucursal sucursal;

    @Field("detalle_ventas")
    private List<DetalleVenta> detalleVentas;
}
