package com.upb.modulo_01.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class VentaRequestDto {
    private Long customerId;
    private List<DetalleNotaVentaRequestDto> detalle;
    private BigDecimal total;
}
