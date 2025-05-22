package com.upb.modulo_01.repository;

import com.upb.modulo_01.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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



}
