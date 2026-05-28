package com.grupoy.tpbd2.model;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Domicilio {

    @Field("calle")
    private String calle;

    @Field("numero")
    private Integer numero;

    @Field("localidad")
    private String localidad;

    @Field("provincia")
    private String provincia;
}
