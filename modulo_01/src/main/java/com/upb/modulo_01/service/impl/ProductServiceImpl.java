package com.upb.modulo_01.service.impl;

import com.upb.modulo_01.entity.Product;
import com.upb.modulo_01.entity.dto.ProductResponseDto;
import com.upb.modulo_01.repository.ProductoRespository;
import com.upb.modulo_01.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductoRespository repository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ProductResponseDto> listAll() {
        return repository.findAllStock();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveTx(Product product) {
        repository.save(product);
    }

    @Override
    @Transactional
    public void save(Product product) throws Exception {
        log.info("[save], Iniciando la transaccion");
        //logService.info("[save], Iniciando la transaccion");
        if(this.repository.existsByNameContainingIgnoreCase(product.getName())) {
           throw new Exception("YA hay un producto con el mismo nombre");
        }
        log.info("[save], Iniciando la transaccion");

        log.info("[save], Iniciando la transaccion");

        log.info("[save], Iniciando la transaccion");
        this.repository.save(product);
        log.info("[save], Producto guardado con exito");
    }

    @Override
    @Transactional
    public void update(Long id, String nombre, BigDecimal precio) throws Exception {
        Optional<Product> optionalProduct = this.repository.findById(id);
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(nombre);
            product.setPrice(precio);
        }
    }
}
