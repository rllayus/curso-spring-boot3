package com.upb.modulo_01.service;


import com.upb.modulo_01.entity.dto.NotaVentaResponseDto;

import java.util.List;

public interface NotaVentaService {
    List<NotaVentaResponseDto> listAll();
    List<NotaVentaResponseDto> listByCutomerId(Long id);
    void guardar(NotaVentaResponseDto notaVentaResponseDto);
    void notificar(NotaVentaResponseDto notaVentaResponseDto);
}
