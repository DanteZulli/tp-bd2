package com.grupoy.tpbd2.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.grupoy.tpbd2.model.Venta;

@Repository
public interface VentaRepository extends MongoRepository<Venta, Integer> {

    // DTO para que Spring Boot no confunda el resultado con la clase Venta
    public record ReporteVentas(Object _id, int cantidadVentas) {}

    // CONSULTA 1 (Parte A): Total de la cantidad de ventas de la cadena completa
    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $group: { _id: 'Cadena Completa', cantidadVentas: { $sum: 1 } } }"
    })
    List<ReporteVentas> reporteCantidadVentasCadena(LocalDate inicio, LocalDate fin);

    // CONSULTA 1 (Parte B): Cantidad de ventas (tickets) agrupadas por sucursal
    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $group: { _id: '$sucursal.id_sucursal', cantidadVentas: { $sum: 1 } } }"
    })
    List<ReporteVentas> reporteCantidadVentasPorSucursal(LocalDate inicio, LocalDate fin);

    // CONSULTA 4: Cantidades de ventas agrupadas por tipo de producto
    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $unwind: '$detalle_ventas' }",
        "{ $group: { _id: '$detalle_ventas.producto.tipo', cantidadVentas: { $sum: '$detalle_ventas.cantidad' } } }"
    })
    List<ReporteVentas> reporteCantidadVentasPorTipoProducto(LocalDate inicio, LocalDate fin);
}