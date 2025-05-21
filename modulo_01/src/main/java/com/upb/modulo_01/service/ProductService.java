package com.upb.modulo_01.service;

import com.upb.modulo_01.entity.Product;
import com.upb.modulo_01.entity.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> listAll();
    void saveTx(Product product);
    void save(Product product);
}
