package com.upb.modulo_01.service;

import com.upb.modulo_01.entity.Company;
import com.upb.modulo_01.entity.dto.CompanyRequestDto;

import java.util.List;

public interface CompanyService {
    List<Company> listAll();
    void save(CompanyRequestDto company);
}
