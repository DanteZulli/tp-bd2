package com.grupoy.tpbd2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.grupoy.tpbd2.repository.VentaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Order(2) // Ejecuta despues de DataLoader
public class ReporteLoader implements CommandLineRunner {

    private final VentaRepository ventaRepository;

    @Value("${app.json-output-dir:./output}")
    private String outputDir;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== Generando reportes de consultas (Fe de Erratas) ===");

        LocalDate inicio = LocalDate.of(2025, 1, 1);
        LocalDate fin = LocalDate.of(2026, 12, 31);

        // Usamos "var" para recibir el Record
        var reporte1Cadena = ventaRepository.reporteCantidadVentasCadena(inicio, fin);
        var reporte1Sucursal = ventaRepository.reporteCantidadVentasPorSucursal(inicio, fin);
        var reporte4Productos = ventaRepository.reporteCantidadVentasPorTipoProducto(inicio, fin);

        // Serializamos a JSON
        serializarJson(reporte1Cadena, "consulta1_cantidad_ventas_cadena.json");
        serializarJson(reporte1Sucursal, "consulta1_cantidad_ventas_sucursal.json");
        serializarJson(reporte4Productos, "consulta4_cantidad_ventas_productos.json");

        System.out.println("=== Reportes generados exitosamente en la carpeta: " + outputDir + " ===");
    }

    private void serializarJson(List<?> datos, String nombreArchivo) {
        try {
            Path pathDirectorio = Paths.get(outputDir);
            Files.createDirectories(pathDirectorio);

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.findAndRegisterModules();

            Path pathArchivo = pathDirectorio.resolve(nombreArchivo);
            mapper.writeValue(pathArchivo.toFile(), datos);
            System.out.println(" -> Generado: " + nombreArchivo);
        } catch (Exception e) {
            System.err.println("Error al serializar el reporte " + nombreArchivo + ": " + e.getMessage());
        }
    }
}