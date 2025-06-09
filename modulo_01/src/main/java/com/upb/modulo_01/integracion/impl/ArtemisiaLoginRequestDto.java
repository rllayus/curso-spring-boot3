package com.upb.modulo_01.integracion.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArtemisiaLoginRequestDto {
    private String username;
    private String password;
}
