package com.upb.modulo_01.service;

import com.upb.modulo_01.entity.Product;
import com.upb.modulo_01.entity.dto.ProductResponseDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long id);
    List<ProductResponseDto> listAll();
    void saveTx(Product product);
    void save(Product product) throws Exception;
    void update(Long id, String nombre, BigDecimal precio) throws Exception;
}
