# Trabajo Práctico Cursada - Base de Datos II - Grupo Y

**Integrantes:**
- Rodrigo Gabriel Funes
- Anahí Maitén Mansilla
- Marcos Fernando Tambosco
- Dante Zulli

**Docentes:** Prof. Federico Ribeiro / Inst. Franco Aguirre

**Materia:** Base de Datos 2 - TP de cursada

**Departamento de Desarrollo Productivo y Tecnológico**

**LICENCIATURA EN SISTEMAS**

---

## Descripción

Trabajo Práctico Integrador de la materia Bases de Datos II (UNLa). El objetivo es analizar un problema de base de datos desde un enfoque NoSQL, partiendo de un modelo relacional (ERD) para luego generar documentos JSON autocontenidos y almacenarlos en MongoDB.

**Dominio del problema:** una cadena de farmacias que necesita informatizar su operatoria (ventas, productos, clientes, empleados y sucursales).

## Cómo levantar el proyecto

```bash
# 1. Iniciar MongoDB
podman-compose up -d

# 2. Ejecutar la API
./mvnw spring-boot:run
```

La API arranca en `http://localhost:8080`

---

## Diagrama Entidad-Relación (ERD)

```mermaid
erDiagram
    SUCURSAL ||--o{ EMPLEADO : "tiene"
    SUCURSAL ||--|| ENCARGADO_REL : "es_encargado"
    EMPLEADO ||--o{ VENTA : "vendedor"
    EMPLEADO ||--o{ VENTA : "cajero"
    SUCURSAL ||--o{ VENTA : "punto de venta"
    CLIENTE }o--|| OBRA_SOCIAL : "pertenece"
    CLIENTE ||--o{ VENTA : "compra"
    VENTA ||--o{ DETALLE_VENTA : "contiene"
    PRODUCTO ||--o{ DETALLE_VENTA : "se vende en"

    SUCURSAL {
        int id_sucursal PK
        string punto_venta
        string calle
        int numero
        string localidad
        string provincia
    }

    EMPLEADO {
        int id_empleado PK
        string apellido
        string nombre
        string dni
        string cuil
        string calle
        int numero
        string localidad
        string provincia
        string nombre_os_aporta
        string nro_afiliado_aporta
        boolean es_encargado
        int id_sucursal FK
    }

    ENCARGADO_REL {
        int id_obra_social PK
        string nombre
    }

    CLIENTE {
        int id_cliente PK
        string apellido
        string nombre
        string dni
        string calle
        int numero
        string localidad
        string provincia
        boolean es_privado
    }

    OBRA_SOCIAL {
        int id_obra_social PK
        string nombre
    }

    VENTA {
        int id_venta PK
        string nro_ticket
        date fecha
        decimal total_venta
        string forma_pago
        int id_cliente FK
        int id_vendedor FK
        int id_cajero FK
        int id_sucursal FK
    }

    PRODUCTO {
        int id_producto PK
        string codigo_num
        string descripcion
        string tipo
        string laboratorio
        decimal precio_actual
    }

    DETALLE_VENTA {
        int id_venta PK_FK
        int id_producto PK_FK
        int cantidad
        decimal precio_unitario_historico
        decimal subtotal
    }
```
![ERD](docs/ERD%20BBDD2.jpg)