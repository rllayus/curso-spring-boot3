package com.upb.modulo_01.service;

import com.upb.modulo_01.entity.Company;
import com.upb.modulo_01.entity.dto.CompanyRequestDto;
import com.upb.modulo_01.entity.dto.CompanyResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> listAll();
    Page<CompanyResponseDto> list(String nit, String nombre, Pageable pageable);
    void save(CompanyRequestDto company);
    void update(Long companyId, CompanyRequestDto company);
    Optional<Company> findById(Long id);
    void delete(Long id);

}
