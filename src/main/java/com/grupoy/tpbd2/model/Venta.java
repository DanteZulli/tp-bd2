package com.grupoy.tpbd2.model;

import java.util.Date;
import java.util.List;

public class Venta {

    private String idVenta;
    private String nroTicket;
    private Date fecha;
    private Double totalVenta;
    private String formaPago;
    private Cliente cliente;
    private Empleado vendedor;
    private Empleado cajero;
    private Sucursal sucursal;

    private List<DetalleVenta> detalleVentas;

    public Venta() {
    }

    public Venta(String idVenta, String nroTicket, Date fecha, Double totalVenta, String formaPago,
            Cliente cliente, Empleado vendedor, Empleado cajero, Sucursal sucursal,
            List<DetalleVenta> detalleVentas) {
        this.idVenta = idVenta;
        this.nroTicket = nroTicket;
        this.fecha = fecha;
        this.totalVenta = totalVenta;
        this.formaPago = formaPago;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.cajero = cajero;
        this.sucursal = sucursal;
        this.detalleVentas = detalleVentas;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public String getNroTicket() {
        return nroTicket;
    }

    public void setNroTicket(String nroTicket) {
        this.nroTicket = nroTicket;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getVendedor() {
        return vendedor;
    }

    public void setVendedor(Empleado vendedor) {
        this.vendedor = vendedor;
    }

    public Empleado getCajero() {
        return cajero;
    }

    public void setCajero(Empleado cajero) {
        this.cajero = cajero;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public List<DetalleVenta> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }


}
