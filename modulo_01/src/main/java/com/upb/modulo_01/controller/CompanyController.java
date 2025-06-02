package com.upb.modulo_01.controller;

import com.upb.modulo_01.entity.Company;
import com.upb.modulo_01.entity.dto.CompanyRequestDto;
import com.upb.modulo_01.entity.dto.PersonaDto;
import com.upb.modulo_01.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping()
    public ResponseEntity<List<Company>> list() {
        log.info("[list], Listando todas las empresas");
        try {
            return ResponseEntity.ok(companyService.listAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Void>
    save(@RequestBody CompanyRequestDto dto) {
        try {
            companyService.save(dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/test")
    public ResponseEntity<Void>
    test(@RequestBody PersonaDto dto) {
        try {
            System.out.println(dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
