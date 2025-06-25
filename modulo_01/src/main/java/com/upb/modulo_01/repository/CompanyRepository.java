package com.upb.modulo_01.repository;

import com.upb.modulo_01.entity.Company;
import com.upb.modulo_01.entity.dto.CompanyResponseDto;
import com.upb.modulo_01.entity.enums.StateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByName(String name);
    List<Company> findByNit(String nit);

    List<Company> findByNameContainingIgnoreCase(String name);

    @Query("SELECT c FROM Company c " +
            "WHERE c.name=:pName AND c.nit=:pNit")
    List<Company> findByNombreYNit(
            @Param("pName") String name,
            @Param("pNit") String nit);

    List<Company> findByNameAndNit(String name, String nit);

    @Query(value = "SELECT c FROM Company c " +
            "WHERE ( :pNit IS NULL OR c.nit=:pNit)" +
            " AND ( :pName IS NULL OR c.name=:pName) " +
            " AND c.createdDate BETWEEN :pFrom AND :pTo",

            countQuery = "SELECT count (c) FROM Company c " +
            "WHERE ( :pNit IS NULL OR c.nit=:pNit)" +
            " AND ( :pName IS NULL OR c.name=:pName) " +
            " AND c.createdDate BETWEEN :pFrom AND :pTo")
    Page<CompanyResponseDto> findAll(
            @Param("pNit")String nit,
            @Param("pFrom") Date from,
            @Param("pTo") Date to,
            @Param("pName")String name, Pageable pageable);

    @Modifying
    @Query("UPDATE Company c " +
            "SET c.nit=:pNit, c.name=:pName, c.state=:pState" +
            " WHERE c.id=:pId")
    void updateCompany(
            @Param("pId")Long id,
            @Param("pNit")String nit,
            @Param("pName")String name,
            @Param("pState")StateEntity state);
}
