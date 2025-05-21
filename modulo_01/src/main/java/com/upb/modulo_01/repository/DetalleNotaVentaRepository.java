package com.upb.modulo_01.repository;

import com.upb.modulo_01.entity.DetalleNotaVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleNotaVentaRepository extends JpaRepository<DetalleNotaVenta, Long> {

}
