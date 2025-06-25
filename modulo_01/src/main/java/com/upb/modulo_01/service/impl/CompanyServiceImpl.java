package com.upb.modulo_01.service.impl;

import com.upb.modulo_01.entity.Company;
import com.upb.modulo_01.entity.dto.CompanyRequestDto;
import com.upb.modulo_01.entity.dto.CompanyResponseDto;
import com.upb.modulo_01.entity.enums.StateEntity;
import com.upb.modulo_01.exception.NotDataFoundException;
import com.upb.modulo_01.repository.CompanyRepository;
import com.upb.modulo_01.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    @Lazy
    private CompanyRepository companyRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Company> listAll() {
        return companyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CompanyResponseDto> list(Date from, Date to, String nit, String nombre, Pageable pageable) {
        return companyRepository.findAll(nit,  from, to, nombre, pageable);
    }

    @Override
    public void save(CompanyRequestDto company) {
        this.companyRepository.save(Company.builder()
                        .name(company.getName())
                        .nit(company.getNit())
                        .state(company.getState())
                .build());
    }

    @Override
    @Transactional
    public void update(Long companyId, CompanyRequestDto companyDto) {
        this.companyRepository.updateCompany(
                companyId, companyDto.getNit(), companyDto.getName(), companyDto.getState());

        /**
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if(companyOptional.isEmpty()) {
            throw new NotDataFoundException("Company not found");
        }
        Company company = companyOptional.get();
        company.setName(companyDto.getName());
        company.setNit(companyDto.getNit());
        company.setState(companyDto.getState());
         */
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.companyRepository.deleteById(id);
    }

}
