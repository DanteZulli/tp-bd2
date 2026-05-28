package com.grupoy.tpbd2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.grupoy.tpbd2.model.Cliente;
import com.grupoy.tpbd2.model.DetalleVenta;
import com.grupoy.tpbd2.model.Domicilio;
import com.grupoy.tpbd2.model.Empleado;
import com.grupoy.tpbd2.model.ObraSocial;
import com.grupoy.tpbd2.model.Producto;
import com.grupoy.tpbd2.model.Sucursal;
import com.grupoy.tpbd2.model.Venta;
import com.grupoy.tpbd2.repository.VentaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final VentaRepository ventaRepository;

    @Value("${app.json-output}")
    private String jsonOutputPath;

    private static final ObraSocial OSDE = new ObraSocial(1, "OSDE");
    private static final ObraSocial SWISS_MEDICAL = new ObraSocial(2, "Swiss Medical");
    private static final ObraSocial GALENO = new ObraSocial(3, "Galeno");
    private static final ObraSocial MEDICUS = new ObraSocial(4, "Medicus");

    private static final Domicilio DOM_SUC1 = new Domicilio("Av. Rivadavia", 1234, "Capital Federal", "Buenos Aires");
    private static final Domicilio DOM_SUC2 = new Domicilio("Av. Cabildo", 5678, "Capital Federal", "Buenos Aires");
    private static final Domicilio DOM_SUC3 = new Domicilio("Av. Corrientes", 9012, "Capital Federal", "Buenos Aires");

    @Override
    public void run(String... args) {
        System.out.println("=== Iniciando carga de datos de prueba ===");

        ventaRepository.deleteAll();

        List<Venta> ventas = generarVentas();
        ventaRepository.saveAll(ventas);

        long count = ventaRepository.count();
        System.out.println("=== Carga completada: " + count + " ventas en la coleccion 'ventas' ===");

        serializarJson(ventas);
    }

    private List<Venta> generarVentas() {
        List<Producto> productos = crearProductos();
        List<Cliente> clientes = crearClientes();
        List<List<Empleado>> empleadosPorSucursal = crearEmpleados();
        List<Sucursal> sucursales = crearSucursales(empleadosPorSucursal);

        List<Venta> ventas = new ArrayList<>();
        int idVenta = 1;
        Random rnd = new Random(42);

        for (int i = 0; i < sucursales.size(); i++) {
            Sucursal sucursal = sucursales.get(i);
            List<Empleado> empleados = empleadosPorSucursal.get(i);

            int cantVentas = 27 + rnd.nextInt(10); // 27-36 (±20% de 30)

            for (int j = 1; j <= cantVentas; j++) {
                Venta venta = new Venta();
                venta.setIdVenta(idVenta++);
                venta.setSucursal(sucursal);
                venta.setNroTicket(String.format("%s-%08d", sucursal.getPuntoVenta(), j));
                venta.setFecha(LocalDate.of(2025, 3 + rnd.nextInt(3), 1 + rnd.nextInt(28)));
                venta.setCliente(clientes.get(rnd.nextInt(clientes.size())));
                venta.setVendedor(empleados.get(rnd.nextInt(empleados.size())));

                Empleado cajero;
                do {
                    cajero = empleados.get(rnd.nextInt(empleados.size()));
                } while (cajero.equals(venta.getVendedor()));
                venta.setCajero(cajero);

                venta.setFormaPago(FormaPago.values()[rnd.nextInt(FormaPago.values().length)].name());

                int cantProductos = 1 + rnd.nextInt(3); // 1-3 productos
                List<DetalleVenta> detalles = new ArrayList<>();
                BigDecimal total = BigDecimal.ZERO;

                for (int k = 0; k < cantProductos; k++) {
                    Producto prod = productos.get(rnd.nextInt(productos.size()));
                    int cantidad = 1 + rnd.nextInt(3);
                    BigDecimal precioUnitario = prod.getPrecioActual();
                    BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));

                    DetalleVenta detalle = new DetalleVenta();
                    detalle.setProducto(prod);
                    detalle.setCantidad(cantidad);
                    detalle.setPrecioUnitarioHistorico(precioUnitario);
                    detalle.setSubtotal(subtotal);
                    detalles.add(detalle);

                    total = total.add(subtotal);
                }

                venta.setDetalleVentas(detalles);
                venta.setTotalVenta(total.setScale(2, RoundingMode.HALF_UP));
                ventas.add(venta);
            }
        }

        return ventas;
    }

    private List<Producto> crearProductos() {
        return List.of(
                new Producto(1, "MED001", "Ibuprofeno 600mg", "medicamento", "Bayer", new BigDecimal("850.00")),
                new Producto(2, "MED002", "Paracetamol 500mg", "medicamento", "Roemmers", new BigDecimal("620.00")),
                new Producto(3, "MED003", "Amoxicilina 500mg", "medicamento", "Bago", new BigDecimal("1200.00")),
                new Producto(4, "MED004", "Omeprazol 20mg", "medicamento", "Elea", new BigDecimal("980.00")),
                new Producto(5, "MED005", "Losartan 50mg", "medicamento", "Phoenix", new BigDecimal("1500.00")),
                new Producto(6, "MED006", "Enalapril 10mg", "medicamento", "Richmond", new BigDecimal("1100.00")),
                new Producto(7, "MED007", "Diazepam 5mg", "medicamento", "Roche", new BigDecimal("750.00")),
                new Producto(8, "PER001", "Shampoo Pantene", "perfumeria", "Procter & Gamble", new BigDecimal("1800.00")),
                new Producto(9, "PER002", "Crema Dove", "perfumeria", "Unilever", new BigDecimal("2500.00")),
                new Producto(10, "PER003", "Desodorante Rexona", "perfumeria", "Unilever", new BigDecimal("1200.00")));
    }

    private List<Cliente> crearClientes() {
        return List.of(
                crearCliente(1, "Perez", "Juan", "30123456", false, OSDE, "AF-12345"),
                crearCliente(2, "Garcia", "Maria", "30987654", false, SWISS_MEDICAL, "SM-67890"),
                crearCliente(3, "Lopez", "Carlos", "28567890", false, GALENO, "GA-11111"),
                crearCliente(4, "Fernandez", "Ana", "27123456", true, null, null),
                crearCliente(5, "Rodriguez", "Pedro", "33234567", true, null, null),
                crearCliente(6, "Martinez", "Laura", "29456789", false, OSDE, "AF-22222"),
                crearCliente(7, "Gonzalez", "Diego", "35345678", false, SWISS_MEDICAL, "SM-33333"),
                crearCliente(8, "Sanchez", "Valeria", "26789012", true, null, null),
                crearCliente(9, "Romero", "Pablo", "38345678", false, MEDICUS, "ME-44444"),
                crearCliente(10, "Torres", "Agustina", "25456789", false, GALENO, "GA-55555"));
    }

    private Cliente crearCliente(Integer id, String apellido, String nombre, String dni,
            boolean privado, ObraSocial obraSocial, String numAfiliado) {
        return new Cliente(id, apellido, nombre, dni,
                new Domicilio("Calle " + apellido, 100 + id, "Capital Federal", "Buenos Aires"),
                privado, obraSocial, numAfiliado);
    }

    private List<List<Empleado>> crearEmpleados() {
        Domicilio dom = new Domicilio("Av. Siempre Viva", 742, "Capital Federal", "Buenos Aires");

        Empleado emp1 = new Empleado(1, "Garcia", "Juan", "20111111", "20-20111111-1", dom, OSDE, "AF-EMP01", true);
        Empleado emp2 = new Empleado(2, "Martinez", "Ana", "20222222", "20-20222222-2", dom, SWISS_MEDICAL, "SM-EMP02", false);
        Empleado emp3 = new Empleado(3, "Lopez", "Carlos", "20333333", "20-20333333-3", dom, GALENO, "GA-EMP03", false);

        Empleado emp4 = new Empleado(4, "Rodriguez", "Maria", "20444444", "20-20444444-4", dom, OSDE, "AF-EMP04", true);
        Empleado emp5 = new Empleado(5, "Gonzalez", "Pedro", "20555555", "20-20555555-5", dom, SWISS_MEDICAL, "SM-EMP05", false);
        Empleado emp6 = new Empleado(6, "Fernandez", "Laura", "20666666", "20-20666666-6", dom, GALENO, "GA-EMP06", false);

        Empleado emp7 = new Empleado(7, "Perez", "Diego", "20777777", "20-20777777-7", dom, MEDICUS, "ME-EMP07", true);
        Empleado emp8 = new Empleado(8, "Sanchez", "Valeria", "20888888", "20-20888888-8", dom, OSDE, "AF-EMP08", false);
        Empleado emp9 = new Empleado(9, "Romero", "Pablo", "20999999", "20-20999999-9", dom, SWISS_MEDICAL, "SM-EMP09", false);

        return List.of(List.of(emp1, emp2, emp3), List.of(emp4, emp5, emp6), List.of(emp7, emp8, emp9));
    }

    private List<Sucursal> crearSucursales(List<List<Empleado>> empleadosPorSucursal) {
        return List.of(
                new Sucursal(1, "0001", DOM_SUC1, empleadosPorSucursal.get(0).get(0)),
                new Sucursal(2, "0002", DOM_SUC2, empleadosPorSucursal.get(1).get(0)),
                new Sucursal(3, "0003", DOM_SUC3, empleadosPorSucursal.get(2).get(0)));
    }

    private void serializarJson(List<Venta> ventas) {
        try {
            Files.createDirectories(Paths.get(jsonOutputPath).getParent());

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.findAndRegisterModules();

            mapper.writeValue(Path.of(jsonOutputPath).toFile(), ventas);
            System.out.println("=== JSON generado: " + jsonOutputPath + " ===");
        } catch (Exception e) {
            System.err.println("Error al serializar JSON: " + e.getMessage());
        }
    }

    private enum FormaPago {
        efectivo, tarjeta, debito
    }
}
