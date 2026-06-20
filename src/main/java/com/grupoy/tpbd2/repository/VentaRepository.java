package com.grupoy.tpbd2.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.grupoy.tpbd2.model.Venta;

@Repository
public interface VentaRepository extends MongoRepository<Venta, Integer> {

    // REPORTE 1 (Parte A): Total de cantidad de ventas de toda la cadena
    record ReporteCantidadVentasCadena(String cadena, int cantidadVentas) {}

    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $group: { _id: 'Cadena Completa', cantidadVentas: { $sum: 1 } } }",
        "{ $project: { _id: 0, cadena: '$_id', cantidadVentas: 1 } }"
    })
    List<ReporteCantidadVentasCadena> reporteCantidadVentasCadena(LocalDate inicio, LocalDate fin);

    // REPORTE 1 (Parte B): Cantidad de ventas agrupadas por sucursal
    record ReporteCantidadVentasSucursal(int idSucursal, int cantidadVentas) {}

    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $group: { _id: '$sucursal.id_sucursal', cantidadVentas: { $sum: 1 } } }",
        "{ $project: { _id: 0, idSucursal: '$_id', cantidadVentas: 1 } }"
    })
    List<ReporteCantidadVentasSucursal> reporteCantidadVentasPorSucursal(LocalDate inicio, LocalDate fin);

    // REPORTE 2: Cantidad de ventas agrupadas por obra social
    record ReporteVentasObraSocial(String obraSocial, int cantidadVentas) {}

    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $group: { _id: { $ifNull: ['$cliente.obra_social.nombre', 'Privado'] }, cantidadVentas: { $sum: 1 } } }",
        "{ $project: { _id: 0, obraSocial: '$_id', cantidadVentas: 1 } }"
    })
    List<ReporteVentasObraSocial> reporteVentasPorObraSocial(LocalDate inicio, LocalDate fin);

    // REPORTE 3 (Parte A): Total de cobranza de toda la cadena
    record ReporteCobranzaCadena(String cadena, BigDecimal montoTotal) {}

    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $group: { _id: 'Cadena Completa', montoTotal: { $sum: '$total_venta' } } }",
        "{ $project: { _id: 0, cadena: '$_id', montoTotal: 1 } }"
    })
    List<ReporteCobranzaCadena> reporteCobranzaCadena(LocalDate inicio, LocalDate fin);

    // REPORTE 3 (Parte B): Cobranza agrupada por sucursal
    record ReporteCobranzaSucursal(int idSucursal, BigDecimal montoTotal) {}

    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $group: { _id: '$sucursal.id_sucursal', montoTotal: { $sum: '$total_venta' } } }",
        "{ $project: { _id: 0, idSucursal: '$_id', montoTotal: 1 } }"
    })
    List<ReporteCobranzaSucursal> reporteCobranzaPorSucursal(LocalDate inicio, LocalDate fin);

    // REPORTE 4: Cantidad de ventas agrupadas por tipo de producto
    record ReporteVentasTipoProducto(String tipoProducto, int cantidadVentas) {}

    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $unwind: '$detalle_ventas' }",
        "{ $group: { _id: '$detalle_ventas.producto.tipo', cantidadVentas: { $sum: '$detalle_ventas.cantidad' } } }",
        "{ $project: { _id: 0, tipoProducto: '$_id', cantidadVentas: 1 } }"
    })
    List<ReporteVentasTipoProducto> reporteCantidadVentasPorTipoProducto(LocalDate inicio, LocalDate fin);

    // REPORTE 5: Ranking de monto vendido por producto y sucursal
    record ReporteRankingMontoProductoSucursal(String producto, int idSucursal, BigDecimal montoVendido) {}

    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $unwind: '$detalle_ventas' }",
        "{ $group: { _id: { producto: '$detalle_ventas.producto.descripcion', sucursal: '$sucursal.id_sucursal' }, montoVendido: { $sum: '$detalle_ventas.subtotal' } } }",
        "{ $project: { _id: 0, producto: '$_id.producto', idSucursal: '$_id.sucursal', montoVendido: 1 } }",
        "{ $sort: { montoVendido: -1 } }"
    })
    List<ReporteRankingMontoProductoSucursal> reporteRankingMontoProductoSucursal(LocalDate inicio, LocalDate fin);

    // REPORTE 6: Ranking de cantidad de productos vendidos por producto y sucursal
    record ReporteRankingCantidadProductoSucursal(String producto, int idSucursal, int cantidadVendida) {}

    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $unwind: '$detalle_ventas' }",
        "{ $group: { _id: { producto: '$detalle_ventas.producto.descripcion', sucursal: '$sucursal.id_sucursal' }, cantidadVendida: { $sum: '$detalle_ventas.cantidad' } } }",
        "{ $project: { _id: 0, producto: '$_id.producto', idSucursal: '$_id.sucursal', cantidadVendida: 1 } }",
        "{ $sort: { cantidadVendida: -1 } }"
    })
    List<ReporteRankingCantidadProductoSucursal> reporteRankingCantidadProductoSucursal(LocalDate inicio, LocalDate fin);

    // REPORTE 7: Ranking de compras por cliente (cadena completa)
    record ReporteRankingComprasCliente(int idCliente, String apellido, String nombre, int cantidadCompras) {}

    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $group: { _id: { id: '$cliente.id_cliente', apellido: '$cliente.apellido', nombre: '$cliente.nombre' }, cantidadCompras: { $sum: 1 } } }",
        "{ $project: { _id: 0, idCliente: '$_id.id', apellido: '$_id.apellido', nombre: '$_id.nombre', cantidadCompras: 1 } }",
        "{ $sort: { cantidadCompras: -1 } }"
    })
    List<ReporteRankingComprasCliente> reporteRankingComprasCliente(LocalDate inicio, LocalDate fin);

    // REPORTE 8: Ranking de compras por cliente y sucursal
    record ReporteRankingComprasClienteSucursal(int idCliente, String apellido, String nombre, int idSucursal, int cantidadCompras) {}

    @Aggregation(pipeline = {
        "{ $match: { fecha: { $gte: ?0, $lte: ?1 } } }",
        "{ $group: { _id: { id: '$cliente.id_cliente', apellido: '$cliente.apellido', nombre: '$cliente.nombre', sucursal: '$sucursal.id_sucursal' }, cantidadCompras: { $sum: 1 } } }",
        "{ $project: { _id: 0, idCliente: '$_id.id', apellido: '$_id.apellido', nombre: '$_id.nombre', idSucursal: '$_id.sucursal', cantidadCompras: 1 } }",
        "{ $sort: { cantidadCompras: -1 } }"
    })
    List<ReporteRankingComprasClienteSucursal> reporteRankingComprasClienteSucursal(LocalDate inicio, LocalDate fin);

}
