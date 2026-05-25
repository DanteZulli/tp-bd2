package com.grupoy.tpbd2.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    private Integer idVenta;
    private String nroTicket;
    private Date fecha;
    private Double totalVenta;
    private String formaPago;
    private Cliente cliente;
    private Empleado vendedor;
    private Empleado cajero;
    private Sucursal sucursal;
    private List<DetalleVenta> detalleVentas;
}
