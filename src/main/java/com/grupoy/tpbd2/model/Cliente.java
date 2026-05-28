package com.grupoy.tpbd2.model;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Field("id_cliente")
    private Integer idCliente;

    @Field("apellido")
    private String apellido;

    @Field("nombre")
    private String nombre;

    @Field("dni")
    private String dni;

    @Field("domicilio")
    private Domicilio domicilio;

    @Field("es_privado")
    private boolean esPrivado;

    @Field("obra_social")
    private ObraSocial obraSocial;

    @Field("numero_afiliado")
    private String numeroAfiliado;
}
