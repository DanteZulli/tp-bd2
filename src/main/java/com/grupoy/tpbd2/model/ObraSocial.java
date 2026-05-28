package com.grupoy.tpbd2.model;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObraSocial {

    @Field("id_obra_social")
    private Integer idObraSocial;

    @Field("nombre")
    private String nombre;
}
