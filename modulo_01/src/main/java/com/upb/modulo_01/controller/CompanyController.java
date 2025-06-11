package com.upb.modulo_01.controller;

import com.upb.modulo_01.entity.Company;
import com.upb.modulo_01.entity.dto.CompanyRequestDto;
import com.upb.modulo_01.entity.dto.PersonaDto;
import com.upb.modulo_01.exception.NotDataFoundException;
import com.upb.modulo_01.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/companies")
@Secured({"ROLE_ADMIN", "ROLE_CAJERO"})
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping()
    public ResponseEntity<List<Company>> list(
            @RequestParam(name = "nit", required = false) String nit,
            @RequestParam(name = "nombre", required = false) String nombre
            ) {
        log.info("[list], Listando todas las empresas");
        try {
            return ResponseEntity.ok(companyService.list(nit, nombre));
        } catch (Exception e) {
            log.error("[list], Error al listar las empresas", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompany(
            @PathVariable(name = "companyId") Long companyId) {
        try {
           Optional<Company> optionalCompany = companyService.findById(companyId);
           if(optionalCompany.isPresent()) {
               return ResponseEntity.ok(optionalCompany.get());
           }
           throw new NotDataFoundException("No se encontro el id de la empresa");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Void> save(@RequestBody CompanyRequestDto dto) {
        try {
            companyService.save(dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<Void> update(
            @PathVariable(name = "companyId") Long companyId,
            @RequestBody CompanyRequestDto dto) {
        try {
            companyService.update(companyId, dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> delete(
            @PathVariable(name = "companyId") Long companyId) {
        try {
            companyService.delete(companyId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
