package com.upb.modulo_01.repository;

import com.upb.modulo_01.entity.NotaVenta;
import com.upb.modulo_01.entity.dto.NotaVentaResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaVentaRepository extends JpaRepository<NotaVenta, Long> {
    //@Query("SELECT u.name, nv.date, nv.total FROM nota_venta nv INNER JOIN my_user u " +
     //       "ON (nv.customer_id=u.id) ")
    @Query("SELECT nv FROM NotaVenta nv INNER JOIN FETCH nv.customer c ")
    List<NotaVentaResponseDto> findAllV1();

    @Query("SELECT nv FROM NotaVenta nv " +
            "INNER JOIN FETCH MyUser u " +
            "ON (nv.customer.id=u.id)" +
            "WHERE u.id=:pUserId")
    List<NotaVenta> findAllV2(
            @Param("pUserId")Long pUserId);
}
