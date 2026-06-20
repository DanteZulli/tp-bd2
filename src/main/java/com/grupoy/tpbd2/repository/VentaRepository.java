package com.grupoy.tpbd2.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.grupoy.tpbd2.model.Venta;

@Repository
public interface VentaRepository extends MongoRepository<Venta, Integer> {

    public record ReporteVentas(Object _id, int cantidadVentas) {}

    public record ReporteCobranza(Object _id, BigDecimal montoTotal) {}

    public record ReporteRankingMonto(Object _id, BigDecimal montoVendido) {}

    public record ReporteRankingCantidad(Object _id, int cantidadVendida) {}

    public record ReporteRankingCliente(Object _id, int cantidadCompras) {}

    // REPORTE 1 (Parte A): Total de la cantidad de ventas de la cadena completa
    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $group: { _id: 'Cadena Completa', cantidadVentas: { $sum: 1 } } }"
    })
    List<ReporteVentas> reporteCantidadVentasCadena(LocalDate inicio, LocalDate fin);

    // REPORTE 1 (Parte B): Cantidad de ventas (tickets) agrupadas por sucursal
    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $group: { _id: '$sucursal.id_sucursal', cantidadVentas: { $sum: 1 } } }"
    })
    List<ReporteVentas> reporteCantidadVentasPorSucursal(LocalDate inicio, LocalDate fin);

    // REPORTE 2: Cantidades de ventas agrupadas por obras sociales (privados como grupo)
    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $group: { _id: { $ifNull: ['$cliente.obra_social.nombre', 'Privado'] }, cantidadVentas: { $sum: 1 } } }"
    })
    List<ReporteVentas> reporteVentasPorObraSocial(LocalDate inicio, LocalDate fin);

    // REPORTE 3 (Parte A): Total de cobranza de toda la cadena
    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $group: { _id: 'Cadena Completa', montoTotal: { $sum: '$total_venta' } } }"
    })
    List<ReporteCobranza> reporteCobranzaCadena(LocalDate inicio, LocalDate fin);

    // REPORTE 3 (Parte B): Cobranza agrupada por sucursal
    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $group: { _id: '$sucursal.id_sucursal', montoTotal: { $sum: '$total_venta' } } }"
    })
    List<ReporteCobranza> reporteCobranzaPorSucursal(LocalDate inicio, LocalDate fin);

    // REPORTE 4: Cantidades de ventas agrupadas por tipo de producto
    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $unwind: '$detalle_ventas' }",
        "{ $group: { _id: '$detalle_ventas.producto.tipo', cantidadVentas: { $sum: '$detalle_ventas.cantidad' } } }"
    })
    List<ReporteVentas> reporteCantidadVentasPorTipoProducto(LocalDate inicio, LocalDate fin);

    // REPORTE 5: Ranking de monto vendido por producto y sucursal
    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $unwind: '$detalle_ventas' }",
        "{ $group: { _id: { producto: '$detalle_ventas.producto.descripcion', sucursal: '$sucursal.id_sucursal' }, montoVendido: { $sum: '$detalle_ventas.subtotal' } } }",
        "{ $sort: { montoVendido: -1 } }"
    })
    List<ReporteRankingMonto> reporteRankingMontoProductoSucursal(LocalDate inicio, LocalDate fin);

    // REPORTE 6: Ranking de cantidad de productos vendidos por producto y sucursal
    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $unwind: '$detalle_ventas' }",
        "{ $group: { _id: { producto: '$detalle_ventas.producto.descripcion', sucursal: '$sucursal.id_sucursal' }, cantidadVendida: { $sum: '$detalle_ventas.cantidad' } } }",
        "{ $sort: { cantidadVendida: -1 } }"
    })
    List<ReporteRankingCantidad> reporteRankingCantidadProductoSucursal(LocalDate inicio, LocalDate fin);

    // REPORTE 7: Ranking de compras por cliente (cadena completa)
    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $group: { _id: { id: '$cliente.id_cliente', apellido: '$cliente.apellido', nombre: '$cliente.nombre' }, cantidadCompras: { $sum: 1 } } }",
        "{ $sort: { cantidadCompras: -1 } }"
    })
    List<ReporteRankingCliente> reporteRankingComprasCliente(LocalDate inicio, LocalDate fin);

    // REPORTE 8: Ranking de compras por cliente y sucursal
    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $group: { _id: { id: '$cliente.id_cliente', apellido: '$cliente.apellido', nombre: '$cliente.nombre', sucursal: '$sucursal.id_sucursal' }, cantidadCompras: { $sum: 1 } } }",
        "{ $sort: { cantidadCompras: -1 } }"
    })
    List<ReporteRankingCliente> reporteRankingComprasClienteSucursal(LocalDate inicio, LocalDate fin);

}
