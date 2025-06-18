package com.upb.modulo_01.controller;

import com.upb.modulo_01.entity.Company;
import com.upb.modulo_01.entity.dto.CompanyRequestDto;
import com.upb.modulo_01.entity.dto.CompanyResponseDto;
import com.upb.modulo_01.entity.dto.PersonaDto;
import com.upb.modulo_01.exception.NotDataFoundException;
import com.upb.modulo_01.exception.OperationException;
import com.upb.modulo_01.service.CompanyService;
import com.upb.modulo_01.utils.DateUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/companies")
@Secured({"ROLE_ADMIN", "ROLE_CAJERO"})
public class CompanyController {
    private final CompanyService companyService;

    @Operation(summary = "Método para listar empresas",
            description = "Método para listar empresas paginados",
            tags = {"companies"},
            responses = {
                    @ApiResponse(description = "Operación satisfactorio", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CompanyResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso no encontrado", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Fallo de autentificación", content = @Content(schema = @Schema(hidden = true))),
            }, security = @SecurityRequirement(name = "bearerToken"))
    @GetMapping()
    public ResponseEntity<Page<CompanyResponseDto>> listaPaginada(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "DESC") Sort.Direction sortDir,

            @RequestParam(name = "nit", required = false) String nit,
            @RequestParam(name = "nombre", required = false) String nombre,

            @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = DateUtils.FORMAT_ISO_8601_SHORT) Date from,
            @RequestParam(value = "to" , required = false) @DateTimeFormat(pattern = DateUtils.FORMAT_ISO_8601_SHORT) Date to) {

        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No autorizado");
        }

        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortDir, sortBy));
            return ResponseEntity.ok(companyService.list(nit, nombre, pageable));
        } catch (OperationException e) {
            log.error("Error al listar el empresas. Causa:{}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            log.error("Error al listar el empresas", e);
            return ResponseEntity.badRequest().build();
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
