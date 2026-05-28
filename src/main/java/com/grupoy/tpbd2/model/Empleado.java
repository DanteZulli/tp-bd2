package com.grupoy.tpbd2.model;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {

    @Field("id_empleado")
    private Integer idEmpleado;

    @Field("apellido")
    private String apellido;

    @Field("nombre")
    private String nombre;

    @Field("dni")
    private String dni;

    @Field("cuil")
    private String cuil;

    @Field("domicilio")
    private Domicilio domicilio;

    @Field("obra_social")
    private ObraSocial obraSocial;

    @Field("numero_afiliado")
    private String numeroAfiliado;

    @Field("es_encargado")
    private boolean esEncargado;
}
