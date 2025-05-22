package com.upb.modulo_01.controller;

import com.upb.modulo_01.entity.dto.NotaVentaResponseDto;
import com.upb.modulo_01.entity.dto.VentaRequestDto;
import com.upb.modulo_01.service.NotaVentaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/nota-ventas")
public class NotaVentaController {
    private final NotaVentaService notaVentaService;

    @GetMapping()
    public ResponseEntity<List<NotaVentaResponseDto>> list() {
        log.info("[list], Listando todas las empresas");
        try {
            return ResponseEntity.ok(notaVentaService.listAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<NotaVentaResponseDto>> list(@RequestParam("id") Long id) {
        log.info("[list], Listando todas las empresas");
        try {
            return ResponseEntity.ok(notaVentaService.listByCutomerId(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/vender")
    public ResponseEntity<Void> save(@RequestBody VentaRequestDto requestDto) {
        log.info("[vender], Vendiendo una nota de venta con el cliente con id: {} ", requestDto.getCustomerId());
        try {
            notaVentaService.vender(requestDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
