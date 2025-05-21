package com.upb.modulo_01.service.impl;

import com.upb.modulo_01.entity.NotaVenta;
import com.upb.modulo_01.entity.Product;
import com.upb.modulo_01.entity.dto.NotaVentaResponseDto;
import com.upb.modulo_01.repository.NotaVentaRepository;
import com.upb.modulo_01.service.NotaVentaService;
import com.upb.modulo_01.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class NotaVentaServiceImpl implements NotaVentaService {
    private final NotaVentaRepository  repository;
    private final ProductService productService;

    @Override
    public List<NotaVentaResponseDto> listAll() {
        // Inicia la transacción
        return repository.findAllV1();
    }

    @Override
    @Transactional(readOnly = true)// Esta etiqueta es para indicar
    // que la transacción sea sólo de lectura
    public List<NotaVentaResponseDto> listByCutomerId(Long id) {
        // Inicia transaccion
        List<NotaVentaResponseDto> list = new ArrayList<>();
        for (NotaVenta notaVenta : repository.findAllV2(id)) {
            if(notaVenta.getCustomer().getName().contains("Nombre")) {
                list.add(new NotaVentaResponseDto(notaVenta));
            }
        }
        return list;
    }

    @Override
    @Transactional
    public void guardar(NotaVentaResponseDto notaVentaResponseDto) {
        // Inicia la transaccion
        //log.info("Iniciando la transaccion");
        repository.saveAndFlush(NotaVenta.builder().build());
        // dadasda
        this.productService.saveTx(Product.builder().build());
        this.productService.saveTx(Product.builder().build());
        this.productService.save(Product.builder().build());
        //
        // dasdasdas
    }
    private void save(){

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void notificar(NotaVentaResponseDto notaVentaResponseDto) {
        // Notifiacion
    }
}
