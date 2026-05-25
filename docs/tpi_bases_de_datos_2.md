# Trabajo Práctico Integrador - Bases de Datos II
## Universidad Nacional de Lanús - Licenciatura en Sistemas

Este documento combina las especificaciones del enunciado original del Trabajo Práctico Integrador junto con la fe de erratas oficial de la lista de reportes.

---

### Requerimientos Generales del Set de Datos
Para poder realizar las consultas, se deberá generar un set de datos conteniendo como mínimo:
* **3 sucursales**.
* **10 clientes**.
* **3 vendedores por sucursal**, donde uno de ellos debe tener la categoría de **encargado**.
* **10 productos** (7 medicamentos y 3 de perfumería).
* Un promedio de **30 ventas por sucursal** (con una variación aceptable de +/- 20%).
* Las ventas deben tener un promedio mínimo de **1,5 productos**.

---

### Resumen del Relevamiento del Problema
* **Modelado Comercial:** La farmacia vende medicamentos y productos de perfumería.
* **Clientes:** Se registran apellido, nombre, DNI, domicilio (calle, número, localidad, provincia) y obra social si posee (nombre y número de afiliado). Si no posee, se clasifica como privado.
* **Empleados:** Se requiere apellido, nombre, DNI, CUIL, domicilio y la obra social a la que aporta.
* **Productos:** Se requiere identificar si es medicamento o perfumería, descripción, laboratorio, código numérico y precio.
* **Sucursales:** Se debe registrar qué empleados pertenecen a ella, su domicilio y quién es el encargado.
* **Ventas:** Se registra fecha, número de ticket (los primeros 4 dígitos corresponden al punto de venta que identifica la sucursal, ej: `0001-00001234`), total, forma de pago (efectivo, tarjeta o débito), productos con sus cantidades, precio unitario y total, el empleado que atendió y el empleado que realizó el cobro.

---

### Cronograma de Entregas Parciales

#### ENTREGA 01: Análisis del Problema y Modelo Relacional (ERD)
* **Objetivo:** Analizar el problema desde un enfoque relacional clásico.
* **Entregables:**
  1. Análisis formal del problema.
  2. Construcción y entrega del Diagrama de Entidad-Relación (ERD) resultante.

#### ENTREGA 02: Clases Java (POJOs) y Serialización JSON
* **Objetivo:** Migrar hacia un diseño orientado a objetos y posteriormente a una estructura documental desnormalizada (autocontenida).
* **Entregables:**
  1. Construcción de las clases POJOs en Java en base al ERD previo.
  2. Instanciación de la estructura de datos con los datos de prueba mínimos requeridos.
  3. Serialización completa de la estructura de datos usando GSON o Jackson para generar un **único documento JSON**.
* **Formato:** Archivo comprimido `.ZIP` con el proyecto de código y el archivo `.json` resultante.

#### ENTREGA 03: Almacenamiento en MongoDB y Consultas Integradas
* **Objetivo:** Implementación física en base de datos NoSQL y generación de reportes.
* **Entregables:**
  1. Carga de datos de prueba en la base de datos MongoDB **enfocada exclusivamente en la colección de Ventas**. Este proceso debe ser ejecutado mediante una clase Java incluida en el proyecto.
  2. Resolución obligatoria de las **Consultas 1 y 4** (detalladas abajo) para la aprobación de la cursada.
* **Formato:** Las consultas pueden estar embebidas en el código Java o entregarse en un archivo de texto aparte si se ejecutan directo en el cliente de MongoDB.

---

### Especificación de Reportes Requeridos (Fe de Erratas Aplicada)
Todas las consultas que involucran agrupaciones o cálculos cronológicos deben recibir como parámetros de filtrado una **fecha desde** y una **fecha hasta**.

1. **Reporte 1 (Obligatorio para aprobación):** Un reporte con dos resultados: por un lado, el total de la cantidad de ventas de toda la cadena completa (todas las sucursales) y, por otro lado, las cantidades de ventas agrupadas por sucursales.
2. **Reporte 2:** Cantidades de ventas agrupadas por obras sociales, considerando a los privados (sin obra social) como un grupo más.
3. **Reporte 3:** Un reporte con dos resultados: por un lado, el total de la cobranza de toda la cadena completa y, por otro lado, la cobranza agrupada por sucursales.
4. **Reporte 4 (Obligatorio para aprobación):** Cantidades de ventas agrupadas por tipo de producto (diferenciando entre farmacia y perfumería).
5. **Reporte 5:** Ranking de monto vendido, agrupado por producto y por sucursal.
6. **Reporte 6:** Ranking de cantidad de productos vendidos, agrupado por producto y por sucursal.
7. **Reporte 7:** Ranking de compras agrupadas por cliente para el total de la cadena (para identificar a los clientes que más compraron globalmente).
8. **Reporte 8:** Ranking de compras agrupadas por cliente y por sucursal (para evaluar el comportamiento de los clientes intra-sucursal).

> *Nota:* La resolución de los reportes 2, 3, 5, 6, 7 y 8 es opcional pero será tenida en cuenta para mejorar la nota final de la cursada.
