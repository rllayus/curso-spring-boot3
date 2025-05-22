package com.upb.modulo_01.service.impl;

import com.upb.modulo_01.entity.DetalleNotaVenta;
import com.upb.modulo_01.repository.DetalleNotaVentaRepository;
import com.upb.modulo_01.service.DetalleNotaVentaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Slf4j
@Service
public class DetalleNotaVentaServiceImpl implements DetalleNotaVentaService {
    private final DetalleNotaVentaRepository repository;

    @Override
    @Transactional
    public void save(DetalleNotaVenta detalleNotaVenta) {
        this.repository.save(detalleNotaVenta);
    }
}
