package com.upb.modulo_01.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetalleNotaVentaRequestDto {
    private Long id;
    private int cantidad;
    private BigDecimal precio;
}
