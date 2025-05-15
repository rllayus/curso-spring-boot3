package com.upb.modulo_01.service.impl;

import com.upb.modulo_01.entity.Company;
import com.upb.modulo_01.entity.dto.CompanyRequestDto;
import com.upb.modulo_01.repository.CompanyRepository;
import com.upb.modulo_01.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Override
    public List<Company> listAll() {
        return companyRepository.findAll();
    }

    @Override
    public void save(CompanyRequestDto company) {
        this.companyRepository.save(Company.builder()
                        .name(company.getName())
                        .nit(company.getNit())
                        .state(company.getState())
                .build());
    }
}
