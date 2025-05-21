package com.upb.modulo_01.entity.dto;

import com.upb.modulo_01.entity.NotaVenta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class NotaVentaResponseDto {
    private Long id;
    private String customerName;
    private Date date;
    private BigDecimal total;

    public NotaVentaResponseDto(NotaVenta notaVenta) {
        this.id = notaVenta.getId();
        this.customerName = notaVenta.getCustomer().getName();
        this.date = notaVenta.getDate();
        this.total = notaVenta.getTotal();

    }
}
