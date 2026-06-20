package com.grupoy.tpbd2;

import java.nio.file.Paths;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.grupoy.tpbd2.repository.VentaRepository;
import com.grupoy.tpbd2.util.JsonUtil;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Order(2)
public class ReporteRunner implements CommandLineRunner {

    private final VentaRepository ventaRepository;

    @Value("${app.json-output}")
    private String jsonOutputPath;

    @Override
    public void run(String... args) {
        System.out.println("=== Generando reportes ===");

        LocalDate inicio = LocalDate.of(2025, 1, 1);
        LocalDate fin = LocalDate.of(2026, 12, 31);

        var outputDir = Paths.get(jsonOutputPath).getParent();

        JsonUtil.serializarJson(
            ventaRepository.reporteCantidadVentasCadena(inicio, fin),
            outputDir.resolve("reporte1a_cantidad_ventas_cadena.json").toString()
        );
        JsonUtil.serializarJson(
            ventaRepository.reporteCantidadVentasPorSucursal(inicio, fin),
            outputDir.resolve("reporte1b_cantidad_ventas_sucursal.json").toString()
        );
        JsonUtil.serializarJson(
            ventaRepository.reporteVentasPorObraSocial(inicio, fin),
            outputDir.resolve("reporte2_ventas_obra_social.json").toString()
        );
        JsonUtil.serializarJson(
            ventaRepository.reporteCobranzaCadena(inicio, fin),
            outputDir.resolve("reporte3a_cobranza_cadena.json").toString()
        );
        JsonUtil.serializarJson(
            ventaRepository.reporteCobranzaPorSucursal(inicio, fin),
            outputDir.resolve("reporte3b_cobranza_sucursal.json").toString()
        );
        JsonUtil.serializarJson(
            ventaRepository.reporteCantidadVentasPorTipoProducto(inicio, fin),
            outputDir.resolve("reporte4_cantidad_ventas_productos.json").toString()
        );
        JsonUtil.serializarJson(
            ventaRepository.reporteRankingMontoProductoSucursal(inicio, fin),
            outputDir.resolve("reporte5_ranking_monto_producto_sucursal.json").toString()
        );
        JsonUtil.serializarJson(
            ventaRepository.reporteRankingCantidadProductoSucursal(inicio, fin),
            outputDir.resolve("reporte6_ranking_cantidad_producto_sucursal.json").toString()
        );
        JsonUtil.serializarJson(
            ventaRepository.reporteRankingComprasCliente(inicio, fin),
            outputDir.resolve("reporte7_ranking_compras_cliente.json").toString()
        );
        JsonUtil.serializarJson(
            ventaRepository.reporteRankingComprasClienteSucursal(inicio, fin),
            outputDir.resolve("reporte8_ranking_compras_cliente_sucursal.json").toString()
        );

        System.out.println("=== Reportes generados en: " + outputDir + " ===");
    }
}
