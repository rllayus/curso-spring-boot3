package com.upb.modulo_01.entity.dto;

import com.upb.modulo_01.entity.enums.StateEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CompanyRequestDto {
    private String name;
    private String nit;
    private StateEntity state;
}
