package com.upb.modulo_01.repository;

import com.upb.modulo_01.entity.Company;
import com.upb.modulo_01.entity.Product;
import com.upb.modulo_01.entity.dto.ProductResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRespository extends JpaRepository<Product, Long> {
    @Query("select p " +
            "FROM Product p where  p.stock>1")
    List<ProductResponseDto> findAllStock();


    @Query("SELECT new com.upb.modulo_01.entity.dto.ProductResponseDto(a.id, a.name, a.description)" +
            " FROM Product a WHERE  a.stock>1")
    List<ProductResponseDto> findAllStockV();

    @Query("SELECT new com.upb.modulo_01.entity.dto.ProductResponseDto(a.id, a.name, a.description, new java.math.BigDecimal(2))" +
            " FROM Product a WHERE  a.stock>1")
    List<ProductResponseDto> findAllStockV3();

    List<ProductResponseDto> findByCompany(Company company);

    List<Product> findByCompany_Id(Long companyId);

    @Query("SELECT p FROM Product p WHERE p.id=:pId AND p.stock>0")
    Optional<Product> findPorID(Long id);

    boolean existsByNameContainingIgnoreCase(String name);
}
