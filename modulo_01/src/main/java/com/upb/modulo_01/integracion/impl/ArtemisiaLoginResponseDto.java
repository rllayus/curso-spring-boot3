package com.upb.modulo_01.integracion.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArtemisiaLoginResponseDto {
    @JsonProperty("id_token")
    private String token;
    private String username;
}
