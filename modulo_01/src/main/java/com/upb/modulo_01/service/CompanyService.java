package com.upb.modulo_01.service;

import com.upb.modulo_01.entity.Company;
import com.upb.modulo_01.entity.dto.CompanyRequestDto;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> listAll();
    List<Company> list(String nit, String nombre);
    void save(CompanyRequestDto company);
    void update(Long companyId, CompanyRequestDto company);
    Optional<Company> findById(Long id);
    void delete(Long id);

}
