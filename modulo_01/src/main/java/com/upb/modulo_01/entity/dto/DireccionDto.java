package com.upb.modulo_01.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DireccionDto {
    private String barrio;
    @JsonProperty("nro_casa")
    private String nroCasa;
    private String calle;
}
