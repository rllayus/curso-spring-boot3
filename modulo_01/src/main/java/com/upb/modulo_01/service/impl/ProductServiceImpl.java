package com.upb.modulo_01.service.impl;

import com.upb.modulo_01.entity.Product;
import com.upb.modulo_01.entity.dto.ProductResponseDto;
import com.upb.modulo_01.repository.ProductoRespository;
import com.upb.modulo_01.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductoRespository repository;

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
    public void save(Product product) {

    }
}
