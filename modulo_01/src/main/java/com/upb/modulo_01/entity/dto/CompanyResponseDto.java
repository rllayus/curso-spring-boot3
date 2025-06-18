package com.upb.modulo_01.entity.dto;

import com.upb.modulo_01.entity.Company;
import com.upb.modulo_01.entity.enums.StateEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CompanyResponseDto {
    private Long id;
    private String name;
    private String nit;
    private StateEntity state;
    public CompanyResponseDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.nit = company.getNit();
        this.state = company.getState();
    }
}
